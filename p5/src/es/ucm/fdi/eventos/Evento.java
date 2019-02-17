package es.ucm.fdi.eventos;

import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;

public abstract class Evento {


		protected Integer tiempo;
		
		public Evento(int tiempo) {
			
			this.tiempo = tiempo;
		}
		public int getTiempo() {
			return this.tiempo;
		}
		//...
		// cada clase que hereda implementa su método ejecuta, que creará
		// el correspondiente objeto de la simulación.
		public abstract void ejecuta(MapaCarreteras mapa) throws EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, ErrorCarretera;
		
}
