/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author victor
 */
public class OwaUtileria {
    
    public static int catAptitud(double pixOwa){
        int categoria =0;
        double [] cortes = {0.0,0.2,0.4,0.6,0.8,1.0};
        for (int i = 0; i< cortes.length-1;i++){
              if(pixOwa>=cortes[i] && pixOwa<=cortes[i+1]){
                  categoria =i+1;
              }
            }
        
        return categoria;}
    
    public static double[] getPesos(FuncionValor [] capas){
        double[] pesos = new double[capas.length];
        int indicePesos = 0;
        for(FuncionValor capa: capas){
            pesos[indicePesos]=capa.getW();
            indicePesos +=1;
        }
        return pesos;
    }
    public static void imprimeLista(double[]lista){
        System.out.printf("\n");
        for(double v :lista){
            System.out.print(v+",");
            }
        System.out.println("************* ");
        }
    public static double[] getValores(FuncionValor [] capas,int x,int y){
        double[] valores = new double[capas.length];
        int indiceValores = 0;
        for(FuncionValor capa: capas){
            valores[indiceValores]=capa.iPixel(x, y);
            indiceValores +=1;
        }
        return valores;
    }
    
    public static void generaEscenarioAptitud(FuncionValor[] capas,double alpha, String pathSalida) throws IOException{
        int columnas = capas[0].getColumnas();
        int renglones = capas[0].getRenglones();
        BufferedImage resultado = new BufferedImage(columnas, renglones, BufferedImage.TYPE_USHORT_GRAY);
        WritableRaster rasterResultado = resultado.getRaster();
        
        for (int y = 0; y < renglones; y++) {
             for (int x = 0; x < columnas; x++){
                double[] listaValores = getValores(capas,x,y);
                double[] listaPesos = getPesos(capas);
                boolean existeNan = FuncionValor.contineNaN(listaValores);
                if (existeNan==true){
                  rasterResultado.setSample(x, y, 0, 0);
                  
                }else{
                  double pixOwa = PixelOwa.valorPixel(listaValores, listaPesos, alpha);
                  rasterResultado.setSample(x, y, 0, catAptitud(pixOwa));
                }
            }
        }
        
        File pathRasterTif = new File(pathSalida+BaseDatos.fechaFormat());
        if (pathRasterTif.exists()) {
            System.out.println("El archivo ya existe. No se va a sobrescribir.");
            }
            else{
                try {
                        ImageIO.write(resultado, "tif", pathRasterTif);
                    }catch(IOException e){
                        e.printStackTrace();
                        }
                }
    
    }
         
    
    
}
