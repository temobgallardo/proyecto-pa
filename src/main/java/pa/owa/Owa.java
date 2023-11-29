/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pa.owa;
import java.io.File;
import java.io.IOException;
import utils.FuncionValor;
import utils.OwaUtileria;
import utils.BaseDatos;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author victor
 */
public class Owa {
    
    public static void main(String[] args) throws IOException {

        double tiempoTotal, tiempoSerial, tiempoConcurrente;
        double speedUp;
        boolean archivoEncontrado = false;
        String pathCSV = "";
        Date tiempoInicialTotal, tiempoFinalTotal, tiempoInicialSerial, tiempoFinalSerial, tiempoInicialConcurrente, tiempoFinalConcurrente;
       
        System.out.println("**********************************************************************");
        System.out.println("Equipo 01");
        System.out.println("Programación Avanzada 2024-1");
        System.out.println("Bienvenido al programa que genera escenarios de aptitud mediante OWA");
        System.out.println("**********************************************************************");
        System.out.println("Recordatorio: Asegurate de que el archivo CSV contenga la estructura y la ruta correcta de los archivo *,tif");
        
        Scanner entradaRuta = new Scanner(System.in);
        
        String nSector;
        
        while (!archivoEncontrado){
            try{
                System.out.print("Ingresa la ruta del archivo CSV: ");
                pathCSV = entradaRuta.nextLine();
                File pathBaseDatos = new File(pathCSV);
                if(pathBaseDatos.exists()){
                    archivoEncontrado=true;
                    }else{
                    System.out.print("El archivo CSV no se encuentra en la ruta mencionada ");
                    System.out.print("intenta de nuevo: ");
                    }
            }catch(Exception e){
                    entradaRuta.nextLine();}
        }

        System.out.print("Ingresa el nombre del sector productivo: ");
        nSector = entradaRuta.nextLine();
        tiempoInicialTotal = new Date();
        FuncionValor [] capas  = BaseDatos.listaCapas(pathCSV,nSector);
        double [] alphas = {0.0001,0.1,0.5,1.0,2.0,10.0,1000.0};
        int nAlphas = alphas.length;
        String[] pathAlphas = {nSector+"_aptitud_0001_at_least_one",
                               nSector+"_aptitud_01_at_least_a_few",
                               nSector+"_aptitud_05_a_few",
                               nSector+"_aptitud_10_half",
                               nSector+"_aptitud_20_most",
                               nSector+"_aptitud_100_almost_all",
                               nSector+"_aptitud_10000_all",
                                };
        
        tiempoInicialSerial = new Date() ;
        // SECUENCIAL
        
        
        for (int a = 0; a < nAlphas; a++) {
            OwaUtileria.generaEscenarioAptitud(capas,alphas[a],  "Resultados/sec_"+pathAlphas[a]);
            }
        
        
        tiempoFinalSerial = new Date();
        // MANAGER WORKER 
        Manager m = new Manager();
        tiempoInicialConcurrente = new Date();
        for (int a = 0; a < nAlphas; a++) {
            m.generaEscAptConcurrente(capas,alphas[a],"Resultados/hilos_"+pathAlphas[a]);
            }
        tiempoFinalConcurrente = new Date();
        
        tiempoFinalTotal = new Date();
        
        tiempoSerial = tiempoFinalSerial.getTime() - tiempoInicialSerial.getTime();
        tiempoConcurrente = tiempoFinalConcurrente.getTime() - tiempoInicialConcurrente.getTime();
        tiempoTotal = tiempoFinalTotal.getTime() - tiempoInicialTotal.getTime();

        speedUp = calcularSpeedUp(tiempoSerial, tiempoConcurrente);
        System.out.printf("\n Los escenarios de aptitud han sido generados \n y almacenados en la carpeta de Resultados");
        System.out.printf("\n Tiempo serial: %,.6f ms ", tiempoSerial);        
        System.out.printf("\n Tiempo concurrente: %,.6f ms ", tiempoConcurrente);
        System.out.printf("\n Tiempo total: %,.6f ms ", tiempoTotal);
        System.out.printf("\n SpeedUp: %.6f  \n\n", speedUp);
    }
    private static double calcularSpeedUp(double s, double p) {
        double r;
        r = s / p;
        return r;
    }
}// cierra Owa
