package mathdiffer;

import mathdiffer.Calculation;

public class DifferCalc {

    private static final float EPS = 0.001f;
    private static final float H = 1;

    public static void get(String form, double a, double b, double y0) {
        double n = (b - a) / H;
        double[] X = new double[(int) n + 1];
        double[] Y1 = new double[(int) n + 1];
        double[] Y = new double[(int) n + 1];
        //calculate
        X[0] = a;
        Y[0] = y0;
        for (int i = 1; i <= n; i++) {
            X[i] = a + i * H;
            Y1[i] = Y[i - 1] + H * f(X[i - 1], Y[i - 1], form);
            Y[i] = Y[i - 1] + H * (f(X[i - 1], Y[i - 1], form) + f(X[i], Y1[i], form)) / 2.0;
        }
        //print results
        for (int i = 0; i <= n; i++) {
            System.out.print("X[" + i + "]=" + X[i] + " \n");
        }
        System.out.println("\n");
        for (int i = 0; i <= n; i++) {
            System.out.print("Y[" + i + "]=" + Y[i] + " \n");
        }
    }

    private static double f(double x, double y, String form) {
        String res;
        res = form.replaceAll("x", String.valueOf(x));
        res = res.replaceAll("y", String.valueOf(y));
        return Calculation.eval(Calculation.processingStr(res));
    }

}
