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
        setSize(350, 250); // Un poco más grande para añadir márgenes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creamos un panel principal con márgenes para que no quede pegado a los bordes
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));
        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Título de bienvenida
        JLabel lblTitulo = new JLabel("Bienvenido al Gestor");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPrincipal.add(lblTitulo);
        pnlPrincipal.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciador

        // Panel de datos (con 10px de separación entre filas y columnas)
        JPanel pnlDatos = new JPanel(new GridLayout(2, 2, 10, 10));

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

        pnlPrincipal.add(pnlDatos);
        pnlPrincipal.add(Box.createRigidArea(new Dimension(0, 15))); // Espaciador

        // Panel de botones
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        // Botón de iniciar sesión (Personalizado)
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBackground(new Color(63, 81, 181)); // Azul oscuro [cite: 923]
        btnLogin.setForeground(Color.WHITE); // Texto blanco [cite: 924]
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Botón de registrarse (Personalizado)
        btnRegister = new JButton("Registrarse");
        btnRegister.setBackground(new Color(96, 125, 139)); // Gris azulado [cite: 923]
        btnRegister.setForeground(Color.WHITE); // Texto blanco [cite: 924]
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agregamos los botones al panel de botones
        pnlBotones.add(btnLogin);
        pnlBotones.add(btnRegister);

        pnlPrincipal.add(pnlBotones);

        // Agregamos el panel principal a la ventana
        add(pnlPrincipal);
    }
}