package es.ucm.fdi.parser;

import es.ucm.fdi.contructoresEventos.ConstructorEventoAveriaCoche;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevaAutopista;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevaBicicleta;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevaCarretera;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevaGrua;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoCamino;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoCoche;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoCruce;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoCruceCircular;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoCruceCongestionado;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoCruceRotonda;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoVehiculo;
import es.ucm.fdi.contructoresEventos.ConstructorEventos;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.ini.IniSection;

public class ParserEventos{
	
	private static ConstructorEventos[] constructoresEventos = {
			new ConstructorEventoAveriaCoche(),
			new ConstructorEventoNuevoCruce(),
			new ConstructorEventoNuevaCarretera(),
			new ConstructorEventoNuevoVehiculo(),
			new ConstructorEventoNuevaBicicleta(),
			new ConstructorEventoNuevaAutopista(),
			new ConstructorEventoNuevoCamino(),
			new ConstructorEventoNuevoCruceCircular(),
			new ConstructorEventoNuevoCruceCongestionado(),
			new ConstructorEventoNuevoCruceRotonda(),
			new ConstructorEventoNuevoCoche(),
			new ConstructorEventoNuevaGrua()
			};
	
			// bucle de prueba y error
			public static Evento parseaEvento(IniSection sec) {
			int i = 0;
			boolean seguir = true;
			Evento e = null;
			while (i < ParserEventos.constructoresEventos.length && seguir ) {
			// ConstructorEventos contiene el método parse(sec)
			e = ParserEventos.constructoresEventos[i].parser(sec);
			if (e!=null) seguir = false;
			else i++;
			}
			return e;
	}
		
	private static String template() {
		String s="";
		
		for (ConstructorEventos c : constructoresEventos) {
			s = c.toString();
		}
		return s;
	}

	public static ConstructorEventos[] getConstructoresEventos() {
		return constructoresEventos;
	}

}
