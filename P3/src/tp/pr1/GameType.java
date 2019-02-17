package tp.pr1;

import Excepciones.Parse_null;
import Rules.GameRules;
import Rules.Rules2048;
import Rules.RulesFib;
import Rules.RulesInverse;

public enum GameType {
ORIG,FIB,INV;


public GameRules crearNormas() {

	if (this== ORIG) {
		return new Rules2048();
	}
	else if (this == FIB) {
		return new RulesFib();
	}
	else 	
		return new RulesInverse();
}



public GameType distincion(String juego) throws Parse_null {
	
	switch (juego) {
	
	case "original":
		return GameType.ORIG;
						
	case "fib":
		return GameType.FIB;
	case "fibonacci":
		return GameType.FIB;
					
	case "inverse":
		return GameType.INV;
		
	default: throw new Parse_null("Unknown game type, the availables games are : original,fib,inverse.");
		
	}
}

}