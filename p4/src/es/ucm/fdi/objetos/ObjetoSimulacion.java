package es.ucm.fdi.objetos;

import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.ini.IniSection;

public abstract class ObjetoSimulacion{
	
		protected String id;
		
		public ObjetoSimulacion(String id){
			this.id= id;
		}
			public String getId(){
			return id;	
			}
			
			
			public  String generaInforme(int tiempo) {
				
				IniSection is = new IniSection(this.getNombreSeccion());
				is.setValue("id", this.id);
				is.setValue("time", tiempo);
				this.completaDetallesSeccion(is);
				return is.toString();
				
			}
			public abstract void avanza() throws ErrorCarretera;
			protected abstract void completaDetallesSeccion(IniSection is);
				
			protected abstract String getNombreSeccion();


}
