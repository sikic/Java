package es.ucm.fdi.eventos;

import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevaAveria extends Evento {
	
	protected String  averias;
	protected int duracion;
	
	public EventoNuevaAveria(int tiempo, String  strings,int duracion) {
		super(tiempo);
		// TODO Auto-generated constructor stub
		this.averias= strings;
		this.duracion= duracion;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorBusquedaMapa {
		// TODO Auto-generated method stub
			mapa.getVehiculo(averias).setTiempoAveria(this.duracion);
		
	}

	@Override
	public String toString() {
		return "EventoNuevaAveria []";
	}

}
