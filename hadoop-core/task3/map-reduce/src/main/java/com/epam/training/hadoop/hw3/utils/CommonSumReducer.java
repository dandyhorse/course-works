package com.epam.training.hadoop.hw3.utils;


import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class CommonSumReducer {

    public Optional<Long> reduceToSumOfBytes(Iterable<BytesInfo> values, LongAdder counter) {
        Stream<Long> byteStream = Stream.of(values).flatMap(infos -> {
            long sum = 0L;
            for (BytesInfo i : infos) {
                counter.add(i.getCount());
                sum += i.getBytes();
            }
            return Stream.of(sum);
        });
        return byteStream.reduce((left, right) -> left + right);
    }
}
