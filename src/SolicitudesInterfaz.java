import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolicitudesInterfaz extends JFrame {
    public SolicitudesInterfaz() {
        // Configuración básica de la ventana
        setTitle("Solicitudes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        // Panel principal con borde
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Solicitudes");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.LEFT);

        // Panel para las solicitudes
        JPanel solicitudesPanel = new JPanel();
        solicitudesPanel.setLayout(new BoxLayout(solicitudesPanel, BoxLayout.Y_AXIS));
        solicitudesPanel.setBackground(Color.WHITE);

        // Array con los datos de las solicitudes
        String[][] solicitudes = {
                {"Mantenimento a Servidor", "12:00 pm • 11/10/2025", "Asignada"},
                {"Reportes para Bajas de Equipo", "512 pm • 08/10/2025", "Asignada"},
                {"Mantenimiento a Computadora", "512 pm • 05/10/2025", "Asignada"},
                {"Investigar Impresora", "5:00 pm • 18/10/2025", "Sin Asignar"},
                {"Inventario de Discos Duros", "5:00 pm • 18/10/2025", "Sin Asignar"},
                {"Inventario de Maquinas", "5:00 pm • 18/10/2025", "Sin Asignar"}
        };

        // Crear cada panel de solicitud
        for (String[] solicitud : solicitudes) {
            solicitudesPanel.add(crearPanelSolicitud(solicitud[0], solicitud[1], solicitud[2]));
            solicitudesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre elementos
        }

        // Scroll pane para las solicitudes
        JScrollPane scrollPane = new JScrollPane(solicitudesPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Agregar componentes al panel principal
        mainPanel.add(titulo, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar panel principal a la ventana
        add(mainPanel);
    }

    private JPanel crearPanelSolicitud(String titulo, String fechaHora, String estado) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setMaximumSize(new Dimension(Short.MAX_VALUE, 80));

        // Panel izquierdo con título y fecha/hora
        JPanel izquierdo = new JPanel();
        izquierdo.setLayout(new BoxLayout(izquierdo, BoxLayout.Y_AXIS));
        izquierdo.setBackground(Color.WHITE);
        izquierdo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tituloLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel fechaLabel = new JLabel(fechaHora);
        fechaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        fechaLabel.setForeground(Color.GRAY);
        fechaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        izquierdo.add(tituloLabel);
        izquierdo.add(Box.createRigidArea(new Dimension(0, 5)));
        izquierdo.add(fechaLabel);

        // Panel derecho con estado y botón
        JPanel derecho = new JPanel(new BorderLayout());
        derecho.setBackground(Color.WHITE);

        JLabel estadoLabel = new JLabel(estado);
        estadoLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JButton boton = new JButton();
        if (estado.equals("Asignada")) {
            estadoLabel.setForeground(new Color(0, 100, 0)); // Verde oscuro
            boton.setText("Ver detalles");
            boton.setBackground(new Color(240, 240, 240));
        } else {
            estadoLabel.setForeground(Color.RED);
            boton.setText("Tomar Responsabilidad");
            boton.setBackground(new Color(220, 240, 255));
        }

        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // ActionListener para el botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boton.getText().equals("Tomar Responsabilidad")) {
                    int respuesta = JOptionPane.showConfirmDialog(
                            SolicitudesInterfaz.this,
                            "¿Estás seguro de que quieres tomar responsabilidad de: " + titulo + "?",
                            "Confirmar Responsabilidad",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (respuesta == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(SolicitudesInterfaz.this,
                                "Has tomado responsabilidad de: " + titulo);
                    }
                } else {
                    JOptionPane.showMessageDialog(SolicitudesInterfaz.this,
                            "Detalles de: " + titulo + "\nFecha: " + fechaHora + "\nEstado: " + estado);
                }
            }
        });

        derecho.add(estadoLabel, BorderLayout.NORTH);
        derecho.add(boton, BorderLayout.SOUTH);

        // Agregar paneles al panel principal
        panel.add(izquierdo, BorderLayout.CENTER);
        panel.add(derecho, BorderLayout.EAST);

        return panel;
    }

    public static void main(String[] args) {
        // Establecer el look and feel del sistema


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SolicitudesInterfaz().setVisible(true);
            }
        });
    }
}