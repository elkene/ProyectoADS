package modulos;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import clases.Solicitud;
import clases.SesionUsuario;
import bd.SolicitudDAO;
import java.util.ArrayList;

public class DashboardS extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaSolicitudes;
    private DefaultTableModel modelo;
    private JTextField campoBuscar;

    // Método para crear botones con bordes redondeados
    private JButton crearBotonRedondeado(String texto, Color bgColor, Color fgColor, int fontSize) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo redondeado
                if (getModel().isArmed()) {
                    g2.setColor(bgColor.darker());
                } else {
                    g2.setColor(bgColor);
                }
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 25, 25));

                // Borde
                g2.setColor(bgColor.darker());
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 25, 25));

                g2.dispose();
                super.paintComponent(g);
            }
        };

        boton.setFont(new Font("Tahoma", Font.BOLD, fontSize));
        boton.setForeground(fgColor);
        boton.setBackground(bgColor);
        boton.setBorder(new EmptyBorder(10, 20, 10, 20));
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setResizable(true);

        return boton;
    }

    public DashboardS(String correo) {
        setTitle("Panel de Soporte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        setLocationRelativeTo(null);

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 128, 64));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(null);
        setResizable(true);
        setContentPane(contentPane);

        // ---------------- IMAGEN LOGO ----------------
        JLabel labelLogo = new JLabel();
        labelLogo.setBounds(90, 24, 305, 248);
        labelLogo.setHorizontalAlignment(JLabel.CENTER);
        labelLogo.setVerticalAlignment(JLabel.CENTER);

        try {
            ImageIcon iconoOriginal = new ImageIcon(DashboardS.class.getResource("/img/cimarrones-tijuana.png"));
            Image img = iconoOriginal.getImage();
            Image imgEscalada = img.getScaledInstance(
                    labelLogo.getWidth(),
                    labelLogo.getHeight(),
                    Image.SCALE_SMOOTH
            );
            labelLogo.setIcon(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen del logo");
        }
        contentPane.add(labelLogo);

        // ---------------- IMAGEN LATERAL ----------------
        JLabel labelImagen3 = new JLabel();
        labelImagen3.setBounds(175, 280, 120, 100);
        labelImagen3.setHorizontalAlignment(JLabel.CENTER);
        labelImagen3.setVerticalAlignment(JLabel.CENTER);

        try {
            ImageIcon iconoOriginal5 = new ImageIcon(
                    DashboardS.class.getResource("/img/Captura de pantalla 2025-11-24 191833.png")
            );
            Image img3 = iconoOriginal5.getImage();
            Image imgEscalada3 = img3.getScaledInstance(
                    labelImagen3.getWidth(),
                    labelImagen3.getHeight(),
                    Image.SCALE_SMOOTH
            );
            labelImagen3.setIcon(new ImageIcon(imgEscalada3));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen lateral");
        }
        contentPane.add(labelImagen3);

        // ---------------- ETIQUETA USUARIO ----------------
        JLabel lblRol = new JLabel("Soporte");
        lblRol.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRol.setForeground(Color.WHITE);
        lblRol.setBounds(75, 400, 210, 40);
        contentPane.add(lblRol);

        JLabel lblUsuario = new JLabel(SesionUsuario.getCorreo());
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(75, 430, 310, 30);
        contentPane.add(lblUsuario);

        // ================== PANEL DERECHO - ÁREA DE TRABAJO ==================
        JPanel panelTrabajo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
            }
        };
        panelTrabajo.setOpaque(false);
        panelTrabajo.setBounds(480, 24, 1400, 750);
        panelTrabajo.setLayout(null);
        contentPane.add(panelTrabajo);

        // Título del panel
        JLabel lblTitulo = new JLabel("Gestión de Solicitudes");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitulo.setBounds(20, 10, 400, 40);
        panelTrabajo.add(lblTitulo);

        // ================== CAMPO DE BÚSQUEDA ==================
        JLabel lblBuscar = new JLabel("Buscar por título:");
        lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblBuscar.setBounds(20, 60, 150, 30);
        panelTrabajo.add(lblBuscar);

        // Campo de búsqueda con bordes redondeados
        campoBuscar = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo blanco
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                // Borde gris claro
                g2d.setColor(new Color(200, 200, 200));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));

                super.paintComponent(g);
            }
        };
        campoBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        campoBuscar.setBounds(160, 60, 300, 30);
        campoBuscar.setBorder(new EmptyBorder(0, 10, 0, 10));
        campoBuscar.setOpaque(false);
        panelTrabajo.add(campoBuscar);
        campoBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarTabla();
            }
        });

        // ================== TABLA DE SOLICITUDES ==================
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Título", "Estado", "Fecha", "Categoría"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Solo el estado es editable
            }
        };

        tablaSolicitudes = new JTable(modelo) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                // Bordes redondeados para celdas
                if (c instanceof JComponent) {
                    ((JComponent) c).setBorder(new EmptyBorder(5, 10, 5, 10));
                }

                return c;
            }
        };

        // Personalizar encabezado de tabla
        JTableHeader header = tablaSolicitudes.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(0, 128, 64));
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Tahoma", Font.BOLD, 14));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 1, Color.WHITE),
                        new EmptyBorder(10, 10, 10, 10)
                ));
                return label;
            }
        });

        tablaSolicitudes.setRowHeight(40);
        tablaSolicitudes.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tablaSolicitudes.setShowGrid(false);
        tablaSolicitudes.setIntercellSpacing(new Dimension(0, 0));

        // Borde redondeado para el scroll pane
        JScrollPane scroll = new JScrollPane(tablaSolicitudes) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                // Borde gris
                g2d.setColor(new Color(220, 220, 220));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));

                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No pintar borde por defecto
            }
        };
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBounds(20, 110, 1360, 500);
        scroll.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelTrabajo.add(scroll);

        // COLORES SEGÚN ESTADO
        colorearFilasPorEstado();

        // ComboBox en la columna Estado con diseño mejorado
        String[] estados = {"Pendiente", "En proceso", "Resuelto", "Cerrado"};
        JComboBox<String> comboEstados = new JComboBox<>(estados) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo blanco
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));

                // Borde
                g2d.setColor(new Color(200, 200, 200));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 10, 10));

                super.paintComponent(g);
            }
        };
        comboEstados.setFont(new Font("Tahoma", Font.PLAIN, 12));
        TableColumn columnaEstado = tablaSolicitudes.getColumnModel().getColumn(2);
        columnaEstado.setCellEditor(new DefaultCellEditor(comboEstados));

        // ================== BOTONES DE ACCIÓN ==================
        JButton btnActualizar = crearBotonRedondeado("Confirmar Cambio de Estado",
                new Color(220, 188, 35), Color.WHITE, 15);
        btnActualizar.setBounds(20, 630, 280, 45);
        btnActualizar.addActionListener(e -> actualizarEstado());
        panelTrabajo.add(btnActualizar);

        JButton btnVerDetalles = crearBotonRedondeado("Ver Detalles Completos",
                new Color(100, 100, 255), Color.WHITE, 15);
        btnVerDetalles.setBounds(320, 630, 280, 45);
        btnVerDetalles.addActionListener(e -> verDetalles());
        panelTrabajo.add(btnVerDetalles);

        JButton btnFiltrar = crearBotonRedondeado("Filtrar por Estado",
                new Color(70, 130, 180), Color.WHITE, 15);
        btnFiltrar.setBounds(620, 630, 250, 45);
        btnFiltrar.addActionListener(e -> filtrarPorEstado());
        panelTrabajo.add(btnFiltrar);

        // ================== BOTONES LATERALES ==================
        JButton btnRecargar = crearBotonRedondeado("Recargar Solicitudes",
                new Color(0, 128, 64), Color.WHITE, 16);
        btnRecargar.setBounds(75, 490, 310, 40);
        btnRecargar.addActionListener(e -> cargarSolicitudes());
        contentPane.add(btnRecargar);

        JButton btnEstadisticas = crearBotonRedondeado("Ver Estadísticas",
                new Color(0, 128, 64), Color.WHITE, 16);
        btnEstadisticas.setBounds(75, 545, 310, 40);
        btnEstadisticas.addActionListener(e -> mostrarEstadisticas());
        contentPane.add(btnEstadisticas);

        JButton btnCerrarSesion = crearBotonRedondeado("Cerrar Sesión",
                new Color(200, 60, 60), Color.WHITE, 16);
        btnCerrarSesion.setBounds(75, 655, 310, 40);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        contentPane.add(btnCerrarSesion);

        // Cargar datos iniciales
        cargarSolicitudes();
    }

    private void colorearFilasPorEstado() {
        tablaSolicitudes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Establecer bordes redondeados internos para celdas
                if (c instanceof JLabel) {
                    ((JLabel) c).setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(240, 240, 240)),
                            new EmptyBorder(5, 10, 5, 10)
                    ));
                    ((JLabel) c).setHorizontalAlignment(JLabel.LEFT);
                }

                // Obtener el estado de la fila real (no del RowSorter)
                int modelRow = table.convertRowIndexToModel(row);
                String estado = (String) table.getModel().getValueAt(modelRow, 2);

                // Establecer color según el estado
                switch (estado) {
                    case "Pendiente":
                        c.setBackground(new Color(255, 255, 153));
                        break;
                    case "En proceso":
                        c.setBackground(new Color(117, 143, 244));
                        break;
                    case "Resuelto":
                        c.setBackground(new Color(141, 228, 147));
                        break;
                    case "Cerrado":
                        c.setBackground(new Color(239, 107, 107));
                        break;
                    default:
                        c.setBackground(Color.WHITE);
                }

                // IMPORTANTE: Si la fila está seleccionada, mostrar el color de selección
                if (isSelected) {
                    c.setBackground(new Color(156, 176, 195));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });
    }

    private void cargarSolicitudes() {
        modelo.setRowCount(0);
        try {
            ArrayList<Solicitud> lista = SolicitudDAO.obtenerTodasSolicitudes();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No hay solicitudes registradas en el sistema.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            for (Solicitud sol : lista) {
                modelo.addRow(new Object[]{
                        sol.getId(),
                        sol.getTitulo(),
                        sol.getEstado(),
                        sol.getFecha(),
                        sol.getCategoria()
                });
            }
            colorearFilasPorEstado();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar solicitudes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void filtrarTabla() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tablaSolicitudes.setRowSorter(sorter);
        String texto = campoBuscar.getText().trim();
        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1));
        }
    }

    private void actualizarEstado() {
        int fila = tablaSolicitudes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione una solicitud de la tabla",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = (int) tablaSolicitudes.getValueAt(fila, 0);
            String nuevoEstado = (String) tablaSolicitudes.getValueAt(fila, 2);

            int confirmar = JOptionPane.showConfirmDialog(this,
                    "¿Confirma el cambio de estado a: " + nuevoEstado + "?",
                    "Confirmar actualización",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                if (SolicitudDAO.actualizarEstado(id, nuevoEstado)) {
                    JOptionPane.showMessageDialog(this,
                            "Estado actualizado correctamente",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    cargarSolicitudes();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Error al actualizar el estado en la base de datos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void verDetalles() {
        int fila = tablaSolicitudes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una solicitud para ver sus detalles",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        mostrarDescripcion((int) tablaSolicitudes.getValueAt(fila, 0));
    }

    private void mostrarDescripcion(int idSolicitud) {
        try {
            ArrayList<Solicitud> lista = SolicitudDAO.obtenerTodasSolicitudes();
            Solicitud sol = lista.stream()
                    .filter(s -> s.getId() == idSolicitud)
                    .findFirst()
                    .orElse(null);

            if (sol == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró la solicitud",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String mensaje = String.format(
                    "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                            "📌 TÍTULO: %s\n" +
                            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n" +
                            "📝 DESCRIPCIÓN:\n%s\n\n" +
                            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                            "📅 Fecha: %s\n" +
                            "📍 Categoría: %s\n" +
                            "🔖 Estado: %s\n" +
                            "👤 ID Usuario: %d\n" +
                            "🆔 ID Solicitud: %d\n" +
                            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━",
                    sol.getTitulo(),
                    sol.getDescripcion(),
                    sol.getFecha(),
                    sol.getCategoria(),
                    sol.getEstado(),
                    sol.getUsuarioId(),
                    sol.getId()
            );

            JTextArea textArea = new JTextArea(mensaje);
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 350));

            JOptionPane.showMessageDialog(this,
                    scrollPane,
                    "Detalles Completos de la Solicitud",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener detalles: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void filtrarPorEstado() {
        String[] opciones = {"Todos", "Pendiente", "En proceso", "Resuelto", "Cerrado"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el estado a filtrar:",
                "Filtrar por Estado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == null) return;

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tablaSolicitudes.setRowSorter(sorter);

        if (seleccion.equals("Todos")) {
            sorter.setRowFilter(null);
            campoBuscar.setText("");
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(seleccion, 2));
        }
    }

    private void mostrarEstadisticas() {
        try {
            ArrayList<Solicitud> lista = SolicitudDAO.obtenerTodasSolicitudes();

            int pendientes = 0, enProceso = 0, resueltas = 0, cerradas = 0;

            for (Solicitud sol : lista) {
                switch (sol.getEstado()) {
                    case "Pendiente": pendientes++; break;
                    case "En proceso": enProceso++; break;
                    case "Resuelto": resueltas++; break;
                    case "Cerrado": cerradas++; break;
                }
            }

            String mensaje = String.format(
                    "═══════════════════════════════════\n" +
                            "           ESTADÍSTICAS DEL SISTEMA\n" +
                            "═══════════════════════════════════\n\n" +
                            "📊 Total de solicitudes: %d\n\n" +
                            "⏳ Pendientes: %d\n" +
                            "🔄 En proceso: %d\n" +
                            "✅ Resueltas: %d\n" +
                            "🔒 Cerradas: %d\n\n" +
                            "═══════════════════════════════════",
                    lista.size(), pendientes, enProceso, resueltas, cerradas
            );

            JOptionPane.showMessageDialog(this, mensaje, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al calcular estadísticas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarSesion() {
        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Desea cerrar sesión?",
                "Confirmar cierre de sesión",
                JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            this.dispose();
            new Login().setVisible(true);
        }
    }
}