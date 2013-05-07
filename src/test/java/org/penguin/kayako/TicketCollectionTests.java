package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.TicketCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class TicketCollectionTests {
    @Test
    public void testTicketsUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_tickets.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketCollection.class);

        // act
        TicketCollection tickets = (TicketCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));

        // assert
        assertNotNull(tickets);
        assertEquals(1, tickets.getTickets().size());
    }
}
