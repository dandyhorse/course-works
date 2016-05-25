package com.epam.homework_7.task2.a;

public class RunnerA {

    public void start() {
        DinnerTable tableA = new DinnerTable();
        Mediator mediator = new Mediator(5);
        tableA.setMediator(mediator);
        tableA.newPh().start();
        tableA.newPh().start();
        tableA.newPh().start();
        tableA.newPh().start();
        tableA.newPh().start();
    }

}
