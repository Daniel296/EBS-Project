package pubsub.bolt;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import pubsub.utils.Generator;
import pubsub.utils.Validator;

public class ContentFilterBolt extends BaseRichBolt
{
    private static final long serialVersionUID = 1L;

    private OutputCollector collector;

    private Subscription subscription;

    private List<Map> unsucessfullEntries;
    
    private boolean willFail;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector)
    {
        this.collector = collector;
        unsucessfullEntries = new ArrayList<>();
    }
    
    public ContentFilterBolt() 
    {
    	
    }
    
    public ContentFilterBolt(Subscription subscription) 
    {
    	super();
        this.subscription = subscription;
    }

    public ContentFilterBolt(Subscription subscription, boolean willFail)
    {
        super();
        this.subscription = subscription;
        this.willFail = willFail;
    }

    @SuppressWarnings({"rawtypes"})
    public void execute(Tuple input)
    {
        /**
         * [{"birthdate":"1981-02-18","eyeColor":"Blue","heartRate":"60","name":"Allan", "height":"1.66"}]
         */

        Map entry = (Map) input.getValueByField("subscriptions");

        Publication publication = null;
        try {
            publication = Validator.convertMapToPublication(entry);
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        Map<Class, Object> filteredEntry = null;
        // System.out.println("Before check");
        try {
            if (publication != null && Validator.checkPublicationWithSubscription(publication, subscription)) {
                filteredEntry = new HashMap<>();
                filteredEntry.put(Publication.class, publication);
                filteredEntry.put(Subscription.class, subscription);
                Date sentTime = (Date) entry.get("date");

                filteredEntry.put(Date.class, sentTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

		if (willFail && Generator.getRandomNumber(1, 1000) % 99 == 0) {
			try {
				throw new InterruptedException("Woops! Bad luck!");
			} catch (InterruptedException e) {
				e.getMessage();
				if (filteredEntry != null) {
					//adding entries for future retry
					unsucessfullEntries.add(filteredEntry);
				}
				System.out.println("Thread was interrupted, Failed to complete operation");
				return;
			}
		}
		
		if(filteredEntry != null) {
			this.collector.emit(new Values(filteredEntry));
		}
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
        declarer.declare(new Fields("subscription"));
		/* declarer.declareStream("failStream",new Fields("fail")); */

    }
    
    @Override
    public void cleanup() {
    	if (!unsucessfullEntries.isEmpty()) {
    		System.out.println("Unsucessfull entries:" + unsucessfullEntries);
			/* this.collector.emit(new Values(unsucessfullEntries)); */
    	}
    }

}
