package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.AttachmentCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class AttachmentCollectionTests {
    @Test
    public void testAttachmentsUnmarshall() throws Exception {
        // arrange
        String ticketsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_attachments.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(AttachmentCollection.class);

        // act
        AttachmentCollection collection = (AttachmentCollection) unmarshaller.unmarshal(new StringReader(ticketsXml));

        // assert
        assertNotNull(collection);
        assertEquals(1, collection.getAttachments().size());
    }
}
