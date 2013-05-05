package org.penguin.kayako.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;

import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.penguin.kayako.UnmarshallerFactory;
import org.penguin.kayako.domain.Department;
import org.penguin.kayako.domain.KayakoAccessibility;
import org.penguin.kayako.domain.KayakoApp;
import org.penguin.kayako.util.ContentLoader;

public class DepartmentTests {
    
    @Test
    public void testXmlParsingDepartment() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_department.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Department.class);
        
        // act
        Department department = (Department) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        assertNotNull(department);
        assertEquals(2, department.getId());
        assertEquals("Sales", department.getTitle());
        assertEquals(KayakoAccessibility.PUBLIC, department.getType());
        assertEquals("tickets", department.getModule());
        assertEquals(KayakoApp.TICKETS, department.getApp());
        assertEquals(30, department.getDisplayOrder());
        assertEquals(0, department.getParentDepartmentId());
        assertEquals(false, department.getUserVisibilityCustom());
    }
    
    @Test
    public void testXmlParsingDepartmentWithNoUserGroups() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_department2.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Department.class);
        
        // act
        Department department = (Department) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        assertEquals(0, department.getUserGroups().size());
    }
    
    @Test
    public void testXmlParsingDepartmentWithUserVisibilityCustom() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_department2.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Department.class);
        
        // act
        Department department = (Department) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        assertEquals(true, department.getUserVisibilityCustom());
    }
    
    @Test
    public void testXmlParsingDepartmentWithManyUserGroups() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_department3.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Department.class);
        
        // act
        Department department = (Department) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        assertEquals(3, department.getUserGroups().size());
        assertEquals(Integer.valueOf(1234), department.getUserGroups().get(1));
    }
}
