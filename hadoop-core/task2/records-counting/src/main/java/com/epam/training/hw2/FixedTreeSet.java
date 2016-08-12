package com.epam.training.hw2;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author Anton_Solovev
 * @since 8/10/2016.
 */
public class FixedTreeSet extends TreeSet<String> {

    private final int MAX_SIZE;

    public FixedTreeSet(int max_size, Comparator<String> comparator) {
        super(comparator);
        MAX_SIZE = max_size;
    }

    @Override
    public boolean add(String s) {
        boolean b;
        if (super.size() < MAX_SIZE) {
            b = super.add(s);
        } else {
            super.pollLast();
            b = super.add(s);
        }
        return b;
    }
}
