package com.epam.homework_7.task2.a;

import com.epam.homework_7.task2.AbstractPhilosopher;

public class PhilosopherA extends AbstractPhilosopher implements Runnable {

    private Mediator mediator;
    private ForkA left;
    private ForkA right;

    public PhilosopherA(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                left = mediator.askAFork();
                right = mediator.askAFork();
                if (left != null || right != null) {
                    eat();
                }
            } finally {
                mediator.giveBackFork(left);
                mediator.giveBackFork(right);
            }
            think();
        }
    }

}
