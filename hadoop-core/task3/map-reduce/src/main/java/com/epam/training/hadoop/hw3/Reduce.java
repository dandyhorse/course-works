package com.epam.training.hadoop.hw3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class Reduce extends Reducer<Text, IpBytes, Text, IpBytes> {
    @Override
    protected void reduce(Text key, Iterable<IpBytes> values, Context context) throws IOException, InterruptedException {
        super.reduce(key, values, context);
    }
}
