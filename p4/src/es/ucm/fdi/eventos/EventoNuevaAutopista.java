package es.ucm.fdi.eventos;


import es.ucm.fdi.objetos.Autopista;
import es.ucm.fdi.objetos.Carretera;
import es.ucm.fdi.objetos.CruceGenerico;

public class EventoNuevaAutopista extends EventoNuevaCarretera {
	
	protected Integer numCarriles;

	public EventoNuevaAutopista(int tiempo,String id,String origen,String destino, int velocidadMaxima, int longitud, int carriles) {
		super(tiempo, id, destino, origen, velocidadMaxima, longitud);
		this.numCarriles = carriles;
	}

	protected Carretera creaCarretera(CruceGenerico<?> origen, CruceGenerico<?> destino) {
		return new Autopista(this.id,this.velocidadMaxima,this.longitud,origen,destino,this.numCarriles);
	}
}
