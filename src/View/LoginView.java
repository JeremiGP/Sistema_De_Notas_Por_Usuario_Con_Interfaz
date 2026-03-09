package View;

// Importaciones .swing
import javax.swing.*;

// Importaciones .awt
import java.awt.*;

/**
 * Clase que representa la vista de inicio de sesión
 */
public class LoginView extends JFrame {
    // Atributos de la clase
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton btnLogin, btnRegister;

    /**
     * Constructor de la clase
     */
    public LoginView() {
        // Configuración de la ventana
        setTitle("Gestor de Notas - Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        // Panel de datos
        JPanel pnlDatos = new JPanel(new GridLayout(2, 2));
        // Panel de usuario
        pnlDatos.add(new JLabel("Usuario:"));
        // Campo de usuario
        txtUsername = new JTextField();
        // Agregamos el campo de usuario al panel de datos
        pnlDatos.add(txtUsername);

        // Panel de contraseña
        pnlDatos.add(new JLabel("Contraseña:"));
        // Campo de contraseña
        txtPassword = new JPasswordField();
        // Agregamos el campo de contraseña al panel de datos
        pnlDatos.add(txtPassword);

        // Panel de botones
        JPanel pnlBotones = new JPanel();
        // Botón de iniciar sesión
        btnLogin = new JButton("Iniciar Sesión");
        // Botón de registrarse
        btnRegister = new JButton("Registrarse");
        // Agregamos los botones al panel de botones
        pnlBotones.add(btnLogin);
        pnlBotones.add(btnRegister);

        // Agregamos el panel de datos y el panel de botones a la ventana
        add(pnlDatos);
        add(pnlBotones);
    }
}