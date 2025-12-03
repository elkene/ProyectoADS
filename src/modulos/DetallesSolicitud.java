package modulos;

import javax.swing.*;
import java.awt.*;

public class DetallesSolicitud extends JDialog {
    public DetallesSolicitud(Frame owner, String titulo, String fecha, String estado, String descripcion) {
        super(owner, "Detalles de la solicitud", true);
        setSize(520, 360);
        setLocationRelativeTo(owner);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 15, 80, 25);
        add(lblTitulo);

        JLabel valTitulo = new JLabel(titulo);
        valTitulo.setBounds(100, 15, 380, 25);
        add(valTitulo);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(20, 50, 80, 25);
        add(lblFecha);

        JLabel valFecha = new JLabel(fecha);
        valFecha.setBounds(100, 50, 200, 25);
        add(valFecha);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(20, 85, 80, 25);
        add(lblEstado);

        JLabel valEstado = new JLabel(estado);
        valEstado.setBounds(100, 85, 200, 25);
        add(valEstado);

        JLabel lblDesc = new JLabel("Descripción:");
        lblDesc.setBounds(20, 120, 100, 25);
        add(lblDesc);

        JTextArea txtDesc = new JTextArea(descripcion);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setEditable(false);
        JScrollPane sp = new JScrollPane(txtDesc);
        sp.setBounds(20, 150, 460, 150);
        add(sp);
    }
}
