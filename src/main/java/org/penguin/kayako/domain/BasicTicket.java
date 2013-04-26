package org.penguin.kayako.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A representation of a kayako ticket. Includes all available information except for any associated posts.
 * 
 * @author raynerw
 * 
 */
@XmlRootElement(name = "ticket")
public class BasicTicket extends AbstractTicket {

}
