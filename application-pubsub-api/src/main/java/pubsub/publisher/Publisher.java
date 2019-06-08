package pubsub.publisher;

import pubsub.Publication;
import pubsub.service.PubSubService;

public interface Publisher
{
    // Publishes new publication to PubSubService
    void publish(Publication publication, PubSubService pubSubService);
}
