package es.ucm.fdi.modelos;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.ObservadorSimuladorTrafico;

public abstract class ModeloTabla<T> extends DefaultTableModel implements ObservadorSimuladorTrafico {
	
	protected String[] columnIds;
	protected List<T> lista;
	
	public ModeloTabla(String[] columnIdEventos, Controlador ctrl) {
		
		this.lista = null;
		this.columnIds = columnIdEventos;
		ctrl.addObserver(this);
	}
	
	@Override
	public String getColumnName(int col) { return this.columnIds[col]; }
	
	@Override
	public int getColumnCount() {return this.columnIds.length;}
	
	@Override
	public int getRowCount() {return this.lista == null ? 0 : this.lista.size();}
	
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
}