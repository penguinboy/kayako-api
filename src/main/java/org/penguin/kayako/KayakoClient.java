package org.penguin.kayako;

public class KayakoClient {
    private final String apiKey;
    private final String apiSecret;
    private final String baseUrl;

    public KayakoClient(String apiKey, String apiSecret, String baseUrl) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.baseUrl = baseUrl;
    }

}
