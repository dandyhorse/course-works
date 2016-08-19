package com.epam.hadoop.hw4.mapreduce;

import com.epam.hadoop.hw4.mapreduce.utils.CityCodeRepo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class Reduce extends Reducer<CustomKey, CustomValue, Text, Text> {

    private HashMap<Long, String> cityCodeMap;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        cityCodeMap = new HashMap<>();
        URI uri = context.getCacheFiles()[0];
    }

    @Override
    protected void reduce(CustomKey key, Iterable<CustomValue> values, Context context)
            throws IOException, InterruptedException {

        Long cityCode = key.getCityCode();
        String cityName = CityCodeRepo.getCityName(cityCode);


        context.write(new Text(key.getOsType() + " " + cityName), new Text("amount bid: "));
    }
}
