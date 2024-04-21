package org.unrn.ejercicio1.ui;

import org.unrn.ejercicio1.modelo.Modelo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI extends JFrame {
    private final Modelo modelo;
    private JTextField nombre;
    private JTextField telefono;
    private JTextField region;

    public UI(Modelo modelo) {
        this.modelo = modelo;
        setupUI();
    }

    public void setupUI() {
        setTitle("Agregar Participante");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nombre = new JTextField(10);
        this.telefono = new JTextField(10);
        this.region = new JTextField(10);
        this.nombre.setText("");
        this.telefono.setText("");
        this.region.setText("China");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JLabel("Nombre: "));
        contentPane.add(nombre);
        contentPane.add(new JLabel("Telefono: "));
        contentPane.add(telefono);
        contentPane.add(new JLabel("Region: "));
        contentPane.add(region);
        JButton botonCargar = new JButton("Cargar");
        agregarActionListener(botonCargar);
        contentPane.add(botonCargar);
        setContentPane(contentPane);
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pack();
        setVisible(true);
    }

    private void agregarActionListener(JButton botonCargar) {
        botonCargar.addActionListener(e -> onBotonCargar());
    }

    public void onBotonCargar() {
        if (algunCampoInvalido()) return;
        modelo.cargarPersona(nombre.getText(), telefono.getText(), region.getText());
        dispose();
    }

    private boolean algunCampoInvalido() {
        if (isVacio(nombre)) {
            notificarError("Debe cargar un nombre");
            return true;
        }
        if (isVacio(telefono)) {
            notificarError("Debe cargar un telefono");
            return true;
        }
        if (!validarTelefono(telefono.getText())) {
            notificarError("El teléfono debe ingresarse" +
                    " de la siguiente forma: NNNN-NNNNNN");
            return true;
        }
        if (isRegionInvalida(region.getText())) {
            notificarError("Region desconocida." +
                    " Las conocidas son: China, US, Europa");
            return true;
        }
        return false;
    }

    private void notificarError(String notificacion) {
        JOptionPane.showMessageDialog(this, notificacion);
    }

    private boolean isVacio(JTextField campo) {
        return campo.getText().isEmpty();
    }

    private boolean validarTelefono(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    private boolean isRegionInvalida(String region) {
        return !region.equals("China") && !region.equals("US") && !
                region.equals("Europa");
    }
}

