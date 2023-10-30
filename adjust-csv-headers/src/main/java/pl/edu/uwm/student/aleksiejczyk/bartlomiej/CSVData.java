package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CSVData {
    private final static String INPUT_PATH = "unconverted_input.csv";
    private final static String OUTPUT_PATH = "converted_output.csv";

    public static void convertCSV() throws IOException {
        Reader input = new FileReader(INPUT_PATH);
        CSVFormat.Builder builder =  CSVFormat.Builder.create();
        CSVFormat csvFormat = builder.setHeader().setSkipHeaderRecord(true).build();
        CSVParser parser = new CSVParser(input, csvFormat);

        Map<String, Integer> headers = parser.getHeaderMap();
        String[] headersList = headers.keySet().toArray(new String[0]);

        Map<String, String> map = new HashMap<>();
        map.put("index", "dddd");
        map.put("Two", "ddd");
        map.put("Three", "ddd");
        map.put("Four", "4");
        System.out.println();

        CSVFormat csvFormatHeaders =  CSVFormat.Builder.create().setHeader(HeaderUtils.replaceKeys(headersList, map)).build();
        try(            Reader copiedInput = new FileReader(INPUT_PATH);
                        Writer output = new FileWriter(OUTPUT_PATH);
            CSVPrinter csvPrinter = new CSVPrinter(output, csvFormatHeaders)){
            for (CSVRecord record : csvFormat.parse(input)) {
                csvPrinter.printRecord(record);
            }
        }
    }
}
