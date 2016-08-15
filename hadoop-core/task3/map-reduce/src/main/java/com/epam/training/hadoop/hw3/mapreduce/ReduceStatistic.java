package com.epam.training.hadoop.hw3.mapreduce;

import com.epam.training.hadoop.hw3.mapreduce.utils.WritableInfo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class ReduceStatistic extends Reducer<Text, WritableInfo, Text, Text> {

    private LongAdder ipCounter = new LongAdder();
    private Map<String, LongAdder> browsersMapCounter = new HashMap<>();

    @Override
    protected void reduce(Text key, Iterable<WritableInfo> values, Context context)
            throws IOException, InterruptedException {

        Stream<Long> byteStream = Stream.of(values).flatMap(infos -> {
            long sum = 0L;
            for (WritableInfo i : infos) {
                String name = i.getBrowsersName().toLowerCase();
                ipCounter.add(i.getCountOfRequestsByIp());
                sum += i.getBytes();
                countBrowsers(name);
            }
            return Stream.of(sum);
        });
        Optional<Long> reducedBytes = byteStream.reduce((left, right) -> left + right);

        Long byteSum = reducedBytes.orElseThrow(() -> new RuntimeException("cannot reduce to a number. byte optional stream is null"));
        Long byteAvg = (byteSum / ipCounter.sumThenReset());

        String str = String.format(",%d,%d", byteAvg, byteSum);
        context.write(key, new Text(str));
    }

    private void countBrowsers(String name) {
        browsersMapCounter.putIfAbsent(name, new LongAdder());
        browsersMapCounter.computeIfPresent(name, (s, longAdder) -> {
            longAdder.increment();
            return longAdder;
        });
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        browsersMapCounter.forEach((browserName, count) -> System.out.printf("%s : %d\n", browserName, count.sum()));
    }
}