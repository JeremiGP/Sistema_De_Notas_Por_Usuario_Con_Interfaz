package App;

// Importaciones de los paquetes necesarios
import Controller.LoginController;
import Service.AuthService;
import View.LoginView;

// Importaciones de la libreria Swing
import javax.swing.*;

/**
 * Clase principal que inicia la aplicacion
 */
public class Main {
    /**
     * Metodo que se ejecuta al iniciar el programa
     * 
     * @param args Argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        // Metodo que se ejecuta al iniciar el programa
        SwingUtilities.invokeLater(() -> {

            // Añadido para que la aplicacion se vea mas bonita
            // Contenido aprendido en base a internet y IA (Pequeña mejora que marca la
            // diferencia o eso en mi opinion)
            try {
                // El Look & Feel Nimbus para un diseño más moderno
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Creacion de la vista de login
            LoginView view = new LoginView();
            // Creacion del servicio de autenticacion
            AuthService service = new AuthService();
            // Creacion del controlador de login
            new LoginController(view, service);

            view.setVisible(true);
        });
    }
}