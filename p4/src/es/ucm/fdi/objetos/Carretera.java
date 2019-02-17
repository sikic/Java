package es.ucm.fdi.objetos;
import java.util.*;

import es.ucm.fdi.ini.IniSection;

public class Carretera extends ObjetoSimulacion{

	protected  int longitud;
	protected int velocidadMaxima;
	protected CruceGenerico<?> cruceOrigen;
	protected CruceGenerico<?> cruceDestino;
	protected List<Vehiculo> vehiculos;
	protected Comparator<Vehiculo> cmp;
	
	public Carretera(String id, int longitud,int maxima, CruceGenerico<?> origen, CruceGenerico<?> destino) {
		super(id);
		this.longitud = longitud;
		this.velocidadMaxima= maxima;
		this.cruceOrigen = origen;
		this.cruceDestino = destino;
		this.cmp= new Comparator<Vehiculo>(){
			@Override
			public int compare(Vehiculo v1, Vehiculo v2) { // para que funcione el add de sortedArray
				// hay que invertir los valores que se retorman para que la ordene a la inversa
				if(v1.getLocalizacion() > v2.getLocalizacion() )
					return -1;
				else if (v1.getLocalizacion() < v2.getLocalizacion())
					return 1;
				else 
					
				return 0;
				
			}
		};
		this.vehiculos = new SortedArrayList<Vehiculo>(cmp);
		
	}

	public  int getLongitud() {
		return longitud;
	}
	
	public void avanza() {
		// calcular velocidad base de la carretera
		// inicializar obst�culos a 0
		// Para cada veh�culo de la lista �vehiculos�:
		 //1. Si el veh�culo est� averiado se incrementa el n�mero de obstaculos.
		 //2. Se fija la velocidad actual del veh�culo
	    //3. Se pide al veh�culo que avance.
		// ordenar la lista de veh�culos
		int velocidadBase= calculaVelocidadBase();
		int obstaculos = 0;
		for (Vehiculo vehiculo : vehiculos) {
			if(vehiculo.tiempoAveria > 0) { 
				obstaculos++;
				vehiculo.setVelocidadActual(0);
				vehiculo.avanza();
			}
			else {
				if(vehiculo.localizacion < this.longitud) {
				vehiculo.setVelocidadActual(velocidadBase/calculaFactorReduccion(obstaculos));
				}
				vehiculo.avanza();
			}
			
			
		}
		
		this.vehiculos.sort(cmp);
		
	}

	public void entraVehiculo(Vehiculo vehiculo) {
		// Si el veh�culo no existe en la carretera, se a�ade a la lista de veh�culos y
		// se ordena la lista.
		// Si existe no se hace nada.
		if(!this.vehiculos.contains(vehiculo))
			this.vehiculos.add(vehiculo);
			
		
		
		}
		public void saleVehiculo(Vehiculo vehiculo) {
		// elimina el veh�culo de la lista de veh�culos
			this.vehiculos.remove(vehiculo);
		}
		public void entraVehiculoAlCruce(Vehiculo v) {
		// a�ade el veh�culo al �cruceDestino� de la carretera�
			this.cruceDestino.entraVehiculoAlCruce(id, v);
		}
		protected int calculaVelocidadBase() {
			return Math.min(this.velocidadMaxima,(this.velocidadMaxima/(Math.max(this.vehiculos.size(),1))) +1);
		}
		protected int calculaFactorReduccion(int obstaculos) {
			if(obstaculos > 0) return 2;
			
			else return 1;
			}
		
		@Override
		protected String getNombreSeccion() {	return "road_report";}
		@Override
		protected void completaDetallesSeccion(IniSection is) {
		// crea �vehicles = (v1,10),(v2,10) �
			String s  = "";
			for (Vehiculo vehiculo : this.vehiculos) {
				//if(vehiculo.kilometraje != this.longitud)
				s += "(" + vehiculo.id + "," + vehiculo.localizacion+ ")" + ",";
			}
			if( s != "")
				s = s.substring(0,s.length() -1);
			is.setValue("state", s);
			
		}
}
