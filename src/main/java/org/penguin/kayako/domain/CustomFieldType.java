package org.penguin.kayako.domain;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 *
 * @author fatroom
 */
public enum CustomFieldType {
    TEXT(1),
    TEXTAREA(2),
    PASSWORD(3),
    CHECKBOX(4),
    RADIO(5),
    SELECT(6),
    MULTISELECT(7),
    CUSTOM(8),
    LINKED_FIELDS(9),
    DATE(10),
    FILE(11),
    UNKNOWN(-1);

    private int id;

    private CustomFieldType(final int id) {
        this.id = id;
    }

    public static class CustomFieldTypeAdapter extends XmlAdapter<Integer, CustomFieldType> {
        @Override
        public CustomFieldType unmarshal(final Integer id) throws Exception {
            for (CustomFieldType type: CustomFieldType.values()) {
                if (id == type.id) {
                    return type;
                }
            }
            return UNKNOWN;
        }

        @Override
        public Integer marshal(final CustomFieldType type) throws Exception {
            return type.id;
        }
    }
}
