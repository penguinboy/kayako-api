package org.penguin.kayako.domain;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * An object used to unmarshall api responses from kayako. Should never be returned to a user.
 *
 * @author fatroom
 */
@XmlRootElement(name = "notes")
public class NoteCollection {
    @XmlElement(name = "note")
    private List<Note> notes = Lists.newArrayList();

    public List<Note> getNotes() {
        return notes;
    }
}
