package es.ucm.fdi.objetos;

import java.util.*;

import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.ini.IniSection;

abstract public class CruceGenerico<T extends CarreteraEntrante> extends ObjetoSimulacion {

	protected int indiceSemaforoVerde;
	protected List<T> carreterasEntrantes;
	protected Map<String,T> mapaCarreterasEntrantes;
	protected Map<CruceGenerico<?>, Carretera> carreterasSalientes;
	protected int minValorIntervalo;
	protected int maxValorIntervalo;
	
	public CruceGenerico(String id) {
		super(id);
		 this.carreterasEntrantes = new ArrayList<T>();
		 this.mapaCarreterasEntrantes = new HashMap<String,T>();
		 this.carreterasSalientes = new HashMap<CruceGenerico<?>,Carretera>();
		 this.indiceSemaforoVerde = 0;
		 
	}

	

	public void avanza() throws ErrorCarretera {
		// Si “carreterasEntrantes” es vacío, no hace nada.
		 // en otro caso “avanzaPrimerVehiculo” de la carretera con el semáforo verde.
		 // Posteriormente actualiza los semáforos.
			 if (!this.carreterasEntrantes.isEmpty()) {
				 this.carreterasEntrantes.get(indiceSemaforoVerde).avanzaPrimerVehiculo();
				 actualizaSemaforos();
			 }
		 }
		
	abstract protected void actualizaSemaforos();
	public String generaInforme(int time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getNombreSeccion() {
		// TODO Auto-generated method stub
		return null;
	}

	public void entraVehiculoAlCruce(String id, Vehiculo v) {
		this.mapaCarreterasEntrantes.get(id).addVehiculo(v);
	}

	public void addCarreteraSalienteAlCruce(CruceGenerico<?> destino, Carretera carretera) {
		 this.carreterasSalientes.put(destino, carretera);
	}

	public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
		T ri = creaCarreteraEntrante(carretera);
		this.carreterasEntrantes.add(ri);
		this.mapaCarreterasEntrantes.put(idCarretera, ri);
		
	}

	abstract protected T creaCarreteraEntrante(Carretera carretera);
	
	public Carretera carreteraHaciaCruce(CruceGenerico<?> cruce) {
		return this.carreterasSalientes.get(cruce);
	}

}
