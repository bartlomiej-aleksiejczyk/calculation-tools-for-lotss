package pl.edu.uwm.student.aleksiejczyk.bartlomiej;
import org.xml.sax.SAXException;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.xml.XMLHeaderSchema;
import tech.tablesaw.api.Table;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static final String CONFIG_TAG_NAME = "combine_files_configuration";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        Map<String, String> config = XMLHeaderSchema.readHeaders(CONFIG_TAG_NAME);

        String firstCsvName = config.get("first_input_name");
        String secondCsvName = config.get("second_input_name");
        String outputCsvName = config.get("output_name");

        String firstKey = config.get("first_input_key");
        String secondKey = config.get("second_input_key");

        Table firstTable = Table.read().csv(firstCsvName);
        Table secondTable = Table.read().csv(secondCsvName);

        /*for (String columnName : firstTable.columnNames()) {
            if (secondTable.columnNames().contains(columnName) && !columnName.equals(key2)) {
                Column<?> duplicateColumn = secondTable.column(columnName);
                duplicateColumn.setName(columnName + "_2"); // Appends "_2" to the duplicate column name
            }
        }*/
        if (firstTable.columnNames().contains("C0")) {
            firstTable.removeColumns("C0");
        }

        // Check if 'C0' column exists in secondTable, and remove it if it does
        if (secondTable.columnNames().contains("C0")) {
            secondTable.removeColumns("C0");
        }

        // Perform an inner join on the tables using the keys
        Table combinedCsvTable = firstTable.joinOn(firstKey).inner(secondTable, secondKey);

        System.out.println("BAJO JAJO");
        // Write the result to a new CSV file using Apache Commons CSV
        combinedCsvTable.write().csv(outputCsvName);
    }
}