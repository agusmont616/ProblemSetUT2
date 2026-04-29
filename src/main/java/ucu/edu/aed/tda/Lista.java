package ucu.edu.aed.tda;

import java.util.Comparator;
import java.util.function.Predicate;

public class Lista<T> implements TDALista<T> {
    private Nodo<T> cabeza;
    private int tamanio;

    public Lista() { // Constructor
        this.cabeza = null;
        this.tamanio = 0;
    }

    public boolean indiceValido(int index) {
        // Método para verificar si un índice es válido para la lista
        return index >= 0 && index < tamanio;
    }



    @Override
    public void agregar(T elem) {
        
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (this.esVacio()) {   // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
            cabeza = nuevoNodo;
        } else {    // Si la lista no está vacía, se recorre hasta el final y se agrega el nuevo nodo
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) { // Recorre la lista hasta encontrar el último nodo
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamanio++;

    }

    @Override
    public void agregar(int index, T elem) {

            if (index < 0 || index > tamanio) { // permitimos index == tamanio (insertar al final)
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (index == 0) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            for (int i = 0; i < index - 1; i++) {
                actual = actual.getSiguiente();
            }
            nuevoNodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevoNodo);
        }
        tamanio++;
    }

    @Override
    public T obtener(int index) {
            if (indiceValido(index)){
                Nodo<T> actual = cabeza; // Se inicia desde la cabeza de la lista
                for (int i = 0; i < index; i++) { // Recorre la lista hasta llegar al índice deseado
                    actual = actual.getSiguiente();
                }
                return actual.getDato(); // Retorna el dato del nodo en el índice especificado
            } else {
                throw new IndexOutOfBoundsException("Índice fuera de rango");
            }
    }

    @Override
    public T remover(int index) {
        if (indiceValido(index)){
            Nodo<T> actual = cabeza; // Se inicia desde la cabeza de la lista
            T datoRemovido;
            if (index == 0) {
                datoRemovido = cabeza.getDato(); // Se guarda el dato antes de mover la cabeza
                cabeza = actual.getSiguiente(); // Si se remueve el nodo en la posición 0, la cabeza se actualiza al siguiente nodo 
            } else {
                for (int i = 0; i < index - 1; i++) {
                    actual = actual.getSiguiente(); // Recorre la lista hasta llegar a la posición anterior al índice deseado
                }
                datoRemovido = actual.getSiguiente().getDato(); // Se guarda el dato del nodo a remover
                actual.setSiguiente(actual.getSiguiente().getSiguiente()); // Se actualiza el puntero del nodo anterior para omitir el nodo a remover
            }
            tamanio--;  // Se decrementa el tamaño de la lista
            return datoRemovido;    // Retorna el dato del nodo removido
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
    }

    @Override
    public boolean remover(T elem) {
        if (elem != null) { // Verificación de elemento válido
            Nodo<T> actual = cabeza;
            Nodo<T> anterior = null;
            while (actual != null) {
                if (actual.getDato().equals(elem)) { // Si se encuentra el elemento a remover
                    if (anterior == null) { // Si el elemento a remover es la cabeza
                        cabeza = actual.getSiguiente();
                    } else { // Si el elemento a remover no es la cabeza
                        anterior.setSiguiente(actual.getSiguiente());
                    }
                    tamanio--;
                    return true; // Retorna true si se removió el elemento
                }
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }
        return false; // Retorna false si no se encontró el elemento a remover o si el elemento es null
    }

    @Override
    public boolean contiene(T elem) {
        Nodo<T> actual = cabeza; // Se inicia desde la cabeza de la lista
        while (actual != null) { // Recorre la lista hasta el final
            if (actual.getDato().equals(elem)) { // Si se encuentra el elemento, retorna true
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false; // Retorna false si no se encontró el elemento en la lista
    }

    @Override
    public int indiceDe(T elem) {
        Nodo<T> actual = cabeza; // Se inicia desde la cabeza de la lista
        int index = 0; // Variable para llevar el índice actual
        while (actual != null) { // Recorre la lista hasta el final
            if (actual.getDato().equals(elem)) { // Si se encuentra el elemento, retorna el índice
                return index;
            }
            actual = actual.getSiguiente();
            index++;
        }
        return -1; // Retorna -1 si no se encontró el elemento en la lista
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (criterio.test(actual.getDato())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparador) {
        Lista<T> ordenada = new Lista<>();
        for (int i = 0; i < tamanio(); i++) { // Se agregan todos los elementos
            ordenada.agregar(this.obtener(i));
        }

        for (int i = 0; i < ordenada.tamanio() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < ordenada.tamanio(); j++) {
                if (comparador.compare(ordenada.obtener(j), ordenada.obtener(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                T valorI = ordenada.obtener(i);
                T valorMin = ordenada.obtener(minIndex);

                ordenada.remover(minIndex);
                ordenada.agregar(minIndex, valorI);
                ordenada.remover(i);
                ordenada.agregar(i, valorMin);
            }
        }

        return ordenada; // Placeholder, debe retornar una nueva lista ordenada
    }

    @Override
    public int tamanio() {
        return tamanio;
    }

    @Override
    public boolean esVacio() {
        if (tamanio == 0) {
            return true; // Retorna true si el tamaño es 0, indicando que la lista está vacía
        }
        return false; // Retorna false si el tamaño no es 0, indicando que la lista no está vacía
    }

    @Override
    public void vaciar() {
        cabeza = null; // Se establece la cabeza a null para eliminar todas las referencias a los nodos
        tamanio = 0; // Se restablece el tamaño a 0
    }
}