package es.ucm.fdi.modelos;

import java.util.List;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;

public class ModeloTablaEventos extends ModeloTabla<Evento>{

	public ModeloTablaEventos(String[] columnideventos, Controlador controlador) {
		super(columnideventos,controlador);
	}

	@Override // necesario para que se visualicen los datos
	public Object getValueAt(int indiceFil, int indiceCol) {
		
		Object s = null;
		switch (indiceCol) {
		
		case 0: s = indiceFil;break;
		case 1: s = this.lista.get(indiceFil).getTiempo();break;
		case 2: s = this.lista.get(indiceFil).toString();break;
		default: assert (false);
		}
		return s;
	}
	
	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		
		
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.lista = eventos;
		this.fireTableStructureChanged();
		
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.lista = eventos;
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
