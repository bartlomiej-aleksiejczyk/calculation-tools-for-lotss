package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.TableHDU;
import nom.tam.util.ColumnTable;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static final Integer INDEX_OF_DATA_HDU = 1;
    public static void main(String[] args) throws FitsException, IOException {
        Fits fits = new Fits("LoTSS_DR2_v110_masked.srl.fits");

        TableHDU hdu = (TableHDU) fits.getHDU(INDEX_OF_DATA_HDU);
        int colUTC = hdu.findColumn("Source_Name");
        System.out.println(colUTC);


        ColumnTable tableData = (ColumnTable) fits.getHDU(INDEX_OF_DATA_HDU).getKernel();

        for(int row = 0; row < 10; row++) {

            // Retrieve scalar entries with convenient getters...
/*            Object retrievedData = tableData.getElement(row, colUTC);
            double[] retrievedDataArray = (double[]) retrievedData;
            System.out.println(Arrays.toString(retrievedDataArray));*/
            Object retrievedData = tableData.getElement(row, colUTC);
            System.out.println(retrievedData.);
            if (retrievedData instanceof double[]) {
                System.out.println(Arrays.toString((double[]) retrievedData));
            } else if (retrievedData instanceof byte[]) {
                System.out.println(Arrays.toString((byte[]) retrievedData));
            } else if (retrievedData instanceof String[]) {
                System.out.println(Arrays.toString((String[]) retrievedData));
            } else {
                System.out.println("Unknown array type: " + retrievedData.getClass());
            }

        }

        fits.close();
    }

}