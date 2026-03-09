package Service;

// Importaciones de la clase
import Model.Usuario;
import Utils.SecurityUtils;

// Importaciones .io
import java.io.*;

/**
 * Clase que representa el servicio de autenticacion
 */
public class AuthService {
    // Atributos de la clase
    private final String FILE_USERS = "usuarios.txt";

    /**
     * Metodo que registra un usuario
     * 
     * @param username Nombre de usuario
     * @param password Contraseña del usuario
     * @return true si el usuario se registro correctamente, false en caso contrario
     */
    public boolean registrarUsuario(String username, String password) {
        // Validamos si el usuario ya existe
        if (validarLogin(username, password) != null)
            return false;

        // Hasheamos la contraseña
        String hash = SecurityUtils.hashPassword(password);
        // Escribimos el usuario en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_USERS, true))) {
            writer.write(username + "," + hash);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo que valida el login de un usuario
     * 
     * @param username Nombre de usuario
     * @param password Contraseña del usuario
     * @return true si el usuario se valido correctamente, false en caso contrario
     */
    public Usuario validarLogin(String username, String password) {
        // Creamos el archivo
        File file = new File(FILE_USERS);
        // Validamos si el archivo existe
        if (!file.exists())
            return null;

        // Hasheamos la contraseña
        String inputHash = SecurityUtils.hashPassword(password);

        // Leemos el archivo
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