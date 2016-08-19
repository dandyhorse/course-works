package com.epam.training.hadoop.hw3.mapreduce;

import com.epam.training.hadoop.hw3.mapreduce.utils.CommonReducer;
import com.epam.training.hadoop.hw3.mapreduce.utils.WritableInfo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class ReduceStatistic extends Reducer<Text, WritableInfo, Text, Text> {


    @Override
    protected void reduce(Text key, Iterable<WritableInfo> values, Context context)
            throws IOException, InterruptedException {

        LongAdder ipCounter = new LongAdder();
        CommonReducer reducer = new CommonReducer();
        Optional<Long> reducedBytes = reducer.reduceStream(values, ipCounter);

        Long byteSum = reducedBytes.orElseThrow(() -> new RuntimeException("cannot reduce to a number. bytes optional stream is null"));
        Long byteAvg = (byteSum / ipCounter.sumThenReset());

        String str = String.format(",%d,%d", byteAvg, byteSum);
        context.write(key, new Text(str));
    }


}