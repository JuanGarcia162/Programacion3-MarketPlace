package co.edu.uniquindio.marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String imagen;
	private String categoria;
	private Double precio;
	private Estado estado;
	private ArrayList<MeGusta>listaMegusta = new ArrayList<>();
	private ArrayList<Comentarios>listaComentarios= new ArrayList<>();
	public String getNombre() {
		return nombre;
	}	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Double getPresio() {
		return precio;
	}
	public void setPresio(Double presio) {
		this.precio = presio;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public ArrayList<MeGusta> getListaMegusta() {
		return listaMegusta;
	}
	public void setListaMegusta(ArrayList<MeGusta> listaMegusta) {
		this.listaMegusta = listaMegusta;
	}
	public ArrayList<Comentarios> getListaComentarios() {
		return listaComentarios;
	}
	public void setListaComentarios(ArrayList<Comentarios> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}
	
	
	
	
	
	
	
	

}
