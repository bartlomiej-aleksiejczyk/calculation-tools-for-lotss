package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import org.xml.sax.SAXException;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.GaiaDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.LotssDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.ResultPojo;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.search.NeighborSearchObject;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.search.NeighborSearchObjectNoPmCorrection;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.xml.XMLHeaderSchema;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

import static pl.edu.uwm.student.aleksiejczyk.bartlomiej.opencsv.OpenCsv.gatGaiaDataFrame;
import static pl.edu.uwm.student.aleksiejczyk.bartlomiej.opencsv.OpenCsv.getLotssDataFrame;
import static pl.edu.uwm.student.aleksiejczyk.bartlomiej.savecsv.ResultStructure.saveObjectToCsv;

public class Main {
    public static final String SEARCH_NN_CONFIG_NAME = "nn_config_file";
    public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        Map<String, String> config = XMLHeaderSchema.readHeaders(SEARCH_NN_CONFIG_NAME);
        boolean isCorrectionTurnedOn = Boolean.parseBoolean(config.get("if_proper_motion_correction"));

        GaiaDataFrame gaiaDataFrame = gatGaiaDataFrame();
        LotssDataFrame lotssDataFrame = getLotssDataFrame();

        NeighborSearchObject neighborSearchObject = isCorrectionTurnedOn ?
                new NeighborSearchObject(gaiaDataFrame, lotssDataFrame) :
                new NeighborSearchObjectNoPmCorrection(gaiaDataFrame, lotssDataFrame);
        ResultPojo resultNeighborSearch = neighborSearchObject.searchForNeightbor();
        saveObjectToCsv(resultNeighborSearch);
/*        SkyMap skyMap = new SkyMap(lotssDataFrame.lotssRa, lotssDataFrame.lotssDec);
        System.out.println(skyMap.getDensity());
        SkyMap skyMap1 = new SkyMap(gaiaDataFrame.gaiaRa, gaiaDataFrame.gaiaDec);
        System.out.println(skyMap1.getDensity());*/
    }
}