package com.epam.homework_6_1.injectors;

import com.epam.homework_6_1.analyzers.CacheAnnotationAnalyzer;
import com.epam.homework_6_1.caches.CachePool;
import com.epam.homework_6_1.caches.impl.EmptyCache;
import com.epam.homework_6_1.caches.impl.SubCache;
import com.epam.homework_6_1.caches.impl.SuperCache;
import com.epam.homework_6_1.caches.interfaces.ICache;
import com.epam.homework_6_1.caches.interfaces.Pool;
import com.epam.homework_6_1.consumers.CacheConsumer;
import com.epam.homework_6_1.consumers.ParentConsumer;
import com.epam.homework_6_1.consumers.SuperParentConsumer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheInjectorImplTest {

    @Before
    public void setUp() throws Exception {
        Pool<ICache> pool = CachePool.getInstance();
        pool.addCache(SuperCache::new, () -> CacheAnnotationAnalyzer.analyseCacheName(SuperCache.class));
        pool.addCache(EmptyCache::new, () -> CacheAnnotationAnalyzer.analyseCacheName(EmptyCache.class));
        pool.addCache(SubCache::new, () -> CacheAnnotationAnalyzer.analyseCacheName(SubCache.class));
    }

    @Test
    public void testTwoLevelInject() throws Exception {
        ParentConsumer consumer = new ParentConsumer();
        Injector injector = new CacheInjectorImpl();
        injector.inject(consumer);
        ICache cache = consumer.getCache();
        ICache emptyCache = consumer.getEmptyCache();
//        assertEquals(cache.getClass(), SuperCache.class);
        assertEquals(emptyCache.getClass(), EmptyCache.class);
        System.out.println(cache);
        System.out.println(emptyCache);
    }

    @Test
    public void testManyLevelsInject() throws Exception {
        CacheConsumer consumer = new CacheConsumer();
        Injector injector = new CacheInjectorImpl();

        injector.inject(consumer);

//        assertEquals(consumer.getCache().getClass(), SubCache.class);

        //TODO improve test
    }

    @Test
    public void testOneLevelInject() throws Exception {
        SuperParentConsumer consumer = new SuperParentConsumer();
        Injector injector = new CacheInjectorImpl();
        injector.inject(consumer);
        System.out.println(consumer.getEmptyCache());
        assertEquals(consumer.getEmptyCache().getClass(), EmptyCache.class);
//        assertEquals(consumer.getCache().getClass(), EmptyCache.class);
    }
}