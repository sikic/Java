package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaAutopista;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaAutopista extends ConstructorEventos{
private String[] valoresPorDefecto;
	
	public ConstructorEventoNuevaAutopista() {
		this.etiqueta = "new_road";
		this.claves = new String[] { "time", "id" ,"src","dest","max_speed","length","type"};
		this.valoresPorDefecto = new String[] { "", "","","","","","lanes"};
		}
		
	
		public Evento parser(IniSection section){
			
			if (!section.getTag().equals(this.etiqueta) ||
				!section.getValue("type").equals("lanes"))  return null;
			else
				return new EventoNuevaAutopista(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"),section.getValue("src"),section.getValue("dest"),
		Integer.valueOf(section.getValue("max_speed")),Integer.valueOf(section.getValue("length")),parseaInt(section, "lanes"));
		}
		
		
		public String toString() { return "New LANES Road"; }
		public String template() {
			String s = "[" + this.etiqueta + "]" + '\n';
			for (int i = 0; i < claves.length; i++) {
				s += claves[i] + " = " + valoresPorDefecto[i] + '\n';
			}
			
			return  s;
		}

}
