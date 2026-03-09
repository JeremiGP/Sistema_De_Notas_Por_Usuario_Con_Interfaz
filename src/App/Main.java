package App;

import Controller.LoginController;
import Service.AuthService;
import View.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView view = new LoginView();
            AuthService service = new AuthService();
            new LoginController(view, service);

            view.setVisible(true);
        });
    }
}