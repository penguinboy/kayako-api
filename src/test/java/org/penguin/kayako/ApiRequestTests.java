package org.penguin.kayako;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.Mockito;

public class ApiRequestTests {
    
    @Test
    public void testSignatureGeneratesCorrectly() throws Exception {
        // arrange
        String apiSecret = "MDA4YzBiMWMtN2RiOC1hZTY0LTMxODgtMzE1MThjNmU5NDJlYTM1ZTgwY2YtYjA1ZS1jMzQ0LWY5MjktMzQ1ZjliMDA4ODIx";
        KayakoClient client = mock(KayakoClient.class);
        Mockito.when(client.getApiSecret()).thenReturn(apiSecret);
        
        // act
        ApiRequest request = new ApiRequest(client).setSalt("3777329113");
        
        // assert
        assertEquals("gFhgQ1Gydk+DLsn6BnHO/eqs1KxPwqmlj2bRWfjFFYs=", request.getSignature());
    }
}
