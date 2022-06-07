package co.edu.uniquindio.marketplace.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.marketplace.aplication.Aplicacion;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class vendedorViewController {

	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
	ObservableList<Producto> listadoProductos = FXCollections.observableArrayList();
	Producto productoSeleccionado;

	ObservableList<Vendedor> listaVendedorAliado = FXCollections.observableArrayList();
	Vendedor vendedorAliadoSeleccionado;

	private Aplicacion aplicacion;

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		cargarListadoProductos();
		cargarListadoVendedoresAliados();
	}

	@FXML
	private ResourceBundle resources;


	@FXML
	private Label cantidadMegusta;

	@FXML
	private URL location;

	@FXML
	private TableColumn<Producto, String> colApellidoProducto;

	@FXML
	private Label lbNombre;

	@FXML
	private Label lbApellido;

	@FXML
	private TableView<Producto> tblListaProductos;

	@FXML
	private TableColumn<Producto, String> colNombreProducto;

	@FXML
	private TableView<Vendedor> tablaVendedoresAliados;

	@FXML
	private TableColumn<Vendedor, String> colNombreAliado;

	@FXML
	private TableColumn<Vendedor, String> colApellidoAliado;

	@FXML
	private Label lbCedula;

	@FXML
	private Label lbDiresccion;

	@FXML
	private Button bttAgregarProducto;

	@FXML
	private Button bttAgregarVendedor;

	@FXML
	private Button bttGestionarAliados;

	@FXML
	void bttComentarios(ActionEvent event) throws IOException {

		if (productoSeleccionado != null) {
			modelFactoryController.setProductoSeleccionado(productoSeleccionado);
			aplicacion.abrirGestinarComentariosProductos();
		}
	}

	@FXML
	void bttAgregarProducto(ActionEvent event) throws IOException {
		aplicacion.abrirAgregarProducto();

	}
	
	@FXML
	void bttAgregarVendedor(ActionEvent event) throws IOException {
		aplicacion.abrirAgregarVendedor();
	}

	public void setiarDatos() {
		Vendedor vendedor = modelFactoryController.getVendedorActual();
		lbNombre.setText("Nombre: " + vendedor.getNombre());
		lbApellido.setText("Apellido: " + vendedor.getApellido());
		lbDiresccion.setText("Direccion: " + vendedor.getDireccion());
		lbCedula.setText("Cedula: " + vendedor.getCedula());

	}

	@FXML
	void bttGestionarAliados(ActionEvent event) throws IOException {
		aplicacion.abrirGestinarAliados();

	}

	@FXML
	void initialize() {
		this.colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colApellidoProducto.setCellValueFactory(new PropertyValueFactory<>("estado"));
		tblListaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			productoSeleccionado = newSelection;
			cantidadMegusta.setText(String.valueOf(productoSeleccionado.getListaMegusta().size()));
		});

		this.colNombreAliado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colApellidoAliado.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		tablaVendedoresAliados.getSelectionModel().selectedItemProperty()
		.addListener((obs, oldSelection, newSelection) -> {
			vendedorAliadoSeleccionado = newSelection;
		});

		cargarListadoVendedoresAliados();
		cargarListadoProductos();
		setiarDatos();

		if(!modelFactoryController.getVendedorActual().getUsurio().equals("admin")){
			bttAgregarVendedor.setDisable(true);
			bttAgregarVendedor.setVisible(false);
		}
	}

	private void cargarListadoVendedoresAliados() {
		tablaVendedoresAliados.getItems().clear();
		tablaVendedoresAliados.setItems(obtenerVendedoresAliados());
	}

	private void cargarListadoProductos() {
		tblListaProductos.getItems().clear();
		tblListaProductos.setItems(obtenerProductos());
	}

	private ObservableList<Producto> obtenerProductos() {
		listadoProductos.addAll(modelFactoryController.obtenerProductos());
		return listadoProductos;
	}

	private ObservableList<Vendedor> obtenerVendedoresAliados() {
		listaVendedorAliado.addAll(modelFactoryController.obtenerVendedorAliado());
		return listaVendedorAliado;
	}

	@FXML
	void btnMeGusta(ActionEvent event) {
		gestionarMeGustaProducto();
	}

	private void gestionarMeGustaProducto() {
		if(productoSeleccionado!=null) {
			modelFactoryController.agregarMegustaProducto(productoSeleccionado);

		}else {
			modelFactoryController.mostrarMensaje("Notificacion Producto", "Producto no seleccionado", "debe seleccionar un producto el cual le gusta",
					AlertType.INFORMATION);
		}
	}



}
