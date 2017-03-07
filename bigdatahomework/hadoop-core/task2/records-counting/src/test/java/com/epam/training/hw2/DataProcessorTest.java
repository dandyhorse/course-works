package com.epam.training.hw2;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Anton_Solovev
 * @since 8/11/2016.
 */
public class DataProcessorTest {

    private DataProcessor dataProcessor;
    private static final String strNull =
            "c0550000008e5a94ac18823d6f275121 " +
                    "20130218134701883 " +
                    "null " +
                    "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0 " +
                    "119.163.222.* " +
                    "146 " +
                    "147 " +
                    "2 " +
                    "e80f4ec7f5bfbc9ca416a8c01cd1a049 " +
                    "hz55b000008e5a94ac18823d6f275121 " +
                    "Null " +
                    "mm_10982364_973726_9023493 " +
                    "300 " +
                    "250 " +
                    "1 " +
                    "1 " +
                    "(RMB/CPM) (*) 0 " +
                    "f80f4ec7f5bfbc9ca416a8c01cd1a049 1234 7892,12398,87927";
    private static final String strOk =
            "c0550000008e5a94ac18823d6f275121 " +
                    "20130218134701883 " +
                    "dF_5qwD1UDI " +
                    "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0 " +
                    "119.163.222.* " +
                    "146 " +
                    "147 " +
                    "2 " +
                    "e80f4ec7f5bfbc9ca416a8c01cd1a049 " +
                    "hz55b000008e5a94ac18823d6f275121 " +
                    "Null " +
                    "mm_10982364_973726_9023493 " +
                    "300 " +
                    "250 " +
                    "1 " +
                    "1 " +
                    "(RMB/CPM) (*) 0 " +
                    "f80f4ec7f5bfbc9ca416a8c01cd1a049 1234 7892,12398,87927";


    @Test
    public void readAndProcessDataTest() throws Exception {
        FixedTreeSet set = mock(FixedTreeSet.class);
        dataProcessor = new DataProcessor(set, 0L);
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(strOk.getBytes());
        dataProcessor.readAndProcess(arrayInputStream);
        verify(set, times(1)).add(any());
    }

    @Test
    public void readAndProcessDataTestNullSituation() throws Exception {
        FixedTreeSet set = mock(FixedTreeSet.class);
        dataProcessor = new DataProcessor(set, 0L);
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(strNull.getBytes());
        dataProcessor.readAndProcess(arrayInputStream);
        verify(set, times(0)).add(any());
    }
}