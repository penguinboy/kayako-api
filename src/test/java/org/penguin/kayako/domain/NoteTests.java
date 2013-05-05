package org.penguin.kayako.domain;

import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.Note;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NoteTests {
    
    @Test
    public void testNoteUnmarshall() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_note.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Note.class);
        
        // act
        Note note = (Note) unmarshaller.unmarshal(new StringReader(noteXml));
        
        // assert
        assertNotNull(note);
    }
    
    @Test
    public void testNoteContentCorrect() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_note.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Note.class);
        
        // act
        Note note = (Note) unmarshaller.unmarshal(new StringReader(noteXml));
        
        // assert
        assertEquals("ticket", note.getType());
        assertEquals(1, note.getId());
        assertEquals(2, note.getTicketId());
        assertEquals(3, note.getColor());
        assertEquals(4, note.getCreatorStaffId());
        assertEquals(5, note.getForStaffId());
        assertEquals("Varun Shoor", note.getCreatorStaffName());
        assertEquals("This is a ticket note", note.getContent());
        
        // Look for Wed Feb 16, 18:46:57 EST 2011
        assertEquals(new Date((long) 1297842417 * 1000), note.getCreationDate());
    }
}
