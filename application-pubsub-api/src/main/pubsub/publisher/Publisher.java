package main.pubsub.publisher;

import main.pubsub.Message;
import main.pubsub.service.PubSubService;

public interface Publisher {
    //Publishes new message to PubSubService
    void publish(Message message, PubSubService pubSubService);
}