package br.com.deivi.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando {
		ZERAR, SINAL, NUMERO, DIV, MULT, SUB, IGUAL, VIRGULA, SOMA;	
	};

	private static final Memoria instan = new Memoria();
	
	private final List<MemoriaObsereve> obes = new ArrayList();
	
	private boolean substituir;
	private String textAtual = "";
	private String textBuffer = "";
	private TipoComando ultimaOp;
	
	
	private Memoria() {
		
	}

	public static Memoria getInstan() {
		return instan;
	}
	
	public void adcObservador(MemoriaObsereve o) {
		obes.add(o);
	}

	public String getTextAtual() {
		return textAtual.isEmpty() ? "0" : textAtual;
	}
	
	public void precessarComando(String valor) {
		
		TipoComando tipo = detectarTipo(valor);
		System.out.println(tipo);
		
		if(tipo == null) {
			return;
		}else if(tipo == TipoComando.ZERAR) {
			textAtual = "";
			textBuffer = "";
			substituir = false;
			ultimaOp = null;
		}else if(tipo == TipoComando.SINAL && textAtual.contains("-")){
			textAtual = textAtual.substring(1);			
		}else if(tipo == TipoComando.SINAL && !textAtual.contains("-")){
			textAtual = "-" +textAtual;			
		}else if(tipo == TipoComando.NUMERO 
				|| tipo == TipoComando.VIRGULA) {
			textAtual = substituir ? valor : textAtual + valor;
			substituir = false;
		} else {
			substituir = true;
			textAtual = obterResulOp();
			textBuffer = textAtual;
			ultimaOp = tipo;
		}		
		obes.forEach(o -> o.valorAlterado(getTextAtual()));
	}

	private String obterResulOp() {
		if(ultimaOp == null || ultimaOp == TipoComando.IGUAL) {
			return textAtual;
		}
		
		double numBuffer = Double.parseDouble(textBuffer.replace(",", "."));
		double numAtual = Double.parseDouble(textAtual.replace(",", "."));
		
		double resul = 0;
		
		if(ultimaOp == TipoComando.SOMA) {
			resul = numBuffer + numAtual;
		}else if(ultimaOp == TipoComando.SUB) {
			resul = numBuffer - numAtual;
		}else if(ultimaOp == TipoComando.DIV) {
			resul = numBuffer / numAtual;
		}else if(ultimaOp == TipoComando.MULT) {
			resul = numBuffer * numAtual;
		}
		String resuStrin = Double.toString(resul).replace(".", ",");
		boolean inteiro = resuStrin.endsWith(",0");
		return  inteiro ? resuStrin.replace(",0" , "") : resuStrin;
	}
	private TipoComando detectarTipo(String valor) {
		if(textAtual.isEmpty() && valor == "0") {
			return null;
		}
		try {
			Integer.parseInt(valor);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			if("AC".equals(valor)) {
				return TipoComando.ZERAR;
			}else if("/".equals(valor)) {
				return TipoComando.DIV;
			}else if("×".equals(valor)) {
				return TipoComando.MULT;
		}else if("+".equals(valor)) {
			return TipoComando.SOMA;
	    }else if("-".equals(valor)) {
		    return TipoComando.SUB;
	    }else if("=".equals(valor)) {
			return TipoComando.IGUAL;
	    }else if(",".equals(valor) && !textAtual.contains(",")) {
			return TipoComando.VIRGULA;
	    }else if("+-".equals(valor)) {
			return TipoComando.SINAL;
		}
	}  
		return null;
	}
}