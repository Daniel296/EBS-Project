package pubsub.spout;

import java.io.FileReader;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import pubsub.App;

@SuppressWarnings("serial")
public class SourceTextSpout extends BaseRichSpout
{

    private SpoutOutputCollector collector;

    private JSONArray sourceFeed;

    private int i = 0;

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector)
    {
        // TODO Auto-generated method stub
        this.collector = collector;
        this.sourceFeed = readSourceFile();
    }

    public void nextTuple()
    {
        // TODO Auto-generated method stub

        this.collector.emit(new Values(sourceFeed.toArray()[i]));
        i++;
        if (i >= sourceFeed.toArray().length) {
            i = 0;
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
        declarer.declare(new Fields("words"));
    }

    /*
	 * Sample of generated feed.
	 * [{"birthdate":"1981-02-18","eyeColor":"Blue","heartRate":"60","name":"Allan", "height":"1.66"}]
	 */
    private JSONArray readSourceFile()
    {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(App.FEED_NAME)) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            return (JSONArray) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
