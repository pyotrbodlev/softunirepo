package mostwanted.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlParserImpl implements XmlParser {

    @Override
    @SuppressWarnings(value = "unchecked")
    public <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File file = new File(filePath);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return (O) unmarshaller.unmarshal(reader);
    }
}
