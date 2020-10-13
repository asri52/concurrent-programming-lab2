/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Adri
 */
public class ArrayCalcExecutor {

    public static final int SIZE = 100000000;
    public static final double RANGE = 1000.0;

    public static void main(String[] args) {
        double[] data = new double[SIZE]; // and fill the array with random data
        for (int i = 0; i < SIZE; i++) {
            data[i] = Math.random() * RANGE;
        }

        long startTime = System.currentTimeMillis();
        ArrayCalculator ac = new ArrayCalculator(data);

        //New executor service thread pool
        ExecutorService ex = Executors.newFixedThreadPool(3);

        //list for future objects 
        List<Future<Double>> results = new ArrayList<>();

        //initialise callables
        AvgCallable avgCall = new AvgCallable(ac);
        MaxCallable maxCall = new MaxCallable(ac);
        MinCallable minCall = new MinCallable(ac);

        //submit callables and store results in future objects
        Future<Double> avgFuture = ex.submit(avgCall);
        Future<Double> maxFuture = ex.submit(maxCall);
        Future<Double> minFuture = ex.submit(minCall);

        //try to retrieve result from future objects and print them
        try {
            Double avgResult = avgFuture.get();
            System.out.println(avgResult);

            Double maxResult = maxFuture.get();
            System.out.println(maxResult);

            Double minResult = minFuture.get();
            System.out.println(minResult);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Calculation for " + SIZE + " values took "
                + (endTime - startTime) + " milliseconds");
       
    }
}
