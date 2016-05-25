package com.epam.homework_7.task2.b;

public class DinnerTable {

    public Thread newPh(ForkB left, ForkB right) {
        return new Thread(new PhilosopherB(left, right));
    }

}
