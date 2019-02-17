package es.ucm.fdi.objetos;


import es.ucm.fdi.ini.IniSection;

public class CruceCongestionado  extends CruceGenerico<CarreteraEntranteConIntervalo> {

	public CruceCongestionado(String id) {
		super(id);
		
	}

	@Override
	protected CarreteraEntranteConIntervalo creaCarreteraEntrante(Carretera carretera) {
		// TODO Auto-generated method stub
		return new CarreteraEntranteConIntervalo(carretera,0);
	}

	@Override
	protected void actualizaSemaforos() {
		// TODO Auto-generated method stub
		//- Si no hay carretera con sem�foro en verde (indiceSemaforoVerde == -1) entonces se
		// selecciona la carretera que tenga m�s veh�culos en su cola de veh�culos.
		 //- Si hay carretera entrante "ri" con su sem�foro en verde, (indiceSemaforoVerde != -1) entonces:
		// 1. Si ha consumido su intervalo de tiempo en verde ("ri.tiempoConsumido()"):
		 //1.1. Se pone el sem�foro de "ri" a rojo.
		// 1.2. Se inicializan los atributos de "ri".
		// 1.3. Se busca la posici�n "max" que ocupa la primera carretera entrante
		// distinta de "ri" con el mayor n�mero de veh�culos en su cola.
		// 1.4. "indiceSemaforoVerde" se pone a "max".
		// 1.5. Se pone el sem�foro de la carretera entrante en la posici�n "max" ("rj")
		// a verde y se inicializan los atributos de "rj", entre ellos el
		 //"intervaloTiempo" a Math.max(rj.numVehiculosEnCola()/2,1).
		
		if(this.indiceSemaforoVerde == -1) {
			CarreteraEntranteConIntervalo c1 = this.carreterasEntrantes.get(0);
			this.indiceSemaforoVerde = 0;
			for (int i = 1; i < this.carreterasEntrantes.size(); i++) {
				if(c1.colaVehiculos.size() < this.carreterasEntrantes.get(i).colaVehiculos.size()) {
					c1 = this.carreterasEntrantes.get(i);
					this.indiceSemaforoVerde = i;}
			}// sale con la carretera que mas cola tiene
			
			c1.ponSemaforo(true);
			c1.setIntervaloDeTiempo(Math.max(c1.colaVehiculos.size()/2,1)); 
			
			
			
		}
		else if(this.carreterasEntrantes.get(indiceSemaforoVerde).tiempoConsumido()){
			int j = 0,i = 0;
			this.carreterasEntrantes.get(this.indiceSemaforoVerde).setUnidadesDeTiempoUsadas(0);
			this.carreterasEntrantes.get(this.indiceSemaforoVerde).setUsadaPorUnVehiculo(false);
			this.carreterasEntrantes.get(this.indiceSemaforoVerde).setUsoCompleto(false);
			this.carreterasEntrantes.get(this.indiceSemaforoVerde).setIntervaloDeTiempo(0);
			this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(false);
			i = this.indiceSemaforoVerde;
			if( this.carreterasEntrantes.size() == 2) {
				if (i == 0) j = 1;
				else j= 0;
			}
			else if (this.carreterasEntrantes.size()==1) j = 0;
			else {
			if (i == 0) j = 1;
			for(int x = 1;x <this.carreterasEntrantes.size();x++) {
				if(this.carreterasEntrantes.get(j).colaVehiculos.size()< this.carreterasEntrantes.get(x).colaVehiculos.size() && x!= i)
					j= x;
			}
			}
			this.indiceSemaforoVerde = j;
			this.carreterasEntrantes.get(j).ponSemaforo(true);
			this.carreterasEntrantes.get(j).setUnidadesDeTiempoUsadas(0);
			this.carreterasEntrantes.get(j).setUsadaPorUnVehiculo(false);
			this.carreterasEntrantes.get(j).setUsoCompleto(false);
			this.carreterasEntrantes.get(j).setIntervaloDeTiempo(Math.max(this.carreterasEntrantes.get(j).colaVehiculos.size()/2,1));
			
			}
		
		
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		// TODO Auto-generated method stub
		  // genera la secci�n queues = (r2,green,[]),...
		 String s ="";
		 
		 for (CarreteraEntrante e : carreterasEntrantes) {
			s +=  "(" + e.carretera.id + ",";
					if(this.carreterasEntrantes.size() != 0)
			 s += colorsemaforo(e.semaforo);
					if (colorsemaforo(e.semaforo).equals("green"))
						s+= ":" + this.carreterasEntrantes.get(indiceSemaforoVerde).tiempoRestante();		
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
		 is.setValue("type","mc");
	 }
	
	
	@Override
	protected String getNombreSeccion() {
		// TODO Auto-generated method stub
		return "junction_report";
	}

}
