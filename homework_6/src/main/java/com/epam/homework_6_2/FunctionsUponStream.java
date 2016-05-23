package com.epam.homework_6_2;

import com.epam.homework_6_2.entity.EnumSex;
import com.epam.homework_6_2.entity.Person;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionsUponStream {

    public static void printWomanMinusTen(Stream<Person> personStream, UnaryOperator<Person> func) {
        Stream<Person> stream = printStreamAndCopy(personStream, "printWomanMinusTen()\nStream values");
        Stream<Person> youngWomenStream = stream.filter(p -> p.getSex().equals(EnumSex.WOMAN)).map(func);
        printStream(youngWomenStream, "Young Women:");
    }

    public static void printAdults(Stream<Person> personStream) {
        Stream<Person> stream = printStreamAndCopy(personStream, "printAdults()\nStream values");
        Stream<Person> filtered = stream.filter(person -> person.getAge() >= 18);
        printStream(filtered, "Stream of Adult Person");
    }

    public static void printGroupsByNames(Stream<Person> personStream) {
        Stream<Person> stream = printStreamAndCopy(personStream, "printGroupsByNames()\nStream values:");
        Map<String, Long> map = stream.collect(Collectors.toMap(Person::getName, p -> 1L, (existedV, newV) -> existedV + 1));
        System.out.println("Groups by name");
        map.forEach((s, aLong) -> System.out.printf("Name: %s", s + ": " + aLong));
    }

    public static void printSortedStream(Stream<Person> personStream) {
        Stream<Person> stream = printStreamAndCopy(personStream, "printSortedStream()\nBefore sorting:");
        Stream<Person> sorted = stream.sorted((o1, o2) -> o1.getAge() - o2.getAge());
        printStream(sorted, "After sorting:");
    }

    public static void printAverageAge(Stream<Person> personStream) {
        Stream<Person> stream = printStreamAndCopy(personStream, "printAverageAge()\nStream values:");
        IntSummaryStatistics statistic = stream.collect(Collectors.summarizingInt(Person::getAge));
        int averageAge = (int) statistic.getAverage();
        System.out.printf("average age of persons is: %d\n", averageAge);
    }

    private static Stream<Person> printStreamAndCopy(Stream<Person> personStream, String title) {
        List<Person> tempLIst = personStream.collect(Collectors.toList());
        printStream(tempLIst.stream(), title);
        return tempLIst.stream();
    }

    private static void printStream(Stream<Person> personStream, String title) {
        System.out.println(title);
        personStream.forEach(System.out::println);
    }

}
