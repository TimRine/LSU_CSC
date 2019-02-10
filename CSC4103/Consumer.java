package producerconsumer;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Timothy Rine
 * @since 2018-03-11
 * CSC 4103
 * Dr. Karki & Dr. Douglas
 * Programming Assignment #2
 */
public class Consumer extends Thread {

    //This will hold the shared array...
    private int[] sharedArray;

    //Initializes a new instance of consumer thread.
    public Consumer(int[] sharedArray) {
        this.sharedArray = sharedArray;
    }

    //Starts the consumer job.
    public void run() {
        
        //We'll keep on repeating 2 times...
        int times = 0;
        while (times < 2) {
            
            //Enter the synchronized on the lock obj of producer.
            synchronized (Producer.syncLockObj) {
                try {
                    //First wait for the producer to notify us how many numbers we're going to get.             
                    Producer.syncLockObj.wait();

                    //Then read how many numbers producer has generated...
                    int howManyNubs = Producer.howManyNumbers;
                    
                    //Notify the producer that he can start producing...
                    Producer.syncLockObj.notify();

                    //Wait till all numbers are generated...
                    Producer.syncLockObj.wait();

                    //And now print all numbers...
                    for (int i = 0; i < Producer.howManyNumbers; i++) {
                        System.out.println("Consumer Got :" + sharedArray[i]);
                    }

                    //Notify the producer that we're done reading...
                    Producer.syncLockObj.notify();

                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            times++;
        }
        
         System.out.println("Consumer Done !");
    }
}