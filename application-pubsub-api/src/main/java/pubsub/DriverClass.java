package pubsub;
//package main.pubsub;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Date;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//import main.pubsub.publisher.Publisher;
//import main.pubsub.publisher.PublisherImpl;
//import main.pubsub.service.PubSubService;
//import main.pubsub.subscriber.Subscriber;
//import main.pubsub.subscriber.SubscriberImpl;
//
//public class DriverClass
//{
//    private static final String FEED_NAME = "publications.json";
//
//    private static final int MAX_MESSAGES = 100;
//
//    private static final boolean GENERATE_PUBLICATIONS = false;
//
//    public static void main(String[] args)
//    {
//        if (GENERATE_PUBLICATIONS) {
//            generatePublications();
//        }
//
//        // Instantiate publishers, subscribers and PubSubService
//        Publisher javaPublisher = new PublisherImpl();
//        Publisher pythonPublisher = new PublisherImpl();
//
//        Subscriber javaSubscriber = new SubscriberImpl();
//        Subscriber allLanguagesSubscriber = new SubscriberImpl();
//        Subscriber pythonSubscriber = new SubscriberImpl();
//
//        PubSubService pubSubService = new PubSubService();
//
//        // Publishers will read Publications from the feed and will publish them to the PubService.
//        publish(javaPublisher, pubSubService, 0, 30);
//        publish(pythonPublisher, pubSubService, 30, 50);
//
//        Subscription sub1 = new Subscription(Generator.getRandomName(), Generator.getRandomNameOperator(), Generator.getRandomHeartRate(), Generator.getRandomOperator(), Generator.getRandomDate(), Generator.getRandomOperator());
//        Subscription sub2 = new Subscription(Generator.getRandomName(), Generator.getRandomNameOperator(), Generator.getRandomHeartRate(), Generator.getRandomOperator(), Generator.getRandomDate(), Generator.getRandomOperator());
//        Subscription sub3 = new Subscription(Generator.getRandomName(), Generator.getRandomNameOperator(), Generator.getRandomHeartRate(), Generator.getRandomOperator(), Generator.getRandomDate(), Generator.getRandomOperator());
//        Subscription sub4 = new Subscription(Generator.getRandomName(), Generator.getRandomNameOperator(), Generator.getRandomHeartRate(), Generator.getRandomOperator(), Generator.getRandomDate(), Generator.getRandomOperator());
//
//        // Declare Subscribers
//        javaSubscriber.addSubscriber(sub1, pubSubService); // Java subscriber only subscribes to Java topics
//        javaSubscriber.addSubscriber(sub2, pubSubService); // Python subscriber only subscribes to Python topics
//        pythonSubscriber.addSubscriber(sub3, pubSubService); // all subscriber, subscribes to both Java and
//        allLanguagesSubscriber.addSubscriber(sub4, pubSubService);// Python
//
//        // Trying unSubscribing a subscriber
//        // pythonSubscriber.unSubscribe("Python", pubSubService);
//
//        // Broadcast publication to all subscribers. After broadcast, publicationQueue will be empty in PubSubService
//        pubSubService.broadcast();
//
//        // Print publications of each subscriber to see which publications they got
//        System.out.println("Publications of Java Subscriber are: ");
//        javaSubscriber.printPublications();
//
//        System.out.println("\nPublications of Python Subscriber are: ");
//        pythonSubscriber.printPublications();
//
//        System.out.println("\nPublications of All Languages Subscriber are: ");
//        allLanguagesSubscriber.printPublications();
//
//        // // After broadcast the publicationsQueue will be empty, so publishing new publications to server
//        // System.out.println("\nPublishing 2 more Java Publications...");
//        // Publication javaMsg4 = new Publication("Java", "JSP and Servlets");
//        // Publication javaMsg5 = new Publication("Java", "Struts framework");
//        //
//        // javaPublisher.publish(javaMsg4, pubSubService);
//        // javaPublisher.publish(javaMsg5, pubSubService);
//        //
//        // javaSubscriber.getPublicationsForSubscriberOfTopic("Java", pubSubService);
//        // System.out.println("\nPublications of Java Subscriber now are: ");
//        // javaSubscriber.printPublications();
//    }
//
//    private static void publish(Publisher publisher, PubSubService pubSubService, int index, int size)
//    {
//        // JSON parser object to parse read file
//        JSONParser jsonParser = new JSONParser();
//
//        try (FileReader reader = new FileReader(FEED_NAME)) {
//            // Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray publications = (JSONArray) obj;
//
//            for (int i = index; i < size; i++) {
//                JSONObject publicationJSON = (JSONObject) publications.get(i);
//                Publication publication = new Publication(publicationJSON.get("name").toString(),
//                    Date.valueOf(publicationJSON.get("birthdate").toString()),
//                    Double.parseDouble(publicationJSON.get("height").toString()),
//                    publicationJSON.get("eyeColor").toString(),
//                    Integer.parseInt(publicationJSON.get("heartRate").toString()));
//                publisher.publish(publication, pubSubService);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private static void generatePublications()
//    {
//        // Generate the Publications feed in JSON format.
//        JSONArray publications = new JSONArray();
//        for (int i = 0; i < MAX_MESSAGES; i++) {
//            JSONObject publication = new JSONObject();
//            publication.put("name", Generator.getRandomName());
//            publication.put("birthdate", Generator.getRandomDate());
//            publication.put("height", Generator.getRandomHeight());
//            publication.put("eyeColor", Generator.getRandomEyeColor());
//            publication.put("heartRate", Generator.getRandomHeartRate());
//            publications.add(publication);
//        }
//
//        writeToFile(publications, FEED_NAME);
//    }
//
//    private static void writeToFile(JSONArray publications, String fileName)
//    {
//        File file = new File(fileName);
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//
//            bufferedOutputStream.write((publications.toString()).getBytes());
//
//            bufferedOutputStream.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
