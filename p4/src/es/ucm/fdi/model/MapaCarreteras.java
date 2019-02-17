package es.ucm.fdi.model;

import java.util.*;

import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorEventoA�adido;
import es.ucm.fdi.objetos.Carretera;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.objetos.Vehiculo;
public class MapaCarreteras {

	private List<Carretera> carreteras;
	private List<CruceGenerico<?>> cruces;
	private List<Vehiculo> vehiculos;
	// estructuras para agilizar la b�squeda (id,valor)
	private Map<String, Carretera> mapaDeCarreteras;
	private Map<String, CruceGenerico<?>> mapaDeCruces;
	private Map<String, Vehiculo> mapaDeVehiculos;

	
	public MapaCarreteras() {
		// inicializa los atributos a sus constructoras por defecto.
		// Para carreteras, cruces y veh�culos puede usarse ArrayList.
		// Para los mapas puede usarse HashMap
		this.carreteras = new ArrayList<Carretera>();
		this.cruces = new ArrayList<CruceGenerico<?>>();
		this.vehiculos = new ArrayList<Vehiculo>();
		this.mapaDeCarreteras = new HashMap<String,Carretera>();
		this.mapaDeCruces = new HashMap<String,CruceGenerico<?>>();
		this.mapaDeVehiculos = new HashMap<String,Vehiculo>();
		
		}
		public void addCruce(String idCruce, CruceGenerico<?> cruce) throws ErrorEventoA�adido {
		// comprueba que �idCruce� no existe en el mapa.
		if(this.mapaDeCruces.containsKey(cruce)) 
			throw new ErrorEventoA�adido("Cruce  ya a�adido");
		else
			this.cruces.add(cruce);
			this.mapaDeCruces.put(idCruce, cruce);
		// Si no existe, lo a�ade a �cruces� y a �mapaDeCruces�.
		// Si existe lanza una excepci�n.
			
		}
		
		public void addVehiculo(String idVehiculo,Vehiculo vehiculo) throws ErrorEventoA�adido, ErrorCarretera {
			// comprueba que �idVehiculo� no existe en el mapa.
			if(this.mapaDeVehiculos.containsKey(vehiculo))
				throw new ErrorEventoA�adido("Vehiculo ya a�adido");
			else {
				this.mapaDeVehiculos.put(idVehiculo, vehiculo);
				this.vehiculos.add(vehiculo);
			}
				
				vehiculo.moverASiguienteCarretera();
			// Si no existe, lo a�ade a �vehiculos� y a �mapaDeVehiculos�,
			// y posteriormente solicita al vehiculo que se mueva a la siguiente
			// carretera de su itinerario (moverASiguienteCarretera).
			// Si existe lanza una excepci�n.
			}
		
		public void addCarretera(String idCarretera,CruceGenerico<?> origen,Carretera carretera,CruceGenerico<?> destino) throws ErrorEventoA�adido {
			// comprueba que �idCarretera� no existe en el mapa.	
			// Si no existe, lo a�ade a �carreteras� y a �mapaDeCarreteras�,
			if(this.mapaDeCarreteras.containsKey(carretera))
				throw new ErrorEventoA�adido("Carretera ya a�adida");
			else// y posteriormente actualiza los cruces origen y destino como sigue:
				this.carreteras.add(carretera);
				this.mapaDeCarreteras.put(idCarretera, carretera);
			// - A�ade al crude destino la carretera, como �carretera entrante�
				destino.addCarreteraEntranteAlCruce(idCarretera,carretera);
			// - A�ade al cruce origen la carretera, como �carretera saliente�
				origen.addCarreteraSalienteAlCruce(destino, carretera);
				
				// Si existe lanza una excepci�n.
			}
		
		public String generateReport(int time) {
			String report = "";
			// genera informe para cruces
			for (CruceGenerico<?> e : cruces) {
				report += e.generaInforme(time) + "\n";;
			}
			// genera informe para carreteras
			
			for (Carretera e : carreteras) {
				report += e.generaInforme(time) + "\n";;
			}
			// genera informe para vehiculos
			for (Vehiculo e : vehiculos) {
				report += e.generaInforme(time) + "\n";
			}
			return report;
			}
		
		   public void actualizar() throws ErrorCarretera {
			// llama al m�todo avanza de cada carretera
			   for (Carretera e : carreteras) {
				e.avanza();
			}
			// llama al m�todo avanza de cada cruce
			   for (CruceGenerico<?> e : cruces) {
				e.avanza();
			   }
			
			} 
		
		   public Vehiculo getVehiculo(String id) throws ErrorBusquedaMapa {
			// devuelve el veh�culo con ese �id� utilizando el mapaDeVehiculos.
					Vehiculo c = this.mapaDeVehiculos.get(id);
					if (c == null) throw new ErrorBusquedaMapa("No se ha encontrado el vehiculo en el mapa.");
			// sino existe el veh�culo lanza excepci�n.
					else return c;
			} 
		   
		   public Carretera getCarretera(String id) throws ErrorBusquedaMapa {
			// devuelve la carretera con ese �id� utilizando el mapaDeCarreteras.
			
					  Carretera c=  this.mapaDeCarreteras.get(id);
					  if (c == null) throw new ErrorBusquedaMapa("No se ha encontrado la carreterra en el mapa.");
			// sino existe la carretra lanza excepci�n.
					  else return c;
			}
		   public String toString() {
			   String s = "";
			   System.out.print(carreteras.size());
			   for (Carretera carretera : carreteras) {
				s += carretera;
			}
			   return s;
		   }
		public CruceGenerico<?> getCruce(String cruce) throws ErrorBusquedaMapa {
			  CruceGenerico<?> c = this.mapaDeCruces.get(cruce);
			   if (c == null) {
				   throw new ErrorBusquedaMapa("No se ha encontrado el cruce en el mapa.");
			   }
			   else return c;
		  
		}
	
}
