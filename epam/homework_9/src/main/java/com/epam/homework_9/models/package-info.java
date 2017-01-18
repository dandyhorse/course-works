@XmlSchema(
        namespace = "http://www.epam.com/musicGuide",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {@XmlNs(prefix = Tags.PREFIX, namespaceURI = "http://www.epam.com/musicGuide")})
package com.epam.homework_9.models;

import com.epam.homework_9.dao.impl.xml.utils.Tags;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;