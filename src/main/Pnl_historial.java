package main;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;

public class Pnl_historial extends JPanel {

	JScrollPane scrollPane;
	static JPanel panelPrincipal;
	
	/**
	 * Create the panel.
	 */
	public Pnl_historial() {
		setLayout(null);
		
		JLayeredPane pnl_historial = new JLayeredPane();
		pnl_historial.setBounds(0, 0, 704, 527);
		add(pnl_historial);
		
		JLabel lbl_fondo = new JLabel("");
		lbl_fondo.setIcon(new ImageIcon(Pnl_historial.class.getResource("/recursos/fondos/Fondo2.png")));
		lbl_fondo.setBounds(0, 0, 704, 527);
		pnl_historial.add(lbl_fondo);
		
		JPanel pnl_Ventana = new JPanel();
		pnl_historial.setLayer(pnl_Ventana, 1);
		pnl_Ventana.setBackground(new Color(105, 105, 105));
		pnl_Ventana.setBounds(102, 11, 500, 505);
		pnl_historial.add(pnl_Ventana);
		pnl_Ventana.setLayout(null);
		
		JLabel lbl_Historial = new JLabel("Historial de Partidas");
		lbl_Historial.setForeground(Color.WHITE);
		lbl_Historial.setFont(new Font("Unispace", Font.PLAIN, 18));
		lbl_Historial.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Historial.setBounds(10, 11, 480, 30);
		pnl_Ventana.add(lbl_Historial);
		
		panelPrincipal = new JPanel();
		//scrollPane.setColumnHeaderView(panelPrincipal);
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(panelPrincipal);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 52, 480, 442);
		pnl_Ventana.add(scrollPane);
		pnl_historial.setLayer(scrollPane, 1);
		
	}
	
	// Extraer el historial
	public static void extraerHistorial() throws SQLException {
		
		// Limpiar el panel para que no se dubliquen los datos si ya hay
		panelPrincipal.removeAll();
		panelPrincipal.revalidate();
		panelPrincipal.repaint();
		
		Main_frame.rset = ConexionMySQL.ejecutarSelect("SELECT * FROM Partidas WHERE sesion_id = '" + Pnl_sesion.nombreSesion + "';");
		while (Main_frame.rset.next()) {
			panelPrincipal.add(crearPanel()); // Mete los datos en los paneles
		}
	}
	
	// Crear panel para insertar los datos del historial
	private static JPanel crearPanel() throws SQLException  {
		
		JPanel panelExterno = new JPanel();
		panelExterno.setPreferredSize(new Dimension(458, 60));
		panelPrincipal.add(panelExterno);
		panelExterno.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 480, 2);
		panelExterno.add(separator);
		
		JLabel lbl_Partida = new JLabel("Partida: " + Main_frame.rset.getString("modo_juego"));
		lbl_Partida.setBounds(10, 8, 219, 25);
		lbl_Partida.setFont(new Font("Unispace", Font.PLAIN, 11));
		panelExterno.add(lbl_Partida);
		
		JLabel lbl_Fecha = new JLabel(Main_frame.rset.getString("fecha"));
		lbl_Fecha.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_Fecha.setBounds(229, 8, 229, 25);
		lbl_Fecha.setFont(new Font("Unispace", Font.PLAIN, 11));
		panelExterno.add(lbl_Fecha);
		
		JLabel lbl_Ganador = new JLabel("Ganador: " + Main_frame.rset.getString("ganador_nombre"));
		lbl_Ganador.setBounds(30, 33, 199, 25);
		lbl_Ganador.setFont(new Font("Unispace", Font.PLAIN, 11));
		panelExterno.add(lbl_Ganador);
		
		JLabel lbl_Perdedor = new JLabel("Perdedor: " + Main_frame.rset.getString("Perdedor_nombre"));
		lbl_Perdedor.setBounds(229, 33, 199, 25);
		lbl_Perdedor.setFont(new Font("Unispace", Font.PLAIN, 11));
		panelExterno.add(lbl_Perdedor);
		
		return panelExterno;
	}
}
