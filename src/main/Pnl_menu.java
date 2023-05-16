package main;

import java.awt.Color;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

public class Pnl_menu extends JPanel {

	private String arrAv[] = { "A", "Callabo", "Camalar", "Cucudrulu", "Gato", "Peter", "Robohoto", "Smile", "Surmarino" };
	
	static JTextField txt_P1Name, txt_P2Name;
	static JLabel lbl_P1Avatar, lbl_P2Avatar;

	/**
	 * Create the panel.
	 */
	public Pnl_menu() {
		setLayout(null);
		
		JLayeredPane pnl_menu = new JLayeredPane();
		pnl_menu.setBackground(Color.WHITE);
		pnl_menu.setBounds(0, 0, 704, 527);
		add(pnl_menu);
		pnl_menu.setLayout(null);
		
		JLabel lbl_fondo = new JLabel();
		pnl_menu.setLayer(lbl_fondo, 0);
		lbl_fondo.setIcon(new ImageIcon(Pnl_menu.class.getResource("/recursos/fondos/Fondo2.png")));
		lbl_fondo.setBounds(0, 0, 704, 527);
		pnl_menu.add(lbl_fondo);
		
		txt_P1Name = new JTextField();
		txt_P1Name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Pnl_sesion.jugador1_NombreSesion = Pnl_menu.txt_P1Name.getText();
				try {
					guardarDatosJugador(1, "n");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txt_P1Name.setBackground(Color.BLACK);
		txt_P1Name.setForeground(Color.WHITE);
		txt_P1Name.setFont(new Font("Roboto", Font.PLAIN, 16));
		pnl_menu.setLayer(txt_P1Name, 2);
		txt_P1Name.setHorizontalAlignment(SwingConstants.CENTER);
		txt_P1Name.setText("Jugador1");
		txt_P1Name.setColumns(10);
		txt_P1Name.setBounds(69, 70, 212, 26);
		pnl_menu.add(txt_P1Name);
		
		txt_P2Name = new JTextField();
		txt_P2Name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Pnl_sesion.jugador2_NombreSesion = Pnl_menu.txt_P2Name.getText();
				try {
					guardarDatosJugador(2, "n");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txt_P2Name.setBackground(Color.BLACK);
		txt_P2Name.setForeground(Color.WHITE);
		txt_P2Name.setFont(new Font("Roboto", Font.PLAIN, 16));
		pnl_menu.setLayer(txt_P2Name, 2);
		txt_P2Name.setHorizontalAlignment(SwingConstants.CENTER);
		txt_P2Name.setText("Jugador2");
		txt_P2Name.setColumns(10);
		txt_P2Name.setBounds(423, 70, 212, 26);
		pnl_menu.add(txt_P2Name);
		
		lbl_P1Avatar = new JLabel("");
		pnl_menu.setLayer(lbl_P1Avatar, 2);
		lbl_P1Avatar.setIcon(Main_frame.escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador1_AvatarSesion+".png", 506, 650));
		lbl_P1Avatar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P1Avatar.setBounds(109, 105, 132, 132);
		pnl_menu.add(lbl_P1Avatar);
		
		lbl_P2Avatar = new JLabel("");
		pnl_menu.setLayer(lbl_P2Avatar, 2);
		lbl_P2Avatar.setIcon(Main_frame.escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador2_AvatarSesion+".png", 506, 650));
		lbl_P2Avatar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P2Avatar.setBounds(463, 105, 132, 132);
		pnl_menu.add(lbl_P2Avatar);
		
		JButton btn_P1TLeftAvatar = new JButton("<");
		btn_P1TLeftAvatar.setForeground(Color.WHITE);
		btn_P1TLeftAvatar.setBackground(Color.BLACK);
		pnl_menu.setLayer(btn_P1TLeftAvatar, 2);
		btn_P1TLeftAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pnl_sesion.Jugador1_AvatarSesion = anteriorAvatar(Pnl_sesion.Jugador1_AvatarSesion);
				lbl_P1Avatar.setIcon(Main_frame.escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador1_AvatarSesion+".png", 506, 650)); // Cambiar al avatar anterior
				try {
					guardarDatosJugador(1, "a");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_P1TLeftAvatar.setBounds(69, 156, 41, 30);
		pnl_menu.add(btn_P1TLeftAvatar);
		
		JButton btn_P1TRigthAvatar = new JButton(">");
		btn_P1TRigthAvatar.setForeground(Color.WHITE);
		btn_P1TRigthAvatar.setBackground(Color.BLACK);
		pnl_menu.setLayer(btn_P1TRigthAvatar, 2);
		btn_P1TRigthAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Pnl_sesion.Jugador1_AvatarSesion = siguienteAvatar(Pnl_sesion.Jugador1_AvatarSesion);
				lbl_P1Avatar.setIcon(Main_frame.escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador1_AvatarSesion+".png", 506, 650)); // Cambiar al siguiente avatar
				try {
					guardarDatosJugador(1, "a");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_P1TRigthAvatar.setBounds(240, 156, 41, 30);
		pnl_menu.add(btn_P1TRigthAvatar);
		
		JButton btn_P2TLeftAvatar = new JButton("<");
		btn_P2TLeftAvatar.setForeground(Color.WHITE);
		btn_P2TLeftAvatar.setBackground(Color.BLACK);
		pnl_menu.setLayer(btn_P2TLeftAvatar, 2);
		btn_P2TLeftAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pnl_sesion.Jugador2_AvatarSesion = anteriorAvatar(Pnl_sesion.Jugador2_AvatarSesion);
				lbl_P2Avatar.setIcon(Main_frame.escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador2_AvatarSesion+".png", 506, 650)); // Cambiar al avatar anterior
				try {
					guardarDatosJugador(2, "a");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_P2TLeftAvatar.setBounds(423, 156, 41, 30);
		pnl_menu.add(btn_P2TLeftAvatar);
		
		JButton btn_P2TRigthAvatar = new JButton(">");
		btn_P2TRigthAvatar.setForeground(Color.WHITE);
		btn_P2TRigthAvatar.setBackground(Color.BLACK);
		pnl_menu.setLayer(btn_P2TRigthAvatar, 2);
		btn_P2TRigthAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pnl_sesion.Jugador2_AvatarSesion = siguienteAvatar(Pnl_sesion.Jugador2_AvatarSesion);
				lbl_P2Avatar.setIcon(Main_frame.escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador2_AvatarSesion+".png", 506, 650)); // Cambiar al siguiente avatar
				try {
					guardarDatosJugador(2, "a");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_P2TRigthAvatar.setBounds(594, 156, 41, 30);
		pnl_menu.add(btn_P2TRigthAvatar);
		
		JButton btn_Dados = new JButton("DADOS");
		btn_Dados.setForeground(Color.WHITE);
		btn_Dados.setBackground(new Color(25, 25, 112));
		btn_Dados.setFont(new Font("Unispace", Font.PLAIN, 14));
		pnl_menu.setLayer(btn_Dados, 1);
		btn_Dados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarDatosJugador("dados");
				Main_frame.cambiarPanel("dados"); // Cambiar al panel dados
			}
		});
		btn_Dados.setBounds(190, 326, 324, 45);
		pnl_menu.add(btn_Dados);
		
		JButton btn_Ahorcado = new JButton("AHORCADO");
		btn_Ahorcado.setForeground(Color.WHITE);
		btn_Ahorcado.setBackground(new Color(25, 25, 112));
		btn_Ahorcado.setFont(new Font("Unispace", Font.PLAIN, 14));
		pnl_menu.setLayer(btn_Ahorcado, 1);
		btn_Ahorcado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarDatosJugador("ahorcado");
				Main_frame.cambiarPanel("ahorcado"); // Cambiar al panel ahorcado
			}
		});
		btn_Ahorcado.setBounds(190, 382, 324, 45);
		pnl_menu.add(btn_Ahorcado);
		
		JLabel lbl_CapsulaP1_1 = new JLabel("");
		lbl_CapsulaP1_1.setIcon(new ImageIcon(Pnl_menu.class.getResource("/recursos/general/CapsulaDelante.png")));
		lbl_CapsulaP1_1.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_menu.setLayer(lbl_CapsulaP1_1, 3);
		lbl_CapsulaP1_1.setBounds(110, 154, 130, 103);
		pnl_menu.add(lbl_CapsulaP1_1);
		
		JLabel lbl_CapsulaP2_1 = new JLabel("");
		pnl_menu.setLayer(lbl_CapsulaP2_1, 3);
		lbl_CapsulaP2_1.setIcon(new ImageIcon(Pnl_menu.class.getResource("/recursos/general/CapsulaDelante.png")));
		lbl_CapsulaP2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CapsulaP2_1.setBounds(464, 154, 130, 103);
		pnl_menu.add(lbl_CapsulaP2_1);
		
		JLabel lbl_CapsulaP1_2 = new JLabel("");
		lbl_CapsulaP1_2.setIcon(new ImageIcon(Pnl_menu.class.getResource("/recursos/general/CapsulaAtras.png")));
		pnl_menu.setLayer(lbl_CapsulaP1_2, 1);
		lbl_CapsulaP1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CapsulaP1_2.setBounds(110, 154, 130, 103);
		pnl_menu.add(lbl_CapsulaP1_2);
		
		JLabel lbl_CapsulaP2_2 = new JLabel("");
		lbl_CapsulaP2_2.setIcon(new ImageIcon(Pnl_menu.class.getResource("/recursos/general/CapsulaAtras.png")));
		pnl_menu.setLayer(lbl_CapsulaP2_2, 1);
		lbl_CapsulaP2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CapsulaP2_2.setBounds(464, 154, 130, 103);
		pnl_menu.add(lbl_CapsulaP2_2);
		
		JLabel lbl_FondoJugadores= new JLabel();
		lbl_FondoJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_menu.setLayer(lbl_FondoJugadores, 1);
		lbl_FondoJugadores.setBounds(51, 54, 602, 203);
		pnl_menu.add(lbl_FondoJugadores);
		
	}
	
	// Pasa al siguiente avatar en el array
	private String siguienteAvatar(String avatar) {
		
		String currentAvatar = avatar;
		int i = Arrays.asList(arrAv).indexOf(currentAvatar);
		
		if (i < arrAv.length-1) {
			i++;
		} else {
			i = 0;
		}
		currentAvatar = arrAv[i];
		
		return currentAvatar;
	}
	
	// Pasa al anterior avatar en el array
	private String anteriorAvatar(String avatar) {
		
		String currentAvatar = avatar;
		int i = Arrays.asList(arrAv).indexOf(currentAvatar);
		
		if (i > 0) {
			i--;
		} else {
			i = 8;
		}
		currentAvatar = arrAv[i];
		
		return currentAvatar;
	}
	
	// Pasa los datos de los jugadores del menú a los juegos
	private void pasarDatosJugador(String juego) {
		
		if (juego.equals("dados")) {		
			Pnl_dados.lbl_P1Avatar.setIcon(new ImageIcon(Pnl_menu.class.getResource("/recursos/avatares/"+Pnl_sesion.Jugador1_AvatarSesion+".png")));
			Pnl_dados.lbl_P2Avatar.setIcon(new ImageIcon(Pnl_menu.class.getResource("/recursos/avatares/"+Pnl_sesion.Jugador2_AvatarSesion+".png")));
			Pnl_dados.lbl_Player1.setText(txt_P1Name.getText());
			Pnl_dados.lbl_Player2.setText(txt_P2Name.getText());
			Pnl_dados.lbl_Turno.setText(txt_P1Name.getText());
			
		} else if (juego.equals("ahorcado")) {
			Pnl_ahorcado.lbl_P1Avatar.setIcon(Main_frame.escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador1_AvatarSesion+".png", 506, 650));
			Pnl_ahorcado.lbl_P2Avatar.setIcon(Main_frame.escalarImagen("/recursos/avatares/"+Pnl_sesion.Jugador2_AvatarSesion+".png", 506, 650));
			Pnl_ahorcado.lbl_Player1.setText(txt_P1Name.getText());
			Pnl_ahorcado.lbl_Player2.setText(txt_P2Name.getText());
			Pnl_ahorcado.lbl_Turno.setText("Turno de: "+txt_P1Name.getText());
		}
	}
	
	// Guardar dinámicamente los datos de los jugadores en la base de datos si hay una sesión activa
	private void guardarDatosJugador(int jugador, String tipo) throws SQLException {
		
		// Comprobar si hay una sesión activa
		if (!Pnl_sesion.nombreSesion.equals("")) {
			String consultaSQL = "";
			
			if (jugador == 1) {		
				if (tipo.equals("n")) {
					consultaSQL = "UPDATE Sesiones SET jugador1_nombre = '" + Pnl_sesion.jugador1_NombreSesion + "' WHERE sesion_id = '" + Pnl_sesion.nombreSesion + "';";
					
				} else if (tipo.equals("a")) {
					consultaSQL = "UPDATE Sesiones SET jugador1_avatar = '" + Pnl_sesion.Jugador1_AvatarSesion + "' WHERE sesion_id = '" + Pnl_sesion.nombreSesion + "';";
				}
				
			} else if (jugador == 2) {
				if (tipo.equals("n")) {
					consultaSQL = "UPDATE Sesiones SET jugador2_nombre = '" + Pnl_sesion.jugador2_NombreSesion + "' WHERE sesion_id = '" + Pnl_sesion.nombreSesion + "';";
					
				} else if (tipo.equals("a")) {
					consultaSQL = "UPDATE Sesiones SET jugador2_avatar = '" + Pnl_sesion.Jugador2_AvatarSesion + "' WHERE sesion_id = '" + Pnl_sesion.nombreSesion + "';";
				}
			}
			ConexionMySQL.ejecutarInsertDeleteUpdate(consultaSQL); // Ejecutar consulta
		}
		
	}
	
}
