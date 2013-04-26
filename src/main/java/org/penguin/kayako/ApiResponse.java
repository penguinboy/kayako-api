package org.penguin.kayako;

import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.common.collect.Lists;
import org.penguin.kayako.exception.ApiResponseException;

public class ApiResponse {
    private final String responseContent;
    
    protected ApiResponse(String responseContent) {
        this.responseContent = responseContent;
        Lists.newArrayList();
    }
    
    @SuppressWarnings("unchecked")
    protected <E> E as(Class<E> returnType) throws ApiResponseException {
        
        try {
            Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(returnType);
            return (E) unmarshaller.unmarshal(new StringReader(responseContent));
        } catch (JAXBException e) {
            throw new ApiResponseException("An exception occurred unmarshalling return content", e);
        }
    }

}
