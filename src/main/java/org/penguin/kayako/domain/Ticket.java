package org.penguin.kayako.domain;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * A representation of a kayako ticket. Same as {@link BasicTicket} but includes collection of {@link Post}.
 *
 * @author fatroom
 *
 */
@XmlRootElement(name = "ticket")
public class Ticket extends AbstractTicket {

    @XmlElementWrapper(name = "posts")
    @XmlElement(name = "post")
    private List<Post> posts = Lists.newArrayList();

    public List<Post> getPosts() {
        return posts;
    }

}
