package es.ucm.fdi.objetos;

import es.ucm.fdi.ini.IniSection;

public class Autopista  extends Carretera {
	
	private int numCarriles;
	
	 public Autopista(String id, int longitud, int maxima, CruceGenerico<?> origen, CruceGenerico<?> destino,int numCar) {
		super(id, longitud, maxima, origen, destino);
		// TODO Auto-generated constructor stub
		this.numCarriles = numCar;
	}
	
	 @Override
	 protected int calculaVelocidadBase() {
		 return Math.min(this.velocidadMaxima,((this.velocidadMaxima * this.numCarriles)/(Math.max(this.vehiculos.size(),1))) +1);
	 }
	 @Override
	 protected int calculaFactorReduccion(int obstacles) {
	 return obstacles < this.numCarriles ? 1 : 2;
	 }
	 @Override
	 protected void completaDetallesSeccion(IniSection is) {
		 String s  = "";
		 		is.setValue("type","lanes");
			for (Vehiculo vehiculo : this.vehiculos) {
				//if(vehiculo.kilometraje != this.longitud)
				s += "(" + vehiculo.id + "," + vehiculo.localizacion+ ")" + ",";
			}
			if( s != "")
				s = s.substring(0,s.length() -1);
			is.setValue("state", s);;
		}

}
