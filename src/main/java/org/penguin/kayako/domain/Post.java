package org.penguin.kayako.domain;

import org.penguin.kayako.adapters.UnixDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * A representation of a kayako post.
 *
 * @author fatroom
 *
 */
@XmlRootElement(name = "post")
public class Post {
    @XmlElement
    private int id;
    @XmlElement(name = "ticketid")
    private int ticketId;
    @XmlElement(name = "dateline")
    @XmlJavaTypeAdapter(UnixDateAdapter.class)
    private Date dateline;
    @XmlElement(name = "userid")
    private int userId;
    @XmlElement(name = "fullname")
    private String userFullName;
    @XmlElement(name = "email")
    private String userEmail;
    @XmlElement(name = "emailto")
    private String userEmailTo;
    @XmlElement(name = "ipaddress")
    private String ipAddress;
    @XmlElement(name = "creator")
    private int creatorId;
    @XmlElement(name = "hasattachments")
    private int attachmentsCount;
    @XmlElement(name = "isthirdparty")
    private boolean thirdparty;
    @XmlElement(name = "ishtml")
    private boolean html;
    @XmlElement(name = "isemailed")
    private boolean emailed;
    @XmlElement(name = "issurveycomment")
    private boolean surveycomment;
    @XmlElement(name = "staffid")
    private int staffId;
    @XmlElement(name = "contents")
    private String contents;

    public int getId() {
        return id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Date getDateline() {
        return dateline;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserEmailTo() {
        return userEmailTo;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public int getAttachmentsCount() {
        return attachmentsCount;
    }

    public boolean isThirdparty() {
        return thirdparty;
    }

    public boolean isHtml() {
        return html;
    }

    public boolean isEmailed() {
        return emailed;
    }

    public boolean isSurveycomment() {
        return surveycomment;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getContents() {
        return contents;
    }
}
