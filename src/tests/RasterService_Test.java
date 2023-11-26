package tests;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import unam.FilReaderService;
import unam.FuncionValor;
import unam.ImageReaderService;
import unam.RasterService;

public class RasterService_Test {
    @Test
    public void getFuncionesValorDeRuta_test() {
        try {
            String csvPath = "porcino_avicola.csv";
            RasterService sut = new RasterService(new FilReaderService(), new ImageReaderService());
            FuncionValor[] actualResult = sut.getFuncionesValorDeRuta(csvPath);
            Assert.assertEquals(10, actualResult.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFuncionesValorDeRuta_throw_ioexception_test() {
        try {
            String csvPath = "";
            RasterService sut = new RasterService(new FilReaderService(), new ImageReaderService());
            Assert.assertThrows(IOException.class, () -> sut.getFuncionesValorDeRuta(csvPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFuncionesValorDeRuta_throw_ioexception2_test() {
        try {
            RasterService sut = new RasterService(new FilReaderService(), new ImageReaderService());
            Assert.assertThrows(IllegalArgumentException.class, () -> sut.getFuncionesValorDeRuta(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
