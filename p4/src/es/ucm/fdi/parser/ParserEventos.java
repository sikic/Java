package es.ucm.fdi.parser;

import es.ucm.fdi.contructoresEventos.ConstructorEventoAveriaCoche;
import es.ucm.fdi.contructoresEventos.*;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevaCarretera;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoCruce;
import es.ucm.fdi.contructoresEventos.ConstructorEventoNuevoVehiculo;
import es.ucm.fdi.contructoresEventos.ConstructorEventos;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.ini.IniSection;

public class ParserEventos{
	
	private static ConstructorEventos[] constructoresEventos = {
			new ConstructorEventoNuevoCruce(),
			new ConstructorEventoNuevaCarretera(),
			new ConstructorEventoNuevoVehiculo(),
			new ConstructorEventoAveriaCoche(),
			new ConstructorEventoNuevaAutopista(),
			new ConstructorEventoNuevoCamino(),
			new ConstructorEventoNuevoCoche(),
			new ConstructorEventoNuevaBicicleta(),
			new ConstructorEventoNuevoCruceCircular(), 
			new ConstructorEventoNuevoCruceCongestionado() 
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
}
