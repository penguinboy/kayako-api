package org.penguin.kayako;

import java.util.List;

import org.penguin.kayako.ApiRequest.ApiRequestException;
import org.penguin.kayako.ApiResponse.ApiResponseException;
import org.penguin.kayako.domain.BasicTicket;
import org.penguin.kayako.domain.BasicTicketCollection;

import com.google.common.base.Joiner;

/**
 * Wrapper for any API calls specific to tickets
 * 
 * @author raynerw
 * 
 */
public class TicketConnector {
    private final KayakoClient client;
    
    protected TicketConnector(KayakoClient client) {
        this.client = client;
    }
    
    /**
     * Fetches open tickets that belong to a given department. Tickets are ordered by date of last activity, with the
     * oldest first and the newest last. The list can contain a maximum of 1000 results.
     * 
     * @param departmentId
     *            The identifier of the department your are fetching tickets for.
     * @return An ordered list of tickets assigned to the department you specified.
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako.
     */
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
    
    /**
     * Fetches open tickets that belong to any of the departments you've specified. Tickets are ordered by date of last
     * activity, with the oldest first and the newest last. The list can contain a maximum of 1000 results.
     * 
     * @param departmentIds
     *            A collection of department identifiers.
     * @return An ordered list of tickets assigned to the departments you specified.
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako.
     */
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
