package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.TicketPriorityCollection;
import org.penguin.kayako.domain.TicketTypeCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketPriorityCollectionTests {
    @Test
    public void testTicketPriorityCollectionUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketpriorities.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketPriorityCollection.class);

        // act
        TicketPriorityCollection collection = (TicketPriorityCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));

        // assert
        assertNotNull(collection);
        assertEquals(2, collection.getPriorities().size());
    }
}
