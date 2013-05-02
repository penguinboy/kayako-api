package org.penguin.kayako.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A representation of a kayako ticket type.
 *
 * @author fatroom
 */
@XmlRootElement(name = "tickettype")
public class TicketType {
    @XmlElement
    private int id;
    @XmlElement
    private String title;
    @XmlElement(name = "displayorder")
    private int displayOrder;
    @XmlElement(name = "departmentid")
    private int departmentId;
    @XmlElement(name = "displayicon")
    private String icon;
    @XmlElement
    @XmlJavaTypeAdapter(KayakoAccessibility.KayakoAccessibilityAdapter.class)
    private KayakoAccessibility type;
    @XmlElement(name = "uservisibilitycustom")
    private boolean customVisibility;
    @XmlElement(name = "usergroupid")
    private Integer userGroupId;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getIcon() {
        return icon;
    }

    public KayakoAccessibility getType() {
        return type;
    }

    public boolean isCustomVisibility() {
        return customVisibility;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }
}
