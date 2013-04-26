package org.penguin.kayako;

import java.util.List;

import org.penguin.kayako.ApiResponse.ApiResponseException;
import org.penguin.kayako.domain.Department;
import org.penguin.kayako.domain.DepartmentCollection;

/**
 * Wrapper for any API calls specific to departments
 * 
 * @author raynerw
 * 
 */
public class DepartmentConnector {
    private final KayakoClient client;
    
    protected DepartmentConnector(KayakoClient client) {
        this.client = client;
    }
    
    /**
     * Get the details of a department
     * 
     * @param id
     *            The identifier of the department you want to inspect.
     * @return The {@link Department} that represents the department you requested.
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public Department get(int id) throws ApiResponseException, ApiRequestException {
        return new ApiRequest(client)
                .withPath("Base").withPath("Department")
                .withPath(String.valueOf(id))
                .get().as(DepartmentCollection.class).getDepartments().get(0);
    }
    
    /**
     * Get a list of all the departments.
     * 
     * @return A list of {@link Department} instances representing the departments you requested.
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public List<Department> list() throws ApiResponseException, ApiRequestException {
        return new ApiRequest(client)
                .withPath("Base").withPath("Department")
                .get().as(DepartmentCollection.class).getDepartments();
    }
}
