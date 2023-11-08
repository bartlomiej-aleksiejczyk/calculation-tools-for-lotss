package pl.edu.uwm.student.aleksiejczyk.bartlomiej.opencsv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.xml.sax.SAXException;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.GaiaDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.LotssDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.xml.XMLHeaderSchema;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

public class OpenCsv {
    public static final String SEARCH_NN_CONFIG_NAME = "nn_config_file";
    public static final String SEARCH_NN_LOTSS_TAG = "lots_table_scheme";
    public static final String SEARCH_NN_GAIA_TAG = "gaia_table_scheme";

    public static GaiaDataFrame gatGaiaDataFrame () throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> config = XMLHeaderSchema.readHeaders(SEARCH_NN_CONFIG_NAME);
        Map<String, String> gaiaSchema = XMLHeaderSchema.readHeaders(SEARCH_NN_GAIA_TAG);

        String inputNameGaia = config.get("input_name_gaia");

        ArrayList<String> gaiaSourceId = new ArrayList<String>();
        ArrayList<Double> gaiaRa = new ArrayList<Double>();
        ArrayList<Double> gaiaDec = new ArrayList<Double>();
        ArrayList<Double> gaiaPmra = new ArrayList<Double>();
        ArrayList<Double> gaiaPmdec = new ArrayList<Double>();
        ArrayList<Double> gaiaRaE = new ArrayList<Double>();
        ArrayList<Double> gaiaDecE = new ArrayList<Double>();
        ArrayList<Double> gaiaParallax = new ArrayList<Double>();


        Reader input2 = new FileReader(inputNameGaia);
        Iterable<CSVRecord> records2 = CSVFormat.EXCEL.withHeader().parse(input2);
        for (CSVRecord record2 : records2) {
            gaiaSourceId.add((record2.get(gaiaSchema.get("gaia_source_id"))));
            gaiaRa.add(Double.valueOf(record2.get(gaiaSchema.get("gaia_ra"))));
            gaiaDec.add(Double.valueOf(record2.get(gaiaSchema.get("gaia_dec"))));
            gaiaPmra.add(Double.valueOf(record2.get(gaiaSchema.get("gaia_pmra"))));
            gaiaPmdec.add(Double.valueOf(record2.get(gaiaSchema.get("gaia_pmdec"))));
            gaiaRaE.add(Double.valueOf(record2.get(gaiaSchema.get("gaia_ra_e"))));
            gaiaDecE.add(Double.valueOf(record2.get(gaiaSchema.get("gaia_dec_e"))));
            gaiaParallax.add(Double.valueOf(record2.get(gaiaSchema.get("gaia_parallax"))));

        }

        return new GaiaDataFrame(gaiaRa, gaiaPmra,gaiaDec,gaiaPmdec,gaiaSourceId,gaiaRaE,gaiaDecE, gaiaParallax);

    }
    public static LotssDataFrame getLotssDataFrame () throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> config = XMLHeaderSchema.readHeaders(SEARCH_NN_CONFIG_NAME);
        String inputNameLotss = config.get("input_name_lotss");
        Map<String, String> lotssSchema = XMLHeaderSchema.readHeaders(SEARCH_NN_LOTSS_TAG);

        ArrayList<String> lotssSourceId = new ArrayList<String>();
        ArrayList<Double> lotssRa = new ArrayList<Double>();
        ArrayList<Double> lotssDec = new ArrayList<Double>();
        ArrayList<Double> lotssSourceDate = new ArrayList<Double>();
        ArrayList<Double> lotssRaE = new ArrayList<Double>();
        ArrayList<Double> lotssDecE = new ArrayList<Double>();
        ArrayList<Double> lotssParallax = new ArrayList<Double>();

        Reader input1 = new FileReader(inputNameLotss);
        Iterable<CSVRecord> records1 = CSVFormat.EXCEL.withHeader().parse(input1);
        for (CSVRecord record1 : records1) {
            
            lotssSourceId.add((record1.get(lotssSchema.get("lotss_source_id"))));
            lotssRa.add(Double.valueOf(record1.get(lotssSchema.get("lotss_ra"))));
            lotssDec.add(Double.valueOf(record1.get(lotssSchema.get("lotss_dec"))));
            lotssSourceDate.add(Double.valueOf(record1.get(lotssSchema.get("lotss_source_date"))));
            lotssRaE.add(Double.valueOf(record1.get(lotssSchema.get("lotss_ra_e"))));
            lotssDecE.add(Double.valueOf(record1.get(lotssSchema.get("lotss_dec_e"))));
            lotssParallax.add(Double.valueOf(record1.get(lotssSchema.get("lotss_parallax"))));
        }
        input1.close();
        return new LotssDataFrame(lotssRa,lotssDec,lotssSourceDate,lotssSourceId, lotssRaE, lotssDecE, lotssParallax);
    }
}
