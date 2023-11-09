package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.xml.sax.SAXException;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.xml.XMLHeaderSchema;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Map;

public class CSVData {

    private final static String SCHEMA_TAG_NAME = "csv_input_schema";
    private final static String CONFIG_TAG_NAME = "csv_file_configuration";


    public static void convertCSVHeaders() throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> config = XMLHeaderSchema.readHeaders(CONFIG_TAG_NAME);


        Reader input = new FileReader(config.get("input_filename"));
        CSVFormat.Builder builder =  CSVFormat.Builder.create();
        CSVFormat csvFormat = builder.setHeader().setSkipHeaderRecord(true).build();
        CSVParser parser = new CSVParser(input, csvFormat);

        Map<String, Integer> headers = parser.getHeaderMap();
        String[] headersList = headers.keySet().toArray(new String[0]);

        Map<String, String> newHeaders = XMLHeaderSchema.readHeaders(SCHEMA_TAG_NAME);
        newHeaders.forEach((key, value) -> System.out.println(key + " : " + value));

        CSVFormat csvFormatHeaders =  CSVFormat.Builder.create().setHeader(HeaderUtils.replaceKeys(headersList, newHeaders)).build();
        try(            Reader copiedInput = new FileReader(config.get("input_filename"));
                        Writer output = new FileWriter(config.get("output_filename"));
            CSVPrinter csvPrinter = new CSVPrinter(output, csvFormatHeaders)){
            for (CSVRecord record : csvFormat.parse(input)) {
                System.out.println(record);
                csvPrinter.printRecord(record);
            }
        }
    }
}
