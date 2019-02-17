package es.ucm.fdi.main;
/************************************************************
*				Práctica 4: Simulador de Tráfico
*
*
* TECNOLOGIA DE LA PROGRAMACION
* Facultad de Informatica
* Universidad Complutense de Madrid
*
* Autores:  Francisco Garcia Rofes y Luis Antonio Rojas
* Practica: Simulador De Trafico
* Fecha de creacion: 23/02/2018
* Fecha de ultima modificacion: 19/03/2012
* Contacto: lurojas@ucm.es  /  franga06@ucm.es
*
*************************************************************/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.SimuladorTrafico;
import es.ucm.fdi.excepciones.EjecutaException;
import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.excepciones.ErrorCarretera;
import es.ucm.fdi.excepciones.ErrorDeSimulacion;
import es.ucm.fdi.excepciones.ErrorEventoAñadido;
import es.ucm.fdi.excepciones.ExcepcionCreaccionObjeto;
import es.ucm.fdi.objetos.FactorMasificacion;
import es.ucm.fdi.vista.VentanaPrincipal;


public class Main {


	private final static Integer limiteTiempoPorDefecto = 50;
	private static Integer limiteTiempo = null;
	private static String ficheroEntrada = null;
	private static String ficheroSalida = null;
	private static ModoEjecucion ModoEjecucion = null;

	
	private static void ParseaArgumentos(String[] args) {

		// define the valid command line options
		//
		Options opcionesLineaComandos = Main.construyeOpciones();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine linea = parser.parse(opcionesLineaComandos, args);
			parseaOpcionHELP(linea, opcionesLineaComandos);
			parseaOpcionFicheroIN(linea);
			parseaOpcionFicheroOUT(linea);
			parseaOpcionSTEPS(linea);
			parseaOpcionModo(linea);
				
			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] resto = linea.getArgs();
			if (resto.length > 0) {
				String error = "Illegal arguments:";
				for (String o : resto)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}
	private static void parseaOpcionModo(CommandLine linea) {
		String model = linea.getOptionValue("m");
		
		if(model.equals("gui")) {
			Main.ModoEjecucion  = ModoEjecucion.GUI;
			
		} else if(model.equals("bach")) {
			Main.ModoEjecucion = ModoEjecucion.BATCH;
		}

	}

	private static Options construyeOpciones() {
		Options opcionesLineacomandos = new Options();

		opcionesLineacomandos.addOption(Option.builder("h").longOpt("help").desc("Muestra la ayuda.").build());
		opcionesLineacomandos.addOption(Option.builder("i").longOpt("input").hasArg().desc("Fichero de entrada de eventos.").build());
		opcionesLineacomandos.addOption(
				Option.builder("o").longOpt("output").hasArg().desc("Fichero de salida, donde se escriben los informes.").build());
		opcionesLineacomandos.addOption(Option.builder("t").longOpt("ticks").hasArg()
				.desc("Pasos que ejecuta el simulador en su bucle principal (el valor por defecto es " + Main.limiteTiempoPorDefecto + ").")
				.build());
		opcionesLineacomandos.addOption(Option.builder("m").longOpt("type").hasArg().desc("Cambia entre consola y la gui").build());

		return opcionesLineacomandos;
	}

	private static void parseaOpcionHELP(CommandLine linea, Options opcionesLineaComandos) {
		if (linea.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), opcionesLineaComandos, true);
			System.exit(0);
		}
	}

	private static void parseaOpcionFicheroIN(CommandLine linea) throws ParseException {
		Main.ficheroEntrada = linea.getOptionValue("i");
		if (Main.ficheroEntrada == null) {
			throw new ParseException("El fichero de eventos no existe");
		}
	}

	private static void parseaOpcionFicheroOUT(CommandLine linea) throws ParseException {
		Main.ficheroSalida = linea.getOptionValue("o");
	}

	private static void parseaOpcionSTEPS(CommandLine linea) throws ParseException {
		String t = linea.getOptionValue("t", Main.limiteTiempoPorDefecto.toString());
		try {
			Main.limiteTiempo = Integer.parseInt(t);
			assert (Main.limiteTiempo < 0);
		} catch (Exception e) {
			throw new ParseException("Valor invalido para el limite de tiempo: " + t);
		}
	}

	private static void iniciaModoEstandar() throws IOException, ErrorDeSimulacion, EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, ErrorCarretera {
		
		InputStream is = new FileInputStream(new File(Main.ficheroEntrada));
		OutputStream os = Main.ficheroSalida == null ? System.out : new FileOutputStream(new File(Main.ficheroSalida));
		SimuladorTrafico sim = new SimuladorTrafico();
		Controlador ctrl = new Controlador(sim,Main.limiteTiempo,is,os);
		FactorMasificacion c = new FactorMasificacion(ctrl);
		ctrl.ejecuta();
		is.close();
		System.out.println("Done!");
		for (int i = 0; i < c.getCarretera_id().size(); i++) {
			System.out.println(c.getCarretera_id().get(i) + " " + c.getVehiculos().get(i));
		}

	}
	
	private enum ModoEjecucion{
		
		BATCH("batch"), GUI("gui");
		private String descModo;
		private ModoEjecucion(String modeDesc){
			descModo = modeDesc;
			}
		private String getModelDesc(){
			return descModo;
			}
		}
	
	private static void iniciaModoGrafico() throws FileNotFoundException,
		InvocationTargetException, InterruptedException {
		SimuladorTrafico sim = new SimuladorTrafico();
		OutputStream os = Main.ficheroSalida == null ?
				System.out : new FileOutputStream(new File(Main.ficheroSalida));
		Controlador ctrl = new Controlador(sim, Main.limiteTiempo, null, os);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new VentanaPrincipal(Main.ficheroEntrada, ctrl);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void ejecutaFicheros(String path) throws IOException, ErrorDeSimulacion, EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, ErrorCarretera {

		File dir = new File(path);

		if ( !dir.exists() ) {
			throw new FileNotFoundException(path);
		}
		
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".ini");
			}
		});

		for (File file : files) {
			Main.ficheroEntrada = file.getAbsolutePath();
			Main.ficheroSalida = file.getAbsolutePath() + ".out";
			Main.limiteTiempo = 10;
			Main.iniciaModoEstandar();
		}

	}
	
		
	public static void main(String[] args) throws IOException, ErrorDeSimulacion, EjecutaException, ErrorEventoAñadido, ErrorBusquedaMapa, ExcepcionCreaccionObjeto, ErrorCarretera {

		// example command lines:
		//
		// -i resources/examples/events/basic/ex1.ini
		// -i resources/examples/events/advanced/ex1.ini
		// 
		//-m batch -i resources/examples/events/advanced/ex1.ini 
		//-m gui -i resources/examples/events/basic/ex1.ini
		//-m gui -i resources/examples/events/advanced/ex1.ini
		
		Main.ParseaArgumentos(args);
		if(Main.ModoEjecucion == ModoEjecucion.GUI)
			try {
				iniciaModoGrafico();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else
			
				Main.iniciaModoEstandar();
		
		//Main.ejecutaFicheros("resources/examples/events/basic");
		//Main.ejecutaFicheros("resources/examples/events/advanced");
	
	}

}
