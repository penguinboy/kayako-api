package org.penguin.kayako;

import org.penguin.kayako.domain.TicketCustomFieldGroup;
import org.penguin.kayako.domain.TicketCustomFieldGroupCollection;
import org.penguin.kayako.exception.ApiRequestException;
import org.penguin.kayako.exception.ApiResponseException;

import java.util.List;

/**
 *  Wrapper for any API calls specific to ticket custom fields.
 *
 * @author fatroom
 */
public class TicketCustomFieldConnector extends AbstractConnector {

    protected TicketCustomFieldConnector(final KayakoClient client) {
        super(client);
    }

    /**
     * Retrieve a list of a ticket's custom fields.
     *
     * @param ticketId The unique numeric identifier of the ticket
     * @return collection of custom field groups assigned to specified ticket
     * @throws ApiResponseException
     *            A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *            A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<TicketCustomFieldGroup> forTicket(final int ticketId) throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .withPathRaw(String.valueOf(ticketId))
                .get()
                .as(TicketCustomFieldGroupCollection.class)
                .getGroups();
    }

    /**
     * Update the custom field values for a ticket.
     * This method is only used to create a new record (so if you POST a custom field set to a ticket which already has a custom field set, it will overwrite the existing set).
     *
     * @param request request with custom field values
     * @return collection of custom field groups assigned to specified ticket
     * @throws ApiResponseException
     *            A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *            A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<TicketCustomFieldGroup> update(final TicketCustomFieldRequest request) throws ApiRequestException, ApiResponseException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    protected ApiRequest getApiRequest() {
        return super.getApiRequest()
                .withPath("Tickets")
                .withPath("TicketCustomField");
    }

    public static class TicketCustomFieldRequest extends AbstractRequest {

        private TicketCustomFieldRequest() {

        }

        public static TicketCustomFieldRequest where() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        protected void validate() throws ApiRequestException {
            throw new ApiRequestException(new UnsupportedOperationException("Not implemented"));
        }
    }
}
