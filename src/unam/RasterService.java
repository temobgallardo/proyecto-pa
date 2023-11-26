package unam;

import java.io.IOException;
import java.util.ArrayList;
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
        if (ruta == null || ruta.trim().isEmpty()) {
            throw new IllegalArgumentException(ruta);
        }

        var datosCsv = fileReader.getRows(ruta);
        List<FuncionValor> funcionValores = new ArrayList<>();

        for (String lineaCsv : datosCsv) {
            var row = lineaCsv.split(",");
            FuncionValorModel model = new FuncionValorModel(row[0], Double.parseDouble(row[2]), row[1]);
            FuncionValor currentFuncionValor = new FuncionValor(model, (ImageReaderService) imageReader);
            funcionValores.add(currentFuncionValor);
        }

        return funcionValores.toArray(new FuncionValor[0]);
    }
}
