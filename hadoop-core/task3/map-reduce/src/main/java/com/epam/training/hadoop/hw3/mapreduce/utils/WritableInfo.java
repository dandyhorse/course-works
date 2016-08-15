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

    private BytesInfo byteInfo;
    private BrowserInfo browserInfo;

    public WritableInfo() {
    }

    public WritableInfo(BytesInfo byteInfo, BrowserInfo browserInfo) {
        this.byteInfo = byteInfo;
        this.browserInfo = browserInfo;
    }

    public Long getCountOfRequestsByIp() {
        return byteInfo.getCountOfRequestsByIp();
    }

    public Long getBytes() {
        return byteInfo.getBytes();
    }

    public String getBrowsersName() {
        return browserInfo.getBrowserName();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(getBytes());
        out.writeLong(getCountOfRequestsByIp());
        out.writeUTF(getBrowsersName());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        Long bytes = in.readLong();
        Long countOfRequestsByIp = in.readLong();

        String browserName = in.readUTF();

        byteInfo = new BytesInfo(bytes, countOfRequestsByIp);
        browserInfo = new BrowserInfo(browserName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WritableInfo that = (WritableInfo) o;

        if (!byteInfo.equals(that.byteInfo)) return false;
        return browserInfo.equals(that.browserInfo);

    }

    @Override
    public int hashCode() {
        int result = byteInfo.hashCode();
        result = 31 * result + browserInfo.hashCode();
        return result;
    }
}
