package es.ucm.fdi.excepciones;

public class ErrorDeSimulacion extends Exception {

	public ErrorDeSimulacion() {};
	public ErrorDeSimulacion(String message) {
		super(message);
	}
}
