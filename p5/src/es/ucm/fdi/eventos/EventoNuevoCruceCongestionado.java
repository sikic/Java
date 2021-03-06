package es.ucm.fdi.eventos;

import es.ucm.fdi.objetos.CruceCongestionado;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.excepciones.ErrorEventoAņadido;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevoCruceCongestionado extends EventoNuevoCruce {

public EventoNuevoCruceCongestionado(int tiempo, String id) {
		super(tiempo, id);
		// TODO Auto-generated constructor stub
	}

public void ejecuta(MapaCarreteras mapa) throws ErrorEventoAņadido {
	// crea el cruce y se lo aņade al mapa
	mapa.addCruce(this.id, this.creaCruce());
	
}
@Override
protected CruceGenerico<?> creaCruce() {
 return new CruceCongestionado(this.id);
}

public String toString() {
	
	
	return  "nuevo Cruce -> " + this.id;
	}
}