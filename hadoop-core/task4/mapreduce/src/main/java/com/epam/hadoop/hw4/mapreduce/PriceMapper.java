package com.epam.hadoop.hw4.mapreduce;

import com.epam.hadoop.hw4.mapreduce.utils.StringSearcher;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class PriceMapper extends Mapper<LongWritable, Text, CustomKey, CustomValue> {
    private static final int BID_PRICE = 250;
    private static final Logger logger = Logger.getLogger(PriceReducer.class);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Path directory = context.getWorkingDirectory();
        if (directory != null) {
            logger.info("training:" + directory.getName() + ", " + directory.getParent().getName());
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        logger.warn("INFOOOOOOOOO");
        StringSearcher searcher = new StringSearcher(value.toString());

        int bidPrice = Integer.decode(searcher.getBidPrice());
        String cityCodeString = searcher.getCityCode();
        String osType = searcher.getOSType();

        if (bidPrice > BID_PRICE) {
            long code = Long.parseLong(cityCodeString);
            context.write(new CustomKey(code, osType), new CustomValue(code));
        }
    }
}
