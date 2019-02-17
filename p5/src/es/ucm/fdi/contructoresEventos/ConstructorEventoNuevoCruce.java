package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCruce;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruce extends ConstructorEventos {

	private String[] valoresPorDefecto;
	
	public ConstructorEventoNuevoCruce() {
		this.etiqueta = "new_junction";
		this.claves = new String[] { "time", "id" };
		this.valoresPorDefecto = new String[] { "", "", };
		}
		
	
		public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
		section.getValue("type") != null) return null;
		else
		return new EventoNuevoCruce(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"));
		}
		
		public String toString() { return "New Junction"; }
	
		public String template() {
			String s = "[" + this.etiqueta + "]" + '\n';
			for (int i = 0; i < claves.length; i++) {
				s += claves[i] + " = " + valoresPorDefecto[i] + '\n';
			}
			
			return  s;
		}
}
