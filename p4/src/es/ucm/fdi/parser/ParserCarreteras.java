package es.ucm.fdi.parser;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.excepciones.ErrorBusquedaMapa;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.objetos.Cruce;
import es.ucm.fdi.objetos.CruceGenerico;

public class ParserCarreteras {

	public static List<CruceGenerico<?>> parseaListaCruces(String[] itinerario, MapaCarreteras mapa) throws ErrorBusquedaMapa {
		// TODO Auto-generated method stub
		List <CruceGenerico<?>> iti = new ArrayList<CruceGenerico<?>>();
		for (String cruce : itinerario) {
			iti.add(mapa.getCruce(cruce));
		}
		return iti;
	}

}
