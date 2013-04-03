package org.penguin.kayako;

public class KayakoClient {
    private final String apiKey;
    private final String apiSecret;
    private final UriBuilder baseURI;
    
    public KayakoClient(String apiKey, String apiSecret, String baseURI) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.baseURI = new UriBuilder(baseURI).path("api").path("index.php");
    }
    
    public DepartmentConnector departments() {
        return new DepartmentConnector(this);
    }
    
    public TicketConnector tickets() {
        return new TicketConnector(this);
    }
    
    protected String getApiKey() {
        return apiKey;
    }
    
    protected String getApiSecret() {
        return apiSecret;
    }
    
    protected UriBuilder getBaseURI() {
        return baseURI;
    }
    
}
