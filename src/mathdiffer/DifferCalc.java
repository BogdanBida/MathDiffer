package mathdiffer;
import mathdiffer.Calculation.*;

public class DifferCalc {

    public static double[] get(String form, double a, double b) {
        int range = (int) Math.abs(a - b);
        double[] result = new double[range];
        for (int i = 0; i < range; i++) {
            result[i] = i;
        }
        return result;
    }
    

}