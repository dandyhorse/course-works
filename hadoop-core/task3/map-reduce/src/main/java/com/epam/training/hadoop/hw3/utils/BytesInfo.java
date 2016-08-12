package com.epam.training.hadoop.hw3.utils;

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
}
