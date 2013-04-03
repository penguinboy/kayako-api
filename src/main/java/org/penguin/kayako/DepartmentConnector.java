package org.penguin.kayako;

import java.util.List;

import org.penguin.kayako.ApiRequest.ApiRequestException;
import org.penguin.kayako.ApiResponse.ApiResponseException;
import org.penguin.kayako.domain.Department;
import org.penguin.kayako.domain.DepartmentCollection;

public class DepartmentConnector {
    private final KayakoClient client;
    
    protected DepartmentConnector(KayakoClient client) {
        this.client = client;
    }
    
    public Department get(int id) throws ApiResponseException, ApiRequestException {
        return new ApiRequest(client)
                .withPath("Base").withPath("Department")
                .withPath(String.valueOf(id))
                .get().as(DepartmentCollection.class).getDepartments().get(0);
    }
    
    public List<Department> list() throws ApiResponseException, ApiRequestException {
        return new ApiRequest(client)
                .withPath("Base").withPath("Department")
                .get().as(DepartmentCollection.class).getDepartments();
    }
}
