package Controller;

import Model.Usuario;
import Service.AuthService;
import View.LoginView;
import View.MainView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView view;
    private AuthService authService;

    public LoginController(LoginView view, AuthService authService) {
        this.view = view;
        this.authService = authService;

        this.view.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        this.view.btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
    }

    private void iniciarSesion() {
        String user = view.txtUsername.getText();
        String pass = new String(view.txtPassword.getPassword());

        Usuario usuario = authService.validarLogin(user, pass);
        if (usuario != null) {
            JOptionPane.showMessageDialog(view, "¡Bienvenido, " + user + "!");
            abrirGestorNotas(usuario);
        } else {
            JOptionPane.showMessageDialog(view, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrar() {
        String user = view.txtUsername.getText();
        String pass = new String(view.txtPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Rellena todos los campos.");
            return;
        }

        if (authService.registrarUsuario(user, pass)) {
            JOptionPane.showMessageDialog(view, "Usuario registrado con éxito. Ya puedes iniciar sesión.");
        } else {
            JOptionPane.showMessageDialog(view, "El usuario ya existe.");
        }
    }

    private void abrirGestorNotas(Usuario usuario) {
        view.dispose();
        MainView mainView = new MainView();
        new MainController(mainView, usuario);
        mainView.setVisible(true);
    }
}