package main;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Pnl_sesion_crear extends JPanel {

	private JTextField nombreField;
	private JPasswordField passwordField, confirmarPasswordField;
	private JLabel lbl_ErrorContrasena, lbl_ErrorConfContrasena, lbl_ErrorNombre, lbl_ResultadoCreacion;

	/**
	 * Create the panel.
	 */
	public Pnl_sesion_crear() {
		setLayout(null);

		JPanel pnl_sesion_crear = new JPanel();
		pnl_sesion_crear.setBackground(new Color(105, 105, 105));
		pnl_sesion_crear.setBounds(0, 0, 554, 241);
		add(pnl_sesion_crear);
		pnl_sesion_crear.setLayout(null);

		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setForeground(Color.WHITE);
		nombreLabel.setFont(new Font("Unispace", Font.PLAIN, 17));
		nombreLabel.setBounds(10, 22, 262, 29);
		pnl_sesion_crear.add(nombreLabel);

		nombreField = new JTextField(20);
		nombreField.setBackground(new Color(245, 245, 245));
		nombreField.setFont(new Font("Roboto", Font.PLAIN, 16));
		nombreField.setBounds(10, 51, 262, 40);
		pnl_sesion_crear.add(nombreField);

		JLabel contrasenaLabel = new JLabel("Contraseña");
		contrasenaLabel.setForeground(Color.WHITE);
		contrasenaLabel.setFont(new Font("Unispace", Font.PLAIN, 17));
		contrasenaLabel.setBounds(10, 134, 262, 29);
		pnl_sesion_crear.add(contrasenaLabel);

		passwordField = new JPasswordField(20);
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));
		passwordField.setBounds(10, 163, 262, 40);
		pnl_sesion_crear.add(passwordField);

		JLabel confirmarContrasenaLabel = new JLabel("Confirmar contraseña");
		confirmarContrasenaLabel.setForeground(Color.WHITE);
		confirmarContrasenaLabel.setFont(new Font("Unispace", Font.PLAIN, 17));
		confirmarContrasenaLabel.setBounds(282, 134, 262, 29);
		pnl_sesion_crear.add(confirmarContrasenaLabel);

		confirmarPasswordField = new JPasswordField(20);
		confirmarPasswordField.setBackground(new Color(245, 245, 245));
		confirmarPasswordField.setFont(new Font("Roboto", Font.PLAIN, 16));
		confirmarPasswordField.setBounds(282, 163, 262, 40);
		pnl_sesion_crear.add(confirmarPasswordField);

		lbl_ErrorContrasena = new JLabel("");
		lbl_ErrorContrasena.setForeground(Color.RED);
		lbl_ErrorContrasena.setFont(new Font("Roboto", Font.PLAIN, 12));
		lbl_ErrorContrasena.setBounds(20, 214, 252, 16);
		pnl_sesion_crear.add(lbl_ErrorContrasena);

		lbl_ErrorConfContrasena = new JLabel("");
		lbl_ErrorConfContrasena.setForeground(Color.RED);
		lbl_ErrorConfContrasena.setFont(new Font("Roboto", Font.PLAIN, 12));
		lbl_ErrorConfContrasena.setBounds(292, 214, 252, 16);
		pnl_sesion_crear.add(lbl_ErrorConfContrasena);

		lbl_ErrorNombre = new JLabel("");
		lbl_ErrorNombre.setForeground(Color.RED);
		lbl_ErrorNombre.setFont(new Font("Roboto", Font.PLAIN, 12));
		lbl_ErrorNombre.setBounds(20, 102, 252, 16);
		pnl_sesion_crear.add(lbl_ErrorNombre);

		lbl_ResultadoCreacion = new JLabel("");
		lbl_ResultadoCreacion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ResultadoCreacion.setForeground(Color.WHITE);
		lbl_ResultadoCreacion.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbl_ResultadoCreacion.setBounds(323, 103, 179, 14);
		pnl_sesion_crear.add(lbl_ResultadoCreacion);

		JButton btn_Crear = new JButton("CREAR SESIÓN");
		btn_Crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crearSesion();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_Crear.setForeground(Color.WHITE);
		btn_Crear.setBackground(new Color(30, 144, 255));
		btn_Crear.setFont(new Font("Unispace", Font.PLAIN, 14));
		btn_Crear.setBounds(323, 50, 179, 40);
		pnl_sesion_crear.add(btn_Crear);

	}

	// Comprueba que los campos estas ingresados correctamente y crea una sesión
	private void crearSesion() throws SQLException {
		
		lbl_ResultadoCreacion.setText("");
		lbl_ErrorNombre.setText("");
		lbl_ErrorContrasena.setText("");
		lbl_ErrorConfContrasena.setText("");
		
		// Extrae el nombre del campo
		String nombreSesion = nombreField.getText();
		
		// Extrae la contraseña del campo
		char[] passwordChar = passwordField.getPassword();
		String password = new String(passwordChar);
		
		// Extrae la contraeña confirmada del campo
		char[] confirmarPasswordChar = confirmarPasswordField.getPassword();
		String confirmarPassword = new String(confirmarPasswordChar);
		
		boolean nomb = false, pass = false, confpass = false;
		
		// Verifica si el nombre está introducido correctamente
		if (nombreSesion.length() == 0) {
			lbl_ErrorNombre.setText("Debes introducir un nombre");
			
		} else if (nombreSesion.length() > 20) {
			lbl_ErrorNombre.setText("Nombre no puede tener más de 20 caracteres");
			
		} else {
			nomb = true;
		}
		
		// Verifica si la contraseña está introducidas correctamente
		if (password.length() == 0) {
			lbl_ErrorContrasena.setText("Debes introducir una contraseña");
			passwordField.setText("");
			confirmarPasswordField.setText("");
			
		} else if (password.length() < 6) {
			lbl_ErrorContrasena.setText("Contraseña debe tener 6 caracteres mínimo");
			passwordField.setText("");
			confirmarPasswordField.setText("");
			
		} else {
			pass = true;
			
			// Verifica si las dos contraseñas son iguales
			if (!confirmarPassword.equals(password)) {
				lbl_ErrorConfContrasena.setText("Ambas contraseñas deben de ser iguales");
				confirmarPasswordField.setText("");

			} else {
				confpass = true;
			}
		}
		
		// Crea la sesión en la base de datos
		if (nomb == true && pass == true && confpass == true) {
			
			String verificarConsultaSQL = "";
			LocalDate fechaActual = LocalDate.now();
			Date fechaSQL = Date.valueOf(fechaActual); // Extraer Fecha
			
			// Crea la sesión
			String consultaSQL = "INSERT INTO Sesiones (sesion_id, fecha_creacion, contraseña, jugador1_nombre, jugador1_avatar, jugador2_nombre, jugador2_avatar)"
					+ "VALUES ('" + nombreSesion + "', '" + fechaSQL + "', '" + password + "', 'Jugador1', 'A', 'Jugador2', 'A');";
			ConexionMySQL.ejecutarInsertDeleteUpdate(consultaSQL);
			
			// Extrae el nombre de la sesión desde la base de datos
			Main_frame.rset = ConexionMySQL.ejecutarSelect("SELECT sesion_id FROM Sesiones WHERE sesion_id = '"+nombreSesion+"';");
			if (Main_frame.rset.next()) {
				verificarConsultaSQL = Main_frame.rset.getString("sesion_id");
			}

			// Comprueba si la sesión se ha creado correctamente
			if (verificarConsultaSQL.equals(nombreSesion)) {
				lbl_ResultadoCreacion.setText("Sesión creada con éxito");
				lbl_ResultadoCreacion.setForeground(Color.GREEN);
				
				nombreField.setText("");
				passwordField.setText("");
				confirmarPasswordField.setText("");
				
			} else {
				lbl_ResultadoCreacion.setText("Fallo al crear la sesión");
				lbl_ResultadoCreacion.setForeground(Color.RED);
			}
			Main_frame.rset.close();
		}
	}
}
