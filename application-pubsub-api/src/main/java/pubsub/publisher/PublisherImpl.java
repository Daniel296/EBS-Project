package pubsub.publisher;

import pubsub.Publication;
import pubsub.service.PubSubService;

public class PublisherImpl implements Publisher
{
    // Publishes new publication to PubSubService
    public void publish(Publication publication, PubSubService pubSubService)
    {
        pubSubService.addPublicationToQueue(publication);
    }
}
