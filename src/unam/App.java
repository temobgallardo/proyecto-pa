/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package unam;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import unam.utils.PixelOwa;
import utils.FuncionValor2;

/**
 *
 * @author victor
 */
public class App {

    public static String pDir = "C:\\source\\programacion avanzada\\MediaPonderadaOrdenada\\porcino_avicola.csv";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        FuncionValor2 fv1 = new FuncionValor2("lineas electricas", 0.046411428,
                pDir + "fv_por_bio_usv_cobertura_usv_svi_16cats_costa_manglar.tif");
        FuncionValor2 fv2 = new FuncionValor2("manto freatico", 0.232140714,
                pDir + "fv_por_bio_agua_d_manto_freatico.tif");
        FuncionValor2 fv3 = new FuncionValor2("manto freatico", 0.01156958,
                pDir + "fv_por_socio_loca_d_localidades_15k_exc.tif");
        FuncionValor2 fv4 = new FuncionValor2("manto freatico", 0.05285302,
                pDir + "fv_por_socio_loca_d_localidades_2500_15k_exc.tif");
        FuncionValor2 fv5 = new FuncionValor2("manto freatico", 0.03016794,
                pDir + "fv_por_socio_loca_d_localidades_2500_exc.tif");
        FuncionValor2 fv6 = new FuncionValor2("manto freatico", 0.26526176,
                pDir + "fv_por_infra_cam_d_carretera_prolongacion.tif");
        FuncionValor2 fv7 = new FuncionValor2("manto freatico", 0.06631544,
                pDir + "fv_por_infra_cam_d_caminos_veredas.tif");
        FuncionValor2 fv8 = new FuncionValor2("manto freatico", 0.087476208,
                pDir + "fv_por_infra_tif_d_rastros_porcinos_avicolas.tif");
        FuncionValor2 fv9 = new FuncionValor2("manto freatico", 0.16102492,
                pDir + "fv_por_infra_elec_d_lineas_electricas.tif");
        FuncionValor2 fv10 = new FuncionValor2("manto freatico", 0.046941052,
                pDir + "fv_por_infra_puer_d_puerto_progreso_con_carreteras.tif");
        FuncionValor2[] capas = { fv1, fv2, fv3, fv4, fv5, fv6, fv7, fv8, fv9, fv10 };

        unam.IFileReaderService fr = new unam.FilReaderService();
        RasterService raster = new RasterService(fr, new ImageReaderService());

        capas = raster.getFuncionesValorDeRuta(
                "C:\\source\\programacion avanzada\\MediaPonderadaOrdenada\\porcino_avicola.csv");

        double[] alphas = new double[] { .5, 1, 1.5 };
        for (double alpha : alphas) {
            calcularAptitudEspacial(capas, 1.0, "aptitud_" + alpha + ".tif");
        }
    }

    private static void calcularAptitudEspacial(FuncionValor2[] capas, double alfa, String fileName)
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
                double pixOwa = PixelOwa.valorPixel(listaValores, listaPesos, 0.5);

                if (Double.isNaN(listaValores[0])) {
                    rasterResultado.setSample(x, y, 0, nulo);
                } else {
                    rasterResultado.setSample(x, y, 0, (pixOwa * 1000));
                }
            }
        }

        ImageIO.write(resultado, "tif", new File(fileName));
    }

    public static double[] getPesos(FuncionValor2[] capas) {
        double[] pesos = new double[capas.length];
        int indicePesos = 0;
        for (FuncionValor2 capa : capas) {
            pesos[indicePesos] = capa.getPeso();
            indicePesos += 1;
        }
        return pesos;
    }

    public static double[] getValores(FuncionValor2[] capas, int x, int y) {
        double[] valores = new double[capas.length];
        int indiceValores = 0;
        for (FuncionValor2 capa : capas) {
            valores[indiceValores] = capa.getPixel(x, y);
            indiceValores += 1;
        }
        return valores;
    }
}
