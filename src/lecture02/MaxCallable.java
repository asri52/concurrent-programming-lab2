/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture02;

import java.util.concurrent.Callable;

/**
 *
 * @author Adri
 */
public class MaxCallable implements Callable<Double>{
    
    private ArrayCalculator arrayCalc;
    
    public MaxCallable(ArrayCalculator arr) {
        arrayCalc = arr;
    }
    
    @Override
    public Double call() throws Exception {
        return arrayCalc.max();
    }
    
}
