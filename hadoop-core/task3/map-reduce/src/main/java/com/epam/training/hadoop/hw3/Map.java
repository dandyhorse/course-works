package com.epam.training.hadoop.hw3;

import com.epam.training.hadoop.hw3.utils.BytesInfo;
import com.epam.training.hadoop.hw3.utils.StringSearcher;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class Map extends Mapper<LongWritable, Text, Text, BytesInfo> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        StringSearcher searcher = new StringSearcher(value.toString());
        String bytes = searcher.getBytes();
        String ip = searcher.getIp();
        BytesInfo info = new BytesInfo(Long.decode(bytes));
        context.write(new Text(ip), info);
    }
}
