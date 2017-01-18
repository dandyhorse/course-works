package com.epam.homework_7.task2;

public abstract class AbstractPhilosopher {

    protected void eat() {
        try {
            System.out.println("PhilosopherB " + Thread.currentThread().getId() + " eat");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void think() {
        try {
            System.out.println("PhilosopherB " + Thread.currentThread().getId() + " thinking");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
