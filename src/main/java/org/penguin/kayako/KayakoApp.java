package org.penguin.kayako;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public enum KayakoApp {
    TICKETS, LIVECHAT;

    public static class KayakoAppAdapter extends XmlAdapter<String, KayakoApp> {
        @Override
        public String marshal(KayakoApp v) throws Exception {
            return v.name().toLowerCase();
        }

        @Override
        public KayakoApp unmarshal(String v) throws Exception {
            return KayakoApp.valueOf(v.toUpperCase());
        }
    }
}
