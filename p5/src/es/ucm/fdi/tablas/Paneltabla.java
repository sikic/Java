package es.ucm.fdi.tablas;

import java.awt.*;

import javax.swing.*;

import es.ucm.fdi.modelos.ModeloTabla;
import es.ucm.fdi.vista.VentanaPrincipal;

public class Paneltabla<T> extends JPanel{
	
	private ModeloTabla<T> modelo;
	
		public Paneltabla (String bordeId, ModeloTabla<T> modelo){
			
			this.setLayout(new GridLayout(1,1));
			this.setBorder(VentanaPrincipal.bordePorDefecto);
			this.modelo = modelo;
			JTable tabla = new JTable(this.modelo);
			this.add(new JScrollPane(tabla));
		}

}
