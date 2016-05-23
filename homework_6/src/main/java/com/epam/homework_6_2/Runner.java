package com.epam.homework_6_2;

import com.epam.homework_6_2.entity.Person;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.epam.homework_6_2.FunctionsUponStream.*;

public class Runner {

    public void run() {
        int maxPeopleAge = 30;
        String[] poolNames = new String[]{"Tony", "Molly", "Johny", "Alex", "Jennifer", "Kostya", "Xiao", "Teon Greyjoy"};
        RandomPersonsFabric fabric = new RandomPersonsFabric(poolNames, maxPeopleAge);

        Supplier<Stream<Person>> supplierOfPersonStream = fabric.getSupplierOfPersonStream(10);

        printAverageAge(supplierOfPersonStream.get());
        printSortedStream(supplierOfPersonStream.get());
        printGroupsByNames(supplierOfPersonStream.get());
        printAdults(supplierOfPersonStream.get());
        printWomanMinusTen(supplierOfPersonStream.get(), person -> {
            int newAge = person.getAge() - 10;
            if (person.getAge() > 10) {
                return Person.builder().name(person.getName()).sex(person.getSex()).age(newAge).build();
            } else {
                return person;
            }
        });
    }


}
