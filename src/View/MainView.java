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
        setSize(850, 600); // Ampliamos para que quepa bien el contenido
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla

        // Panel raíz con padding general de 15px
        JPanel rootPanel = new JPanel(new BorderLayout(15, 15));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel izquierdo
        JPanel pnlIzquierdo = new JPanel(new BorderLayout(5, 5));
        // Añadimos un borde con título para la zona izquierda
        pnlIzquierdo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Directorio"));

        // Campo de búsqueda
        txtBuscar = new JTextField(15);
        // Agregamos el campo de búsqueda al panel izquierdo
        JPanel pnlBuscar = new JPanel(new BorderLayout(5, 5));
        pnlBuscar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlBuscar.add(new JLabel("Buscar por título:"), BorderLayout.NORTH);
        pnlBuscar.add(txtBuscar, BorderLayout.CENTER);
        pnlIzquierdo.add(pnlBuscar, BorderLayout.NORTH);

        // Modelo de lista
        modeloLista = new DefaultListModel<>();
        listaNotas = new JList<>(modeloLista);
        listaNotas.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane scrollLista = new JScrollPane(listaNotas);
        scrollLista.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        pnlIzquierdo.add(scrollLista, BorderLayout.CENTER);

        // Panel central
        JPanel pnlCentro = new JPanel(new BorderLayout(10, 10));
        // Añadimos un borde con título para el editor
        pnlCentro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Editor de Nota"));

        // Panel de título
        JPanel pnlTitulo = new JPanel(new BorderLayout(5, 5));
        pnlTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        // Etiqueta de título
        JLabel lblTitulo = new JLabel("Título: ");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        pnlTitulo.add(lblTitulo, BorderLayout.WEST);

        // Campo de título
        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        // Agregamos el campo de título al panel de título
        pnlTitulo.add(txtTitulo, BorderLayout.CENTER);

        // Área de contenido
        txtContenido = new JTextArea();
        txtContenido.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtContenido.setLineWrap(true);
        txtContenido.setWrapStyleWord(true); // Evita cortar palabras a la mitad

        JScrollPane scrollContenido = new JScrollPane(txtContenido);
        scrollContenido.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Agregamos el panel de título y el área de contenido al panel central
        pnlCentro.add(pnlTitulo, BorderLayout.NORTH);
        pnlCentro.add(scrollContenido, BorderLayout.CENTER);

        // Panel de botones
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Botón de nueva nota
        btnNueva = new JButton("Limpiar / Nueva");

        // Botón de guardar nota (Destacado)
        btnGuardar = new JButton("Guardar Nota");
        btnGuardar.setBackground(new Color(76, 175, 80)); // Verde [cite: 923]
        btnGuardar.setForeground(Color.WHITE); // [cite: 924]

        // Botón de eliminar nota (Peligro)
        btnEliminar = new JButton("Eliminar Nota");
        btnEliminar.setBackground(new Color(244, 67, 54)); // Rojo [cite: 923]
        btnEliminar.setForeground(Color.WHITE); // [cite: 924]

        // Botón de borrar todas las notas
        btnBorrarTodas = new JButton("Borrar Todas");
        // Botón de exportar
        btnExportar = new JButton("Exportar");
        // Botón de cerrar sesión
        btnCerrarSesion = new JButton("Cerrar Sesión");

        // Añadimos cursor de mano a todos los botones
        JButton[] botones = { btnNueva, btnGuardar, btnEliminar, btnBorrarTodas, btnExportar, btnCerrarSesion };
        for (JButton btn : botones) {
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

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
        rootPanel.add(pnlIzquierdo, BorderLayout.WEST);
        rootPanel.add(pnlCentro, BorderLayout.CENTER);
        rootPanel.add(pnlBotones, BorderLayout.SOUTH);

        add(rootPanel);
    }
}