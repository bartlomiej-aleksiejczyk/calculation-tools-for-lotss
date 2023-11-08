package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import org.xml.sax.SAXException;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.xml.XMLHeaderSchema;
import tech.tablesaw.api.Table;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static final String CONFIG_TAG_NAME = "file_configuration";
    public static final String EXCLUDE_TAG_NAME = "columns_to_exclude";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        Map<String, String> config = XMLHeaderSchema.readHeaders(CONFIG_TAG_NAME);
        Map<String, String> columnsToExclude = XMLHeaderSchema.readHeaders(EXCLUDE_TAG_NAME);

        String inputName = config.get("input_name");
        String outputName = config.get("output_name");

        Table table = Table.read().csv(inputName);

        for (String value : columnsToExclude.values()) {
            if (table.columnNames().contains(value)) {
                table.removeColumns(value);
            }
        }

        table.write().csv(outputName);
    }
}
