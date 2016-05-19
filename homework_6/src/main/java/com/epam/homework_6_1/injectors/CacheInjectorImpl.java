package com.epam.homework_6_1.injectors;

import com.epam.homework_6_1.analyzers.InjectCacheAnnotationAnalyzer;
import com.epam.homework_6_1.caches.CachePool;
import com.epam.homework_6_1.caches.interfaces.ICache;
import com.epam.homework_6_1.caches.interfaces.Pool;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

public class CacheInjectorImpl implements Injector {

    private void injectFromPool(Object toObject, Pool<ICache> fromPool) {
        Set<String> names = InjectCacheAnnotationAnalyzer.analyseInjectCacheNames(toObject);

        names.forEach(annotationName -> {
            Optional<ICache> cache = fromPool.getCache(annotationName);
            cache.ifPresent(iCache -> injectCache(toObject, iCache, annotationName));
        });

    }

    private void injectCache(Object toObject, ICache iCache, String annotationName) {
        Set<Field> fieldSet = InjectCacheAnnotationAnalyzer.analyseInjectCacheFields(toObject, annotationName);
        fieldSet.forEach(setCacheInto(toObject, iCache));
    }

    private Consumer<Field> setCacheInto(Object toObject, ICache iCache) {
        return field -> {
            try {
                if (field.isAccessible()) {
                    field.set(toObject, iCache);
                } else {
                    field.setAccessible(true);
                    field.set(toObject, iCache);
                    field.setAccessible(false);

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void inject(Object toObject) {
        CachePool pool = CachePool.getInstance();
        checkIsPoolEmpty(pool);
        injectFromPool(toObject, pool);
    }

    private void checkIsPoolEmpty(CachePool pool) {
        if (pool.isEmpty()) {
            throw new NoSuchElementException("pool is empty");
        }
    }

}
