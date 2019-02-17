package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaCarretera;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaCarretera extends ConstructorEventos {
	
	public ConstructorEventoNuevaCarretera() {
		this.etiqueta = "new_road";
		this.claves = new String[] { "time", "id" ,"src","dest","max_speed","length",/*"lanes"*/};
		}
		
	
		public Evento parser(IniSection section){
			if (!section.getTag().equals(this.etiqueta) ||
				section.getValue("type") != null) return null;
			else
				return new EventoNuevaCarretera(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"),section.getValue("src"),section.getValue("dest"),
		Integer.valueOf(section.getValue("max_speed")),Integer.valueOf(section.getValue("length")));
		}
		
		
		public String toString() { return "New Road"; }
		
}
