package mathdiffer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public strictfp class Calculation {

    public static final double EPS = 8;
    public static final double N = 100;
    private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("JavaScript");

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
        double result = (double) Math.round(s * eps) / eps;
        return String.valueOf(result) + "\n\n" + toFraction(result) ;
    }
    
    public static String processingStr(String form) {
        String line = form;
        line = line.replaceAll(" ", "");
        line = line.replaceAll("pi", String.valueOf(Math.PI));
        line = line.replaceAll("e", String.valueOf(Math.E));
        line = line.replaceAll("sin[(]", "Math.sin(");
        line = line.replaceAll("cos[(]", "Math.cos(");
        line = line.replaceAll("tan[(]", "Math.tan(");
        line = line.replaceAll("asin[(]", "Math.asin(");
        line = line.replaceAll("acos[(]", "Math.acos(");
        line = line.replaceAll("atan[(]", "Math.atan(");
        line = line.replaceAll("exp[(]", "Math.exp(");
        line = line.replaceAll("log[(]", "Math.log(");
        line = line.replaceAll("log10[(]", "Math.log10(");
        line = line.replaceAll("log2[(]", "Math.log2(");
        line = line.replaceAll("pow[(]", "Math.pow(");
        line = line.replaceAll("sign[(]", "Math.sign(");
        line = line.replaceAll("sqrt[(]", "Math.sqrt(");
        line = line.replaceAll("abs[(]", "Math.abs(");
        return line;
    }
    
    public static double f(double x, String form) {
        form = form.replaceAll("x", String.valueOf(x));
        return eval(processingStr(form));
    }

    public static double eval(String form) throws ArithmeticException {
        try {
            Object s2 = ENGINE.eval(form);
            return Double.valueOf(s2.toString());
        } catch (ScriptException e1) {
            throw new ArithmeticException();
        }
    }
    
    private static String toFraction(double val) {
        String res;
        final double ratio = Math.pow(10, -1);
        for (int i = 1; true; i++) {
            double tem = val / (1f / i);
            if (Math.abs(tem - Math.round(tem)) <= ratio) {
                res = String.valueOf(Math.round(tem)) + "/" + i;
                break;
            }
        }
        return res;
    }

}
