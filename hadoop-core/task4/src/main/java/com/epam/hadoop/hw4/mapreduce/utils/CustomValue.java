package com.epam.hadoop.hw4.mapreduce.utils;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class CustomValue implements Writable {

    private Long cityCode;
    private int bidPrice;

    public CustomValue(Long cityCode, int bidPrice) {
        this.cityCode = cityCode;
        this.bidPrice = bidPrice;
    }

    public CustomValue() {
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(cityCode);
        dataOutput.writeInt(bidPrice);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.cityCode = dataInput.readLong();
        this.bidPrice = dataInput.readInt();
    }

    public Long getCityCode() {
        return cityCode;
    }

    public int getBidPrice() {
        return bidPrice;
    }
}
