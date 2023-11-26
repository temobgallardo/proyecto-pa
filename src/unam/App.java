/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package unam;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author victor
 */
public class App {

    public static String pDir = "C:\\source\\programacion avanzada\\MediaPonderadaOrdenada\\porcino_avicola.csv";

    public static void main(String[] args) throws IOException {

        IFileReaderService fr = new FilReaderService();
        RasterService raster = new RasterService(fr, new ImageReaderService());

        capas = raster.getFuncionesValorDeRuta(
                "C:\\source\\programacion avanzada\\MediaPonderadaOrdenada\\porcino_avicola.csv");

        double[] alphas = new double[] { .5, 1, 1.5 };
        for (double alpha : alphas) {
            calcularAptitudEspacial(capas, 1.0, "aptitud_" + alpha + ".tif");
        }
    }

    private static void calcularAptitudEspacial(FuncionValor[] capas, double alfa, String fileName)
            throws IOException {
        double[] listaPesos = getPesos(capas);
        int columnas = capas[0].getColumnas();
        int renglones = capas[0].getRenglones();
        double nulo = Double.NaN;
        BufferedImage resultado = new BufferedImage(columnas, renglones, BufferedImage.TYPE_USHORT_GRAY);
        WritableRaster rasterResultado = resultado.getRaster();

        for (int x = 0; x < columnas; x++) {
            for (int y = 0; y < renglones; y++) {

                double[] listaValores = getValores(capas, x, y);
                double pixOwa = Owa.valorPixel(listaValores, listaPesos, 0.5);

                if (Double.isNaN(listaValores[0])) {
                    rasterResultado.setSample(x, y, 0, nulo);
                } else {
                    rasterResultado.setSample(x, y, 0, (pixOwa * 1000));
                }
            }
        }

        ImageIO.write(resultado, "tif", new File(fileName));
    }

    public static double[] getPesos(FuncionValor[] capas) {
        double[] pesos = new double[capas.length];
        int indicePesos = 0;
        for (FuncionValor capa : capas) {
            pesos[indicePesos] = capa.getPeso();
            indicePesos += 1;
        }
        return pesos;
    }

    public static double[] getValores(FuncionValor[] capas, int x, int y) {
        double[] valores = new double[capas.length];
        int indiceValores = 0;
        for (FuncionValor capa : capas) {
            valores[indiceValores] = capa.getPixel(x, y);
            indiceValores += 1;
        }
        return valores;
    }
}
