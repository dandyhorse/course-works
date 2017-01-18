package com.epam.homework_6_1.analyzers;

import com.epam.homework_6_1.caches.annotations.Cache;
import com.epam.homework_6_1.caches.interfaces.ICache;
import com.epam.homework_6_1.exceptions.AnaliseException;

public class CacheAnnotationAnalyzer {

    public static String analyseCacheName(Class<? extends ICache> clazz) {
        Cache cacheAnnotation = clazz.getDeclaredAnnotation(Cache.class);
        checkAbsentAnnotation(clazz, cacheAnnotation);
        String name = cacheAnnotation.name();
        checkEmptyName(clazz, name);
        return name;
    }

    private static void checkAbsentAnnotation(Class<? extends ICache> clazz, Cache cacheAnnotation) {
        if (cacheAnnotation == null) {
            throw new  AnaliseException(clazz.getSimpleName() +
                    ".class is not annotated with " +
                    Cache.class.getName());
        }
    }

    private static void checkEmptyName(Class<? extends ICache> clazz, String name) {
        if (name == null || name.equals("")) {
            throw new AnaliseException(clazz.getSimpleName() +
                    ".class: name in annotation " +
                    Cache.class.getName() +
                    " is empty");
        }
    }
}
