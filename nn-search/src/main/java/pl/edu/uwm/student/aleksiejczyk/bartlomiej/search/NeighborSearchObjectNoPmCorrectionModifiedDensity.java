package pl.edu.uwm.student.aleksiejczyk.bartlomiej.search;

import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.GaiaDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.LotssDataFrame;

public class NeighborSearchObjectNoPmCorrectionModifiedDensity extends NeighborSearchObjectModifiedDensity {

    public NeighborSearchObjectNoPmCorrectionModifiedDensity(GaiaDataFrame gaiaDataFrame, LotssDataFrame lotssDataFrame) {
        super(gaiaDataFrame, lotssDataFrame);
    }

    @Override
    public double[] dateLotss(double[] a, Double b, double[] c) {
        return a;
    }
}