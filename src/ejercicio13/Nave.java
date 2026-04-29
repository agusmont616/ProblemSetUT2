public class Nave {
    private int codigo;
    private String clase;
    private int combustibleRestante;

    public Nave(int codigo, String clase, int combustible) {
        this.codigo = codigo;
        this.clase = clase;
        this.combustibleRestante = combustible;
    }

    // getters

    public int getCodigo() {
        return this.codigo;
    }

    public String getClase() {
        return this.clase;
    }

    public int getCombustibleRestante() {
        return this.combustibleRestante;
    }

    // toString y compareTo

    @Override
    public string toString() {
        return "[ " + codigo + "," + clase + "," + combustibleRestante + " ]\n";
    }

    @Override
    public int compareTo(Nave otra) {
        return Integer.compare(codigo, otra.getCodigo());
    }
}
