package mathdiffer;

public class Calculation {
    private static final double EPS = 0.0001;
    
    public static double get(double a, double b, String formula) {
        double result = 0;
        double I = EPS + 1, I1 = 0; //I-предыдущее вычисленное значение интеграла, I1-новое, с большим N.

        for (int N = 2; (N <= 4) || (Math.abs(I1 - I) > EPS); N *= 2) {
            double h, sum2 = 0, sum4 = 0, sum = 0;
            h = (b - a) / (2 * N);  //Шаг интегрирования.
            for (int i = 1; i <= 2 * N - 1; i += 2) {
                sum4 += f(a + h * i, formula);  //Значения с нечётными индексами, которые нужно умножить на 4.
                sum2 += f(a + h * (i + 1), formula);    //Значения с чётными индексами, которые нужно умножить на 2.
            }
            sum = f(a, formula) + 4 * sum4 + 2 * sum2 - f(b, formula);  //Отнимаем значение f(b) так как ранее прибавили его дважды. 
            I = I1;
            I1 = (h / 3) * sum;
        }
        
        return I1;
    }
    
    private static double f(double x, String form) {
        String line = form.replaceAll("x", String.valueOf(x));
        line = line.replaceAll(" ", "");
        line = line.replaceAll("^", "**");
        /*
            def getEval(formula):
                try:
                    res = eval(t)
                except ValueError as err:
                    print(err)
            return res
            
            код для питона который нужно вызвать ниже
        */
        
        // return getEval(line); вернуть результат питонской функции
        return x*x + 3*x; // стереть эту строку
    }
    
}
