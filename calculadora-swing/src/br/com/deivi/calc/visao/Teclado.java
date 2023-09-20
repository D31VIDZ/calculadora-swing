package br.com.deivi.calc.visao;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.deivi.calc.modelo.Memoria;


@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{

	private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
	private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
	private final Color COR_LARANJA = new Color(242, 163, 60);
	
	public Teclado() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		
		setLayout(layout);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		c.gridwidth = 2;
		ADCbOTAO("AC", COR_CINZA_ESCURO, c, 0, 0);	
		c.gridwidth = 1;
		ADCbOTAO("+-", COR_LARANJA, c,  2, 0);		
		ADCbOTAO("/", COR_LARANJA, c,  3, 0);		
		
		ADCbOTAO("7", COR_CINZA_CLARO, c, 0, 1);
		ADCbOTAO("8", COR_CINZA_CLARO, c,  1, 1);		
		ADCbOTAO("9", COR_CINZA_CLARO, c,  2, 1);		
		ADCbOTAO("×", COR_LARANJA, c,  3, 1);
		
		ADCbOTAO("4", COR_CINZA_CLARO, c, 0, 2);
		ADCbOTAO("5", COR_CINZA_CLARO, c,  1, 2);		
		ADCbOTAO("6", COR_CINZA_CLARO, c,  2, 2);		
		ADCbOTAO("-", COR_LARANJA, c,  3, 2);
		
		ADCbOTAO("1", COR_CINZA_CLARO, c, 0, 3);
		ADCbOTAO("2", COR_CINZA_CLARO, c,  1, 3);		
		ADCbOTAO("3", COR_CINZA_CLARO, c,  2, 3);		
		ADCbOTAO("+", COR_LARANJA, c,  3, 3);
		
		c.gridwidth = 2;	
		ADCbOTAO("0", COR_CINZA_CLARO, c, 0, 4);
		c.gridwidth = 1;
		ADCbOTAO(",", COR_CINZA_CLARO, c,  2, 4);		
		ADCbOTAO("=", COR_LARANJA, c,  3, 4);
		
	}

	private void ADCbOTAO(String texto, Color cor,
			     GridBagConstraints c, int x, int y) {
			
		c.gridx = x;
		c.gridy = y;
		BotaoCalc bot = new BotaoCalc(texto, cor);
		bot.addActionListener(this);
		add(bot, c);
	}	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			Memoria.getInstan().precessarComando(botao.getText());
		}			
	}	
}