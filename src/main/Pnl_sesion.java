package main;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Pnl_sesion extends JPanel {

	private Pnl_sesion_crear pnl_sesion_crear = new Pnl_sesion_crear();
	private Pnl_sesion_iniciar pnl_sesion_iniciar = new Pnl_sesion_iniciar();
	private CardLayout cardLayout;
	
	static JLabel lbl_NombreSesion, lbl_FechaSesion;
	static JButton btn_CerrarSesion, btn_EliminarSesion;
	static String nombreSesion = "", fechaSesion, jugador1_NombreSesion = "Jugador1", Jugador1_AvatarSesion = "A", jugador2_NombreSesion = "Jugador2", Jugador2_AvatarSesion = "A";
	private JLayeredPane pnl_sesion;
	
	/**
	 * Create the panel.
	 */
	public Pnl_sesion() {
		setLayout(null);
		
		pnl_sesion = new JLayeredPane();
		pnl_sesion.setBounds(0, 0, 704, 527);
		add(pnl_sesion);
		pnl_sesion.setLayout(null);
		
		JLabel lbl_fondo = new JLabel("");
		lbl_fondo.setIcon(new ImageIcon(Pnl_sesion.class.getResource("/recursos/fondos/Fondo2.png")));
		lbl_fondo.setBounds(0, 0, 704, 527);
		pnl_sesion.add(lbl_fondo);
		
		JPanel pnl_sesion_vista = new JPanel();
		pnl_sesion_vista.setBackground(new Color(105, 105, 105));
		pnl_sesion.setLayer(pnl_sesion_vista, 1);
		pnl_sesion_vista.setBounds(138, 57, 554, 241);
		pnl_sesion.add(pnl_sesion_vista);
		pnl_sesion_vista.setLayout(new CardLayout(0, 0));
		
		cardLayout = (CardLayout) pnl_sesion_vista.getLayout();
		
		JButton btn_CrearSesion = new JButton("Crear sesión");
		btn_CrearSesion.setForeground(Color.WHITE);
		btn_CrearSesion.setBackground(new Color(25, 25, 112));
		btn_CrearSesion.setFont(new Font("Unispace", Font.PLAIN, 10));
		pnl_sesion.setLayer(btn_CrearSesion, 1);
		btn_CrearSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnl_sesion_vista.add(pnl_sesion_crear, "crear");
				cardLayout.show(pnl_sesion_vista, "crear");
				SwingUtilities.updateComponentTreeUI(pnl_sesion_vista);
				pnl_sesion_vista.repaint(); // Cambiar al panel crear sesión
			}
		});
		btn_CrearSesion.setBounds(10, 183, 118, 115);
		pnl_sesion.add(btn_CrearSesion);
		
		JButton btn_IniciarSesion = new JButton("Iniciar sesión");
		btn_IniciarSesion.setForeground(Color.WHITE);
		btn_IniciarSesion.setBackground(new Color(25, 25, 112));
		btn_IniciarSesion.setFont(new Font("Unispace", Font.PLAIN, 10));
		pnl_sesion.setLayer(btn_IniciarSesion, 1);
		btn_IniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnl_sesion_vista.add(pnl_sesion_iniciar, "iniciar");
				cardLayout.show(pnl_sesion_vista, "iniciar");
				SwingUtilities.updateComponentTreeUI(pnl_sesion_vista);
				pnl_sesion_vista.repaint(); // Cambiar al panel iniciar sesión
			}
		});
		btn_IniciarSesion.setBounds(10, 56, 118, 115);
		pnl_sesion.add(btn_IniciarSesion);
		
		btn_CerrarSesion = new JButton("Cerrar");
		btn_CerrarSesion.setEnabled(false);
		btn_CerrarSesion.setForeground(Color.WHITE);
		btn_CerrarSesion.setBackground(new Color(65, 105, 225));
		btn_CerrarSesion.setFont(new Font("Unispace", Font.PLAIN, 11));
		pnl_sesion.setLayer(btn_CerrarSesion, 1);
		btn_CerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		btn_CerrarSesion.setBounds(138, 309, 118, 70);
		pnl_sesion.add(btn_CerrarSesion);
		
		btn_EliminarSesion = new JButton("Eliminar");
		btn_EliminarSesion.setEnabled(false);
		btn_EliminarSesion.setForeground(Color.WHITE);
		btn_EliminarSesion.setBackground(new Color(65, 105, 225));
		btn_EliminarSesion.setFont(new Font("Unispace", Font.PLAIN, 11));
		pnl_sesion.setLayer(btn_EliminarSesion, 1);
		btn_EliminarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarSesion();
			}
		});
		btn_EliminarSesion.setBounds(138, 390, 118, 70);
		pnl_sesion.add(btn_EliminarSesion);
		
		JPanel pnl_sesion_actual = new JPanel();
		pnl_sesion_actual.setBackground(new Color(211, 211, 211));
		pnl_sesion.setLayer(pnl_sesion_actual, 1);
		pnl_sesion_actual.setBounds(266, 309, 426, 151);
		pnl_sesion.add(pnl_sesion_actual);
		pnl_sesion_actual.setLayout(null);
		
		JLabel lbl_SesionActual = new JLabel("SESION ACTUAL");
		lbl_SesionActual.setFont(new Font("Unispace", Font.PLAIN, 17));
		lbl_SesionActual.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SesionActual.setBounds(10, 11, 406, 36);
		pnl_sesion_actual.add(lbl_SesionActual);
		
		lbl_NombreSesion = new JLabel("¡Inicia sesión para ver tus partidas!");
		lbl_NombreSesion.setFont(new Font("Unispace", Font.PLAIN, 14));
		lbl_NombreSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NombreSesion.setBounds(10, 68, 406, 19);
		pnl_sesion_actual.add(lbl_NombreSesion);
		
		lbl_FechaSesion = new JLabel("");
		lbl_FechaSesion.setForeground(Color.DARK_GRAY);
		lbl_FechaSesion.setFont(new Font("Unispace", Font.PLAIN, 11));
		lbl_FechaSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_FechaSesion.setBounds(10, 93, 406, 14);
		pnl_sesion_actual.add(lbl_FechaSesion);
		
	}
	
	// Cierra la sesión actual
	private void cerrarSesion() {
		
		nombreSesion = "";
		fechaSesion = "";
		
		btn_CerrarSesion.setEnabled(false);
		btn_EliminarSesion.setEnabled(false);

		lbl_NombreSesion.setText("¡Inicia sesión para ver tus partidas!");
		lbl_FechaSesion.setText("");
		
	}
	
	// Elimina la sesión actual
	private void eliminarSesion() {

        String[] botones = { "Eliminar", "Cancelar" };
        JLabel label = new JLabel("Esta acción es irreversible");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        int opcion = JOptionPane.showOptionDialog(pnl_sesion, label, "¿Estás seguro?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, botones, botones[0]);

        if (opcion == 0) {
            // Se pulsó el botón Eliminar
        	String consultaSQL = "DELETE FROM Sesiones WHERE sesion_id = '" + nombreSesion + "';";
			try {
				ConexionMySQL.ejecutarInsertDeleteUpdate(consultaSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cerrarSesion();
			
        } else {
            // Se pulsó el botón Cancelar
        }
	}
}
