package modulos;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import clases.Usuario;
import bd.UsuarioDAO;
import clases.SesionUsuario;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField campoCorreo;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        try {
            Login frame = new Login();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Login() {
        setTitle("Sistema de Solicitudes - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar ventana
        setSize(1920, 1080);
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(true); // Permitir redimensionar

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 64));
        panel.setBounds(0, 0, 1920, 1080);
        contentPanel.add(panel);
        panel.setLayout(null);

        // ============== LOGO PRINCIPAL ==============
        JLabel labelLogo = new JLabel();
        labelLogo.setBounds(560, 80, 800, 250);
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            ImageIcon originalIcon = new ImageIcon(Login.class.getResource("/img/cimarrones-tijuana.png"));
            Image img = originalIcon.getImage();
            Image imgEscalada = img.getScaledInstance(
                    labelLogo.getWidth(),
                    labelLogo.getHeight(),
                    Image.SCALE_SMOOTH
            );
            labelLogo.setIcon(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el logo");
        }
        panel.add(labelLogo);

        // ============== TÍTULO ==============
        JLabel lblTitulo = new JLabel("Sistema de Gestión de Solicitudes");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(560, 350, 800, 50);
        panel.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Iniciar Sesión");
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setBounds(560, 405, 800, 35);
        panel.add(lblSubtitulo);

        // ============== PANEL DE LOGIN ==============
        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(255, 255, 255, 230));
        panelLogin.setBounds(710, 460, 500, 320);
        panelLogin.setLayout(null);
        panel.add(panelLogin);

        // CORREO
        JLabel labelCorreo = new JLabel("Correo Institucional");
        labelCorreo.setForeground(new Color(0, 128, 64));
        labelCorreo.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelCorreo.setBounds(70, 35, 360, 30);
        panelLogin.add(labelCorreo);

        campoCorreo = new JTextField();
        campoCorreo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        campoCorreo.setBounds(70, 70, 360, 40);
        campoCorreo.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new Color(0, 128, 64), 2),
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panelLogin.add(campoCorreo);

        // CONTRASEÑA
        JLabel labelContraseña = new JLabel("Contraseña");
        labelContraseña.setForeground(new Color(0, 128, 64));
        labelContraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelContraseña.setBounds(70, 130, 360, 30);
        panelLogin.add(labelContraseña);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setBounds(70, 165, 360, 40);
        passwordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new Color(0, 128, 64), 2),
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panelLogin.add(passwordField);

        // BOTÓN INICIAR SESIÓN
        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setForeground(Color.BLACK);
        botonIniciarSesion.setBackground(new Color(220, 188, 35));
        botonIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 17));
        botonIniciarSesion.setBounds(70, 230, 360, 50);
        botonIniciarSesion.setOpaque(true);
        botonIniciarSesion.setContentAreaFilled(true);
        botonIniciarSesion.setBorderPainted(false);
        botonIniciarSesion.setFocusPainted(false);
        botonIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelLogin.add(botonIniciarSesion);

        // ============== ACCIÓN DE LOGIN ==============
        botonIniciarSesion.addActionListener(e -> iniciarSesion());

        // También permitir login con Enter
        passwordField.addActionListener(e -> iniciarSesion());
        campoCorreo.addActionListener(e -> passwordField.requestFocus());

        // ============== IMAGEN MASCOTA (DECORATIVA) ==============
        JLabel labelMascota = new JLabel();
        labelMascota.setBounds(50, 750, 200, 250);
        labelMascota.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            ImageIcon iconoMascota = new ImageIcon(Login.class.getResource("/img/mascotav2.png"));
            Image imgMascota = iconoMascota.getImage();
            Image imgMascotaEscalada = imgMascota.getScaledInstance(
                    labelMascota.getWidth(),
                    labelMascota.getHeight(),
                    Image.SCALE_SMOOTH
            );
            labelMascota.setIcon(new ImageIcon(imgMascotaEscalada));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la mascota");
        }
        panel.add(labelMascota);

        // ============== FOOTER ==============
        JLabel lblFooter = new JLabel("© 2025 Universidad Autónoma de Baja California");
        lblFooter.setForeground(new Color(255, 255, 255, 180));
        lblFooter.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        lblFooter.setBounds(0, 1020, 1920, 30);
        panel.add(lblFooter);
    }

    private void iniciarSesion() {
        String correo = campoCorreo.getText().trim();
        String contra = new String(passwordField.getPassword());

        // Validación de campos vacíos
        if (correo.isEmpty() || contra.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar formato de correo
        if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un correo válido",
                    "Formato incorrecto",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Intentar login
        Usuario usuario = UsuarioDAO.login(correo, contra);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this,
                    "Correo o contraseña incorrectos.\nVerifique sus credenciales e intente nuevamente.",
                    "Acceso Denegado",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText(""); // Limpiar contraseña
            return;
        }

        // Guardar usuario en sesión
        SesionUsuario.iniciar(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol()
        );

        JOptionPane.showMessageDialog(this,
                "¡Bienvenido, " + usuario.getNombre() + "!\n" +
                        "Rol: " + usuario.getRol(),
                "Acceso Concedido",
                JOptionPane.INFORMATION_MESSAGE);

        // Redirección según rol
        switch (usuario.getRol()) {
            case "Maestro":
                new DashboardM().setVisible(true);
                break;
            case "Soporte":
                new DashboardS(usuario.getCorreo()).setVisible(true);
                break;
            case "Administrador":
                new DashboardA(usuario.getCorreo()).setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(this,
                        "Rol no reconocido: " + usuario.getRol(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
        }

        dispose(); // Cerrar ventana de login
    }
}