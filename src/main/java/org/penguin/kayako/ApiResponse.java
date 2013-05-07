package org.penguin.kayako;

import com.google.common.collect.Lists;
import org.penguin.kayako.exception.ApiResponseException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class ApiResponse {
    private final String responseContent;
    
    protected ApiResponse(String responseContent) {
        this.responseContent = responseContent;
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
