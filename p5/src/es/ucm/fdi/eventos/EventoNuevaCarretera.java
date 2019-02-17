package es.ucm.fdi.eventos;

import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Carretera;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;

public class EventoNuevaCarretera extends Evento {

	protected String id;
	protected Integer velocidadMaxima;
	protected Integer longitud;
	protected String cruceOrigenId;
	protected String cruceDestinoId;
	
	public EventoNuevaCarretera(int tiempo, String id, String origen,String destino, int velocidadMaxima, int longitud) {
		super(tiempo);
		this.id = id;
		this.cruceOrigenId = origen;
		this.cruceDestinoId = destino;
		this.velocidadMaxima = velocidadMaxima;
		this.longitud = longitud;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorBusquedaMapa, ErrorEventoAñadido, EjecutaException, ExcepcionCreaccionObjeto, ErrorCarretera {
		// obten cruce origen y cruce destino utilizando el mapa
		CruceGenerico<?>  destino = mapa.getCruce(this.cruceDestinoId);
		CruceGenerico<?>  origen = mapa.getCruce(this.cruceOrigenId);
		// crea la carretera
		Carretera nuevaCarretera = creaCarretera(origen,destino);
		//añade al mapa la carretera
		mapa.addCarretera(this.id, origen, nuevaCarretera, destino);
	}
	
	public String toString() {
		
		return  "nueva Carretera -> " + this.id;
		
	}
	protected Carretera creaCarretera(CruceGenerico<?> origen,CruceGenerico<?> destino) {
			 return new Carretera(this.id, this.longitud, this.velocidadMaxima,origen, destino);
			 }

}
