package View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton btnLogin, btnRegister;

    public LoginView() {
        setTitle("Gestor de Notas - Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JPanel pnlDatos = new JPanel(new GridLayout(2, 2));
        pnlDatos.add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        pnlDatos.add(txtUsername);

        pnlDatos.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        pnlDatos.add(txtPassword);

        JPanel pnlBotones = new JPanel();
        btnLogin = new JButton("Iniciar Sesión");
        btnRegister = new JButton("Registrarse");
        pnlBotones.add(btnLogin);
        pnlBotones.add(btnRegister);

        add(pnlDatos);
        add(pnlBotones);
    }
}