package com.epam.homework_6_1.consumers;

import com.epam.homework_6_1.caches.annotations.InjectCache;
import com.epam.homework_6_1.caches.interfaces.ICache;

public class ParentConsumer extends SuperParentConsumer {

    @InjectCache(name = "superCache")
    private ICache parentCache;

    @InjectCache(name = "superCache")
    protected ICache cache;

    private String message = "example text";

    public ICache getParentCache() {
        return this.parentCache;
    }

    public ICache getCache() {
        return this.cache;
    }

}
