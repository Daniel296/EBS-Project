package pubsub;

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

@SuppressWarnings("serial")
public class SourceTextSpout extends BaseRichSpout
{

    private SpoutOutputCollector collector;

    private JSONArray sourceFeed;

    // private String[] sourcetext = {"text one", "text two", "text three", "text four", "too much text after one"};
//    [{"birthdate":"1981-02-18","eyeColor":"Blue","heartRate":"60","name":"Allan","height":"1.66"}]

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
        // TODO Auto-generated method stub
        declarer.declare(new Fields("words"));
    }

    private JSONArray readSourceFile()
    {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(App.FEED_NAME)) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            return (JSONArray) obj;

            // for (int i = 0; i < App.MAX_MESSAGES; i++) {
            // JSONObject publicationJSON = (JSONObject) publications.get(i);
            // Publication publication = new Publication(publicationJSON.get("name").toString(),
            // Date.valueOf(publicationJSON.get("birthdate").toString()),
            // Double.parseDouble(publicationJSON.get("height").toString()),
            // publicationJSON.get("eyeColor").toString(),
            // Integer.parseInt(publicationJSON.get("heartRate").toString()));
            // publisher.publish(publication, pubSubService);
            // }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
