/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

/**
 *
 * @author TJ
 */
public class Main {
    public static void main(String[] args) {
                //Define the sharred array...
        int[] sharedArray = new int[10];

        //Allocate one producer and one consumer...
        Producer p = new Producer(sharedArray);
        Consumer c = new Consumer(sharedArray);

        //Start them all...
        p.start();
        c.start();
    }
}
