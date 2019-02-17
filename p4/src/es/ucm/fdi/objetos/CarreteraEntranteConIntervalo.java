package es.ucm.fdi.objetos;

import es.ucm.fdi.excepciones.ErrorCarretera;

public class CarreteraEntranteConIntervalo extends CarreteraEntrante{
	
	private int intervaloDeTiempo; // Tiempo que ha de transcurrir para poner
	// el sem�foro de la carretera en rojo
	private int unidadesDeTiempoUsadas; // Se incrementa cada vez que
	// avanza un veh�culo
	private boolean usoCompleto; // Controla que en cada paso con el sem�foro
	// en verde, ha pasado un veh�culo
	private boolean usadaPorUnVehiculo; // Controla que al menos ha pasado un
	// veh�culo mientras el sem�foro estaba
	// en verde.
	
	protected CarreteraEntranteConIntervalo(Carretera carretera, int intervalTiempo) {
		super(carretera);
		this.intervaloDeTiempo = intervalTiempo;
		this.unidadesDeTiempoUsadas = 0;
		}
		@Override
		protected void avanzaPrimerVehiculo() throws ErrorCarretera {
		// Incrementa unidadesDeTiempoUsadas
		this.unidadesDeTiempoUsadas++;	
		// Actualiza usoCompleto:
		// - Si �colaVehiculos� es vac�a, entonces �usoCompleto=false�
		// - En otro caso saca el primer veh�culo �v� de la �colaVehiculos�,
		// y le mueve a la siguiente carretera (�v.moverASiguienteCarretera()�)
		// Pone �usadaPorUnVehiculo� a true.
		if(this.colaVehiculos.isEmpty())
			this.usoCompleto = false;
		else {
			Vehiculo v = this.colaVehiculos.get(0);
			v.moverASiguienteCarretera();
			this.usadaPorUnVehiculo = true;
		}
		}
		public boolean tiempoConsumido() {
		// comprueba si se ha agotado el intervalo de tiempo.
		// �unidadesDeTiempoUsadas >= �intervaloDeTiempo�
			return this.unidadesDeTiempoUsadas >= this.intervaloDeTiempo;
		}
		public boolean usoCompleto() {
			return this.usoCompleto;
		} // m�todo get
		public boolean usada() {
			return this.usadaPorUnVehiculo;
		} // m�todo get
	
		public void setIntervalo(int x) {
			this.intervaloDeTiempo = x;
		}
		public int getIntervalo() {
			return this.intervaloDeTiempo;
		}
		public int getUnidadesDeTiempoUsadas() {
			return unidadesDeTiempoUsadas;
		}
		public void setUnidadesDeTiempoUsadas(int unidadesDeTiempoUsadas) {
			this.unidadesDeTiempoUsadas = unidadesDeTiempoUsadas;
		}
		public void setUsoCompleto(boolean usoCompleto) {
			this.usoCompleto = usoCompleto;
		}
		public void setUsadaPorUnVehiculo(boolean usadaPorUnVehiculo) {
			this.usadaPorUnVehiculo = usadaPorUnVehiculo;
		}
		
}
