package es.ucm.fdi.objetos;

public class CruceCongestionado extends CruceGenerico<CarreteraEntranteConIntervalo> {

	public CruceCongestionado(String id) {
		super(id);
		this.indiceSemaforoVerde = 0;
	
	}
	@Override
	protected void actualizaSemaforos() {
		CarreteraEntranteConIntervalo c1 = this.carreterasEntrantes.get(0);
		if(this.carreterasEntrantes.get(indiceSemaforoVerde).semaforo == false) {
			for (int i = 1; i < this.carreterasEntrantes.size(); i++) {
				if(c1.colaVehiculos.size() < this.carreterasEntrantes.get(i).colaVehiculos.size())
					c1 = this.carreterasEntrantes.get(i);
			}// sale con la carretera que mas cola tiene
			c1.ponSemaforo(true);
		}
		else if(this.carreterasEntrantes.get(indiceSemaforoVerde).tiempoConsumido()){
			int j = 0;
			this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(false);
			this.carreterasEntrantes.get(indiceSemaforoVerde).setUnidadesDeTiempoUsadas(0);
			this.carreterasEntrantes.get(indiceSemaforoVerde).setUsadaPorUnVehiculo(false);
			this.carreterasEntrantes.get(indiceSemaforoVerde).setUsoCompleto(false);
			while(this.carreterasEntrantes.get(indiceSemaforoVerde).colaVehiculos.size() > this.carreterasEntrantes.get(j).colaVehiculos.size())
				j++;
			this.indiceSemaforoVerde = j;
			this.carreterasEntrantes.get(j).ponSemaforo(true);
			this.carreterasEntrantes.get(j).setIntervalo(Math.max(this.carreterasEntrantes.get(j).colaVehiculos.size()/2,1));
		
		}
			
		
	}
	
	@Override
	protected CarreteraEntranteConIntervalo creaCarreteraEntrante(Carretera carretera) {
		
		return new CarreteraEntranteConIntervalo(carretera,0);
	}

}
