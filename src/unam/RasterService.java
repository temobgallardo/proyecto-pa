package unam;

import java.io.IOException;
import java.util.List;

import unam.model.CsvHolder;
import unam.utils.FuncionValor2;

public class RasterService implements IRasterService {
    List<CsvHolder> funcionValores;
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
        List<CsvHolder> otherFuncionesValor = List.of();

        for (String lineaCsv : datosCsv) {
            var row = lineaCsv.split(",");
            funcionValores.add(new CsvHolder(row[0], Double.parseDouble(row[1]), row[2]));
            otherFuncionesValor.add(new CsvHolder(row[0], Double.parseDouble(row[1]), row[2]));
        }

        return (FuncionValor2[]) otherFuncionesValor.toArray();
    }
}
