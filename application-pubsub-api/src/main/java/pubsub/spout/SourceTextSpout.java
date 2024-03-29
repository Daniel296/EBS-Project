package pubsub.spout;

import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import pubsub.App;

public class SourceTextSpout extends BaseRichSpout
{
    private static final long serialVersionUID = 1L;

    private SpoutOutputCollector collector;

    private JSONArray sourceFeed;

    private int i = 0;

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector)
    {
        this.collector = collector;
        this.sourceFeed = readSourceFile();
    }

    @SuppressWarnings("unchecked")
    public void nextTuple()
    {
        JSONObject entry = (JSONObject) sourceFeed.toArray()[i];
        entry.put("date", new Date());

        this.collector.emit(new Values(entry));
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
        declarer.declare(new Fields("subscriptions"));
    }

    /**
     * Sample of generated feed. [{"birthdate":"1981-02-18","eyeColor":"Blue","heartRate":"60","name":"Allan",
     * "height":"1.66"}]
     */
    private JSONArray readSourceFile()
    {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        File file = new File(classLoader.getResource(App.FEED_NAME).getFile());

        try (FileReader reader = new FileReader(file)) {
            // Read JSON file
            Object obj = (JSONArray) jsonParser.parse(reader);

            return (JSONArray) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
