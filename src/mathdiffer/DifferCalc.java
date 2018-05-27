package mathdiffer;

import mathdiffer.Calculation;

public class DifferCalc {

    private static final float EPS = 0.01f;
    private static final float N = 1;

    public static double[] get(String form, double a, double b, double y0) {
        int range = (int) Math.abs(a - b);
        double[] result = new double[range];
        double k1, k2, k3, k4;
        double x = a;
        double yn = y0;
        double h = 1;
        for (int i = 0; x <= b; i++) {
            k1 = f(x, yn, form);
            k2 = f(x + h / 2, yn + (h * k1) / 2, form);
            k3 = f(x + h / 2, yn + (h * k2) / 2, form);
            k4 = f(x + h, yn + h * k3, form);
            yn = yn + h / 6 * (k1 + k2 + k3 + k4);
            result[i] = yn;
            x += h;
        }

        return result;
    }

    private static double f(double x, double y, String form) {
        String res;
        res = form.replaceAll("x", String.valueOf(x));
        res = res.replaceAll("y", String.valueOf(y));
        return Calculation.eval(res);
    }

}
