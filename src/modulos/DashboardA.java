package modulos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Solicitud;



	public class DashboardA extends JFrame {

		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private String correoUsuario;
		private JLabel lblCorreoLabel;
		private JTable table;
		private DefaultTableModel tableModel;
		private JPanel panelDashboard;
		
		private ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();
		
		public DashboardA(String correo) {
			this.correoUsuario = correo;
			inicializarComponentes();
			lblCorreoLabel.setText(correoUsuario);
		}

		private void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 64));
		panel.setBounds(0, 0, 1920, 1080);
		contentPane.add(panel);
		panel.setLayout(null);

		panelDashboard = new JPanel();
		panelDashboard.setBounds(480, 24, 1020, 750);
		panel.add(panelDashboard);
		panelDashboard.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Solicitudes");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(30, 50, 400, 50);
		panelDashboard.add(lblNewLabel_2);

		// ---------------- IMAGEN LOGO ----------------
		JLabel labelLogo = new JLabel();
		labelLogo.setBounds(90, 24, 305, 248);
		labelLogo.setHorizontalAlignment(JLabel.CENTER);
		labelLogo.setVerticalAlignment(JLabel.CENTER);

		ImageIcon iconoOriginal = new ImageIcon(DashboardM.class.getResource("/img/cimarrones-tijuana.png"));
		Image img = iconoOriginal.getImage();
		Image imgEscalada = img.getScaledInstance(
				labelLogo.getWidth(),
				labelLogo.getHeight(),
				Image.SCALE_SMOOTH
		);
		labelLogo.setIcon(new ImageIcon(imgEscalada));
		panel.add(labelLogo);

		// ---------------- IMAGEN LATERAL ----------------
		JLabel labelImagen3 = new JLabel();
		labelImagen3.setBounds(175, 280, 120, 100);
		labelImagen3.setHorizontalAlignment(JLabel.CENTER);
		labelImagen3.setVerticalAlignment(JLabel.CENTER);

		ImageIcon iconoOriginal5 = new ImageIcon(
				DashboardM.class.getResource("/img/Captura de pantalla 2025-11-29 235822.png")
		);
		Image img3 = iconoOriginal5.getImage();
		Image imgEscalada3 = img3.getScaledInstance(
				labelImagen3.getWidth(),
				labelImagen3.getHeight(),
				Image.SCALE_SMOOTH
		);
		labelImagen3.setIcon(new ImageIcon(imgEscalada3));
		panel.add(labelImagen3);

		JLabel lblNewLabel = new JLabel("Adminsitrador");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(75, 430, 210, 40);
		panel.add(lblNewLabel);

		// ===== ETIQUETA DEL CORREO =====
		lblCorreoLabel = new JLabel("Administrador@gmail.com");
		lblCorreoLabel.setForeground(Color.WHITE);
		lblCorreoLabel.setBounds(75, 461, 290, 30);
		panel.add(lblCorreoLabel);

		// ---------------- BOTONES LATERALES ----------------
		JButton botonSolicitudes = new JButton("Solicitudes");
		botonSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						//mostrarPanelSolicitudes();
			}
		});
		botonSolicitudes.setForeground(new Color(255, 255, 255));
		botonSolicitudes.setBackground(new Color(0, 128, 64));
		botonSolicitudes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		botonSolicitudes.setBounds(75, 512, 310, 40);
		panel.add(botonSolicitudes);

		JButton btnNewButton = new JButton("Agregar Usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mostrarFormularioCrear();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 64));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(75, 565, 310, 40);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Modificar Solicitudes");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 128, 64));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(75, 625, 310, 40);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Admninistrar cuentas");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(0, 128, 64));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(75, 625, 310, 40);
		panel.add(btnNewButton_2);

		}

	}
