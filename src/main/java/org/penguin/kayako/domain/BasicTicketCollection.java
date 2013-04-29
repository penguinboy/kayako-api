package org.penguin.kayako.domain;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * An object used to unmarshall api responses from kayako. Should never be returned to a user.
 * 
 * @author raynerw
 */
@XmlRootElement(name = "tickets")
public class BasicTicketCollection {
    
    @XmlElement(name = "ticket")
    private List<BasicTicket> tickets = Lists.newArrayList();
    
    public List<BasicTicket> getTickets() {
        return tickets;
    }
    
}
