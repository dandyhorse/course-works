package com.epam.training.hadoop.hw3;

import com.epam.training.hadoop.hw3.utils.BrowserInfo;
import com.epam.training.hadoop.hw3.utils.BytesInfo;
import com.epam.training.hadoop.hw3.utils.WritableInfo;
import com.epam.training.hadoop.hw3.utils.StringSearcher;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class Map extends Mapper<LongWritable, Text, Text, WritableInfo> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        StringSearcher searcher = new StringSearcher(value.toString());
        String ip = searcher.getIp();
        Long bytes = Long.decode(searcher.getBytes());
        String browser = searcher.getBrowserName();

        BytesInfo byteInfo = new BytesInfo(bytes, 1L);
        BrowserInfo browserInfo = new BrowserInfo(browser, 1L);

        WritableInfo info = new WritableInfo(byteInfo, browserInfo);
        context.write(new Text(ip), info);
    }
}
