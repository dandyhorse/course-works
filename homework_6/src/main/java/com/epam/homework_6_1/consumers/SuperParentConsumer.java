package com.epam.homework_6_1.consumers;

import com.epam.homework_6_1.caches.annotations.InjectCache;
import com.epam.homework_6_1.caches.interfaces.ICache;

public class SuperParentConsumer {

    @InjectCache(name = "emptyCache")
    private ICache emptyCache;

    @InjectCache(name = "emptyCache")
    public ICache cache;

    public ICache getEmptyCache() {
        return emptyCache;
    }

    public ICache getCache() {
        return cache;
    }
}
