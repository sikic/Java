package es.ucm.fdi.eventos;

import es.ucm.fdi.excepciones.ErrorEventoA�adido;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.objetos.CruceRotonda;

public class EventoNuevoCruceRotonda extends Evento {

		
		protected String id;

	public EventoNuevoCruceRotonda(int tiempo, String id) {
			super(tiempo);
			this.id = id;
		}

		
	public void ejecuta(MapaCarreteras mapa) throws ErrorEventoA�adido {
			// crea el cruce y se lo a�ade al mapa
			
			mapa.addCruce(this.id, this.creaCruce()); 
			
		}



	protected CruceGenerico<?> creaCruce() {
		 return new CruceRotonda(this.id);
		}



	public String toString() {
			
		
		return  "nuevo Cruce -> " + this.id;
		}
	}


