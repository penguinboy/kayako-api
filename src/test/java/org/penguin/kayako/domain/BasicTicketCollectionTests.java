package org.penguin.kayako.domain;

import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.BasicTicketCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class BasicTicketCollectionTests {
    @Test
    public void testBasicTicketUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_basictickets.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicketCollection.class);
        
        // act
        BasicTicketCollection tickets = (BasicTicketCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));
        
        // assert
        assertNotNull(tickets);
        assertEquals(1, tickets.getTickets().size());
    }
}
