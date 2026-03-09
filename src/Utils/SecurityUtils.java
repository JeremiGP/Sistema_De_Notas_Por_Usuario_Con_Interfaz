package Utils;

// Importaciones .security
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que representa la seguridad de la aplicacion
 */
public class SecurityUtils {
    /**
     * Metodo que hashea una contraseña
     * 
     * @param password Contraseña a hashear
     * @return Contraseña hasheada
     */
    public static String hashPassword(String password) {
        try {
            // Creamos el hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            // Convertimos el hash a hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Lanzamos la excepcion
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }
}