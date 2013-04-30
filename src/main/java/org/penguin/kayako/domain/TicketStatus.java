package org.penguin.kayako.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A representation of a kayako ticket status.
 *
 * @author fatroom
 */
@XmlRootElement(name = "ticketstatus")
public class TicketStatus {
    @XmlElement
    private int id;
    @XmlElement
    private String title;
    @XmlElement(name = "displayorder")
    private int displayOrder;
    @XmlElement(name = "departmentid")
    private int departmentId;
    @XmlElement(name = "displayicon")
    private String displayIcon;
    @XmlElement
    @XmlJavaTypeAdapter(KayakoAccessibility.KayakoAccessibilityAdapter.class)
    private KayakoAccessibility type;
    @XmlElement(name = "displayinmainlist")
    private boolean displayInMainList;
    @XmlElement(name = "markasresolved")
    private boolean markAsResolved;
    @XmlElement(name = "displaycount")
    private boolean displayCount;
    @XmlElement(name = "statuscolor")
    private String statusColor;
    @XmlElement(name = "statusbgcolor")
    private String statusBackgroundColor;
    @XmlElement(name = "resetduetime")
    private boolean resetDueTime;
    @XmlElement(name = "triggersurvey")
    private boolean triggerSurvey;
    @XmlElement(name = "staffvisibilitycustom")
    private boolean staffVisibilityCustom;
    @XmlElement(name = "staffgroupid")
    private Integer staffGroupId;

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

    public String getDisplayIcon() {
        return displayIcon;
    }

    public KayakoAccessibility getType() {
        return type;
    }

    public boolean isDisplayInMainList() {
        return displayInMainList;
    }

    public boolean isMarkAsResolved() {
        return markAsResolved;
    }

    public boolean isDisplayCount() {
        return displayCount;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public String getStatusBackgroundColor() {
        return statusBackgroundColor;
    }

    public boolean isResetDueTime() {
        return resetDueTime;
    }

    public boolean isTriggerSurvey() {
        return triggerSurvey;
    }

    public boolean isStaffVisibilityCustom() {
        return staffVisibilityCustom;
    }

    public Integer getStaffGroupId() {
        return staffGroupId;
    }
}
