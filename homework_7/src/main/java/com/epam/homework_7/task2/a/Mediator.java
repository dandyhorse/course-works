package com.epam.homework_7.task2.a;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Mediator that keeps all forks and allocates them to all philosophers
 */
public class Mediator {
    private final int forkCount;
    private final Deque<ForkA> stack;
    private int grippedForks;

    public Mediator(int forkCount) {
        this.grippedForks = 0;
        this.forkCount = forkCount;
        this.stack = new ArrayDeque<>();
        fillStackWithForks(forkCount);
    }

    private void fillStackWithForks(int forkCount) {
        for (int i = 0; i < forkCount; i++) {
            stack.add(new ForkA());
        }
    }

    public ForkA askAFork() {
        synchronized (this.stack) {
            if (grippedForks < forkCount - 1) {
                grippedForks++;
                return stack.pop();
            }
        }
        return null;
    }

    public void giveBackFork(ForkA fork) {
        synchronized (this.stack) {
            if (fork != null) {
                grippedForks--;
                stack.push(fork);
            }
        }
    }
}
