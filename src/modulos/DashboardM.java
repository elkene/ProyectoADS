package modulos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import bd.SolicitudDAO;
import clases.SesionUsuario;
import clases.Solicitud;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class DashboardM extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCorreoLabel;
    private JPanel panelDashboard;

    private ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();

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

        return boton;
    }

    // Método para crear paneles con bordes redondeados
    private JPanel crearPanelRedondeado(Color bgColor) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(bgColor);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
            }
        };
    }

    // Método para crear campos de texto redondeados
    private JTextField crearTextFieldRedondeado() {
        return new JTextField() {
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
    }

    // Método para crear áreas de texto redondeadas
    private JTextArea crearTextAreaRedondeada() {
        return new JTextArea() {
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
    }

    // Método para crear ComboBox redondeados
    private JComboBox<String> crearComboBoxRedondeado(String[] items) {
        return new JComboBox<>(items) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo blanco
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                // Borde
                g2d.setColor(new Color(200, 200, 200));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));

                super.paintComponent(g);
            }
        };
    }

    public DashboardM() {
        setTitle("Panel de Maestro");
        inicializarComponentes();
        lblCorreoLabel.setText(SesionUsuario.getCorreo());
    }

    private void inicializarComponentes() {
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
            ImageIcon iconoOriginal = new ImageIcon(DashboardM.class.getResource("/img/cimarrones-tijuana.png"));
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
                    DashboardM.class.getResource("/img/Captura de pantalla 2025-11-24 191833.png")
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
        JLabel lblRol = new JLabel("Maestro");
        lblRol.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRol.setForeground(Color.WHITE);
        lblRol.setBounds(75, 400, 210, 40);
        contentPane.add(lblRol);

        lblCorreoLabel = new JLabel();
        lblCorreoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCorreoLabel.setForeground(Color.WHITE);
        lblCorreoLabel.setBounds(75, 430, 310, 30);
        contentPane.add(lblCorreoLabel);

        // ================== PANEL DERECHO - ÁREA DE TRABAJO ==================
        panelDashboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
            }
        };
        panelDashboard.setOpaque(false);
        panelDashboard.setBounds(480, 24, 1400, 750);
        panelDashboard.setLayout(null);
        contentPane.add(panelDashboard);

        // ================== BOTONES LATERALES ==================
        JButton botonSolicitudes = crearBotonRedondeado("Mis Solicitudes",
                new Color(0, 128, 64), Color.WHITE, 16);
        botonSolicitudes.setBounds(75, 490, 310, 40);
        botonSolicitudes.addActionListener(e -> mostrarPanelSolicitudes());
        contentPane.add(botonSolicitudes);

        JButton btnCrear = crearBotonRedondeado("Crear Solicitud",
                new Color(0, 128, 64), Color.WHITE, 16);
        btnCrear.setBounds(75, 545, 310, 40);
        btnCrear.addActionListener(e -> mostrarFormularioCrear());
        contentPane.add(btnCrear);

        JButton btnBuscar = crearBotonRedondeado("Buscar Solicitud",
                new Color(0, 128, 64), Color.WHITE, 16);
        btnBuscar.setBounds(75, 600, 310, 40);
        btnBuscar.addActionListener(e -> mostrarBusqueda());
        contentPane.add(btnBuscar);

        JButton btnCerrarSesion = crearBotonRedondeado("Cerrar Sesión",
                new Color(200, 60, 60), Color.WHITE, 16);
        btnCerrarSesion.setBounds(75, 710, 310, 40);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        contentPane.add(btnCerrarSesion);

        mostrarPanelSolicitudes();
    }

    // --- PANEL DE MIS SOLICITUDES ---
    private void mostrarPanelSolicitudes() {
        panelDashboard.removeAll();

        // Título del panel
        JLabel lblTitulo = new JLabel("Mis Solicitudes");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitulo.setBounds(20, 10, 400, 40);
        panelDashboard.add(lblTitulo);

        try {
            listaSolicitudes = SolicitudDAO.obtenerSolicitudesDeUsuario(SesionUsuario.getIdUsuario());

            if (listaSolicitudes.isEmpty()) {
                JLabel lblVacio = new JLabel("No tienes solicitudes registradas");
                lblVacio.setFont(new Font("Tahoma", Font.ITALIC, 16));
                lblVacio.setForeground(Color.GRAY);
                lblVacio.setBounds(20, 80, 400, 30);
                panelDashboard.add(lblVacio);
            } else {
                JPanel contenedor = new JPanel();
                contenedor.setBackground(Color.WHITE);
                contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));

                // ScrollPane con bordes redondeados
                JScrollPane scroll = new JScrollPane(contenedor) {
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
                scroll.setBounds(20, 70, 1360, 600);
                scroll.setBorder(new EmptyBorder(5, 5, 5, 5));
                panelDashboard.add(scroll);

                for (Solicitud sol : listaSolicitudes) {
                    contenedor.add(crearTarjetaSolicitud(sol));
                    contenedor.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar solicitudes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        panelDashboard.revalidate();
        panelDashboard.repaint();
    }

    private JPanel crearTarjetaSolicitud(Solicitud sol) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo blanco
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                // Borde
                g2d.setColor(new Color(220, 220, 220));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));

                super.paintComponent(g);
            }
        };

        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(1340, 80));
        panel.setPreferredSize(new Dimension(1340, 80));

        // Indicador de color según estado (redondeado en la esquina superior izquierda)
        JPanel indicadorEstado = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
            }
        };
        indicadorEstado.setBounds(0, 0, 8, 80);
        indicadorEstado.setOpaque(false);

        switch (sol.getEstado()) {
            case "Pendiente":
                indicadorEstado.setBackground(new Color(255, 193, 7));
                break;
            case "En proceso":
                indicadorEstado.setBackground(new Color(255, 152, 0));
                break;
            case "Resuelto":
                indicadorEstado.setBackground(new Color(76, 175, 80));
                break;
            case "Cerrado":
                indicadorEstado.setBackground(new Color(96, 125, 139));
                break;
            default:
                indicadorEstado.setBackground(Color.LIGHT_GRAY);
        }
        panel.add(indicadorEstado);

        JLabel lblTitulo = new JLabel("📋 " + sol.getTitulo());
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(20, 5, 500, 25);
        panel.add(lblTitulo);

        JLabel lblCategoria = new JLabel("Categoría: " + sol.getCategoria());
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCategoria.setForeground(Color.GRAY);
        lblCategoria.setBounds(20, 30, 300, 20);
        panel.add(lblCategoria);

        JLabel lblFecha = new JLabel("📅 " + sol.getFecha());
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblFecha.setBounds(550, 10, 200, 25);
        panel.add(lblFecha);

        JLabel lblEstado = new JLabel("🔖 " + sol.getEstado());
        lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEstado.setBounds(770, 10, 200, 25);

        switch (sol.getEstado()) {
            case "Pendiente":
                lblEstado.setForeground(new Color(255, 152, 0));
                break;
            case "En proceso":
                lblEstado.setForeground(new Color(33, 150, 243));
                break;
            case "Resuelto":
                lblEstado.setForeground(new Color(76, 175, 80));
                break;
            case "Cerrado":
                lblEstado.setForeground(new Color(96, 125, 139));
                break;
        }
        panel.add(lblEstado);

        JButton btnVer = crearBotonRedondeado("Ver Detalles",
                new Color(0, 128, 64), Color.WHITE, 12);
        btnVer.setBounds(1000, 15, 150, 35);
        btnVer.addActionListener(e -> mostrarDetallesSolicitud(sol));
        panel.add(btnVer);

        JButton btnEditar = crearBotonRedondeado("Editar",
                new Color(33, 150, 243), Color.WHITE, 12);
        btnEditar.setBounds(1170, 15, 100, 35);
        btnEditar.addActionListener(e -> editarSolicitud(sol));
        panel.add(btnEditar);

        return panel;
    }

    private void mostrarDetallesSolicitud(Solicitud sol) {
        String mensaje = String.format(
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "📌 TÍTULO: %s\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n" +
                        "📝 DESCRIPCIÓN:\n%s\n\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "📅 Fecha: %s\n" +
                        "📍 Categoría: %s\n" +
                        "🔖 Estado: %s\n" +
                        "🆔 ID Solicitud: %d\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━",
                sol.getTitulo(),
                sol.getDescripcion(),
                sol.getFecha(),
                sol.getCategoria(),
                sol.getEstado(),
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
                "Detalles de la Solicitud",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarSolicitud(Solicitud sol) {
        if (!sol.getEstado().equals("Pendiente")) {
            JOptionPane.showMessageDialog(this,
                    "Solo se pueden editar solicitudes con estado 'Pendiente'",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Aquí puedes implementar la lógica de edición
        JOptionPane.showMessageDialog(this,
                "Función de edición en desarrollo",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // --- FORMULARIO CREAR SOLICITUD ---
    private void mostrarFormularioCrear() {
        panelDashboard.removeAll();

        JLabel titulo = new JLabel("Crear Nueva Solicitud");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        titulo.setBounds(20, 10, 400, 40);
        panelDashboard.add(titulo);

        JLabel lblTitulo = new JLabel("Título de la solicitud:");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitulo.setBounds(40, 80, 300, 20);
        panelDashboard.add(lblTitulo);

        JTextField campoTitulo = crearTextFieldRedondeado();
        campoTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        campoTitulo.setBounds(40, 105, 500, 35);
        campoTitulo.setBorder(new EmptyBorder(0, 10, 0, 10));
        campoTitulo.setOpaque(false);
        panelDashboard.add(campoTitulo);

        JLabel lblDesc = new JLabel("Descripción del problema:");
        lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDesc.setBounds(40, 160, 300, 20);
        panelDashboard.add(lblDesc);

        JTextArea campoDescripcion = crearTextAreaRedondeada();
        campoDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        campoDescripcion.setLineWrap(true);
        campoDescripcion.setWrapStyleWord(true);
        campoDescripcion.setBorder(new EmptyBorder(10, 10, 10, 10));
        campoDescripcion.setOpaque(false);

        JScrollPane scrollDesc = new JScrollPane(campoDescripcion) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                // Borde
                g2d.setColor(new Color(200, 200, 200));
                g2d.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));

                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No pintar borde por defecto
            }
        };
        scrollDesc.setOpaque(false);
        scrollDesc.getViewport().setOpaque(false);
        scrollDesc.setBounds(40, 185, 500, 150);
        scrollDesc.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelDashboard.add(scrollDesc);

        JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFecha.setBounds(40, 360, 200, 20);
        panelDashboard.add(lblFecha);

        JTextField campoFecha = crearTextFieldRedondeado();
        campoFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        campoFecha.setBounds(40, 385, 250, 35);
        campoFecha.setBorder(new EmptyBorder(0, 10, 0, 10));
        campoFecha.setOpaque(false);
        panelDashboard.add(campoFecha);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCategoria.setBounds(40, 440, 200, 20);
        panelDashboard.add(lblCategoria);

        JComboBox<String> comboCategoria = crearComboBoxRedondeado(new String[]{"Infraestructura", "Equipo", "Sistema", "Otro"});
        comboCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboCategoria.setBounds(40, 465, 250, 35);
        comboCategoria.setOpaque(false);
        comboCategoria.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelDashboard.add(comboCategoria);

        JButton btnGuardar = crearBotonRedondeado("Guardar Solicitud",
                new Color(0, 128, 64), Color.WHITE, 15);
        btnGuardar.setBounds(40, 530, 250, 45);
        panelDashboard.add(btnGuardar);

        JButton btnCancelar = crearBotonRedondeado("Cancelar",
                new Color(200, 60, 60), Color.WHITE, 15);
        btnCancelar.setBounds(310, 530, 230, 45);
        btnCancelar.addActionListener(e -> mostrarPanelSolicitudes());
        panelDashboard.add(btnCancelar);

        btnGuardar.addActionListener(e -> {
            String t = campoTitulo.getText().trim();
            String d = campoDescripcion.getText().trim();
            String f = campoFecha.getText().trim();
            String c = (String) comboCategoria.getSelectedItem();
            int id = SesionUsuario.getIdUsuario();

            if (t.isEmpty() || d.isEmpty() || f.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Debe llenar todos los campos obligatorios",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!f.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this,
                        "Formato de fecha incorrecto.\nUse el formato: AAAA-MM-DD\nEjemplo: 2025-01-15",
                        "Error de formato",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Solicitud sol = new Solicitud(t, d, f, c, id);

                boolean guardado = SolicitudDAO.crearSolicitud(sol);

                if (guardado) {
                    JOptionPane.showMessageDialog(this,
                            "✅ Solicitud creada con éxito\n\nEstado: PENDIENTE\nSu solicitud será revisada por el equipo de soporte.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    mostrarPanelSolicitudes();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "❌ Error al registrar la solicitud en la base de datos.\n\nVerifique:\n- Conexión a la base de datos\n- Datos ingresados",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error inesperado al crear la solicitud:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        panelDashboard.revalidate();
        panelDashboard.repaint();
    }

    private void mostrarBusqueda() {
        String busqueda = JOptionPane.showInputDialog(this,
                "Ingrese el título o palabra clave de la solicitud:",
                "Buscar Solicitud",
                JOptionPane.QUESTION_MESSAGE);

        if (busqueda == null || busqueda.trim().isEmpty()) {
            return;
        }

        try {
            ArrayList<Solicitud> resultados = new ArrayList<>();
            for (Solicitud sol : listaSolicitudes) {
                if (sol.getTitulo().toLowerCase().contains(busqueda.toLowerCase())) {
                    resultados.add(sol);
                }
            }

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron solicitudes con: \"" + busqueda + "\"",
                        "Sin resultados",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarResultadosBusqueda(resultados);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarResultadosBusqueda(ArrayList<Solicitud> resultados) {
        panelDashboard.removeAll();

        JLabel lblTitulo = new JLabel("Resultados de Búsqueda (" + resultados.size() + ")");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitulo.setBounds(20, 10, 500, 40);
        panelDashboard.add(lblTitulo);

        JButton btnVolver = crearBotonRedondeado("← Volver a Mis Solicitudes",
                new Color(0, 128, 64), Color.WHITE, 13);
        btnVolver.setBounds(20, 60, 250, 35);
        btnVolver.addActionListener(e -> mostrarPanelSolicitudes());
        panelDashboard.add(btnVolver);

        JPanel contenedor = new JPanel();
        contenedor.setBackground(Color.WHITE);
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));

        // ScrollPane con bordes redondeados
        JScrollPane scroll = new JScrollPane(contenedor) {
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
        scroll.setBounds(20, 110, 1360, 600);
        scroll.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelDashboard.add(scroll);

        for (Solicitud sol : resultados) {
            contenedor.add(crearTarjetaSolicitud(sol));
            contenedor.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        panelDashboard.revalidate();
        panelDashboard.repaint();
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