package ucu.edu.aed.ejercicio12;

import ucu.edu.aed.tda.ArbolBinario;
import ucu.edu.aed.tda.TDAElemento;
import java.util.ArrayList;
import java.util.List;

public class Grimorio {

    private ArbolBinario<Hechizo> arbol;

    public Grimorio() {
        this.arbol = new ArbolBinario<Hechizo>();
    }

    public void agregarHechizo(Hechizo hechizo) {
        this.arbol.insertar(hechizo);
    }

    /**
     * Descripción: Retorna todos los hechizos cuyo ID es impar.
     *
     * Precondición: el árbol puede estar vacío o no.
     * Postcondición: se retorna una lista con todos los hechizos
     *                cuyo ID es impar, en orden ascendente de ID.
     *
     * Pseudocódigo:
     *   procedimiento consultarHechizosProhibidos():
     *     prohibidos ← lista vacía
     *     recorrer árbol en inorden:
     *       para cada nodo n:
     *         si n.dato.id mod 2 ≠ 0 entonces
     *           agregar n.dato a prohibidos
     *         fin si
     *     retornar prohibidos
     *
     * Análisis de tiempo: O(n) — el inorden visita todos los nodos una vez.
     */
    public List<Hechizo> consultarHechizosProhibidos() {
        List<Hechizo> prohibidos = new ArrayList<>();
        this.arbol.inOrder(nodo -> {
            TDAElemento<Hechizo> elemento = (TDAElemento<Hechizo>) nodo;
            Hechizo hechizo = elemento.getDato();
            if (hechizo.getId() % 2 != 0) {
                prohibidos.add(hechizo);
            }
        });
        return prohibidos;
    }

    /**
     * Descripción: Recorre el árbol en inorden y concatena los nombres
     *              de los hechizos prohibidos separados por " - ".
     *
     * Precondición: el árbol puede estar vacío o no.
     * Postcondición: se retorna un String con los nombres de los hechizos
     *                prohibidos separados por " - ", en orden ascendente de ID.
     *                Si no hay prohibidos, retorna string vacío.
     *
     * Pseudocódigo:
     *   procedimiento generarCantico():
     *     prohibidos ← consultarHechizosProhibidos()
     *     si prohibidos está vacío entonces
     *       retornar ""
     *     fin si
     *     cantico ← ""
     *     para cada hechizo h en prohibidos:
     *       si cantico ≠ "" entonces
     *         cantico ← cantico + " - "
     *       fin si
     *       cantico ← cantico + h.nombre
     *     retornar cantico
     *
     * Análisis de tiempo: O(n) — consultarHechizosProhibidos() ya es O(n).
     */
    public String generarCantico() {
        List<Hechizo> prohibidos = this.consultarHechizosProhibidos();
        if (prohibidos.isEmpty()) return "";
        StringBuilder cantico = new StringBuilder();
        for (int i = 0; i < prohibidos.size(); i++) {
            if (i > 0) cantico.append(" - ");
            cantico.append(prohibidos.get(i).getNombre());
        }
        return cantico.toString();
    }

    public static void main(String[] args) {
        Grimorio grimorio = new Grimorio();

        grimorio.agregarHechizo(new Hechizo(42, "Fireball"));
        grimorio.agregarHechizo(new Hechizo(17, "Ice Lance"));
        grimorio.agregarHechizo(new Hechizo(58, "Thunder"));
        grimorio.agregarHechizo(new Hechizo(9,  "Invisibility"));
        grimorio.agregarHechizo(new Hechizo(31, "Levitate"));
        grimorio.agregarHechizo(new Hechizo(73, "Summon"));
        grimorio.agregarHechizo(new Hechizo(25, "Heal"));
        grimorio.agregarHechizo(new Hechizo(50, "Teleport"));
        grimorio.agregarHechizo(new Hechizo(65, "Shield"));
        grimorio.agregarHechizo(new Hechizo(88, "Curse"));

        System.out.println("=== Hechizos prohibidos ===");
        for (Hechizo h : grimorio.consultarHechizosProhibidos()) {
            System.out.println("  " + h.toString());
        }

        System.out.println("\n=== Cántico secreto ===");
        System.out.println("  " + grimorio.generarCantico());
    }
}