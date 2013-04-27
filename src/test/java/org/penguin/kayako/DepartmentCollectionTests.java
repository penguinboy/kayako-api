package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.DepartmentCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertNotNull;

public class DepartmentCollectionTests {
    @Test
    public void testXmlParsingDepartments() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_departments.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(DepartmentCollection.class);
        
        // act
        DepartmentCollection departments = (DepartmentCollection) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        assertNotNull(departments);
    }
}
