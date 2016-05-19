package com.epam.homework_6_1.analyzers;

import com.epam.homework_6_1.consumers.CacheConsumer;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.*;

public class InjectCacheAnnotationAnalyzerTest {

    @Test
    public void analyseInjectCacheNames() throws Exception {
        CacheConsumer consumer = new CacheConsumer();
        Set<String> nameSet = InjectCacheAnnotationAnalyzer.analyseInjectCacheNames(consumer);
        assertNotNull(nameSet);
        System.out.println(nameSet);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoAnnotatedFields() throws Exception {
        Object obj = new Object();
        InjectCacheAnnotationAnalyzer.analyseInjectCacheNames(obj);
    }

}