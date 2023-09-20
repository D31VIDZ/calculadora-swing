package br.com.deivi.calc.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BotaoCalc extends JButton{

	public BotaoCalc(String texto, Color cor) {
		setText(texto);
		setOpaque(true);
		setBackground(cor);
		setForeground(Color.WHITE);
		setFont(new Font("curier", Font.PLAIN, 25));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
}