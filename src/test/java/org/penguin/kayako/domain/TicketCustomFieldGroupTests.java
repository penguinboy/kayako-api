package org.penguin.kayako.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;

import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.TicketCustomFieldGroup;
import org.penguin.kayako.util.ContentLoader;

public class TicketCustomFieldGroupTests {
    @Test
    public void testTicketCustomFieldGroupUnmarshall() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketcustomfieldgroup.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketCustomFieldGroup.class);
        
        // act
        TicketCustomFieldGroup group = (TicketCustomFieldGroup) unmarshaller.unmarshal(new StringReader(noteXml));
        
        // assert
        assertNotNull(group);
    }
    
    @Test
    public void testTicketCustomFieldGroupContentCorrect() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketcustomfieldgroup.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketCustomFieldGroup.class);
        
        // act
        TicketCustomFieldGroup group = (TicketCustomFieldGroup) unmarshaller.unmarshal(new StringReader(noteXml));
        
        // assert
        assertEquals(1, group.getId());
        assertEquals("Test Ticket", group.getTitle());
        
        assertFalse(group.getFields().isEmpty());
        assertEquals(5, group.getFields().size());
        
    }
}
