package com.epam.homework_7.task2.b;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ForkB {
    private Lock lock = new ReentrantLock(true);

    public Lock getLock() {
        return lock;
    }
}
