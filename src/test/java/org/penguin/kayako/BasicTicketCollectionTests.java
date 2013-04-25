package org.penguin.kayako;

import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;
import org.penguin.kayako.domain.BasicTicketCollection;

import com.google.common.io.CharStreams;

public class BasicTicketCollectionTests {
    @Test
    public void testBasicTicketUnmarshall() throws Exception {
        // arrange
        String ticketsXml = CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("/example_xml_basictickets.xml")));
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicketCollection.class);
        
        // act
        BasicTicketCollection tickets = (BasicTicketCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));
        
        // assert
        Assert.assertNotNull(tickets);
        Assert.assertEquals(1, tickets.getTickets().size());
    }
}
