package tp.pr1;

import java.util.*;

public class arrayBoards {

	private static final int CAPACITY = 20;
	private GameState [] arrayMovi; 
	private int contador;
	
	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public arrayBoards() {
		this.arrayMovi = new GameState[CAPACITY];
		this.contador = 0;
	}
	

	public GameState pop()//Que devuelve el ultimo estado almacenado,
	{
	
			try {
				 return this.arrayMovi[this.contador - 1];
				 
			} catch (EmptyStackException e) {
				
				return null;
			}
	}
	
	public void push(GameState state){
		//Almacena un nuevo estado y comprueba que no sea mayor que 20
		try{
			this.arrayMovi[this.contador] = state;
			this.contador++;
			
		}catch (Exception e) {
			
			for (int i = 1; i < CAPACITY; i++) {
				
				this.arrayMovi[i-1] = this.arrayMovi[i];
				
			}
			this.arrayMovi[this.contador - 1] = state;
		}
	}
		

	public boolean isEmpty() { //Que devuelve si la estructura de datos está vacía.
		return this.contador== 0;
	}
}
