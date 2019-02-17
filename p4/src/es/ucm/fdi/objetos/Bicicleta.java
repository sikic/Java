package es.ucm.fdi.objetos;

import java.util.List;

import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.ini.IniSection;

public class Bicicleta extends Vehiculo{

	public Bicicleta(String id, int velocidadMaxima, List<CruceGenerico<?>> iti) throws ExcepcionCreaccionObjeto {
		super(id, velocidadMaxima, iti);
		
	}

	@Override
	public void setTiempoAveria(Integer duracionAveria) {
		if(this.velocidadActual >= this.velocidadMaxima/2) {
			if(this.carretera != null) {
				this.tiempoAveria = duracionAveria;
				if(this.tiempoAveria >0) {
					this.velocidadActual = 0;
				}
			}
		}
}
	
	protected void completaDetallesSeccion(IniSection is) {
		is.setValue("type", "bike");
		is.setValue("speed", this.velocidadActual);
		is.setValue("kilometrage", this.kilometraje);
		is.setValue("faulty", this.tiempoAveria);
		is.setValue("location", this.haLlegado ? "arrived" :
		"(" + this.carretera.id + "," + this.getLocalizacion() + ")");
	}
}
