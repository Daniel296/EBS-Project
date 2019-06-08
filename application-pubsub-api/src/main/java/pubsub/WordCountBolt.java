package pubsub;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class WordCountBolt extends BaseRichBolt
{

    private OutputCollector collector;

    private HashMap<String, Integer> count;

    private Set<Map> maps;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector)
    {
        // TODO Auto-generated method stub
        this.collector = collector;
        this.count = new HashMap<String, Integer>();
        this.maps = new LinkedHashSet<>();

    }

    public void execute(Tuple input)
    {

        Map entries = (Map) input.getValueByField("word");

        if (maps.contains(entries)) {
            return;
        }
        maps.add(entries);

        String word = (String) entries.get("eyeColor");
        Integer wordcount = this.count.get(word);
        // TODO Auto-generated method stub
        if (wordcount == null) {
            wordcount = 0;
        }
        if (entries.get("eyeColor").equals("Blue")) {
            wordcount++;
            this.count.put(word, wordcount);
            this.collector.emit(new Values(word, wordcount));
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
        // TODO Auto-generated method stub
        declarer.declare(new Fields("word", "count"));
    }

}
