package org.penguin.kayako;

import junit.framework.Assert;
import org.junit.Test;
import org.penguin.kayako.domain.Post;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Date;

import static junit.framework.Assert.*;

public class PostTests {
    @Test
    public void testPostUnmarshall() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_post.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Post.class);

        // act
        Post note = (Post) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        Assert.assertNotNull(note);
    }

    @Test
    public void testNoteContentCorrect() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_post.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Post.class);

        // act
        Post post = (Post) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, post.getId());
        assertEquals(2, post.getTicketId());
        assertEquals(3, post.getUserId());
        assertEquals("John Doe", post.getUserFullName());
        assertEquals("john.doe@kayako.com", post.getUserEmail());
        assertTrue(post.getUserEmailTo().isEmpty());
        assertEquals("127.0.0.1", post.getIpAddress());
        assertEquals(4, post.getAttachmentsCount());
        assertEquals(5, post.getCreatorId());
        assertFalse(post.isThirdparty());
        assertTrue(post.isHtml());
        assertFalse(post.isEmailed());
        assertEquals(6, post.getStaffId());
        assertFalse(post.isSurveycomment());
        assertEquals("I am just testing", post.getContents());
        // Look for Wed Feb 16, 18:46:57 EST 2011
        assertEquals(new Date(1297842417L * 1000), post.getDateline());
    }

}
