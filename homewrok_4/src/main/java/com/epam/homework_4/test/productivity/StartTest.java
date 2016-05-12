package com.epam.homework_4.test.productivity;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class StartTest {

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(Productivity.class.getSimpleName())
                .forks(2)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }

}
