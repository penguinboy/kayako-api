package org.penguin.kayako.domain;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * An object used to unmarshall api responses from kayako. Should never be returned to a user.
 *
 * @author fatroom
 */
@XmlRootElement(name = "customfields")
public class TicketCustomFieldGroupCollection {

    @XmlElement(name = "group")
    private List<TicketCustomFieldGroup> groups = Lists.newArrayList();

    public List<TicketCustomFieldGroup> getGroups() {
        return groups;
    }
}
