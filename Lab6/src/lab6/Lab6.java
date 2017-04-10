package lab6;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Lab6 {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter wash time: ");
        int WASHTIME = kb.nextInt();
        System.out.println("Enter arrival probability: ");
        double ARRIVALPROB = kb.nextDouble();
        System.out.println("enter time for simulation: ");
        int TOTALTIME = kb.nextInt();
        System.out.println("Enter maximum queue length: ");
        int maxLen = kb.nextInt();

        carWashSimulate(WASHTIME, ARRIVALPROB, TOTALTIME, maxLen);
        graphicalElement(WASHTIME, ARRIVALPROB, TOTALTIME, maxLen);
    }
    
    /**
     * Created by Eric Walton
     * 2017/04/07
     * @param numberOfServers
     * @param washTime
     * @return 
     */
    private static Server[] createMultipleServers(int numberOfServers, int washTime) {
        Server[] servers = new Server[numberOfServers];
        for (int i = 0; i < numberOfServers; i++) {
            Server server = new Server(washTime);
            servers[i] = server;
        }
        return servers;
    }
    
    private static Queue[] createMultipleQueues(int numberOfQueues){
        Queue[] queues = new Queue[3];
        for (int i = 0; i < numberOfQueues; i++) {
            Queue<Integer> queue = new LinkedList<Integer>();
            queues[i] = queue;
        }
        return queues;
    }
    
    
    public static void carWashSimulate(int washTime, double arrivalProb, int totalTime, int max) {
        
        
        Queue[] queues = createMultipleQueues(3);
        
        int next;
        ClientGenerator arrival = new ClientGenerator(arrivalProb);

        /**
         * created by Eric Walton
         * 2017/04/07
         * calls the createMultipleServers method.
         */
        Server[] servers = createMultipleServers(3, washTime);

        Averager waitTimes = new Averager();
        Averager longerWaitTimes = new Averager();
        int currentSecond;

        // Write the parameters to System.out.
        System.out.println("Seconds to wash one car: " + washTime);
        System.out.print("Probability of customer arrival during a second: ");
        System.out.println(arrivalProb);
        System.out.println("Total simulation seconds: " + totalTime);

        // Check the precondition:
        if (washTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0) {
            throw new IllegalArgumentException("Values out of range");
        }

        for (currentSecond = 0; currentSecond < totalTime; currentSecond++) {  // Simulate the passage of one second of time.

            // Check whether a new customer has arrived.
            if (arrival.query()) {
                /**
                 * Modified by Eric Walton
                 * 2017/04/07
                 */
//                if (queue1.size() >= max) {
//                    System.out.println("Lost a customer at " + currentSecond);
//                } else {
//                    queue1.add(currentSecond);
//                }
                System.out.println("Customer arrived at " + currentSecond);
                
                
                if (queues[0].size() < queues[1].size() && queues[0].size() < queues[2].size()) {
                    if (queues[0].size() >= max) {
                        System.out.println("Queue1 Lost a customer at " + currentSecond);
                    }else{
                        queues[0].add(currentSecond);
                        System.out.println("customer added to queue(1)");
                    }
                }else if(queues[1].size() < queues[2].size()){
                    if (queues[1].size() >= max) {
                        System.out.println("Queue2 Lost a customer at " + currentSecond);
                    }else{
                        queues[1].add(currentSecond);
                        System.out.println("customer added to queue(2)");
                    }
                }else{
                    if (queues[2].size() >= max) {
                        System.out.println("Queue3 Lost a customer at " + currentSecond);
                    }else{
                        queues[2].add(currentSecond);
                        System.out.println("customer added to queue(3)");
                    }
                }
                
            }
            // Check whether we can start washing another car.
            /**
             * Modified by Eric Walton 04/07/2017 original commented out.
             */
//         if ((!servers[0].isBusy( ))  &&  (!queue1.isEmpty( )))
//         {
//            next = queue1.remove( );
//            waitTimes.addNumber(currentSecond - next);
//            servers[0].start( );
//            System.out.println("Server started at " + currentSecond);
//         }
            /**
             * Created by Eric Walton 
             * 2017/04/07 
             * Checks to see if there are any arrivalTimes and
             * checks each server to see if they are busy 
             */
                int serverNum = 0;
                for (Server server : servers) {
                    serverNum++;
                    if (!server.isBusy() && !queues[0].isEmpty()) {
                            next = (int) queues[0].remove();
                            waitTimes.addNumber(currentSecond - next);
                        server.start();
                        System.out.println("Server(" + serverNum + ") started at " + currentSecond);
                    }else{
                        server.reduceRemainingTime();
                    }
                }
                
                
            

            // Subtract one second from the remaining time in the current wash cycle.
            /**
             * modified by Eric Walton
             * handled in if server.isBusy inside the for loop above
             */
            //servers[0].reduceRemainingTime();            

            // Subtract one second from the remaining time in the current wash cycle.
            /**
             * modified by Eric Walton
             * handled in if server.isBusy inside the for loop above
             */
            //servers[0].reduceRemainingTime();
        }
        while (!queues[0].isEmpty()) {
            next = (int) queues[0].remove();
            longerWaitTimes.addNumber(totalTime - next);
        }
        // Write the summary information about the simulation.
        System.out.println("Customers served: " + waitTimes.howManyNumbers());
        if (waitTimes.howManyNumbers() > 0) {
            System.out.println("Average wait for customers served: " + waitTimes.average() + " sec");
        }
        if (longerWaitTimes.howManyNumbers() > 0) {
            System.out.println("Average wait for customers left on queue: " + longerWaitTimes.average() + " sec");
        }
    }
    
       public static void graphicalElement(int washTime, double arrivalProb, int totalTime, int max) {

        Queue[] queues = createMultipleQueues(3);

        ClientGenerator arrival = new ClientGenerator(arrivalProb);

        int currentSecond;

        // Check the precondition:
        if (washTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0) {
            throw new IllegalArgumentException("Values out of range");
        }
        
        System.out.println("");
        System.out.println("Visual Indication of each queue: ");
        System.out.println("++ means a customer was added"); 
        System.out.println("-- means the queue lost a customer");
        System.out.printf("%-10s %-10s %-10s\n", "Queue 1", "Queue 2", "Queue 3");
        System.out.println("................................");

        for (currentSecond = 0; currentSecond < totalTime; currentSecond++) {  // Simulate the passage of one second of time.

            // Check whether a new customer has arrived.
            if (arrival.query()) {
           
                //System.out.println("Customer arrived at ".replace("Customer arrived at", "O"));

                if (queues[0].size() < queues[1].size() && queues[0].size() < queues[2].size()) {
                    if (queues[0].size() >= max) {
                        System.out.println("Queue1 Lost a customer at ".replace("Queue1 Lost a customer at ", "  --"));
                    } else {
                        queues[0].add(currentSecond);
                        System.out.println("customer added to queue(1)".replace("customer added to queue(1)", "  ++"));
                    }
                } else if (queues[1].size() < queues[2].size()) {
                    if (queues[1].size() >= max) {
                        System.out.println("Queue2 Lost a customer at ".replace("Queue2 Lost a customer at ", "\t     --"));
                    } else {
                        queues[1].add(currentSecond);
                        System.out.println("customer added to queue(2)".replace("customer added to queue(2)", " \t     ++"));
                    }
                } else {
                    if (queues[2].size() >= max) {
                        System.out.println("Queue3 Lost a customer at ".replace("Queue3 Lost a customer at ", "\t\t\t--"));
                    } else {
                        queues[2].add(currentSecond);
                        System.out.println("customer added to queue(3)".replace("customer added to queue(3)", "\t\t\t++"));
                    }
                }
            }
        }
    }

}
