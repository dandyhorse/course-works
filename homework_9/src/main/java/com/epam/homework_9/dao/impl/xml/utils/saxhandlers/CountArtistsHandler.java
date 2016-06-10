package com.epam.homework_9.dao.impl.xml.utils.saxhandlers;

import com.epam.homework_9.dao.impl.xml.utils.Tags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class CountArtistsHandler extends DefaultHandler {

    private Set<Long> idSet;

    public CountArtistsHandler() {
        this.idSet = new HashSet<>();
    }

    public Set<Long> getCount() {
        return idSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case Tags.PREFIX + ":" + Tags.ARTIST_TAG:
                String id = attributes.getValue("id");
                idSet.add(Long.valueOf(id));
        }
    }
}
