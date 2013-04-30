package org.penguin.kayako;


import org.penguin.kayako.domain.TicketStatus;
import org.penguin.kayako.domain.TicketStatusCollection;
import org.penguin.kayako.exception.ApiRequestException;
import org.penguin.kayako.exception.ApiResponseException;

import java.util.List;

/**
 * Wrapper for any API calls specific to ticket statuses.
 *
 * @author fatroom
 */
public class TicketStatusConnector extends AbstractConnector {

    protected TicketStatusConnector(final KayakoClient client) {
        super(client);
    }

    /**
     * Retrieve a list of all ticket statues in the help desk.
     *
     * @return An collection of {@link TicketStatus}
     * @throws ApiResponseException A wrapped exception of anything that went wrong when handling the response from kayako
     * @throws ApiRequestException  A wrapped exception of anything that went wrong sending the request to kayako
     */
    public List<TicketStatus> list() throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .get()
                .as(TicketStatusCollection.class)
                .getStatuses();
    }

    /**
     * Retrieve the ticket status identified by id.
     *
     * @param statusId ticket status id
     * @return An collection of {@link TicketStatus}
     * @throws ApiResponseException A wrapped exception of anything that went wrong when handling the response from kayako
     * @throws ApiRequestException  A wrapped exception of anything that went wrong sending the request to kayako
     */
    public List<TicketStatus> forId(final int statusId) throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .withPathRaw(String.valueOf(statusId))
                .get()
                .as(TicketStatusCollection.class)
                .getStatuses();
    }

    @Override
    protected ApiRequest getApiRequest() {
        ApiRequest request = super.getApiRequest();
        return request
                .withPath("Tickets")
                .withPath("TicketStatus");
    }
}
