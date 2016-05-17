package com.epam.homework_6_1.chaches;

import com.epam.homework_6_1.chaches.interfaces.ICache;

import java.util.HashSet;

@com.epam.homework_6_1.chaches.annotations.Cache(name = "integer")
public class IntegerCacheImpl implements ICache<Integer> {
    private HashSet<Integer> integerHashSet;

    public IntegerCacheImpl() {
        integerHashSet = new HashSet<>();
    }

    @Override
    public Integer get() {
        return null;
    }

    @Override
    public void set(Integer integer) {

    }
}
