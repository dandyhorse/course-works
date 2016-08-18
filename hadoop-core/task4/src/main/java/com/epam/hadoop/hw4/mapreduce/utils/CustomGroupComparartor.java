package com.epam.hadoop.hw4.mapreduce.utils;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class CustomGroupComparartor extends WritableComparator {

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2) {
        CustomKey key1 = (CustomKey) wc1;
        CustomKey key2 = (CustomKey) wc2;
        return Long.compare(key1.getCityCode(), key2.getCityCode());
    }
}
