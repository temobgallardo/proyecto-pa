package unam.utils;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class FuncionValor2 {
    private String nombre;
    private double peso;
    private String filePath;
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

    public FuncionValor2(String nombre, double w, String rPath) {
        this.nombre = nombre;
        this.peso = w;
        this.filePath = rPath;
        abrirRaster(this.filePath);
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public Raster getRaster() {
        return raster;
    }

    public Rectangle getExtension() {
        return extension;
    }

    private void abrirRaster(String rPath) {
        try {
            this.c_raster = ImageIO.read(new File(rPath));
            this.raster = this.c_raster.getData();

            this.renglones = this.raster.getHeight();
            this.columnas = this.raster.getWidth();
            this.extension = this.raster.getBounds();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public float getPixel(int x, int y) {

        return this.raster.getSampleFloat(x, y, 0);
    }

}
