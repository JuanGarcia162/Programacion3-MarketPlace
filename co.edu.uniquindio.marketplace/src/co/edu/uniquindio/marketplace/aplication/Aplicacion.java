package co.edu.uniquindio.marketplace.aplication;

import java.io.IOException;
import java.io.Serializable;

import co.edu.uniquindio.marketplace.controller.AgregarProductoController;
import co.edu.uniquindio.marketplace.controller.AgregarVendedorController;
import co.edu.uniquindio.marketplace.controller.ComentariosController;
import co.edu.uniquindio.marketplace.controller.GestionarAliadosController;
import co.edu.uniquindio.marketplace.controller.loginViewController;
import co.edu.uniquindio.marketplace.controller.vendedorViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Aplicacion extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("market");
		mostrarventanaPrincipal();
	}

	private void mostrarventanaPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/edu/uniquindio/marketplace/view/loginView.fxml"));
			AnchorPane rootLayout = loader.load();
			loginViewController loginController = loader.getController();
			loginController.setAplicacion(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void abrirAgregarVendedor() throws IOException {
		FXMLLoader fxmlLouder = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace/view/agregarVendedorView.fxml"));
		Parent root2 = (Parent) fxmlLouder.load();
		AgregarVendedorController vcc = (AgregarVendedorController) fxmlLouder.getController();
		vcc.setAplicacion(this);
		Stage stage = new Stage();
		stage.setScene(new Scene(root2));
		stage.show();
	}
	public void abrirVendedor() throws IOException {
		FXMLLoader fxmlLouder = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace/view/vendedorView.fxml"));
		Parent root2 = (Parent) fxmlLouder.load();
		vendedorViewController vcc = (vendedorViewController) fxmlLouder.getController();
		vcc.setAplicacion(this);
		Stage stage = new Stage();
		stage.setScene(new Scene(root2));
		stage.show();
	}
	public void abrirAgregarProducto() throws IOException {
		FXMLLoader fxmlLouder = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace/view/agregarProductoView.fxml"));
		Parent root2 = (Parent) fxmlLouder.load();
		AgregarProductoController vcc = (AgregarProductoController) fxmlLouder.getController();
		vcc.setAplicacion(this);
		Stage stage = new Stage();
		stage.setScene(new Scene(root2));
		stage.show();
	}
	
	public void abrirGestinarAliados() throws IOException {
		FXMLLoader fxmlLouder = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace/view/gestinarAliados.fxml"));
		Parent root2 = (Parent) fxmlLouder.load();
		GestionarAliadosController vcc = (GestionarAliadosController) fxmlLouder.getController();
		vcc.setAplicacion(this);
		Stage stage = new Stage();
		stage.setScene(new Scene(root2));
		stage.show();
	}
	public void abrirGestinarComentariosProductos() throws IOException {
		FXMLLoader fxmlLouder = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace/view/comentariosView.fxml"));
		Parent root2 = (Parent) fxmlLouder.load();
		ComentariosController vcc = (ComentariosController) fxmlLouder.getController();
		vcc.setAplicacion(this);
		Stage stage = new Stage();
		stage.setScene(new Scene(root2));
		stage.show();
	}
}