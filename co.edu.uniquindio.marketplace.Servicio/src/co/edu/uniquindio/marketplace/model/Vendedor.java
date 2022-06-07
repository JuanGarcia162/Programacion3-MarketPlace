package co.edu.uniquindio.marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String cedula;
	private String apellido;
	private String direccion;
	private String usurio;
	private String contraseña;
	private ArrayList<Vendedor>listaVendedores=new ArrayList<>();
	private  ArrayList<Producto>listaProductoa=new ArrayList<>();
	private  ArrayList<Producto>Sugeridos=new ArrayList<>();
	private  ArrayList<Solicitud>ListaSolicitudes=new ArrayList<>();
	private  ArrayList<Solicitud>ListaSolicitudesEnivadas=new ArrayList<>();
	
	
	public ArrayList<Producto> getSugeridos() {
		return Sugeridos;
	}
	public void setSugeridos(ArrayList<Producto> sugeridos) {
		Sugeridos = sugeridos;
	}
	public Vendedor(String nombre, String cedula, String apellido, String direccion, String usurio, String contraceña,
			ArrayList<Vendedor> listaVendedores, ArrayList<Producto> listaProductoa) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.apellido = apellido;
		this.direccion = direccion;
		this.usurio = usurio;
		this.contraseña = contraceña;
		this.listaVendedores = listaVendedores;
		this.listaProductoa = listaProductoa;
	}
	public Vendedor() { 
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUsurio() {
		return usurio;
	}
	public void setUsurio(String usurio) {
		this.usurio = usurio;
	}
	public String getContraceña() {
		return contraseña;
	}
	public void setContraceña(String contraceña) {
		this.contraseña = contraceña;
	}
	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}
	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}
	public ArrayList<Producto> getListaProductoa() {
		return listaProductoa;
	}
	public void setListaProductoa(ArrayList<Producto> listaProductoa) {
		this.listaProductoa = listaProductoa;
	}
	public ArrayList<Solicitud> getListaSolicitudes() {
		return ListaSolicitudes;
	}
	public void setListaSolicitudes(ArrayList<Solicitud> listaSolicitudes) {
		ListaSolicitudes = listaSolicitudes;
	}
	public ArrayList<Solicitud> getListaSolicitudesEnivadas() {
		return ListaSolicitudesEnivadas;
	}
	public void setListaSolicitudesEnivadas(ArrayList<Solicitud> listaSolicitudesEnivadas) {
		ListaSolicitudesEnivadas = listaSolicitudesEnivadas;
	}
	
	

}
