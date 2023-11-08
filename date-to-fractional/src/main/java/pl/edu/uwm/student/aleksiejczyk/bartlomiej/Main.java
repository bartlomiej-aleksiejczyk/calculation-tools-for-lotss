package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import org.xml.sax.SAXException;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.xml.XMLHeaderSchema;
import tech.tablesaw.api.DateTimeColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static final String CONFIG_TAG_NAME = "fractional_date_configuration";


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        Map<String, String> config = XMLHeaderSchema.readHeaders(CONFIG_TAG_NAME);
        String inputName = config.get("input_name");
        String outputName = config.get("output_name");
        String dateColumnName = config.get("date_column_name");
        String fractionalDateColumnName = config.get("date_fractional_column_name");

        Table table = Table.read().csv(inputName);

        DateTimeColumn dateColumn = table.dateTimeColumn(dateColumnName);

        DoubleColumn fractionalYearColumn = DoubleColumn.create(fractionalDateColumnName,
                dateColumn.asList().stream()
                        .map(DateToFractional::convertDateToFractionalYear)
                        .collect(Collectors.toList()));

        table.addColumns(fractionalYearColumn);

        table.write().csv(outputName);
    }
}