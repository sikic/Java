package es.ucm.fdi.eventos;

import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Autopista;
import es.ucm.fdi.objetos.Carretera;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;

public class EventoNuevaAutopista extends EventoNuevaCarretera {
	protected Integer numCarriles;
	
	public EventoNuevaAutopista(int tiempo, String id, String origen,String destino, int velocidadMaxima, int longitud,int carriles) {
		super(tiempo,id,origen,destino,velocidadMaxima,longitud);
		this.numCarriles= carriles;
		// TODO Auto-generated constructor stub
	}

	protected Carretera creaCarretera(CruceGenerico<?> origen,CruceGenerico<?> destino) {
		
			 return new Autopista(this.id,this.longitud,this.velocidadMaxima,origen,destino,this.numCarriles);
			 }
	
	@Override
	public String toString() {
		return "nueva_autopista -> " + this.id;
		
	}
}
