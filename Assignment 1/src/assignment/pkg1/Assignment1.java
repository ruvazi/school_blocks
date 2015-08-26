/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author casperschultz
 */
public class Assignment1 {

    Assignment1() {

        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());

        // Image urls
        CalcBytes[] urls = {
            new CalcBytes("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/fronter_big-logo.png"),
            new CalcBytes("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/folder-image-transp.png"),
            new CalcBytes("https://fronter.com/volY12-cache/cache/img/design_images/Classic/other_images/button_bg.png")
        };
        
        /*
            The parallel method runs faster because each thread works
            as parallel with each other.
        */
            
        // Parallel method
        parallelMethod(urls);
            
        /*
            The sequential method runs slower, because it waits
            for each thread to finish the before the next task starts.
        */
        
        // Sequential method
        //sequentialMethod(urls);   
    }
    
    
    /**
     * Calculates the bytes parallel.
     * 
     * @param urls 
     */
    public void parallelMethod(CalcBytes[] urls) {

        ExecutorService executor = Executors.newFixedThreadPool(urls.length);
        long sum = 0;
        long start = System.currentTimeMillis();

        try {

            for (CalcBytes url : urls) {
                executor.submit(url);
            }

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);

            for (CalcBytes url : urls) {
                sum += url.getBytes();
            }

        } catch (InterruptedException e) {
        }

        long end = System.currentTimeMillis();
        
        System.out.println("Time Parallel: " + (end - start));
        System.out.println("Parallel bytes: " + sum);
    }

    /**
     * Calculates the bytes sequentially.
     *
     * @param urls
     */
    public void sequentialMethod(CalcBytes[] urls) {

        long sum = 0;
        long start = System.currentTimeMillis();

        // Foreach url, we create a thread and get the bytes.
        for (CalcBytes calc : urls) {

            // We create a new thread with a Calc that gets passed the url.
            Thread t = new Thread(calc);
            t.start();

            try {
                t.join(0);
            } catch (Exception e) {
            }

            sum += calc.getBytes();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time Sequental: " + (end - start));
        System.out.println("Total byte count: " + sum);
    }

    /**
     * Calculates the bytes of a fetched image.
     */
    public class CalcBytes implements Runnable {

        String imgUrl;
        long byteCount;

        CalcBytes(String imgUrl) {
            this.imgUrl = imgUrl;
            this.byteCount = 0;
        }

        
        /**
         * Sets the sum variable with the bytes from the image fetched. 
         */
        public void run() {

            byte[] byteArray = this.getBytesFromUrl(imgUrl);
            long sum = 0;

            // Calculate bytes
            for (byte value : byteArray) {
                sum += value;
            }

            // Update byteCount
            this.byteCount = sum;
        }
        

        public long getBytes() {
            return this.byteCount;
        }

        /**
         * Fetches an image and turns it into a byteArray.
         *
         * @param url
         * @return
         */
        byte[] getBytesFromUrl(String url) {
            ByteArrayOutputStream bis = new ByteArrayOutputStream();
            try {
                InputStream is = new URL(url).openStream();
                byte[] bytebuff = new byte[4096];
                int read;
                while ((read = is.read(bytebuff)) > 0) {
                    bis.write(bytebuff, 0, read);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
            return bis.toByteArray();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Assignment1();
    }
}
