/************************************************************
*				PRACTICA 1 - 2048 GAME
*
*
* TECNOLOGIA DE LA PROGRAMACION
* Facultad de Informatica
* Universidad Complutense de Madrid
*
* Autores:  Francisco Garcia Rofes y Luis Antonio Rojas
* Practica: 2048 game
* Fecha de creacion: 28/10/2017
* Fecha de ultima modificacion: 13/11/2017
* Contacto: lurojas@ucm.es  /  franga06@ucm.es
*
*************************************************************/

package Game_2048;

import Rules.GameRules;
import Rules.Rules2048;
import utils.MyStringUtils;

public class Game2048 {
	
	private Controller juego;
	
	
	public Game2048(int dimension,int baldIni, long seed ,GameRules rules) { 
		
		this.juego = new Controller (dimension,baldIni,seed,rules);
	}
	
	
	public static void main (String[]  args) 
	{
		try{
			{
			
		int dim = Integer.parseInt(args[0]);
		int BaldIni = Integer.parseInt(args[1]);
		int seed = (int)(Math.random()* 1000);
		
		if(args.length == 3)
			if(MyStringUtils.isNumeric(args[2]))
			seed = Integer.parseInt(args[2]);
				
			GameRules rules = new Rules2048();
			Game2048 Game2048 = new Game2048(dim,BaldIni, seed,rules);
			Game2048.juego.run();
		}
		}
		catch(Exception e){
			System.out.println("BE CAREFUL !!! The command-line arguments must be numbers.");
		}

	}
}
