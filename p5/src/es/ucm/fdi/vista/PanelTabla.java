package es.ucm.fdi.vista;

import java.awt.*;

import javax.swing.*;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.modelos.ModeloTabla;
import es.ucm.fdi.modelos.ModeloTablaEventos;

public class PanelTabla <T> extends JPanel {

	private ModeloTabla<T> modelo;
	
	public PanelTabla(String string, ModeloTabla<T> modelo) {
			
			this.setLayout(new GridLayout(1,1));
			this.setBorder(BorderFactory.createTitledBorder(string));
			this.modelo = modelo;
			JTable tabla = new JTable(this.modelo);
			this.add(new JScrollPane(tabla));
		//	this.modelo.addRow(modelo.getColumnClass());
		}
	}


