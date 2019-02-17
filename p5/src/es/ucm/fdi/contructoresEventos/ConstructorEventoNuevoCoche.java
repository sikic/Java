package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoCoche;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCoche extends ConstructorEventos {
		private String[] valoresPorDefecto;
	
	public  ConstructorEventoNuevoCoche() {
		this.etiqueta = "new_vehicle";
		this.claves = new String[] { "time", "id","itinerary","max_speed","type","resistance","fault_probability","max_fault_duration","seed"};
		this.valoresPorDefecto = new String[] { "", "","","","car", "","","","" };
		
		}
		
		@Override
		public Evento parser(IniSection section) {
		if (!section.getTag().equals(this.etiqueta) ||
		!section.getValue("type").equals("car")) return null;
		long aux;
		aux = Long.valueOf(section.getValue("seed"));
		return new EventoNuevoCoche(
		// extrae el valor del campo “time” en la sección
		// 0 es el valor por defecto en caso de no especificar el tiempo
		ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
		// extrae el valor del campo “id” de la sección
		ConstructorEventos.identificadorValido(section, "id"),section.getValue("itinerary").split(","),Integer.valueOf(section.getValue("max_speed")),
		ConstructorEventos.parseaIntNoNegativo(section,"resistance",0),Double.valueOf(section.getValue("fault_probability")),
		ConstructorEventos.parseaIntNoNegativo(section, "max_fault_duration", 0),
		aux);
		
		}
		
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		return "New Car";
		}
		public String template() {
			String s = "[" + this.etiqueta + "]" + '\n';
			for (int i = 0; i < claves.length; i++) {
				s += claves[i] + " = " + valoresPorDefecto[i] + '\n';
			}
			
			return  s;
		}
		}
	
	


