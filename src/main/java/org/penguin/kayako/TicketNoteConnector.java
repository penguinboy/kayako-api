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
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako
     *
     */
    public List<Note> forTicket(final int ticketId) throws ApiResponseException, ApiRequestException {
        return new ApiRequest(getClient())
                .withPath("Tickets")
                .withPath("TicketNote")
                .withPath("ListAll")
                .withPathRaw(String.valueOf(ticketId))
                .get().as(NoteCollection.class)
                .getNotes();
    }

    /**
     * Retrieve the note identified by id that belongs to a specified ticket.
     *
     * @param ticketId unique numeric identifier of the ticket
     * @param noteId unique numeric identifier of the ticket note
     * @return An collection with specified note
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako
     */
    public List<Note> forId(final int ticketId, final int noteId) {
        return new ApiRequest(getClient())
                .withPath("Tickets")
                .withPath("TicketNote")
                .withPathRaw(String.valueOf(ticketId))
                .withPathRaw(String.valueOf(noteId))
                .get().as(NoteCollection.class)
                .getNotes();
    }

}
