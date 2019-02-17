package es.ucm.fdi.vista;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;

public class BarraMenu extends JMenuBar{

	private int pasos;
	private ToolBar aux;
	
	public BarraMenu(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		super();
		// MANEJO DE FICHEROS
		JMenu menuFicheros = new JMenu("Ficheros");
		this.add(menuFicheros);
		this.creaMenuFicheros(menuFicheros,ventanaPrincipal);
		// SIMULADOR
		JMenu menuSimulador = new JMenu("Simulador");
		this.add(menuSimulador);
		this.creaMenuSimulador(menuSimulador,controlador,ventanaPrincipal);
		// INFORMES
		JMenu menuReport = new JMenu("Informes");
		this.add(menuReport);
		this.creaMenuInformes(menuReport,ventanaPrincipal);
		this.aux = ventanaPrincipal.getToolbar();
			}

	private void creaMenuInformes(JMenu menuReport, VentanaPrincipal mainWindow){
		
		JMenuItem generaInformes = new JMenuItem("Generar");
		generaInformes.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		// OPCIONAL
		mainWindow.generaInformes();
		}
		});
		
		menuReport.add(generaInformes);
		JMenuItem limpiaAreaInformes = new JMenuItem("Clear");
		limpiaAreaInformes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.clear();
				
			}
			
		});
		menuReport.add(limpiaAreaInformes);
		
	}

	private void creaMenuSimulador(JMenu menuSimulador, Controlador controlador, VentanaPrincipal mainWindow) {
		
		JMenuItem ejecuta = new JMenuItem("Ejecuta");
		ejecuta.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
			controlador.ejecuta(1);
			
		} catch (ErrorDeSimulacion | EjecutaException | ErrorEventoAñadido | ErrorBusquedaMapa
				| ExcepcionCreaccionObjeto | IOException | ErrorCarretera e1) {
			
			e1.printStackTrace();
		}
		}
		});
		
		JMenuItem reinicia = new JMenuItem("Reinicia");
		reinicia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.reinicia();
				
			}
			
			
		});
		menuSimulador.add(ejecuta);
		menuSimulador.add(reinicia);
		
	}

	private void creaMenuFicheros(JMenu menu, VentanaPrincipal ventanaPrincipal){
		
		JMenuItem cargar = new JMenuItem("Abrir");
		cargar.setMnemonic(KeyEvent.VK_L);
		cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.ALT_MASK));
		cargar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
			ventanaPrincipal.cargaFichero();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		});
		

		JMenuItem salvar = new JMenuItem("Cargar informes");
		salvar.setMnemonic(KeyEvent.VK_L);
		salvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,ActionEvent.ALT_MASK));
		salvar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			try {
				ventanaPrincipal.cargaFichero();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		});
		
		JMenuItem salvarInformes = new JMenuItem("Guardar informes");
		salvarInformes.setMnemonic(KeyEvent.VK_L);
		salvarInformes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,ActionEvent.ALT_MASK));
		salvarInformes.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			ventanaPrincipal.guardar();
		
			// TODO Auto-generated catch block
			
		}
		});
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_L);
		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.ALT_MASK));
		salir.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			ImageIcon p = new ImageIcon("resources/icons/pregunta.gif");
			if(JOptionPane.showConfirmDialog(getRootPane(), "¿Realmente deseas salir?","Salir", 
					JOptionPane.YES_NO_OPTION,1,p) == JOptionPane.YES_OPTION)
				
			System.exit(0);
		}
		});
		
		menu.add(cargar);
		menu.add(salvar);
		menu.addSeparator();
		menu.add(salvarInformes);
		menu.addSeparator();
		menu.add(salir);
		
	}
}


