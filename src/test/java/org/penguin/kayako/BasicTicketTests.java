package org.penguin.kayako;

import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import junit.framework.Assert;
import org.junit.Test;
import org.penguin.kayako.domain.BasicTicket;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class BasicTicketTests {
    
    @Test
    public void testTicketUnmarshalling() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_basicticket.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicket.class);
        
        // act
        BasicTicket ticket = (BasicTicket) unmarshaller.unmarshal(new StringReader(ticketXml));
        
        // assert
        Assert.assertNotNull(ticket);
    }
    
    @Test
    public void testTicketContentCorrect() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_basicticket.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicket.class);
        
        // act
        BasicTicket ticket = (BasicTicket) unmarshaller.unmarshal(new StringReader(ticketXml));
        
        // assert
        assertEquals(2, ticket.getId());
        assertEquals("OLJ-171-16930", ticket.getDisplayId());
        assertEquals(2, ticket.getDepartmentId());
        assertEquals(3, ticket.getStatusId());
        assertEquals(4, ticket.getPriorityId());
        assertEquals(5, ticket.getTypeId());
        assertEquals(6, ticket.getUserId());
        assertEquals("Test", ticket.getUserOrganizationName());
        assertEquals(7, ticket.getUserOrganizationId());
        assertEquals(8, ticket.getOwnerStaffId());
        assertEquals(null, ticket.getOwnerStaffName());
        assertEquals("Varun Shoor", ticket.getUserFullName());
        assertEquals("varun.shoor@kayako.com", ticket.getUserEmail());
        assertEquals("Varun Shoor", ticket.getLastReplierFullName());
        assertEquals("This is just a test", ticket.getSubject());
        assertEquals(new Date((long) 1297840147 * 1000), ticket.getCreationDate());
        assertEquals(new Date((long) 1297840147 * 1000), ticket.getLastActivityDate());
        assertEquals(9, ticket.getLastStaffReplyId());
        assertEquals(new Date((long) 1297840147 * 1000), ticket.getLastUserReplyDate());
        assertEquals(10, ticket.getSlaPlanId());
        assertEquals(new Date((long) 1297883347 * 1000), ticket.getNextReplyDueDate());
        assertEquals(new Date((long) 1298012947 * 1000), ticket.getResolutionDueDate());
        assertEquals(11, ticket.getReplies());
        assertEquals("127.0.0.1", ticket.getIpAddress());
        assertEquals(12, ticket.getCreatorId());
        assertEquals(13, ticket.getCreationMode());
        assertEquals(14, ticket.getCreationType());
        assertEquals(true, ticket.isEscalated());
        assertEquals(15, ticket.getEscalationRuleId());
        assertEquals(16, ticket.getTemplateGroupId());
        assertEquals("default", ticket.getTemplateGroupName());
        assertEquals(Lists.newArrayList("test", "tag", "client"), ticket.getTags());
        
    }
    
    @Test
    public void testTicketUnmarshallGetsWatcher() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_basicticket.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicket.class);
        
        // act
        BasicTicket ticket = (BasicTicket) unmarshaller.unmarshal(new StringReader(ticketXml));
        
        // assert
        assertEquals(1, ticket.getWatchers().size());
        assertEquals(1, ticket.getWatchers().get(0).getStaffId());
        assertEquals("Varun Shoor", ticket.getWatchers().get(0).getStaffName());
    }
    
    @Test
    public void testTicketUnmarshallGetsWorkflow() throws Exception {
        // arrange
        String ticketXml = CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("/example_xml_basicticket.xml")));
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicket.class);
        
        // act
        BasicTicket ticket = (BasicTicket) unmarshaller.unmarshal(new StringReader(ticketXml));
        
        // assert
        assertEquals(1, ticket.getWorkflows().size());
        assertEquals(1, ticket.getWorkflows().get(0).getId());
        assertEquals("Close Ticket", ticket.getWorkflows().get(0).getTitle());
    }
    
    @Test
    public void testTicketUnmarshallGetsNotes() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_basicticket.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicket.class);
        
        // act
        BasicTicket ticket = (BasicTicket) unmarshaller.unmarshal(new StringReader(ticketXml));
        
        // assert
        assertEquals(4, ticket.getNotes().size());
    }
    
    @Test
    public void testTicketUnmarshallGetsWatchers() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_basicticket2.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicket.class);
        
        // act
        BasicTicket ticket = (BasicTicket) unmarshaller.unmarshal(new StringReader(ticketXml));
        
        // assert
        assertEquals(2, ticket.getWatchers().size());
    }
    
    @Test
    public void testTicketUnmarshallGetsWorkflows() throws Exception {
        // arrange
        String ticketXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_basicticket2.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(BasicTicket.class);
        
        // act
        BasicTicket ticket = (BasicTicket) unmarshaller.unmarshal(new StringReader(ticketXml));
        
        // assert
        assertEquals(2, ticket.getWorkflows().size());
    }
}
