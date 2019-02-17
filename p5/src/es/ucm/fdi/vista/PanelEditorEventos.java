package es.ucm.fdi.vista;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import es.ucm.fdi.contructoresEventos.ConstructorEventos;
import es.ucm.fdi.parser.ParserEventos;

public class PanelEditorEventos extends PanelAreaTexto {

	public PanelEditorEventos(String titulo, String texto, boolean b, VentanaPrincipal ventanaPrincipal) {
		super(titulo,b);
		this.setTexto(texto);
		
		// OPCIONAL
		JPopupMenu popUp = new JPopupMenu(ventanaPrincipal.getName());
		
		JMenuItem op = new JMenuItem("Generar Informes");
		op.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPrincipal.generaInformes();
			}
			
		});
		popUp.add(op);
		
		JMenuItem op2 = new JMenuItem("Cargar");
		op2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ventanaPrincipal.cargaFichero();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
			}
			
		});
		popUp.add(op2);
		
		JMenuItem op3 = new JMenuItem("Guardar");
		op3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPrincipal.guardar();
			}
			
		});
		popUp.add(op3);
		
		JMenuItem op4 = new JMenuItem("Limpiar");
		op4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPrincipal.clear();
			}
			
		});
		popUp.add(op4);
		
		JMenu op1 = new JMenu("Nueva Plantilla");
		popUp.add(op1);
		
		for (ConstructorEventos ce : ParserEventos.getConstructoresEventos()) {
		
		JMenuItem plantillas = new JMenuItem(ce.toString());
		plantillas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPrincipal.inserta(ce.template() + System.lineSeparator());;
			}
			
		});
		op1.add(plantillas);
		
		}
	
		this.areatexto.add(popUp);
		this.areatexto.addMouseListener(new MouseListener() {
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		if (e.getButton() == e.BUTTON3 && areatexto.isEnabled())
		popUp.show(e.getComponent(), e.getX(), e.getY());	
		
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		
	
		});
		}
		
}
