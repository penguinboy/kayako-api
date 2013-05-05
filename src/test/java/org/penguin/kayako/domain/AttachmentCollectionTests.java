package org.penguin.kayako.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;

import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.AttachmentCollection;
import org.penguin.kayako.util.ContentLoader;

public class AttachmentCollectionTests {
    @Test
    public void testAttachmentsUnmarshall() throws Exception {
        // arrange
        String attachmentssXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_attachments.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(AttachmentCollection.class);
        
        // act
        AttachmentCollection collection = (AttachmentCollection) unmarshaller.unmarshal(new StringReader(attachmentssXml));
        
        // assert
        assertNotNull(collection);
        assertEquals(1, collection.getAttachments().size());
    }
    
    @Test
    public void testMultipleAttachmentsUnmarshall() throws Exception {
        // arrange
        String attachmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_attachments2.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(AttachmentCollection.class);
        
        // act
        AttachmentCollection collection = (AttachmentCollection) unmarshaller.unmarshal(new StringReader(attachmentsXml));
        
        // assert
        assertEquals(2, collection.getAttachments().size());
    }
    
    @Test
    public void testMultipleAttachmentsUnmarshallAndAreDifferent() throws Exception {
        // arrange
        String attachmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_attachments2.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(AttachmentCollection.class);
        
        // act
        AttachmentCollection collection = (AttachmentCollection) unmarshaller.unmarshal(new StringReader(attachmentsXml));
        
        // assert
        Assert.assertTrue("Attachment ids are different",
                collection.getAttachments().get(0).getId() != collection.getAttachments().get(1).getId());
    }
}
