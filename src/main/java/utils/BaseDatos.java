/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author victor
 */
public class BaseDatos {
    
    private String pathCsv;
    private String nombreSector;
    
   
    
    
    public static FuncionValor[] listaCapas(String pathCsv, String nombreSector){
        String linea;
        String [] campos;
        int n_capas = contarCapas(pathCsv);
        FuncionValor[] capas = new FuncionValor[n_capas];
        dirResultados();
        int i = 0;
        try{
            BufferedReader baseDatosFv = new BufferedReader(new FileReader(pathCsv));
            while ((linea = baseDatosFv.readLine()) != null) {
                campos = linea.split(",");  //  
                if(!"nombre".equals(campos[0]) && !"ruta".equals(campos[1]) && !"w".equals(campos[2])){
                    String nombre = campos[0];
                    String ruta = campos[1];
                    double peso = Double.parseDouble(campos[2]);
                    capas[i]=new FuncionValor(nombre,peso,ruta);
                    i++;
                }
            }baseDatosFv.close();
        }catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return capas;}
    
    private static int contarCapas(String pathCsv){
        int i = 0;
        try{
            BufferedReader baseDatosFv = new BufferedReader(new FileReader(pathCsv));
            while (baseDatosFv.readLine() != null) {
                i++;
            }
            baseDatosFv.close();
        }catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return i-1;
    }
    
    public static void dirResultados(){
        File dir = new File("Resultados");

        if (!dir.exists()) {
            try {
                dir.mkdir();
                System.out.println("Directorio 'Resultados' creado");
            } catch (SecurityException e) {
                System.out.println("No se pudo crear el directorio 'Resultados' debido a restricciones de seguridad");
            }
        } else {
            System.out.println("Las capas se almacenaran en el directorio 'Resultados' ");
        }
        }
    public static String fechaFormat(){
        Date meta = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd HHmm");
        String formatoFecha = "_filtered("+formato.format(meta)+").tif";

        return formatoFecha;}
    
    
}
