package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class SchemaValidator {
    static final String SCHEMA_FILE_PATH = "./schema.xsd";
    static final String CONFIG_FILE_NAME = "configuration.xml";

    public static Boolean validateXMLAgainstXSDSchema() {
        try {
            File schemaFile = new File(SCHEMA_FILE_PATH);
            File configurationFile = new File(new File("").getAbsoluteFile().getParentFile(), CONFIG_FILE_NAME);

            Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(configurationFile));

            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}
