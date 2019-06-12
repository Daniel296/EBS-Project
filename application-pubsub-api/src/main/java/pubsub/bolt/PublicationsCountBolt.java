package pubsub.bolt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import pubsub.model.Publication;
import pubsub.model.Subscription;
import pubsub.utils.AverageCalculator;

public class PublicationsCountBolt extends BaseRichBolt
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private OutputCollector collector;

    private HashMap<Subscription, Integer> count;
    
    private double averageDelay;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector)
    {
        this.collector = collector;
        this.count = new HashMap<Subscription, Integer>();

    }

    public void execute(Tuple input)
    {
        Date receivedTime = new Date();
        Map<Class, Object> entry = (Map<Class, Object>) input.getValueByField("subscription");
        
        if (entry == null) {
        	return;
        }
        
        Subscription sub = (Subscription) entry.get(Subscription.class);
        
        Date sentTime = (Date) entry.get(Date.class);
        
        long diff = receivedTime.getTime() - sentTime.getTime();
        Publication pub = (Publication) entry.get(Publication.class);
        System.out.println("Delay for publication " + pub + " is " + diff + " milliseconds");
        
        AverageCalculator instance = AverageCalculator.getInstance();
        instance.pushDelay(diff);
        
        
        Integer subCount = this.count.get(sub);

        if (subCount == null) {
            subCount = 0;
        }
        subCount++;
        this.count.put(sub, subCount);
        this.collector.emit(new Values(entry, subCount));

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
        declarer.declare(new Fields("subscription", "count"));
    }

}
