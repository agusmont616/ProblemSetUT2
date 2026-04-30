package ucu.edu.aed.tda.ejercicio13;

import ucu.edu.aed.tda.ArbolAVL;
import ucu.edu.aed.tda.Lista;

public class SistemaGestion {
    private ArbolAVL<Nave> registro;

    public SistemaGestion() {
        this.registro = new ArbolAVL<Nave>();
    }

    public void agregarNave(Nave nave) { 
        registro.insertar((Comparable<Nave>) nave); // usa la implementación del arbolAVL
    }

    public Lista<Integer> retornarExploradoras() {
        Lista<Integer> exploradoras = new Lista<>();
        registro.inOrder(nave -> {                      // uso de función lambda "->"
            if (nave.getClase() == "Explorador") {
                exploradoras.agregar(nave.getCodigo());
            }
        });

        return exploradoras;
    }

    public float calcularPromedioCombustible() {
        final int[] suma = new int[] { 0 };
        float totalNaves = (float) registro.cantidadNodos();
        
        registro.inOrder(nave -> {  //uso funcion lamda
            suma[0] += nave.getCombustibleRestante();
        });

        return suma[0] / totalNaves;

    }
}
