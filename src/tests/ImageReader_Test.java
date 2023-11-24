package tests;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import unam.FilReader;
import unam.IFileReader;
import unam.IImageReader;
import unam.ImageReader;
import unam.model.FuncionValor;

public class ImageReader_Test {
    @Test
    public void readImage_test() {
        IImageReader suv = new ImageReader();

        try {
            java.util.List<FuncionValor> funcionValores = setFuncionDeValores();
            BufferedImage image = suv.readImage(funcionValores.get(0).getRuta());
            Assert.assertNotNull(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private java.util.List<FuncionValor> setFuncionDeValores() throws IOException {
        IFileReader fr = new FilReader();
        var datosCsv = fr.getRows("porciono_avicola.csv");
        java.util.List<FuncionValor> funcionValores = java.util.List.of();

        for (String lineaCsv : datosCsv) {
            var row = lineaCsv.split(",");
            funcionValores.add(new FuncionValor(row[0], Double.parseDouble(row[1]), row[2]));
        }

        return funcionValores;
    }
}