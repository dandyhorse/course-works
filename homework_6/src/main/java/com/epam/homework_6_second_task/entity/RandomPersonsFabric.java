package com.epam.homework_6_second_task.entity;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class RandomPersonsFabric {

    private String[] namePool;
    private Random randomizer;
    private int maxPersonAge;

    public RandomPersonsFabric() {
        this.namePool = new String[]{"Tony", "Molly", "Johny"};
        this.randomizer = new Random();
        this.maxPersonAge = 100;
    }

    public RandomPersonsFabric(String[] namePool, int maxPersonAge) {
        this.namePool = namePool;
        this.randomizer = new Random();
        this.maxPersonAge = maxPersonAge;
    }

    public RandomPersonsFabric(String[] namePool) {
        this.namePool = namePool;
        this.randomizer = new Random();
        this.maxPersonAge = 100;
    }

    public Person getRandomPerson() {
        EnumSex valueSex = EnumSex.values()[getRandom(EnumSex.values().length)];
        String valueName = namePool[getRandom(namePool.length)];
        int valueAge = getRandom(maxPersonAge);
        return Person.builder().sex(valueSex).name(valueName).age(valueAge).build();
    }

    private int getRandom(int i) {
        return randomizer.nextInt(Math.abs(i));
    }

    public Stream<Person> getPersonStream(int size) {
        return Stream.generate(this::getRandomPerson).limit(size);
    }

    public Supplier<Stream<Person>> getSupplierOfPersonStream(int size) {
        return () -> getPersonStream(size);
    }

}
