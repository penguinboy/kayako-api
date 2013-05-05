package org.penguin.kayako.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.penguin.kayako.adapters.UnixDateAdapter;

/**
 * A representation of a kayako ticket attachment.
 * 
 * @author fatroom
 */
@XmlRootElement(name = "attachment")
public class Attachment {
    @XmlElement
    private int id;
    @XmlElement(name = "ticketid")
    private int ticketId;
    @XmlElement(name = "ticketpostid")
    private int postId;
    @XmlElement
    private String filename;
    @XmlElement(name = "filesize")
    private long size;
    @XmlElement(name = "filetype")
    private String fileType;
    @XmlElement(name = "dateline")
    @XmlJavaTypeAdapter(UnixDateAdapter.class)
    private Date date;
    @XmlElement
    private String contents;
    
    public int getId() {
        return id;
    }
    
    /**
     * @return The id of the ticket this attachment is associated with
     */
    public int getTicketId() {
        return ticketId;
    }
    
    /**
     * @return The id of the ticket post this attachment is associated with
     */
    public int getPostId() {
        return postId;
    }
    
    public String getFilename() {
        return filename;
    }
    
    /**
     * @return The size of the attachment in kb
     */
    public long getSize() {
        return size;
    }
    
    /**
     * @return The mime type of the attachment
     */
    public String getFileType() {
        return fileType;
    }
    
    /**
     * @return The date this attachment was associated with the ticket post
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * @return Base64 encoded string of the attachment
     */
    public String getContents() {
        return contents;
    }
}
