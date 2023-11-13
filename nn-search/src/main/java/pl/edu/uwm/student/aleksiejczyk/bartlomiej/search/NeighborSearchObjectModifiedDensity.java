package pl.edu.uwm.student.aleksiejczyk.bartlomiej.search;

import pl.edu.uwm.student.aleksiejczyk.bartlomiej.makeMapForCalculations.SkyMap;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.GaiaDataFrame;
import pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos.LotssDataFrame;

import java.util.Arrays;

public class NeighborSearchObjectModifiedDensity extends NeighborSearchObject {
    double[] lotssParallax;
    double[] gaiaParallax;
    double gaiaArea;

    public NeighborSearchObjectModifiedDensity(GaiaDataFrame gaiaDataFrame, LotssDataFrame lotssDataFrame) {
        super(gaiaDataFrame, lotssDataFrame);
        lotssParallax = lotssDataFrame.lotssParallax;
        gaiaParallax = gaiaDataFrame.gaiaParallax;
        System.out.println(gaiaParallax.length);
        Arrays.sort(gaiaParallax, 0, super.lenGaia);
        gaiaArea = new SkyMap(super.gaiaDataFrame.gaiaRa, super.gaiaDataFrame.gaiaDec).getArea();
    }

    @Override
    public double getDensity(double density, int lotssNum) {
        int indexOfGreaterOrEqualGaia = Arrays.binarySearch(gaiaParallax, lotssParallax[lotssNum]);

        int numberOfGreaterParallaxes = 0;
        if (indexOfGreaterOrEqualGaia < 0) {
            // Calculate the insertion point by taking the absolute value of the result and subtracting 1
            int insertionPoint = Math.abs(indexOfGreaterOrEqualGaia) - 1;
            // Subtract the insertion point from the length of the array to get the count
            numberOfGreaterParallaxes = super.lenLotss - insertionPoint;
        }
        return numberOfGreaterParallaxes / gaiaArea;
    }
}