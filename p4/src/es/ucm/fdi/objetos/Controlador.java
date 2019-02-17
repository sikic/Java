package es.ucm.fdi.objetos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.ini.Ini;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.objetos.SimuladorTrafico;
import es.ucm.fdi.parser.ParserEventos;

public class Controlador {

	private SimuladorTrafico simulador;
	private OutputStream ficheroSalida;
	private InputStream ficheroEntrada;
	private int pasosSimulacion;
	
	public Controlador(SimuladorTrafico sim, Integer limiteTiempo, InputStream is, OutputStream os) {
		this.simulador = sim;
		this.pasosSimulacion = limiteTiempo;
		this.ficheroEntrada = is ;
		this.ficheroSalida = os;
	}
	public void ejecuta() throws ErrorDeSimulacion, EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, IOException, ErrorCarretera {
		// lee los eventos y se los manda al simulador
		this.cargaEventos(this.ficheroEntrada);
		this.simulador.ejecuta(this.pasosSimulacion,this.ficheroSalida);
	}
	
	private void cargaEventos(InputStream inStream) throws ErrorDeSimulacion {
		
		Ini ini;
		try {
			//lee el fichero y carga su atributo iniSections
			ini = new Ini(inStream);
		}catch(IOException e) {
			throw new ErrorDeSimulacion("Error en la lectura de eventos: " + e);
		}
		//recorremos todos los elementos de iniSections para generar el evento correspondiente
		for (IniSection sec: ini.getSections()) {
		// parseamos la sección para ver a que evento corresponde
		Evento e = ParserEventos.parseaEvento(sec);
		if (e != null) 
			this.simulador.insertaEvento(e);
		else
			throw new ErrorDeSimulacion("Evento desconocido: " + sec.getTag());
	}
	
   }
}
