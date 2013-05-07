package org.penguin.kayako;


import org.junit.Test;
import org.penguin.kayako.domain.KayakoApp;
import org.penguin.kayako.domain.KayakoApp.KayakoAppAdapter;

import static org.junit.Assert.assertEquals;

public class KayakoAppTests {

    @Test
    public void testMarshalTickets() throws Exception {
        // arrange
        KayakoApp app = KayakoApp.TICKETS;
        KayakoAppAdapter adapter = new KayakoAppAdapter();

        // act
        String v = adapter.marshal(app);

        // assert
        assertEquals("tickets", v);
    }

    @Test
    public void testMarshalLivechat() throws Exception {
        // arrange
        KayakoApp app = KayakoApp.LIVECHAT;
        KayakoAppAdapter adapter = new KayakoAppAdapter();

        // act
        String v = adapter.marshal(app);

        // assert
        assertEquals("livechat", v);
    }

    @Test
    public void testUnmarshalTickets() throws Exception {
        // arrange
        String v = "tickets";
        KayakoAppAdapter adapter = new KayakoAppAdapter();

        // act
        KayakoApp app = adapter.unmarshal(v);

        // assert
        assertEquals(KayakoApp.TICKETS, app);
    }

    @Test
    public void testUnmarshalLivechat() throws Exception {
        // arrange
        String v = "livechat";
        KayakoAppAdapter adapter = new KayakoAppAdapter();

        // act
        KayakoApp app = adapter.unmarshal(v);

        // assert
        assertEquals(KayakoApp.LIVECHAT, app);
    }
}
