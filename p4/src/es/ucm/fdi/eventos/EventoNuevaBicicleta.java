package es.ucm.fdi.eventos;

import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoA�adido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;

public class EventoNuevaBicicleta extends Evento{

	public EventoNuevaBicicleta(int tiempo) {
		super(tiempo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecuta(MapaCarreteras mapa)
			throws EjecutaException, ErrorEventoA�adido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, ErrorCarretera {
		// TODO Auto-generated method stub
		
	}

}
