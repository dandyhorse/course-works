package com.epam.training.hadoop.hw3.mapreduce.utils;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class WritableInfo implements Writable {

    private Long bytes;
    private Long countOfRequestsByIp;

    public WritableInfo() {
    }

    public WritableInfo(Long bytes, Long countOfRequestsByIp) {
        this.bytes = bytes;
        this.countOfRequestsByIp = countOfRequestsByIp;
    }

    public Long getBytes() {
        return bytes;
    }

    public Long getCountOfRequestsByIp() {
        return countOfRequestsByIp;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(getBytes());
        out.writeLong(getCountOfRequestsByIp());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.bytes = in.readLong();
        this.countOfRequestsByIp = in.readLong();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WritableInfo that = (WritableInfo) o;

        if (bytes != null ? !bytes.equals(that.bytes) : that.bytes != null) return false;
        return countOfRequestsByIp != null ? countOfRequestsByIp.equals(that.countOfRequestsByIp) : that.countOfRequestsByIp == null;
    }

    @Override
    public int hashCode() {
        int result = bytes != null ? bytes.hashCode() : 0;
        result = 31 * result + (countOfRequestsByIp != null ? countOfRequestsByIp.hashCode() : 0);
        return result;
    }
}
