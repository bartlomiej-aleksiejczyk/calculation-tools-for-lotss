package pl.edu.uwm.student.aleksiejczyk.bartlomiej.savecsv;

import org.xml.sax.SAXException;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.ResultPojo;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.xml.XMLHeaderSchema;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ResultStructure {
    public static final String SEARCH_NN_CONFIG_NAME = "nn_config_file";

    public static void saveObjectToCsv(ResultPojo resultPojo) throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> config = XMLHeaderSchema.readHeaders(SEARCH_NN_CONFIG_NAME);

        String outputName = config.get("output_name");

        String[][] resultColumns = resultPojo.data;
        String[] headers = resultPojo.headers;
        int lenHeight = resultColumns[0].length;
        int lenWidth = resultColumns.length;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputName));

        for(int h = 0; h<lenWidth; h++){
            if (h != 0) {
                bufferedWriter.write(",");
            }
            bufferedWriter.write((headers[h]));
            System.out.println(headers[h]);
        }
        bufferedWriter.newLine();

        for(int i = 0; i<lenHeight; i++){
            for(int j = 0; j<lenWidth; j++){
                if (j!=0){
                    bufferedWriter.write(",");
                }
                try{
                    bufferedWriter.write((resultColumns[j][i]));
                } catch (NullPointerException ignored){
                }
            }
            if (i!=(lenHeight-1)){
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }
}
