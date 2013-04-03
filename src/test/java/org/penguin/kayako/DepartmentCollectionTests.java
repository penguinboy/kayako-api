package org.penguin.kayako;

import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;
import org.penguin.kayako.domain.DepartmentCollection;

import com.google.common.io.CharStreams;

public class DepartmentCollectionTests {
    @Test
    public void testXmlParsingDepartments() throws Exception {
        // arrange
        String departmentsXml = CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("example_xml_departments.xml")));
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(DepartmentCollection.class);
        
        // act
        DepartmentCollection departments = (DepartmentCollection) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        Assert.assertNotNull(departments);
    }
}
