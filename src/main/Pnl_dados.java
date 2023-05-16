package main;

import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.awt.Font;
import java.awt.Color;

public class Pnl_dados extends JPanel {

	private ImageIcon[] caras;
	
	static JLabel lbl_P1Avatar, lbl_P2Avatar, lbl_Player1, lbl_Player2, lbl_Turno;
	
	private JLabel lbl_dadoP1, lbl_dadoP2, lbl_Evento, lbl_Fondo;
	private JButton btn_tirarP1, btn_tirarP2, btn_Reiniciar;
	private Random random;
	private Timer timerD, timerM;
	private int delay = 10, repetitions = 0, index, movements, posNew;
	private boolean reverse = false, isPlayer1, endTimerM;
	private String ganador, perdedor;

	public Pnl_dados() {
		setLayout(null);
		
		caras = new ImageIcon[6];
		caras[0] = new ImageIcon(Pnl_dados.class.getResource("/recursos/dados/DadoCara1.png"));
		caras[1] = new ImageIcon(Pnl_dados.class.getResource("/recursos/dados/DadoCara2.png"));
		caras[2] = new ImageIcon(Pnl_dados.class.getResource("/recursos/dados/DadoCara3.png"));
		caras[3] = new ImageIcon(Pnl_dados.class.getResource("/recursos/dados/DadoCara4.png"));
		caras[4] = new ImageIcon(Pnl_dados.class.getResource("/recursos/dados/DadoCara5.png"));
		caras[5] = new ImageIcon(Pnl_dados.class.getResource("/recursos/dados/DadoCara6.png"));

		JLayeredPane pnl_dados = new JLayeredPane();
		pnl_dados.setBounds(0, 0, 704, 527);
		add(pnl_dados);
		pnl_dados.setLayout(null);

		lbl_dadoP1 = new JLabel("");
		pnl_dados.setLayer(lbl_dadoP1, 1);
		lbl_dadoP1.setIcon(new ImageIcon(Pnl_dados.class.getResource("/recursos/dados/DadoCara6.png")));
		lbl_dadoP1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dadoP1.setBounds(123, 53, 90, 90);
		pnl_dados.add(lbl_dadoP1);

		btn_tirarP1 = new JButton("TIRAR");
		btn_tirarP1.setForeground(Color.WHITE);
		btn_tirarP1.setBackground(Color.BLACK);
		pnl_dados.setLayer(btn_tirarP1, 1);
		btn_tirarP1.setFont(new Font("Unispace", Font.PLAIN, 14));
		btn_tirarP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_tirarP1.setEnabled(false);
				btn_tirarP2.setEnabled(false);
				index = random.nextInt(6);
				lbl_Evento.setText("");
				tirarDado(1);
			}
		});
		btn_tirarP1.setBounds(123, 154, 93, 28);
		pnl_dados.add(btn_tirarP1);

		lbl_P1Avatar = new JLabel("");
		pnl_dados.setLayer(lbl_P1Avatar, 2);
		lbl_P1Avatar.setIcon(new ImageIcon(Pnl_dados.class.getResource("/recursos/avatares/A.png")));
		lbl_P1Avatar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P1Avatar.setBounds(10, 222, 103, 132);
		pnl_dados.add(lbl_P1Avatar);

		lbl_dadoP2 = new JLabel("");
		pnl_dados.setLayer(lbl_dadoP2, 1);
		lbl_dadoP2.setIcon(new ImageIcon(Pnl_dados.class.getResource("/recursos/dados/DadoCara6.png")));
		lbl_dadoP2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_dadoP2.setBounds(491, 53, 90, 90);
		pnl_dados.add(lbl_dadoP2);

		btn_tirarP2 = new JButton("TIRAR");
		btn_tirarP2.setForeground(Color.WHITE);
		btn_tirarP2.setBackground(Color.BLACK);
		pnl_dados.setLayer(btn_tirarP2, 1);
		btn_tirarP2.setFont(new Font("Unispace", Font.PLAIN, 14));
		btn_tirarP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_tirarP1.setEnabled(false);
				btn_tirarP2.setEnabled(false);
				index = random.nextInt(6);
				lbl_Evento.setText("");
				tirarDado(2);
			}
		});
		btn_tirarP2.setBounds(488, 154, 93, 28);
		btn_tirarP2.setEnabled(false);
		pnl_dados.add(btn_tirarP2);

		lbl_P2Avatar = new JLabel("");
		pnl_dados.setLayer(lbl_P2Avatar, 2);
		lbl_P2Avatar.setIcon(new ImageIcon(Pnl_dados.class.getResource("/recursos/avatares/A.png")));
		lbl_P2Avatar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_P2Avatar.setBounds(10, 365, 103, 132);
		pnl_dados.add(lbl_P2Avatar);
		
		JLabel lbl_barraProgresoP1 = new JLabel("");
		pnl_dados.setLayer(lbl_barraProgresoP1, 1);
		lbl_barraProgresoP1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_barraProgresoP1.setIcon(Main_frame.escalarImagen("/recursos/general/BarraProgreso.png", 734, 332));
		lbl_barraProgresoP1.setBounds(10, 251, 684, 103);
		pnl_dados.add(lbl_barraProgresoP1);
		
		JLabel lbl_barraProgresoP2 = new JLabel("");
		pnl_dados.setLayer(lbl_barraProgresoP2, 1);
		lbl_barraProgresoP2.setIcon(Main_frame.escalarImagen("/recursos/general/BarraProgreso.png", 734, 332));
		lbl_barraProgresoP2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_barraProgresoP2.setBounds(10, 394, 684, 103);
		pnl_dados.add(lbl_barraProgresoP2);
		
		lbl_Player1 = new JLabel("NombreP1");
		lbl_Player1.setForeground(Color.WHITE);
		pnl_dados.setLayer(lbl_Player1, 1);
		lbl_Player1.setFont(new Font("Unispace", Font.PLAIN, 14));
		lbl_Player1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Player1.setBounds(71, 28, 194, 23);
		pnl_dados.add(lbl_Player1);
		
		lbl_Player2 = new JLabel("NombreP2");
		lbl_Player2.setForeground(Color.WHITE);
		pnl_dados.setLayer(lbl_Player2, 1);
		lbl_Player2.setFont(new Font("Unispace", Font.PLAIN, 14));
		lbl_Player2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Player2.setBounds(439, 28, 194, 23);
		pnl_dados.add(lbl_Player2);
		
		lbl_Evento = new JLabel("");
		lbl_Evento.setForeground(Color.WHITE);
		pnl_dados.setLayer(lbl_Evento, 1);
		lbl_Evento.setFont(new Font("Roboto", Font.PLAIN, 17));
		lbl_Evento.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Evento.setBounds(251, 154, 200, 28);
		pnl_dados.add(lbl_Evento);
		
		btn_Reiniciar = new JButton("REINICIAR");
		btn_Reiniciar.setForeground(Color.WHITE);
		btn_Reiniciar.setBackground(new Color(25, 25, 112));
		pnl_dados.setLayer(btn_Reiniciar, 1);
		btn_Reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarJuegoDados();
		}});
		btn_Reiniciar.setFont(new Font("Unispace", Font.PLAIN, 10));
		btn_Reiniciar.setBounds(305, 193, 93, 28);
		pnl_dados.add(btn_Reiniciar);
		btn_Reiniciar.setVisible(false);
		
		JLabel lbl_TurnoTitulo = new JLabel("Turno de:");
		lbl_TurnoTitulo.setForeground(Color.WHITE);
		pnl_dados.setLayer(lbl_TurnoTitulo, 1);
		lbl_TurnoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TurnoTitulo.setFont(new Font("Unispace", Font.PLAIN, 16));
		lbl_TurnoTitulo.setBounds(251, 62, 200, 23);
		pnl_dados.add(lbl_TurnoTitulo);
		
		lbl_Turno = new JLabel("NombreP1");
		lbl_Turno.setForeground(Color.WHITE);
		pnl_dados.setLayer(lbl_Turno, 1);
		lbl_Turno.setFont(new Font("Unispace", Font.PLAIN, 16));
		lbl_Turno.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Turno.setBounds(251, 91, 200, 23);
		pnl_dados.add(lbl_Turno);
		
		lbl_Fondo = new JLabel("");
		lbl_Fondo.setIcon(new ImageIcon(Pnl_dados.class.getResource("/recursos/fondos/Fondo2.png")));
		lbl_Fondo.setBounds(0, 0, 704, 527);
		pnl_dados.add(lbl_Fondo);

		// Temporizador para rotar el dado
		timerD = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotarDado();
			}
		});
		
		// Temporizador para mover el avatar
		timerM = new Timer(600, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (endTimerM == true) {
					checkCasilla(posNew); // Comprobar casilla
				} else {
					moverAvatar();
				}
			}
		});

		random = new Random();
	}

	// Tirar dado del jugador
	private void tirarDado(int jugador) {
		
		if (jugador == 1) {
			isPlayer1 = true;
			timerD.start();
		} else if (jugador == 2) {
			isPlayer1 = false;
			timerD.start();
		}

	}

	// Animación girar dado
	private void rotarDado() {
		
		if (index < 5) {
			index++;
		} else {
			index = 0;
		}
		
		if (isPlayer1 == true) {
			lbl_dadoP1.setIcon(caras[index]);
		} else {
			lbl_dadoP2.setIcon(caras[index]);
		}
		repetitions++;
		
		if (repetitions > 20) {
			if (timerD.getDelay() < 500) {
				timerD.setDelay(timerD.getDelay() + delay);
				delay = delay + 5;
			} else {
				timerD.stop();
				timerD.setDelay(50);
				repetitions = 0;
				delay = 10;
				
				movements = index + 1;
				timerM.start();
			}
		}

	}
	
	// Mover el jugador las casillas indicadas por el dado
	private void moverAvatar() {
		
		if (isPlayer1 == true) {
			int posActualP1 = lbl_P1Avatar.getX();
			
			if (posActualP1 == 593) {
				posNew = posActualP1 - 53;
				reverse = true;
			} else if (reverse == true) {
				posNew = posActualP1 - 53; // Mover el jugador hacia atrás si los movimientos superan la casilla final
			} else {
				posNew = posActualP1 + 53; // Mover jugador
			}
			lbl_P1Avatar.setBounds(posNew, 222, 103, 132);
			movements--;
			
			// Parar temporizador cuando se acaben los movimientos
			if (movements == 0) {
				endTimerM = true;
				reverse = false;
			}
		} else {
			int posActualP2 = lbl_P2Avatar.getX();
			if (posActualP2 == 593) {
				posNew = posActualP2 - 53;
				reverse = true;
			} else if (reverse == true) {
				posNew = posActualP2 - 53; // Mover el jugador hacia atrás si los movimientos superan la casilla final
			} else {
				posNew = posActualP2 + 53; // Mover jugador
			}
			lbl_P2Avatar.setBounds(posNew, 365, 103, 132);
			movements--;
			if (movements == 0) {
				endTimerM = true;
				reverse = false;
			}
		}
	}
	
	// Comprobar en qué casilla ha caído el jugador
	private void checkCasilla(int pos) {
		
		if (pos == 116 || pos == 328 || pos == 540) { // Casillas doradas
			lbl_Evento.setText("¡Tirada extra!");
			if (isPlayer1 == true) {
				btn_tirarP1.setEnabled(true);
			} else {
				btn_tirarP2.setEnabled(true);
			}
		} else if (pos == 222 || pos == 434) { // Casillas rojas
			lbl_Evento.setText("¡Oh no! Vuelves al incio");
			if (isPlayer1 == true) {
				lbl_P1Avatar.setBounds(10, 222, 103, 132);
				lbl_Turno.setText(lbl_Player2.getText());
				btn_tirarP2.setEnabled(true);
			} else {
				lbl_P2Avatar.setBounds(10, 365, 103, 132);
				lbl_Turno.setText(lbl_Player1.getText());
				btn_tirarP1.setEnabled(true);
			}
		} else if (pos == 593) { // Casilla final
			lbl_Evento.setText("¡¡¡GANASTE!!!");
			btn_Reiniciar.setVisible(true);
			
			// Selecciona el ganador
			if (lbl_Turno.getText().equals(lbl_Player1.getText())) { // Ganador es Jugador 1
				ganador = lbl_Player1.getText();
				perdedor = lbl_Player2.getText();
			} else {  // Ganador es Jugador 2
				ganador = lbl_Player2.getText();
				perdedor = lbl_Player1.getText();
			}
			
			try {
				guardarPartidaDados(); // Guardar la partida
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			if (isPlayer1 == true) {
				lbl_Turno.setText(lbl_Player2.getText());
				btn_tirarP2.setEnabled(true);
			} else {
				lbl_Turno.setText(lbl_Player1.getText());
				btn_tirarP1.setEnabled(true);
			}
		}
		
		timerM.stop();
		endTimerM = false;
		
	}
	
	// Reinicia el juego dados
	private void reiniciarJuegoDados() {
		lbl_P1Avatar.setBounds(10, 222, 103, 132);
		lbl_P2Avatar.setBounds(10, 365, 103, 132);
		btn_tirarP1.setEnabled(true);
		btn_tirarP2.setEnabled(false);
		lbl_Evento.setText("");
		btn_Reiniciar.setVisible(false);
	}
	
	// Guardar la partida
	private void guardarPartidaDados() throws SQLException {
		
		// Comprobar si hay una sesión activa
		if (!Pnl_sesion.nombreSesion.equals("")) {
			String consultaSQL = "";
			
			LocalDate fechaActual = LocalDate.now();
			Date fechaSQL = Date.valueOf(fechaActual);
			
			consultaSQL = "INSERT INTO Partidas (partida_id, sesion_id, modo_juego, fecha, ganador_nombre, perdedor_nombre)"
					+ "VALUES (DEFAULT, '" + Pnl_sesion.nombreSesion + "', 'Dados', '" + fechaSQL + "', '" + ganador + "', '" + perdedor + "');";
			ConexionMySQL.ejecutarInsertDeleteUpdate(consultaSQL); // Ejecutar consulta
		}
	}
}
