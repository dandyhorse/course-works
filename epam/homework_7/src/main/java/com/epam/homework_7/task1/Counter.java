package com.epam.homework_7.task1;

import java.util.concurrent.atomic.LongAdder;

public class Counter {

    private LongAdder counter = new LongAdder();

    public void incrementCounter() {
        counter.increment();
    }

    public Long getResult() {
        return counter.sumThenReset();
    }

}
