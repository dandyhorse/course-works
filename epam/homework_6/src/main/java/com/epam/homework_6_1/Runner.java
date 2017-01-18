package com.epam.homework_6_1;

import com.epam.homework_6_1.analyzers.CacheAnnotationAnalyzer;
import com.epam.homework_6_1.caches.impl.SuperParentCache;
import com.epam.homework_6_1.caches.CachePool;
import com.epam.homework_6_1.caches.impl.SubCache;
import com.epam.homework_6_1.caches.impl.ParentCache;
import com.epam.homework_6_1.caches.interfaces.ICache;
import com.epam.homework_6_1.caches.interfaces.Pool;
import com.epam.homework_6_1.consumers.CacheConsumer;
import com.epam.homework_6_1.injectors.Injector;
import com.epam.homework_6_1.injectors.CacheInjectorImpl;

import java.util.function.Supplier;

public class Runner {
    /* TODO
     1.  Analise Caches and Lazy init Caches
     2.  Init Consumers
   2.2.  Analise Consumers - recursively
     3.  Inject Caches into Consumers
     4.  Do anything with Consumers
     */
    public static void run() {
        Pool<ICache> pool = CachePool.getInstance();
        //create caches
        pool.addCache(SuperParentCache::new, () -> CacheAnnotationAnalyzer.analyseCacheName(SuperParentCache.class));
        pool.addCache(getCacheSupplier(), () -> CacheAnnotationAnalyzer.analyseCacheName(SubCache.class));
        pool.addCache(ParentCache::new, () -> CacheAnnotationAnalyzer.analyseCacheName(ParentCache.class));

        //create consumers of a cache
        CacheConsumer consumer = new CacheConsumer();

        //inject from Pool
        Injector injector = new CacheInjectorImpl();
        injector.inject(consumer);

        System.out.println(consumer.getSuperParentCache());
        System.out.println(consumer.getParentCache());
        System.out.println(consumer.getCache());
    }

    private static Supplier<ICache> getCacheSupplier() {
        return () -> {
            ICache cache = new SubCache();
            cache.set(1, "one");
            cache.set(2, "two");
            return cache;
        };
    }

}
