package main;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

public class Main_frame extends JFrame {

	private JPanel contentPane;
	private final int ANCHO = 720, ALTO = 600;

	private static ConexionMySQL CONEXION;
	
	private static PanelContainer panelContainer;
	static Statement stmt;
	static ResultSet rset;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CONEXION = new ConexionMySQL("root", "", "monstipartydb");
			CONEXION.conectar(); // Conectar con la base
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_frame frame = new Main_frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_frame() {
		setResizable(false);
		setTitle("Monsti Palty");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, ANCHO, ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelContainer = new PanelContainer();
		panelContainer.setBounds(0, 0, 704, 527);
		contentPane.add(panelContainer);
		
		JPanel pnl_barra = new JPanel();
		pnl_barra.setBackground(Color.WHITE);
		pnl_barra.setBounds(0, 527, 704, 34);
		contentPane.add(pnl_barra);
		pnl_barra.setLayout(null);
		
		JButton btn_Salir = new JButton("SALIR");
		btn_Salir.setBackground(new Color(0, 0, 51));
		btn_Salir.setForeground(Color.WHITE);
		btn_Salir.setFont(new Font("Unispace", Font.BOLD, 14));
		btn_Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CONEXION.desconectar(); // Desconectar la base de datos
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dispose(); // Cerrar programa
			}
		});
		btn_Salir.setBounds(0, 0, 118, 34);
		pnl_barra.add(btn_Salir);
		
		JButton btn_Menu = new JButton("MENU");
		btn_Menu.setBackground(new Color(0, 0, 51));
		btn_Menu.setForeground(Color.WHITE);
		btn_Menu.setFont(new Font("Unispace", Font.BOLD, 14));
		btn_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pnl_menu.lbl_P1Avatar.setIcon(escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador1_AvatarSesion+".png", 506, 650)); // Sincronizar avatar con sesion
				Pnl_menu.lbl_P2Avatar.setIcon(escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador2_AvatarSesion+".png", 506, 650)); // Sincronizar avatar con sesion
				Pnl_menu.txt_P1Name.setText(Pnl_sesion.jugador1_NombreSesion); // Sincronizar nombre con sesion
				Pnl_menu.txt_P2Name.setText(Pnl_sesion.jugador2_NombreSesion); // Sincronizar nombre con sesion
				cambiarPanel("menu");  // Cambiar al panel menú
			}
		});
		btn_Menu.setBounds(118, 0, 234, 34);
		pnl_barra.add(btn_Menu);
		
		JButton btn_Historial = new JButton("HISTORIAL");
		btn_Historial.setBackground(new Color(0, 0, 51));
		btn_Historial.setForeground(Color.WHITE);
		btn_Historial.setFont(new Font("Unispace", Font.BOLD, 14));
		btn_Historial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pnl_historial.extraerHistorial(); // Extrae los datos para el historial
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				cambiarPanel("historial");  // Cambiar al panel historial
			}
		});
		btn_Historial.setBounds(352, 0, 176, 34);
		pnl_barra.add(btn_Historial);
		
		JButton btn_Sesion = new JButton("SESION");
		btn_Sesion.setBackground(new Color(0, 0, 51));
		btn_Sesion.setForeground(Color.WHITE);
		btn_Sesion.setFont(new Font("Unispace", Font.BOLD, 14));
		btn_Sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel("sesion"); // Cambiar al panel sesión
			}
		});
		btn_Sesion.setBounds(528, 0, 176, 34);
		pnl_barra.add(btn_Sesion);
	}
	
	// Cambiar entre los paneles
	public static void cambiarPanel(String nombrePanel) {
		panelContainer.getCardLayout().show(panelContainer, nombrePanel);
	}
	
	// Escalar una imagen para añadirla a un ImageIcon
	public static ImageIcon escalarImagen(String ruta, int ancho, int alto) {
		
		ImageIcon imagenIcono = new ImageIcon(PanelContainer.class.getResource(ruta));
        Image imagen = imagenIcono.getImage();  // Obtener la imagen original
        Image imagenEscalada;
        
        String extensionImagen = ruta.substring(ruta.length() - 3); // Extraer la extensión de la imagen
        
        // Comprobar si es un gif
        if (extensionImagen.equals("gif")) {
        	imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT); // Escalar el gif al tamaño deseado
        } else {
        	imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); // Escalar la imagen al tamaño deseado
        }

        // Crear un nuevo ImageIcon con la imagen escalada
        ImageIcon imagenEscaladaIcono = new ImageIcon(imagenEscalada);
		
		return imagenEscaladaIcono;
	}
	
}