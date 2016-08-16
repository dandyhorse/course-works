package com.epam.training.hadoop.hw3.mapreduce;

import com.epam.training.hadoop.hw3.mapreduce.utils.CommonReducer;
import com.epam.training.hadoop.hw3.mapreduce.utils.WritableInfo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Anton_Solovev
 * @since 8/16/2016.
 */
public class CombineStatistic extends Reducer<Text, WritableInfo, Text, WritableInfo> {

    @Override
    protected void reduce(Text key, Iterable<WritableInfo> values, Context context)
            throws IOException, InterruptedException {

        CommonReducer reducer = new CommonReducer();
        LongAdder ipCounter = new LongAdder();
        Optional<Long> reducedBytes = reducer.reduceStream(values, ipCounter);

        WritableInfo info = new WritableInfo(reducedBytes.orElse(0L), ipCounter.sumThenReset());
        context.write(key, info);
    }

}
