package unam;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import unam.model.FuncionValorModel;

import java.io.IOException;
import java.awt.Rectangle;

public class FuncionValor {
    private int renglones;
    private int columnas;
    private Rectangle extension;
    private Raster raster;
    private BufferedImage image;
    private FuncionValorModel row;
    private IImageReaderService imageReaderService;

    public FuncionValor(FuncionValorModel rFuncionValorModel, ImageReaderService imageReaderService) {
        row = rFuncionValorModel;
        this.imageReaderService = imageReaderService;
        initialize();
    }

    public int getRenglones() {
        return renglones;
    }

    public int getColumnas() {
        return columnas;
    }

    public Raster getRaster() {
        return raster;
    }

    public Rectangle getExtension() {
        return extension;
    }

    private void initialize() {
        try {
            this.image = imageReaderService.readImage(row.getRuta());
            this.raster = this.image.getData();

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
