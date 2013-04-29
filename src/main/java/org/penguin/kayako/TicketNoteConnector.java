package org.penguin.kayako;

import org.penguin.kayako.domain.Note;
import org.penguin.kayako.domain.NoteCollection;
import org.penguin.kayako.exception.ApiRequestException;
import org.penguin.kayako.exception.ApiResponseException;

import java.util.List;

/**
 * Wrapper for any API calls specific to ticket notes
 *
 * @author fatroom
 */
public class TicketNoteConnector extends AbstractConnector {

    protected TicketNoteConnector(final KayakoClient client) {
        super(client);
    }

    /**
     * Retrieve a list of a ticket's notes.
     *
     * @param ticketId The unique numeric identifier of the ticket
     * @return An ordered list of notes assigned to the ticket you specified
     * @throws ApiResponseException A wrapped exception of anything that went wrong when handling the response from kayako
     * @throws ApiRequestException  A wrapped exception of anything that went wrong sending the request to kayako
     */
    public List<Note> forTicket(final int ticketId) throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .withPath("ListAll")
                .withPathRaw(String.valueOf(ticketId))
                .get().as(NoteCollection.class)
                .getNotes();
    }

    /**
     * Retrieve the note identified by id that belongs to a specified ticket.
     *
     * @param ticketId unique numeric identifier of the ticket
     * @param noteId   unique numeric identifier of the ticket note
     * @return An collection with specified note
     * @throws ApiRequestException  A wrapped exception of anything that went wrong sending the request to kayako
     * @throws ApiResponseException A wrapped exception of anything that went wrong when handling the response from kayako
     */
    public List<Note> forId(final int ticketId, final int noteId) throws ApiRequestException, ApiResponseException {
        return getApiRequest()
                .withPathRaw(String.valueOf(ticketId))
                .withPathRaw(String.valueOf(noteId))
                .get().as(NoteCollection.class)
                .getNotes();
    }

    /**
     * Add a new note to a ticket.
     *
     * @param request note creation request
     * @return An collection with created note
     * @throws ApiRequestException  A wrapped exception of anything that went wrong sending the request to kayako
     * @throws ApiResponseException A wrapped exception of anything that went wrong when handling the response from kayako
     */
    public List<Note> createNote(final NoteCreateRequest request) throws ApiRequestException, ApiResponseException {
        request.validate();
        ApiRequest apiRequest = getApiRequest()
                .withPostParam("ticketid", request.getTicketId())
                .withPostParam("contents", request.getContents());
        if (request.getStaffId() != null) {
            apiRequest = apiRequest.withPostParam("staffid", request.getStaffId());
        }
        if (request.getFullname() != null) {
            apiRequest = apiRequest.withPostParam("fullname", request.getFullname());
        }
        if (request.getForStaffId() != null) {
            apiRequest = apiRequest.withPostParam("forstaffid", request.getForStaffId());
        }
        if (request.getNoteColor() != null) {
            apiRequest = apiRequest.withPostParam("notecolor", request.getNoteColor());
        }
        return apiRequest.post()
                .as(NoteCollection.class)
                .getNotes();
    }

    /**
     * Delete the note identified by id linked to the specified ticket.
     *
     * @param ticketId unique numeric identifier of the ticket
     * @param noteId   unique numeric identifier of the ticket note
     * @throws ApiRequestException  A wrapped exception of anything that went wrong sending the request to kayako
     * @throws ApiResponseException A wrapped exception of anything that went wrong when handling the response from kayako
     */
    public void delete(final int ticketId, final int noteId) throws ApiRequestException, ApiResponseException {
        getApiRequest()
                .withPathRaw(String.valueOf(ticketId))
                .withPathRaw(String.valueOf(noteId))
                .delete();
    }

    @Override
    protected ApiRequest getApiRequest() {
        ApiRequest request = super.getApiRequest();
        return request
                .withPath("Tickets")
                .withPath("TicketNote");
    }

    public static class NoteCreateRequest extends AbstractRequest {
        private Integer ticketId;
        private String contents;
        private Integer staffId;
        private String fullname;
        private Integer forStaffId;
        private Integer noteColor;

        private NoteCreateRequest() {
        }

        private NoteCreateRequest(final NoteCreateRequest request) {
            this.ticketId = request.getTicketId();
            this.contents = request.getContents();
            this.staffId = request.getStaffId();
            this.fullname = request.getFullname();
            this.forStaffId = request.getForStaffId();
            this.noteColor = request.getNoteColor();
        }

        public static NoteCreateRequest where() {
            return new NoteCreateRequest();
        }

        /**
         * The unique numeric identifier of the ticket.
         *
         * @param id identifier
         * @return request instance
         */
        public NoteCreateRequest ticketId(final int id) {
            NoteCreateRequest request = new NoteCreateRequest(this);
            request.ticketId = id;
            return request;
        }

        /**
         * The ticket note contents.
         *
         * @param contents contents
         * @return request instance
         * @throws ApiRequestException in case argument is null
         */
        public NoteCreateRequest contents(final String contents) throws ApiRequestException {
            checkNotNull(contents);
            NoteCreateRequest request = new NoteCreateRequest(this);
            request.contents = contents;
            return request;
        }

        /**
         * The Staff ID, if the ticket is to be created as a staff.
         *
         * @param id identifier
         * @return request instance
         * @throws ApiRequestException in case note creator fullname already set in this request
         */
        public NoteCreateRequest staffId(final int id) throws ApiRequestException {
            if (fullname != null) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration"));
            }
            NoteCreateRequest request = new NoteCreateRequest(this);
            request.staffId = staffId;
            return request;
        }

        /**
         * The Fullname, if the ticket is to be created without providing a staff user. Example: System messages, Alerts etc.
         *
         * @param fullname note creator
         * @return request instance
         * @throws ApiRequestException in case note creator id already set in this request
         */
        public NoteCreateRequest fullname(final String fullname) throws ApiRequestException {
            if (staffId != null) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration"));
            }
            NoteCreateRequest request = new NoteCreateRequest(this);
            request.fullname = fullname;
            return request;
        }

        /**
         * The Staff ID, this value can be provided if you wish to restrict the note visibility to a specific staff
         * Optional parameter.
         *
         * @param id identifier
         * @return request instance
         */
        public NoteCreateRequest forStaffId(final int id) {
            NoteCreateRequest request = new NoteCreateRequest(this);
            request.forStaffId = id;
            return request;
        }

        /**
         * The Note color.
         * Optional parameter.
         *
         * @param color color id
         * @return request instance
         */
        public NoteCreateRequest noteColor(final int color) {
            NoteCreateRequest request = new NoteCreateRequest();
            request.noteColor = color;
            return request;
        }

        @Override
        protected void validate() throws ApiRequestException {
            if (ticketId == null) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration. Ticket id is required"));
            }
            if (contents == null) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration. Note contents is required"));
            }
            if ((staffId == null) && (fullname == null)) {
                throw new ApiRequestException(new IllegalStateException("Invalid request configuration. Staff id or note creator full name is required"));
            }
        }

        private Integer getTicketId() {
            return ticketId;
        }

        private String getContents() {
            return contents;
        }

        private Integer getStaffId() {
            return staffId;
        }

        private String getFullname() {
            return fullname;
        }

        private Integer getForStaffId() {
            return forStaffId;
        }

        private Integer getNoteColor() {
            return noteColor;
        }
    }

}
