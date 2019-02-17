package es.ucm.fdi.control;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.*;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;

//alias modelo

public class SimuladorTrafico  implements Observador<ObservadorSimuladorTrafico> {

	private MapaCarreteras mapa;
	private List<Evento> eventos;
	private int contadorTiempo;
	private List<ObservadorSimuladorTrafico> observadores;
	
	public SimuladorTrafico() {
		this.mapa = new MapaCarreteras();
		this.contadorTiempo = 0;
		Comparator<Evento> cmp = new Comparator<Evento>(){
			@Override
			public int compare(Evento arg0, Evento arg1) {
				if(arg0.getTiempo() > arg1.getTiempo())
					return 1;
				else if (arg0.getTiempo() < arg1.getTiempo())
					return -1;
				else 
					
				return 0;
				
			}
		};
		
		this.eventos = new SortedArrayList<Evento>(cmp);// estructura ordenada por “tiempo”
		this.observadores = new ArrayList<ObservadorSimuladorTrafico>();
}

	
	
public List<Evento> getEventos() {
		return eventos;
	}



public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida) throws EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, IOException, ErrorCarretera {
		int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		while (this.contadorTiempo <= limiteTiempo) {
		// ejecutar todos los eventos correspondienes a “this.contadorTiempo”
		
		for (Evento evento : eventos) {
			if(this.contadorTiempo== evento.getTiempo()) 
				try {
					evento.ejecuta(mapa);
				} catch (Exception e) {
					this.notificaError(new ErrorDeSimulacion("error al ejecutar la simulacion"));
				} // los eventos no estan ordenados por lo que es la unica formas de hacerlo
		}
		// actualizar “mapa”	
		// escribir el informe en “ficheroSalida”, controlando que no sea null.
		this.mapa.actualizar();
		this.notificaAvanza();
		ficheroSalida.write(this.mapa.generateReport(this.contadorTiempo+1).getBytes());
		this.contadorTiempo++;
		
		}
}

private void notificaAvanza() {
	for (ObservadorSimuladorTrafico o : this.observadores) {
		o.avanza(this.contadorTiempo, mapa, eventos);
		}
}



public void insertaEvento(Evento e) throws ErrorDeSimulacion {
	// inserta un evento en “eventos”, controlando que el tiempo de
	// ejecución del evento sea menor que “contadorTiempo”
	//ErrorDeSimulacion err = new ErrorDeSimulacion();
	if(this.contadorTiempo <= e.getTiempo()) {
		if(this.eventos.add(e))
			this.notificaNuevoEvento(); // se notifica a los observadores
		else 
			this.notificaError(new ErrorDeSimulacion());
		
	}
}



public void notificaError(ErrorDeSimulacion err) {
	
	for (ObservadorSimuladorTrafico o : this.observadores) {
		o.errorSimulador(this.contadorTiempo,this.mapa, eventos, err);
		}
}



public void notificaNuevoEvento() {
	
	for (ObservadorSimuladorTrafico o : this.observadores) {
		o.addEvento(this.contadorTiempo,this.mapa,this.eventos);
		}
		
}



@Override
public String toString() {
	String s="" ;
	for (int i = 0; i < this.eventos.size(); i++) {
		s += this.eventos.get(i) + "\n";
	}
	return s;
}



@Override
public void addObservador(ObservadorSimuladorTrafico o) {
	if (o != null && !this.observadores.contains(o)) {
		this.observadores.add(o);
}
}



@Override
public void removeObservador(ObservadorSimuladorTrafico o) {
	if (o != null && !this.observadores.contains(o)) {
		this.observadores.add(o);
}
}

public void notificaReinicia() {
	for (ObservadorSimuladorTrafico o : this.observadores) {
		o.reinicia(this.contadorTiempo, mapa, eventos);
		}
}



public void reinicia() {
	
	this.mapa = new MapaCarreteras();
	this.contadorTiempo = 0;
	Comparator<Evento> cm = new Comparator<Evento>(){
		@Override
		public int compare(Evento arg0, Evento arg1) {
			if(arg0.getTiempo() > arg1.getTiempo())
				return 1;
			else if (arg0.getTiempo() < arg1.getTiempo())
				return -1;
			else 
				
			return 0;
			
		}
	};
	
	this.eventos = new SortedArrayList<Evento>(cm);// estructura ordenada por “tiempo”
	for (ObservadorSimuladorTrafico o : this.observadores) {
		removeObservador(o);
		}
	
}


}
