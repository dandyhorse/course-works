package com.epam.homework_7.task2.a;

/**
 * DinnerTable is kind of container of Philosophers
 */
public class DinnerTable {

    private Mediator mediator;

    public Thread newPh() {
        return new Thread(new PhilosopherA(mediator));
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

}
