package unam;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class App {

    public static String csvPath = "porcino_avicola.csv";

    public static void main(String[] args) throws IOException {
        RasterService raster = new RasterService(new FilReaderService(), new ImageReaderService());

        FuncionValor[] capas = raster.getFuncionesValorDeRuta(csvPath);

        double[] alphas = new double[] { 0.5, 1.0, 2.0 };
        for (double alpha : alphas) {
            String alphaString = Double.toString(alpha).replace('.', '_');
            calcularAptitudEspacial(capas, 1.0, "aptitud_" + alphaString + ".tif");
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
                double pixOwa = Owa.computePixel(listaValores, listaPesos, 0.5);

                if (Double.isNaN(listaValores[0])) {
                    rasterResultado.setSample(x, y, 0, nulo);
                } else {
                    rasterResultado.setSample(x, y, 0, (pixOwa * 1000));
                }
            }
        }

        ImageIO.write(resultado, "tif", new File(fileName));
    }

    private static double[] getPesos(FuncionValor[] capas) {
        double[] pesos = new double[capas.length];
        int indicePesos = 0;
        for (FuncionValor capa : capas) {
            pesos[indicePesos] = capa.getModel().getPeso();
            indicePesos += 1;
        }
        return pesos;
    }

    private static double[] getValores(FuncionValor[] capas, int x, int y) {
        double[] valores = new double[capas.length];
        int indiceValores = 0;
        for (FuncionValor capa : capas) {
            valores[indiceValores] = capa.getPixel(x, y);
            indiceValores += 1;
        }
        return valores;
    }
}
