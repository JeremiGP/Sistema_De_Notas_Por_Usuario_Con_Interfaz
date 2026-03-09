package Service;

// Importaciones de la clase
import Model.Nota;

// Importaciones .io
import java.io.*;

// Importaciones .util
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el servicio de notas
 */
public class NotaService {
    // Atributos de la clase
    private String usernameActual;

    /**
     * Metodo que establece el usuario actual
     * * @param username Nombre de usuario
     */
    public void setUsuarioActual(String username) {
        this.usernameActual = username;
    }

    /**
     * Metodo que obtiene el nombre del archivo
     * * @return Nombre del archivo
     */
    private String getFileName() {
        return "data/notas/notas_" + usernameActual + ".txt";
    }

    /**
     * Metodo que carga las notas
     * * @return Lista de notas
     */
    public List<Nota> cargarNotas() {
        // Creamos la lista de notas
        List<Nota> notas = new ArrayList<>();
        // Creamos el archivo
        File file = new File(getFileName());
        // Validamos si el archivo existe
        if (!file.exists())
            return notas;

        // Leemos el archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("---SEP---");
                if (partes.length == 2) {
                    notas.add(new Nota(partes[0], partes[1].replace("<br>", "\n")));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar notas: " + e.getMessage());
        }
        return notas;
    }

    /**
     * Metodo que guarda las notas
     * * @param notas Lista de notas
     */
    public void guardarNotas(List<Nota> notas) {
        // Validamos si el usuario actual es null
        if (usernameActual == null)
            return;

        File file = new File(getFileName());
        // Aseguramos que la carpeta data/notas exista
        file.getParentFile().mkdirs();

        // Escribimos las notas en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Nota n : notas) {
                String contenidoPlano = n.getContenido().replace("\n", "<br>");
                writer.write(n.getTitulo() + "---SEP---" + contenidoPlano);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar notas: " + e.getMessage());
        }
    }

    /**
     * Metodo que exporta las notas
     * * @param archivoDestino Archivo de destino
     * 
     * @param notas Lista de notas
     */
    public void exportarNotas(File archivoDestino, List<Nota> notas) {
        // Escribimos las notas en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDestino))) {
            for (Nota n : notas) {
                writer.write("TÍTULO: " + n.getTitulo());
                writer.newLine();
                writer.write(n.getContenido());
                writer.newLine();
                writer.write("-----------------------------------");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al exportar notas: " + e.getMessage());
        }
    }
}