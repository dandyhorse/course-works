package com.epam.training.hadoop.hw3.mapreduce.utils;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class BytesInfo {

    private Long bytes;
    private Long countOfRequestsByIp;

    public BytesInfo(Long bytes, Long countOfRequestsByIp) {
        this.bytes = bytes;
        this.countOfRequestsByIp = countOfRequestsByIp;
    }

    public Long getCountOfRequestsByIp() {
        return countOfRequestsByIp;
    }

    public Long getBytes() {
        return bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BytesInfo bytesInfo = (BytesInfo) o;

        if (bytes != null ? !bytes.equals(bytesInfo.bytes) : bytesInfo.bytes != null) return false;
        return countOfRequestsByIp != null ? countOfRequestsByIp.equals(bytesInfo.countOfRequestsByIp) : bytesInfo.countOfRequestsByIp == null;

    }

    @Override
    public int hashCode() {
        int result = bytes != null ? bytes.hashCode() : 0;
        result = 31 * result + (countOfRequestsByIp != null ? countOfRequestsByIp.hashCode() : 0);
        return result;
    }
}
