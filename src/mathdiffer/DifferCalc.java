package mathdiffer;

public class DifferCalc {

    private static final float EPS = 0.001f;

    public static double[][] get(String form, double a, double b, double y0, double h) {
        double n = (b - a) / h;
        double[] X = new double[(int) n];
        double[] Y1 = new double[(int) n];
        double[] Y = new double[(int) n];
        //calculate
        X[0] = a;
        Y[0] = y0;
        for (int i = 1; i < n; i++) {
            X[i] = a + i * h;
            Y1[i] = Y[i - 1] + h * f(X[i - 1], Y[i - 1], form);
            Y[i] = Y[i - 1] + h * (f(X[i - 1], Y[i - 1], form) + f(X[i], Y1[i], form)) / 2.0;
        }
        double[][] Res = {X,Y}; 
        return Res;
    }

    private static double f(double x, double y, String form) {
        String res;
        res = form.replaceAll("x", String.valueOf(x));
        res = res.replaceAll("y", String.valueOf(y));
        return Calculation.eval(Calculation.processingStr(res));
    }

}
