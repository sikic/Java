package Game_2048;

import Rules.GameRules;

//Clase que permite representar cada una de las baldosas del tablero.
public class Cell {
	private int valor;
	
	public Cell(int valor) {
		 this.valor = valor;
	}
	 
	public int getValor() { 
		return this.valor;
	}

	public void setValor(int valor) { 
		this.valor = valor;
	}


	public boolean isEmpty(){// Devuelve si una baldosa esta vacia.
		boolean vacio  = false;
		if ( this.getValor() == 0)
			vacio = true;
		
		return vacio;
	}
	
	
	public int doMerge(Cell neighbour, GameRules rules) {// Este método comprueba si se puede realizar la combinación de una baldosa y la baldosa vecina pasada como argumento.
		
		return rules.merge(this, neighbour);
		
	}

	
	
	
	
}
