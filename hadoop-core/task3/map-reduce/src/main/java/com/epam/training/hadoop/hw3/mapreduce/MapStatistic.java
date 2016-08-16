package com.epam.training.hadoop.hw3.mapreduce;

import com.epam.training.hadoop.hw3.mapreduce.utils.StringSearcher;
import com.epam.training.hadoop.hw3.mapreduce.utils.WritableInfo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class MapStatistic extends Mapper<LongWritable, Text, Text, WritableInfo> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        StringSearcher searcher = new StringSearcher(value.toString());
        String ip = searcher.getIp();
        Long bytes = Long.decode(searcher.getBytes());
        String browserName = searcher.getBrowserName();
        Counter counter = context.getCounter("browsers", browserName);
        counter.increment(1L);
        WritableInfo info = new WritableInfo(bytes, 1L);
        context.write(new Text(ip), info);
    }
}
