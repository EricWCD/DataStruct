package lab6;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Lab6 {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter ride time: ");
        int RIDETIME = kb.nextInt();
        System.out.println("Enter arrival probability: ");
        double ARRIVALPROB = kb.nextDouble();
        System.out.println("enter time for simulation: ");
        int TOTALTIME = kb.nextInt();
        System.out.println("Enter maximum queue length: ");
        int maxLen = kb.nextInt();

        themeParkSimulate(RIDETIME, ARRIVALPROB, TOTALTIME, maxLen);
        economicElements(RIDETIME, ARRIVALPROB, TOTALTIME, maxLen);
        graphicalElement(RIDETIME, ARRIVALPROB, TOTALTIME, maxLen);
    }

    /**
     *
     * @param numberOfServers
     * @param rideTime
     * @return
     */
    private static Server[] createMultipleServers(int numberOfServers, int rideTime) {
        Server[] servers = new Server[numberOfServers];
        for (int i = 0; i < numberOfServers; i++) {
            Server server;
            if (i == 0) {
                server = new Server(rideTime / 2);
            }else{
                server = new Server(rideTime);
            }
            servers[i] = server;
        }
        return servers;
    }

    private static Queue[] createMultipleQueues(int numberOfQueues) {
        Queue[] queues = new Queue[3];
        for (int i = 0; i < numberOfQueues; i++) {
            Queue<Integer> queue = new LinkedList<Integer>();
            queues[i] = queue;
        }
        return queues;
    }

    public static void themeParkSimulate(int rideTime, double arrivalProb, int totalTime, int max) {

        Queue[] queues = createMultipleQueues(3);

        int next;
        ClientGenerator arrival = new ClientGenerator(arrivalProb);

        /**
         *
         * calls the createMultipleServers method.
         */
        Server[] servers = createMultipleServers(3, rideTime);

        Averager waitTimes = new Averager();
        Averager longerWaitTimes = new Averager();
        int currentSecond;

        // Write the parameters to System.out.
        System.out.println("Seconds on ride: " + rideTime);
        System.out.print("Probability of customer arrival during a second: ");
        System.out.println(arrivalProb);
        System.out.println("Total simulation seconds: " + totalTime);

        // Check the precondition:
        if (rideTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0) {
            throw new IllegalArgumentException("Values out of range");
        }

        for (currentSecond = 0; currentSecond < totalTime; currentSecond++) {  // Simulate the passage of one second of time.

            // Check whether a new customer has arrived.
            if (arrival.query()) {
                /**
                 * Modified 2017/04/07
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
                        // we can add a lost variable for each queue and increment it. When the program is done
                        // we can print the graphic for line queue, served and lost.
                    } else {
                        queues[0].add(currentSecond);
                        System.out.println("customer added to queue(1)");
                    }
                } else if (queues[1].size() < queues[2].size()) {
                    if (queues[1].size() >= max) {
                        System.out.println("Queue2 Lost a customer at " + currentSecond);
                    } else {
                        queues[1].add(currentSecond);
                        System.out.println("customer added to queue(2)");
                    }
                } else {
                    if (queues[2].size() >= max) {
                        System.out.println("Queue3 Lost a customer at " + currentSecond);
                    } else {
                        queues[2].add(currentSecond);
                        System.out.println("customer added to queue(3)");
                    }
                }

            }
            // Check whether we can start another ride.
            /**
             * original commented out.
             */
//         if ((!servers[0].isBusy( ))  &&  (!queue1.isEmpty( )))
//         {
//            next = queue1.remove( );
//            waitTimes.addNumber(currentSecond - next);
//            servers[0].start( );
//            System.out.println("Server started at " + currentSecond);
//         }
            /**
             * Checks to see if there are any arrivalTimes and checks each
             * server to see if they are busy
             */
            int serverNum = 0;
            int queNum = 0;
            for (Server server : servers) {
                serverNum++;
                if (queNum < 2) {
                    queNum++;
                }
                if (!server.isBusy() && !queues[queNum].isEmpty()) {
                    next = (int) queues[queNum].remove();
                    waitTimes.addNumber(currentSecond - next);
                    server.start();
                    System.out.println("Server(" + serverNum + ") started at " + currentSecond);
                } else {
                    server.reduceRemainingTime();
                }
            }

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

    public static void graphicalElement(int rideTime, double arrivalProb, int totalTime, int max) {

        Queue[] queues = createMultipleQueues(3);

        ClientGenerator arrival = new ClientGenerator(arrivalProb);

        int currentSecond;

        // Check the precondition:
        if (rideTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0) {
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

    public static void economicElements(int rideTime, double arrivalProb, int totalTime, int max) {

        Queue[] queues = createMultipleQueues(3);

        ClientGenerator arrival = new ClientGenerator(arrivalProb);

        int currentSecond;
        double stotal = 0;
        final double HOURLY_WAGES = 30;
        final double HOURLY_OPERATING_COST = 90;
        double expenses = 0;
        double profit = 0;

        // Check the precondition:
        if (rideTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0) {
            throw new IllegalArgumentException("Values out of range");
        }

        for (currentSecond = 0; currentSecond < totalTime; currentSecond++) {  // Simulate the passage of one second of time.

            // Check whether a new customer has arrived.
            if (arrival.query()) {

                //System.out.println("Customer arrived at ".replace("Customer arrived at", "O"));
                if (queues[0].size() < queues[1].size() && queues[0].size() < queues[2].size()) {
                    if (queues[0].size() >= max) {
                        stotal = stotal;
                    } else {
                        queues[0].add(currentSecond);
                        stotal = stotal + 5;
                    }
                } else if (queues[1].size() < queues[2].size()) {
                    if (queues[1].size() >= max) {
                        stotal = stotal;
                    } else {
                        queues[1].add(currentSecond);
                        stotal = stotal + 5;
                    }
                } else {
                    if (queues[2].size() >= max) {
                        stotal = stotal;
                    } else {
                        queues[2].add(currentSecond);
                        stotal = stotal + 5;
                    }
                }
            }
        }

        System.out.println("\nTotal Sales: " + stotal);
        expenses = ((HOURLY_WAGES + HOURLY_OPERATING_COST) / 3600) * totalTime;
        profit = stotal - expenses;
        System.out.println("Expenses for this time period: " + expenses);
        System.out.println("Profit for this time period: " + profit);
    }

}
