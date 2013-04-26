package org.penguin.kayako;

import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.union;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Set;

import org.penguin.kayako.ApiResponse.ApiResponseException;
import org.penguin.kayako.domain.BasicTicket;
import org.penguin.kayako.domain.BasicTicketCollection;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.penguin.kayako.domain.Ticket;
import org.penguin.kayako.domain.TicketCollection;
import sun.plugin.dom.exception.InvalidStateException;

/**
 * Wrapper for any API calls specific to tickets
 * 
 * @author raynerw
 * @author fatroom
 * 
 */
public class TicketConnector {
    private final KayakoClient client;
    
    protected TicketConnector(KayakoClient client) {
        this.client = client;
    }
    
    /**
     * Fetches open tickets that belong to a given department. Tickets are not ordered.
     * 
     * @param departmentId
     *            The identifier of the department your are fetching tickets for.
     * @return An ordered list of tickets that match your given criteria
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<BasicTicket> forDepartment(int departmentId) throws ApiResponseException, ApiRequestException {
        return forDepartment(departmentId, DepartmentTicketRequest.where());
    }
    
    /**
     * Fetches open tickets that belong to a given department and filter. Tickets are not ordered.
     * 
     * @param departmentId
     *            The identifier of the department your are fetching tickets for.
     * @param filter
     *            A filter object specifying limiting the scope of the request.
     * @return An ordered list of tickets assigned to the department you specified.
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<BasicTicket> forDepartment(int departmentId, DepartmentTicketRequest filter) throws ApiResponseException, ApiRequestException {
        return forDepartments(Lists.newArrayList(departmentId), filter);
    }
    
    /**
     * Fetches open tickets that belong to given departments. Tickets are not ordered.
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
        return forDepartments(departmentIds, DepartmentTicketRequest.where());
    }
    
    /**
     * Fetches open tickets that belong to given departments and match the filter you supply. Tickets are not ordered.
     * 
     * @param departmentIds
     *            A collection of department identifiers.
     * @param filter
     *            A filter object specifying limiting the scope of the request.
     * @return An ordered list of tickets assigned to the departments you specified.
     * @throws ApiResponseException
     *            A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *            A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<BasicTicket> forDepartments(Iterable<Integer> departmentIds, DepartmentTicketRequest filter) throws ApiResponseException, ApiRequestException {
        return new ApiRequest(client)
                .withPath("Tickets")
                .withPath("Ticket")
                .withPath("ListAll")
                .withPathRaw(Joiner.on(',').skipNulls().join(departmentIds))
                .withPathRaw(Joiner.on(',').join(filter.getTicketStatusIds()))
                .withPathRaw(Joiner.on(',').join(filter.getOwnerStaffIds()))
                .withPathRaw(Joiner.on(',').join(filter.getUserIds()))
                .get().as(BasicTicketCollection.class)
                .getTickets();
    }

    /**
     * Fetches ticket identified by ticket id or by ticket mask ID.
     *
     * @param ticketId The unique numeric identifier of the ticket or the ticket mask ID (e.g. ABC-123-4567).
     * @return An ordered list of tickets with specified id.
     * @throws ApiResponseException
     *            A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *            A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<Ticket> forId(String ticketId) throws ApiResponseException, ApiRequestException {
        return new ApiRequest(client)
                .withPath("Tickets")
                .withPath("Ticket")
                .withPathRaw(ticketId)
                .get().as(TicketCollection.class)
                .getTickets();
    }

    /**
     * Create a new ticket.
     *
     * @param request ticket creation request. {@link TicketCreateRequest}
     * @return collection with an created ticket.
     * @throws ApiResponseException
     *            A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *            A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<Ticket> createTicket(TicketCreateRequest request) throws ApiRequestException, ApiResponseException {
        request.validate();
        ApiRequest apiRequest = new ApiRequest(client)
                .withPath("Tickets")
                .withPath("Ticket")
                .withPostParam("subject", request.getSubject())
                .withPostParam("fullname", request.getFullname())
                .withPostParam("email", request.getEmail())
                .withPostParam("contents", request.getContents())
                .withPostParam("departmentid", request.getDepartmentId())
                .withPostParam("ticketstatusid", request.getTicketStatusId())
                .withPostParam("ticketpriorityid", request.getTicketPriorityId())
                .withPostParam("tickettypeid", request.getTicketTypeId());
        if (request.getAutoUserId() != null && request.getAutoUserId()) {
            apiRequest = apiRequest.withPostParam("autouserid", 1);
        }
        if (request.getUserId() != null) {
            apiRequest = apiRequest.withPostParam("userid", request.getUserId());

        }
        if (request.getStaffId() != null) {
            apiRequest = apiRequest.withPostParam("staffid", request.getStaffId());
        }
        if (request.getOwnerStaffId() != null) {
            apiRequest = apiRequest.withPostParam("ownerstaffid", request.getOwnerStaffId());
        }
        if (request.getType() != null) {
            apiRequest = apiRequest.withPostParam("type", request.getType());
        }
        if (request.getTemplateGroup() != null) {
            apiRequest = apiRequest.withPostParam("templategroup", request.getTemplateGroup());

        }
        if (request.getIgnoreAutoresponder() != null && request.getIgnoreAutoresponder()) {
            apiRequest = apiRequest.withPostParam("ignoreautoresponder", 1);
        }
        return apiRequest.post().as(TicketCollection.class).getTickets();
    }


    /**
     * Delete the ticket identified by ticket id.
     *
     * @param ticketId The unique numeric identifier of the ticket.
     * @throws ApiRequestException
     *            A wrapped exception of anything that went wrong sending the request to kayako.
     * @throws ApiResponseException
     *            A wrapped exception of anything that went wrong when handling the response from kayako.
     */
    public void delete(String ticketId) throws ApiRequestException, ApiResponseException {
        new ApiRequest(client)
                .withPath("Tickets")
                .withPath("Ticket")
                .withPathRaw(ticketId)
                .delete();
    }

    public static class DepartmentTicketRequest {
        private final Set<Integer> ticketStatusIds;
        private final Set<Integer> ownerStaffIds;
        private final Set<Integer> userIds;
        
        private DepartmentTicketRequest(Set<Integer> ticketStatusIds, Set<Integer> ownerStaffIds, Set<Integer> userIds) {
            this.ticketStatusIds = ticketStatusIds;
            this.ownerStaffIds = ownerStaffIds;
            this.userIds = userIds;
        }
        
        public static DepartmentTicketRequest where() {
            return new DepartmentTicketRequest(null, null, null);
        }
        
        public DepartmentTicketRequest ticketStatusId(Integer... ticketStatusIds) {
            return new DepartmentTicketRequest(addToSet(this.ticketStatusIds, ticketStatusIds), ownerStaffIds, userIds);
        }
        
        public DepartmentTicketRequest ownerStaffId(Integer... ownerStaffIds) {
            return new DepartmentTicketRequest(ticketStatusIds, addToSet(this.ownerStaffIds, ownerStaffIds), userIds);
        }
        
        public DepartmentTicketRequest userId(Integer... userIds) {
            return new DepartmentTicketRequest(ticketStatusIds, ownerStaffIds, addToSet(this.userIds, userIds));
        }
        
        private static final Set<Integer> EMPTY_SET = ImmutableSet.<Integer> builder().add(-1).build();
        
        private Set<Integer> getTicketStatusIds() {
            if (ticketStatusIds == null || ticketStatusIds.size() == 0) {
                return EMPTY_SET;
            }
            return ticketStatusIds;
        }
        
        private Set<Integer> getOwnerStaffIds() {
            if (ownerStaffIds == null || ownerStaffIds.size() == 0) {
                return EMPTY_SET;
            }
            return ownerStaffIds;
        }
        
        private Set<Integer> getUserIds() {
            if (userIds == null || userIds.size() == 0) {
                return EMPTY_SET;
            }
            return userIds;
        }
        
        private static Set<Integer> addToSet(Set<Integer> set, Integer... values) {
            if (set == null) {
                return newHashSet(asList(values));
            }
            return union(set, newHashSet(asList(values)));
        }
    }

    public static class TicketCreateRequest {
        public enum TICKETTYPE {
            DEFAULT("default"),
            PHONE("phone");

            private String representation;

            private TICKETTYPE(String representation) {
                this.representation = representation;
            }

            @Override
            public String toString() {
                return representation;
            }
        }
        private String subject;
        private String fullname;
        private String email;
        private String contents;
        private Integer departmentId;
        private Integer ticketStatusId;
        private Integer ticketPriorityId;
        private Integer ticketTypeId;

        private Boolean autoUserId;
        private Integer userId;
        private Integer staffId;

        private Integer ownerStaffId;
        private TICKETTYPE type;
        private String  templateGroup;
        private Boolean ignoreAutoresponder;

        private TicketCreateRequest() {
        }

        public static TicketCreateRequest where() {
            return new TicketCreateRequest();
        }

        /**
         * The Ticket Subject.
         *
         * @param subject subject
         * @return instance of request
         */
        public TicketCreateRequest subject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Full name of ticket creator.
         *
         * @param fullname full name
         * @return instance of request
         */
        public TicketCreateRequest fullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        /**
         * Email address of ticket creator.
         *
         * @param email address
         * @return instance of request
         */
        public TicketCreateRequest email(String email) {
            this.email = email;
            return this;
        }

        /**
         * The contents of the first ticket post.
         *
         * @param contents contents of the first ticket post
         * @return instance of request
         */
        public TicketCreateRequest contents(String contents) {
            this.contents = contents;
            return this;
        }

        /**
         * The department id.
         *
         * @param id department id
         * @return instance of request
         */
        public TicketCreateRequest departmentId(int id) {
            this.departmentId = id;
            return this;
        }

        /**
         * The ticket status id.
         *
         * @param id status id
         * @return instance of request
         */
        public TicketCreateRequest ticketStatusId(int id) {
            this.ticketStatusId = id;
            return this;
        }

        /**
         * The ticket priority id.
         *
         * @param id priority id
         * @return instance of request
         */
        public TicketCreateRequest ticketPriorityId(int id) {
            this.ticketPriorityId = id;
            return this;
        }

        /**
         * The ticket type id.
         *
         * @param id type id
         * @return instance of request
         */
        public TicketCreateRequest ticketTypeId(int id) {
            this.ticketTypeId = id;
            return this;
        }

        /**
         * If dispatched then User ID is looked up based on the email address.
         * If none is found, the system ends up creating a new user based on the information supplied.
         *
         * @return instance of request
         * @throws ApiRequestException in case ticket creator user id or ticket creator staff id explicitly set in this request instance.
         */
        public TicketCreateRequest autoCreateUser() throws ApiRequestException {
            if (userId != null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration"));
            }
            if (staffId != null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration"));
            }
            this.autoUserId = true;
            return this;
        }

        /**
         * The user id, if the ticket is to be created as a user.
         *
         * @param id user id
         * @return instance of request
         * @throws ApiRequestException in case automatic user id lookup or ticket creator staff id explicitly set in this request instance.
         */
        public TicketCreateRequest userId(int id) throws ApiRequestException {
            if (autoUserId != null && autoUserId) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration"));
            }
            if (staffId != null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration"));
            }
            this.userId = id;
            return this;
        }

        /**
         * The staff id, if the ticket is to be created as a staff.
         *
         * @param id staff id
         * @return request instance
         * @throws ApiRequestException in case automatic user id lookup or ticket creator user id explicitly set in this request instance.
         */
        public TicketCreateRequest staffId(int id) throws ApiRequestException {
            if (autoUserId != null && autoUserId) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration"));
            }
            if (userId != null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration"));
            }
            this.staffId = id;
            return this;
        }

        /**
         * The owner staff id, if you want to set an Owner for this ticket.
         * Optional parameter.
         *
         * @param id staff id
         * @return request instance
         */
        public TicketCreateRequest ownerStaffId(int id) {
            this.ownerStaffId = id;
            return this;
        }

        /**
         * The ticket type. Check for available types {@link TICKETTYPE}.
         * Optional parameter.
         *
         * @param type type
         * @return request instance
         */
        public TicketCreateRequest type(TICKETTYPE type) {
            this.type = type;
            return this;
        }

        /**
         * The custom template group identifier (ID or Name) for the Ticket.
         * Optional parameter.
         *
         * @param templateGroup identifier
         * @return request instance
         */
        public TicketCreateRequest templateGroup(String templateGroup) {
            this.templateGroup = templateGroup;
            return this;
        }

        /**
         * Option to disable autoresponder email.
         *
         * @return request instance
         */
        public TicketCreateRequest ignoreAutoresponder() {
            this.ignoreAutoresponder = true;
            return this;
        }

        private void validate() {
            if (subject == null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket subject is required"));
            }
            if (fullname == null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket creator full name is required"));
            }

            if (email == null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket creator email is required"));
            }

            if (contents == null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket contents is required"));
            }

            if (departmentId == null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket department id is required"));
            }

            if (ticketStatusId == null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket status id is required"));
            }

            if (ticketPriorityId == null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket priority id is required"));
            }

            if (ticketTypeId == null) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket type id is required"));
            }
            if ((autoUserId == null || !autoUserId) && (userId == null) && (staffId == null) ) {
                throw new ApiRequestException(new InvalidStateException("Invalid request configuration. Ticket creator id should be specified or allow auto lookup"));
            }
        }

        private String getSubject() {
            return subject;
        }

        private String getFullname() {
            return fullname;
        }

        private String getEmail() {
            return email;
        }

        private String getContents() {
            return contents;
        }

        private Integer getDepartmentId() {
            return departmentId;
        }

        private Integer getTicketStatusId() {
            return ticketStatusId;
        }

        private Integer getTicketPriorityId() {
            return ticketPriorityId;
        }

        private Integer getTicketTypeId() {
            return ticketTypeId;
        }

        private Boolean getAutoUserId() {
            return autoUserId;
        }

        private Integer getUserId() {
            return userId;
        }

        private Integer getStaffId() {
            return staffId;
        }

        private Integer getOwnerStaffId() {
            return ownerStaffId;
        }

        private String getType() {
            if (type == null) {
                return null;
            }
            return type.toString();
        }

        private String getTemplateGroup() {
            return templateGroup;
        }

        private Boolean getIgnoreAutoresponder() {
            return ignoreAutoresponder;
        }
    }
}
