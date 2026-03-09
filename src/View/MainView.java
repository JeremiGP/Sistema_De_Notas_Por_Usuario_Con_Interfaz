package View;

import Model.Nota;
import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public JTextField txtBuscar, txtTitulo;
    public JTextArea txtContenido;
    public JList<Nota> listaNotas;
    public DefaultListModel<Nota> modeloLista;
    public JButton btnNueva, btnGuardar, btnEliminar, btnBorrarTodas, btnExportar, btnCerrarSesion;

    public MainView() {
        setTitle("Mis Notas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlIzquierdo = new JPanel(new BorderLayout());
        txtBuscar = new JTextField(15);
        pnlIzquierdo.add(new JLabel(" Buscar por título:"), BorderLayout.NORTH);
        pnlIzquierdo.add(txtBuscar, BorderLayout.CENTER);

        modeloLista = new DefaultListModel<>();
        listaNotas = new JList<>(modeloLista);
        pnlIzquierdo.add(new JScrollPane(listaNotas), BorderLayout.SOUTH);

        JPanel pnlCentro = new JPanel(new BorderLayout());

        JPanel pnlTitulo = new JPanel(new BorderLayout());
        pnlTitulo.add(new JLabel("Título: "), BorderLayout.WEST);
        txtTitulo = new JTextField();
        pnlTitulo.add(txtTitulo, BorderLayout.CENTER);

        txtContenido = new JTextArea();
        pnlCentro.add(pnlTitulo, BorderLayout.NORTH);
        pnlCentro.add(new JScrollPane(txtContenido), BorderLayout.CENTER);

        JPanel pnlBotones = new JPanel(new FlowLayout());
        btnNueva = new JButton("Limpiar / Nueva");
        btnGuardar = new JButton("Guardar Nota");
        btnEliminar = new JButton("Eliminar Nota");
        btnBorrarTodas = new JButton("Borrar Todas");
        btnExportar = new JButton("Exportar");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        btnEliminar.setEnabled(false);

        pnlBotones.add(btnNueva);
        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnBorrarTodas);
        pnlBotones.add(btnExportar);
        pnlBotones.add(btnCerrarSesion);

        add(pnlIzquierdo, BorderLayout.WEST);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlBotones, BorderLayout.SOUTH);
    }
}