package ucu.edu.aed.tda;

public class Nodo<T> { // Clase Nodo para la implementación de la lista enlazada

    private T dato;
    private Nodo<T> siguiente;

    public Nodo(T valor) { // Constructor para inicializar el nodo con un valor y el siguiente nodo como null
        this.dato = valor;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T valor) {
        this.dato = valor;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
    
}