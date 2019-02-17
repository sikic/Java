package es.ucm.fdi.vista;


import java.awt.*;

import javax.swing.*;

public class PanelAreaTexto extends JPanel {
	
	protected JTextArea areatexto; 
	
	public PanelAreaTexto(String titulo, boolean editable){
		
		this.setLayout(new GridLayout(1,1));
		this.areatexto = new JTextArea(40, 30);
		this.areatexto.setEditable(editable);
		this.add(new JScrollPane(areatexto));
		this.setBorde(titulo);
	}
	
	public void setBorde(String titulo){
		
		this.setBorder(BorderFactory.createTitledBorder(titulo));
	}
	
	public String getTexto() {return this.areatexto.getText();}
	public void setTexto(String texto) {this.areatexto.setText(texto);}
	public void limpiar() {setTexto("");}
	public void inserta(String valor){
		
	this.areatexto.insert(valor, this.areatexto.getCaretPosition());
	}
}

