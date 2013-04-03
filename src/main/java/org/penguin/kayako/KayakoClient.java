package org.penguin.kayako;

/**
 * Kayako client interface. This is the starting point for any developers looking to use this kayako api wrapper.
 * 
 * @author raynerw
 * 
 */
public class KayakoClient {
    private final String apiKey;
    private final String apiSecret;
    private final UriBuilder baseURI;
    
    /**
     * Initialize a new instance of the kayako client with your kayako address and security details.
     * 
     * @param apiKey
     *            The api key listed in your Kayako admin interface.
     * @param apiSecret
     *            The api secret listed in your Kayako admin interface.
     * @param baseURI
     *            The base address for your kayako installation. This should not include a scheme or a port. This
     *            wrapper assumes your api module is installed in the default top-level directory of your kayako
     *            installation (/api).
     * 
     *            Example address: my.kayakoinstallation.com
     */
    public KayakoClient(String apiKey, String apiSecret, String baseURI) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.baseURI = new UriBuilder(baseURI).path("api").path("index.php");
    }
    
    /**
     * Interact with kayako departments
     * 
     * @return An instance of {@link DepartmentConnector} that allows you to fetch departments.
     */
    public DepartmentConnector departments() {
        return new DepartmentConnector(this);
    }
    
    /**
     * Interact with kayako tickets
     * 
     * @return An instance of {@link TicketConnector} that allows you to fetch Tickets.
     */
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
