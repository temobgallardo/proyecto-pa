package unam;

public class Test_Owa {
    public static double[] valores = { 1.000, 1.000, 0.028, 0.983, 1.000, 0.830, 0.085, 0.705, 0.527, 0.776 };
    public static double[] pesos = { 0.046, 0.232, 0.012, 0.053, 0.030, 0.265, 0.066, 0.087, 0.161, 0.047 };
    public static double alpha = 0.5;

    static Owa o = new Owa(valores, pesos, alpha);

    /**
     * @param args
     */
    // public static void main(String[] args) {
    //     System.out.println(o.valorPixel());
    // }
}
