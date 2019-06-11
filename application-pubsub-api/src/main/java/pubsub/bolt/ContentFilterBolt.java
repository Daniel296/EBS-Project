package pubsub.bolt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import pubsub.Generator;
import pubsub.model.Publication;
import pubsub.model.Subscription;

public class ContentFilterBolt extends BaseRichBolt
{
    private static final long serialVersionUID = 1L;

    private OutputCollector collector;

    private Subscription subscription;

    private List<Map> unsucessfullEntries;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector)
    {
        this.collector = collector;
        unsucessfullEntries = new ArrayList<>();
    }

    public ContentFilterBolt(Subscription subscription)
    {
        super();
        this.subscription = subscription;
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
            publication = convertMapToPublication(entry);
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        Map<Class, Object> filteredEntry = null;
        // System.out.println("Before check");
        try {
            if (publication != null && checkPublicationWithSubscription(publication, subscription)) {
                filteredEntry = new HashMap<>();
                filteredEntry.put(Publication.class, publication);
                filteredEntry.put(Subscription.class, subscription);
                Date sentTime = (Date) entry.get("date");

                filteredEntry.put(Date.class, sentTime);

                System.out.println("Content filtering: " + filteredEntry);

                this.collector.emit(new Values(filteredEntry));

            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /* Fail scenario
         * if (Generator.getRandomNumber(1, 100) % 98 == 0) {
            try {
                throw new InterruptedException("Woops! Bad luck!");
            } catch (InterruptedException e) {
                e.getMessage();
                if (filteredEntry != null) {
                    unsucessfullEntries.add(filteredEntry);
                }

                System.out.println("Thread was interrupted, Failed to complete operation");
            }

        }*/

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
        declarer.declare(new Fields("subscription"));

    }

    private Publication convertMapToPublication(Map entry) throws NumberFormatException, ParseException
    {
        String patientName = (String) entry.get("name");
        String dateOfBirth = (String) entry.get("birthdate");
        Double height = (Double) entry.get("height");
        String eyeColor = (String) entry.get("eyeColor");
        Long heartRate = (Long) entry.get("heartRate");

        return new Publication(patientName, new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth), height, eyeColor,
            heartRate.intValue());

    }

    private boolean checkPublicationWithSubscription(Publication publication, Subscription subscription)
        throws ParseException
    {
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
                if (publication.getDateOfBirth()
                    .compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(subscription.getDateOfBirth())) == 0) {
                    dateOfBirth = true;
                }
                break;
            case 1: // operator is 1 for '!='
                if (publication.getDateOfBirth()
                    .compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(subscription.getDateOfBirth())) != 0) {
                    dateOfBirth = true;
                }
                break;
            case 2: // operator is 2 for '>'
                if (publication.getDateOfBirth()
                    .compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(subscription.getDateOfBirth())) > 0) {
                    dateOfBirth = true;
                }
                break;
            case 3: // operator is 3 for '<'
                if (publication.getDateOfBirth()
                    .compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(subscription.getDateOfBirth())) < 0) {
                    dateOfBirth = true;
                }
                break;
            case 4: // operator is 4 for '>='
                if (publication.getDateOfBirth()
                    .compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(subscription.getDateOfBirth())) >= 0) {
                    dateOfBirth = true;
                }
                break;
            case 5: // operator is 5 for '<='
                if (publication.getDateOfBirth()
                    .compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(subscription.getDateOfBirth())) <= 0) {
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
