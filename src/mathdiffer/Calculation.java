package mathdiffer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public strictfp class Calculation {

    private static final double EPS = 3;
    private static final double N = 100;

    public static String get(double b, double a, String formula) {
        double x, h, s;
        try {
            h = (b - a) / N;
            s = 0;
            x = a + h;
            while (x < b) {
                s = s + 4 * f(x, formula);
                x = x + h;
                s = s + 2 * f(x, formula);
                x = x + h;
            }
            s = h / 3 * (s + f(a, formula) - f(b, formula));
        } catch (ArithmeticException exeption) {
            return "Ошибка в записи выражения";
        }
        int eps = (int) Math.pow(10, EPS);
        return String.valueOf((double) Math.round(s * eps) / eps);
    }

    public static double f(double x, String form) {
        String line = form;
        line = line.replaceAll(" ", "");
        line = line.replaceAll("pi", String.valueOf(Math.PI));
        line = line.replaceAll("e", String.valueOf(Math.E));
        // replace 'x^3' on 'x*x*x'
        line = line.replaceAll("x", String.valueOf(x));
        // replace sin(3*3+4*3) on Math.sin(21) ...s
        // replace log(4) on Math.log(4) ...
        // ...
        return eval(line);
    }

    private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("JavaScript");

    private static double eval(String form) throws ArithmeticException {
        try {
            Object s2 = ENGINE.eval(form);
            return Double.valueOf(s2.toString());
        } catch (ScriptException e1) {
            throw new ArithmeticException();
        }
    }

}
