package org.penguin.kayako;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.penguin.kayako.domain.TicketCustomFieldGroup;
import org.penguin.kayako.domain.TicketCustomFieldGroupCollection;
import org.penguin.kayako.exception.ApiRequestException;
import org.penguin.kayako.exception.ApiResponseException;

import java.util.List;
import java.util.Map;

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
     * @param ticketId The unique numeric identifier of the ticket
     * @param request request with custom field values
     * @return collection of custom field groups assigned to specified ticket
     * @throws ApiResponseException
     *            A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *            A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<TicketCustomFieldGroup> update(final int ticketId, final TicketCustomFieldRequest request) throws ApiRequestException, ApiResponseException {
        request.validate();
        ApiRequest apiRequest = getApiRequest()
                .withPathRaw(String.valueOf(ticketId));
        for (String fieldName : request.fields.keySet()) {
            for (String value : request.fields.get(fieldName)) {
                apiRequest = apiRequest.withPostParam(fieldName, value);
            }
        }
        return apiRequest
                .post()
                .as(TicketCustomFieldGroupCollection.class)
                .getGroups();
    }

    @Override
    protected ApiRequest getApiRequest() {
        return super.getApiRequest()
                .withPath("Tickets")
                .withPath("TicketCustomField");
    }

    public static class TicketCustomFieldRequest extends AbstractRequest {

        private Map<String, List<String>> fields = Maps.newHashMap();

        private TicketCustomFieldRequest() {
        }

        private TicketCustomFieldRequest(TicketCustomFieldRequest request) {
            fields.putAll(request.fields);
        }

        public static TicketCustomFieldRequest where() {
            return new TicketCustomFieldRequest();
        }

        public TicketCustomFieldRequest field(final String name, final String value) {
            TicketCustomFieldRequest request = new TicketCustomFieldRequest(this);
            List<String> values = request.fields.get(name);
            if (values == null) {
                values = Lists.newArrayList();
                request.fields.put(name, values);
            }
            values.add(value);
            return request;
        }

        @Override
        protected void validate() throws ApiRequestException {
        }
    }
}
