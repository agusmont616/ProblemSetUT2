package ucu.edu.aed.ejercicio12;

public class Hechizo implements Comparable<Hechizo> {

    private int id;
    private String nombre;

    public Hechizo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public int compareTo(Hechizo otro) {
        return Integer.compare(this.id, otro.getId());
    }

    @Override
    public String toString() {
        return "(" + this.id + ", \"" + this.nombre + "\")";
    }
}