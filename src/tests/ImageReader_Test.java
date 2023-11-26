package tests;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import unam.FilReaderService;
import unam.IFileReaderService;
import unam.IImageReaderService;
import unam.ImageReaderService;
import unam.model.FuncionValorModel;

public class ImageReader_Test {
    @Test
    public void readImage_test() {
        IImageReaderService suv = new ImageReaderService();

        try {
            java.util.List<FuncionValorModel> funcionValores = setFuncionDeValores();
            BufferedImage image = suv.readImage(funcionValores.get(0).getRuta());
            Assert.assertNotNull(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readImage_throw_exception_wrong_path() {
        IImageReaderService suv = new ImageReaderService();

        Assert.assertThrows(IOException.class, () -> suv.readImage(""));
    }

    private java.util.List<FuncionValorModel> setFuncionDeValores() throws IOException {
        IFileReaderService fr = new FilReaderService();
        String[] datosCsv = fr.getRows("porcino_avicola.csv");
        java.util.List<FuncionValorModel> funcionValores = java.util.List.of();

        for (String lineaCsv : datosCsv) {
            var row = lineaCsv.split(",");
            funcionValores.add(new FuncionValorModel(row[0], Double.parseDouble(row[1]), row[2]));
        }

        return funcionValores;
    }
}
