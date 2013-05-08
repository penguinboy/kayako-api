package org.penguin.kayako;

import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.penguin.kayako.exception.ApiResponseException;

import com.google.common.base.Strings;

public class ApiResponse {
    private final String responseContent;
    
    protected ApiResponse(String responseContent) {
        this.responseContent = responseContent;
    }
    
    @SuppressWarnings("unchecked")
    protected <E> E as(Class<E> returnType) throws ApiResponseException {
        
        try {
            Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(returnType);

            return (E) unmarshaller.unmarshal(new StringReader(stripNonValidXMLCharacters(Strings.nullToEmpty(responseContent))));
        } catch (JAXBException e) {
            throw new ApiResponseException("An exception occurred unmarshalling return content", e);
        }
    }

    /**
     * This method ensures that the output String has only
     * valid XML unicode characters as specified by the
     * XML 1.0 standard. For reference, please see
     * <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
     * standard</a>. This method will return an empty
     * String if the input is null or empty.
     *
     * @param in The String whose non-valid characters we want to remove.
     * @return The in String, stripped of non-valid characters.
     */
    public String stripNonValidXMLCharacters(String in) {
        StringBuilder out = new StringBuilder(); // Used to hold the output.
        char current; // Used to reference the current character.

        if (in == null || ("".equals(in))) return ""; // vacancy test.
        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught here; it should not happen.
            if ((current == 0x9) ||
                    (current == 0xA) ||
                    (current == 0xD) ||
                    ((current >= 0x20) && (current <= 0xD7FF)) ||
                    ((current >= 0xE000) && (current <= 0xFFFD)) ||
                    ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }

}
