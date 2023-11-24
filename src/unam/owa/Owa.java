/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pa.owa;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import javax.imageio.ImageIO;

import utils.funcionValor;
import utils.PixelOwa;
import java.awt.Rectangle;
import java.awt.Point;

/**
 *
 * @author victor
 */
public class Owa {
    public static String pDir = "/Users/victor/Downloads/funciones_valor/porcino_avicola/";
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        
        funcionValor fv1= new funcionValor("lineas electricas",0.046411428,pDir+"fv_por_bio_usv_cobertura_usv_svi_16cats_costa_manglar.tif");
        funcionValor fv2= new funcionValor("manto freatico",0.232140714,pDir+"fv_por_bio_agua_d_manto_freatico.tif");
        funcionValor fv3= new funcionValor("manto freatico",0.01156958,pDir+"fv_por_socio_loca_d_localidades_15k_exc.tif");
        funcionValor fv4= new funcionValor("manto freatico",0.05285302,pDir+"fv_por_socio_loca_d_localidades_2500_15k_exc.tif");
        funcionValor fv5= new funcionValor("manto freatico",0.03016794,pDir+"fv_por_socio_loca_d_localidades_2500_exc.tif");
        funcionValor fv6= new funcionValor("manto freatico",0.26526176,pDir+"fv_por_infra_cam_d_carretera_prolongacion.tif");
        funcionValor fv7= new funcionValor("manto freatico",0.06631544,pDir+"fv_por_infra_cam_d_caminos_veredas.tif");
        funcionValor fv8= new funcionValor("manto freatico",0.087476208,pDir+"fv_por_infra_tif_d_rastros_porcinos_avicolas.tif");
        funcionValor fv9= new funcionValor("manto freatico",0.16102492,pDir+"fv_por_infra_elec_d_lineas_electricas.tif");
        funcionValor fv10= new funcionValor("manto freatico",0.046941052,pDir+"fv_por_infra_puer_d_puerto_progreso_con_carreteras.tif");
        funcionValor [] capas = {fv1,fv2,fv3,fv4,fv5,fv6,fv7,fv8,fv9,fv10};
        
        double[] listaPesos = getPesos(capas);
        
        for (double peso:listaPesos){
            System.out.println(peso);
            }
        
        int columnas = fv1.getColumnas();
        int renglones = fv2.getRenglones();
        Rectangle bounds = fv1.getExtension();
        double nulo = Double.NaN;
        BufferedImage resultado = new BufferedImage(columnas, renglones, BufferedImage.TYPE_USHORT_GRAY);
        WritableRaster rasterResultado = resultado.getRaster();
        
        for (int x = 0; x < columnas; x++) {
            for (int y = 0; y < renglones; y++) {
                
                double[] listaValores = getValores(capas,x,y);
                double pixOwa = PixelOwa.valorPixel(listaValores, listaPesos, 0.5);
                
               //float pixel = fv1.iPixel(x, y);
              //outputRaster.setPixel(x, y, pixel);
              if (Double.isNaN(listaValores[0])){
                rasterResultado.setSample(x, y, 0, nulo);
              }else{
                rasterResultado.setSample(x, y, 0, (pixOwa*1000));
              }
            /*
            if(x%100==0){
                System.out.printf("Columna "+x);
            }*/
              
              
              
            }
        }
        
        ImageIO.write(resultado, "tif", new File("/Users/victor/Downloads/funciones_valor/output_05.tif"));
        /*
        
        //Raster outputRaster = new Raster(columnas,renglones,fv1.getW());
        
        
        for(funcionValor capa:capas){
            double pixel = capa.iPixel(1000, 1000);
            System.out.println(pixel);
            System.out.println(capa.getNombre());
            }
        
        
        
        
        double pixel = fv1.iPixel(0, 0);
        System.out.print(pixel);
        System.out.print(fv1.getNombre());
        
        funcionValor capa1 = new funcionValor();
        capa1.abrirRaster("/Users/victor/Downloads/funciones_valor/porcino_avicola/fv_por_infra_elec_d_lineas_electricas.tif");
        capa1.datos();
        double pixel = capa1.iPixel(1000, 1000);
        System.out.print(pixel);
        
        
        try {
            BufferedImage image = ImageIO.read(new File("/Users/victor/Downloads/funciones_valor/porcino_avicola/fv_por_infra_elec_d_lineas_electricas.tif"));
            Raster raster = image.getData();
            // Obtener el ancho y alto de la imagen
            int width = raster.getWidth();
            int height = raster.getHeight();
            double[][] pixelValues = new double[width][height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Obtener el valor del píxel en la posición (x, y)
                    pixelValues[x][y] = raster.getSampleFloat(x, y, 0);
                    
                    //System.out.println("Pixel en (" + x + ", " + y + "): " + pixelValues[x][y]);
                }
            }
            System.out.print(pixelValues[1000][1000]);
        }catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
    
    public static double[] getPesos(funcionValor [] capas){
        double[] pesos = new double[capas.length];
        int indicePesos = 0;
        for(funcionValor capa: capas){
            pesos[indicePesos]=capa.getW();
            indicePesos +=1;
        }
        return pesos;
    }
    
    public static double[] getValores(funcionValor [] capas,int x,int y){
        double[] valores = new double[capas.length];
        int indiceValores = 0;
        for(funcionValor capa: capas){
            valores[indiceValores]=capa.iPixel(x, y);
            indiceValores +=1;
        }
        return valores;
    }
}
