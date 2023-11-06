package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        CSVData.convertCSVHeaders();
    }
}