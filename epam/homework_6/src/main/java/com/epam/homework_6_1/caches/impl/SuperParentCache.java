package com.epam.homework_6_1.caches.impl;

import com.epam.homework_6_1.caches.annotations.Cache;
import com.epam.homework_6_1.caches.interfaces.ICache;

import java.util.HashMap;

@Cache(name = "superParentCache")
public class SuperParentCache implements ICache {
    private HashMap<Integer, String> cache;

    public SuperParentCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public String get(Integer key) {
        return cache.get(key);
    }

    @Override
    public void set(Integer key, String value) {
        cache.put(key, value);
    }
}
