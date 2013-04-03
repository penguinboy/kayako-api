package org.penguin.kayako;

import junit.framework.Assert;

import org.junit.Test;
import org.penguin.kayako.domain.KayakoAccessibility;
import org.penguin.kayako.domain.KayakoAccessibility.KayakoAccessibilityAdapter;

public class KayakoAccessibilityTests {

    @Test
    public void testMarshalPrivate() throws Exception {
        // arrange
        KayakoAccessibility app = KayakoAccessibility.PRIVATE;
        KayakoAccessibilityAdapter adapter = new KayakoAccessibilityAdapter();

        // act
        String v = adapter.marshal(app);

        // assert
        Assert.assertEquals("private", v);
    }

    @Test
    public void testMarshalPublic() throws Exception {
        // arrange
        KayakoAccessibility app = KayakoAccessibility.PUBLIC;
        KayakoAccessibilityAdapter adapter = new KayakoAccessibilityAdapter();

        // act
        String v = adapter.marshal(app);

        // assert
        Assert.assertEquals("public", v);
    }

    @Test
    public void testUnmarshalPrivate() throws Exception {
        // arrange
        String v = "private";
        KayakoAccessibilityAdapter adapter = new KayakoAccessibilityAdapter();

        // act
        KayakoAccessibility accessibility = adapter.unmarshal(v);

        // assert
        Assert.assertEquals(KayakoAccessibility.PRIVATE, accessibility);
    }

    @Test
    public void testUnmarshalLivechat() throws Exception {
        // arrange
        String v = "public";
        KayakoAccessibilityAdapter adapter = new KayakoAccessibilityAdapter();

        // act
        KayakoAccessibility accessibility = adapter.unmarshal(v);

        // assert
        Assert.assertEquals(KayakoAccessibility.PUBLIC, accessibility);
    }
}
