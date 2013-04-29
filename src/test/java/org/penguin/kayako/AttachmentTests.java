package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.Attachment;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AttachmentTests {
    @Test
    public void testAttachmentUnmarshalling() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_attachment.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Attachment.class);

        // act
        Attachment attachment = (Attachment) unmarshaller.unmarshal(new StringReader(ticketXml));

        // assert
        assertNotNull(attachment);
    }

    @Test
    public void testTicketContentCorrect() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_attachment.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Attachment.class);

        // act
        Attachment attachment = (Attachment) unmarshaller.unmarshal(new StringReader(ticketXml));

        // assert
        assertEquals(1, attachment.getId());
        assertEquals(2, attachment.getTicketId());
        assertEquals(3, attachment.getPostId());
        assertEquals("icon_chart.gif", attachment.getFilename());
        assertEquals(4, attachment.getSize());
        assertEquals("image/gif", attachment.getFileType());
        // Look for Wed Feb 16, 18:46:57 EST 2011
        assertEquals(new Date(1297842417L * 1000), attachment.getDate());
        assertNull(attachment.getContents());
    }

    @Test
    public void testTicketContentCorrectWithContent() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_attachment2.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Attachment.class);

        // act
        Attachment attachment = (Attachment) unmarshaller.unmarshal(new StringReader(ticketXml));

        // assert
        assertEquals(1, attachment.getId());
        assertEquals(2, attachment.getTicketId());
        assertEquals(3, attachment.getPostId());
        assertEquals("icon_chart.gif", attachment.getFilename());
        assertEquals(541, attachment.getSize());
        assertEquals("image/gif", attachment.getFileType());
        // Look for Wed Feb 16, 18:46:57 EST 2011
        assertEquals(new Date(1297842417L * 1000), attachment.getDate());
        assertEquals("iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAGvSURBVDjLpZO7alZREEbXiSdqJJDKYJNCkPBXYq12prHwBezSCpaidnY+graCYO0DpLRTQcR3EFLl8p+9525xgkRIJJApB2bN+gZmqCouU+NZzVef9isyUYeIRD0RTz482xouBBBNHi5u4JlkgUfx+evhxQ2aJRrJ/oFjUWysXeG45cUBy+aoJ90Sj0LGFY6anw2o1y/mK2ZS5pQ50+2XiBbdCvPk+mpw2OM/Bo92IJMhgiGCox+JeNEksIC11eLwvAhlzuAO37+BG9y9x3FTuiWTzhH61QFvdg5AdAZIB3Mw50AKsaRJYlGsX0tymTzf2y1TR9WwbogYY3ZhxR26gBmocrxMuhZNE435FtmSx1tP8QgiHEvj45d3jNlONouAKrjjzWaDv4CkmmNu/Pz9CzVh++Yd2rIz5tTnwdZmAzNymXT9F5AtMFeaTogJYkJfdsaaGpyO4E62pJ0yUCtKQFxo0hAT1JU2CWNOJ5vvP4AIcKeao17c2ljFE8SKEkVdWWxu42GYK9KE4c3O20pzSpyyoCx4v/6ECkCTCqccKorNxR5uSXgQnmQkw2Xf+Q+0iqQ9Ap64TwAAAABJRU5ErkJggg==", attachment.getContents());
    }
}
