package exercise;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


/*
 This is the solution for task 4.1 and 4.2

 4.1 has been cemmented out and the active solutio is the solution
 that uses the atomic integer
 */
/* public class TurnstileCounter {

 static final long DELAY_VAL = 10000;
 AtomicInteger count = new AtomicInteger();

 public int getValue() {
 return count.get();
 }
  
 // We fix the problem, by making locking the method making it synchronized
 //public synchronized void incr() {
      
 public synchronized void incr() {  
 //   If the program initially does not fail, replace the count line with the lines below
 //    long n = count;
 //    //Spend some time to force preemtion
 //    for(long a=0; a<LockDemo.DELAY_VAL; a++);
 //    n = n + 1;
 //    count = n;

 count.incrementAndGet();
 }
 } /*



 /*
 This is the solution for task 4.1 and 4.2

 4.1 has been cemmented out and the active solutio is the solution
 that uses the atomic integer
 */
public class TurnstileCounter {

    static final long DELAY_VAL = 10000;
    private long count = 0;
    private ReentrantLock lock = new ReentrantLock();

    
    public long getValue() {
        return this.count;
    }

    
    public void incr() {

        lock.lock();

        try {
            this.count++;
        } finally {
            lock.unlock();
        }
    }
}
