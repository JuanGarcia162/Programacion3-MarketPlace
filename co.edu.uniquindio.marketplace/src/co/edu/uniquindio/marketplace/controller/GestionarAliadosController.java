package co.edu.uniquindio.marketplace.controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uniquindio.marketplace.aplication.Aplicacion;
import co.edu.uniquindio.marketplace.model.Solicitud;
import co.edu.uniquindio.marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionarAliadosController{

	private Aplicacion aplicacion;
	FilteredList<Vendedor> filteredVendedorData;
	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lbIdentificador;

	@FXML
	private TableColumn<Vendedor, String> colNomSugerencia;

	@FXML
	private TableView<Vendedor> tblEnviarSugerencia;

	@FXML
	private Button bttAceptar;

	@FXML
	private Button bttRechazar;

	@FXML
	private TableColumn<Vendedor, String> colNombreSolicitud;

	@FXML
	private TableColumn<Vendedor, String> colApellidoSolicitud;

	@FXML
	private Button bttEnviarSugerencia;

	@FXML
	private TableView<Solicitud> tblSolicitud;

	@FXML
	private TableColumn<Vendedor, String> colNomApellido;

	@FXML
	private TextField texBusqueda;

	@FXML
	private Button bttBuscar;

	Vendedor vendedorSeleccionado;
	Solicitud solicitudSeleccionada;

	ObservableList<Vendedor> listadoVendedoresSugeridos = FXCollections.observableArrayList();
	ObservableList<Vendedor> listadoVendedores = FXCollections.observableArrayList();
	ObservableList<Vendedor> listadoVendedoresSugeridosFiltro = FXCollections.observableArrayList();
	ObservableList<Solicitud> listadoSolicutud = FXCollections.observableArrayList();

	@FXML
	void bttBuscar(ActionEvent event) {
		filtrarNombre();
	}

	@FXML
	void bttAceptar(ActionEvent event) {
		AceptarSolicitud();
	}

	private void AceptarSolicitud() {
		modelFactoryController.aceptarSolicitud(solicitudSeleccionada);
		cargarListadoSolicitudes();
	}

	@FXML
	void bttRechazar(ActionEvent event) {
		rechazarSolicitud();
	}

	private void rechazarSolicitud() {
		modelFactoryController.rechazarSolicitud(solicitudSeleccionada);
		cargarListadoSolicitudes();
	}

	@FXML
	void bttEnviarSugerencia(ActionEvent event) {
		enviarSolicitud();
		listadoVendedores.remove(vendedorSeleccionado);
		cargarListadoSugeridos();
	}

	@FXML
	void initialize() {

		this.colNomSugerencia.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colNomApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

		this.colNombreSolicitud.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colApellidoSolicitud.setCellValueFactory(new PropertyValueFactory<>("apellido"));

		tblSolicitud.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			solicitudSeleccionada = newSelection;
		});

		tblEnviarSugerencia.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {

					vendedorSeleccionado = newSelection;
				});
		cargarListadoSolicitudes();
		cargarListadoSugeridos();
	}

	private void enviarSolicitud() {
		Vendedor vendedor2 = vendedorSeleccionado;

		modelFactoryController.enviarSilicitud(vendedor2);
		cargarListadoSolicitudes();

	}

	@FXML
	private void filtrarNombre() {

		String filtroNombre = this.texBusqueda.getText();
		if (!filtroNombre.isEmpty()) {
			lbIdentificador.setText("VENDEDORES BUSCADOS");
			cargarListadoVendedores();
				// Limpio la lista
				this.listadoVendedoresSugeridosFiltro.clear();

				for (Vendedor p : this.listadoVendedores) {
					if (p.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) {
						this.listadoVendedoresSugeridosFiltro.add(p);
					}
				}
				this.tblEnviarSugerencia.setItems(listadoVendedoresSugeridosFiltro);
			
		} else {
			cargarListadoSugeridos();
			lbIdentificador.setText("VENDEDORES SUGERIDOS");
		}

	}

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	private void cargarListadoVendedores() {
		tblEnviarSugerencia.getItems().clear();
		tblEnviarSugerencia.setItems(obtenerVendedores());
		tblEnviarSugerencia.refresh();

	}

	private ObservableList<Vendedor> obtenerVendedores() {
		ArrayList<Vendedor> vendedores = modelFactoryController.obtenerVendedores();
		listadoVendedores.clear();		
		listadoVendedores.addAll(vendedores);		
		listadoVendedores.remove(modelFactoryController.getVendedorActual());
		for (Solicitud solicitud : modelFactoryController.getVendedorActual().getListaSolicitudes()) {
			listadoVendedores.remove(solicitud.getVendedorEnviado());
		}
		for (Solicitud solicitud : modelFactoryController.getVendedorActual().getListaSolicitudesEnivadas()) {
			listadoVendedores.remove(solicitud.getVendedorResivido());
		}
//		for (Vendedor miVendedorActual : listadoVendedores) {
//			if (miVendedorActual.getUsurio().equals("admin")) {
//				listadoVendedores.remove(miVendedorActual);
//			}
//		}
		
		return listadoVendedores;
	}

	private void cargarListadoSolicitudes() {
		tblSolicitud.getItems().clear();
		tblSolicitud.setItems(obtenerSolicitudes());
		tblSolicitud.refresh();

	}

	private ObservableList<Solicitud> obtenerSolicitudes() {
		listadoSolicutud.clear();

		listadoSolicutud.addAll(modelFactoryController.getVendedorActual().getListaSolicitudes());

		return listadoSolicutud;
	}

	private void cargarListadoSugeridos() {
		tblEnviarSugerencia.getItems().clear();
		tblEnviarSugerencia.setItems(obtenerListaSugeridos());

	}

	public ObservableList<Vendedor> obtenerListaSugeridos() {
		ArrayList<Vendedor> lista = new ArrayList<>();
		for (Vendedor vendedor : modelFactoryController.getVendedorActual().getListaVendedores()) {
			for (Vendedor sugeridos : vendedor.getListaVendedores()) {
				if (!volidarSugerido(sugeridos)) {
					if (!validarExistenciSugerido(sugeridos, lista)) {
						lista.add(sugeridos);
					}
				}
			}
		}
		listadoVendedoresSugeridos.addAll(lista);
		return listadoVendedoresSugeridos;

	}

	private boolean volidarSugerido(Vendedor sugeridos) {
		for (Vendedor vendedor : modelFactoryController.getVendedorActual().getListaVendedores()) {
			if (vendedor == sugeridos) {
				return true;
			}
		}
		return false;
	}

	private boolean validarExistenciSugerido(Vendedor sugeridos, ArrayList<Vendedor> lista) {
		boolean existencia = false;
		for (Vendedor vendedor : lista) {
			if (sugeridos == vendedor) {
				existencia = true;
			}
		}
		return existencia;
	}
}
