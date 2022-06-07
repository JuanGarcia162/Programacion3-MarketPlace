package co.edu.uniquindio.marketplace.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.marketplace.aplication.Aplicacion;
import co.edu.uniquindio.marketplace.exepciones.vendedorException;
import co.edu.uniquindio.marketplace.model.Estado;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class AgregarProductoController {

	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	private Aplicacion aplicacion;

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		cargarListadoVendedores();
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<Producto> tabListaProducto;

	@FXML
	private Button btnGuardar;

	@FXML
	private TableColumn<Producto, String> colEstado;

	@FXML
	private Button bttEliminar;

	@FXML
	private Button bttImagen;
	
	@FXML
	private TableColumn<Producto, String> colNombre;

	@FXML
	private Button btnActualizar;

	@FXML
	private TextField texNombre;

	@FXML
	private TextField texCategoria;

	@FXML
	private TextField texPrecio;

	@FXML
	private ComboBox<Estado> cbEstado;

	Producto productoSeleccionado;

	ObservableList<Producto> listadoProducto = FXCollections.observableArrayList();

	@FXML
	void bttGuardar(ActionEvent event) throws vendedorException {
		agregarProducto();
	}
	
	@FXML
	void bttImagen(ActionEvent event){
		
	}

	private void agregarProducto() throws vendedorException {

		String nombre = texNombre.getText();

		String categoria = texCategoria.getText();
		Double precio = Double.parseDouble(texPrecio.getText());
		Estado estado = cbEstado.getValue();
		if (datosValidos(nombre, categoria, precio, estado)) {
			Producto producto = null;
			producto = modelFactoryController.agregarProducto(nombre, categoria, precio, estado);
			cargarListadoVendedores();
			limpiarDatos();
		if (producto != null) {
			mostrarMensaje("Notificacion Producto", "Producto registrado", "El Producto fue registrado con exito",					AlertType.INFORMATION);
			} else {
				mostrarMensaje("Notificacion Producto", "Producto no registrado", "El Producto no fue registrado",
						AlertType.INFORMATION);
			}
		}
	}
	
	private void mostraInformacionvendedor(Producto producto) {

		if (producto != null) {

			texNombre.setText(producto.getNombre());
			texCategoria.setText(producto.getCategoria());
			texPrecio.setText(String.valueOf(producto.getPresio()));
			cbEstado.setValue(producto.getEstado());
		
		}
	}

	private boolean datosValidos(String nombre, String apellido, Double precio, Estado estado) {

		String mensaje = "";

		if (nombre == null || nombre.equals("")) {
			mensaje += "El nombre es invalido";
		}
		if (apellido == null || apellido.equals("")) {
			mensaje += "El apellido es invalido";
		}
		if (precio == 0 || precio.equals(0)) {
			mensaje += "El documento es invalido";
		} else {

			if (productoSeleccionado != null) {
				if (!precio.equals(productoSeleccionado.getNombre())) {
					if (modelFactoryController.buscarProducto(nombre)) {
						mensaje += "Ya existe un cliente con este documento";
					}
				}

			} else {
				if (modelFactoryController.buscarProducto(nombre)) {
					mensaje += "Ya existe un cliente con este documento";
				}
			}
		}
		if (estado == null || estado.equals("")) {
			mensaje += "La direccion es invalida";
		}

		if (mensaje.equals("")) {
			return true;
		} else {
			mostrarMensaje("Notificacion Cliente", "Datos Invalidos", mensaje, AlertType.WARNING);
			return false;
		}

	}

	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alerta = new Alert(alertType);
		alerta.setTitle(titulo);
		alerta.setHeaderText(header);
		alerta.setContentText(contenido);
		alerta.showAndWait();

	}

	@FXML
	void btnActualizar(ActionEvent event) {

		actualizarProducto();
	}

	private void actualizarProducto() {
		String nombre = texNombre.getText();

		String categoria = texCategoria.getText();
		Double precio = Double.parseDouble(texPrecio.getText());
		Estado estado = cbEstado.getValue();
		
		if(datosValidos(nombre, categoria, precio, estado)) {
			
			if(productoSeleccionado!=null) {

		Producto producto = null;
		producto = modelFactoryController.actualizarProducto(nombre, categoria, precio, estado,
				productoSeleccionado.getNombre());
		
		mostrarMensaje("Notificacion Cliente", "Cliente Actualizado", "El cliente ha sido actualizado", AlertType.INFORMATION);
		cargarListadoVendedores();
		limpiarDatos();
		
			}
			else{
				mostrarMensaje("Notificacion Cliente", "Cliente no seleccionado", "Para eliminar un cliente debe seleccionar a uno", AlertType.WARNING);
			}
	}}

	private boolean mostrarMensajeConfirmacion(String mensaje) {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Confirmacion");
		alerta.setContentText(mensaje);
		Optional<ButtonType> action = alerta.showAndWait();
		if (action.get() == ButtonType.OK) {
			return true;
		}

		return false;

	}

	
	
	@FXML
	void bttEliminar(ActionEvent event) {
		eliminarProducto();

	}

	private void eliminarProducto() {

		if (productoSeleccionado != null) {
			
			if (mostrarMensajeConfirmacion("¿Esta seguro de eliminar a este Producto?")) {
			Producto producto = modelFactoryController.eliminarProducto(productoSeleccionado.getNombre());
			cargarListadoVendedores();
			
			mostrarMensaje("Notificacion producto", "producto eliminado", "El producto fue eliminado con exito",
					AlertType.INFORMATION);
			limpiarDatos();
			
			
			
			}else {
				mostrarMensaje("Notificacion producto", "Vendedor no producto", "El producto  no fue eliminado",
						AlertType.INFORMATION);
				
			}
				

		}
	}

	private void limpiarDatos() {
		texNombre.setText("");
		
		

		texCategoria.setText("");
		texPrecio.setText("");
	 cbEstado.setValue(null);
		
	}

	@FXML
	void initialize() {
		ObservableList<Estado> listaEstado = FXCollections.observableArrayList(Estado.values());
		cbEstado.setItems(listaEstado);

		this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

		tabListaProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			productoSeleccionado = newSelection;
			mostraInformacionvendedor(productoSeleccionado);
		});

		cargarListadoVendedores();

	}

	private void cargarListadoVendedores() {
		tabListaProducto.getItems().clear();
		tabListaProducto.setItems(obtenerProductos());
		tabListaProducto.refresh();

	}

	private ObservableList<Producto> obtenerProductos() {

		listadoProducto.addAll(modelFactoryController.getVendedorActual().getListaProductoa());

		return listadoProducto;
	}
}
