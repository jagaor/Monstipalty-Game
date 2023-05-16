package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelContainer extends JPanel {

    private CardLayout cardLayout;

    /**
	 * Create the panel.
	 */
    public PanelContainer() {
        setLayout(new CardLayout());
        cardLayout = (CardLayout) getLayout();

        JLayeredPane pnl_inicio = new JLayeredPane();
        add(pnl_inicio, "inicio");
        pnl_inicio.setLayout(null);
        
	        JLabel lbl_fondo = new JLabel();
	        lbl_fondo.setIcon(Main_frame.escalarImagen("/recursos/fondos/FondoAnimado2.gif", 704, 527));
	        lbl_fondo.setBounds(0, 0, 704, 527);
	        pnl_inicio.add(lbl_fondo);
        
	        JLabel lbl_titulo = new JLabel("");
	        lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
	        lbl_titulo.setIcon(new ImageIcon(PanelContainer.class.getResource("/recursos/general/TituloMonstiParty.png")));
	        pnl_inicio.setLayer(lbl_titulo, 1);
	        lbl_titulo.setBounds(0, 205, 698, 100);
	        pnl_inicio.add(lbl_titulo);

        Pnl_menu pnl_menu = new Pnl_menu();
        add(pnl_menu, "menu"); // Añadir panel menu
        
        Pnl_historial pnl_historial = new Pnl_historial();
        add(pnl_historial, "historial"); // Añadir panel historial
        
        Pnl_sesion pnl_sesion = new Pnl_sesion();
        add(pnl_sesion, "sesion"); // Añadir panel sesion
        
        Pnl_dados pnl_dados = new Pnl_dados();
        add(pnl_dados, "dados"); // Añadir panel dados
        
        Pnl_ahorcado pnl_ahorcado = new Pnl_ahorcado();
        add(pnl_ahorcado, "ahorcado"); // Añadir panel ahorcado
        
        setLayout(cardLayout);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
