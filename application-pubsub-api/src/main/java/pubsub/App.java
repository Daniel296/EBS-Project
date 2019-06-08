package pubsub;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class App
{
    private static final String SPOUT_ID = "source_text_spout";

    private static final String SPLIT_BOLT_ID = "split_bolt";

    private static final String COUNT_BOLT_ID = "count_bolt";

    private static final String TERMINAL_BOLT_ID = "terminal_bolt";

    private static final boolean GENERATE_PUBLICATIONS = false;

    public static final String FEED_NAME = "publications.json";

    public static final int MAX_MESSAGES = 100;

    public static void main(String[] args)
    {
        if (GENERATE_PUBLICATIONS) {
            generatePublications();
        }

        TopologyBuilder builder = new TopologyBuilder();
        SourceTextSpout spout = new SourceTextSpout();
        SplitTextBolt splitbolt = new SplitTextBolt();
        WordCountBolt countbolt = new WordCountBolt();
        TerminalBolt terminalbolt = new TerminalBolt();

        builder.setSpout(SPOUT_ID, spout);
        builder.setBolt(SPLIT_BOLT_ID, splitbolt).shuffleGrouping(SPOUT_ID);
        builder.setBolt(COUNT_BOLT_ID, countbolt).fieldsGrouping(SPLIT_BOLT_ID, new Fields("word"));
        builder.setBolt(TERMINAL_BOLT_ID, terminalbolt).globalGrouping(COUNT_BOLT_ID);

        Config config = new Config();
        if (args != null && args.length > 0) {
            config.setNumWorkers(3);

            try {
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } catch (AlreadyAliveException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (AuthorizationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("count_topology", config, builder.createTopology());

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            cluster.killTopology("count_topology");
            cluster.shutdown();

        }
    }

    @SuppressWarnings("unchecked")
    private static void generatePublications()
    {
        // Generate the Publications feed in JSON format.
        JSONArray publications = new JSONArray();
        for (int i = 0; i < MAX_MESSAGES; i++) {
            JSONObject publication = new JSONObject();
            publication.put("name", Generator.getRandomName());
            publication.put("birthdate", Generator.getRandomDate());
            publication.put("height", Generator.getRandomHeight());
            publication.put("eyeColor", Generator.getRandomEyeColor());
            publication.put("heartRate", Generator.getRandomHeartRate());
            publications.add(publication);
        }

        writeToFile(publications, FEED_NAME);
    }

    private static void writeToFile(JSONArray publications, String fileName)
    {
        File file = new File(fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            bufferedOutputStream.write((publications.toString()).getBytes());

            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
