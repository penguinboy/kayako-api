package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.KayakoAccessibility;
import org.penguin.kayako.domain.TicketStatus;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.*;

public class TicketStatusTests {
    @Test
    public void testTicketStatusUnmarshall() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketstatus.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketStatus.class);

        // act
        TicketStatus status = (TicketStatus) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertNotNull(status);
    }

    @Test
    public void testTicketCustomFieldContentCorrect() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketstatus.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketStatus.class);

        // act
        TicketStatus status = (TicketStatus) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, status.getId());
        assertEquals("Open", status.getTitle());
        assertEquals(2, status.getDisplayOrder());
        assertEquals(3, status.getDepartmentId());
        assertEquals("icon.png", status.getDisplayIcon());
        assertEquals(KayakoAccessibility.PRIVATE, status.getType());
        assertFalse(status.isDisplayInMainList());
        assertFalse(status.isMarkAsResolved());
        assertTrue(status.isDisplayCount());
        assertEquals("#8BB467", status.getStatusColor());
        assertEquals("#8BB468", status.getStatusBackgroundColor());
        assertFalse(status.isResetDueTime());
        assertFalse(status.isTriggerSurvey());
        assertTrue(status.isStaffVisibilityCustom());
        assertEquals(Integer.valueOf(4), status.getStaffGroupId());
    }

    @Test
    public void testTicketCustomFieldContentCorrectMissingStaffGroupId() throws Exception {
        // arrange
        String noteXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_ticketstatus2.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(TicketStatus.class);

        // act
        TicketStatus status = (TicketStatus) unmarshaller.unmarshal(new StringReader(noteXml));

        // assert
        assertEquals(1, status.getId());
        assertEquals("Open", status.getTitle());
        assertEquals(2, status.getDisplayOrder());
        assertEquals(3, status.getDepartmentId());
        assertEquals("icon.png", status.getDisplayIcon());
        assertEquals(KayakoAccessibility.PUBLIC, status.getType());
        assertTrue(status.isDisplayInMainList());
        assertTrue(status.isMarkAsResolved());
        assertFalse(status.isDisplayCount());
        assertEquals("#8BB467", status.getStatusColor());
        assertEquals("#8BB468", status.getStatusBackgroundColor());
        assertTrue(status.isResetDueTime());
        assertTrue(status.isTriggerSurvey());
        assertFalse(status.isStaffVisibilityCustom());
        assertNull(status.getStaffGroupId());
    }
}
