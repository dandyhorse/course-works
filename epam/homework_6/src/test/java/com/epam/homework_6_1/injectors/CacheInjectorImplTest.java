package com.epam.homework_6_1.injectors;

import com.epam.homework_6_1.analyzers.CacheAnnotationAnalyzer;
import com.epam.homework_6_1.caches.CachePool;
import com.epam.homework_6_1.caches.impl.SuperParentCache;
import com.epam.homework_6_1.caches.impl.SubCache;
import com.epam.homework_6_1.caches.impl.ParentCache;
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
        pool.addCache(ParentCache::new, () -> CacheAnnotationAnalyzer.analyseCacheName(ParentCache.class));
        pool.addCache(SuperParentCache::new, () -> CacheAnnotationAnalyzer.analyseCacheName(SuperParentCache.class));
        pool.addCache(SubCache::new, () -> CacheAnnotationAnalyzer.analyseCacheName(SubCache.class));
    }

    @Test
    public void testOneLevelInject() throws Exception {
        SuperParentConsumer consumer = new SuperParentConsumer();

        Injector injector = new CacheInjectorImpl();
        injector.inject(consumer);

        assertEquals(consumer.getSuperParentCache().getClass(), SuperParentCache.class);
        assertEquals(consumer.getCache().getClass(), SuperParentCache.class);
    }

    @Test
    public void testTwoLevelInject() throws Exception {
        ParentConsumer consumer = new ParentConsumer();

        Injector injector = new CacheInjectorImpl();
        injector.inject(consumer);

        assertEquals(consumer.getSuperParentCache().getClass(), SuperParentCache.class);
        assertEquals(consumer.getParentCache().getClass(), ParentCache.class);
        assertEquals(consumer.getCache().getClass(), ParentCache.class);
    }

    @Test
    public void testManyLevelsInject() throws Exception {
        CacheConsumer consumer = new CacheConsumer();

        Injector injector = new CacheInjectorImpl();
        injector.inject(consumer);

        assertEquals(consumer.getSuperParentCache().getClass(), SuperParentCache.class);
        assertEquals(consumer.getParentCache().getClass(), ParentCache.class);
        assertEquals(consumer.getCache().getClass(), SubCache.class);
    }
}