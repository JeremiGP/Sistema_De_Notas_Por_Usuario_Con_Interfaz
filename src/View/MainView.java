package View;

// Importaciones .model
import Model.Nota;

// Importaciones .swing
import javax.swing.*;

// Importaciones .awt
import java.awt.*;

/**
 * Clase que representa la vista principal
 */
public class MainView extends JFrame {
    // Atributos de la clase
    public JTextField txtBuscar, txtTitulo;
    public JTextArea txtContenido;
    public JList<Nota> listaNotas;
    public DefaultListModel<Nota> modeloLista;
    public JButton btnNueva, btnGuardar, btnEliminar, btnBorrarTodas, btnExportar, btnCerrarSesion;

    /**
     * Constructor de la clase
     */
    public MainView() {
        // Configuración de la ventana
        setTitle("Mis Notas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel izquierdo
        JPanel pnlIzquierdo = new JPanel(new BorderLayout());
        // Campo de búsqueda
        txtBuscar = new JTextField(15);
        // Agregamos el campo de búsqueda al panel izquierdo
        pnlIzquierdo.add(new JLabel(" Buscar por título:"), BorderLayout.NORTH);
        pnlIzquierdo.add(txtBuscar, BorderLayout.CENTER);

        // Modelo de lista
        modeloLista = new DefaultListModel<>();
        listaNotas = new JList<>(modeloLista);
        pnlIzquierdo.add(new JScrollPane(listaNotas), BorderLayout.SOUTH);

        // Panel central
        JPanel pnlCentro = new JPanel(new BorderLayout());

        // Panel de título
        JPanel pnlTitulo = new JPanel(new BorderLayout());
        // Etiqueta de título
        pnlTitulo.add(new JLabel("Título: "), BorderLayout.WEST);
        // Campo de título
        txtTitulo = new JTextField();
        // Agregamos el campo de título al panel de título
        pnlTitulo.add(txtTitulo, BorderLayout.CENTER);

        // Área de contenido
        txtContenido = new JTextArea();
        // Agregamos el panel de título y el área de contenido al panel central
        pnlCentro.add(pnlTitulo, BorderLayout.NORTH);
        pnlCentro.add(new JScrollPane(txtContenido), BorderLayout.CENTER);

        // Panel de botones
        JPanel pnlBotones = new JPanel(new FlowLayout());
        // Botón de nueva nota
        btnNueva = new JButton("Limpiar / Nueva");
        // Botón de guardar nota
        btnGuardar = new JButton("Guardar Nota");
        // Botón de eliminar nota
        btnEliminar = new JButton("Eliminar Nota");
        // Botón de borrar todas las notas
        btnBorrarTodas = new JButton("Borrar Todas");
        // Botón de exportar
        btnExportar = new JButton("Exportar");
        // Botón de cerrar sesión
        btnCerrarSesion = new JButton("Cerrar Sesión");

        // Deshabilitamos el botón de eliminar nota
        btnEliminar.setEnabled(false);

        // Agregamos los botones al panel de botones
        pnlBotones.add(btnNueva);
        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnBorrarTodas);
        pnlBotones.add(btnExportar);
        pnlBotones.add(btnCerrarSesion);

        // Agregamos el panel izquierdo, el panel central y el panel de botones a la
        // ventana
        add(pnlIzquierdo, BorderLayout.WEST);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlBotones, BorderLayout.SOUTH);
    }
}