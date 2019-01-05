/************************************************************
*				PRÁCTICA 1 - 2048 GAME
*
*
* TECNOLOGIA DE LA PROGRAMACION
* Facultad de Informática
* Universidad Complutense de Madrid
*
* Autores:  Francisco García Rofes y Luis Antonio Rojas
* Práctica: 2048 game
* Fecha de creación: 28/10/2017
* Fecha de última modificación: 13/11/2017
* Contacto: lurojas@ucm.es  /  franga06@ucm.es
*
*************************************************************/

package tp.pr1;

import java.util.*;

public class Game2048 {
	
	private Controller juego;
	
	
	public Game2048(int dimension,int baldIni,Random aleatorio) { 
		
		this.juego = new Controller (dimension,baldIni,aleatorio);
	}

	public static void main (String[]  args) 
	{
		int dim = Integer.parseInt(args[0]);
		int BaldIni = Integer.parseInt(args[1]);
		Random aleatorio = new Random();
		Game2048 Game2048 = new Game2048(dim,BaldIni,aleatorio);
		Game2048.juego.run();

		
	}
}
