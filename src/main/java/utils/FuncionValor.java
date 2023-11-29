package utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victor
 */

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;


public class FuncionValor {
    private String nombre;
    private double w;
    private String rPath;
    private int renglones;
    private int columnas;
    private Rectangle extension;
    private Raster raster;
    private BufferedImage c_raster;

    public int getRenglones() {
        return renglones;
    }

    public int getColumnas() {
        return columnas;
    }
    
    
    public FuncionValor(String nombre, double w, String rPath){
        this.nombre = nombre;
        this.w = w;
        this.rPath=rPath;
        abrirRaster(this.rPath);
        }

    public String getNombre() {
        return nombre;
    }

    public double getW() {
        return w;
    }

    public Raster getRaster() {
        return raster;
    }
    
    public Rectangle getExtension() {
        return extension;
    }
    
    
    private void abrirRaster(String rPath){
        try{
            this.c_raster = ImageIO.read(new File(rPath));
            this.raster = this.c_raster.getData();
            
            this.renglones = this.raster.getHeight();
            this.columnas = this.raster.getWidth();
            this.extension = this.raster.getBounds();
            
           
        }catch(IOException e){
            e.printStackTrace();
                    }
        
    }

    public double iPixel(int x, int y){
        return  this.raster.getSampleDouble(x,y,0);
        

    }
    
    public static boolean contineNaN(double[]  list) {
        for (Double d : list) {
            if (Double.isNaN(d)) {
                return true;
            }
        }
        return false;
    }
    
    
    

    
   
   
}
