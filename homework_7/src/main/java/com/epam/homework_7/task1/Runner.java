package com.epam.homework_7.task1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public Long doCalculation() {
        Counter counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementCounter();
            }
        };
        ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            for (int i = 0; i < 100; i++) {
                service.submit(runnable).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Long result = counter.getResult();
        System.out.println(result);
        service.shutdown();
        return result;
    }

}
