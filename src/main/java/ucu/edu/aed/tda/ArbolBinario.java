package ucu.edu.aed.tda;

import java.util.function.Consumer;

public class ArbolBinario<T> implements TDAArbolBinario<T> {

    private TDAElemento<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    @Override
    public T buscar(Comparable<T> predicate) {

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
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        
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
    public boolean insertar(Comparable<T> dato) {
            if (dato==null) {
                return false;
            }else{}
            
                return raiz.insertar(dato);
    }

    @Override
    public void inOrder(Consumer<T> consumer) {
        if (raiz != null){
        raiz.inOrder(nodo -> consumer.accept(nodo.getDato()));
    }
    }

    @Override
    public void preOrder(Consumer<T> consumer) {
        if (raiz != null){
        raiz.preOrder(nodo -> consumer.accept(nodo.getDato()));
        }
    }

    @Override
    public void postOrder(Consumer<T> consumer) {
        if (raiz != null){
        raiz.postOrder(nodo -> consumer.accept(nodo.getDato()));
    }
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
}
