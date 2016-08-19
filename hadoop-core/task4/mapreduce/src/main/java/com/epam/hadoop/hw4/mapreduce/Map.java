package com.epam.hadoop.hw4.mapreduce;

import com.epam.hadoop.hw4.mapreduce.utils.StringSearcher;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class Map extends Mapper<LongWritable, Text, CustomKey, CustomValue> {

    private static final int BID_PRICE = 250;

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        StringSearcher searcher = new StringSearcher(value.toString());

        int bidPrice = Integer.decode(searcher.getBidPrice());
        String osType = searcher.getOSType();
        long cityCode = Long.parseLong(searcher.getCityCode());

        if (bidPrice > BID_PRICE) {
            context.write(new CustomKey(cityCode, osType), new CustomValue(cityCode, bidPrice));
        }
    }
}
