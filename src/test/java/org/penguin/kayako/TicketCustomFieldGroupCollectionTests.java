package org.penguin.kayako;


import org.junit.Test;
import org.penguin.kayako.domain.BasicTicketCollection;
import org.penguin.kayako.domain.TicketCustomFieldGroupCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketCustomFieldGroupCollectionTests {
    @Test
    public void testTicketCustomFieldGroupCollectionUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketcustomfields.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketCustomFieldGroupCollection.class);

        // act
        TicketCustomFieldGroupCollection collection = (TicketCustomFieldGroupCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));

        // assert
        assertNotNull(collection);
        assertEquals(1, collection.getGroups().size());
    }
}
