package co.edu.uniquindio.marketplace.persistencia;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.model.Vendedor;

public class Persistencia {

	public static final String RUTA_ARCHIVO_MARKEPLACE_BINARIO_DAT = "src/Resource/markeplaceBinario.dat";
	public static final String RUTA_ARCHIVO_MARKEPLACE_XML = "src/Resource/Markeplace.xml";
	public static final String RUTA_ARCHIVO_LOG = "src/Resource/log.txt";


//	----------------------LOADS------------------------
	public static synchronized void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}

//	----------------------SAVES------------------------
// ------------------------------------SERIALIZACIÓN(BINARIO) y XML
	
	public static Marketplace cargarRecursomarkBinario() {
		Marketplace mark = null;
		try {
			mark = (Marketplace)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MARKEPLACE_BINARIO_DAT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mark;
	}

	public static synchronized void guardarRecursomarkBinario(Marketplace mark) {
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MARKEPLACE_BINARIO_DAT, mark);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static Marketplace cargarRecursoMarketplaceXML() {
		Object object = null;
		Marketplace mark = null;
		try {
			object = ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MARKEPLACE_XML);
			mark = (Marketplace) object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mark;
	}

	public static synchronized void guardarRecursoMarketplaceXML(Marketplace mark) {
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MARKEPLACE_XML, mark);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}