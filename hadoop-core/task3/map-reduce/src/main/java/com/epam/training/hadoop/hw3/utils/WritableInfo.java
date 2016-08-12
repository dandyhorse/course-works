package com.epam.training.hadoop.hw3.utils;

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

    public String getBrowserName() {
        return browserInfo.getBrowserName();
    }

    public long getBrowserCount() {
        return browserInfo.getBrowserCount();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(getBytes());
        out.writeLong(getCountOfRequestsByIp());
        out.writeUTF(getBrowserName());
        out.writeLong(getBrowserCount());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        Long bytes = in.readLong();
        Long countOfRequestsByIp = in.readLong();

        String browserName = in.readUTF();
        Long browserCount = in.readLong();

        byteInfo = new BytesInfo(bytes, countOfRequestsByIp);
        browserInfo = new BrowserInfo(browserName, browserCount);
    }

}
