package pubsub;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class SplitTextBolt extends BaseRichBolt {
    
    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        // TODO Auto-generated method stub
        this.collector = collector;

    }

    public void execute(Tuple input) {
        
        // private String[] sourcetext = {"text one", "text two", "text three", "text four", "too much text after one"};
//      [{"birthdate":"1981-02-18","eyeColor":"Blue","heartRate":"60","name":"Allan","height":"1.66"}]
        
        
        // TODO Auto-generated method stub
//        String sourcetext = input.getStringByField("words");
        Map entries = (Map) input.getValueByField("words");
        
        this.collector.emit(new Values(entries));
////        String[] words = sourcetext.split(" ");
//        for(Etr word : words) {
//            this.collector.emit(new Values(word));
//        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        declarer.declare(new Fields("word"));

    }

}
