package org.penguin.kayako.domain;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * A representation of a kayako ticket custom field group.
 *
 * @author fatroom
 */
@XmlRootElement(name = "group")
public class TicketCustomFieldGroup {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String title;
    @XmlElement(name = "field")
    private List<TicketCustomField> fields = Lists.newArrayList();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<TicketCustomField> getFields() {
        return fields;
    }
}
