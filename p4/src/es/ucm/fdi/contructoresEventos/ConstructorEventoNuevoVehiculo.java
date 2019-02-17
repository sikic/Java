package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoVehiculo;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoVehiculo extends ConstructorEventos {

	
	public ConstructorEventoNuevoVehiculo() {
		this.etiqueta = "new_vehicle";
		this.claves = new String[] { "time", "id","itinerary","max_speed"};
		}
		
	
		public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
		section.getValue("type") != null) return null;
		else
		return new EventoNuevoVehiculo(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"),section.getValue("itinerary").split(","),Integer.valueOf(section.getValue("max_speed")));
		}
		
		public String toString() { return "New Vehicle"; }
	
}
