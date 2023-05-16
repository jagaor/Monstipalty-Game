package main;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Pnl_ahorcado extends JPanel {

	private String[] palabras = {"JAVA", "PROGRAMACION", "INFORMATICA", "COMPUTADORA", "ALGORITMO"};
	private ImageIcon[] vidas;

	static JLabel lbl_P2Avatar, lbl_P1Avatar, lbl_Player1, lbl_Player2, lbl_Turno;
	
	private JLabel lbl_PalabraAdivinar, lbl_Evento, lbl_P1Num, lbl_P2Num, lbl_P1Compuerta, lbl_P2Compuerta;
	private JButton btn_Reiniciar, btn_Advivinar;
	private JTextField textField;
	
	private Random random;
	private Timer timer1, timer2;
	private String palabraSeleccionada, palabraAdivinada, ganador, perdedor;
	private int erroresP1 = 0, erroresP2 = 0, maxErrores = 10, jugador, posNew;
	private boolean isPlayer1;

	/**
	 * Create the panel.
	 */
	public Pnl_ahorcado() {
		setLayout(null);

		// Vidas de los jugadores
		vidas = new ImageIcon[11];
		vidas[0] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/0.png"));
		vidas[1] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/1.png"));
		vidas[2] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/2.png"));
		vidas[3] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/3.png"));
		vidas[4] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/4.png"));
		vidas[5] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/5.png"));
		vidas[6] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/6.png"));
		vidas[7] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/7.png"));
		vidas[8] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/8.png"));
		vidas[9] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/9.png"));
		vidas[10] = new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/numeros/10.png"));

		JLayeredPane pnl_ahorcado = new JLayeredPane();
		pnl_ahorcado.setBounds(0, 0, 704, 527);
		add(pnl_ahorcado);
		pnl_ahorcado.setLayout(null);

		lbl_PalabraAdivinar = new JLabel("PALABRA SECRETA");
		lbl_PalabraAdivinar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lbl_PalabraAdivinar.setForeground(Color.WHITE);
		pnl_ahorcado.setLayer(lbl_PalabraAdivinar, 1);
		lbl_PalabraAdivinar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_PalabraAdivinar.setBounds(213, 241, 276, 31);
		pnl_ahorcado.add(lbl_PalabraAdivinar);

		btn_Reiniciar = new JButton("REINICIAR");
		btn_Reiniciar.setBackground(new Color(25, 25, 112));
		btn_Reiniciar.setForeground(Color.WHITE);
		pnl_ahorcado.setLayer(btn_Reiniciar, 1);
		btn_Reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarJuegoAhorcado();
			}
		});
		btn_Reiniciar.setFont(new Font("Unispace", Font.PLAIN, 10));
		btn_Reiniciar.setBounds(305, 283, 93, 28);
		pnl_ahorcado.add(btn_Reiniciar);
		btn_Reiniciar.setVisible(false);

		lbl_Turno = new JLabel("Turno de: NombreP1");
		lbl_Turno.setFont(new Font("Unispace", Font.PLAIN, 16));
		lbl_Turno.setForeground(Color.WHITE);
		pnl_ahorcado.setLayer(lbl_Turno, 1);
		lbl_Turno.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Turno.setBounds(213, 36, 276, 31);
		pnl_ahorcado.add(lbl_Turno);

		lbl_P2Avatar = new JLabel("");
		pnl_ahorcado.setLayer(lbl_P2Avatar, 1);
		lbl_P2Avatar.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/avatares/A.png")));
		lbl_P2Avatar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P2Avatar.setBounds(520, 128, 159, 132);
		pnl_ahorcado.add(lbl_P2Avatar);

		lbl_P1Avatar = new JLabel("");
		pnl_ahorcado.setLayer(lbl_P1Avatar, 1);
		lbl_P1Avatar.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/avatares/A.png")));
		lbl_P1Avatar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P1Avatar.setBounds(22, 128, 159, 132);
		pnl_ahorcado.add(lbl_P1Avatar);

		lbl_P2Compuerta = new JLabel("");
		pnl_ahorcado.setLayer(lbl_P2Compuerta, 2);
		lbl_P2Compuerta.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/general/CompuertaD.png")));
		lbl_P2Compuerta.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P2Compuerta.setBounds(533, 234, 192, 132);
		pnl_ahorcado.add(lbl_P2Compuerta);

		lbl_P1Compuerta = new JLabel("");
		pnl_ahorcado.setLayer(lbl_P1Compuerta, 2);
		lbl_P1Compuerta.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/general/CompuertaI.png")));
		lbl_P1Compuerta.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P1Compuerta.setBounds(-22, 234, 185, 132);
		pnl_ahorcado.add(lbl_P1Compuerta);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");
			}
		});
		textField.setForeground(Color.WHITE);
		textField.setBackground(Color.BLACK);
		textField.setFont(new Font("Roboto", Font.PLAIN, 18));
		pnl_ahorcado.setLayer(textField, 1);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(213, 78, 276, 31);
		pnl_ahorcado.add(textField);
		textField.setColumns(10);

		btn_Advivinar = new JButton("Adivinar");
		btn_Advivinar.setBackground(Color.BLACK);
		btn_Advivinar.setForeground(Color.WHITE);
		btn_Advivinar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detectarLetraOPalabra();
			}
		});
		btn_Advivinar.setFont(new Font("Unispace", Font.PLAIN, 10));
		pnl_ahorcado.setLayer(btn_Advivinar, 1);
		btn_Advivinar.setBounds(283, 120, 136, 23);
		pnl_ahorcado.add(btn_Advivinar);

		JLabel lbl_Fondo = new JLabel("");
		lbl_Fondo.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/fondos/FondoMenu.png")));
		lbl_Fondo.setBounds(0, 0, 704, 527);
		pnl_ahorcado.add(lbl_Fondo);

		lbl_P1Num = new JLabel("");
		lbl_P1Num.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P1Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/10.png", 40, 40));
		pnl_ahorcado.setLayer(lbl_P1Num, 3);
		lbl_P1Num.setBounds(5, 235, 50, 50);
		pnl_ahorcado.add(lbl_P1Num);

		lbl_P2Num = new JLabel("");
		pnl_ahorcado.setLayer(lbl_P2Num, 3);
		lbl_P2Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/10.png", 40, 40));
		lbl_P2Num.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P2Num.setBounds(649, 234, 50, 50);
		pnl_ahorcado.add(lbl_P2Num);

		lbl_Player1 = new JLabel("NombreP1");
		pnl_ahorcado.setLayer(lbl_Player1, 1);
		lbl_Player1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Player1.setForeground(Color.WHITE);
		lbl_Player1.setFont(new Font("Unispace", Font.PLAIN, 14));
		lbl_Player1.setBounds(4, 103, 194, 23);
		pnl_ahorcado.add(lbl_Player1);

		lbl_Player2 = new JLabel("NombreP2");
		pnl_ahorcado.setLayer(lbl_Player2, 1);
		lbl_Player2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Player2.setForeground(Color.WHITE);
		lbl_Player2.setFont(new Font("Unispace", Font.PLAIN, 14));
		lbl_Player2.setBounds(502, 103, 194, 23);
		pnl_ahorcado.add(lbl_Player2);

		lbl_Evento = new JLabel("");
		pnl_ahorcado.setLayer(lbl_Evento, 1);
		lbl_Evento.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Evento.setForeground(Color.WHITE);
		lbl_Evento.setFont(new Font("Roboto", Font.PLAIN, 17));
		lbl_Evento.setBounds(192, 182, 317, 28);
		pnl_ahorcado.add(lbl_Evento);

		palabraAleatoria(); // Selecciona una palabra aleatoria del array
		
		// Temporizador para tirar al jugador 1
		timer1 = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caerAnimacionP1();
			}
		});
		// Temporizador para tirar al jugador 2
		timer2 = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caerAnimacionP2();
			}
		});
	}

	public void palabraAleatoria() {
		// Seleccionar una palabra aleatoria del conjunto de palabras predefinidas y la oculta
		random = new Random();
		palabraSeleccionada = palabras[random.nextInt(palabras.length)];
		palabraAdivinada = "";
		for (int i = 0; i < palabraSeleccionada.length(); i++) {
			palabraAdivinada += "-";
		}
		lbl_PalabraAdivinar.setText(palabraAdivinada);

		// Iniciar el juego con el jugador 1
		isPlayer1 = true;
	}

	private void detectarLetraOPalabra() {
		String entrada = textField.getText().toUpperCase();

		// Verificar si se ha ingresado una letra o palabra
		if (entrada.length() == 1) {
			char letra = entrada.charAt(0);

			// Verificar si la letra ya fue adivinada
			if (palabraAdivinada.indexOf(letra) != -1) {
				lbl_Evento.setText("Letra ya encontra, prueba con otra");
			} else {
				boolean letraEncontrada = false;
				StringBuilder nuevaPalabraAdivinada = new StringBuilder(palabraAdivinada);

				// Buscar la letra en la palabra seleccionada
				for (int i = 0; i < palabraSeleccionada.length(); i++) {
					if (palabraSeleccionada.charAt(i) == letra) {
						letraEncontrada = true;
						nuevaPalabraAdivinada.setCharAt(i, letra);
					}
				}

				// Actualizar la palabra adivinada en la interfaz
				palabraAdivinada = nuevaPalabraAdivinada.toString();
				lbl_PalabraAdivinar.setText(palabraAdivinada);

				// Verificar si la letra se encontró o no
				if (letraEncontrada) {
					lbl_Evento.setText("¡Letra encontrada!");

					// Verificar si se completó la palabra
					if (palabraAdivinada.indexOf("-") == -1) {
						
						lbl_PalabraAdivinar.setText(palabraSeleccionada);
						lbl_Evento.setText("¡Palabra resuelta! ¡¡¡GANASTE!!!");
						btn_Advivinar.setEnabled(false);
						
						if (isPlayer1 == true) {
							jugador = 2;
							caer(jugador);
							lbl_P2Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/0.png", 40, 40));
						} else {
							jugador = 1;
							caer(jugador);
							lbl_P1Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/0.png", 40, 40));
						}
						
					}

					if (isPlayer1 == true) {
						isPlayer1 = false;
						lbl_Turno.setText("Turno de: " + lbl_Player2.getText());
					} else {
						isPlayer1 = true;
						lbl_Turno.setText("Turno de: " + lbl_Player1.getText());
					}

				} else {

					// Verificar si es jugador 1 o 2
					if (isPlayer1 == true) {
						erroresP1++;

						// Verificar si se alcanzó el máximo de errores permitidos
						if (erroresP1 == maxErrores) {
							lbl_P1Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/0.png", 40, 40));
							lbl_Evento.setText("¡Letra incorrecta! PERDISTE");
							jugador = 1;
							caer(jugador);
						} else {
							lbl_P1Num.setIcon(Main_frame
									.escalarImagen("/recursos/numeros/" + (maxErrores - erroresP1) + ".png", 40, 40));
							lbl_Evento.setText("¡Letra incorrecta! Te quedan " + (maxErrores - erroresP1) + " vidas");
							isPlayer1 = false;
							lbl_Turno.setText("Turno de: " + lbl_Player2.getText());
						}
					} else {
						erroresP2++;

						// Verificar si se alcanzó el máximo de errores permitidos
						if (erroresP2 == maxErrores) {
							lbl_P2Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/0.png", 40, 40));
							lbl_Evento.setText("¡Letra incorrecta! PERDISTE");
							jugador = 2;
							caer(jugador);
						} else {
							lbl_P2Num.setIcon(Main_frame
									.escalarImagen("/recursos/numeros/" + (maxErrores - erroresP2) + ".png", 40, 40));
							lbl_Evento.setText("¡Letra incorrecta! Te quedan " + (maxErrores - erroresP2) + " vidas");
							isPlayer1 = true;
							lbl_Turno.setText("Turno de: " + lbl_Player1.getText());
						}
					}
				}
			}
		} else if (entrada.length() >= 1) {
			
			// Verificar si se está resolviendo la palabra
			if (entrada.equals(palabraSeleccionada)) {
				
				lbl_PalabraAdivinar.setText(palabraSeleccionada);
				lbl_Evento.setText("¡Palabra resuelta! ¡¡¡GANASTE!!!");
				btn_Advivinar.setEnabled(false);
				
				if (isPlayer1 == true) {
					jugador = 2;
					caer(jugador);
					lbl_P2Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/0.png", 40, 40));
				} else {
					jugador = 1;
					caer(jugador);
					lbl_P1Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/0.png", 40, 40));
				}
				
			} else {

				// Verificar si es jugador 1 o 2
				if (isPlayer1 == true) {
					erroresP1++;

					// Verificar si se alcanzó el máximo de errores permitidos
					if (erroresP1 == maxErrores) {
						lbl_P1Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/0.png", 40, 40));
						lbl_Evento.setText("¡Palabra incorrecta! PERDISTE");
						lbl_PalabraAdivinar.setText(palabraSeleccionada);
						jugador = 1;
						caer(jugador);
					} else {
						lbl_P1Num.setIcon(Main_frame
								.escalarImagen("/recursos/numeros/" + (maxErrores - erroresP1) + ".png", 40, 40));
						lbl_Evento.setText("¡Palabra incorrecta! Te quedan " + (maxErrores - erroresP1) + " vidas");
						isPlayer1 = false;
						lbl_Turno.setText("Turno de: " + lbl_Player2.getText());
					}
				} else {
					erroresP2++;

					// Verificar si se alcanzó el máximo de errores permitidos
					if (erroresP2 == maxErrores) {
						lbl_P2Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/0.png", 40, 40));
						lbl_Evento.setText("¡Palabra incorrecta! PERDISTE");
						lbl_PalabraAdivinar.setText(palabraSeleccionada);
						jugador = 2;
						caer(jugador);
					} else {
						lbl_P2Num.setIcon(Main_frame
								.escalarImagen("/recursos/numeros/" + (maxErrores - erroresP2) + ".png", 40, 40));
						lbl_Evento.setText("¡Palabra incorrecta! Te quedan " + (maxErrores - erroresP2) + " vidas");
						isPlayer1 = true;
						lbl_Turno.setText("Turno de: " + lbl_Player1.getText());
					}
				}
			}
		}
	}

	// Trigger de la animación de caer
	private void caer(int jugador) {

		lbl_PalabraAdivinar.setText(palabraSeleccionada);
		btn_Advivinar.setEnabled(false);
		btn_Reiniciar.setVisible(true);

		if (jugador == 1) {
			lbl_P1Compuerta.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/general/CompuertaActivadaI.png")));
			timer1.start();
			
			// Selecciona el ganador
			ganador = lbl_Player2.getText(); // Ganador es Jugador 2
			perdedor = lbl_Player1.getText();
			
		} else if (jugador == 2) {
			lbl_P2Compuerta.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/general/CompuertaActivadaD.png")));
			timer2.start();
			
			// Selecciona el ganador
			ganador = lbl_Player1.getText(); // Ganador es Jugador 1
			perdedor = lbl_Player2.getText();
		}
		
		try {
			guardarPartidaAhorcado(); // Guardar la partida
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Animación caer de jugador 1
	private void caerAnimacionP1() {

		int posActualP1 = lbl_P1Avatar.getY();
		if (posActualP1 == 704) {
			timer1.stop();
		} else {
			posNew = posActualP1 + 3; // Mover jugador
			lbl_P1Avatar.setBounds(23, posNew, 159, 132);
		}
	}

	// Animación caer de jugador 2
	private void caerAnimacionP2() {

		int posActualP2 = lbl_P2Avatar.getY();
		if (posActualP2 == 704) {
			timer2.stop();
		} else {
			posNew = posActualP2 + 3; // Mover jugador
			lbl_P2Avatar.setBounds(519, posNew, 159, 132);
		}
	}
	
	// Reiniciar juego
	private void reiniciarJuegoAhorcado() {
		palabraAleatoria();  // Selecciona una palabra aleatoria del array
		erroresP1 = 0;
		erroresP2 = 0;
		timer1.setDelay(1);
		timer2.setDelay(1);
		timer1.stop();
		timer2.stop();
		lbl_P1Avatar.setBounds(23, 128, 159, 132);
		lbl_P2Avatar.setBounds(519, 128, 159, 132);
		lbl_P1Compuerta.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/general/CompuertaI.png")));
		lbl_P2Compuerta.setIcon(new ImageIcon(Pnl_ahorcado.class.getResource("/recursos/general/CompuertaD.png")));
		lbl_P1Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/10.png", 40, 40));
		lbl_P2Num.setIcon(Main_frame.escalarImagen("/recursos/numeros/10.png", 40, 40));
		lbl_Evento.setText("");
		textField.setText("");
		btn_Advivinar.setEnabled(true);
		btn_Reiniciar.setVisible(false);
	}
	
	// Guardar la partida
	private void guardarPartidaAhorcado() throws SQLException {
		
		// Comprobar si hay una sesión activa
		if (!Pnl_sesion.nombreSesion.equals("")) {
			String consultaSQL = "";
			
			LocalDate fechaActual = LocalDate.now();
			Date fechaSQL = Date.valueOf(fechaActual); // Extraer fecha
			
			consultaSQL = "INSERT INTO Partidas (partida_id, sesion_id, modo_juego, fecha, ganador_nombre, perdedor_nombre)"
					+ "VALUES (DEFAULT, '" + Pnl_sesion.nombreSesion + "', 'Ahorcado', '" + fechaSQL + "', '" + ganador + "', '" + perdedor + "');";
			ConexionMySQL.ejecutarInsertDeleteUpdate(consultaSQL); // Ejecutar consulta
		}
	}
}
