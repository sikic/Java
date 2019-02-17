package es.ucm.fdi.objetos;

import java.util.*;

import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.ini.IniSection;

	public class Cruce extends CruceGenerico<CarreteraEntrante>  {


	
	 public Cruce(String id) {
		 super(id);
		 
	 }
	 
	 public Carretera carreteraHaciaCruce(Cruce cruce) {
	  // devuelve la carretera que llega a ese cruce desde “this”
		return super.carreterasSalientes.get(cruce);
	 }
	 public void addCarreteraEntranteAlCruce(String idCarretera, Carretera carretera) {
	  // añade una carretera entrante al “mapacarreterasEntrantesmal” y
	  // a las “carreterasEntrantesmal”
		 CarreteraEntrante entra = new CarreteraEntrante(carretera);
		 super.carreterasEntrantes.add(entra);
		 super.mapaCarreterasEntrantes.put(idCarretera,entra);
	 }
	 public void addCarreteraSalienteAlCruce(Cruce destino, Carretera road) {
	  // añade una carretera saliente
		 super.carreterasSalientes.put(destino, road);
	 }
	 public void entraVehiculoAlCruce(String idCarretera, Vehiculo vehiculo){//No sabemos si añadirselo al mapa o a list.
	  // añade el “vehiculo” a la carretera entrante “idCarretera”
		 super.mapaCarreterasEntrantes.get(idCarretera).addVehiculo(vehiculo);
		 
	 }
	 protected void actualizaSemaforos(){
	  // pone el semáforo de la carretera actual a “rojo”, y busca la siguiente
	  // carretera entrante para ponerlo a “verde”
		 if (this.indiceSemaforoVerde == -1) {
			 this.indiceSemaforoVerde = 0;
			 super.carreterasEntrantes.get(indiceSemaforoVerde).semaforo = true;
		 }
		 else if (this.indiceSemaforoVerde + 1 < super.carreterasEntrantes.size()){
		super.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(false);
		 super.carreterasEntrantes.get(this.indiceSemaforoVerde +1).ponSemaforo(true);
		 this.indiceSemaforoVerde++;
		 }
		 else {
			 super.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(false);
			 this.indiceSemaforoVerde = 0;
			 super.carreterasEntrantes.get(this.indiceSemaforoVerde).ponSemaforo(true);
		 }
	 }
	
	 @Override
	 protected String getNombreSeccion() {
		 return "junction_report";
		 
	 }
	 @Override
	 protected void completaDetallesSeccion(IniSection is) {
	  // genera la sección queues = (r2,green,[]),...
String s ="";
		 
		 for (CarreteraEntrante e : super.carreterasEntrantes) {
			s +=  "(" + e.carretera.id + ",";
					if(super.carreterasEntrantes.size() != 0)
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
		CarreteraEntrante c = new CarreteraEntrante(carretera);
		return c;
	}
	
	
}