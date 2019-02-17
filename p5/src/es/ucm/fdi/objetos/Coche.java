package es.ucm.fdi.objetos;

import java.util.List;
import java.util.Random;

import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.ini.IniSection;

public class Coche extends Vehiculo {
	protected int kmUltimaAveria;
	protected int resistenciaKm;
	protected int duracionMaximaAveria;
	protected double probabilidadDeAveria;
	protected Random numAleatorio;
	protected boolean averiado;
	
	public Coche(String id, int velocidadMaxima, List<CruceGenerico<?>> iti,int resistencia,double probabilidadAveria,int duracionMax,long semilla) throws ExcepcionCreaccionObjeto {
		super(id, velocidadMaxima, iti);
		// TODO Auto-generated constructor stub
		this.resistenciaKm = resistencia;
		this.probabilidadDeAveria = probabilidadAveria;
		this.duracionMaximaAveria = duracionMax;
		this.numAleatorio = new Random(semilla);
		this.averiado = false;
	}
	@Override
	 public void avanza() {
	 // - Si el coche está averiado poner “kmUltimaAveria” a “kilometraje”.
	 // - Sino el coche no está averiado y ha recorrido “resistenciakm”, y además
	 // “numAleatorio”.nextDouble() < “probabilidadDeAveria”, entonces
	 // incrementar “tiempoAveria” con “numAleatorio.nextInt(“duracionMaximaAveria”)+1
	 // - Llamar a super.avanza();
		if(this.tiempoAveria > 0 && !this.averiado) {
			this.averiado = true;
			this.kmUltimaAveria = this.kilometraje;
		}
		else {
			if( this.kilometraje >= this.resistenciaKm && this.numAleatorio.nextDouble() < this.probabilidadDeAveria) {
				this.tiempoAveria = numAleatorio.nextInt(this.duracionMaximaAveria) + 1;
				this.setVelocidadActual(0);
			}
		}
		super.avanza();
	}
	@Override
	protected void completaDetallesSeccion(IniSection is) {
		is.setValue("type", "car");
		is.setValue("speed", this.velocidadActual);
		is.setValue("kilometrage", this.kilometraje);
		is.setValue("faulty", this.tiempoAveria);
		is.setValue("location", this.haLlegado ? "arrived" :
		"(" + this.carretera.id + "," + this.getLocalizacion() + ")");
	}
	}

