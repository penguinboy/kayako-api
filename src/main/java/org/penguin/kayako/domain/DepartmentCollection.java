package org.penguin.kayako.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An object used to unmarshall api responses from kayako. Should never be returned to a user.
 * 
 * @author raynerw
 * 
 */
@XmlRootElement(name = "departments")
public class DepartmentCollection {
    @XmlElement(name = "department")
    private List<Department> departments;
    
    public List<Department> getDepartments() {
        return departments;
    }
}
