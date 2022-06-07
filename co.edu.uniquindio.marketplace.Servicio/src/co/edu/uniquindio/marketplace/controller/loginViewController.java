package co.edu.uniquindio.marketplace.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;


import co.edu.uniquindio.marketplace.aplication.Aplicacion;
import co.edu.uniquindio.marketplace.exepciones.vendedorExisteIngresarException;
import co.edu.uniquindio.marketplace.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class loginViewController {
	
	private Aplicacion aplicacion;
	 ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	public void setModelFactoryController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
	}

	@FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button bttIngresar;

	    @FXML
	    private Button bttCancelar;

	    @FXML
	    private TextField texUsuario;

	    @FXML
	    private TextField texContraseña;

	    @FXML
	    void bttIngresar(ActionEvent event) throws IOException, vendedorExisteIngresarException {
	    	String usuario=texUsuario.getText();
	    	String contraseña=texContraseña.getText();
	    	Vendedor vendedor=null;
	    	
	
	    	if (validarTexto(usuario) && validarTexto(contraseña)){
		        vendedor=modelFactoryController.validarIngraso(usuario, contraseña);
		    		if(vendedor != null && tipoUsuario(vendedor)) {
		    			aplicacion.abrirAgregarVendedor();
		    		}
		    		else {
		    			if(vendedor != null ) {
		    			aplicacion.abrirVendedor();
		    			}
	   		
		    		}
	    		
	   	}
		else {
			mostrarMensaje("Datos de acceso incompletos", null, "Asegurese de introducir todos los datos",
    				AlertType.ERROR);
	  	}
		
   	
	
    
  	}
	    
	    private void mostrarMensaje(String titulo, String head, String content, AlertType tipo) {
			Alert alerta = new Alert(null);
			alerta.setTitle(titulo);
			alerta.setHeaderText(head);
			alerta.setContentText(content);
			alerta.setAlertType(tipo);
			alerta.show();
		}
	  

	    private boolean tipoUsuario(Vendedor vendedor) {
	    	if (vendedor.getUsurio().equals("admin" )) {
	    		return true;
	    	}
			return false;
		}



		private boolean validarTexto(String contraseña) {
	    	if (contraseña != null && !contraseña.equalsIgnoreCase("admin")){
				return true;
			}
			return false;
		}



		@FXML
	    void bttCancelar(ActionEvent event) {
	    	salir();
	    }
	    public void salir() {
	    	
	    	 Stage stage =  (Stage) this.bttCancelar.getScene().getWindow();
	    	 stage.close();
	    }

	    @FXML
	    void initialize() {
	       
	    }


	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	

}
