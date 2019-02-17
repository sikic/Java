package es.ucm.fdi.eventos;

import java.util.List;

import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Bicicleta;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.objetos.Vehiculo;
import es.ucm.fdi.parser.ParserCarreteras;

public class EventoNuevaBicicleta extends EventoNuevoVehiculo{

	public EventoNuevaBicicleta(int tiempo,String id,String [] itinerario,int velmax) {
		super(tiempo,id,itinerario,velmax);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecuta(MapaCarreteras mapa)
			throws EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, ErrorCarretera {

		List<CruceGenerico<?>> iti = ParserCarreteras.parseaListaCruces(this.itinerario,mapa);
		// si iti es null o tiene menos de dos cruces lanzar excepción
		if(iti== null || iti.size() < 2)
			throw new EjecutaException("la Lista de cruces esta vacia o es menos de dos");
		// en otro caso crear el vehículo y añadirlo al mapa.
		else {
			
			Bicicleta nueva = new Bicicleta(this.id,this.velocidadMaxima,iti);
			mapa.addVehiculo(this.id, nueva);
		}
		
	}
	
	@Override
	public String toString() {
		return "Nueva_Bicicleta -> " + this.id;
		
	}

}
