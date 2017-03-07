package com.epam.hadoop.hw4.mapreduce;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class CustomGroupComparator extends WritableComparator {

    public CustomGroupComparator() {
        super(CustomKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CustomKey key1 = (CustomKey) a;
        CustomKey key2 = (CustomKey) b;
        return Long.compare(key1.getCityCode(), key2.getCityCode());
    }



}
