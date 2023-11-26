package unam;

import java.io.IOException;
import java.util.List;

import unam.model.FuncionValor;
import utils.FuncionValor2;

public class RasterService implements IRasterService {
    List<FuncionValor> funcionValores;
    IFileReaderService fileReader;
    IImageReaderService imageReader;

    public RasterService(unam.IFileReaderService fileReader, ImageReaderService imageReader) throws IOException {
        this.fileReader = fileReader;
        this.imageReader = imageReader;
    }

    @Override
    public FuncionValor2[] getFuncionesValorDeRuta(String ruta) throws IOException {
        var datosCsv = fileReader.getRows(ruta);
        funcionValores = List.of();
        List<FuncionValor> otherFuncionesValor = List.of();

        for (String lineaCsv : datosCsv) {
            var row = lineaCsv.split(",");
            funcionValores.add(new FuncionValor(row[0], Double.parseDouble(row[1]), row[2]));
            otherFuncionesValor.add(new FuncionValor(row[0], Double.parseDouble(row[1]), row[2]));
        }

        return (FuncionValor2[]) otherFuncionesValor.toArray();
    }
}
