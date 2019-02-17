package es.ucm.fdi.eventos;


import es.ucm.fdi.objetos.Camino;
import es.ucm.fdi.objetos.Carretera;
import es.ucm.fdi.objetos.CruceGenerico;

public class EventoNuevoCamino extends EventoNuevaCarretera {

	public EventoNuevoCamino(int tiempo,String id, int longitud, int velocidadMaxima,String cruceOrigen,String cruceDestino) {
		super(tiempo, id, cruceOrigen, cruceDestino, velocidadMaxima, longitud);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Carretera creaCarretera(CruceGenerico <?> origen,CruceGenerico<?> destino) {
		return new Camino(this.id,this.longitud,this.velocidadMaxima,origen,destino);
	}

}
