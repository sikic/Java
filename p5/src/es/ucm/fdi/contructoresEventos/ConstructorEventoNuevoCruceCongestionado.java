package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCruceCongestionado;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruceCongestionado extends ConstructorEventos {

private String[] valoresPorDefecto;
	
	public ConstructorEventoNuevoCruceCongestionado() {
		this.etiqueta = "new_junction";
		this.claves = new String[] { "time", "id","type" };
		this.valoresPorDefecto = new String[] { "", "","mc" };
		}
		
	
		public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
			!section.getValue("type").equals("mc")) return null;
		else
		return new EventoNuevoCruceCongestionado(
		// extrae el valor del campo �time� en la secci�n
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo �id� de la secci�n
		ConstructorEventos.identificadorValido(section, "id"));
		}
		
		public String toString() { return "New MC Junction "; }
		public String template() {
			String s = "[" + this.etiqueta + "]" + '\n';
			for (int i = 0; i < claves.length; i++) {
				s += claves[i] + " = " + valoresPorDefecto[i] + '\n';
			}
			
			return  s;
		}
}
