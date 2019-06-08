package pubsub.subscriber;

import java.util.HashSet;
import java.util.Set;

import pubsub.Publication;
import pubsub.Subscription;
import pubsub.service.PubSubService;

public abstract class Subscriber
{
    // store all publications received by the subscriber
    private Set<Publication> subscriberPublications = new HashSet<Publication>();

    public Set<Publication> getSubscriberPublications()
    {
        return subscriberPublications;
    }

    public void setSubscriberPublications(Set<Publication> subscriberPublications)
    {
        this.subscriberPublications = subscriberPublications;
    }

    // Add subscriber with PubSubService for a topic
    public abstract void addSubscriber(Subscription subscription, PubSubService pubSubService);

    // Unsubscribe subscriber with PubSubService for a topic
    public abstract void unSubscribe(Subscription subscription, PubSubService pubSubService);

    // Request specifically for publications related to topic from PubSubService
    public abstract void getPublicationsForSubscriberOfTopic(Subscription subscription, PubSubService pubSubService);

    // Print all publications received by the subscriber
    public void printPublications()
    {
        for (Publication publication : subscriberPublications) {
            System.out.println(publication);
        }
    }
}
