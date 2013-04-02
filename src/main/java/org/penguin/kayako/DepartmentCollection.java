package org.penguin.kayako;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "departments")
public class DepartmentCollection {
    @XmlElement(name = "department")
    private List<Department> departments;
    
    protected List<Department> getDepartments() {
        return departments;
    }
    
    protected void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
