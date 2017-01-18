package com.epam.homework_6_1.caches;

import com.epam.homework_6_1.caches.impl.SubCache;
import com.epam.homework_6_1.caches.interfaces.ICache;
import com.epam.homework_6_1.caches.interfaces.Pool;
import com.epam.homework_6_1.exceptions.PoolException;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

public class CachePoolTest {

    private Pool<ICache> cachePool = CachePool.getInstance();
    private String FIRST_TEST_NAME = "test";
    private String SECOND_TEST_NAME = "random_name";

    @Test
    public void addCache() throws Exception {
        try {
            cachePool.getCache(FIRST_TEST_NAME);
        } catch (PoolException e) {
            System.out.printf("proper exception: " + e.getMessage());
        }
        cachePool.addCache(SubCache::new, () -> FIRST_TEST_NAME);
        Optional<ICache> cache = cachePool.getCache(FIRST_TEST_NAME);
        assertTrue(cache.isPresent());
        assertEquals(cache.get().getClass(), SubCache.class);
    }

    @Test
    public void getCache() throws Exception {
        cachePool.addCache(SubCache::new, () -> FIRST_TEST_NAME);
        Optional<ICache> cache = cachePool.getCache(FIRST_TEST_NAME);
        assertTrue(cache.isPresent());
        assertEquals(cache.get().getClass(), SubCache.class);
    }

    @Test(expected = PoolException.class)
    public void clearCaches() throws Exception {
        cachePool.addCache(SubCache::new, () -> FIRST_TEST_NAME);
        cachePool.addCache(SubCache::new, () -> SECOND_TEST_NAME);
        cachePool.getCache(FIRST_TEST_NAME);
        cachePool.clearCaches();
        cachePool.getCache(SECOND_TEST_NAME);
    }

}