package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCruceCircular;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruceCircular extends ConstructorEventos {

	public ConstructorEventoNuevoCruceCircular() {
		this.etiqueta = "new_junction";
		this.claves = new String[] { "time", "id","max_time_slice","min_time_slice","type" };
		}
		
	
		public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
		section.getValue("type") != null) return null;
		else
		return new EventoNuevoCruceCircular(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"),Integer.valueOf(section.getValue("max_time_slice"))
		,Integer.valueOf(section.getValue("min_time_slice")));
		}
		
		public String toString() { return "New Junction"; }
	
}

