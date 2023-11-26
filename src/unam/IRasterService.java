package unam;

import java.io.IOException;

public interface IRasterService {
    public FuncionValor[] getFuncionesValorDeRuta(String ruta) throws IOException;
}