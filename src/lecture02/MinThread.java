/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture02;

/**
 *
 * @author Adri
 */
public class MinThread extends Thread {
    private final ArrayCalculator theCalc;
    private double theResult;

    public MinThread(ArrayCalculator arrayCalc) {
        theCalc = arrayCalc;
    }

    @Override
    public void run() {
        theResult = theCalc.min();
    }

    public double getResult () {
        return theResult;
    }
}
