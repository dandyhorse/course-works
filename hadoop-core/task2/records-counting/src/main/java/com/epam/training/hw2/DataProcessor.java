package com.epam.training.hw2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 8/11/2016.
 */
public class DataProcessor {

    private Long counter;
    private FixedTreeSet iPinSet;

    public DataProcessor(FixedTreeSet iPinSet, Long counter) {
        this.iPinSet = iPinSet;
        this.counter = counter;
    }


    public long readAndProcess(InputStream in) throws IOException {
        Scanner scanner = new Scanner(in);
        String pattern = "(?:\\s)";
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(pattern);
            Stream<String> stringStream = Stream.of(split).limit(3).skip(2);
            Optional<String> id = stringStream.findFirst();
            id.ifPresent(s -> {
                if (!s.equals("null")) {
                    iPinSet.add(s);
                    counter++;
                }
            });
        }
        return counter;
    }
}
