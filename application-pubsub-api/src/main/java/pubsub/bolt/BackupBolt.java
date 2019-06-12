package pubsub.bolt;

import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class BackupBolt extends BaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Tuple input) {
		List<Map<Class, Object>> failedEntries = (List<Map<Class, Object>>) input.getValueByField("fail");
		
		for(Map<Class, Object> failedEntry : failedEntries) {
			this.collector.emit(new Values(failedEntry));
		}

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("subscription"));

	}
}
