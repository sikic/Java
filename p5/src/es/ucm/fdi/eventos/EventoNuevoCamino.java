package es.ucm.fdi.eventos;

import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Camino;
import es.ucm.fdi.objetos.Carretera;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;

public class EventoNuevoCamino extends EventoNuevaCarretera {

	public EventoNuevoCamino(int tiempo, String id, String origen,String destino, int velocidadMaxima, int longitud) {
		super(tiempo,id,origen,destino,velocidadMaxima,longitud);
			
	}
	@Override
	protected Carretera creaCarretera(CruceGenerico<?> cruceOrigen,CruceGenerico<?>  cruceDestino) {
		 return new Camino(this.id, this.longitud, this.velocidadMaxima,cruceOrigen, cruceDestino);
		 }
	
	@Override
	public String toString() {
		return "nuevo Camino -> " + this.id;
		
	}
}
