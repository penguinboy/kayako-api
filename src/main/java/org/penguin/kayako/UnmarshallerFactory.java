package org.penguin.kayako;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UnmarshallerFactory {

    public static Unmarshaller getMapper() throws JAXBException {
        return JAXBContext.newInstance(Department.class).createUnmarshaller();
    }
}
