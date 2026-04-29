package ucu.edu.aed.tda.ejercicio7;

public class Probar {
    public static void main(String[] args) {
        // Construcción manual del árbol: 8 + (3 * x) - 7
        ElementosNumericos raiz = new ElementosNumericos("-");
        ElementosNumericos suma = new ElementosNumericos("+");
        raiz.setHijoIzq(suma);
        raiz.setHijoDer(new ElementosNumericos("7"));

        suma.setHijoIzq(new ElementosNumericos("8"));
        ElementosNumericos mult = new ElementosNumericos("*");
        suma.setHijoDer(mult);

        mult.setHijoIzq(new ElementosNumericos("3"));
        mult.setHijoDer(new ElementosNumericos("x"));

        ArbolAritmetico arbol = new ArbolAritmetico(raiz);

        double resultado = arbol.evaluar(5); // x = 5
        System.out.println("Resultado: " + resultado); // Imprime 16
    }
}
