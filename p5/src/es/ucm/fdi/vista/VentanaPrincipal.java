package es.ucm.fdi.vista;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.ObservadorSimuladorTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.modelos.ModeloTablaCarreteras;
import es.ucm.fdi.modelos.ModeloTablaCruces;
import es.ucm.fdi.modelos.ModeloTablaEventos;
import es.ucm.fdi.modelos.ModeloTablaVehiculos;
import es.ucm.fdi.objetos.Carretera;
import es.ucm.fdi.objetos.CruceGenerico;
import es.ucm.fdi.objetos.Vehiculo;


public class VentanaPrincipal  extends JFrame implements ObservadorSimuladorTrafico {
	
	
		public static Border bordePorDefecto = BorderFactory.createLineBorder(Color.black, 2);
		
		// SUPERIOR PANEL
		static private final String[] columnIdEventos = { "#", "Tiempo", "Tipo" };
		private PanelAreaTexto panelEditorEventos;
		private PanelAreaTexto panelInformes;
		private PanelTabla<Evento> panelColaEventos;
		
		// MENU AND TOOL BAR
		private JFileChooser fc;
		private ToolBar toolbar;
		
		public ToolBar getToolbar() {
			return toolbar;
		}

		// GRAPHIC PANEL
		private ComponenteMapa componenteMapa;
		
		// STATUS BAR (INFO AT THE BOTTOM OF THE WINDOW)
		private PanelBarraEstado panelBarraEstado;
		
		// INFERIOR PANEL
		static private final String[] columnIdVehiculo = { "ID", "Carretera",
				"Localizacion", "Vel.", "Km", "Tiempo. Ave.", "Itinerario" };
		static private final String[] columnIdCarretera = { "ID", "Origen",
				"Destino", "Longitud", "Vel. Max", "Vehiculos" };
		static private final String[] columnIdCruce = { "ID", "Verde", "Rojo" };
		
		
		private PanelTabla<Vehiculo> panelVehiculos;
		private PanelTabla<Carretera> panelCarreteras;
		private PanelTabla<CruceGenerico<?>> panelCruces;
		
		// REPORT DIALOG
		private DialogoInformes dialogoInformes; // opcional			// MODEL PART - VIEW CONTROLLER MODEL
		private File ficheroActual;
		private Controlador controlador;

		public VentanaPrincipal(String ficheroEntrada,Controlador ctrl) throws FileNotFoundException{
				super("Simulador de Trafico");
				super.setIconImage(new ImageIcon("resources/icons/clear.png").getImage());
				this.controlador = ctrl;
				this.ficheroActual = ficheroEntrada != null ? new File(ficheroEntrada) : null;
				this.initGUI();
				// añadimos la ventana principal como observadora
				ctrl.addObserver(this);
		}
			
		private void initGUI() throws FileNotFoundException{
				
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			JFrame.setDefaultLookAndFeelDecorated(true);	
			this.addWindowListener(new WindowListener() {
				@Override
			public void windowActivated(WindowEvent e) {
					
					
			}

			@Override
			public void windowClosed(WindowEvent e) {
					
					
			}
				@Override
			public void windowClosing(WindowEvent e) {
					
				
				if(JOptionPane.showConfirmDialog(rootPane, "¿Realmente deseas salir?","Salir", 
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					
				System.exit(0);
					// al salir pide confirmación
			}

				@Override
			public void windowDeactivated(WindowEvent e){
					
					
			}

				@Override
			public void windowDeiconified(WindowEvent e) {
					
					
				}

			@Override
			public void windowIconified(WindowEvent e) {
					
			}

			@Override
			public void windowOpened(WindowEvent e) {
				
					
			}
			
			});
			
			
			//PANEL PRINCIPAL
			JPanel panelPrincipal = this.creaPanelPrincipal();
			this.setContentPane(panelPrincipal);
			
			
			// BARRA DE ESTADO INFERIOR
				// (contiene una JLabel para mostrar el estado delsimulador)
			this.addBarraEstado(panelPrincipal);
			
			// PANEL QUE CONTIENE EL RESTO DE COMPONENTES
			// (Lo dividimos en dos paneles (superior e inferior)
			JPanel panelCentral = this.createPanelCentral();
			panelPrincipal.add(panelCentral,BorderLayout.CENTER);
			
			// PANEL SUPERIOR
			this.createPanelSuperior(panelCentral);
			
			// PANEL INFERIOR
			this.createPanelInferior(panelCentral);
			
			// MENU
			BarraMenu menubar = new BarraMenu(this,this.controlador);
			this.setJMenuBar(menubar);	
				
		
			
			// BARRA DE HERRAMIENTAS
			this.addToolBar(panelPrincipal);	
			
			// FILE CHOOSER
			this.fc = new JFileChooser();
			
			// REPORT DIALOG (OPCIONAL)
			this.dialogoInformes = new DialogoInformes(this,this.controlador);
			this.pack();
			this.setVisible(true);
			
			}

			
		
			private void createPanelInferior(JPanel panelCentral) {
				
			JPanel inferior = new JPanel();
			inferior.setLayout(new BoxLayout(inferior,BoxLayout.LINE_AXIS));
			
			JPanel subpanel = new JPanel();
			subpanel.setLayout(new GridLayout(3,1));
			
			
			this.panelVehiculos = new PanelTabla<Vehiculo>("Vehiculos",
					new ModeloTablaVehiculos(VentanaPrincipal.columnIdVehiculo,this.controlador));
			
			this.panelCarreteras = new PanelTabla<Carretera>("Carretras",
					new ModeloTablaCarreteras(VentanaPrincipal.columnIdCarretera,this.controlador));
			
			this.panelCruces = new PanelTabla<CruceGenerico<?>>("Cruces",
					new ModeloTablaCruces(VentanaPrincipal.columnIdCruce,this.controlador));
			
			this.componenteMapa = new ComponenteMapa(this.controlador);
			JButton luis =new JButton("hola");
			luis.setPreferredSize(new Dimension(50,50));
			subpanel.add(luis);
			subpanel.add(panelVehiculos);
			subpanel.add(panelCarreteras);
			subpanel.add(panelCruces);
			inferior.add(subpanel);
			
			
			
			
			// añadir un ScroolPane al panel inferior donde se coloca la
			// componente.
			inferior.add(new JScrollPane(componenteMapa));
			panelCentral.add(inferior);
			
		}

			private void addToolBar(JPanel panelPrincipal) {
				
				this.toolbar = new ToolBar(this, this.controlador);
				this.toolbar.setFloatable(false);getContentPane();
				panelPrincipal.add(this.toolbar, BorderLayout.PAGE_START);
			
		}

			private void createPanelSuperior(JPanel panelCentral) throws FileNotFoundException {
				String texto = "";
				JPanel superior = new JPanel();
				superior.setLayout(new BoxLayout(superior,BoxLayout.LINE_AXIS));
				try {
					
					texto = this.leeFichero(this.ficheroActual);
					
				} catch (IOException e) {
					
				this.ficheroActual = null;
				this.muestraDialogoError("Error durante la lectura del fichero: " + e.getMessage());
				
				}
			
				this.panelEditorEventos = new PanelEditorEventos("Eventos",texto,true,this);
				this.panelEditorEventos.setTexto(texto);
				
				this.panelColaEventos = new PanelTabla<Evento>("Cola Eventos: ",
						new ModeloTablaEventos(VentanaPrincipal.columnIdEventos,
						this.controlador));
				
				this.panelInformes = new PanelInformes("Informes: ", false , this.controlador);
				
				
				
				superior.add(panelEditorEventos);
				superior.add(panelColaEventos);
				superior.add(panelInformes);
				
				panelCentral.add(superior);
				
				
				
			}
			
			private String leeFichero(File fichero) throws IOException{
				
				String s ="";
				int c;
				BufferedReader lectura = new BufferedReader(new FileReader(fichero));
				c = lectura.read();
				
				
				while(c != -1) {
					s += (char)c;
					c= lectura.read();
				}
			
				lectura.close();
				return s;
			}

			public void muestraDialogoError(String string) {
				if(JOptionPane.showConfirmDialog(rootPane, string,"error",JOptionPane.CLOSED_OPTION) == JOptionPane.OK_OPTION);
				controlador.reinicia();
			}

			private JPanel creaPanelPrincipal() {
				JPanel panelPrincipal = new JPanel();
				// para colocar el panel superior e inferior
				panelPrincipal.setLayout(new BorderLayout());
				return panelPrincipal;
			
			}
			private void addBarraEstado(JPanel panelPrincipal) {
				this.panelBarraEstado = new PanelBarraEstado("Bienvenido al simulador !",this.controlador);
						// se añade al panel principal (el que contiene al panel
						// superior y al inferior)
						panelPrincipal.add(this.panelBarraEstado,BorderLayout.PAGE_END);
				
			}
			
			private JPanel createPanelCentral() {
				JPanel panelCentral = new JPanel();
				// para colocar el panel superior e inferior
				panelCentral.setLayout(new GridLayout(2,1));
				return panelCentral;
				}
			
			@Override
			public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
			
				this.muestraDialogoError(e.getMessage());
			}
			@Override
			public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
				
				
			}
			@Override
			public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
				
				
			}
			@Override
			public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
			
				
				this.panelInformes.setTexto("");
				
			}

			public void cargaFichero() throws FileNotFoundException {
				int returnVal = this.fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				File fichero = this.fc.getSelectedFile();
				try {
					String s = leeFichero(fichero);
					
					this.ficheroActual = fichero;
					this.panelEditorEventos.setTexto(s);
					this.panelEditorEventos.setBorde(this.ficheroActual.getName());
					this.panelBarraEstado.setMensaje("Fichero " + fichero.getName() +
					" de eventos cargado into the editor");
				}
				catch (IOException e) {
				this.muestraDialogoError("Error durante la lectura del fichero: " +
				e.getMessage());
				}
				}	
			}

			public String getTextoEditorEventos() {
				
				return this.panelEditorEventos.getTexto();
			}

			public void setMensaje(String string) {
				this.setTitle(string);
				
			}

			public void generaInformes() {
			this.add(this.dialogoInformes);
				
				
			}

			public void guardar() {
				int returnVal = this.fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				File fichero = this.fc.getSelectedFile();
				try {
					
					BufferedWriter save = new BufferedWriter(new FileWriter(fichero));
					System.out.print(this.panelEditorEventos.getTexto());
					save.write(this.panelEditorEventos.getTexto());
					save.close();
					
				}
				catch (IOException e) {
				this.muestraDialogoError("Error durante la lectura del fichero: " +
				e.getMessage());
				}
				}	
				
			}

			public void clear() {
				
			this.panelEditorEventos.limpiar();
				
			}

			public int getSteps(Object e) {
				
				return (int)e;
			}

			public void inserta(String string) {
				
				this.panelEditorEventos.setTexto(this.panelEditorEventos.getTexto() + string);
				
			}

			
			
}

