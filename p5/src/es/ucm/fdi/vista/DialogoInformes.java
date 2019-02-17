package es.ucm.fdi.vista;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.ObservadorSimuladorTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Carretera;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.objetos.Vehiculo;

public class DialogoInformes extends JDialog implements ObservadorSimuladorTrafico {

	protected static final char TECLALIMPIAR = 'c';

	private PanelBotones panelBotones;
	private PanelObjSim<Vehiculo> panelVehiculos;
	private PanelObjSim<Carretera> panelCarreteras;
	private PanelObjSim<CruceGenerico<?>> panelCruces;
	
	public DialogoInformes(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		super();
		this.initGUI();
		controlador.addObserver(this);
	}


	private void initGUI() {
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		this.panelVehiculos = new PanelObjSim<Vehiculo>("Vehiculos");
		this.panelCarreteras = new PanelObjSim<Carretera>("Carreteras");
		this.panelCruces = new PanelObjSim<CruceGenerico<?>>("Cruces");
		this.panelBotones = new PanelBotones(this);
		InformationPanel panelInfo = new InformationPanel();
		//panelPrincipal.add(panelInfo,BorderLayout.PAGE_START);
		this.add(panelPrincipal);
		mostrar();
		
		}
	
	public void mostrar() { this.setVisible(true); }
	
	private void setMapa(MapaCarreteras mapa){
		
	
			this.panelVehiculos.setList(mapa.getVehiculos());
			this.panelCarreteras.setList(mapa.getCarreteras());
			this.panelCruces.setList(mapa.getCruces());
	}
	
	public List<Vehiculo> getVehiculosSeleccionados() {
		
		return this.panelVehiculos.getSelectedItems();
	}
	
	public List<Carretera> getCarreterasSeleccionadas() {
		return this.panelCarreteras.getSelectedItems();
	}	
	
	public List<CruceGenerico<?>> getCrucesSeleccionados() {
		return this.panelCruces.getSelectedItems();
	}
	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.setMapa(mapa);
		
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.setMapa(mapa);
		
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.setMapa(mapa);
		
	}

}
