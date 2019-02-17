package es.ucm.fdi.objetos;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.ObservadorSimuladorTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;

public class FactorMasificacion implements ObservadorSimuladorTrafico{
	private ArrayList<String> carretera_id;
	private ArrayList<Integer> vehiculos;
	private Controlador ctrl;
	
	public  FactorMasificacion(Controlador x){
		this.carretera_id = new ArrayList<String>();
		this.vehiculos = new ArrayList<Integer>();
		this.ctrl=x;
		this.ctrl.addObserver(this);
		
	}
	
	
	public ArrayList<String> getCarretera_id() {
		return carretera_id;
	}


	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		
		for (int i = 0; i < mapa.getCarreteras().size(); i++){
			if(this.carretera_id.size() != mapa.getCarreteras().size()) {
				this.carretera_id.add(mapa.getCarreteras().get(i).getId());
				this.vehiculos.add(0);
			}
			
			if(mapa.getCarreteras().get(i).getVehiculos().size() > this.vehiculos.get(i)){
				this.vehiculos.set(i,mapa.getCarreteras().get(i).getVehiculos().size());
			}
				
		}
		
	}

	public ArrayList<Integer> getVehiculos() {
		return vehiculos;
	}


	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		
	}

}
