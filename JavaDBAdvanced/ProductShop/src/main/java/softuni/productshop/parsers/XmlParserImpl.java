package softuni.productshop.parsers;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
public class XmlParserImpl implements XmlParser {

    @Override
    public <E> String toXml(E entity, Class<?> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(entity, writer);

        return writer.toString();
    }
}
