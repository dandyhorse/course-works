package com.epam.homework_4.test.productivity;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Warmup(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class Productivity {

    /**
     * useful except in adding to emptyLists
     */
    @Param({"1000"})
    private static int COUNT_OF_NUMBERS;
    private static Random randomizer = new Random();
    private LinkedList<Integer> filledLinkedList;
    private LinkedList<Integer> emptyLinkedList;
    private ArrayList<Integer> filledArrayList;
    private ArrayList<Integer> emptyArrayList;

    @Setup(Level.Iteration)
    public void setup() {
        initLinkedLists();
        initArrayLists();
    }

    private void initLinkedLists() {
        emptyLinkedList = new LinkedList<>();
        emptyLinkedList.add(randomizer.nextInt());
        filledLinkedList = randomizer.ints(COUNT_OF_NUMBERS).boxed().collect(Collectors.toCollection(LinkedList::new));
    }

    private void initArrayLists() {
        emptyArrayList = new ArrayList<>();
        emptyArrayList.add(randomizer.nextInt());
        filledArrayList = randomizer.ints(COUNT_OF_NUMBERS).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    //LinkedList tests

    @Benchmark
    public boolean sequentialAdditionInLinkedList() {
        return emptyLinkedList.add(randomizer.nextInt());
    }

    @Benchmark
    public void randomAdditionInLinkedList() {
        emptyLinkedList.add(getRandomIndex(emptyLinkedList.size()), randomizer.nextInt());
    }

    @Benchmark
    public void iterationInLinkedList() {
        for (Integer i : filledLinkedList);
    }

    @Benchmark
    public void sortingInLinkedList() {
        Collections.sort(filledLinkedList);
    }

    @Benchmark
    public Integer randomRemovingInLinkedList() {
        filledLinkedList.add(0, 0);
        return filledLinkedList.remove(getRandomIndex(filledLinkedList.size()));

    }

    @Benchmark
    public Integer randomAccessInLinkedList() {
        return filledLinkedList.get(getRandomIndex(filledLinkedList.size()));
    }


    //ArrayList tests

    @Benchmark
    public boolean sequentialAdditionInArrayList() {
        return emptyArrayList.add(randomizer.nextInt());
    }

    @Benchmark
    public void randomAdditionInArrayList() {
        emptyArrayList.add(getRandomIndex(emptyArrayList.size()), randomizer.nextInt());
    }

    @Benchmark
    public void iterationInArrayList() {
        for (Integer i : filledArrayList);
    }

    @Benchmark
    public void sortingInArrayList() {
        Collections.sort(filledArrayList);
    }

    @Benchmark
    public Integer randomRemovingInArrayList() {
        filledArrayList.add(0);
        return filledArrayList.remove(getRandomIndex(filledArrayList.size()));
    }

    @Benchmark
    public Integer randomAccessInArrayList() {
        return filledArrayList.get(getRandomIndex(filledArrayList.size()));

    }

    private int getRandomIndex(int num) {
        return Math.abs(randomizer.nextInt(num));

    }

    /*@Benchmark
    public void randomMidAdditionInArrayList() {
        List<Integer> list = emptyArrayList;
        list.add(getMidIndex(list), randomizer.nextInt());
    }

    private int getMidIndex(List<Integer> list) {
        return list.size() / 2;
    }

    @Benchmark
    public void randomMidAdditionInLinkedList() {
        List<Integer> list = emptyLinkedList;
        list.add(getMidIndex(list), randomizer.nextInt());
    }*/
}
