package com.epam.homework_6_1.analyzers;

import com.epam.homework_6_1.caches.annotations.Cache;
import com.epam.homework_6_1.caches.interfaces.ICache;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CacheAnnotationAnalyzerTest {

    @Cache(name = "test")
    private class TestCache implements ICache {

        @Override
        public String get(Integer k) {
            return null;
        }

        @Override
        public void set(Integer key, String value) {

        }

    }

    private class AbsentAnnotationCache implements ICache {

        @Override
        public String get(Integer k) {
            return null;
        }

        @Override
        public void set(Integer key, String value) {

        }

    }

    @Cache(name = "")
    private class EmptyNameCache implements ICache {

        @Override
        public String get(Integer k) {
            return null;
        }

        @Override
        public void set(Integer key, String value) {

        }

    }

    @Test
    public void testAnalyseCacheName() throws Exception {
        ICache cache = new TestCache();
        String annotatedName = CacheAnnotationAnalyzer.analyseCacheName(cache.getClass());
        assertEquals(annotatedName, "test");
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyName() throws Exception {
        ICache cache = new EmptyNameCache();
        CacheAnnotationAnalyzer.analyseCacheName(cache.getClass());
    }

    @Test(expected = NoSuchElementException.class)
    public void testAbsentAnnotation() throws Exception {
        ICache cache = new AbsentAnnotationCache();
        CacheAnnotationAnalyzer.analyseCacheName(cache.getClass());
    }

}