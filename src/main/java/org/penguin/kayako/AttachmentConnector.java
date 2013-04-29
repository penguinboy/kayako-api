package org.penguin.kayako;

import org.penguin.kayako.domain.Attachment;
import org.penguin.kayako.domain.AttachmentCollection;
import org.penguin.kayako.exception.ApiRequestException;
import org.penguin.kayako.exception.ApiResponseException;

import java.util.List;

/**
 * Wrapper for any API calls specific to ticket attachments.
 *
 * @author fatroom
 */
public class AttachmentConnector extends AbstractConnector {

    protected AttachmentConnector(final KayakoClient client) {
        super(client);
    }

    /**
     * Retrieve a list of a ticket's attachments.
     *
     * @param ticketId ticket id
     * @return collection with {@link Attachment}
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako
     */
    public List<Attachment> forTicket(final int ticketId) throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .withPath("ListAll")
                .withPathRaw(String.valueOf(ticketId))
                .get()
                .as(AttachmentCollection.class)
                .getAttachments();
    }


    /**
     * Retrieve the attachment identified by id that belongs to a specified ticket.
     *
     * @param ticketId ticket id
     * @param attachmentId attachment id
     * @return collection with {@link Attachment}. The attachment contents are base64 encoded
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako
     */
    public List<Attachment> forId(final int ticketId, final int attachmentId) throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .withPathRaw(String.valueOf(ticketId))
                .withPathRaw(String.valueOf(attachmentId))
                .get()
                .as(AttachmentCollection.class)
                .getAttachments();
    }

    public List<Attachment> createAttachment(final AttachmentCreateRequest request) throws ApiRequestException, ApiResponseException {
        request.validate();
        return getApiRequest()
                .withPostParam("ticketid", request.getTicketId())
                .withPostParam("ticketpostid", request.getPostId())
                .withPostParam("filename", request.getFilename())
                .withPostParam("contents", request.getContents())
                .post()
                .as(AttachmentCollection.class)
                .getAttachments();
    }

    /**
     * Delete an attachment identified by id which belongs to a specified ticket.
     *
     * @param ticketId The unique numeric identifier of the ticket
     * @param attachmentId The unique numeric identifier of the attachment
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako
     */
    public void delete(final int ticketId, final int attachmentId) throws ApiRequestException, ApiResponseException {
        getApiRequest()
                .withPathRaw(String.valueOf(ticketId))
                .withPathRaw(String.valueOf(attachmentId))
                .delete();
    }

    @Override
    protected ApiRequest getApiRequest() {
        ApiRequest request = super.getApiRequest();
        return request
                .withPath("Tickets")
                .withPath("TicketAttachment");
    }

    public static class AttachmentCreateRequest extends AbstractRequest {
        private Integer ticketId;
        private Integer postId;
        private String filename;
        private String contents;

        private AttachmentCreateRequest() {

        }

        public AttachmentCreateRequest(final AttachmentCreateRequest request) {
            this.ticketId = request.getTicketId();
            this.postId = request.getPostId();
            this.filename = request.getFilename();
            this.contents = request.getContents();
        }

        public static AttachmentCreateRequest where() {
            return new AttachmentCreateRequest();
        }

        /**
         * The unique numeric identifier of the ticket.
         *
         * @param id identifier
         * @return request instance
         */
        public AttachmentCreateRequest ticketId(final int id) {
            AttachmentCreateRequest request = new AttachmentCreateRequest(this);
            request.ticketId = id;
            return request;
        }

        /**
         * The unique numeric identifier of the ticket post.
         *
         * @param id identifier
         * @return request instance
         */
        public AttachmentCreateRequest postId(final int id) {
            AttachmentCreateRequest request = new AttachmentCreateRequest(this);
            request.postId = id;
            return request;
        }

        /**
         * The file name for the attachment.
         *
         * @param filename filename
         * @return request instance
         * @throws ApiRequestException if parameter is null
         */
        public AttachmentCreateRequest filename(final String filename) throws ApiRequestException {
            checkNotNull(filename);
            AttachmentCreateRequest request = new AttachmentCreateRequest(this);
            request.filename = filename;
            return request;
        }

        /**
         * The BASE64 encoded attachment contents.
         *
         * @param contents filename
         * @return request instance
         * @throws ApiRequestException if parameter is null
         */
        public AttachmentCreateRequest contents(final String contents) throws ApiRequestException {
            checkNotNull(contents);
            AttachmentCreateRequest request = new AttachmentCreateRequest(this);
            request.contents = contents;
            return request;
        }

        @Override
        protected void validate() throws ApiRequestException {
            if (ticketId == null) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration. Ticket id must be specified"));
            }
            if (postId == null) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration. Post id must be specified"));
            }
            if (filename == null) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration. Filename must be specified"));
            }
            if (contents == null) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration. Contents must be specified"));
            }
        }

        private Integer getTicketId() {
            return ticketId;
        }

        private Integer getPostId() {
            return postId;
        }

        private String getFilename() {
            return filename;
        }

        private String getContents() {
            return contents;
        }
    }
}
