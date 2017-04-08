
package lab6;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class Lab6 {

     public static void main(String[ ] args)
   {
      Scanner kb = new Scanner (System.in);
      System.out.println("Enter wash time: ");
      int WASHTIME = kb.nextInt();
      System.out.println("Enter arrival probability: ");
      double ARRIVALPROB = kb.nextDouble();
      System.out.println("enter time for simulation: ");
      int TOTALTIME = kb.nextInt();
      System.out.println("Enter maximum queue length: ");
      int maxLen = kb.nextInt();
      
      carWashSimulate(WASHTIME, ARRIVALPROB, TOTALTIME, maxLen);
   }
     
     private static Server[] createMultipleServers(int numberOfServers,int washTime){
         Server[] servers = new Server[numberOfServers];
         for (int i = 0; i < numberOfServers; i++) {
             Server server = new Server(washTime);
             servers[i] = server;
         }
         return servers;
     }
     
    
   public static void carWashSimulate
   (int washTime, double arrivalProb, int totalTime, int max)
   {
      Queue<Integer> arrivalTimes = new LinkedList<Integer>( );  
      int next;
      ClientGenerator arrival = new ClientGenerator(arrivalProb);
       
      Server[] servers = createMultipleServers(3,washTime);
       
      
      Averager waitTimes = new Averager( );
      Averager longerWaitTimes = new Averager();
      int currentSecond;
  
      // Write the parameters to System.out.
      System.out.println("Seconds to wash one car: " + washTime);
      System.out.print("Probability of customer arrival during a second: ");
      System.out.println(arrivalProb);
      System.out.println("Total simulation seconds: " + totalTime); 
   
      // Check the precondition:
      if (washTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0)
         throw new IllegalArgumentException("Values out of range"); 

      for (currentSecond = 0; currentSecond < totalTime; currentSecond++)
      {  // Simulate the passage of one second of time.

         // Check whether a new customer has arrived.
         if (arrival.query( )){
            if(arrivalTimes.size() >= max)
                System.out.println("Lost a customer at " + currentSecond);
            else
                arrivalTimes.add(currentSecond);
            System.out.println("Customer arrived at " + currentSecond);
        }
         // Check whether we can start washing another car.
         if ((!servers[0].isBusy( ))  &&  (!arrivalTimes.isEmpty( )))
         {
            next = arrivalTimes.remove( );
            waitTimes.addNumber(currentSecond - next);
            servers[0].start( );
            System.out.println("Server started at " + currentSecond);
         }

         // Subtract one second from the remaining time in the current wash cycle.
         servers[0].reduceRemainingTime( );
      }
      while (!arrivalTimes.isEmpty())
      {
          next = arrivalTimes.remove();
          longerWaitTimes.addNumber(totalTime - next);
      }
      // Write the summary information about the simulation.
      System.out.println("Customers served: " + waitTimes.howManyNumbers( )); 
      if (waitTimes.howManyNumbers( ) > 0)
         System.out.println("Average wait for customers served: " + waitTimes.average( ) + " sec");
      if (longerWaitTimes.howManyNumbers() > 0)
        System.out.println("Average wait for customers left on queue: " + longerWaitTimes.average( ) + " sec");
   } 
    
}
