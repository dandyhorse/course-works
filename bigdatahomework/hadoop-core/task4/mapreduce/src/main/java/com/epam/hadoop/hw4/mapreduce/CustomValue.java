package com.epam.hadoop.hw4.mapreduce;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class CustomValue implements Writable {

    private long cityCode;

    public CustomValue(long cityCode) {
        this.cityCode = cityCode;
    }

    public CustomValue() {
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(cityCode);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.cityCode = dataInput.readLong();
    }

    public long getCityCode() {
        return cityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomValue value = (CustomValue) o;

        return cityCode == value.cityCode;

    }

    @Override
    public int hashCode() {
        return (int) (cityCode ^ (cityCode >>> 32));
    }
}
