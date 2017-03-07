package com.epam.hadoop.hw4.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.hadoop.hw4.mapreduce.ResourceForTest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class MRTest {
    private MapDriver<LongWritable, Text, CustomKey, CustomValue> mapDriver;
    private ReduceDriver<CustomKey, CustomValue, Text, Text> reduceDriver;
    private MapReduceDriver<LongWritable, Text, CustomKey, CustomValue, Text, Text> mapReduceDriver;

    private CustomKey key;
    private CustomValue value;
    private String pathCacheCities;
    private String pathCacheRegions;

    @Before
    public void setUp() {
        PriceMapper mapper = new PriceMapper();
        PriceReducer reducer = new PriceReducer();

        mapDriver = new MapDriver<>();
        mapDriver.setMapper(mapper);

        reduceDriver = new ReduceDriver<>();
        reduceDriver.setReducer(reducer);

        mapReduceDriver = new MapReduceDriver<>();
        mapReduceDriver.setMapper(mapper);
        mapReduceDriver.setReducer(reducer);

        long code = Long.parseLong(TEST_CITY_CODE);
        value = new CustomValue(code);
        key = new CustomKey(code, TEST_OS_TYPE);
        pathCacheCities = "C:/Users/Anton_Solovev/share/mapreduce/src/main/resources/city.en.txt";
        pathCacheRegions = "C:/Users/Anton_Solovev/share/mapreduce/src/main/resources/region.en.txt";
    }

    @Test
    public void test() throws Exception {
        assertThat("a", is(not("b")));
    }

    @Test
    public void mapperTest() throws Exception {
        mapDriver.withInput(new LongWritable(1), new Text(TEST_LINE_EXAMPLE));

        mapDriver.withOutput(key, value);
        mapDriver.runTest();
    }

    @Test
    public void reducerTest() throws Exception {
        reduceDriver.addCacheFile(new URI("file:///" + pathCacheCities));
        reduceDriver.addCacheFile(new URI("file:///" + pathCacheRegions));
        reduceDriver.withInput(key, Collections.singletonList(value));
        // new Text("1")  - means there is high bid price
        reduceDriver.withOutput(new Text(TEST_OS_TYPE), new Text(String.format("%s,1", TEST_CITY_NAME)));
        reduceDriver.runTest();
    }


    @Test
    @Ignore
    public void mapReduceTest() throws Exception {
        mapReduceDriver.setKeyGroupingComparator(new CustomGroupComparator());
        mapReduceDriver.addCacheFile(new URI("file:///" + pathCacheCities));
        mapReduceDriver.addCacheFile(new URI("file:///" + pathCacheRegions));
        Stream<Pair<LongWritable, Text>> stream = getExamplePairStream();
        mapReduceDriver.withAll(stream.collect(Collectors.toList()));
        // 0 = unknown 79 = shanghai 393 = taiwan 79 = shanghai 149 = zibo
//        mapReduceDriver.addOutput(new Text("unknown"), new Text("1"));
//        mapReduceDriver.addOutput(new Text("shanghai"), new Text("2"));
//        mapReduceDriver.addOutput(new Text("zibo"), new Text("1"));
//        mapReduceDriver.addOutput(new Text("taiwan"), new Text("1"));
        mapReduceDriver.runTest();
    }

}