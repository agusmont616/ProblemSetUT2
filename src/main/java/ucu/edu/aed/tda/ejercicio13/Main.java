package ucu.edu.aed.tda.ejercicio13;

import java.io.*;

public class Main {
    
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();

        sistema.agregarNave(new Nave(10,  "Explorador", 0));
        sistema.agregarNave(new Nave(20,  "Destructor", 90));
        sistema.agregarNave(new Nave(30,  "Médica",     100));
        sistema.agregarNave(new Nave(40,  "Explorador", 50));
        sistema.agregarNave(new Nave(50,  "Carguero",   20));
        sistema.agregarNave(new Nave(60,  "Destructor", 28));
        sistema.agregarNave(new Nave(70,  "Explorador", 14));
        sistema.agregarNave(new Nave(80,  "Médica",     7));
        sistema.agregarNave(new Nave(90,  "Carguero",   23));
        sistema.agregarNave(new Nave(100, "Explorador", 26));
 
        System.out.println("=== Naves exploradoras ===");
        for (int i = 0; i < sistema.retornarExploradoras().tamanio(); i++) {
            System.out.println("  Código: " + sistema.retornarExploradoras().obtener(i));
        }
 
        System.out.println("\n=== Combustible promedio (exploradoras) ===");
        System.out.println ("  " + sistema.calcularPromedioCombustible());
    }
}