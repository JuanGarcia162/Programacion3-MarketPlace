package co.edu.uniquindio.marketplace.controller;

import java.net.URL;

import java.util.ResourceBundle;

import co.edu.uniquindio.marketplace.aplication.Aplicacion;
import co.edu.uniquindio.marketplace.model.Comentarios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

public class ComentariosController {

	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	private ResourceBundle resources;
    
	@FXML
	private URL location;

	@FXML
	private TableColumn<Comentarios, String> colApellido;

	@FXML
	private TableView<Comentarios> tblComentarios;

	@FXML
	private TableColumn<Comentarios, String> colNombre;
	
	@FXML
	private TextField texComentarios;

	@FXML
	private Button butComenmtar;

	@FXML
	private TableColumn<Comentarios, String> colComentario;

	private Aplicacion aplicacion;

	Comentarios comentarioSeleccionado;

	ObservableList<Comentarios> listadoComentarios = FXCollections.observableArrayList();

	@FXML
	void initialize() {
		this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.colComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));

		tblComentarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			comentarioSeleccionado = newSelection;
		});
		
		cargarListadoProductos();
	}

	private void cargarListadoProductos() {
		tblComentarios.getItems().clear();
		tblComentarios.setItems(obtenerProductos());
		tblComentarios.refresh();
	}

	private ObservableList<Comentarios> obtenerProductos() {
		listadoComentarios.addAll(modelFactoryController.obtenerComemtarios());
		return listadoComentarios;
	}

	@FXML
	void butComenmtar(ActionEvent event) {
		String comentario = texComentarios.getText();

		modelFactoryController.agregarComentario(comentario);
		cargarListadoProductos();
	}

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		cargarListadoProductos();
	}
}
