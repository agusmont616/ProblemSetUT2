package ucu.edu.aed.tda.ejercicio7;

public class ArbolAritmetico {

    private ElementosNumericos raiz;

    public ArbolAritmetico(ElementosNumericos raiz) {
        this.raiz = raiz;
    }

    // Evaluar con un valor concreto para la variable x
    public double evaluar(double valorX) {
        return evaluarNodo(raiz, valorX);
    }

    private double evaluarNodo(ElementosNumericos nodo, double valorX) {
        if (nodo == null) {
            throw new IllegalArgumentException("Nodo nulo");
        }

        String dato = nodo.getDato();

        // Caso operador
        if (dato.equals("+") || dato.equals("-") || dato.equals("*") || dato.equals("/")) {
            double izq = evaluarNodo(nodo.getHijoIzq(), valorX);
            double der = evaluarNodo(nodo.getHijoDer(), valorX);

            switch (dato) {
                case "+": return izq + der;
                case "-": return izq - der;
                case "*": return izq * der;
                case "/": return izq / der;
            }
        }

        // Caso número
        try {
            return Double.parseDouble(dato);
        } catch (NumberFormatException e) {
            // Caso variable (ejemplo: "x")
            if (dato.equals("x")) {
                return valorX;
            } else {
                throw new IllegalArgumentException("Variable no reconocida: " + dato);
            }
        }
    }
}
