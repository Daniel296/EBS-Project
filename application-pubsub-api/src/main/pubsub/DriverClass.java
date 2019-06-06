package main.pubsub;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import main.pubsub.publisher.Publisher;
import main.pubsub.publisher.PublisherImpl;
import main.pubsub.service.PubSubService;
import main.pubsub.subscriber.Subscriber;
import main.pubsub.subscriber.SubscriberImpl;

public class DriverClass
{
    private static final int MAX_MESSAGES = 100;
    
    private static final boolean GENERATE_PUBLICATIONS = false;

    public static void main(String[] args)
    {
        if (GENERATE_PUBLICATIONS) {
            generatePublications();
        }
        

        // Instantiate publishers, subscribers and PubSubService
        Publisher javaPublisher = new PublisherImpl();
        Publisher pythonPublisher = new PublisherImpl();

        Subscriber javaSubscriber = new SubscriberImpl();
        Subscriber allLanguagesSubscriber = new SubscriberImpl();
        Subscriber pythonSubscriber = new SubscriberImpl();

        PubSubService pubSubService = new PubSubService();

        // Declare Messages and Publish Messages to PubSubService
        Message javaMsg1 = new Message("Java", "Core Java Concepts");
        Message javaMsg2 = new Message("Java", "Spring MVC : Dependency Injection and AOP");
        Message javaMsg3 = new Message("Java", "JPA & Hibernate");

        javaPublisher.publish(javaMsg1, pubSubService);
        javaPublisher.publish(javaMsg2, pubSubService);
        javaPublisher.publish(javaMsg3, pubSubService);

        Message pythonMsg1 = new Message("Python", "Easy and Powerful programming language");
        Message pythonMsg2 = new Message("Python", "Advanced Python message");

        pythonPublisher.publish(pythonMsg1, pubSubService);
        pythonPublisher.publish(pythonMsg2, pubSubService);

        // Declare Subscribers
        javaSubscriber.addSubscriber("Java", pubSubService); // Java subscriber only subscribes to Java topics
        pythonSubscriber.addSubscriber("Python", pubSubService); // Python subscriber only subscribes to Python topics
        allLanguagesSubscriber.addSubscriber("Java", pubSubService); // all subscriber, subscribes to both Java and
                                                                     // Python
        allLanguagesSubscriber.addSubscriber("Python", pubSubService);

        // Trying unSubscribing a subscriber
        // pythonSubscriber.unSubscribe("Python", pubSubService);

        // Broadcast message to all subscribers. After broadcast, messageQueue will be empty in PubSubService
        pubSubService.broadcast();

        // Print messages of each subscriber to see which messages they got
        System.out.println("Messages of Java Subscriber are: ");
        javaSubscriber.printMessages();

        System.out.println("\nMessages of Python Subscriber are: ");
        pythonSubscriber.printMessages();

        System.out.println("\nMessages of All Languages Subscriber are: ");
        allLanguagesSubscriber.printMessages();

        // After broadcast the messagesQueue will be empty, so publishing new messages to server
        System.out.println("\nPublishing 2 more Java Messages...");
        Message javaMsg4 = new Message("Java", "JSP and Servlets");
        Message javaMsg5 = new Message("Java", "Struts framework");

        javaPublisher.publish(javaMsg4, pubSubService);
        javaPublisher.publish(javaMsg5, pubSubService);

        javaSubscriber.getMessagesForSubscriberOfTopic("Java", pubSubService);
        System.out.println("\nMessages of Java Subscriber now are: ");
        javaSubscriber.printMessages();
    }

    private static void generatePublications()
    {
        // Generate the Publications feed in JSON format.
        JSONArray publications = new JSONArray();
        for (int i = 0; i < MAX_MESSAGES; i++) {
            JSONObject publication = new JSONObject();
            publication.put("name", Generator.getRandomName());
            publication.put("birthdate", Generator.getRandomDate());
            publication.put("height", Generator.getRandomHeight());
            publication.put("eyeColor", Generator.getRandomEyeColor());
            publication.put("heartRate", Generator.getRandomHeartRate());
            publications.add(publication);
        }

        writeToFile(publications, "publications.json");
    }

    private static void writeToFile(JSONArray publications, String fileName)
    {
        File file = new File(fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            bufferedOutputStream.write((publications.toString()).getBytes());

            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
