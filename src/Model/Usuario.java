package Model;

/**
 * Clase que representa un usuario
 */
public class Usuario {
    // Atributos de la clase
    private String username;
    private String passwordHash;

    /**
     * Constructor de la clase
     * 
     * @param username     Nombre de usuario
     * @param passwordHash Hash de la contraseña
     */
    public Usuario(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /**
     * Metodo que obtiene el nombre de usuario
     * 
     * @return Nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo que obtiene el hash de la contraseña
     * 
     * @return Hash de la contraseña
     */
    public String getPasswordHash() {
        return passwordHash;
    }
}