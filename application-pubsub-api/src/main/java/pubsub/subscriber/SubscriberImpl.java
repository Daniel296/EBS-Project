package pubsub.subscriber;

import pubsub.Subscription;
import pubsub.service.PubSubService;

public class SubscriberImpl extends Subscriber{
    //Add subscriber with PubSubService for a topic
    public void addSubscriber(Subscription subscription, PubSubService pubSubService){
        pubSubService.addSubscriber(subscription, this);
    }

    //Unsubscribe subscriber with PubSubService for a topic
    public void unSubscribe(Subscription subscription, PubSubService pubSubService){
        pubSubService.removeSubscriber(subscription, this);
    }

    //Request specifically for publications related to topic from PubSubService
    public void getPublicationsForSubscriberOfTopic(Subscription subscription, PubSubService pubSubService) {
//        pubSubService.getPublicationsForSubscriberOfTopic(subscription, this);

    }
}