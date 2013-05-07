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
@XmlRootElement(name = "attachments")
public class AttachmentCollection {
    @XmlElement(name = "attachment")
    private List<Attachment> attachments = Lists.newArrayList();

    public List<Attachment> getAttachments() {
        return attachments;
    }
}
