package br.com.deivi.calc.visao;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.deivi.calc.modelo.Memoria;
import br.com.deivi.calc.modelo.MemoriaObsereve;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObsereve{
	
	private final JLabel label;

	public Display() {
		Memoria.getInstan().adcObservador(this);
		
		setBackground(new Color(46, 49, 50));
		label = new JLabel(Memoria.getInstan().getTextAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("curier", Font.PLAIN, 30));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 50));
		add(label);
		
	}
	
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
	}
	
}