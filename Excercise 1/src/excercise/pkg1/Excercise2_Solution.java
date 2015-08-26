/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excercise.pkg1;

/**
 *
 * @author casperschultz
 */
public class Excercise2_Solution {
    
    public static Even even = new Even();
    public static boolean running;
    
    public static class Even {

        private int n = 0;
        
        // Ved at lave metoden synchronized, kan den kun tilgås
        // af 1 tråd ad gangen.
        public synchronized int next() {
            n++;
            n++;
            return n;
        }
    }
    
    public static class Thread1 extends Thread {
        
        public void run() {
         
            while(true) {
                int i = even.next();
                if (i % 2 != 0) {
                    System.out.println(i);
                    break;
                }
            } 
        }
    }
    

    public static void main(String[] args) {
            
        Thread1 t1 = new Thread1();
        Thread1 t2 = new Thread1();
        
        t1.start();
        t2.start();
    }
    
}
