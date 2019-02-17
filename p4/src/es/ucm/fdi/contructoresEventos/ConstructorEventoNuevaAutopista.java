package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaAutopista;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaAutopista extends ConstructorEventos{

	public ConstructorEventoNuevaAutopista() {
		this.etiqueta = "new_road";
		this.claves = new String[] { "time", "id" ,"src","dest","max_speed","length","type","lanes"};
	}
	@Override
	public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
				section.getValue("type") != null) return null;
			else
				return new EventoNuevaAutopista(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"),section.getValue("src"),section.getValue("dest"),
		Integer.valueOf(section.getValue("max_speed")),Integer.valueOf(section.getValue("length")), Integer.valueOf(section.getValue("lanes")));
		}
		
	public String toString() { return "New Road";}
}
