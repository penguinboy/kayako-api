package org.penguin.kayako;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public enum KayakoAccessibility {
    PRIVATE, PUBLIC;

    public static class KayakoAccessibilityAdapter extends XmlAdapter<String, KayakoAccessibility> {
        @Override
        public String marshal(KayakoAccessibility v) throws Exception {
            return v.name().toLowerCase();
        }

        @Override
        public KayakoAccessibility unmarshal(String v) throws Exception {
            return KayakoAccessibility.valueOf(v.toUpperCase());
        }
    }
}
