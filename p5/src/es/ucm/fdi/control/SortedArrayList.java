package es.ucm.fdi.control;

import java.util.*;

import es.ucm.fdi.excepciones.SortedArrayListException;


public class SortedArrayList<E> extends ArrayList<E>{

	private Comparator<E> cmp;
	
	public SortedArrayList(Comparator<E> cmp) {
		this.cmp = cmp;
		
	}
	
	@Override
	public boolean add(E e) {
	// programar la inserción ordenada.
		int indice = super.size();
		if(super.isEmpty())
			return super.add(e);
		else {
			for (int i = 0; i < super.size()-1; i++){// encontramos el primero que es mayor que el nuestro	
				if(cmp.compare(e, super.get(i)) == -1) {
					indice = i;
				 super.add(i, e);
				 return true;
				}
			}
				
			}				
			super.add(indice, e);
			return true;
		}
	
			
	
	@Override
	public boolean addAll(Collection< ? extends E> c) {
	// programar inserción ordenada (invocando a add)
		while(c.iterator().hasNext()) {
			this.add(c.iterator().next());
		}
		return true;
	}
	// sobreescribir los métodos que realizan operaciones de
	// inserción basados en un índice para que lancen excepcion.
	// Ten en cuenta que esta operación rompería la ordenación.
	// estos métodos son add(int index, E element),
	// addAll(int index, Colection<? Extends E>) y E set(int index, E element).
	@Override
	 public void add(int index,E element) {
		try {
			throw new SortedArrayListException();
		} catch (SortedArrayListException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		try {
			throw new SortedArrayListException();
		} catch (SortedArrayListException e) {
			return true;
		}
	}
	
	@Override
	public E set(int index,E element) {
		try {
			throw new SortedArrayListException();
		} catch (SortedArrayListException e) {
			return null;
		}
	}
}


