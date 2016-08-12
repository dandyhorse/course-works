package com.epam.training.hadoop.hw3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class Map extends Mapper<LongWritable, Text, Text, IpBytes> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        super.map(key, value, context);
    }
}
