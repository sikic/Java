package es.ucm.fdi.objetos;

import java.util.*;

import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.ini.IniSection;

public class Vehiculo extends ObjetoSimulacion {
	
	protected Carretera carretera; // carretera en la que est� el veh�culo
	protected int velocidadMaxima; // velocidad m�xima
	protected int velocidadActual; // velocidad actual
	protected int kilometraje; // distancia recorrida
	protected int localizacion; // localizaci�n en la carretera
	protected int tiempoAveria; // tiempo que estar� averiado
	protected List<CruceGenerico<?>> itinerario; // itinerario a recorrer (m�nimo 2)
	protected boolean haLlegado;
	protected boolean estEnCruce;
	protected int contadorIti;
	
	public Vehiculo(String id, int velocidadMaxima, List<CruceGenerico<?>> iti) throws ExcepcionCreaccionObjeto {
		super(id);
		if (velocidadMaxima >= 0 && iti.size()> 1) {
			this.velocidadMaxima = velocidadMaxima;
			this.itinerario = iti;
			this.velocidadActual = 0;
			this.carretera= null;
			this.localizacion= 0;
			this.tiempoAveria = 0;
			this.haLlegado = false;
			this.estEnCruce = false;
			this.contadorIti = 0;
		}
		else throw new ExcepcionCreaccionObjeto("Parametros del vehiculo no validos.");
	}
	
	public int getLocalizacion() {
		
		return this.localizacion;
	}
	public int getTiempoDeInfraccion() {
		return this.tiempoAveria;
	}
	public void setVelocidadActual(int velocidad) {
		// Si �velocidad� es negativa, entonces la �velocidadActual� es 0.
		// Si �velocidad� excede a �velocidadMaxima�, entonces la
		// �velocidadActual� es �velocidadMaxima�
		// En otro caso, �velocidadActual� es �velocidad�
		if(velocidad < 0) this.velocidadActual =0;
		else if(velocidad > this.velocidadMaxima) this.velocidadActual = this.velocidadMaxima;
		else this.velocidadActual=velocidad;
		}
		public void setTiempoAveria(Integer duracionAveria) {
		// Comprobar que �carretera� no es null.
		// Se fija el tiempo de aver�a de acuerdo con el enunciado.
		// Si el tiempo de aver�a es finalmente positivo, entonces
		// la �velocidadActual� se pone a 0
			if(this.carretera != null) {
				this.tiempoAveria = duracionAveria;
				if(this.tiempoAveria >0) {
					this.velocidadActual = 0;
				}
			}
		}
		@Override
		protected void completaDetallesSeccion(IniSection is) {
			is.setValue("speed", this.velocidadActual);
			is.setValue("kilometrage", this.kilometraje);
			is.setValue("faulty", this.tiempoAveria);
			is.setValue("location", this.haLlegado ? "arrived" :
			"(" + this.carretera.id + "," + this.getLocalizacion() + ")");
				}
		

	public void moverASiguienteCarretera() throws ErrorCarretera {
		// TODO Auto-generated method stub
		// Si la carretera no es null, sacar el veh�culo de la carretera.
		// Si hemos llegado al �ltimo cruce del itinerario, entonces:
		//1. Se marca que el veh�culo ha llegado (this.haLlegado = true).
		//2. Se pone su carretera a null.
		//3. Se pone su �velocidadActual� y �localizacion� a 0.
		// En otro caso:
		//1. Se calcula la siguiente carretera a la que tiene que ir.
		//2. Si dicha carretera no existe, se lanza excepci�n.
		//3. En otro caso, se introduce el veh�culo en la carretera.
		//4. Se inicializa su localizaci�n.
		// marcamos que el veh�culo no est� en un cruce (estaEnUnCruce = false).
		if( this.carretera != null) {
			this.carretera.saleVehiculo(this);
		}
		if(this.itinerario.size() -1 == this.contadorIti) {
				this.haLlegado = true;
				this.carretera = null;
				this.localizacion = 0;
				this.velocidadActual = 0;
				
			}
		else {
				this.carretera = this.itinerario.get(this.contadorIti).carreteraHaciaCruce(this.itinerario.get(this.contadorIti+1));
				if (this.carretera == null) {
					throw  new ErrorCarretera("Carretera Inexistente");
				}
				this.carretera.entraVehiculo(this);
				this.localizacion = 0;
				this.velocidadActual = 0;
				this.estEnCruce = false;
			}
		
		
	}

	@Override
	protected String getNombreSeccion() {
		return "vehicle_report";
	}
	

	@Override
	public void avanza() {
		// TODO Auto-generated method stub
		// si el coche est� averiado, decrementar tiempoAveria
		// si el coche est� esperando en un cruce, no se hace nada.
		// en otro caso:
		//1. Actualizar su �localizacion�
		//2. Actualizar su �kilometraje�
		//3. Si el coche ha llegado a un cruce (localizacion >= carretera.getLength())
		//3.1. Poner la localizaci�n igual a la longitud de la carretera.
		//3.2. Corregir el kilometraje.
		//3.3. Indicar a la carretera que el veh�culo entra al cruce.(FALTA POR HACER)
		//3.4. Marcar que �ste veh�culo est� en un cruce (this.estEnCruce = true)
		if(this.tiempoAveria > 0) this.tiempoAveria--;
		if (!this.estEnCruce) {
			if(this.localizacion + this.velocidadActual >= this.carretera.getLongitud()) {
				int kilo = this.carretera.getLongitud() - this.localizacion;
				this.localizacion = this.carretera.getLongitud();
				this.kilometraje += kilo;
				this.estEnCruce = true;
				this.contadorIti++;
				this.velocidadActual = 0;
				this.carretera.entraVehiculoAlCruce(this);
			}
			else {
				this.localizacion += this.velocidadActual;
				this.kilometraje += this.velocidadActual;
			}
		}
		
	}
	
}
