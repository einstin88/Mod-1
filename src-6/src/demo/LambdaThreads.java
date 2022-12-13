package demo;

import java.security.SecureRandom;
// import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LambdaThreads {
    public static void main(String[] args) {
        ExecutorService threadpool = Executors.newFixedThreadPool(2);

        for (Integer i = 0; i < 3; i++) {
            threadpool.submit(
                    () -> {
                        Random rand = new SecureRandom();
                        for (int j = 0; j < 10; j++) {
                            System.out.printf("[%d]. %d%n", j, rand.nextInt(100));
                        }
                    });

            // Similar to :
            // Runnable run = () -> {
            //     Random rand = new SecureRandom();
            //     for (int j = 0; j < 10; j++) {
            //         System.out.printf("[%d]. %d", j, rand.nextInt(100));
            //     }
            // };
            // threadpool.submit(run);
        }
    }
}
