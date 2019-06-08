package pubsub.service;

import java.util.*;

import pubsub.Publication;
import pubsub.Subscription;
import pubsub.subscriber.Subscriber;

public class PubSubService {
    //Keeps set of subscriber topic wise, using set to prevent duplicates
    private Map<Subscription, Set<Subscriber>> subscribersTopicMap = new HashMap<Subscription, Set<Subscriber>>();

    //Holds publications published by publishers
    private Queue<Publication> publicationsQueue = new LinkedList<Publication>();

    //Adds publication sent by publisher to queue
    public void addPublicationToQueue(Publication publication){
        publicationsQueue.add(publication);
    }

    //Add a new Subscriber for a topic
    public void addSubscriber(Subscription subscription, Subscriber subscriber){

        if(subscribersTopicMap.containsKey(subscription)){
            Set<Subscriber> subscribers = subscribersTopicMap.get(subscription);
            subscribers.add(subscriber);
            subscribersTopicMap.put(subscription, subscribers);
        }else{
            Set<Subscriber> subscribers = new HashSet<Subscriber>();
            subscribers.add(subscriber);
            subscribersTopicMap.put(subscription, subscribers);
        }
    }

    //Remove an existing subscriber for a topic
    public void removeSubscriber(Subscription subscription, Subscriber subscriber){
        if(subscribersTopicMap.containsKey(subscription)){
            Set<Subscriber> subscribers = subscribersTopicMap.get(subscription);
            subscribers.remove(subscriber);
            subscribersTopicMap.put(subscription, subscribers);
        }
    }

    //Broadcast new publications added in queue to All subscribers of the topic. publicationsQueue will be empty after broadcasting
    public void broadcast() {
        if(publicationsQueue.isEmpty()) {
            System.out.println("No publications from publishers to display");
        } else {
            for(Publication publication : publicationsQueue) {
                // we check for each publication whether there is any subscription that match it
                for(Map.Entry<Subscription, Set<Subscriber>> entry : subscribersTopicMap.entrySet()) {
                    Subscription subscription = entry.getKey();
                    if(checkPublicationWithSubscription(publication, subscription)) {
                        Set<Subscriber> subscribersOfSubscription = entry.getValue();

                        //add broadcasted publication to subscribers publication queue
                        for(Subscriber subscriber : subscribersOfSubscription){
                            Set<Publication> subscriberPublications = subscriber.getSubscriberPublications();
                            subscriberPublications.add(publication);
                            subscriber.setSubscriberPublications(subscriberPublications);
                        }
                    }
                }
            }
        }
    }

    private boolean checkPublicationWithSubscription(Publication publication, Subscription subscription) {
        boolean patientName = false;
        switch (subscription.getPatientNameOperator()) {
            case 0: // operator is 0 for '='
                if(publication.getPatientName().equals(subscription.getPatientName())) {
                    patientName = true;
                }
                break;
            case 1: // operator is 1 for '!='
                if(!publication.getPatientName().equals(subscription.getPatientName())) {
                    patientName = true;
                }
                break;
        }

        boolean dateOfBirth = false;
        switch (subscription.getDateOfBirdOperator()) {
            case 0: // operator is 0 for '='
                if(publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) == 0) {
                    dateOfBirth = true;
                }
                break;
            case 1: // operator is 1 for '!='
                if(publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) != 0) {
                    dateOfBirth = true;
                }
                break;
            case 2: // operator is 2 for '>'
                if(publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) > 0) {
                    dateOfBirth = true;
                }
                break;
            case 3: // operator is 3 for '<'
                if(publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) < 0) {
                    dateOfBirth = true;
                }
                break;
            case 4: // operator is 4 for '>='
                if(publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) >= 0) {
                    dateOfBirth = true;
                }
                break;
            case 5: //operator is 5 for '<='
                if(publication.getDateOfBirth().compareTo(subscription.getDateOfBirth()) <= 0) {
                    dateOfBirth = true;
                }
                break;
        }

        boolean heartRate = false;
        switch (subscription.getHeartRateOperator()) {
            case 0: // operator is 0 for '='
                if(publication.getHeartRate() == subscription.getHeartRate()) {
                    heartRate = true;
                }
                break;
            case 1: // operator is 1 for '!='
                if(publication.getHeartRate() != subscription.getHeartRate()) {
                    heartRate = true;
                }
                break;
            case 2: // operator is 2 for '>'
                if(publication.getHeartRate() > subscription.getHeartRate()) {
                    heartRate = true;
                }
                break;
            case 3: // operator is 3 for '<'
                if(publication.getHeartRate() < subscription.getHeartRate()) {
                    heartRate = true;
                }
                break;
            case 4: // operator is 4 for '>='
                if(publication.getHeartRate() >= subscription.getHeartRate()) {
                    heartRate = true;
                }
                break;
            case 5: //operator is 5 for '<='
                if(publication.getHeartRate() <= subscription.getHeartRate()) {
                    heartRate = true;
                }
                break;
        }

        return patientName && heartRate && dateOfBirth;

    }

    //Sends publications about a topic for subscriber at any point
//    public void getPublicationsForSubscriberOfTopic(Subscription subscription, Subscriber subscriber) {
//        if(publicationsQueue.isEmpty()){
//            System.out.println("No publications from publishers to display");
//        }else{
//            while(!publicationsQueue.isEmpty()){
//                Publication publication = publicationsQueue.remove();
//
//                if(publication.getTopic().equalsIgnoreCase(subscription)){
//
//                    Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(subscription);
//
//                    for(Subscriber _subscriber : subscribersOfTopic){
//                        if(_subscriber.equals(subscriber)){
//                            //add broadcasted publication to subscriber publication queue
//                            List<Publication> subscriberPublications = subscriber.getSubscriberPublications();
//                            subscriberPublications.add(publication);
//                            subscriber.setSubscriberPublications(subscriberPublications);
//                        }
//                    }
//                }
//            }
//        }
//    }



}