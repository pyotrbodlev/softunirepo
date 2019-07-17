package alararestaurant.util.parsers;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    @SuppressWarnings(value = "unchecked")
    <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException, JAXBException;
}
