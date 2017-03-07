package com.epam.hadoop.hw4.mapreduce;

import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Objects;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class OSPartitioner extends Partitioner<CustomKey, CustomValue> {

    @Override
    public int getPartition(CustomKey customKey, CustomValue customValue, int numPartitions) {
        int hashCode = Objects.hashCode(customKey.getOsType());
        return Math.abs(hashCode % numPartitions);
    }
}
