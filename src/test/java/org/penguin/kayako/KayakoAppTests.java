package org.penguin.kayako;

import junit.framework.Assert;

import org.junit.Test;
import org.penguin.kayako.KayakoApp.KayakoAppAdapter;

public class KayakoAppTests {

    @Test
    public void testMarshalTickets() throws Exception {
        // arrange
        KayakoApp app = KayakoApp.TICKETS;
        KayakoAppAdapter adapter = new KayakoAppAdapter();

        // act
        String v = adapter.marshal(app);

        // assert
        Assert.assertEquals("tickets", v);
    }

    @Test
    public void testMarshalLivechat() throws Exception {
        // arrange
        KayakoApp app = KayakoApp.LIVECHAT;
        KayakoAppAdapter adapter = new KayakoAppAdapter();

        // act
        String v = adapter.marshal(app);

        // assert
        Assert.assertEquals("livechat", v);
    }

    @Test
    public void testUnmarshalTickets() throws Exception {
        // arrange
        String v = "tickets";
        KayakoAppAdapter adapter = new KayakoAppAdapter();

        // act
        KayakoApp app = adapter.unmarshal(v);

        // assert
        Assert.assertEquals(KayakoApp.TICKETS, app);
    }

    @Test
    public void testUnmarshalLivechat() throws Exception {
        // arrange
        String v = "livechat";
        KayakoAppAdapter adapter = new KayakoAppAdapter();

        // act
        KayakoApp app = adapter.unmarshal(v);

        // assert
        Assert.assertEquals(KayakoApp.LIVECHAT, app);
    }
}
