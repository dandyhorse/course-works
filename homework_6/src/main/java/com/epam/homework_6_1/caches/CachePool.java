package com.epam.homework_6_1.caches;

import com.epam.homework_6_1.caches.interfaces.ICache;
import com.epam.homework_6_1.caches.interfaces.Pool;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Pool of singleton Caches with lazy init
 */
public class CachePool implements Pool<ICache> {
    private static CachePool instance;
    private HashMap<String, ICache> createdCaches;
    private HashMap<String, Supplier<ICache>> postponedCaches;

    private CachePool() {
        createdCaches = new HashMap<>();
        postponedCaches = new HashMap<>();
    }

    public static CachePool getInstance() {
        if (instance == null) {
            instance = new CachePool();
        }
        return instance;
    }

    /**
     * add with replacing
     *
     * @param creationSup - creating function of ICache
     */
    @Override
    public void addCache(Supplier<ICache> creationSup, Supplier<String> getNameSup) {
        String name = getNameSup.get();
        Optional<String> optionalName = Optional.of(name);
        optionalName.ifPresent(s -> {
            postponedCaches.put(s, creationSup);
            if (createdCaches.containsKey(s)) {
                createdCaches.remove(s);
            }
        });
    }

    @Override
    public Optional<ICache> getCache(String name) {
        Optional<ICache> optionalCache = Optional.ofNullable(createdCaches.get(name));
        if (!optionalCache.isPresent()) {
            if (postponedCaches.containsKey(name)) {
                Supplier<ICache> sup = postponedCaches.get(name);
                ICache cache = sup.get();
                createdCaches.put(name, cache);
                postponedCaches.remove(name);
                optionalCache = Optional.of(cache);
            } else {
                throw new NoSuchElementException("element with name " + name + " was not found");
            }
        }
        return optionalCache;
    }

    @Override
    public void clearCaches() {
        createdCaches.clear();
        postponedCaches.clear();
    }

    @Override
    public boolean isEmpty() {
        return createdCaches.isEmpty() && postponedCaches.isEmpty();
    }

    @Override
    public int size() {
        return createdCaches.size() + postponedCaches.size();
    }

}
