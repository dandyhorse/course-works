package com.epam.homework_6_1.consumers;

import com.epam.homework_6_1.caches.annotations.InjectCache;
import com.epam.homework_6_1.caches.interfaces.ICache;

public class SuperParentConsumer {

    @InjectCache(name = "superParentCache")
    public ICache superParentCache;

    @InjectCache(name = "superParentCache")
    ICache cache;

    public ICache getSuperParentCache() {
        return this.superParentCache;
    }

    public ICache getCache() {
        return this.cache;
    }
}
