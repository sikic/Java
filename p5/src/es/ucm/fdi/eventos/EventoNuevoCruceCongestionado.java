package es.ucm.fdi.eventos;

import es.ucm.fdi.objetos.CruceCongestionado;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.excepciones.ErrorEventoA�adido;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevoCruceCongestionado extends EventoNuevoCruce {

public EventoNuevoCruceCongestionado(int tiempo, String id) {
		super(tiempo, id);
		// TODO Auto-generated constructor stub
	}

public void ejecuta(MapaCarreteras mapa) throws ErrorEventoA�adido {
	// crea el cruce y se lo a�ade al mapa
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