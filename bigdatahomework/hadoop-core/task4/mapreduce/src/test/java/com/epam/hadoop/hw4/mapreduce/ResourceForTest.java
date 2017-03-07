package com.epam.hadoop.hw4.mapreduce;

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
 * @since 8/19/2016.
 */
public class ResourceForTest {

    public static final String TEST_BID_PRICE = "12628";
    public static final String TEST_CITY_CODE = "0";
    public static final String TEST_CITY_NAME = "unknown";
    public static final String TEST_OS_TYPE_RAW = "Windows NT 5.1";
    public static final String TEST_OS_TYPE = "Windows XP";
    public static final String TEST_LINE_EXAMPLE = "a80fc47c7b544c78eb54a793e72a198e " +
            "20131027170602323 1 CAV6UQBndoT Mozilla/5.0 (" + TEST_OS_TYPE_RAW + "; rv:24.0) " +
            "Gecko/20100101 Firefox/24.0 10.237.95.*\t0 " + TEST_CITY_CODE + " 1 d2c2382e14b7d7de3a32218dba3192b1\n" +
            "503afcab3679835909f87eedbc559b0\tnull mm_45472847_4168669_13664047 950 90 " +
            "Na Na 0 12628 294 287 null 2261";

    public static Stream<Pair<LongWritable, Text>> getExamplePairStream() throws IOException {
        AtomicInteger integer = new AtomicInteger();
        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/test-example.txt"));
        return Stream.generate(() -> {
            int count = integer.getAndIncrement();
            return new Pair<>(new LongWritable(count), new Text(lines.get(count)));
        }).limit(5);
    }
}
