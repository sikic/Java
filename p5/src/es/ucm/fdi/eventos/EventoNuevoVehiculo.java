package es.ucm.fdi.eventos;

import java.util.*;

import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.objetos.Vehiculo;
import es.ucm.fdi.parser.ParserCarreteras;

public class EventoNuevoVehiculo extends Evento{
	
	protected String id;
	protected Integer velocidadMaxima;
	protected String [] itinerario;
	
	public EventoNuevoVehiculo(int tiempo,String id,String [] itinerario,int velmax) {
		super(tiempo);
		this.id = id;
		this.velocidadMaxima = velmax;
		this.itinerario = itinerario;
		
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws EjecutaException, ErrorEventoAñadido, ExcepcionCreaccionObjeto, ErrorCarretera, ErrorBusquedaMapa {
	
		List<CruceGenerico<?>> iti = ParserCarreteras.parseaListaCruces(this.itinerario,mapa);
		// si iti es null o tiene menos de dos cruces lanzar excepción
		if(iti== null || iti.size() < 2)
			throw new EjecutaException("la Lista de cruces esta vacia o es menos de dos");
		// en otro caso crear el vehículo y añadirlo al mapa.
		else {
			
			Vehiculo nuevo = new Vehiculo(this.id,this.velocidadMaxima,iti);
			mapa.addVehiculo(this.id, nuevo);
		}
			
	}

	@Override
	public String toString() {
		
		
		return "nuevo Vehiculo -> " +  this.id;
			
	}
	
	

}
