package unam;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import unam.model.FuncionValorModel;

import java.io.IOException;
import java.awt.Rectangle;

public class FuncionValor {
    private int renglones;
    private int columnas;
    private int bandaDeColor;
    private Rectangle extension;
    private Raster raster;
    private BufferedImage image;
    private FuncionValorModel model;

    private IImageReaderService imageReaderService;

    public FuncionValor(FuncionValorModel rFuncionValorModel, ImageReaderService imageReaderService) {
        model = rFuncionValorModel;
        this.imageReaderService = imageReaderService;
        initialize();
    }

    public int getRenglones() {
        return renglones;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getBandaDeColor() {
        return bandaDeColor;
    }

    public Raster getRaster() {
        return raster;
    }

    public Rectangle getExtension() {
        return extension;
    }

    public FuncionValorModel getModel() {
        return model;
    }

    public void setModel(FuncionValorModel row) {
        this.model = row;
    }

    public float getPixel(int x, int y, int band) {
        return this.raster.getSampleFloat(x, y, band);
    }

    private void initialize() {
        try {
            this.image = imageReaderService.readImage(model.getRuta());
            this.raster = this.image.getData();

            this.renglones = this.raster.getHeight();
            this.columnas = this.raster.getWidth();
            this.bandaDeColor = this.raster.getNumBands();
            this.extension = this.raster.getBounds();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
