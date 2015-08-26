package excercise.pkg1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author casperschultz
 */
public class Excercise2 {

    public static Even even = new Even();
    public static boolean running;
    
    public static class Even {

        private int n = 0;

        public int next() {
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
    
   /*
        Hvordan kan "n" være ulige?
        
        Det kan lade sig gøre, fordi den første tråe går kalder even metoden 
        og tæller n 1 op. Herefter tæller Tråd 2 n 1 op og når tråd 1 så
        tæller n op for anden gang, vil den være 3 og derved returnere even
        et ulige tal.
    
        Det lader til at være et problem der sker relativt ofte 
        og her i eksepmlet ca. hver 1000 gang.
    
    */
}
