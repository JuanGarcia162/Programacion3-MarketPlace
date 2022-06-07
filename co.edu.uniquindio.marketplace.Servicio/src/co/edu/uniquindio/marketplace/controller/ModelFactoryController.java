package co.edu.uniquindio.marketplace.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.exepciones.errosExisteVendedor;
import co.edu.uniquindio.marketplace.exepciones.vendedorException;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteEliminarException;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteException;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteIngresarException;
import co.edu.uniquindio.marketplace.model.Comentarios;
import co.edu.uniquindio.marketplace.model.Estado;
import co.edu.uniquindio.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Solicitud;
import co.edu.uniquindio.marketplace.model.Vendedor;
import co.edu.uniquindio.marketplace.persistencia.Persistencia;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ModelFactoryController implements Runnable {

	Marketplace marketplace;
	Vendedor vendedorActual;
	Producto productoSeleccionado;

	Thread guardarXML;
	Thread guardarBIN;
	Thread guardarTXT;
	Thread guardarLogTXT;
	String mensajelog = "";
	String nombreVendedorLog="";

	Thread cargarRecursoXML;
	Thread cargarRecursoBIN;
	Thread cargarRecursoTXT;
	
	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		if (currentThread == guardarXML) {
			Persistencia.guardarRecursoMarketplaceXML(marketplace);
		}
		if (currentThread == guardarBIN) {
			Persistencia.guardarRecursomarkBinario(marketplace);
		}
		if (currentThread == guardarTXT) {

		}
		if (currentThread == guardarLogTXT) {
			Persistencia.guardaRegistroLog(mensajelog, 1, nombreVendedorLog);
		}
		if (currentThread == cargarRecursoXML) {
			marketplace = Persistencia.cargarRecursoMarketplaceXML();
		}
		if (currentThread == cargarRecursoBIN) {
			marketplace=Persistencia.cargarRecursomarkBinario();
		}
		if (currentThread == cargarRecursoTXT) {

		}
	}

	// ------------------------------ Singleton
// ------------------------------------------------
// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {
		inicializarDatos();
	}

	public void inicializarDatos() {
		marketplace = new Marketplace();
		cargarRecursoXML= new Thread(this);
		cargarRecursoXML.start();
//		cargarRecursoBIN= new Thread(this);
//		cargarRecursoBIN.start();
	}

	public Vendedor agregarVendedor(String nombre, String cedula, String apellido, String direccion, String usuario,
			String contraceña) throws errosExisteVendedor {
		mensajelog="";
		nombreVendedorLog="";

		Vendedor vendedor = marketplace.agregarVendedor(nombre, cedula, apellido, direccion, usuario, contraceña);
		
		guardarXML= new Thread(this);
		guardarXML.start();
		guardarBIN= new Thread(this);
		guardarBIN.start();
		if (vendedor != null) {
			mensajelog += "se guardo el vendedor satisfactorimente";
			nombreVendedorLog=vendedor.getNombre();
		} else {
			mensajelog += "no se guardo el vendedor";
			nombreVendedorLog=nombre;
		}

		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();

		return vendedor;
	}

	public void actualizarVendedor(String nombre, String cedula, String apellido, String direccion, String usuario,
			String contraceña, String cedula2) throws vendedorExisteException {
		Vendedor vendedor = null;
		mensajelog="";
		nombreVendedorLog="";
		vendedor = marketplace.actualizarVendedor(nombre, cedula, apellido, direccion, usuario, contraceña, cedula2);
		guardarXML= new Thread(this);
		guardarXML.start();
		if (vendedor != null) {
			mensajelog += "se actualizo el vendedor satisfactorimente";
			nombreVendedorLog=vendedor.getNombre();
		} else {
			mensajelog += "no se Actualizo el vendedor";
			nombreVendedorLog=nombre;
		}
		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
	}

	public void eliminarVendedor(String cedula) throws vendedorExisteEliminarException {
		Vendedor vendedor = null;
		mensajelog="";
		nombreVendedorLog="";
		vendedor = marketplace.eliminarVendedor(cedula);
		guardarXML= new Thread(this);
		guardarXML.start();
		if (vendedor != null) {
			mensajelog += "se elimino el vendedor satisfactorimente";
			nombreVendedorLog=vendedor.getNombre();
		} else {
			Vendedor vendedor2 = marketplace.obtenerVendedor(cedula);
			mensajelog += "no se elimino el vendedor";
			nombreVendedorLog=vendedor2.getNombre();
		}
		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
	}

	public ArrayList<Vendedor> obtenerVendedores() {
		return marketplace.getListaVendedor();
	}

	public Vendedor validarIngraso(String usuario, String contraseña) throws vendedorExisteIngresarException {
		Vendedor vendedor = null;
		mensajelog="";
		nombreVendedorLog="";
		vendedor = marketplace.validarIngraso(usuario, contraseña);
		if (vendedor != null) {
			setVendedorActual(vendedor);
			mensajelog += "ingreso el vendedor  " + vendedor.getUsurio();
			nombreVendedorLog=vendedor.getUsurio();
		} else {
			mensajelog += "no pudo ingresor el vendedor";
			nombreVendedorLog=usuario;
		}
		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
		return vendedor;
	}

	public Vendedor getVendedorActual() {
		return vendedorActual;
	}

	public void setVendedorActual(Vendedor vendedorActual) {
		this.vendedorActual = vendedorActual;
	}

	public void enviarSilicitud(Vendedor vendedor2) {
		Solicitud solicitud = null;
		mensajelog="";
		nombreVendedorLog="";
		solicitud = marketplace.crearSolicitud(vendedor2, vendedorActual);

		guardarXML= new Thread(this);
		guardarXML.start();
		if (solicitud != null) {
			mensajelog += " envio la solicitud a" + vendedor2.getNombre();
			nombreVendedorLog=vendedor2.getNombre();
		} else {
			mensajelog += "no pudo enviar la solicitud del " + vendedor2.getNombre();
			nombreVendedorLog=vendedor2.getNombre();
		}
		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
	}

	public void rechazarSolicitud(Solicitud soli) {
		boolean rechazado = false;
		mensajelog="";
		nombreVendedorLog="";
		rechazado = marketplace.rechazarSolicitud(soli, vendedorActual);

		guardarXML= new Thread(this);
		guardarXML.start();
		if (rechazado != false) {
			mensajelog += " rechazo  la solicitud a  " + soli.getNombre();
			nombreVendedorLog=vendedorActual.getNombre();
		} else {
			nombreVendedorLog=vendedorActual.getNombre();
			mensajelog += "no se pudo rechazar la solicitud del " + soli.getNombre();
		}
		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
	}

	public void aceptarSolicitud(Solicitud solicitudSeleccionada) {
		boolean rechazado = false;
		mensajelog="";
		nombreVendedorLog="";
		rechazado = marketplace.aceptarSolicitud(solicitudSeleccionada, vendedorActual);
		guardarXML= new Thread(this);
		guardarXML.start();
		
		if (rechazado != false) {
			nombreVendedorLog= vendedorActual.getNombre();
			mensajelog += " acepto  la solicitud a  " + solicitudSeleccionada.getNombre();

		} else {
			nombreVendedorLog=vendedorActual.getNombre();
			mensajelog += "no se pudo aceptar la solicitud del " + solicitudSeleccionada.getNombre();
		}

		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
	}

	public Producto agregarProducto(String nombre, String categoria, Double precio, Estado estado)
			throws vendedorException {

		Producto producto = null;
		mensajelog="";
		nombreVendedorLog="";
		producto = marketplace.agregarProducto(nombre, categoria, precio, estado, vendedorActual.getCedula());
		guardarXML= new Thread(this);
		guardarXML.start();
		
		if (producto != null) {
			mensajelog += "se guardo el Producto satisfactorimente";
			nombreVendedorLog=producto.getNombre();
		} else {
			mensajelog += "no se guardo el producto";
			nombreVendedorLog=nombre;
		}

		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
		return producto;
	}

	public Producto actualizarProducto(String nombre, String categoria, Double precio, Estado estado, String nombre2) {
		Producto producto = null;

		mensajelog="";
		nombreVendedorLog="";
		producto = marketplace.actualizarProducto(nombre, categoria, precio, estado, nombre2,
				vendedorActual.getCedula());
		guardarXML= new Thread(this);
		guardarXML.start();
		if (producto != null) {
			mensajelog += "se actualizo el Producto satisfactorimente";
			nombreVendedorLog=producto.getNombre();
		} else {
			mensajelog += "no se producto el producto";
			nombreVendedorLog=nombre;
		}
		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
		return producto;

	}

	public Producto eliminarProducto(String nombre) {
		Producto producto = null;
		mensajelog="";
		nombreVendedorLog="";
		producto = marketplace.eliminarProducto(nombre, vendedorActual.getCedula());
		guardarXML= new Thread(this);
		guardarXML.start();
		if (producto != null) {
			mensajelog += "se elimino el vendedor satisfactorimente";
			nombreVendedorLog=producto.getNombre();
		} else {
			Producto producto2 = marketplace.buscarProducto1(nombre, vendedorActual);
			mensajelog += "no se elimino el vendedor";
			nombreVendedorLog=producto2.getNombre();
		}
		guardarLogTXT=new Thread(this);
		guardarLogTXT.start();
		return producto;
	}

	public ArrayList<Producto> obtenerProductos() {
		ArrayList<Vendedor> obtenerVendedores = vendedorActual.getListaVendedores();
		ArrayList<Producto> listaProductos = new ArrayList<>();
		listaProductos.addAll(vendedorActual.getListaProductoa());
		for (Vendedor vendedor : obtenerVendedores) {
			listaProductos.addAll(vendedor.getListaProductoa());
		}
		return listaProductos;
	}

	public ArrayList<Vendedor> obtenerVendedorAliado() {
		return vendedorActual.getListaVendedores();
	}

	public boolean existenciaVendedor(String cedula) {
		return marketplace.existenciaVendedor(cedula);
	}

	public boolean buscarProducto(String nombre) {
		return marketplace.buscarProducto(nombre, vendedorActual);
	}

	public void agregarComentario(String comentario) {
		marketplace.agregarComentario(comentario, productoSeleccionado, vendedorActual);
	}

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public ArrayList<Comentarios> obtenerComemtarios() {
		return productoSeleccionado.getListaComentarios();
	}

	void agregarMegustaProducto(Producto productoSeleccionado) {
		marketplace.agregarMeGustaProducto(productoSeleccionado, vendedorActual);
		guardarXML= new Thread(this);
		guardarXML.start();
//		guardarBIN= new Thread(this);
//		guardarBIN.start();
		
	}

	void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alerta = new Alert(alertType);
		alerta.setTitle(titulo);
		alerta.setHeaderText(header);
		alerta.setContentText(contenido);
		alerta.showAndWait();

	}

}
