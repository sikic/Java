package es.ucm.fdi.modelos;

import java.util.List;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Carretera;

public class ModeloTablaCarreteras extends ModeloTabla<Carretera> {

	public ModeloTablaCarreteras(String[] columnIdEventos, Controlador ctrl) {
		super(columnIdEventos, ctrl);
		// TODO Auto-generated constructor stub
	}

	@Override // necesario para que se visualicen los datos
	public Object getValueAt(int indiceFil, int indiceCol) {
		
		Object s = null;
		switch (indiceCol) {
		
		case 0: s = this.lista.get(indiceFil).getId();break;
		case 1: s = this.lista.get(indiceFil).getCruceOrigen().getId();break;
		case 2: s = this.lista.get(indiceFil).getCruceDestino().getId();break;
		case 3: s = this.lista.get(indiceFil).getLongitud();break;
		case 4: s = this.lista.get(indiceFil).getVelocidadMaxima();break;
		case 5: s = this.lista.get(indiceFil).toString();break;
		default: assert (false);
		}
		return s;
	}
	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.lista = mapa.getCarreteras();
		this.fireTableStructureChanged();
		
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.lista = mapa.getCarreteras();
		this.fireTableStructureChanged();
		
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {

		if(lista != null) {
			this.lista.clear();
			this.fireTableStructureChanged();
		}
	}

	
}
