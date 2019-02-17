package es.ucm.fdi.objetos;

import es.ucm.fdi.ini.IniSection;

public class CruceRotonda extends CruceGenerico<CarreteraEntrante>{

	public CruceRotonda(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CarreteraEntrante creaCarreteraEntrante(Carretera carretera) {
		// TODO Auto-generated method stub
		return new CarreteraEntrante(carretera);
	}

	@Override
	protected void actualizaSemaforos() {
		boolean encontrada= false;
		if(this.indiceSemaforoVerde == -1) {
			this.indiceSemaforoVerde = 0;
			this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(true);
		}

		else if(this.carreterasEntrantes.get(indiceSemaforoVerde).colaVehiculos.isEmpty()) {

			if (this.indiceSemaforoVerde + 1 <= super.carreterasEntrantes.size()){
				this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(false);
				//preguntar a ruby si deberia recorreme toda la lista o lo suyo seria desde la que tiene el semaforo en verde
				for (int i = this.indiceSemaforoVerde + 1 ; i < this.carreterasEntrantes.size() && !encontrada; i++) {
					if(!this.carreterasEntrantes.get(i).colaVehiculos.isEmpty()) {
						this.carreterasEntrantes.get(i).ponSemaforo(true);
						this.indiceSemaforoVerde = i;
						encontrada = true;
					}
				}
						for (int j = 0; j < this.indiceSemaforoVerde && !encontrada; j++) {
							
							if(!this.carreterasEntrantes.get(j).colaVehiculos.isEmpty()) {
								this.carreterasEntrantes.get(j).ponSemaforo(true);
								this.indiceSemaforoVerde = j;
								encontrada = true;
						
						
					}
				}
				
				if(!encontrada){
					
					this.carreterasEntrantes.get(indiceSemaforoVerde + 1).ponSemaforo(true);
					this.indiceSemaforoVerde++;
					}
				}
			else {
				this.carreterasEntrantes.get(indiceSemaforoVerde).ponSemaforo(false);
				this.carreterasEntrantes.get(0).ponSemaforo(true);
				this.indiceSemaforoVerde=0;
			}
			
			
		}			
	
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		// TODO Auto-generated method stub
		  // genera la sección queues = (r2,green,[]),...
		 String s ="";
		 
		 for (CarreteraEntrante e : carreterasEntrantes) {
			s +=  "(" + e.carretera.id + ",";
					if(this.carreterasEntrantes.size() != 0)
			 s += colorsemaforo(e.semaforo);
					
					
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
		 
		 is.setValue("type","ra");
		
	}

	@Override
	protected String getNombreSeccion() {
		// TODO Auto-generated method stub
		return null;
	}

}
