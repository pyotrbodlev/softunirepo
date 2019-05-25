package softuni.cardealer.parsers;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    <E> String toXml(E entity, Class<E> clazz) throws JAXBException;
}
