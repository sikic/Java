package es.ucm.fdi.control;



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
import es.ucm.fdi.control.SimuladorTrafico;
import es.ucm.fdi.parser.ParserEventos;

public class Controlador{

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
	
	public void ejecuta(int pasos) throws ErrorDeSimulacion, EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, IOException, ErrorCarretera {
		// lee los eventos y se los manda al simulador
		
		this.simulador.ejecuta(pasos,this.ficheroSalida);
		
		
	}
	
	public void cargaEventos(InputStream inStream) throws ErrorDeSimulacion {
		
		Ini ini= null;;
		try {
			//lee el fichero y carga su atributo iniSections
			ini = new Ini(inStream);
		}catch(IOException e) {
			this.simulador.notificaError(new ErrorDeSimulacion("Error en la lectura de eventos: " + e));
		}
		//recorremos todos los elementos de iniSections para generar el evento correspondiente
		for (IniSection sec: ini.getSections()) {
		// parseamos la sección para ver a que evento corresponde
		Evento e = ParserEventos.parseaEvento(sec);
		if (e != null) 
			this.simulador.insertaEvento(e);
		else
			new ErrorDeSimulacion("Evento desconocido: " + sec.getTag());
	}
	
   }
	public void addObserver(ObservadorSimuladorTrafico t) {
		this.simulador.addObservador(t);
	}
	
	public void reinicia() {
		this.simulador.reinicia();
		this.simulador.notificaReinicia();
	}
	public int getPasos() {
		// TODO Auto-generated method stub
		return this.pasosSimulacion;
	}
	
	
	
}
