package org.penguin.kayako;

import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.common.collect.Lists;

public class ApiResponse {
    private final String responseContent;
    
    public ApiResponse(String responseContent) {
        this.responseContent = responseContent;
        Lists.newArrayList();
    }
    
    @SuppressWarnings("unchecked")
    public <E> E as(Class<E> returnType) throws ApiResponseException {
        
        try {
            Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(returnType);
            return (E) unmarshaller.unmarshal(new StringReader(responseContent));
        } catch (JAXBException e) {
            throw new ApiResponseException("An exception occurred unmarshalling return content", e);
        }
    }
    
    public static class ApiResponseException extends Exception {
        private static final long serialVersionUID = 6860338648955294068L;
        
        public ApiResponseException(String message, Throwable e) {
            super(message, e);
        }
    }
}
