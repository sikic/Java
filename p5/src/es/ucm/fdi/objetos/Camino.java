package es.ucm.fdi.objetos;

import es.ucm.fdi.ini.IniSection;

public class Camino extends Carretera {
	 public Camino(String id, int longitud, int velocidadMaxima,CruceGenerico<?> cruceOrigen,CruceGenerico<?> cruceDestino) {
			 super(id, longitud, velocidadMaxima, cruceOrigen, cruceDestino);
			 }
			 @Override
			 protected int calculaVelocidadBase() {
				 
				 return this.velocidadMaxima; }
			 @Override
			 protected int calculaFactorReduccion(int obstacles) {
			 return obstacles + 1;
			 }
			 
			
			 @Override
			 protected void completaDetallesSeccion(IniSection is) {
				 String s  = "";
				 		is.setValue("type", "dirt");
					for (Vehiculo vehiculo : this.vehiculos) {
						//if(vehiculo.kilometraje != this.longitud)
						s += "(" + vehiculo.id + "," + vehiculo.localizacion+ ")" + ",";
					}
					if( s != "")
						s = s.substring(0,s.length() -1);
					is.setValue("state", s);;
				}
}
