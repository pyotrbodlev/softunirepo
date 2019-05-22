package softuni.productshop.parsers;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    <E> String toXml(E entity, Class<?> clazz) throws JAXBException;
}
