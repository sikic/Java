package es.ucm.fdi.eventos;

import java.util.List;
import java.util.Random;

import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Coche;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.objetos.Vehiculo;
import es.ucm.fdi.parser.ParserCarreteras;

public class EventoNuevoCoche extends EventoNuevoVehiculo {
	protected int resistenciaKm;
	protected int duracionMaximaAveria;
	protected double probabilidadDeAveria;
	protected long numAleatorio;
	
	public EventoNuevoCoche(int tiempo,String id,String [] itinerario,int velmax,int resistencia,double propAveria,int durMaxAve,long semilla) {
		super(tiempo,id,itinerario,velmax);
		// TODO Auto-generated constructor stub
		this.resistenciaKm = resistencia;
		this.probabilidadDeAveria = propAveria;
		this.duracionMaximaAveria= durMaxAve;
		this.numAleatorio =semilla;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, ErrorCarretera {
		// TODO Auto-generated method stub
		List<CruceGenerico<?>> iti = ParserCarreteras.parseaListaCruces(this.itinerario,mapa);
		// si iti es null o tiene menos de dos cruces lanzar excepción
		if(iti== null || iti.size() < 2)
			throw new EjecutaException("la Lista de cruces esta vacia o es menos de dos");
		// en otro caso crear el vehículo y añadirlo al mapa.
		else {
			
			Coche nuevo= new Coche(this.id,this.velocidadMaxima,iti,this.resistenciaKm,this.probabilidadDeAveria,this.duracionMaximaAveria,this.numAleatorio);
			mapa.addVehiculo(this.id, nuevo);
	}

	
}
	@Override
		public String toString() {
			return "nuevo Coche -> " + this.id;
			
		}
}
