package org.penguin.kayako.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A representation of a kayako ticket custom field.
 *
 * @author fatroom
 */
@XmlRootElement(name = "field")
public class TicketCustomField {
    @XmlAttribute
    private int id;
    @XmlAttribute
    @XmlJavaTypeAdapter(CustomFieldType.CustomFieldTypeAdapter.class)
    private CustomFieldType type;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String title;
    @XmlAttribute
    private String filename;

    @XmlValue
    private String content;

    public int getId() {
        return id;
    }

    public CustomFieldType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getFilename() {
        return filename;
    }

    public String getContent() {
        return content;
    }
}
