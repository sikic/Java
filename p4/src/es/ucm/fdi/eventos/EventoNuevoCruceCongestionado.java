package es.ucm.fdi.eventos;

import es.ucm.fdi.objetos.CruceCongestionado;
import es.ucm.fdi.objetos.CruceGenerico;

public class EventoNuevoCruceCongestionado extends EventoNuevoCruce{

	public EventoNuevoCruceCongestionado(int tiempo, String id) {
		super(tiempo, id);
	}

	@Override
	protected CruceGenerico<?> creaCruce(){
		return new CruceCongestionado(this.id);
	}
	
	public String toString () {
		return super.toString() + "type = mc" + "\n"; 
 	}
}
