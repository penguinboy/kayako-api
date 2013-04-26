package org.penguin.kayako;

import org.junit.Assert;
import org.junit.Test;
import org.penguin.kayako.domain.TicketCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @author Roman Romanchuk<br>
 *         Date: 4/26/13<br>
 *         Time: 11:25 AM<br>
 * @version $Revision$
 * @lastrevision $Date$
 * @contributor $LastChangedBy$
 */
public class TicketCollectionTests {
    @Test
    public void testTicketsUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_tickets.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketCollection.class);

        // act
        TicketCollection tickets = (TicketCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));

        // assert
        Assert.assertNotNull(tickets);
        Assert.assertEquals(1, tickets.getTickets().size());
    }
}
