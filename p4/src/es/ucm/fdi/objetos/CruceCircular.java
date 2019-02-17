package es.ucm.fdi.objetos;

import es.ucm.fdi.ini.IniSection;

public class CruceCircular extends CruceGenerico<CarreteraEntranteConIntervalo>{
	
	public CruceCircular(String id, Integer minValorIntervalo, Integer maxValorIntervalo) {
		super(id);
		this.minValorIntervalo = minValorIntervalo;
		this.maxValorIntervalo = maxValorIntervalo;
		this.indiceSemaforoVerde = 0; 

	}

	@Override
	protected void actualizaSemaforos() {
		/*- Si no hay carretera con semáforo en verde (indiceSemaforoVerde == -1) entonces se
		selecciona la primera carretera entrante (la de la posición 0) y se pone su
		semáforo en verde.
		- Si hay carretera entrante "ri" con su semáforo en verde, (indiceSemaforoVerde !=
		-1) entonces:
		1. Si ha consumido su intervalo de tiempo en verde ("ri.tiempoConsumido()"):
		1.1. Se pone el semáforo de "ri" a rojo.
		1.2. Si ha sido usada en todos los pasos (“ri.usoCompleto()”), se fija
		el intervalo de tiempo a ... Sino, si no ha sido usada
		(“!ri.usada()”) se fija el intervalo de tiempo a ...
		1.3. Se coge como nueva carretera con semáforo a verde la inmediatamente
		Posterior a “ri”.*/
		if(this.carreterasEntrantes.get(indiceSemaforoVerde).semaforo == false)
			this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(true);
		
		else {
			if(this.carreterasEntrantes.get(indiceSemaforoVerde).tiempoConsumido()){
				//pone el semaforo a rojo
				this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(false);
				// actualiza  el intervalo de tiempo en funcion de  si ha sido completamnete usado o no
				if(this.carreterasEntrantes.get(indiceSemaforoVerde).usada())
					this.carreterasEntrantes.get(indiceSemaforoVerde).setIntervalo
					(Math.min(this.carreterasEntrantes.get(indiceSemaforoVerde).getIntervalo() +1,this.maxValorIntervalo));
				else
					this.carreterasEntrantes.get(indiceSemaforoVerde).setIntervalo
					(Math.max(this.carreterasEntrantes.get(indiceSemaforoVerde).getIntervalo() -1,this.minValorIntervalo));
				// pone unidades usadas a 0
				this.carreterasEntrantes.get(indiceSemaforoVerde).setUnidadesDeTiempoUsadas(0);
				// pone en verde la siguiente,
				this.carreterasEntrantes.get(indiceSemaforoVerde + 1).ponSemaforo(true);
			}
		}
		}


	@Override
	protected CarreteraEntranteConIntervalo creaCarreteraEntrante(Carretera carretera) {
		
		return new CarreteraEntranteConIntervalo(carretera,this.maxValorIntervalo);
	}
	
	 protected void completaDetallesSeccion(IniSection is) {
		  // genera la sección queues = (r2,green,[]),...
			 String s ="";
			 
			 for (CarreteraEntrante e : carreterasEntrantes) {
				s +=  "(" + e.carretera.id + ",";
						if(this.carreterasEntrantes.size() != 0)
				 s += colorsemaforo(e.semaforo);
						if (colorsemaforo(e.semaforo).equalsIgnoreCase("green"))
							s+= ":" + this.carreterasEntrantes.get(indiceSemaforoVerde).getUnidadesDeTiempoUsadas();
						
						
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
}
