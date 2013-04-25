package org.penguin.kayako;

import org.junit.Assert;
import org.junit.Test;
import org.penguin.kayako.domain.BasicTicketCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class BasicTicketCollectionTests {
    @Test
    public void testBasicTicketUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_basictickets.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicketCollection.class);
        
        // act
        BasicTicketCollection tickets = (BasicTicketCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));
        
        // assert
        Assert.assertNotNull(tickets);
        Assert.assertEquals(1, tickets.getTickets().size());
    }
}
