package Controller;

import Model.Nota;
import Model.Usuario;
import Service.NotaService;
import View.LoginView;
import View.MainView;
import Service.AuthService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private MainView view;
    private NotaService notaService;
    private List<Nota> todasLasNotas;

    public MainController(MainView view, Usuario usuario) {
        this.view = view;
        this.notaService = new NotaService();
        this.notaService.setUsuarioActual(usuario.getUsername());
        this.todasLasNotas = notaService.cargarNotas();

        actualizarListaVisual(this.todasLasNotas);
        iniciarEventos();
    }

    private void actualizarListaVisual(List<Nota> notasAMostrar) {
        view.modeloLista.clear();
        for (Nota n : notasAMostrar) {
            view.modeloLista.addElement(n);
        }
    }

    private void iniciarEventos() {
        view.listaNotas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Nota seleccionada = view.listaNotas.getSelectedValue();
                    if (seleccionada != null) {
                        view.txtTitulo.setText(seleccionada.getTitulo());
                        view.txtContenido.setText(seleccionada.getContenido());
                        view.btnEliminar.setEnabled(true);
                    } else {
                        view.btnEliminar.setEnabled(false);
                    }
                }
            }
        });

        view.btnNueva.addActionListener(e -> {
            view.listaNotas.clearSelection();
            view.txtTitulo.setText("");
            view.txtContenido.setText("");
        });

        view.btnGuardar.addActionListener(e -> {
            String titulo = view.txtTitulo.getText();
            String contenido = view.txtContenido.getText();

            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "El título no puede estar vacío.");
                return;
            }

            Nota seleccionada = view.listaNotas.getSelectedValue();
            if (seleccionada != null) {
                // Editar existente
                seleccionada.setTitulo(titulo);
                seleccionada.setContenido(contenido);
            } else {
                // Crear nueva
                Nota nueva = new Nota(titulo, contenido);
                todasLasNotas.add(nueva);
            }

            notaService.guardarNotas(todasLasNotas);
            actualizarListaVisual(todasLasNotas);
            JOptionPane.showMessageDialog(view, "Nota guardada correctamente.");
        });

        view.btnEliminar.addActionListener(e -> {
            Nota seleccionada = view.listaNotas.getSelectedValue();
            if (seleccionada != null) {
                todasLasNotas.remove(seleccionada);
                notaService.guardarNotas(todasLasNotas);
                actualizarListaVisual(todasLasNotas);
                view.btnNueva.doClick();
                JOptionPane.showMessageDialog(view, "Nota eliminada.");
            }
        });

        view.btnBorrarTodas.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(view,
                    "¿Estás seguro de borrar TODAS tus notas? Esta acción no se puede deshacer.", "Advertencia",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                todasLasNotas.clear();
                notaService.guardarNotas(todasLasNotas);
                actualizarListaVisual(todasLasNotas);
                view.btnNueva.doClick();
                JOptionPane.showMessageDialog(view, "Se han borrado todas las notas.");
            }
        });

        view.txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }

            private void filtrar() {
                String filtro = view.txtBuscar.getText().toLowerCase();
                List<Nota> filtradas = new ArrayList<>();
                for (Nota n : todasLasNotas) {
                    if (n.getTitulo().toLowerCase().contains(filtro)) {
                        filtradas.add(n);
                    }
                }
                actualizarListaVisual(filtradas);
            }
        });

        view.btnExportar.addActionListener(e -> {
            JFileChooser selector = new JFileChooser();
            int resultado = selector.showSaveDialog(view);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoDestino = selector.getSelectedFile();
                notaService.exportarNotas(archivoDestino, todasLasNotas);
                JOptionPane.showMessageDialog(view, "Notas exportadas exitosamente.");
            }
        });

        view.btnCerrarSesion.addActionListener(e -> {
            view.dispose();
            LoginView loginView = new LoginView();
            new LoginController(loginView, new AuthService());
            loginView.setVisible(true);
        });
    }
}