package org.penguin.kayako;

import java.util.List;

import org.penguin.kayako.ApiRequest.ApiRequestException;
import org.penguin.kayako.ApiResponse.ApiResponseException;

public class TicketConnector {
    private final KayakoClient client;
    
    protected TicketConnector(KayakoClient client) {
        this.client = client;
    }
    
    public List<BasicTicket> getAll() throws ApiResponseException, ApiRequestException {
        
        return new ApiRequest(client)
                .withPath("Tickets")
                .withPath("Ticket")
                .withPath("ListAll")
                .withPath("-1")
                .withPath("-1")
                .withPath("-1")
                .withPath("-1")
                .get().as(BasicTicketCollection.class)
                .getTickets();
    }
}
