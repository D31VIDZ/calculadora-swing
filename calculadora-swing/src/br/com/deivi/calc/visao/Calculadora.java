package br.com.deivi.calc.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame{

	public Calculadora() {
		
		organnizarLayout();
		
		setVisible(true);
		setSize(332, 532);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	private void organnizarLayout() {
		setLayout(new BorderLayout());
		
		Display dis = new Display();
		dis.setPreferredSize(new Dimension(433, 90));
		add(dis, BorderLayout.NORTH);
		
		Teclado tec = new Teclado();
		add(tec, BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		new Calculadora();
		
	}
}