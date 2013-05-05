package org.penguin.kayako;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UnmarshallerFactory {
    public static Unmarshaller getMapper(Class<?> clazz) throws JAXBException {
        return JAXBContext.newInstance(clazz).createUnmarshaller();
    }
}
