/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa.owa;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import utils.BaseDatos;
import utils.FuncionValor;

/**
 *
 * @author victor
 */
public class Manager {
    private Thread[] poolWorkers;
    public synchronized void generaEscAptConcurrente(FuncionValor [] capas,double alpha, String pathSalida) throws IOException{
        int filas = capas[0].getRenglones();
        int columnas = capas[0].getColumnas();
        BufferedImage resultado = new BufferedImage(columnas, filas, BufferedImage.TYPE_USHORT_GRAY);
        WritableRaster rasterResultado = resultado.getRaster();
        this.poolWorkers = new Thread[filas];
        for(int x=0;x<filas;x++){
            this.poolWorkers[x]=new Thread(new Worker(capas,alpha,x,rasterResultado));
            this.poolWorkers[x].start();
        }
        for(int x=0;x<filas;x++){
            try {
                this.poolWorkers[x].join();
            } catch (InterruptedException e) {
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

    

