package es.ucm.fdi.vista;


import java.awt.Dimension;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.ObservadorSimuladorTrafico;
import es.ucm.fdi.eventos.Evento;
import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.model.MapaCarreteras;

public class ToolBar extends JToolBar implements ObservadorSimuladorTrafico{

	private JSpinner steps;
	private JTextField time;
	private int pasos;
	
	public ToolBar(VentanaPrincipal ventanaPrincipal, Controlador controlador){
		super();
		controlador.addObserver(this);
	
		//BOTON PARA CARGAR
		JButton botonCargar = new JButton();
		botonCargar.setToolTipText("Carga un fichero de ventos");
		botonCargar.setIcon(new ImageIcon(Utils.loadImage("resources/icons/open.png")));
		
		botonCargar.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
			controlador.reinicia();
			ventanaPrincipal.cargaFichero();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		});
		this.add(botonCargar);
		
		
		//BOTON GUARDAR
		JButton botonGuardar = new JButton();
		botonGuardar.setToolTipText("Guarda un fichero de ventos");
		botonGuardar.setIcon(new ImageIcon(Utils.loadImage("resources/icons/save.png")));
		
		botonGuardar.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		ventanaPrincipal.guardar();
		}
		});
		
		this.add(botonGuardar);
		
		//BOTON LIMPIAR
		JButton botonLimpiar = new JButton();
		botonLimpiar.setToolTipText("Limpia la ventana");
		botonLimpiar.setIcon(new ImageIcon(Utils.loadImage("resources/icons/clear.png")));
				
		botonLimpiar.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			ventanaPrincipal.clear();
		}
		});
		this.add(botonLimpiar);
		this.addSeparator(new Dimension(5,1));
		
		//BOTON CHECK-IN
		JButton botonCheckIn = new JButton("hola denis");
		botonCheckIn.setBounds(110,100, 500, 500);
		botonCheckIn.setToolTipText("Carga los eventos al simulador");
		botonCheckIn.setIcon(new ImageIcon(Utils.loadImage("resources/icons/47417.jpg").getScaledInstance(50,50, java.awt.Image.SCALE_DEFAULT)));
		
		botonCheckIn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
			controlador.reinicia();
			byte[] contenido = ventanaPrincipal.getTextoEditorEventos().getBytes();
			controlador.cargaEventos(new ByteArrayInputStream(contenido));
			
			
		} catch (Exception c) { 
		
			ventanaPrincipal.muestraDialogoError("Se ha producido un error");
		}
		
			ventanaPrincipal.setMensaje("Eventos cargados al simulador!");
		}
		});
		this.add(botonCheckIn);
		
		
		
		//BOTON PLAY
		JButton botonplay = new JButton();
		botonplay.setToolTipText("Ejecuta el simulador");
		botonplay.setIcon(new ImageIcon("Users/LuisAntonioROJASRAMÍ/Pictures/Wallpapers/4919.jpg"));
		botonplay.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			/*t1 = new hebra((int)steps.getValue(),controlador,(int)delay.getValue());
			
			*/
			try {
				controlador.ejecuta(pasos);
			} catch (ErrorDeSimulacion | EjecutaException | ErrorEventoAñadido | ErrorBusquedaMapa
					| ExcepcionCreaccionObjeto | IOException | ErrorCarretera e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		});
		this.add(botonplay);
		
		/*//BOTON STOP

		JButton botonstop = new JButton();
		botonstop.setToolTipText("detiene el simulador");
		botonstop.setIcon(new ImageIcon(Utils.loadImage("resources/icons/stop.png")));
						
		botonstop.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
				t1.interrupt();
				
			}
		});
		this.add(botonstop);*/
		
		//BOTON RESET
		
		JButton botonreset = new JButton();
		botonreset.setToolTipText("reinicia el simulador");
		botonreset.setIcon(new ImageIcon(Utils.loadImage("resources/icons/reset.png")));
						
		botonreset.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			controlador.reinicia();
			}
		});
		this.add(botonreset);
		
		//DELAY
		
		/*this.delay = new JSpinner(new SpinnerNumberModel(1000, 0, 10000, 1000));
		this.delay.setToolTipText("Delay a ejecutar en el programa(milisegundos): 1-5000");
		this.delay.setMinimumSize(new Dimension(70, 70));
		this.delay.setMaximumSize(new Dimension(70, 70));
		sleep = 0;
		this.delay.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sleep =(int) delay.getValue();
			}
			
		});
		this.add(new JLabel(" Delay : "));
		this.add(delay);*/
		
		//SPINNER
		
		this.steps = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
		this.steps.setToolTipText("pasos a ejecutar: 1-1000");
		this.steps.setMinimumSize(new Dimension(70, 70));
		this.steps.setMaximumSize(new Dimension(70, 70));
		this.steps.setValue(1);
		this.pasos = 1;
		this.steps.addChangeListener(new ChangeListener() {
			

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
					pasos = (int)steps.getValue();
			}
		});
		this.add(new JLabel(" Pasos: "));
		this.add(steps);
	
		//TIEMPO
		this.add(new JLabel(" Tiempo: "));
		this.time = new JTextField("0", 5);
		this.time.setToolTipText("Tiempo actual");
		this.time.setMaximumSize(new Dimension(70, 70));
		this.time.setMinimumSize(new Dimension(70, 70));
		this.time.setEditable(false);
		this.add(this.time);
		
		// OPCIONAL // informes
		JButton botonGeneraReports = new JButton();
		botonGeneraReports.setToolTipText("Genera informes");
		botonGeneraReports.setIcon(new
		ImageIcon(Utils.loadImage("resources/icons/report.png")));
		botonGeneraReports.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		ventanaPrincipal.generaInformes();
		}
		});
		this.add(botonGeneraReports);
	}
	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		this.time.setText("" + tiempo);
		
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		
		
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		this.steps.setValue(1);
		this.time.setText("0");
		
	}

	public int getPasos() {
		return  pasos;
	}
}
