package co.edu.uniquindio.marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.exepciones.errosExisteVendedor;
import co.edu.uniquindio.marketplace.exepciones.vendedorException;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteEliminarException;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteException;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteIngresarException;

public class Marketplace implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Vendedor> listaVendedor;

	public ArrayList<Vendedor> getListaVendedor() {
		return listaVendedor;
	}

	public void setListaVendedor(ArrayList<Vendedor> listaVendedor) {
		this.listaVendedor = listaVendedor;
	}

	public Marketplace() {
		listaVendedor = new ArrayList<>();
	}

	public Vendedor agregarVendedor(String nombre, String cedula, String apellido, String direccion, String usuario,
			String contraceña) throws errosExisteVendedor {
		Vendedor vendedor1 = new Vendedor();

		vendedor1.setApellido(apellido);
		vendedor1.setCedula(cedula);
		vendedor1.setContraceña(contraceña);
		vendedor1.setDireccion(direccion);
		vendedor1.setNombre(nombre);
		vendedor1.setUsurio(contraceña);
		listaVendedor.add(vendedor1);

		return vendedor1;
	}

	public boolean existenciaVendedor(String cedula) {
		for (Vendedor vendedor : listaVendedor) {
			if (vendedor.getCedula().equals(cedula)) {
				return true;
			}
		}
		return false;
	}

	public Vendedor actualizarVendedor(String nombre, String cedula, String apellido, String direccion, String usuario,
			String contraceña, String cedula2) throws vendedorExisteException {

		Vendedor vendedor1 = obtenerVendedor(cedula2);

		if (vendedor1 != null) {
			vendedor1.setApellido(apellido);
			vendedor1.setContraceña(contraceña);
			vendedor1.setDireccion(direccion);
			vendedor1.setNombre(nombre);
			vendedor1.setUsurio(usuario);
		} else {
			throw new vendedorExisteException("No existe el Vendedor");
		}
		return vendedor1;
	}

	public Vendedor obtenerVendedor(String cedula2) {
		for (Vendedor vendedor : listaVendedor) {
			if (vendedor.getCedula().equals(cedula2)) {
				return vendedor;
			}
		}
		return null;
	}

	public Vendedor eliminarVendedor(String cedula) throws vendedorExisteEliminarException {
		Vendedor vendedor1 = obtenerVendedor(cedula);
//		if (vendedor1 != null) {
		listaVendedor.remove(vendedor1);
//		}
//		else {
//			throw new vendedorExisteEliminarException("No existe el Vendedor");
//		}
		return vendedor1;
	}

	public Vendedor validarIngraso(String usuario, String contraseña) throws vendedorExisteIngresarException {
		Vendedor vendedor1 = null;
		for (Vendedor vendedor : listaVendedor) {
			if (vendedor.getUsurio().equalsIgnoreCase(usuario)
					&& vendedor.getContraceña().equalsIgnoreCase(contraseña)) {
				vendedor1 = vendedor;
			} else {
				if (vendedor1 == null) {

				}
			}
		}
		return vendedor1;
	}

	public Solicitud crearSolicitud(Vendedor vendedor2, Vendedor vendedorActual) {
		Solicitud solicitud = new Solicitud();
		solicitud.setVendedorEnviado(vendedorActual);
		solicitud.setRespuesta(false);
		solicitud.setVendedorResivido(vendedor2);
		solicitud.setNombre(vendedorActual.getNombre());
		solicitud.setApellido(vendedorActual.getApellido());
		vendedor2.getListaSolicitudes().add(solicitud);
		vendedorActual.getListaSolicitudesEnivadas().add(solicitud);
		return solicitud;
	}

	public boolean rechazarSolicitud(Solicitud solicitudSeleccionada, Vendedor vendedorActual) {
		boolean vendedor = false;
		vendedor = vendedorActual.getListaSolicitudes().remove(solicitudSeleccionada);
		solicitudSeleccionada.getVendedorEnviado().getListaSolicitudesEnivadas().remove(solicitudSeleccionada);
		return vendedor;
	}

	public boolean aceptarSolicitud(Solicitud solicitudSeleccionada, Vendedor vendedorActual) {
		boolean vendedor = false;
		vendedor = vendedorActual.getListaVendedores().add(solicitudSeleccionada.getVendedorEnviado());
		vendedorActual.getListaSolicitudes().remove(solicitudSeleccionada);
		solicitudSeleccionada.getVendedorEnviado().getListaSolicitudesEnivadas().remove(solicitudSeleccionada);
		return vendedor;
	}

	public Producto agregarProducto(String nombre, String categoria, Double precio, Estado estado,
			String vendedorActual) throws vendedorException {
		Vendedor vendedor1 = obtenerVendedor(vendedorActual);
		Producto producto = new Producto();
		producto.setCategoria(categoria);
		producto.setNombre(nombre);
		producto.setEstado(estado);
		producto.setPresio(precio);
		ArrayList<Producto> listaProducto = vendedor1.getListaProductoa();
		listaProducto.add(producto);
		vendedor1.setListaProductoa(listaProducto);
		return producto;

	}

	public Producto actualizarProducto(String nombre, String categoria, Double precio, Estado estado, String nombre2,
			String cedula) {
		Vendedor vendedor1 = obtenerVendedor(cedula);
		Producto producto = new Producto();
		producto.setCategoria(categoria);
		producto.setNombre(nombre);
		producto.setEstado(estado);
		producto.setPresio(precio);
		ArrayList<Producto> listaProducto = vendedor1.getListaProductoa();
		for (Producto producto2 : listaProducto) {
			if (producto2.getNombre().equalsIgnoreCase(nombre2)) {
				vendedor1.getListaProductoa().remove(producto2);
				vendedor1.getListaProductoa().add(producto);
				producto2 = producto;
				vendedor1.setListaProductoa(vendedor1.getListaProductoa());
				return producto;
			}
		}

		return null;
	}

	public Producto eliminarProducto(String nombre, String cedula) {
		Vendedor vendedor1 = obtenerVendedor(cedula);

		for (Producto producto2 : vendedor1.getListaProductoa()) {
			if (producto2.getNombre().equalsIgnoreCase(nombre)) {
				vendedor1.getListaProductoa().remove(producto2);
				return producto2;
			}
		}
		return null;

	}

	public boolean buscarProducto(String cedula, Vendedor cedulaIngresaado) {
		Producto producto1 = null;
		for (int i = 0; i < cedulaIngresaado.getListaProductoa().size(); i++) {
			producto1 = cedulaIngresaado.getListaProductoa().get(i);
			if (producto1.getNombre().equals(cedula)) {
				return true;
			}
		}
		return false;
	}

	public Producto buscarProducto1(String nombre, Vendedor cedulaIngresaado) {
		Producto producto1 = null;
		for (int i = 0; i < cedulaIngresaado.getListaProductoa().size(); i++) {
			producto1 = cedulaIngresaado.getListaProductoa().get(i);
			if (producto1.getNombre().equals(nombre)) {
				return producto1;
			}
		}
		return producto1;
	}

	public void agregarComentario(String comentario, Producto productoSeleccionado, Vendedor vendedorActual) {
		Comentarios comentario1 = new Comentarios();
		comentario1.setComentario(comentario);
		comentario1.setProductoComentado(productoSeleccionado);
		comentario1.setVendedorEnviado(vendedorActual);
		comentario1.setApellido(vendedorActual.getApellido());
		comentario1.setNombre(vendedorActual.getNombre());
		productoSeleccionado.getListaComentarios().add(comentario1);
	}

	public void agregarMeGustaProducto(Producto productoSeleccionado, Vendedor vendedorActual) {
		boolean encontroMegustaActual = false;
		System.out.println("*********************************"+productoSeleccionado.getListaMegusta().size());
		if (productoSeleccionado.getListaMegusta().size() > 0) {
			for (MeGusta megustaActual : productoSeleccionado.getListaMegusta()) {
				if (vendedorActual.equals(megustaActual.getVendedorEnviado())) {
					productoSeleccionado.getListaMegusta().remove(megustaActual);
					encontroMegustaActual = true;
				}
			}
		}
		if (!encontroMegustaActual) {
			MeGusta nuevoMegusta = new MeGusta(vendedorActual, productoSeleccionado);
			productoSeleccionado.getListaMegusta().add(nuevoMegusta);
		}
	}

}