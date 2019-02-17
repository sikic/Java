package es.ucm.fdi.eventos;

import es.ucm.fdi.objetos.CruceCircular;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevoCruceCircular extends EventoNuevoCruce {
	 protected Integer maxValorIntervalo;
	 protected Integer minValorIntervalo;
	 
	 public EventoNuevoCruceCircular(int time, String id,int minValorIntervalo, int maxValorIntervalo) {
	 super(time, id);
	 this.maxValorIntervalo = maxValorIntervalo;
	 this.minValorIntervalo = minValorIntervalo;
	 }
	 
	 public void ejecuta(MapaCarreteras mapa) throws ErrorEventoAñadido {
			// crea el cruce y se lo añade al mapa
			mapa.addCruce(this.id, this.creaCruce());
			
		}
	 @Override
	 protected CruceGenerico<?> creaCruce() { 
	 return new CruceCircular(this.id, this.minValorIntervalo,this.maxValorIntervalo);
	 }
	 public String toString() {
			
			
			return  "nuevo Cruce Circular -> " + this.id;
			}
	}