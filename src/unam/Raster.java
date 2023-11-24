package unam;

import java.io.IOException;
import java.util.List;

import unam.model.FuncionValor;

public class Raster implements IRaster {
    List<FuncionValor> funcionValores;
    IFileReader fileReader;
    IImageReader imageReader;

    public Raster(IFileReader fileReader, ImageReader imageReader) throws IOException {
        this.fileReader = fileReader;
        this.imageReader = imageReader;

        setFuncionDeValores();
    }

    private void setFuncionDeValores() throws IOException {
        var datosCsv = fileReader.getRows("porciono_avicola.csv");
        funcionValores = List.of();

        for (String lineaCsv : datosCsv) {
            var row = lineaCsv.split(",");
            funcionValores.add(new FuncionValor(row[0], Double.parseDouble(row[1]), row[2]));
        }
    }
}
