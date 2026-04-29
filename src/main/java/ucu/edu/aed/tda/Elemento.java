package ucu.edu.aed.tda;

import java.util.function.Consumer;

public class Elemento<T> implements TDAElemento<T> {
    private TDAElemento<T> hijoIzq;
    private TDAElemento<T> hijoDer;
    private T dato;

    public Elemento(T datoT){
        this.hijoDer=null;
        this.hijoIzq=null;
        this.dato=datoT;
    }

    public void setHijoDerecho(TDAElemento<T> hijoDer){
        this.hijoDer=hijoDer;
    }

    public void setHijoIzquierdo(TDAElemento<T> hijoIzq){
        this.hijoIzq=hijoIzq;
    }

    public void setDato(T dato){
        this.dato=dato;
    }

    public TDAElemento<T> getHijoDerecho(){
        return this.hijoDer;
    }

    public TDAElemento<T> getHijoIzquierdo(){
        return this.hijoIzq;
    }

    public T getDato(){
        return this.dato;
    }

    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda){
        Elemento<T> actual=this;

        if (criterioBusqueda.compareTo(actual.getDato())==0){
            return actual;
        }else if (criterioBusqueda.compareTo(actual.getDato())<0) {
            if (actual.getHijoIzquierdo()==null) {
                return null;
            }
            return actual.getHijoIzquierdo().buscar(criterioBusqueda);
        }else if (criterioBusqueda.compareTo(actual.getDato())>0) {
            if (actual.getHijoDerecho()==null){
                return null;
            }
            return actual.getHijoDerecho().buscar(criterioBusqueda);
        }else{
        return null;
        }
    }

    public boolean insertar(Comparable<T> nuevoDato){
        Elemento<T> actual=this;
        T datoNuevo = (T) nuevoDato;
        Elemento<T> nuevoNodo = new Elemento<T>(datoNuevo);
        if (nuevoDato.compareTo(actual.getDato())==0) {
            return false;
        }else if (nuevoDato.compareTo(actual.getDato())<0){
            if (actual.getHijoIzquierdo()==null) {
                actual.setHijoIzquierdo(nuevoNodo);
                return true;
            }
            return actual.getHijoIzquierdo().insertar(nuevoDato);
        }else if (nuevoDato.compareTo(actual.getDato())>0){
            if(actual.getHijoDerecho()==null){
                actual.setHijoDerecho(nuevoNodo);
                return true;
            }
            return actual.getHijoDerecho().insertar(nuevoDato);
        }else{
        return true;
        }
    }

    public boolean esHoja(){
        Elemento<T> actual=this;
        if (actual.getHijoDerecho()==null && actual.getHijoIzquierdo()==null) {
            return true;
        }else{
            return false;
        }
    }

    public int cantidadHojas(){
        Elemento<T> actual = this;
        if (actual.esHoja()) {
            return 1;
        }
        int hojas = 0;
        if (actual.getHijoIzquierdo() != null) {
            hojas += actual.getHijoIzquierdo().cantidadHojas();
        }if (actual.getHijoDerecho() != null) {
            hojas += actual.getHijoDerecho().cantidadHojas();
        }
        return hojas;
    }

    public int cantidadNodosInternos(){
        Elemento<T> actual=this;
        if (actual.esHoja()) {
            return 0;
        }
        int nodosIN = 0;
        if (actual.getHijoIzquierdo() != null) {
            nodosIN += actual.getHijoIzquierdo().cantidadNodosInternos();
        }if (actual.getHijoDerecho() != null) {
            nodosIN += actual.getHijoDerecho().cantidadNodosInternos();
        }
        return nodosIN+1;
    }

    public int cantidadNodos(){
        Elemento<T> actual=this;
        int nodos = 0;
        if (actual.getHijoIzquierdo() != null) {
            nodos += actual.getHijoIzquierdo().cantidadNodos();
        }if (actual.getHijoDerecho() != null) {
            nodos += actual.getHijoDerecho().cantidadNodos();
        }
        return nodos+1;
    }
    
    public int altura(){
        Elemento<T> actual=this;
        int alturaIzq = 0;
        int alturaDer=0;
        if (actual.getHijoIzquierdo() != null) {
            alturaIzq += actual.getHijoIzquierdo().altura();
        }if (actual.getHijoDerecho() != null) {
            alturaDer += actual.getHijoDerecho().altura();
        }
        return 1+Math.max(alturaDer,alturaIzq);
    }

    public int tamaño() {
        Elemento<T> actual = this;
        int tamañoIzq = 0;
        int tamañoDer = 0;

        if (actual.getHijoIzquierdo() != null) {
            tamañoIzq = actual.getHijoIzquierdo().tamaño();
        }

        if (actual.getHijoDerecho() != null) {
            tamañoDer = actual.getHijoDerecho().tamaño();
        }
        return 1 + tamañoIzq + tamañoDer;
}

    public int obtenerNivel(Comparable<T> criterioBusqueda){
        Elemento<T> actual=this;
        if(criterioBusqueda.compareTo(actual.dato)==0){
            return 0;
        }
        int nivel=0;
        if(criterioBusqueda.compareTo(actual.dato)<0){
            if (actual.getHijoIzquierdo()==null) {
                return-1;
            }
            nivel +=actual.getHijoIzquierdo().obtenerNivel(criterioBusqueda);
            if (nivel==-1) {
                return -1;
            }else{
                return nivel+1;
            }
        }else if(criterioBusqueda.compareTo(actual.dato)>0){
            if (actual.getHijoDerecho()==null) {
                return -1;
            }
            nivel +=actual.getHijoDerecho().obtenerNivel(criterioBusqueda);
            if (nivel==-1){
                return-1;
            }else{
                return nivel+1;
            }
        }else{
        return nivel;
        }
    }

    public TDAElemento<T> eliminar(Comparable<T> criterio){
          int comparar = criterio.compareTo(this.dato);
        TDAElemento<T> actual=this;
        if (comparar<0){//Buscamos el nodo a borrar
            if (actual.getHijoIzquierdo()!=null){
                return actual.getHijoIzquierdo().eliminar(criterio);
            }
            return null;
        }else if (comparar>0){
            if (actual.getHijoDerecho()!=null){
                return actual.getHijoDerecho().eliminar(criterio);
            }
            return null;
        }else{//Lo encontramos
            if (actual.esHoja()) {
                actual=null;
            }else{
                TDAElemento<T> sustituto=actual;
                if (sustituto.getHijoDerecho()!=null) {//buscar en el nodo que encontramos el sustituo
                    sustituto=sustituto.getHijoDerecho();
                    while (sustituto.getHijoIzquierdo()!=null) {
                        sustituto=sustituto.getHijoIzquierdo();
                    }
                }else{
                    if (sustituto.getHijoIzquierdo()!=null) {
                        sustituto=sustituto.getHijoIzquierdo();              
                    }
                }
                actual.setDato(sustituto.getDato());
                sustituto.eliminar((Comparable<T>)sustituto.getDato());
            }            
            return this;
        }
    }

    public TDALista<Elemento<T>> completos() {
        Elemento<T> actual = this;
        Lista<Elemento<T>> lista = new Lista<>();

        // si el nodo actual tiene ambos hijos, lo agregamos
        if (actual.getHijoIzquierdo() != null && actual.getHijoDerecho() != null) {
            lista.agregar(actual);
        }

        // recorrer subárbol izquierdo
        if (actual.getHijoIzquierdo() != null) {
            TDALista<Elemento<T>> listaIzq = actual.getHijoIzquierdo().completos();

            for (int i = 0; i < listaIzq.tamanio(); i++) {
                lista.agregar(listaIzq.obtener(i));
            }
        }

        // recorrer subárbol derecho
        if (actual.getHijoDerecho() != null) {
            TDALista<Elemento<T>> listaDer = actual.getHijoDerecho().completos();

            for (int i = 0; i < listaDer.tamanio(); i++) {
                lista.agregar(listaDer.obtener(i));
            }
        }

        return lista;
    }
    
    public TDALista<Elemento<T>> enNivel(int nivel) {
        Elemento<T> actual = this;
        Lista<Elemento<T>> lista = new Lista<>();

        // Caso base: si el nivel es 1, agregamos el nodo actual a la lista
        if (nivel == 1) {
            lista.agregar(actual);
            return lista;
        }
        
        // Recorrer subárbol izquierdo
        if (actual.getHijoIzquierdo() != null) {
            TDALista<Elemento<T>> listaIzq =
                actual.getHijoIzquierdo().enNivel(nivel - 1);

            for (int i = 0; i < listaIzq.tamanio(); i++) {
                lista.agregar(listaIzq.obtener(i));
            }
        }
    
        // Recorrer subárbol derecho
        if (actual.getHijoDerecho() != null) {
            TDALista<Elemento<T>> listaDer =
                actual.getHijoDerecho().enNivel(nivel - 1);

            for (int i = 0; i < listaDer.tamanio(); i++) {
                lista.agregar(listaDer.obtener(i));
            }
        }
        return lista;
    }


    public void inOrder(Consumer<TDAElemento<T>> consumidor){
        Elemento<T> actual=this;
        if (actual.hijoIzq!=null) {
            actual.hijoIzq.inOrder(consumidor);
        } 
        consumidor.accept(actual);;

        if (actual.hijoDer!=null) {
            actual.hijoDer.inOrder(consumidor);
        }
    }    
    
    public void preOrder(Consumer<TDAElemento<T>> consumidor){
        Elemento<T> actual=this;
        consumidor.accept(this);

        if (actual.hijoIzq!=null) {
            actual.hijoIzq.preOrder(consumidor);
        } 

        if (actual.hijoDer!=null) {
            actual.hijoDer.preOrder(consumidor);
        }
    }

    public void postOrder(Consumer<TDAElemento<T>> consumidor){
        Elemento<T> actual=this;
        if (actual.hijoIzq!=null) {
            actual.hijoIzq.postOrder(consumidor);
        } 

        if (actual.hijoDer!=null) {
            actual.hijoDer.postOrder(consumidor);
        }
        
        consumidor.accept(this);

    }
}
