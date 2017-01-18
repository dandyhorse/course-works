package com.epam.homework_7.task2.b;

public class RunnerB {

    public void start() {
        DinnerTable table = new DinnerTable();
        ForkB[] forks = new ForkB[5];

        for (int i = 0; i < forks.length; i++)
            forks[i] = new ForkB();

        table.newPh(forks[0], forks[1]).start();
        table.newPh(forks[1], forks[2]).start();
        table.newPh(forks[2], forks[3]).start();
        table.newPh(forks[3], forks[4]).start();
        table.newPh(forks[4], forks[0]).start();

    }

}
