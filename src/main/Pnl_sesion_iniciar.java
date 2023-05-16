package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Pnl_sesion_iniciar extends JPanel {

	private JTextField nombreField;
	private JPasswordField passwordField;
	private JLabel lbl_ResultadoInicio;

	/**
	 * Create the panel.
	 */
	public Pnl_sesion_iniciar() {
		setLayout(null);

		JPanel pnl_sesion_iniciar = new JPanel();
		pnl_sesion_iniciar.setBackground(new Color(105, 105, 105));
		pnl_sesion_iniciar.setBounds(0, 0, 554, 241);
		add(pnl_sesion_iniciar);
		pnl_sesion_iniciar.setLayout(null);

		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setForeground(Color.WHITE);
		nombreLabel.setFont(new Font("Unispace", Font.PLAIN, 17));
		nombreLabel.setBounds(10, 51, 262, 29);
		pnl_sesion_iniciar.add(nombreLabel);

		nombreField = new JTextField(20);
		nombreField.setBackground(new Color(245, 245, 245));
		nombreField.setFont(new Font("Roboto", Font.PLAIN, 16));
		nombreField.setBounds(10, 80, 262, 40);
		pnl_sesion_iniciar.add(nombreField);

		JLabel contrasenaLabel = new JLabel("Contraseña");
		contrasenaLabel.setForeground(Color.WHITE);
		contrasenaLabel.setFont(new Font("Unispace", Font.PLAIN, 17));
		contrasenaLabel.setBounds(282, 51, 262, 29);
		pnl_sesion_iniciar.add(contrasenaLabel);

		passwordField = new JPasswordField(20);
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));
		passwordField.setBounds(282, 80, 262, 40);
		pnl_sesion_iniciar.add(passwordField);

		JButton btn_Iniciar = new JButton("INICIAR SESIÓN");
		btn_Iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					iniciarSesion();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_Iniciar.setForeground(Color.WHITE);
		btn_Iniciar.setBackground(new Color(30, 144, 255));
		btn_Iniciar.setFont(new Font("Unispace", Font.PLAIN, 14));
		btn_Iniciar.setBounds(188, 168, 179, 40);
		pnl_sesion_iniciar.add(btn_Iniciar);

		lbl_ResultadoInicio = new JLabel("");
		lbl_ResultadoInicio.setForeground(Color.RED);
		lbl_ResultadoInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ResultadoInicio.setFont(new Font("Roboto", Font.PLAIN, 12));
		lbl_ResultadoInicio.setBounds(10, 131, 534, 14);
		pnl_sesion_iniciar.add(lbl_ResultadoInicio);

	}

	private void iniciarSesion() throws SQLException {
		
		lbl_ResultadoInicio.setText("");

		// Extrae el nombre del campo
		String nombreSesion = nombreField.getText();

		// Extrae la contraseña del campo
		char[] passwordChar = passwordField.getPassword();
		String passwordSesion = new String(passwordChar);

		// Comrueba si existen los datos de la sesión seleccionados y los extrae para usar en el programa
		Main_frame.rset = ConexionMySQL.ejecutarSelect("SELECT * FROM Sesiones WHERE sesion_id = '" + nombreSesion + "' AND contraseña = '" + passwordSesion + "';");
		if (Main_frame.rset.next()) {
			Pnl_sesion.nombreSesion = Main_frame.rset.getString("sesion_id");
			Pnl_sesion.fechaSesion = Main_frame.rset.getString("fecha_creacion");
			Pnl_sesion.jugador1_NombreSesion = Main_frame.rset.getString("jugador1_nombre");
			Pnl_sesion.Jugador1_AvatarSesion = Main_frame.rset.getString("jugador1_avatar");
			Pnl_sesion.jugador2_NombreSesion = Main_frame.rset.getString("jugador2_nombre");
			Pnl_sesion.Jugador2_AvatarSesion = Main_frame.rset.getString("jugador2_avatar");
			
			Pnl_sesion.lbl_NombreSesion.setText(Pnl_sesion.nombreSesion);
			Pnl_sesion.lbl_FechaSesion.setText(Pnl_sesion.fechaSesion);
			
			lbl_ResultadoInicio.setText("Sesión iniciada");
			lbl_ResultadoInicio.setForeground(Color.GREEN);
			nombreField.setText("");
			passwordField.setText("");
			
			Pnl_sesion.btn_CerrarSesion.setEnabled(true);
			Pnl_sesion.btn_EliminarSesion.setEnabled(true);
			
		} else {
			lbl_ResultadoInicio.setText("Nombre o contraseña incorrectos");
			lbl_ResultadoInicio.setForeground(Color.RED);
			nombreField.setText("");
			passwordField.setText("");
		}
		Main_frame.rset.close();

	}

}
