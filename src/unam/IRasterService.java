package unam;

import java.io.IOException;

import utils.FuncionValor2;

public interface IRasterService {
    public FuncionValor2[] getFuncionesValorDeRuta(String ruta) throws IOException;
}