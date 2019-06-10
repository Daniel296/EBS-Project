package pubsub;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

import pubsub.bolt.ContentFilterBolt;
import pubsub.bolt.TerminalBolt;
import pubsub.bolt.PublicationsCountBolt;
import pubsub.model.Subscription;
import pubsub.spout.SourceTextSpout;

public class App
{
    private static final String SPOUT_ID = "source_text_spout";

    private static final String FILTER_BOLT_1 = "filter_bolt1";

    private static final String FILTER_BOLT_2 = "filter_bolt2";

    private static final String FILTER_BOLT_3 = "filter_bolt3";

    private static final String COUNT_BOLT_ID = "count_bolt";

    private static final String TERMINAL_BOLT_ID = "terminal_bolt";

    private static final boolean GENERATE_PUBLICATIONS = true;

    public static final String FEED_NAME = "publications.json";

    public static final int MAX_MESSAGES = 5000;

    public static void main(String[] args)
    {
        if (GENERATE_PUBLICATIONS) {
            generatePublications();
        }

        Subscription firstSubscription = new Subscription(Generator.getRandomName(), Generator.getRandomNameOperator(),
            Generator.getRandomHeartRate(), Generator.getRandomOperator(), Generator.getRandomDate(),
            Generator.getRandomOperator());
        Subscription secondSubscription = new Subscription(Generator.getRandomName(), Generator.getRandomNameOperator(),
            Generator.getRandomHeartRate(), Generator.getRandomOperator(), Generator.getRandomDate(),
            Generator.getRandomOperator());
        Subscription thirdSubscription = new Subscription(Generator.getRandomName(), Generator.getRandomNameOperator(),
            Generator.getRandomHeartRate(), Generator.getRandomOperator(), Generator.getRandomDate(),
            Generator.getRandomOperator());

        TopologyBuilder builder = new TopologyBuilder();
        SourceTextSpout spout = new SourceTextSpout();
        ContentFilterBolt firstContentFilter = new ContentFilterBolt(firstSubscription);
        ContentFilterBolt secondContentFilter = new ContentFilterBolt(secondSubscription);
        ContentFilterBolt thirdContentFilter = new ContentFilterBolt(thirdSubscription);

        PublicationsCountBolt countbolt = new PublicationsCountBolt();
        TerminalBolt terminalbolt = new TerminalBolt();

        builder.setSpout(SPOUT_ID, spout);
        builder.setBolt(FILTER_BOLT_1, firstContentFilter).shuffleGrouping(SPOUT_ID);
        builder.setBolt(FILTER_BOLT_2, secondContentFilter).shuffleGrouping(SPOUT_ID);
        builder.setBolt(FILTER_BOLT_3, thirdContentFilter).shuffleGrouping(SPOUT_ID);
        builder.setBolt(COUNT_BOLT_ID, countbolt).fieldsGrouping(FILTER_BOLT_3, new Fields("subscription"))
            .fieldsGrouping(FILTER_BOLT_2, new Fields("subscription"))
            .fieldsGrouping(FILTER_BOLT_3, new Fields("subscription"));

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
                Thread.sleep(20000);
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
        FileOutputStream fout = null;

        try {
            fout = new FileOutputStream("src/main/resources/" + fileName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fout);

            bufferedOutputStream.write((publications.toString()).getBytes());

            bufferedOutputStream.close();
            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
