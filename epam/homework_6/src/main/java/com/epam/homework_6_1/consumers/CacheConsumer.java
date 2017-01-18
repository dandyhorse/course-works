package com.epam.homework_6_1.consumers;

import com.epam.homework_6_1.caches.annotations.InjectCache;
import com.epam.homework_6_1.caches.interfaces.ICache;

public class CacheConsumer extends ParentConsumer {

    @InjectCache(name = "subCache")
    protected ICache cache;

    private Integer integer;

    public ICache getCache() {
        return this.cache;
    }

}
