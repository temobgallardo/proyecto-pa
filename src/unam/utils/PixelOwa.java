package utils;

public class PixelOwa  {
    // TODO: what is thise for?
    public double[] valores;
    public double[] pesos;
    public double alpha;

    /* Constructor de la clase Owa */
    public PixelOwa(double[] v, double[] w, double al) {
        valores = v;
        pesos = w;
        alpha = al;
    }

    /* Calculo de Owa para un pixel */
    public double valorPixel() {
        /*
         * Utilizamos Bubble sort para ordenar los valores y
         * los pesos respecto a los valores
         */

        double temporal_val = 0.0;
        double temporal_pes = 0.0;

        for (int i = 0; i < valores.length; i++) {
            for (int j = 1; j < (valores.length - i); j++) {
                if (valores[j - 1] < valores[j]) {
                    /*
                     * Ajustamos los valores de la lista valores
                     * de mayor a menor
                     */
                    temporal_val = valores[j - 1];
                    valores[j - 1] = valores[j];
                    valores[j] = temporal_val;

                    /*
                     * Ajustamos los valores de la lista pesos
                     * respecto al orden de la lista valores
                     */
                    temporal_pes = pesos[j - 1];
                    pesos[j - 1] = pesos[j];
                    pesos[j] = temporal_pes;

                }
            }
        }

        /*
         * Calculamos las sumas para obtener el owa del Pixel
         * actual
         */
        double[] oper1 = new double[pesos.length];
        double[] oper2 = new double[pesos.length];
        double owa_f = 0.0;

        for (int i = 0; i < pesos.length; i++) {

            if (i == 0) {
                oper1[0] = pesos[0];
                oper2[0] = oper1[0];
            } else {

                double suma = 0.0;
                for (int j = 0; j <= i; j++) {
                    suma += pesos[j];
                }
                oper1[i] = Math.pow(suma, alpha);
                oper2[i] = (oper1[i] - oper1[i - 1]) * valores[i];
                /*
                 * System.out.println(oper1[i]);
                 * System.out.println(oper2[i]);
                 */

            }
            owa_f += oper2[i];
        }
        return owa_f;
    } 
    
    public static double valorPixel(double[] valores, double[] pesos, double alpha) {
        /*
         * Utilizamos Bubble sort para ordenar los valores y
         * los pesos respecto a los valores
         */

        double temporal_val = 0.0;
        double temporal_pes = 0.0;

        for (int i = 0; i < valores.length; i++) {
            for (int j = 1; j < (valores.length - i); j++) {
                if (valores[j - 1] < valores[j]) {
                    /*
                     * Ajustamos los valores de la lista valores
                     * de mayor a menor
                     */
                    temporal_val = valores[j - 1];
                    valores[j - 1] = valores[j];
                    valores[j] = temporal_val;

                    /*
                     * Ajustamos los valores de la lista pesos
                     * respecto al orden de la lista valores
                     */
                    temporal_pes = pesos[j - 1];
                    pesos[j - 1] = pesos[j];
                    pesos[j] = temporal_pes;

                }
            }
        }

        /*
         * Calculamos las sumas para obtener el owa del Pixel
         * actual
         */
        double[] oper1 = new double[pesos.length];
        double[] oper2 = new double[pesos.length];
        double owa_f = 0.0;

        for (int i = 0; i < pesos.length; i++) {

            if (i == 0) {
                oper1[0] = pesos[0];
                oper2[0] = oper1[0];
            } else {

                double suma = 0.0;
                for (int j = 0; j <= i; j++) {
                    suma += pesos[j];
                }
                oper1[i] = Math.pow(suma, alpha);
                oper2[i] = (oper1[i] - oper1[i - 1]) * valores[i];
                /*
                 * System.out.println(oper1[i]);
                 * System.out.println(oper2[i]);
                 */

            }
            owa_f += oper2[i];
        }
        return owa_f;
    }

}
