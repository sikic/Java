package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCamino;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCamino extends ConstructorEventos {

private String[] valoresPorDefecto;
	
	public ConstructorEventoNuevoCamino() {
		this.etiqueta = "new_road";
		this.claves = new String[] { "time", "id" ,"src","dest","max_speed","length","type"};
		this.valoresPorDefecto = new String[] { "", "","","","","","dirt" };
		}
		
	
		public Evento parser(IniSection section){
			
			if (!section.getTag().equals(this.etiqueta) ||
				!section.getValue("type").equals("dirt")) return null;
			else
				return new EventoNuevoCamino(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"),section.getValue("src"),section.getValue("dest"),
		Integer.valueOf(section.getValue("max_speed")),Integer.valueOf(section.getValue("length")));
		}
		
		
		public String toString() { return "New  DIRT Road"; }
		public String template() {
			String s = "[" + this.etiqueta + "]" + '\n';
			for (int i = 0; i < claves.length; i++) {
				s += claves[i] + " = " + valoresPorDefecto[i] + '\n';
			}
			
			return  s;
		}
}

