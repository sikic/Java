package es.ucm.fdi.contructoresEventos;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevaBicicleta;
import es.ucm.fdi.eventos.EventoNuevaGrua;
import es.ucm.fdi.eventos.EventoNuevoVehiculo;
import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaGrua extends ConstructorEventos {

	
private String[] valoresPorDefecto;
	
public ConstructorEventoNuevaGrua() {
	this.etiqueta = "new_vehicle";
	this.claves = new String[] { "time", "id","itinerary","max_speed","type"};
	this.valoresPorDefecto = new String[] { "", "", "","","grua",};
	}
	

	public Evento parser(IniSection section) {
	if (!section.getTag().equals(this.etiqueta) ||
	!section.getValue("type").equals("grua")) return null;
	else
	return new EventoNuevaGrua(
	// extrae el valor del campo “time” en la sección
	// 0 es el valor por defecto en caso de no especificar el tiempo
	ConstructorEventos.parseaIntNoNegativo(section, "time", 0),
	// extrae el valor del campo “id” de la sección
	ConstructorEventos.identificadorValido(section, "id"),section.getValue("itinerary").split(","),Integer.valueOf(section.getValue("max_speed")));
	}
	
	public String toString() { return "New Grua";}

	public String template() {
		String s = "[" + this.etiqueta + "]" + '\n';
		for (int i = 0; i < claves.length; i++) {
			s += claves[i] + " = " + valoresPorDefecto[i] + '\n';
		}
		
		return  s;
	}
}

