package es.ucm.fdi.objetos;

import es.ucm.fdi.ini.IniSection;

public class Autopista  extends Carretera {
	
	private int numCarriles;
	
	 public Autopista(String id, int longitud, int maxima, CruceGenerico<?> src, CruceGenerico<?> dest,int numCar) {
		super(id, longitud, maxima, src, dest);
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
			super.completaDetallesSeccion(is);
			is.setValue("lanes", this.numCarriles);
			is.setValue("type", "lanes");

	 }
}
