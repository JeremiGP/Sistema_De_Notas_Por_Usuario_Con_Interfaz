package Controller;

// Importaciones de los paquetes necesarios
import Model.Usuario;
import Service.AuthService;
import View.LoginView;
import View.MainView;

// Importaciones de la libreria Swing
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase controladora del login
 */
public class LoginController {
    // Atributos de la clase
    private LoginView view;
    private AuthService authService;

    /**
     * Constructor de la clase
     * 
     * @param view        Vista del login
     * @param authService Servicio de autenticacion
     */
    public LoginController(LoginView view, AuthService authService) {
        this.view = view;
        this.authService = authService;

        // Evento que se ejecuta al presionar el boton de iniciar sesion
        this.view.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        // Evento que se ejecuta al presionar el boton de registrarse
        this.view.btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
    }

    /**
     * Metodo que se ejecuta al presionar el boton de iniciar sesion
     */
    private void iniciarSesion() {
        // Obtencion de los datos del usuario
        String user = view.txtUsername.getText();
        String pass = new String(view.txtPassword.getPassword());

        // Validacion de los datos del usuario
        Usuario usuario = authService.validarLogin(user, pass);
        if (usuario != null) {
            JOptionPane.showMessageDialog(view, "¡Bienvenido, " + user + "!");
            abrirGestorNotas(usuario);
        } else {
            JOptionPane.showMessageDialog(view, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que se ejecuta al presionar el boton de registrarse
     */
    private void registrar() {
        // Obtencion de los datos del usuario
        String user = view.txtUsername.getText();
        String pass = new String(view.txtPassword.getPassword());

        // Validacion de los datos del usuario
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Rellena todos los campos.");
            return;
        }

        // Registro del usuario
        if (authService.registrarUsuario(user, pass)) {
            JOptionPane.showMessageDialog(view, "Usuario registrado con éxito. Ya puedes iniciar sesión.");
        } else {
            JOptionPane.showMessageDialog(view, "El usuario ya existe.");
        }
    }

    /**
     * Metodo que se ejecuta al presionar el boton de iniciar sesion
     * 
     * @param usuario Usuario que ha iniciado sesion
     */
    private void abrirGestorNotas(Usuario usuario) {
        // Cierre de la vista de login
        view.dispose();
        // Creacion de la vista de notas
        MainView mainView = new MainView();
        // Creacion del controlador de notas
        new MainController(mainView, usuario);
        // Visibilidad de la vista de notas
        mainView.setVisible(true);
    }
}