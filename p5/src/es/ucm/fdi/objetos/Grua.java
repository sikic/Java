package es.ucm.fdi.objetos;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.ini.IniSection;

public class Grua extends Vehiculo {
	
	protected List<Vehiculo> estropeado;


	public Grua(String id, int velocidadMaxima, List<CruceGenerico<?>> iti) throws ExcepcionCreaccionObjeto {
		super(id, velocidadMaxima, iti);
		this.estropeado = new ArrayList<Vehiculo>();
	}
		
		
			
		@Override
		protected void completaDetallesSeccion(IniSection is) {
		String enGrua="";	
		is.setValue("speed", this.velocidadActual);
		is.setValue("kilometrage", this.kilometraje);
		is.setValue("faulty", this.tiempoAveria);
		is.setValue("location", this.haLlegado ? "arrived" :
		"(" + this.carretera.getId() + "," + this.getLocalizacion() + ")");
		for (int i = 0; i < this.estropeado.size(); i++) {
			enGrua +=this.estropeado.get(i).getId();
		}
		is.setValue("Dentro de la grua", enGrua);
		}
				

			@Override
			public void avanza() {
				// TODO Auto-generated method stub
				// si el coche está averiado, decrementar tiempoAveria
				// si el coche está esperando en un cruce, no se hace nada.
				// en otro caso:
				//1. Actualizar su “localizacion”
				//2. Actualizar su “kilometraje”
				//3. Si el coche ha llegado a un cruce (localizacion >= carretera.getLength())
				//3.1. Poner la localización igual a la longitud de la carretera.
				//3.2. Corregir el kilometraje.
				//3.3. Indicar a la carretera que el vehículo entra al cruce.(FALTA POR HACER)
				//3.4. Marcar que éste vehículo está en un cruce (this.estEnCruce = true)
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
					else { /*recorremos la lista de vehiculos y miramos si estamos en la localizacion de alguno de los 
						vehiculos, en cuyo caso , comprobamos si la averia es mayor que 0 y lo metemos en la lista de vehiculos
						de la grua para sacarlo de la carretera*/
						for (int i = 0; i < super.carretera.getVehiculos().size(); i++) {
							if(super.localizacion >= super.carretera.getVehiculos().get(i).localizacion) {
								if(super.carretera.getVehiculos().get(i).tiempoAveria > 0) {
									super.carretera.getVehiculos().get(i).setTiempoAveria(0);
									this.estropeado.add(super.carretera.getVehiculos().get(i));
									super.carretera.saleVehiculo(super.carretera.getVehiculos().get(i));
								}
							}
							
						}
						
						this.localizacion += this.velocidadActual;
						this.kilometraje += this.velocidadActual;
					}
				}
				
			}

		
}
