import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Login extends JFrame {

    private JComboBox<String> comboTipoUsuario;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;

    public Login() {
        // Configurar la ventana principal
        setTitle("Universidad Autónoma de Baja California");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700); // Aumentar el tamaño para acomodar las imágenes
        setLocationRelativeTo(null);

        // Crear el panel principal con un layout en borde
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel superior con el título de la universidad
        JPanel panelSuperior = crearPanelSuperior();

        // Panel central que contendrá las imágenes y el formulario
        JPanel panelCentral = crearPanelCentralConImagenes();

        // Panel inferior con el footer
        JPanel panelInferior = crearPanelInferior();

        // Agregar los paneles al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        // Agregar el panel principal a la ventana
        add(panelPrincipal);
    }

    private JPanel crearPanelCentralConImagenes() {
        // Panel principal que divide en dos partes: imágenes y formulario
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));
        panelPrincipal.setBackground(new Color(2, 71, 49));

        // Panel izquierdo para las imágenes
        JPanel panelImagenes = crearPanelImagenes();

        // Panel derecho para el formulario de login
        JPanel panelFormulario = crearPanelFormulario();

        panelPrincipal.add(panelImagenes);
        panelPrincipal.add(panelFormulario);

        return panelPrincipal;
    }

    private JPanel crearPanelImagenes() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBackground(new Color(2, 71, 49));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Primera imagen (superior)
        JLabel imagen1 = new JLabel();
        imagen1.setHorizontalAlignment(SwingConstants.CENTER);
        imagen1.setVerticalAlignment(SwingConstants.CENTER);

        // Segunda imagen (inferior)
        JLabel imagen2 = new JLabel();
        imagen2.setHorizontalAlignment(SwingConstants.CENTER);
        imagen2.setVerticalAlignment(SwingConstants.CENTER);

        try {
            // Cargar imágenes (reemplaza con las rutas de tus imágenes)
            // Si las imágenes están en el directorio del proyecto:
            ImageIcon icono1 = new ImageIcon("assets/mascotav2.png");
            ImageIcon icono2 = new ImageIcon("assets/cimarronv2.png"); // Cambia por tu imagen

            // Si no encuentras las imágenes, usar placeholders
            if (icono1.getIconWidth() <= 0) {
                // Crear placeholder para la primera imagen
                icono1 = crearPlaceholder("IMAGEN DEPORTE 1", new Color(0, 121, 52));
            }
            if (icono2.getIconWidth() <= 0) {
                // Crear placeholder para la segunda imagen
                icono2 = crearPlaceholder("IMAGEN DEPORTE 2", new Color(206, 142, 0));
            }

            // Escalar las imágenes si es necesario
            Image img1 = icono1.getImage().getScaledInstance(193, 323, Image.SCALE_SMOOTH);
            Image img2 = icono2.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);

            imagen1.setIcon(new ImageIcon(img1));
            imagen2.setIcon(new ImageIcon(img2));

        } catch (Exception e) {
            // Si hay error al cargar imágenes, crear placeholders
            ImageIcon placeholder1 = crearPlaceholder("IMAGEN 1 NO ENCONTRADA", new Color(0, 121, 52));
            ImageIcon placeholder2 = crearPlaceholder("IMAGEN 2 NO ENCONTRADA", new Color(206, 142, 0));

            imagen1.setIcon(placeholder1);
            imagen2.setIcon(placeholder2);
        }

        panel.add(imagen1);
        panel.add(imagen2);

        return panel;
    }

    private ImageIcon crearPlaceholder(String texto, Color color) {
        // Crear una imagen placeholder con texto
        int width = 350;
        int height = 200;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Configurar gráficos
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        // Centrar texto
        FontMetrics fm = g2d.getFontMetrics();
        int x = (width - fm.stringWidth(texto)) / 2;
        int y = (height - fm.getHeight()) / 2 + fm.getAscent();

        g2d.drawString(texto, x, y);
        g2d.dispose();

        return new ImageIcon(image);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(2, 71, 49));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título de la facultad
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel facultad = new JLabel("FACULTAD DE DEPORTES", SwingConstants.CENTER);
        facultad.setFont(new Font("Arial", Font.BOLD, 24));
        facultad.setForeground(Color.WHITE);
        panel.add(facultad, gbc);

        // Espacio
        gbc.gridy++;
        panel.add(Box.createVerticalStrut(20), gbc);

        // Selector de tipo de usuario
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblTipoUsuario = new JLabel("Tipo de Usuario:");
        lblTipoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTipoUsuario.setForeground(Color.WHITE);
        panel.add(lblTipoUsuario, gbc);

        gbc.gridx = 1;
        String[] tiposUsuario = {"Estudiante", "Profesor", "Administrador", "Invitado"};
        comboTipoUsuario = new JComboBox<>(tiposUsuario);
        comboTipoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        comboTipoUsuario.setBackground(Color.WHITE);
        panel.add(comboTipoUsuario, gbc);

        // Espacio
        gbc.gridy++;
        panel.add(Box.createVerticalStrut(10), gbc);

        // Campo de dirección de correo
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblCorreo = new JLabel("Dirección de Correo:");
        lblCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCorreo.setForeground(Color.WHITE);
        panel.add(lblCorreo, gbc);

        gbc.gridx = 1;
        txtCorreo = new JTextField(20);
        txtCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(txtCorreo, gbc);

        // Campo de contraseña
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContrasena.setForeground(Color.WHITE);
        panel.add(lblContrasena, gbc);

        gbc.gridx = 1;
        txtContrasena = new JPasswordField(20);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContrasena.setBackground(Color.WHITE);
        panel.add(txtContrasena, gbc);

        // Enlace de recuperar contraseña
        gbc.gridy++;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JButton btnRecuperar = new JButton("Recuperar Contraseña");
        btnRecuperar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnRecuperar.setBorderPainted(false);
        btnRecuperar.setContentAreaFilled(false);
        btnRecuperar.setForeground(Color.WHITE);
        btnRecuperar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel,
                        "Función de recuperación de contraseña en desarrollo",
                        "Recuperar Contraseña",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(btnRecuperar, gbc);

        // Botón de iniciar sesión
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciarSesion.setBackground(new Color(206, 142, 0));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setPreferredSize(new Dimension(180, 40));

        // Agregar acción al botón
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoUsuario = (String) comboTipoUsuario.getSelectedItem();
                String correo = txtCorreo.getText();
                String contrasena = new String(txtContrasena.getPassword());

                // Validar campos
                if (correo.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(panel,
                            "Por favor, complete todos los campos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Mostrar información del inicio de sesión
                String mensaje = String.format(
                        "Tipo de Usuario: %s\nCorreo: %s\nContraseña: %s",
                        tipoUsuario, correo, "••••••"
                );

                JOptionPane.showMessageDialog(panel,
                        mensaje,
                        "Inicio de Sesión Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(btnIniciarSesion, gbc);

        return panel;
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 121, 52));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel titulo = new JLabel("UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);

        panel.add(titulo);
        return panel;
    }

    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 121, 52));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Texto superior del footer
        JLabel footerSuperior = new JLabel("CAKENJU ENTERPRISES  |  CAKENJU ENTERPRISES", SwingConstants.CENTER);
        footerSuperior.setFont(new Font("Arial", Font.PLAIN, 12));
        footerSuperior.setForeground(Color.WHITE);

        // Texto inferior del copyright
        JLabel footerInferior = new JLabel("Copyright © 2025 CAKENJU ENTERPRISES. All rights reserved.", SwingConstants.CENTER);
        footerInferior.setFont(new Font("Arial", Font.PLAIN, 10));
        footerInferior.setForeground(Color.WHITE);
        footerInferior.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        panel.add(footerSuperior, BorderLayout.CENTER);
        panel.add(footerInferior, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        // Crear y mostrar la interfaz
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}