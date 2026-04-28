package ucu.edu.aed.tda;

import java.util.function.Consumer;
import java.lang.Comparable;

public class ArbolAVL<T> extends ArbolBinario<T> {

    // Métodos auxiliares AVL

        private int altura(TDAElemento<T> nodo) {
            if (nodo == null) {
                return 0;
            }
            return nodo.altura();
        }

        private int calcularFactorBalance(TDAElemento<T> nodo) {
            if (nodo == null) {
                return 0;
            }
            return altura(nodo.getHijoIzquierdo()) - altura(nodo.getHijoDerecho());
        }

        // Rotaciones 

        private TDAElemento<T> rotarDerecha(TDAElemento<T> nodoDesb) {
            TDAElemento<T> nuevaRaiz = nodoDesb.getHijoIzquierdo();
            TDAElemento<T> subArbolCentral = nuevaRaiz.getHijoDerecho();

            nuevaRaiz.setHijoDerecho(nodoDesb);
            nodoDesb.setHijoIzquierdo(subArbolCentral);

            return nuevaRaiz;
        }

        private TDAElemento<T> rotarIzquierda(TDAElemento<T> nodoDesb) {
            TDAElemento<T> nuevaRaiz = nodoDesb.getHijoDerecho();
            TDAElemento<T> subArbolCentral = nuevaRaiz.getHijoIzquierdo();

            nuevaRaiz.setHijoIzquierdo(nodoDesb);
            nodoDesb.setHijoDerecho(subArbolCentral);

            return nuevaRaiz;
        }

        // Insertar balanceado

        private TDAElemento<T> insertarBalanceado(TDAElemento<T> nodoActual, Comparable<T> datoNuevo) {
            
            // Caso base
            if (nodoActual == null) {
                return new Elemento<>((T) datoNuevo);
            }

            int comparacion = datoNuevo.compareTo(nodoActual.getDato());

            if (comparacion < 0) { // datoNuevo es menor, va a la izquierda
                nodoActual.setHijoIzquierdo(
                    insertarBalanceado(nodoActual.getHijoIzquierdo(), datoNuevo));
            } else if (comparacion > 0) { // datoNuevo es mayot, va a la derecha
                nodoActual.setHijoDerecho(
                    insertarBalanceado(nodoActual.getHijoDerecho(), datoNuevo));
            } else {
                return nodoActual; // datoNuevo ya existe en el árbol
            }

            int factorBalance = calcularFactorBalance(nodoActual); // para verificar el balance del arbol

            // caso LL

            boolean desbalanceLL = factorBalance < 1 && 
            datoNuevo.compareTo(nodoActual.getHijoIzquierdo().getDato()) < 0;

            if (desbalanceLL) {
                return rotarDerecha(nodoActual);
            }

            // caso RR

            boolean desbalanceRR = factorBalance > 1 &&
            datoNuevo.compareTo(nodoActual.getHijoDerecho().getDato()) > 0;

            if (desbalanceRR) {
                return rotarIzquierda(nodoActual);
            }
            
            // caso LR

            boolean desbalanceLR = 
            factorBalance > 1 &&
            datoNuevo.compareTo(nodoActual.getHijoIzquierdo().getDato()) < 0;

            if (desbalanceLR) {
                nodoActual.setHijoIzquierdo(rotarIzquierda(nodoActual.getHijoIzquierdo()));
                return rotarDerecha(nodoActual);
            }

            // caso RL

            boolean desbalanceRL =
            factorBalance < -1 &&
            datoNuevo.compareTo(nodoActual.getHijoDerecho().getDato()) > 0;

            if (desbalanceRL) {
                nodoActual.setHijoIzquierdo(rotarDerecha(nodoActual.getHijoDerecho()));
                return rotarIzquierda(nodoActual);
            }

            // sin desbalance

            return nodoActual;
        }

        @Override // compara la cantidad de nodos que hay previo a la insersión con la cantidad que hay después
        public boolean insertar(Comparable datoNuevo) {
            int nodosAntes = esVacio() ? 0 : obtenerRaiz().cantidadNodos();
            TDAElemento<T> nuevaRaiz = insertarBalanceado(obtenerRaiz(), datoNuevo);
            setRaiz(nuevaRaiz);
            int nodosDespues = nuevaRaiz.cantidadNodos();

            return nodosDespues > nodosAntes;

        }
    
        // Eliminar balanceado

        private TDAElemento<T> encontrarSucesorInOrder(TDAElemento<T> nodo) {
            TDAElemento<T> actual = nodo;
            while (actual.getHijoIzquierdo() != null) {
                actual = actual.getHijoIzquierdo();
            }
            return actual;
        }

        private TDAElemento<T> eliminarAVL(TDAElemento<T> nodoActual, Comparable<T> criterio) {
            if (nodoActual == null) {
                return null;
            }

            int comparacion = criterio.compareTo(nodoActual.getDato());

            if (comparacion < 0) {
                nodoActual.setHijoIzquierdo(
                    eliminarAVL(nodoActual.getHijoIzquierdo(), criterio)
                );
            } else if (comparacion > 0) {
                nodoActual.setHijoDerecho(
                    eliminarAVL(nodoActual.getHijoDerecho(), criterio)
                );
            } else { // nodo encontrado. eliminar abb
                boolean tieneUnSoloHijoONinguno = nodoActual.getHijoIzquierdo() == null || nodoActual.getHijoDerecho() == null;


                if (tieneUnSoloHijoONinguno) { // el nodo se reemplaza por su único hijo o por null si es hoja
                    nodoActual = (nodoActual.getHijoIzquierdo() != null) ? nodoActual.getHijoIzquierdo() : nodoActual.getHijoDerecho();

                } else {
                    // si tiene dos hijos: reemplazar con el sucesor inorden (inmediato superior)
                    TDAElemento<T> sucesor = encontrarSucesorInOrder(nodoActual.getHijoDerecho());
                    nodoActual.setDato(sucesor.getDato());

                    // eliminar ele sucesor del subárbol derecho (tiene máximo un hijo)
                    nodoActual.setHijoDerecho(
                        eliminarAVL(nodoActual.getHijoDerecho(), (Comparable<T>) sucesor.getDato())
                    );
                }
            }

            // si el subarbol quedó vacío, no hay que balancear
            if (nodoActual == null) {
                return null;
            }

            // rebalanceo luego de la recursión
            int factorBalance = calcularFactorBalance(nodoActual);

            // caso LL
            if (factorBalance > 1 && calcularFactorBalance(nodoActual.getHijoIzquierdo()) >= 0) {
                return rotarDerecha(nodoActual);
            }
            // caso RR
            if (factorBalance < -1 && calcularFactorBalance(nodoActual.getHijoDerecho()) >= 0) {
                return rotarIzquierda(nodoActual);
            }
            // caso LR
            if (factorBalance > 1 && calcularFactorBalance(nodoActual.getHijoIzquierdo()) < 0) {
                nodoActual.setHijoDerecho(rotarIzquierda(nodoActual.getHijoIzquierdo()));
                return rotarDerecha(nodoActual);
            }
            // caso RL
            if (factorBalance < -1 && calcularFactorBalance(nodoActual.getHijoDerecho()) >=0) {
                nodoActual.setHijoDerecho(rotarDerecha(nodoActual.getHijoDerecho()));
                return rotarIzquierda(nodoActual);
            }

            return nodoActual;

        }

        @Override
        public boolean eliminar(Comparable criterio) {
            if (esVacio()){
                return false;
            }

            int nodosAntes = obtenerRaiz().cantidadNodos();
            TDAElemento<T> nuevaRaiz = eliminarAVL(obtenerRaiz(), criterio);
            setRaiz(nuevaRaiz);
            int nodosDespues = esVacio() ? 0 : nuevaRaiz.cantidadNodos();
            return nodosDespues < nodosAntes;
        }
}
