package org.penguin.kayako.domain;


import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.TicketStatusCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketStatusCollectionTests {
    @Test
    public void testTicketStatusCollectionUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketstatuses.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketStatusCollection.class);

        // act
        TicketStatusCollection collection = (TicketStatusCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));

        // assert
        assertNotNull(collection);
        assertEquals(2, collection.getStatuses().size());
    }
}
