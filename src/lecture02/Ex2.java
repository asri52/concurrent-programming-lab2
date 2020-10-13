/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture02;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adri
 */
public class Ex2 {

    public static final int SIZE = 100000000;
    public static final double RANGE = 1000.0;

    public static void main(String[] args) {
        double[] data = new double[SIZE]; // and fill the array with random data
        for (int i = 0; i < SIZE; i++) {
            data[i] = Math.random() * RANGE;
        }

        long startTime = System.currentTimeMillis();
        ArrayCalculator ac = new ArrayCalculator(data);
        
        
        //Creating Thread objects to dispatch later concurrently
        AverageThread avg = new AverageThread(ac);
        MaxThread max = new MaxThread(ac);
        MinThread min = new MinThread(ac);
        
        avg.start();
        max.start();
        min.start();
        
        try {
            avg.join();
            max.join();
            min.join();
            
        } catch (InterruptedException ex) {
            
        }
        
        System.out.println("Min, max, avg threads finished");
        
        //Retrieve post-run data from object field of threads
        double avgResult = avg.getResult();
        double minResult = min.getResult();
        double maxResult = max.getResult();
        
        long endTime = System.currentTimeMillis();
        System.out.println("Calculation for " + SIZE + " values took "
                + (endTime - startTime) + " milliseconds");
        System.out.println("Max = " + maxResult + "\nMin = " + minResult + "\nAverage = " + avgResult);
    }
}
