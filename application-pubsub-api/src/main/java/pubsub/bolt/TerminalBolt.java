package pubsub.bolt;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import pubsub.model.Subscription;

public class TerminalBolt extends BaseRichBolt {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Subscription, Integer> count;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.count = new HashMap<Subscription, Integer>();

    }

    public void execute(Tuple input) {
    	Map<Class,Object> entry = (Map<Class,Object>) input.getValueByField("subscription");
    	Subscription sub = (Subscription) entry.get(Subscription.class);
        Integer count = input.getIntegerByField("count");
        this.count.put(sub, count);
        
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub

    }
    
    public void cleanup() {
        System.out.println("Topology Result:");
        for (Entry<Subscription, Integer> entry : this.count.entrySet()) {
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
    }

}
