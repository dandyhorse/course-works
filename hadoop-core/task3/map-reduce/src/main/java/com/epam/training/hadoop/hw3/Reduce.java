package com.epam.training.hadoop.hw3;

import com.epam.training.hadoop.hw3.utils.WritableInfo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class Reduce extends Reducer<Text, WritableInfo, Text, Text> {

    private LongAdder ipCounter = new LongAdder();
    private HashMap<String, LongAdder> map = new HashMap<>();

    @Override
    protected void reduce(Text key, Iterable<WritableInfo> values, Context context)
            throws IOException, InterruptedException {

        Stream<Long> byteStream = Stream.of(values).flatMap(infos -> {
            long sum = 0L;
            for (WritableInfo i : infos) {
                String name = i.getBrowserName().toLowerCase();
                map.putIfAbsent(name, new LongAdder());
                map.get(name).increment();
                ipCounter.add(i.getCountOfRequestsByIp());
                sum += i.getBytes();
            }
            return Stream.of(sum);
        });
        Optional<Long> reducedBytes = byteStream.reduce((left, right) -> left + right);

        Long byteSum = reducedBytes.orElse(0L);
        Long byteAvg = (byteSum / ipCounter.sumThenReset());

        map.forEach((browserName, count) -> System.out.printf("%s : %d", browserName, count.sum()));

        String str = String.format(",%d,%d", byteAvg, byteSum);
        context.write(key, new Text(str));
    }

}