package org.penguin.kayako.domain;

import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.TicketStatusCollection;
import org.penguin.kayako.domain.TicketTypeCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketTypeCollectionTests {
    @Test
    public void testTicketTypeCollectionUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_tickettypes.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketTypeCollection.class);

        // act
        TicketTypeCollection collection = (TicketTypeCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));

        // assert
        assertNotNull(collection);
        assertEquals(2, collection.getTypes().size());
    }
}
