package es.ucm.fdi.vista;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.ObservadorSimuladorTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;

public class PanelBarraEstado extends JPanel implements ObservadorSimuladorTrafico{

	private JLabel infoEjecucion;
	
	public PanelBarraEstado(String mensaje, Controlador controlador){
		
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.infoEjecucion = new JLabel(mensaje);
			this.add(this.infoEjecucion);
			this.setBorder(BorderFactory.createBevelBorder(1));
			controlador.addObserver(this);
		}
	
		public void setMensaje(String mensaje) { this.infoEjecucion.setText(mensaje);} // la ventana principal se
		 // comunica con el panel
		@Override
		public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos){
			
			this.infoEjecucion.setText("Paso: " + tiempo + " del Simulador");
		}
		
		@Override
		public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos){
			
		this.infoEjecucion.setText("Evento añadido al simulador");
		
		}
		
		@Override
		public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
			
		}

		@Override
		public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
			this.infoEjecucion.setText("Lamentablemente ha ocurrido un error");
		}
		
		
}
