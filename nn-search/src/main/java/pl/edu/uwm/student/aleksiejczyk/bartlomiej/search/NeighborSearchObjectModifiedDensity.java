package pl.edu.uwm.student.aleksiejczyk.bartlomiej.search;

import pl.edu.uwm.student.aleksiejczyk.bartlomiej.makeMapForCalculations.SkyMap;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.GaiaDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.LotssDataFrame;

import java.util.Arrays;

public class NeighborSearchObjectModifiedDensity extends NeighborSearchObject {
    double[] lotssParallax;
    double[] gaiaParallaxSorted;
    double[] gaiaParallax;
    double gaiaArea;

    public NeighborSearchObjectModifiedDensity(GaiaDataFrame gaiaDataFrame, LotssDataFrame lotssDataFrame) {
        super(gaiaDataFrame, lotssDataFrame);
        lotssParallax = lotssDataFrame.lotssParallax;
        gaiaParallax = gaiaDataFrame.gaiaParallax;
        gaiaParallaxSorted = gaiaDataFrame.gaiaParallax;
        Arrays.sort(gaiaParallaxSorted, 0, super.lenGaia);
        gaiaArea = new SkyMap(super.gaiaDataFrame.gaiaRa, super.gaiaDataFrame.gaiaDec).getArea();
    }

    @Override
    public double getDensity(double density, int lotssNum, int lowestIndex) {
        int indexOfGreaterOrEqualGaia = Arrays.binarySearch(gaiaParallaxSorted, gaiaParallax[lowestIndex]);
        //Parallaxes should be sorted desc
        int numberOfGreaterParallaxes = 0;
        if (indexOfGreaterOrEqualGaia < 0) {
            int insertionPoint = Math.abs(indexOfGreaterOrEqualGaia) - 1;
            numberOfGreaterParallaxes = super.lenGaia - insertionPoint;
        } else {
            numberOfGreaterParallaxes = super.lenGaia - indexOfGreaterOrEqualGaia;
        }

        return numberOfGreaterParallaxes / gaiaArea;
    }
}