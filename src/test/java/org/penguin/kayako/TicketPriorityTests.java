package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.KayakoAccessibility;
import org.penguin.kayako.domain.TicketPriority;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.*;

public class TicketPriorityTests {
    @Test
    public void testTicketPriorityUnmarshall() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketpriority.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketPriority.class);

        // act
        TicketPriority priority = (TicketPriority) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertNotNull(priority);
    }

    @Test
    public void testTicketPriorityContentCorrect() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketpriority.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketPriority.class);

        // act
        TicketPriority priority = (TicketPriority) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, priority.getId());
        assertEquals("Critical", priority.getTitle());
        assertEquals(2, priority.getDisplayOrder());
        assertEquals("#ffffff", priority.getForegroundColorCode());
        assertEquals("#d6d6d6", priority.getBackgroundColorCode());

        assertEquals("icon.png", priority.getIcon());
        assertEquals(KayakoAccessibility.PUBLIC, priority.getType());
        assertFalse(priority.isCustomVisibility());
        assertNull(priority.getUserGroupId());
    }

    @Test
    public void testTicketPriorityEnabledCustomVisibility() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketpriority2.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketPriority.class);

        // act
        TicketPriority priority = (TicketPriority) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, priority.getId());
        assertEquals("Critical", priority.getTitle());
        assertEquals(2, priority.getDisplayOrder());
        assertEquals("#ffffff", priority.getForegroundColorCode());
        assertEquals("#d6d6d6", priority.getBackgroundColorCode());

        assertEquals("icon.png", priority.getIcon());
        assertEquals(KayakoAccessibility.PRIVATE, priority.getType());
        assertTrue(priority.isCustomVisibility());
        assertEquals(Integer.valueOf(3), priority.getUserGroupId());
    }
}
