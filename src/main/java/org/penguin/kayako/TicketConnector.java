package org.penguin.kayako;

import java.util.List;

import org.penguin.kayako.ApiRequest.ApiRequestException;
import org.penguin.kayako.ApiResponse.ApiResponseException;
import org.penguin.kayako.domain.BasicTicket;
import org.penguin.kayako.domain.BasicTicketCollection;

import com.google.common.base.Joiner;

public class TicketConnector {
    private final KayakoClient client;
    
    protected TicketConnector(KayakoClient client) {
        this.client = client;
    }
    
    public List<BasicTicket> forDepartment(int departmentId) throws ApiResponseException, ApiRequestException {
        return new ApiRequest(client)
                .withPath("Tickets")
                .withPath("Ticket")
                .withPath("ListAll")
                .withPath(String.valueOf(departmentId))
                .withPath("-1")
                .withPath("-1")
                .withPath("-1")
                .get().as(BasicTicketCollection.class)
                .getTickets();
    }
    
    public List<BasicTicket> forDepartments(Iterable<Integer> departmentIds) throws ApiResponseException, ApiRequestException {
        return new ApiRequest(client)
                .withPath("Tickets")
                .withPath("Ticket")
                .withPath("ListAll")
                .withPath(Joiner.on(',').skipNulls().join(departmentIds))
                .withPath("-1")
                .withPath("-1")
                .withPath("-1")
                .get().as(BasicTicketCollection.class)
                .getTickets();
    }
    
}
