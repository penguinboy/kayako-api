package org.penguin.kayako;

import org.penguin.kayako.domain.TicketPriority;
import org.penguin.kayako.domain.TicketPriorityCollection;
import org.penguin.kayako.domain.TicketType;
import org.penguin.kayako.domain.TicketTypeCollection;
import org.penguin.kayako.exception.ApiRequestException;
import org.penguin.kayako.exception.ApiResponseException;

import java.util.List;

/**
 * Wrapper for any API calls specific to ticket priorities.
 *
 * @author fatroom
 */
public class TicketPriorityConnector extends AbstractConnector {

    protected TicketPriorityConnector(final KayakoClient client) {
        super(client);
    }

    /**
     * Retrieve a list of all ticket priorities and their properties in the help desk.
     *
     * @return An collection of priorities known in system
     * @throws ApiResponseException A wrapped exception of anything that went wrong when handling the response from kayako
     * @throws ApiRequestException  A wrapped exception of anything that went wrong sending the request to kayako
     */
    public List<TicketPriority> list() throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .get()
                .as(TicketPriorityCollection.class)
                .getPriorities();
    }

    /**
     * Retrieve a specific ticket priority identified by id.
     *
     * @param typeId The unique numeric identifier of the ticket priority
     * @return An collection of with requested priorities
     * @throws ApiResponseException A wrapped exception of anything that went wrong when handling the response from kayako
     * @throws ApiRequestException  A wrapped exception of anything that went wrong sending the request to kayako
     */
    public List<TicketPriority> forId(final int typeId) throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .withPathRaw(String.valueOf(typeId))
                .get()
                .as(TicketPriorityCollection.class)
                .getPriorities();
    }

    @Override
    protected ApiRequest getApiRequest() {
        ApiRequest request = super.getApiRequest();
        return request
                .withPath("Tickets")
                .withPath("TicketPriority");
    }
}
