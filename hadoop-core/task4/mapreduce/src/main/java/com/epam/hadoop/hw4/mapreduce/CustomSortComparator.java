package com.epam.hadoop.hw4.mapreduce;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author Anton_Solovev
 * @since 8/22/2016.
 */
public class CustomSortComparator extends WritableComparator {

    protected CustomSortComparator() {
        super(CustomKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CustomKey key1 = (CustomKey) a;
        CustomKey key2 = (CustomKey) b;
        int cmp = key1.getOsType().compareTo(key2.getOsType());
        if (cmp == 0) {
            cmp = Long.compare(key1.getCityCode(), key2.getCityCode());
        }
        return cmp;
    }
}
