package org.penguin.kayako.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.penguin.kayako.domain.KayakoAccessibility.KayakoAccessibilityAdapter;
import org.penguin.kayako.domain.KayakoApp.KayakoAppAdapter;

import com.google.common.collect.Lists;

@XmlRootElement(name = "department")
public class Department {
    @XmlElement
    private int id;
    @XmlElement
    private String title;
    @XmlElement
    @XmlJavaTypeAdapter(KayakoAccessibility.KayakoAccessibilityAdapter.class)
    private KayakoAccessibility type;
    @XmlElement
    private String module;
    @XmlElement
    @XmlJavaTypeAdapter(KayakoApp.KayakoAppAdapter.class)
    private KayakoApp app;
    @XmlElement(name = "displayorder")
    private int displayOrder;
    @XmlElement(name = "parentdepartmentid")
    private int parentDepartmentId;
    @XmlElement(name = "uservisibilitycustom")
    private boolean userVisibilityCustom;

    @XmlElementWrapper(name = "usergroups")
    @XmlElement(name = "id")
    private List<Integer> userGroups = Lists.newArrayList();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public KayakoAccessibility getType() {
        return type;
    }

    public String getModule() {
        return module;
    }

    public KayakoApp getApp() {
        return app;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public int getParentDepartmentId() {
        return parentDepartmentId;
    }

    public boolean getUserVisibilityCustom() {
        return userVisibilityCustom;
    }

    public List<Integer> getUserGroups() {
        return userGroups;
    }
}