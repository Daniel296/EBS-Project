package pubsub.bolt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class ContentFilterBolt extends BaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OutputCollector collector;

	private Subscription subscription;

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	public ContentFilterBolt(Subscription subscription) {
		super();
		this.subscription = subscription;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Tuple input) {

		/**
		 * [{"birthdate":"1981-02-18","eyeColor":"Blue","heartRate":"60","name":"Allan", "height":"1.66"}]
		 */
		Map entry = (Map) input.getValueByField("words");
		Publication publication = null;
		try {
			publication = convertMapToPublication(entry);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}

		if (publication != null && checkPublicationWithSubscription(publication, subscription)) {
			Map<Class, Object> filteredEntry = new HashMap<>();
			filteredEntry.put(Publication.class, publication);
			filteredEntry.put(Subscription.class, subscription);
			this.collector.emit(new Values(entry));
		}

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("subscription"));

	}

	private Publication convertMapToPublication(Map entry) throws NumberFormatException, ParseException {
		String patientName = (String) entry.get("patientName");
		String dateOfBirth = (String) entry.get("dateOfBirth");
		String height = (String) entry.get("height");
		String eyeColor = (String) entry.get("eyeColor");
		String heartRate = (String) entry.get("heartRate");

		return new Publication(patientName, new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth),
				Double.parseDouble(height), eyeColor, Integer.parseInt(heartRate));

	}

	private boolean checkPublicationWithSubscription(Publication publication, Subscription subscription) {
		boolean patientName = false;
		switch (subscription.getPatientNameOperator()) {
		case 0: // operator is 0 for '='
			if (publication.getPatientName().equals(subscription.getPatientName())) {
				patientName = true;
			}
			break;
		case 1: // operator is 1 for '!='
			if (!publication.getPatientName().equals(subscription.getPatientName())) {
				patientName = true;
			}
			break;
		}

		boolean dateOfBirth = false;
		switch (subscription.getDateOfBirdOperator()) {
		case 0: // operator is 0 for '='
			if (publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) == 0) {
				dateOfBirth = true;
			}
			break;
		case 1: // operator is 1 for '!='
			if (publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) != 0) {
				dateOfBirth = true;
			}
			break;
		case 2: // operator is 2 for '>'
			if (publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) > 0) {
				dateOfBirth = true;
			}
			break;
		case 3: // operator is 3 for '<'
			if (publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) < 0) {
				dateOfBirth = true;
			}
			break;
		case 4: // operator is 4 for '>='
			if (publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) >= 0) {
				dateOfBirth = true;
			}
			break;
		case 5: // operator is 5 for '<='
			if (publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) <= 0) {
				dateOfBirth = true;
			}
			break;
		}

		boolean heartRate = false;
		switch (subscription.getHeartRateOperator()) {
		case 0: // operator is 0 for '='
			if (publication.getHeartRate() == subscription.getHeartRate()) {
				heartRate = true;
			}
			break;
		case 1: // operator is 1 for '!='
			if (publication.getHeartRate() != subscription.getHeartRate()) {
				heartRate = true;
			}
			break;
		case 2: // operator is 2 for '>'
			if (publication.getHeartRate() > subscription.getHeartRate()) {
				heartRate = true;
			}
			break;
		case 3: // operator is 3 for '<'
			if (publication.getHeartRate() < subscription.getHeartRate()) {
				heartRate = true;
			}
			break;
		case 4: // operator is 4 for '>='
			if (publication.getHeartRate() >= subscription.getHeartRate()) {
				heartRate = true;
			}
			break;
		case 5: // operator is 5 for '<='
			if (publication.getHeartRate() <= subscription.getHeartRate()) {
				heartRate = true;
			}
			break;
		}

		return patientName && heartRate && dateOfBirth;

	}

}
