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
            calcularAptitudEspacial(capas, alpha, "aptitud_" + alphaString + ".tif");
        }
    }

    private static void calcularAptitudEspacial(FuncionValor[] capas, double alfa, String fileName) throws IOException {
        double[] listaPesos = getPesos(capas);
        BufferedImage resultado = new BufferedImage(capas[0].getColumnas(), capas[0].getRenglones(),
                BufferedImage.TYPE_USHORT_GRAY);
        WritableRaster rasterResultado = resultado.getRaster();

        setResultedImage(capas, alfa, listaPesos, rasterResultado);

        ImageIO.write(resultado, "tif", new File(fileName));
    }

    private static void setResultedImage(FuncionValor[] capas, double alfa, double[] listaPesos,
            WritableRaster rasterResultado) {
        for (int x = 0; x < capas[0].getColumnas(); x++) {
            for (int y = 0; y < capas[0].getRenglones(); y++) {
                for (int banda = 0; banda < capas[0].getBandaDeColor(); banda++) {
                    double processedPixel = getProcessedPixel(capas, alfa, x, y, banda);
                    // Double currentResult = Double.isNaN(listaValores[0]) ? Double.NaN : (pixOwa *
                    // 1000);
                    rasterResultado.setSample(x, y, banda, processedPixel);
                }
            }
        }
    }

    private static double getProcessedPixel(FuncionValor[] capas, double alfa, int x, int y,
            int banda) {
        double[] listaValores = getPixelsFromFuncionValor(capas, x, y, banda);
        double processedPixel = Owa.computePixel(listaValores, getPesos(capas), alfa);
        return processedPixel;
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

    private static double[] getPixelsFromFuncionValor(FuncionValor[] capas, int x, int y, int banda) {
        double[] valores = new double[capas.length];
        int indiceValores = 0;
        for (FuncionValor capa : capas) {
            valores[indiceValores] = capa.getPixel(x, y, banda);
            indiceValores += 1;
        }
        return valores;
    }
}
