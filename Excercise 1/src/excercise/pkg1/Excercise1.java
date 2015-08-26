/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excercise.pkg1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author casperschultz
 */
public class Excercise1 {
    
    public static boolean running = true;    
    
    public static class Thread1 extends Thread {
            
        public void run() {
            
            long sum = 0;
            
            for (long i = 1; i <= 1000000000; i++) {
                sum = sum + i;
            }
            
            System.out.println(sum);
        }
    }

    public static class Thread2 extends Thread {

        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("We are printing from thread 2: " + i);
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) { }
            }
        }
    }

    public static class Thread3 extends Thread {

        public void run() {
            
            int i = 10;
            
            while (running) {
                System.out.println(i);
                i++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) { }
            }
        }
    }

    public static void main(String[] args) {
        
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        Thread3 t3 = new Thread3();   
        
        // We start thread 1
        t1.start();
        t2.start();
        t3.start();
        
        try {
            Thread.sleep(10000);
            running = false;
        } catch (InterruptedException ex) { }
    }
}
