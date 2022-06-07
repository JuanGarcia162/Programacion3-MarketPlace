package co.edu.uniquindio.marketplace.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.marketplace.aplication.Aplicacion;
import co.edu.uniquindio.marketplace.exepciones.errosExisteVendedor;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteEliminarException;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteException;
import co.edu.uniquindio.marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AgregarVendedorController {

	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	public ModelFactoryController getModelFactoryController() {
		return modelFactoryController;
	}

	public void setModelFactoryController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		cargarListadoVendedores();
	}

	private Aplicacion aplicacion;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<Vendedor> tblListaVendedor;

	@FXML
	private Button btnGuardar;

	@FXML
	private TableColumn<Vendedor, String> colCedula;

	@FXML
	private Button bttEliminar;

	@FXML
	private TextField texUsuario;

	@FXML
	private TextField texCedula;

	@FXML
	private TextField texDireccion;

	@FXML
	private TextField rexApellido;

	@FXML
	private TableColumn<Vendedor, String> colNombre;

	@FXML
	private TextField texContraseña;

	@FXML
	private TextField texNombre;

	private Vendedor vendedorSeleccionado;

	ObservableList<Vendedor> listadoVendedores = FXCollections.observableArrayList();

	@FXML
	void bttGuardar(ActionEvent event) throws errosExisteVendedor {
		guardarVendedor();
	}

	private void guardarVendedor() throws errosExisteVendedor {
		String nombre = texNombre.getText();
		String cedula = texCedula.getText();
		String apellido = rexApellido.getText();
		String direccion = texDireccion.getText();
		String usuario = texUsuario.getText();
		String contracenia = texContraseña.getText();
		if (vendedorSeleccionado == null) {
			if (datosValidos(nombre, cedula, apellido, direccion, usuario, contracenia)) {

				Vendedor vendedor1 = null;
				vendedor1 = modelFactoryController.agregarVendedor(nombre, cedula, apellido, direccion, usuario,
						contracenia);
				cargarListadoVendedores();
				if (vendedor1 != null) {
					limpiarDatos();
					modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor registrado",
							"El Vendedor fue registrado con exito", AlertType.INFORMATION);
				} else {
					modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor no registrado", "El Vendedor no fue registrado",
							AlertType.INFORMATION);
				}

			}
		} else {
			modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor no registrado", "no debe seleccionar un vendedor existente",
					AlertType.WARNING);
		}
	}

	private boolean datosValidos(String nombre, String cedula, String apellido, String direccion, String usurio,
			String contrasenia) {

		String mensaje = "";

		if (nombre == null || nombre.equals("") || nombre.isEmpty()) {
			mensaje += "El nombre es invalido";
		}
		if (apellido == null || apellido.equals("") || apellido.isEmpty()) {
			mensaje += "El apellido es invalido";
		}
		if (cedula == null || cedula.equals("") || cedula.isEmpty()) {
			mensaje += "El documento es invalido";
		}
		if (direccion == null || direccion.equals("") || direccion.isEmpty()) {
			mensaje += "la direccion es invalido";
		}
		if (usurio == null || usurio.equals("") || usurio.isEmpty()) {
			mensaje += "el usuario es invalido";
		}
		if (contrasenia == null || contrasenia.equals("") || contrasenia.isEmpty()) {
			mensaje += "la contrasenia es invalido";
		}
		if (modelFactoryController.existenciaVendedor(cedula) && vendedorSeleccionado == null) {
			mensaje += "Ya existe un vendedor con  documento";
		}

		if (contrasenia.length() > 5) {
			mensaje += "la contrasenia debe tener 5 caracteres o menos";
		}
		if (mensaje.equals("")) {
			return true;
		}
		modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Datos Invalidos", mensaje, AlertType.WARNING);
		return false;
	}



	@FXML
	void salir(ActionEvent event) {
		salir();
	}

	@FXML
	void bttActualizar(ActionEvent event) throws vendedorExisteException {
		actualizarVendedor();
	}

	private void actualizarVendedor() throws vendedorExisteException {
		String nombre = texNombre.getText();
		String cedula = texCedula.getText();
		String apellido = rexApellido.getText();
		String direccion = texDireccion.getText();
		String usuario = texUsuario.getText();
		String contraceña = texContraseña.getText();

		if (datosValidos(nombre, cedula, apellido, direccion, usuario, contraceña)) {
			limpiarDatos();
			modelFactoryController.actualizarVendedor(nombre, cedula, apellido, direccion, usuario, contraceña,
					vendedorSeleccionado.getCedula());
			modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor Actualizado", "El Vendedor ha sido actualizado",
					AlertType.INFORMATION);
			cargarListadoVendedores();
		} else {

			modelFactoryController.mostrarMensaje("Notificacion vendedor", "vendedor no seleccionado",
					"Para Vendedor un vendedor debe seleccionar a uno", AlertType.WARNING);

		}
	}

	private void mostraInformacionvendedor(Vendedor vendedor) {

		if (vendedor != null) {

			texNombre.setText(vendedor.getNombre());
			texCedula.setText(vendedor.getCedula());
			rexApellido.setText(vendedor.getApellido());
			texDireccion.setText(vendedor.getDireccion());
			texUsuario.setText(vendedor.getUsurio());
			texContraseña.setText(vendedor.getContraceña());

		}
	}

	@FXML
	void bttEliminar(ActionEvent event) throws vendedorExisteEliminarException {

		eliminarVendedor();

	}

	private void eliminarVendedor() throws vendedorExisteEliminarException {

		if (vendedorSeleccionado != null) {

			if (mostrarMensajeConfirmacion("¿Esta seguro de eliminar a este Vendedor?")) {

				modelFactoryController.eliminarVendedor(vendedorSeleccionado.getCedula());

				modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor eliminado", "El Vendedor fue eliminado con exito",
						AlertType.INFORMATION);
				cargarListadoVendedores();
				tblListaVendedor.refresh();
				limpiarDatos();
			} else {
				modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor no eliminado", "El Vendedor  no fue eliminado",
						AlertType.INFORMATION);
			}

		} else {
			modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor no seleccionado",
					"Para eliminar un Vendedor debe seleccionar a uno", AlertType.WARNING);
		}

	}

	private void limpiarDatos() {
		texNombre.setText("");
		texCedula.setText("");
		rexApellido.setText("");
		texDireccion.setText("");
		texUsuario.setText("");
		texContraseña.setText("");

	}

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

	public void salir() {

		Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
		stage.close();
	}

	@FXML
	void initialize() {

		this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));

		tblListaVendedor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			vendedorSeleccionado = newSelection;

			mostraInformacionvendedor(vendedorSeleccionado);

		});
		cargarListadoVendedores();
	}

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

		cargarListadoVendedores();
	}

	private void cargarListadoVendedores() {
		tblListaVendedor.getItems().clear();
		tblListaVendedor.setItems(obtenerVendedores());

	}

	private ObservableList<Vendedor> obtenerVendedores() {

		listadoVendedores.addAll(modelFactoryController.obtenerVendedores());

		return listadoVendedores;
	}

}
