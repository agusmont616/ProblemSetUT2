package ucu.edu.aed.tda.ejercicio7;

public class ElementosNumericos {
    private ElementosNumericos hijoIzq;
    private ElementosNumericos hijoDer;
    private String dato;

    public ElementosNumericos(String datoT){
        this.dato = datoT;
        this.hijoIzq = null;
        this.hijoDer = null;
    }

    // Getters y setters
    public ElementosNumericos getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(ElementosNumericos hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public ElementosNumericos getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(ElementosNumericos hijoDer) {
        this.hijoDer = hijoDer;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    // Método auxiliar para saber si es hoja
    public boolean esHoja() {
        return hijoIzq == null && hijoDer == null;
    }
}
