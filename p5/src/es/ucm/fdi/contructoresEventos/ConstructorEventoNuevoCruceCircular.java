package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCruceCircular;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruceCircular extends ConstructorEventos {
private String[] valoresPorDefecto;
	
	public ConstructorEventoNuevoCruceCircular() {
		this.etiqueta = "new_junction";
		this.claves = new String[] { "time", "id","type","max_time_slice","min_time_slice" };
		this.valoresPorDefecto = new String[] { "", "","rr","","" };
		}
		
		@Override
		public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
		!section.getValue("type").equals("rr")) return null;
		else
		return new EventoNuevoCruceCircular(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"),
		ConstructorEventos.parseaInt(section, "min_time_slice"),
		ConstructorEventos.parseaInt(section, "max_time_slice"));
		
		}
		
		public String toString() { return "New RR Junction"; }
	
		public String template() {
			String s = "[" + this.etiqueta + "]" + '\n';
			for (int i = 0; i < claves.length; i++) {
				s += claves[i] + " = " + valoresPorDefecto[i] + '\n';
			}
			
			return  s;
		
		}
}


