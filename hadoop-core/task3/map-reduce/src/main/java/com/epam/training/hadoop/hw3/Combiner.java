package com.epam.training.hadoop.hw3;

import com.epam.training.hadoop.hw3.utils.BytesInfo;
import com.epam.training.hadoop.hw3.utils.CommonSumReducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class Combiner extends Reducer<Text, BytesInfo, Text, BytesInfo> {

    private static final Long defaultSumOfBytes = 0L;
    private LongAdder counter = new LongAdder();
    private CommonSumReducer reducer = new CommonSumReducer();

    @Override
    protected void reduce(Text key, Iterable<BytesInfo> values, Context context)
            throws IOException, InterruptedException {
        Optional<Long> sumOfBytesOpt = reducer.reduceToSumOfBytes(values, counter);
        BytesInfo bytesInfo = new BytesInfo(
                sumOfBytesOpt.orElse(defaultSumOfBytes),
                counter.sumThenReset());
        context.write(key, bytesInfo);
    }


}
