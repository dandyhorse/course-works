package com.epam.hadoop.hw4.mapreduce;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Anton_Solovev
 * @since 8/18/2016.
 */
public class CustomKey implements WritableComparable<CustomKey> {
    private long cityCode;
    private String osType;

    public CustomKey(long cityCode, String osType) {
        this.cityCode = cityCode;
        this.osType = osType;
    }

    public CustomKey() {
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(cityCode);
        dataOutput.writeUTF(osType);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.cityCode = dataInput.readLong();
        this.osType = dataInput.readUTF();
    }

    @Override
    public int compareTo(CustomKey o) {
        return Long.compare(this.cityCode, o.cityCode);
    }

    public long getCityCode() {
        return cityCode;
    }

    public String getOsType() {
        return osType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomKey customKey = (CustomKey) o;

        if (cityCode != customKey.cityCode) return false;
        return osType != null ? osType.equals(customKey.osType) : customKey.osType == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (cityCode ^ (cityCode >>> 32));
        result = 31 * result + (osType != null ? osType.hashCode() : 0);
        return result;
    }
}
