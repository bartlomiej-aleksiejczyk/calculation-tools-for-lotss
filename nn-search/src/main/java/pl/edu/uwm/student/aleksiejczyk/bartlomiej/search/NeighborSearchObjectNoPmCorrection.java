package pl.edu.uwm.student.aleksiejczyk.bartlomiej.search;

import com.google.common.util.concurrent.AtomicDoubleArray;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.makeMapForCalculations.SkyMap;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.GaiaDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.LotssDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.ResultPojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NeighborSearchObjectNoPmCorrection extends NeighborSearchObject {

    public NeighborSearchObjectNoPmCorrection(GaiaDataFrame gaiaDataFrame, LotssDataFrame lotssDataFrame) {
        super(gaiaDataFrame, lotssDataFrame);
    }

    @Override
    public double[] dateLotss(double[] a, Double b, double[] c){
        return a;
    }
}