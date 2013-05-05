package org.penguin.kayako.domain;

import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.NoteCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NoteCollectionTests {
    @Test
    public void testNotesUnmarshall() throws Exception {
        // arrange
        String xml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_notes.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(NoteCollection.class);

        // act
        NoteCollection notes = (NoteCollection) unmarshaller.unmarshal(new StringReader(xml));

        // assert
        assertNotNull(notes);
        assertEquals(1, notes.getNotes().size());
    }
}
