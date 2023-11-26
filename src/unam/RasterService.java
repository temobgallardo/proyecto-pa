package unam;

import java.io.IOException;
import java.util.List;

import unam.model.FuncionValorModel;

public class RasterService implements IRasterService {
    List<FuncionValorModel> funcionValores;
    IFileReaderService fileReader;
    IImageReaderService imageReader;

    public RasterService(unam.IFileReaderService fileReader, ImageReaderService imageReader) throws IOException {
        this.fileReader = fileReader;
        this.imageReader = imageReader;
    }

    @Override
    public FuncionValor[] getFuncionesValorDeRuta(String ruta) throws IOException {
        var datosCsv = fileReader.getRows(ruta);
        funcionValores = List.of();
        List<FuncionValorModel> otherFuncionesValor = List.of();

        for (String lineaCsv : datosCsv) {
            var row = lineaCsv.split(",");
            funcionValores.add(new FuncionValorModel(row[0], Double.parseDouble(row[1]), row[2]));
            otherFuncionesValor.add(new FuncionValorModel(row[0], Double.parseDouble(row[1]), row[2]));
        }

        return (FuncionValor[]) otherFuncionesValor.toArray();
    }
}
