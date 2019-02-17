package es.ucm.fdi.objetos;

import java.util.List;

import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.ini.IniSection;

public class Bicicleta extends Vehiculo{
	
	public Bicicleta(String id, int velocidadMaxima, List<CruceGenerico<?>> iti) throws ExcepcionCreaccionObjeto {
		super(id,velocidadMaxima,iti);
		
		}
	@Override
	public void setTiempoAveria(Integer duracionAveria) {
		// Comprobar que “carretera” no es null.
		// Se fija el tiempo de avería de acuerdo con el enunciado.
		// Si el tiempo de avería es finalmente positivo, entonces
		// la “velocidadActual” se pone a 0
			if(this.carretera != null) {
				if(this.velocidadActual >= this.velocidadMaxima / 2)
				this.tiempoAveria = duracionAveria;
				if(this.tiempoAveria >0) {
					this.velocidadActual = 0;
				}
			}
		}
	@Override
	protected void completaDetallesSeccion(IniSection is) {
		is.setValue("type", "bike");
		is.setValue("speed", this.velocidadActual);
		is.setValue("kilometrage", this.kilometraje);
		is.setValue("faulty", this.tiempoAveria);
		is.setValue("location", this.haLlegado ? "arrived" :
		"(" + this.carretera.id + "," + this.getLocalizacion() + ")");
	}
}
