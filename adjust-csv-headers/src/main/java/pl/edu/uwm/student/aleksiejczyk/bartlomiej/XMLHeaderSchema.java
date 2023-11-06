package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class XMLHeaderSchema {
    public static final String XML_SCHEMA_FILENAME = "configuration.xml";
    public static final String HEADER_DATA_TAG_NAME = "configuration.xml";
    public static Map<String, String> readHeaders() throws ParserConfigurationException, IOException, SAXException {

        Document configuration = DocumentBuilderFactory.newDefaultInstance()
                .newDocumentBuilder()
                .parse(new File(XML_SCHEMA_FILENAME));

        configuration.getDocumentElement().normalize();
        Node configNode = configuration.getElementsByTagName("csv_input_schema").item(0);

        return IntStream.range(0, configNode.getChildNodes().getLength())
                .mapToObj(i -> configNode.getChildNodes().item(i))
                .filter(node -> node instanceof Element)
                .map(node -> (Element) node)
                .collect(Collectors.toMap(Element::getTextContent, Element::getTagName));
    };

}
