package mathdiffer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public strictfp class Calculation {

    private static final double EPS = 0.0001;
    private static final double n = 100;

    public static double get(double b, double a, String formula) {
        double x, h, s;
        h = (b - a) / n;
        s = 0;
        x = a + h;
        while (x < b) {
            s = s + 4 * f(x, formula);
            x = x + h;
            s = s + 2 * f(x, formula);
            x = x + h;
        }
        s = h / 3 * (s + f(a, formula) - f(b, formula));

        return s;
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

    private static double eval(String form) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            Object s2 = engine.eval(form);
            return Double.valueOf(s2.toString());
        } catch (ScriptException e1) {
            e1.printStackTrace();
            return 0;
        }
    }

}
