package Controller;

// Importaciones de la clase
import Model.Nota;
import Model.Usuario;
import Service.NotaService;
import View.LoginView;
import View.MainView;
import Service.AuthService;

// Importaciones de la libreria Swing
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase controladora del gestor de notas
 */
public class MainController {
    // Atributos de la clase
    private MainView view;
    private NotaService notaService;
    private List<Nota> todasLasNotas;

    /**
     * Constructor de la clase
     * 
     * @param view    Vista del gestor de notas
     * @param usuario Usuario que ha iniciado sesion
     */
    public MainController(MainView view, Usuario usuario) {
        this.view = view;
        this.notaService = new NotaService();
        this.notaService.setUsuarioActual(usuario.getUsername());
        this.todasLasNotas = notaService.cargarNotas();

        // Actualizacion de la lista visual de notas
        actualizarListaVisual(this.todasLasNotas);
        iniciarEventos();
    }

    /**
     * Metodo que actualiza la lista visual de notas
     * 
     * @param notasAMostrar Lista de notas a mostrar
     */
    private void actualizarListaVisual(List<Nota> notasAMostrar) {
        view.modeloLista.clear();
        // Recorremos la lista de notas
        for (Nota n : notasAMostrar) {
            view.modeloLista.addElement(n);
        }
    }

    /**
     * Metodo que inicia los eventos de la vista
     */
    private void iniciarEventos() {
        // Evento que se ejecuta al seleccionar una nota
        view.listaNotas.addListSelectionListener(new ListSelectionListener() {
            @Override
            // Metodo que se ejecuta al cambiar la seleccion de la lista
            public void valueChanged(ListSelectionEvent e) {
                // Si no se esta ajustando la seleccion
                if (!e.getValueIsAdjusting()) {
                    // Obtenemos la nota seleccionada
                    Nota seleccionada = view.listaNotas.getSelectedValue();
                    // Si la nota no es nula
                    if (seleccionada != null) {
                        // Actualizamos el titulo y el contenido
                        view.txtTitulo.setText(seleccionada.getTitulo());
                        view.txtContenido.setText(seleccionada.getContenido());
                        view.btnEliminar.setEnabled(true);
                    } else {
                        // Si la nota es nula
                        view.btnEliminar.setEnabled(false);
                    }
                }
            }
        });

        // Evento que se ejecuta al presionar el boton de nueva nota
        view.btnNueva.addActionListener(e -> {
            // Limpiamos la seleccion de la lista
            view.listaNotas.clearSelection();
            // Limpiamos el titulo y el contenido
            view.txtTitulo.setText("");
            view.txtContenido.setText("");
        });

        // Evento que se ejecuta al presionar el boton de guardar
        view.btnGuardar.addActionListener(e -> {
            // Obtenemos el titulo y el contenido
            String titulo = view.txtTitulo.getText();
            String contenido = view.txtContenido.getText();

            // Si el titulo esta vacio
            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "El título no puede estar vacío.");
                return;
            }

            // Obtenemos la nota seleccionada
            Nota seleccionada = view.listaNotas.getSelectedValue();
            // Si la nota no es nula
            if (seleccionada != null) {
                // Editar existente
                seleccionada.setTitulo(titulo);
                seleccionada.setContenido(contenido);
            } else {
                // Crear nueva
                Nota nueva = new Nota(titulo, contenido);
                todasLasNotas.add(nueva);
            }

            // Guardamos las notas
            notaService.guardarNotas(todasLasNotas);
            // Actualizamos la lista visual
            actualizarListaVisual(todasLasNotas);
            // Mostramos un mensaje de exito
            JOptionPane.showMessageDialog(view, "Nota guardada correctamente.");
        });

        // Evento que se ejecuta al presionar el boton de eliminar
        view.btnEliminar.addActionListener(e -> {
            // Obtenemos la nota seleccionada
            Nota seleccionada = view.listaNotas.getSelectedValue();
            // Si la nota no es nula
            if (seleccionada != null) {
                // Eliminamos la nota
                todasLasNotas.remove(seleccionada);
                // Guardamos las notas
                notaService.guardarNotas(todasLasNotas);
                // Actualizamos la lista visual
                actualizarListaVisual(todasLasNotas);
                // Limpiamos la seleccion de la lista
                view.listaNotas.clearSelection();
                // Limpiamos el titulo y el contenido
                view.txtTitulo.setText("");
                view.txtContenido.setText("");
                // Mostramos un mensaje de exito
                JOptionPane.showMessageDialog(view, "Nota eliminada.");
            }
        });

        // Evento que se ejecuta al presionar el boton de borrar todas
        view.btnBorrarTodas.addActionListener(e -> {
            // Mostramos un cuadro de dialogo de confirmacion
            int opcion = JOptionPane.showConfirmDialog(view,
                    "¿Estás seguro de borrar TODAS tus notas? Esta acción no se puede deshacer.", "Advertencia",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            // Si la opcion es si
            if (opcion == JOptionPane.YES_OPTION) {
                // Limpiamos la lista de notas
                todasLasNotas.clear();
                // Guardamos las notas
                notaService.guardarNotas(todasLasNotas);
                // Actualizamos la lista visual
                actualizarListaVisual(todasLasNotas);
                // Limpiamos la seleccion de la lista
                view.listaNotas.clearSelection();
                // Limpiamos el titulo y el contenido
                view.txtTitulo.setText("");
                view.txtContenido.setText("");
                // Mostramos un mensaje de exito
                JOptionPane.showMessageDialog(view, "Se han borrado todas las notas.");
            }
        });

        // Evento que se ejecuta al escribir en el campo de busqueda
        view.txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            /**
             * Metodo que se ejecuta al insertar texto en el campo de busqueda
             * 
             * @param e Evento que se ejecuta al insertar texto
             */
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            /**
             * Metodo que se ejecuta al eliminar texto en el campo de busqueda
             * 
             * @param e Evento que se ejecuta al eliminar texto
             */
            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            /**
             * Metodo que se ejecuta al cambiar el texto en el campo de busqueda
             * 
             * @param e Evento que se ejecuta al cambiar el texto
             */
            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }

            /**
             * Metodo que filtra las notas
             */
            private void filtrar() {
                // Obtenemos el filtro
                String filtro = view.txtBuscar.getText().toLowerCase();
                // Creamos una lista de notas filtradas
                List<Nota> filtradas = new ArrayList<>();
                // Recorremos la lista de notas
                for (Nota n : todasLasNotas) {
                    // Si la nota contiene el filtro
                    if (n.getTitulo().toLowerCase().contains(filtro)) {
                        // Agregamos la nota a la lista de notas filtradas
                        filtradas.add(n);
                    }
                }
                // Actualizamos la lista visual
                actualizarListaVisual(filtradas);
            }
        });

        // Evento que se ejecuta al presionar el boton de exportar
        view.btnExportar.addActionListener(e -> {
            // Creamos un cuadro de dialogo para seleccionar el archivo
            JFileChooser selector = new JFileChooser();
            int resultado = selector.showSaveDialog(view);
            // Si la opcion es guardar
            if (resultado == JFileChooser.APPROVE_OPTION) {
                // Obtenemos el archivo destino
                File archivoDestino = selector.getSelectedFile();
                notaService.exportarNotas(archivoDestino, todasLasNotas);
                JOptionPane.showMessageDialog(view, "Notas exportadas exitosamente.");
            }
        });

        // Evento que se ejecuta al presionar el boton de cerrar sesion
        view.btnCerrarSesion.addActionListener(e -> {
            // Cerramos la vista actual
            view.dispose();
            // Creamos una nueva vista de inicio de sesion
            LoginView loginView = new LoginView();
            // Creamos un nuevo controlador de inicio de sesion
            new LoginController(loginView, new AuthService());
            // Mostramos la vista de inicio de sesion
            loginView.setVisible(true);
        });
    }
}