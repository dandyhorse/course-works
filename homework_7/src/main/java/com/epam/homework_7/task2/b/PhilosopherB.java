package com.epam.homework_7.task2.b;

import com.epam.homework_7.task2.AbstractPhilosopher;

import java.util.concurrent.locks.Lock;

public class PhilosopherB extends AbstractPhilosopher implements Runnable {

    private ForkB left;
    private ForkB right;

    public PhilosopherB(ForkB left, ForkB right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        Lock leftLock = left.getLock();
        Lock rightLock = right.getLock();
        while (!Thread.currentThread().isInterrupted()) {
            leftLock.lock();
            try {
                if (rightLock.tryLock()) {
                    try {
                        eat();
                    } finally {
                        rightLock.unlock();
                    }
                }
            } finally {
                leftLock.unlock();
            }
            think();
        }
    }

}
