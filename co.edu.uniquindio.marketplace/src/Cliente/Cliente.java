package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import co.edu.uniquindio.marketplace.controller.ModelFactoryController;
import co.edu.uniquindio.marketplace.model.Marketplace;


public class Cliente {

	 ModelFactoryController modelFactoryController;

	// puerto y host

	int puerto;
	String host;

	// socket cliente
	Socket socketComunicacion;

	// flujos de entrada y salida
	DataOutputStream flujoSalida;
	DataInputStream flujoEntrada;
	ObjectInputStream flujoEntradaObjeto;
	//ObjectOutputStream flujoSalidaObjeto;

	public Cliente(String host, int puerto, ModelFactoryController modelFactoryController) {
		this.puerto = puerto;
		this.host = host;
		this.modelFactoryController = modelFactoryController;
	}


//	public void iniciarCliente() {
//		try {
//			crearConexion();
//
//			flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
//			flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());
//			flujoEntradaObjeto = new ObjectInputStream(socketComunicacion.getInputStream());
//			//flujoSalidaObjeto = new ObjectOutputStream(socketComunicacion.getOutputStream());
//			enviarDatosPrimitivos(1);
//			recibirDatosPrimitivos();
//			flujoEntrada.close();
//			flujoEntradaObjeto.close();
//			socketComunicacion.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	public void cargarDatosApp() throws ClassNotFoundException {
		try {
			crearConexion();
			flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
			flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());
			flujoEntradaObjeto  = new ObjectInputStream(socketComunicacion.getInputStream());
			enviarDatosPrimitivos(2);
			System.out.println("Datos recibidos del servidor");
			Object pruebas=(Object)flujoEntradaObjeto.readObject();
			modelFactoryController.setMarketplace((Marketplace) pruebas);
			System.out.println("fin llamado markeplace");
			flujoEntrada.close();
			flujoEntradaObjeto.close();
			socketComunicacion.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void enviarDatosPrimitivos(int opcionServer) throws IOException {
		flujoSalida.writeInt(opcionServer);
	}


	private void recibirDatosPrimitivos() throws IOException {
		System.out.println("Datos recibidos del servidor: "+flujoEntrada.readUTF());
	}


	private void crearConexion() throws IOException {
		// TODO Auto-generated method stub
		socketComunicacion = new Socket(host,puerto);

	}


}
