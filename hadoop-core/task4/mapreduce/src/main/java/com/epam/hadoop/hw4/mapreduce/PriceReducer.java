package com.epam.hadoop.hw4.mapreduce;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class PriceReducer extends Reducer<CustomKey, CustomValue, Text, Text> {

    private Map<Long, String> cityCodeMap;
    private static final Logger logger = Logger.getLogger(PriceReducer.class);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Path directory = context.getWorkingDirectory();
        if (directory != null) {
            logger.info("training:" + directory.getName() + ", " + directory.getParent().getName());
        }
        Path[] fileClassPaths = context.getFileClassPaths();
        if (fileClassPaths != null) {
            for (Path p : fileClassPaths)
                logger.info("training:" + p.getName() + ", " + p.isUriPathAbsolute());
        }
        URI[] cacheFiles = context.getCacheFiles();
        FileSystem fs = FileSystem.get(context.getConfiguration());
        cityCodeMap = readCacheToMap(fs, cacheFiles[0], cacheFiles[1]);
    }

    private Map<Long, String> readCacheToMap(FileSystem fs, URI uriFirst, URI uriSecond) throws IOException {
        Map<Long, String> map;
        try (FSDataInputStream inputStreamF = fs.open(new Path(uriFirst));
             FSDataInputStream inputStreamS = fs.open(new Path(uriSecond))) {
            BufferedReader bufferF = new BufferedReader(new InputStreamReader(inputStreamF));
            BufferedReader bufferS = new BufferedReader(new InputStreamReader(inputStreamS));
            Stream<String> streamFirst = bufferF.lines();
            Stream<String> streamSecond = bufferS.lines();
            map = Stream.concat(streamFirst, streamSecond)
                    .collect(Collectors.toMap(getKeyMap(), getValueMap(), (s, s2) -> s));
        }
        return map;
    }

    private Function<String, String> getValueMap() {
        return s -> s.replaceFirst("\\d+\\s*", "");
    }

    private Function<String, Long> getKeyMap() {
        return s -> Long.decode(s.replaceFirst("\\s\\w+", ""));
    }

    @Override
    protected void reduce(CustomKey key, Iterable<CustomValue> values, Context context) throws IOException, InterruptedException {
        Long code = key.getCityCode();
        String cityName = cityCodeMap.get(code);
        Long counter = 0L;
        for (CustomValue v : values) {
            counter++;
        }
        context.write(new Text(key.getOsType()), new Text(String.format("%s,%d", cityName, counter)));
    }

}
