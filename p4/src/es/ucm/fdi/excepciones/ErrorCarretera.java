package es.ucm.fdi.excepciones;

public class ErrorCarretera extends Exception {
	
	public ErrorCarretera() {}
	
	public ErrorCarretera(String mensaje) {
		super(mensaje);
	}

}
