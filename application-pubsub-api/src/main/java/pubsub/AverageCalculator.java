package pubsub;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AverageCalculator
{
    
    private static final AverageCalculator INSTANCE = new AverageCalculator();
    
    private AverageCalculator() {
        
    }
    public Queue<Double> globalQueue = new ConcurrentLinkedQueue<Double>();

    public double computeAverage()
    {
        double average = 0;

        for (Double href : globalQueue) {
            average += href.doubleValue();
        }

        return average / globalQueue.size();
    }
    
    public static AverageCalculator getInstance() {
        return INSTANCE;
    }
    
    public void pushDelay(double delay) {
        this.globalQueue.add(delay);
    }
}
