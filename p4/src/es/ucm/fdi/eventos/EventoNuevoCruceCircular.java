package es.ucm.fdi.eventos;

import es.ucm.fdi.objetos.CruceCircular;
import es.ucm.fdi.objetos.CruceGenerico;

public class EventoNuevoCruceCircular extends EventoNuevoCruce {

	protected Integer maxValorIntervalo;
	protected Integer minValorIntervalo;

	public EventoNuevoCruceCircular(int time , String id,int min,int max) {
		super(time,id);
		this.maxValorIntervalo = max;
		this.minValorIntervalo = min;
	}
	@Override
	protected CruceGenerico<?> creaCruce(){
		return new CruceCircular(this.id,this.minValorIntervalo,this.maxValorIntervalo);
	}
	
	public String toString() {
		return super.toString() + "\n" + "type = rr" + "\n" + "max_time_slice = " + this.maxValorIntervalo
				+ "\n" + "min_time_slice = " + this.minValorIntervalo;
		
	}
	
}
