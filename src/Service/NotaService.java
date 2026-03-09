package Service;

import Model.Nota;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotaService {
    private String usernameActual;

    public void setUsuarioActual(String username) {
        this.usernameActual = username;
    }

    private String getFileName() {
        return "notas_" + usernameActual + ".txt";
    }

    public List<Nota> cargarNotas() {
        List<Nota> notas = new ArrayList<>();
        File file = new File(getFileName());
        if (!file.exists())
            return notas;

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

    public void guardarNotas(List<Nota> notas) {
        if (usernameActual == null)
            return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName()))) {
            for (Nota n : notas) {
                String contenidoPlano = n.getContenido().replace("\n", "<br>");
                writer.write(n.getTitulo() + "---SEP---" + contenidoPlano);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar notas: " + e.getMessage());
        }
    }

    public void exportarNotas(File archivoDestino, List<Nota> notas) {
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