package com.epam.training.hadoop.hw3.utils;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class BytesInfo implements Writable {
    private Long countOfRequestsByIp;
    private Long bytes;

    public BytesInfo() {
    }

    public BytesInfo(Long bytes) {
        this.countOfRequestsByIp = 1L;
        this.bytes = bytes;
    }

    public BytesInfo(Long bytes, Long count) {
        this.countOfRequestsByIp = count;
        this.bytes = bytes;
    }

    public Long getCount() {
        return countOfRequestsByIp;
    }

    public Long getBytes() {
        return bytes;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(countOfRequestsByIp);
        out.writeLong(bytes);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        countOfRequestsByIp = in.readLong();
        bytes = in.readLong();
    }
}
