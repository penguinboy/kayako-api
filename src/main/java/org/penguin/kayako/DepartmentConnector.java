package org.penguin.kayako;

import java.util.List;

import org.penguin.kayako.exception.ApiResponseException;
import org.penguin.kayako.domain.Department;
import org.penguin.kayako.domain.DepartmentCollection;
import org.penguin.kayako.exception.ApiRequestException;

/**
 * Wrapper for any API calls specific to departments
 * 
 * @author raynerw
 * 
 */
public class DepartmentConnector extends AbstractConnector {

    protected DepartmentConnector(final KayakoClient client) {
        super(client);
    }

    /**
     * Get the details of a department
     * 
     * @param id
     *            The identifier of the department you want to inspect.
     * @return The {@link Department} that represents the department you requested.
     * @throws ApiResponseException
     *             A wrapped exception of anything that went wrong when handling the response from kayako.
     * @throws org.penguin.kayako.exception.ApiRequestException
     *             A wrapped exception of anything that went wrong sending the request to kayako.
     */
    public Department get(int id) throws ApiResponseException, ApiRequestException {
        return getApiRequest()
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
        return getApiRequest()
                .get().as(DepartmentCollection.class).getDepartments();
    }

    @Override
    protected ApiRequest getApiRequest() {
        ApiRequest request = super.getApiRequest();
        return request
                .withPath("Base")
                .withPath("Department");
    }
}
