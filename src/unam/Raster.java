package unam;

import java.io.IOException;
import java.util.List;

import unam.model.FuncionValor;

public class Raster implements IRaster {
    List<FuncionValor> funcionValores;
    IFileReader fileReader;

    public Raster(IFileReader fileReader) throws IOException {
        this.fileReader = fileReader;

        setFuncionDeValores();
    }

    private void setFuncionDeValores() throws IOException {
        var datosCsv = fileReader.getRows("porciono_avicola.csv");

        for (String lineaCsv : datosCsv) {
            var row = lineaCsv.split(",");
            funcionValores.add(new FuncionValor(row[0], Double.parseDouble(row[1]), row[2]));
        }
    }

}
