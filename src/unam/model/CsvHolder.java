package unam.model;

public class CsvHolder {
    String nombre;
    Double peso;
    String ruta;

    public CsvHolder(String nombre, Double peso, String ruta) {
        this.nombre = nombre;
        this.peso = peso;
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
