package es.ucm.fdi.objetos;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.*;

import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.eventos.EventoNuevoVehiculo;
import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;



public class SimuladorTrafico {

	private MapaCarreteras mapa;
	private List<Evento> eventos;
	private int contadorTiempo;
	
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
			evento.ejecuta(mapa);// los eventos no estan ordenados por lo que es la unica formas de hacerlo
		}
		// actualizar “mapa”	
		// escribir el informe en “ficheroSalida”, controlando que no sea null.
		this.mapa.actualizar();
		ficheroSalida.write(this.mapa.generateReport(this.contadorTiempo+1).getBytes());
		this.contadorTiempo++;
		
		}
}

public void insertaEvento(Evento e) {
	// inserta un evento en “eventos”, controlando que el tiempo de
	// ejecución del evento sea menor que “contadorTiempo”
	if(this.contadorTiempo <= e.getTiempo()) {
		this.eventos.add(e);
		
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


}
