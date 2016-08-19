package com.epam.training.hadoop.hw3.mapreduce.utils;

import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 8/16/2016.
 */
public class CommonReducer {

    public Optional<Long> reduceStream(Iterable<WritableInfo> values, LongAdder ipCounter) {
        Stream<Long> byteStream = Stream.of(values).flatMap(infos -> {
            long sum = 0L;
            for (WritableInfo i : infos) {
                ipCounter.add(i.getCountOfRequestsByIp());
                sum += i.getBytes();
            }
            return Stream.of(sum);
        });
        return byteStream.reduce((left, right) -> left + right);
    }
}
