package org.penguin.kayako.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.penguin.kayako.adapters.UnixDateAdapter;

@XmlRootElement(name = "note")
public class Note {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private int id;
    @XmlAttribute(name = "ticketid")
    private int ticketId;
    @XmlAttribute(name = "notecolor")
    private int color;
    @XmlAttribute(name = "creatorstaffid")
    private int creatorStaffId;
    @XmlAttribute(name = "forstaffid")
    private int forStaffId;
    @XmlAttribute(name = "creatorstaffname")
    private String creatorStaffName;
    @XmlAttribute(name = "creationdate")
    @XmlJavaTypeAdapter(UnixDateAdapter.class)
    private Date creationDate;
    
    @XmlValue
    private String content;
    
    public String getType() {
        return type;
    }
    
    public int getId() {
        return id;
    }
    
    public int getTicketId() {
        return ticketId;
    }
    
    public int getColor() {
        return color;
    }
    
    public int getCreatorStaffId() {
        return creatorStaffId;
    }
    
    public int getForStaffId() {
        return forStaffId;
    }
    
    public String getCreatorStaffName() {
        return creatorStaffName;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public String getContent() {
        return content;
    }
    
}
