package com.epam.homework_9.dao.impl.xml.utils.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Duration;


public class JaxbDurationStringAdapter extends XmlAdapter<String, Duration> {

    @Override
    public Duration unmarshal(String v) throws Exception {
        return Duration.parse(v);
    }

    @Override
    public String marshal(Duration v) throws Exception {
        return v.toString();
    }
}
