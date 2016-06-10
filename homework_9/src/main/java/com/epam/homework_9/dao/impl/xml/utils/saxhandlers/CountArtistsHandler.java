package com.epam.homework_9.dao.impl.xml.utils.saxhandlers;

import com.epam.homework_9.dao.impl.xml.utils.Tags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CountArtistsHandler extends DefaultHandler {

    private Long artistCount = 0L;

    public Long getCount() {
        return artistCount;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case Tags.PREFIX + ":" + Tags.ARTIST_TAG:
                artistCount++;
        }
    }
}
