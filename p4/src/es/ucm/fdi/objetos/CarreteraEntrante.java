package es.ucm.fdi.objetos;

import java.util.*;

import es.ucm.fdi.excepciones.ErrorCarretera;

public class CarreteraEntrante {
	 protected Carretera carretera;
	 protected List<Vehiculo> colaVehiculos;
	 protected boolean semaforo; // true=verde, false=rojo
	 public CarreteraEntrante(Carretera carretera) {
	 // inicia los atributos.
	 // el semáforo a rojo
		 this.carretera = carretera;
		 this.semaforo = false;
		 this.colaVehiculos = new ArrayList<Vehiculo>();
	 }
	 void ponSemaforo(boolean color) {
		 this.semaforo = color;
	 }

	 protected void avanzaPrimerVehiculo() throws ErrorCarretera {
	 // coge el primer vehiculo de la cola, lo elimina,
	 // y le manda que se mueva a su siguiente carretera.
		 if(this.colaVehiculos.size() > 0) {
			 Vehiculo primero = this.colaVehiculos.get(0);
			 this.colaVehiculos.remove(primero);
			 primero.moverASiguienteCarretera();}
	 }
	 public void addVehiculo(Vehiculo v) {
		 this.colaVehiculos.add(v);
	 }
	 @Override
	 public String toString() {
		 return " ";
	 }
	 
	
}


