package org.penguin.kayako.domain;

import org.penguin.kayako.adapters.UnixDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

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

    public int getTicketId() {
        return ticketId;
    }

    public int getPostId() {
        return postId;
    }

    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public String getFileType() {
        return fileType;
    }

    public Date getDate() {
        return date;
    }

    public String getContents() {
        return contents;
    }
}
