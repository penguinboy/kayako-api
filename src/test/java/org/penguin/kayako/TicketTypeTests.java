package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.KayakoAccessibility;
import org.penguin.kayako.domain.TicketType;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.*;

public class TicketTypeTests {
    @Test
    public void testTicketTypeUnmarshall() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_tickettype.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketType.class);

        // act
        TicketType type = (TicketType) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertNotNull(type);
    }

    @Test
    public void testTicketTypeContentCorrect() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_tickettype.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketType.class);

        // act
        TicketType type = (TicketType) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, type.getId());
        assertEquals("Feedback", type.getTitle());
        assertEquals(2, type.getDisplayOrder());
        assertEquals(3, type.getDepartmentId());
        assertEquals("icon.png", type.getIcon());
        assertEquals(KayakoAccessibility.PUBLIC, type.getType());
        assertFalse(type.isCustomVisibility());
        assertNull(type.getUserGroupId());
    }

    @Test
    public void testTicketTypeEnabledCustomVisibility() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_tickettype2.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketType.class);

        // act
        TicketType type = (TicketType) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, type.getId());
        assertEquals("Feedback", type.getTitle());
        assertEquals(2, type.getDisplayOrder());
        assertEquals(3, type.getDepartmentId());
        assertEquals("icon.png", type.getIcon());
        assertEquals(KayakoAccessibility.PRIVATE, type.getType());
        assertTrue(type.isCustomVisibility());
        assertEquals(Integer.valueOf(4), type.getUserGroupId());
    }
}
