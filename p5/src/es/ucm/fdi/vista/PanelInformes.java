package es.ucm.fdi.vista;

import java.util.List;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.ObservadorSimuladorTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;

public class PanelInformes extends PanelAreaTexto implements ObservadorSimuladorTrafico{

	String texto;
	
	public PanelInformes(String titulo, boolean b, Controlador controlador) {
		super(titulo, b);
		this.texto = "";
		controlador.addObserver(this);
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		
		//this.errorSimulador(tiempo, map, eventos, e);
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		
		texto += mapa.generateReport(tiempo);
		this.areatexto.setText(texto);
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		
		
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos){
		//this.areatexto.setText("");
		
	}


}
