package org.penguin.kayako.domain;

import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.CustomFieldType;
import org.penguin.kayako.domain.TicketCustomField;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.*;

public class TicketCustomFieldTests {
    @Test
    public void testTicketCustomFieldUnmarshall() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketcustomfield.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketCustomField.class);

        // act
        TicketCustomField field = (TicketCustomField) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertNotNull(field);
    }

    @Test
    public void testTicketCustomFieldContentCorrect() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketcustomfield.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketCustomField.class);

        // act
        TicketCustomField field = (TicketCustomField) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, field.getId());
        assertEquals(CustomFieldType.TEXTAREA, field.getType());
        assertEquals("s3cr3t", field.getName());
        assertEquals("Test title", field.getTitle());
        assertEquals("screenshot.png", field.getFilename());
        assertEquals("Test Plaintext Field", field.getContent());
    }

    @Test
    public void testTicketCustomFieldContentCorrectMissingFilename() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketcustomfield2.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketCustomField.class);

        // act
        TicketCustomField field = (TicketCustomField) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, field.getId());
        assertEquals(CustomFieldType.TEXTAREA, field.getType());
        assertEquals("s3cr3t", field.getName());
        assertEquals("Test title", field.getTitle());
        assertNull(field.getFilename());
        assertEquals("Test Plaintext Field", field.getContent());
    }
}
