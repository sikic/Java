package es.ucm.fdi.objetos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.excepciones.ErrorCarretera;

abstract public class CruceGenerico<T extends CarreteraEntrante> extends ObjetoSimulacion {
	protected int indiceSemaforoVerde;
	 protected List<T> carreterasEntrantes;
	 protected Map<String,T> mapaCarreterasEntrantes;
	 protected Map<CruceGenerico<?>, Carretera> carreterasSalientes;
	 public CruceGenerico(String id) {
		super(id);
		 this.carreterasEntrantes = new ArrayList<T>();
		 this.mapaCarreterasEntrantes = new HashMap<String,T>();
		 this.carreterasSalientes = new HashMap<CruceGenerico<?>,Carretera>();
		 this.indiceSemaforoVerde = -1;
		// TODO Auto-generated constructor stub
	}
	
	 public Carretera carreteraHaciaCruce(CruceGenerico<?> cruce) {
		 return this.carreterasSalientes.get(cruce);
	 }
	 public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
	 T ri = creaCarreteraEntrante(carretera);
	 this.carreterasEntrantes.add(ri);
	 this.mapaCarreterasEntrantes.put(idCarretera, ri);
	
	 }
	 abstract protected T creaCarreteraEntrante(Carretera carretera);
	 
	 public void addCarreteraSalienteAlCruce(CruceGenerico<?> destino, Carretera carr) {
		 this.carreterasSalientes.put(destino, carr);
	 }
	 public void entraVehiculoAlCruce(String idCarretera, Vehiculo vehiculo){
		 this.mapaCarreterasEntrantes.get(idCarretera).addVehiculo(vehiculo);
	 }
	 @Override
	 public void avanza() throws ErrorCarretera {
		 if (!this.carreterasEntrantes.isEmpty()) {
			 if(this.indiceSemaforoVerde != -1) 
			 this.carreterasEntrantes.get(indiceSemaforoVerde).avanzaPrimerVehiculo();
			 this.actualizaSemaforos();
		 }
	 }
	 abstract protected void actualizaSemaforos();

	public List<T> getCarreteras() {
		// TODO Auto-generated method stub
		return this.carreterasEntrantes;
	}

	public String verde() {
		String s="[";
		for (int i = 0; i < carreterasEntrantes.size(); i++) {
			if(this.carreterasEntrantes.get(i).semaforo) {
				s += this.carreterasEntrantes.get(i).getCarretera().getId() + "," +
						colorsemaforo(this.carreterasEntrantes.get(i).semaforo) + ";" + this.carreterasEntrantes.get(i).getColaVehiculos().size();
			}

			
		}
		s+="]";
		return s;
}
	
	
	protected String colorsemaforo(boolean semaforo) {
		// TODO Auto-generated method stub
		 if(semaforo)
			 return "green";
		 else
			 return "red";
	}

	public String rojo() {
		String s="[";
		for (int i = 0; i < carreterasEntrantes.size(); i++) {
			if(!this.carreterasEntrantes.get(i).semaforo) {
				s += this.carreterasEntrantes.get(i).getCarretera().getId() + "," +
						colorsemaforo(this.carreterasEntrantes.get(i).semaforo) + ";" + this.carreterasEntrantes.get(i).getColaVehiculos().size();
			}

			
		}
		s+="]";
		return s;
	}
 
	 
	}
