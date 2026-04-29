public class Main {
    
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();

        BufferedReader lector = new BufferedReader(new FileReader("naves.txt"));
        String linea;

        while ((linea = lector.readLine()) != null) {
            String[] partes = linea.split(","); 
            int codigo = Integer.parseInt(partes[0].trim());
            String clase = partes[1].trim();
            int combustible = Integer.parseInt(partes[2].trim());

            Nave nave = new Nave(codigo, clase, combustible);
            sistema.agregarNave(nave);
        }
    }
}