package Service;

import Model.Usuario;
import Utils.SecurityUtils;
import java.io.*;

public class AuthService {
    private final String FILE_USERS = "usuarios.txt";

    public boolean registrarUsuario(String username, String password) {
        if (validarLogin(username, password) != null)
            return false; // El usuario ya existe

        String hash = SecurityUtils.hashPassword(password);
        // Usamos FileWriter en modo "append" (true) para no borrar los usuarios
        // anteriores
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_USERS, true))) {
            writer.write(username + "," + hash);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario validarLogin(String username, String password) {
        File file = new File(FILE_USERS);
        if (!file.exists())
            return null;

        String inputHash = SecurityUtils.hashPassword(password);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2 && partes[0].equals(username) && partes[1].equals(inputHash)) {
                    return new Usuario(username, partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }
        return null;
    }
}