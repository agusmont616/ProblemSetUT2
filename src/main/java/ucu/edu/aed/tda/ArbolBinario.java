package ucu.edu.aed.tda;

import java.util.function.Consumer;

public class ArbolBinario<T> implements TDAArbolBinario {

    private TDAElemento<T> raiz;
    private int cantidadNodos;

    public ArbolBinario() {
        this.raiz = null;
        this.cantidadNodos = 0;

    }

    @Override
    public T buscar(Comparable predicate) {

        TDAElemento<T> nodoActual = raiz;

        if (nodoActual == null) {
            return null;
        }

        return (T) nodoActual.buscar(predicate).getDato();
    }

    @Override
    public TDAElemento<T> obtenerRaiz() {
        return raiz;
    }

    @Override
    public boolean eliminar(Comparable criterioBusqueda) {
        
        if (raiz == null) {
            return false;
        } else if (raiz.getDato().equals(criterioBusqueda)) {
            raiz = null;
            return true;
        } else {
            TDAElemento<T> nodoBorrado = raiz.eliminar(criterioBusqueda);
            return nodoBorrado != null;
        }
    }

    @Override
    public boolean insertar(Comparable dato) {
            if (raiz == null) {
                raiz = new Elemento<>(dato);
                return true;
            } else {
                return raiz.insertar(dato);
            }
    }

    @Override
    public void inOrder(Consumer consumer) {
        raiz.inOrder(consumer);
    }

    @Override
    public void preOrder(Consumer consumer) {
        raiz.preOrder(consumer);
    }

    @Override
    public void postOrder(Consumer consumer) {
        raiz.postOrder(consumer);
    }

    @Override
    public boolean esVacio() {
        if (raiz == null) {
            return true;
        }
        return false;
    }

    @Override
    public int cantidadNodos() {
        return raiz.cantidadNodos();
    }

    @Override
    public int cantidadHojas() {
        return raiz.cantidadHojas();
    }

    @Override
    public int cantidadNodosInternos() {
        return raiz.cantidadNodosInternos();
    }

    public int calcularAltura() {
        return raiz.altura();
    }
    
    public int tamaño() {
        if (raiz == null) {
            return 0;
        }
        return raiz.tamaño();
    }
}
