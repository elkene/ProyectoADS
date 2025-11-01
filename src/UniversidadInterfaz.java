import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniversidadInterfaz extends JFrame {

    public UniversidadInterfaz() {
        // Configurar la ventana principal
        setTitle("Universidad Autónoma de Baja California");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(852, 1080);
        setLocationRelativeTo(null);

        // Crear el panel principal con un layout en borde
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        // Panel superior con el título de la universidad
        JPanel panelSuperior = crearPanelSuperior();

        // Panel central con los campos de inicio de sesión
        JPanel panelCentral = crearPanelCentral();

        // Panel inferior con el footer
        JPanel panelInferior = crearPanelInferior();

        // Agregar los paneles al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        // Agregar el panel principal a la ventana
        add(panelPrincipal);
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 51, 102)); // Azul oscuro universitario
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel titulo = new JLabel("UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);

        panel.add(titulo);
        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título de la facultad
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel facultad = new JLabel("FACULTAD DE DEPORTES", SwingConstants.CENTER);
        facultad.setFont(new Font("Arial", Font.BOLD, 24));
        facultad.setForeground(new Color(0, 51, 102));
        panel.add(facultad, gbc);

        // Espacio
        gbc.gridy++;
        panel.add(Box.createVerticalStrut(30), gbc);

        // Campo de dirección de correo
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblCorreo = new JLabel("Dirección de Correo:");
        lblCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblCorreo, gbc);

        gbc.gridx = 1;
        JTextField txtCorreo = new JTextField(20);
        txtCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(txtCorreo, gbc);

        // Campo de contraseña
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblContrasena, gbc);

        gbc.gridx = 1;
        JPasswordField txtContrasena = new JPasswordField(20);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(txtContrasena, gbc);

        // Botón de iniciar sesión
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciarSesion.setBackground(new Color(0, 51, 102));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setPreferredSize(new Dimension(150, 35));

        // Agregar acción al botón
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText();
                String contrasena = new String(txtContrasena.getPassword());

                // Aquí puedes agregar la lógica de autenticación
                JOptionPane.showMessageDialog(panel,
                        "Iniciando sesión para: " + correo,
                        "Inicio de Sesión",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(btnIniciarSesion, gbc);

        return panel;
    }

    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JLabel footer = new JLabel("GRAVENJU ENTERPRISES  |  GRAVENJU ENTERPRISES");
        footer.setFont(new Font("Arial", Font.PLAIN, 12));
        footer.setForeground(Color.DARK_GRAY);

        panel.add(footer);
        return panel;
    }

    public static void main(String[] args) {
        // Crear y mostrar la interfaz
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UniversidadInterfaz().setVisible(true);
            }
        });
    }
}