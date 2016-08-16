package com.epam.training.hadoop.hw3.mapreduce;

import com.epam.training.hadoop.hw3.mapreduce.utils.WritableInfo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.training.hadoop.hw3.mapreduce.ResourcesForTest.*;

/**
 * @author Anton_Solovev
 * @since 8/15/2016.
 */
public class MRTest {

    private MapDriver<LongWritable, Text, Text, WritableInfo> mapDriver;
    private ReduceDriver<Text, WritableInfo, Text, Text> reduceDriver;
    private MapReduceDriver<LongWritable, Text, Text, WritableInfo, Text, Text> mapReduceDriver;
    private Text key;

    @Before
    public void setUp() {
        MapStatistic mapper = new MapStatistic();
        ReduceStatistic reducer = new ReduceStatistic();

        mapDriver = new MapDriver<>();
        mapDriver.setMapper(mapper);

        reduceDriver = new ReduceDriver<>();
        reduceDriver.setReducer(reducer);

        mapReduceDriver = new MapReduceDriver<>();
        mapReduceDriver.setMapper(mapper);
        mapReduceDriver.setReducer(reducer);

        key = new Text(TEST_IP);
    }

    @Test
    public void testMap() throws Exception {
        mapDriver.withInput(new LongWritable(1), new Text(TEST_LINE));
        mapDriver.withOutput(key, new WritableInfo(Long.decode(TEST_BYTES), 1L));
        mapDriver.runTest();
    }

    @Test
    public void testReduce() throws Exception {
        WritableInfo info = new WritableInfo(Long.decode(TEST_BYTES), 1L);
        reduceDriver.withInput(key, Collections.singletonList(info));
        reduceDriver.withOutput(key, new Text(String.format(",%d,%d", info.getBytes(), info.getBytes())));
        reduceDriver.runTest();
    }

    @Test
    public void testMapReduce() throws Exception {
        Stream<Pair<LongWritable, Text>> stream = generatePairStream();
        mapReduceDriver.withAll(stream.collect(Collectors.toList()));
        /* from resources/input/test-res
           ip1: sum = 40028 + 56928
                avg = 96956 / 2
           ip2: sum = 14917 + 12433
                avg = 27350 / 2
           ip4: sum = 0
         */
        mapReduceDriver.addOutput(new Text("ip1"), new Text(String.format(",%d,%d", 48478, 96956)));
        mapReduceDriver.addOutput(new Text("ip2"), new Text(String.format(",%d,%d", 13675, 27350)));
        mapReduceDriver.addOutput(new Text("ip4"), new Text(String.format(",%d,%d", 0, 0)));
        mapReduceDriver.runTest();
    }


}