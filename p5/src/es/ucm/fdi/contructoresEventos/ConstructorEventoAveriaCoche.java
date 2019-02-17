package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaAveria;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoAveriaCoche extends ConstructorEventos {
	
private String[] valoresPorDefecto;
	
	public ConstructorEventoAveriaCoche() {
		this.etiqueta = "make_vehicle_faulty";
		this.claves = new String[] { "time", "vehicles","duration"};
		this.valoresPorDefecto = new String[] { "", "", ""};
		}
		
	
		public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
		section.getValue("type") != null) return null;
		else
		return new EventoNuevaAveria(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "vehicles"),
		ConstructorEventos.parseaInt(section, "duration"));
		}
		
		public String toString() { return "New vehicle faulty"; }
		public String template() {
			String s = "[" + this.etiqueta + "]" + '\n';
			for (int i = 0; i < claves.length; i++) {
				s += claves[i] + " = " + valoresPorDefecto[i] + '\n';
			}
			
			return  s;
		}

}
