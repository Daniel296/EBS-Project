package pubsub.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import pubsub.model.Publication;
import pubsub.model.Subscription;

public class Validator {

    public static Publication convertMapToPublication(Map entry) throws NumberFormatException, ParseException
    {
        String patientName = (String) entry.get("name");
        String dateOfBirth = (String) entry.get("birthdate");
        Double height = (Double) entry.get("height");
        String eyeColor = (String) entry.get("eyeColor");
        Long heartRate = (Long) entry.get("heartRate");

        return new Publication(patientName, new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth), height, eyeColor,
            heartRate.intValue());

    }

    public static boolean checkPublicationWithSubscription(Publication publication, Subscription subscription)
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
