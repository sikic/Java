package es.ucm.fdi.eventos;

import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;

public class EventoNuevoCruce extends Evento {
	
	protected String id;

public EventoNuevoCruce(int tiempo, String id) {
		super(tiempo);
		this.id = id;
	}

	
public void ejecuta(MapaCarreteras mapa) throws ErrorEventoAñadido {
		// crea el cruce y se lo añade al mapa
		
		mapa.addCruce(this.id, this.creaCruce()); 
		
	}



protected CruceGenerico<?> creaCruce() {
	 return new Cruce(this.id);
	}



public String toString() {
		
	
	return  "nuevo Cruce -> " + this.id;
	}
}
