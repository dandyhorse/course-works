package com.epam.training.hadoop.hw3.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.types.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 8/15/2016.
 */
public class ResourcesForTest {

    public static final String TEST_IP = "ip1";
    public static final String TEST_BYTES = "40028";
    public static final String TEST_BROWSER_NAME = "Mozilla";
    public static final String TEST_LINE = TEST_IP + " - - [24/Apr/2011:04:06:01 -0400] " +
            "\"GET /~strabal/grease/photo9/927-3.jpg HTTP/1.1\" " +
            "200 " + TEST_BYTES + " \"-\" \"" + TEST_BROWSER_NAME + "/5.0 " +
            "(compatible; YandexImages/3.0; +http://yandex.com/bots)\"";

    public static Stream<Pair<LongWritable, Text>> generatePairStream() throws IOException {
        AtomicInteger integer = new AtomicInteger();
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/input/test-res"));
        return Stream.generate(() -> {
            int sum = integer.getAndIncrement();
            return new Pair<>(new LongWritable(sum), new Text(lines.get(sum)));
        }).limit(5);
    }
}
