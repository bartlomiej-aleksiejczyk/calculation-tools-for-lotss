package pl.edu.uwm.student.aleksiejczyk.bartlomiej.search;

import com.google.common.util.concurrent.AtomicDoubleArray;
import org.xml.sax.SAXException;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.makeMapForCalculations.SkyMap;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.GaiaDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.LotssDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.ResultPojo;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.xml.XMLHeaderSchema;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NeighborSearchObject {
    public static final String SEARCH_NN_CONFIG_NAME = "nn_config_file";
    public final GaiaDataFrame gaiaDataFrame;
    public final LotssDataFrame lotssDataFrame;
    public final int lenGaia;
    final int lenLotss;
    double[] lotssDist;
    String[] lotssId;
    String[] closestNeightbourId;
    double[] closestNeightbourRa;
    double[] gaiaConvertedRa;
    double[] gaiaConvertedDec;
    double[] closestNeightbourDec;
    double[] closestNeightbourDistSec;
    double[] lotssRaE;
    double[] lotssDecE;
    double[] gaiaRaE;
    double[] gaiaDecE;
    String[][] finalMethodOutput;
    ArrayList<Object> outputTransformHelper;
    double[] gaiaRaEResults;
    double[] gaiaDecEResults;
    double[] rootColumn;
    double[] rColumn;
    double gaiaDensity;
    double lotssDensity;
    double[] gaiaDec;
    double[] gaiaRa;

    CountDownLatch latch;

    public NeighborSearchObject(GaiaDataFrame gaiaDataFrame, LotssDataFrame lotssDataFrame) {

        this.gaiaDataFrame = gaiaDataFrame;
        this.lotssDataFrame = lotssDataFrame;
        this.lenGaia = (gaiaDataFrame.gaiaDec).length;
        this.lenLotss = lotssDataFrame.lotssRa.length;
        lotssDist = new double[lenLotss];
        lotssId = lotssDataFrame.lotssSourceId;
        lotssRaE = lotssDataFrame.lotssRaE;
        lotssDecE = lotssDataFrame.lotssDecE;
        closestNeightbourId = new String[lenLotss];
        closestNeightbourRa = new double[lenLotss];
        gaiaConvertedRa = new double[lenLotss];
        gaiaConvertedDec = new double[lenLotss];
        gaiaRa = gaiaDataFrame.gaiaRa;
        gaiaDec = gaiaDataFrame.gaiaDec;
        gaiaRaE = gaiaDataFrame.gaiaRaE;
        gaiaDecE = gaiaDataFrame.gaiaDecE;
        gaiaRaEResults = new double[lenLotss];
        gaiaDecEResults = new double[lenLotss];
        closestNeightbourDec = new double[lenLotss];
        closestNeightbourDistSec = new double[lenLotss];
        rootColumn = new double[lenLotss];
        rColumn = new double[lenLotss];
        latch = new CountDownLatch(lenLotss);
        lotssDensity = new SkyMap(lotssDataFrame.lotssRa, lotssDataFrame.lotssDec).getDensity();
        //gaiaDensity = new SkyMap(gaiaRa,gaiaDec).getDensity();
        outputTransformHelper = new ArrayList<>() {
            {
                add(lotssId);
                add(closestNeightbourId);
                add(closestNeightbourRa);
                add(closestNeightbourDec);
                add(gaiaConvertedRa);
                add(gaiaConvertedDec);
                add(closestNeightbourDistSec);
                add(lotssRaE);
                add(lotssDecE);
                add(rootColumn);
                add(rColumn);

            }
        };
        finalMethodOutput = new String[outputTransformHelper.size()][lenLotss];

    }

    class taskForMultithread implements Runnable {
        int lotssNum;

        public taskForMultithread(int lotssNum) {
            this.lotssNum = lotssNum;
        }

        @Override
        public void run() {
            oneCalculationCycle(lotssNum);
        }
    }

    public double[] dateLotss(double[] a, Double b, double[] c) {
        int size = a.length;
        double[] outputArray = new double[size];
        for (int i = 0; i < size; i++) {
            outputArray[i] = ((a[i] + ((b - 2016) * (c[i] / 1000)) / 3600));
        }
        return (outputArray);
    }

    public double getDensity(double density, int lotssNum) {
        return (density);
    }

    public void oneCalculationCycle(int lotssNum) {
        double[] gaiaPmra = gaiaDataFrame.gaiaPmra;
        double[] gaiaPmdec = gaiaDataFrame.gaiaPmdec;
        String[] gaiaSourceId = gaiaDataFrame.gaiaSourceId;


        double lotss_obj_ra = lotssDataFrame.lotssRa[lotssNum];
        double lots_obj_dec = lotssDataFrame.lotssDec[lotssNum];
        double lotss_date = lotssDataFrame.lotssSourceDate[lotssNum];
        double[] raGaiaToLotss = dateLotss(gaiaRa, lotss_date, gaiaPmra);
        double[] decGaiaToLotss = dateLotss(gaiaDec, lotss_date, gaiaPmdec);
        double[] partialResult = new double[lenGaia];


        for (int j = 0; j < lenGaia; j++) {
            partialResult[j] = (Math.sqrt(((Math.pow(((raGaiaToLotss[j] - lotss_obj_ra) * Math.cos(((decGaiaToLotss[j] * (Math.PI)) / 180))), 2) + Math.pow(decGaiaToLotss[j] - lots_obj_dec, 2)))));
        }

        int lowestIndex = 0;

        for (int k = 0; k < lenGaia; k++) {

            if (partialResult[k] < partialResult[lowestIndex]) {
                lowestIndex = k;
            }

        }


        closestNeightbourId[lotssNum] = gaiaSourceId[lowestIndex];
        closestNeightbourRa[lotssNum] = gaiaRa[lowestIndex];
        //System.out.println(gaiaRa[lowestIndex]);

        closestNeightbourDec[lotssNum] = gaiaDec[lowestIndex];
        gaiaConvertedRa[lotssNum] = raGaiaToLotss[lowestIndex];
        gaiaConvertedDec[lotssNum] = decGaiaToLotss[lowestIndex];
        closestNeightbourDistSec[lotssNum] = partialResult[lowestIndex];
        gaiaRaEResults[lotssNum] = gaiaRaE[lowestIndex];
        gaiaDecEResults[lotssNum] = gaiaDecE[lowestIndex];
        //Czy coś robić z błędem


        rootColumn[lotssNum] = Math.sqrt((((Math.pow((lotss_obj_ra - gaiaConvertedRa[lotssNum]), 2)) / 12960000) / (Math.pow(lotssRaE[lotssNum], 2) + Math.pow(gaiaRaEResults[lotssNum], 2))) + (((Math.pow((lots_obj_dec - gaiaConvertedDec[lotssNum]), 2)) / 12960000) / (Math.pow(lotssDecE[lotssNum], 2) + Math.pow(gaiaDecEResults[lotssNum], 2))));
        rColumn[lotssNum] = Math.exp(-Math.PI * Math.pow(rootColumn[lotssNum], 2) * Math.sqrt(Math.pow(lotssRaE[lotssNum], 2) + Math.pow(gaiaRaEResults[lotssNum], 2)) * Math.sqrt(Math.pow(lotssDecE[lotssNum], 2) + Math.pow(gaiaDecEResults[lotssNum], 2)) * getDensity(lotssDensity, lotssNum));
        latch.countDown();
    }

    public ResultPojo searchForNeightbor() throws InterruptedException {
        String[] headers = new String[]{"lotssId", "closestNeightbourId", "closestNeightbourRa", "closestNeightbourDec",
                "gaiaConvertedRa", "gaiaConvertedDec", "closestNeightbourDistSec", "lotssRaE", "lotssDecE", "rootColumn", "rColumn"};
        // use virtual threads
        ExecutorService executor = Executors./*.newSingleThreadExecutor();*/newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        //while(latch.getCount()!=0) {
        for (int lotssNum1 = 0; lotssNum1 < lenLotss; lotssNum1++) {
            executor.execute(new taskForMultithread(lotssNum1));
        }
        //System.out.println(latch.getCount());

        //}
        //latch.await();
        while (latch.getCount() != 0) {
            System.out.print("\r" + "" + String.valueOf(latch.getCount()));
        }

        executor.shutdown();
        System.out.println(closestNeightbourId[2]);

        for (int i = 0; i < outputTransformHelper.size(); i++) {
            Object object = outputTransformHelper.get(i);
            if (object instanceof double[]) {
                double[] outputTemp = (double[]) object;
                finalMethodOutput[i] = Arrays.stream(outputTemp)
                        .mapToObj(d -> Double.toString(d))
                        .toArray(String[]::new);
            } else {
                String[] outputTemp = (String[]) object;
                finalMethodOutput[i] = outputTemp;
            }
        }
        AtomicDoubleArray arr = new AtomicDoubleArray(1333);
        return new ResultPojo(headers, finalMethodOutput);
    }
}