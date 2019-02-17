package es.ucm.fdi.objetos;

import java.util.*;

import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.ini.IniSection;

	public class Cruce extends CruceGenerico<CarreteraEntrante> {
	 protected int indiceSemaforoVerde; // lleva el �ndice de la carretera entrante
	 // con el sem�foro en verde
	 protected List<CarreteraEntrante> carreterasEntrantes;

	 // para optimizar las b�squedas de las carreterasEntrantes
	 // (IdCarretera, CarreteraEntrante)
	 protected Map<String,CarreteraEntrante> mapaCarreterasEntrantes;
	 protected Map<CruceGenerico<?>, Carretera> CarreterasSalientes;
	
	 public Cruce(String id) {
		 super(id);
		 this.carreterasEntrantes = new ArrayList<CarreteraEntrante>();
		 this.mapaCarreterasEntrantes = new HashMap<String,CarreteraEntrante>();
		 this.CarreterasSalientes = new HashMap<CruceGenerico<?>,Carretera>();
		 this.indiceSemaforoVerde = 0;
		 
	 }
	 public Carretera carreteraHaciaCruce(Cruce cruce) {
	  // devuelve la carretera que llega a ese cruce desde �this�
		return this.CarreterasSalientes.get(cruce);
	 }
	 public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
	  // a�ade una carretera entrante al �mapaCarreterasEntrantes� y
	  // a las �carreterasEntrantes�
		 CarreteraEntrante entra = new CarreteraEntrante(carretera);
		 this.carreterasEntrantes.add(entra);
		 this.mapaCarreterasEntrantes.put(idCarretera,entra);
	 }
	 public void addCarreteraSalienteAlCruce(Cruce destino, Carretera road) {
	  // a�ade una carretera saliente
		 this.CarreterasSalientes.put(destino, road);
	 }
	 public void entraVehiculoAlCruce(String idCarretera, Vehiculo vehiculo){//No sabemos si a�adirselo al mapa o a list.
	  // a�ade el �vehiculo� a la carretera entrante �idCarretera�
		 this.mapaCarreterasEntrantes.get(idCarretera).addVehiculo(vehiculo);
		 
	 }
	 protected void actualizaSemaforos(){
	  // pone el sem�foro de la carretera actual a �rojo�, y busca la siguiente
	  // carretera entrante para ponerlo a �verde�
		 if (this.carreterasEntrantes.get(indiceSemaforoVerde).semaforo == false) {
			 this.carreterasEntrantes.get(indiceSemaforoVerde).semaforo = true;
		 }
		 else if (this.indiceSemaforoVerde + 1 < this.carreterasEntrantes.size()){
		this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(false);
		 this.carreterasEntrantes.get(this.indiceSemaforoVerde +1).ponSemaforo(true);
		 this.indiceSemaforoVerde++;
		 }
		 else {
			 this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(false);
			 this.indiceSemaforoVerde = 0;
			 this.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
		 }
	 }
	 @Override
	 public void avanza() throws ErrorCarretera {
	 // Si �carreterasEntrantes� es vac�o, no hace nada.
	 // en otro caso �avanzaPrimerVehiculo� de la carretera con el sem�foro verde.
	 // Posteriormente actualiza los sem�foros.
		 if (!this.carreterasEntrantes.isEmpty()) {
			 this.carreterasEntrantes.get(indiceSemaforoVerde).avanzaPrimerVehiculo();
			 actualizaSemaforos();
		 }
	 }
	 
	 @Override
	 protected String getNombreSeccion() {
		 return "junction_report";
		 
	 }
	 @Override
	 protected void completaDetallesSeccion(IniSection is) {
	  // genera la secci�n queues = (r2,green,[]),...
		 String s ="";
		 
		 for (CarreteraEntrante e : carreterasEntrantes) {
			s +=  "(" + e.carretera.id + ",";
					if(this.carreterasEntrantes.size() != 0)
			 s += colorsemaforo(e.semaforo);
					
					
						s+= ",[";
						
					
					for (Vehiculo v: e.colaVehiculos) {
						
						s += v.id;
						if (e.colaVehiculos.get(e.colaVehiculos.size()-1)!= v)
						s +=  ",";
						
					}
					
					s +="]";
					s += "),";
					
		 }
		 
		if( s != "")
			s = s.substring(0,s.length() -1);
		 is.setValue("queues",s);
		 
	 }
	 protected String colorsemaforo(boolean semaforo) {
		 if(semaforo)
			 return "green";
		 else
			 return "red";
	 }
	@Override
	protected CarreteraEntrante creaCarreteraEntrante(Carretera carretera) {
		// TODO Auto-generated method stub
		return null;
	}
}