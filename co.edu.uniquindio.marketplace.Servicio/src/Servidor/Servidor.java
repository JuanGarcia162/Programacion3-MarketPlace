package Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {


	ServerSocket serverComunicacion;
	int puerto;

	private DataInputStream flujoEntradaComunicacion;
	private DataOutputStream flujoSalidaComunicacion;
	private ObjectOutputStream flujoSalidaObjeto;
	//private ObjectInputStream flujoEntradaObjeto;

	public Servidor(int puerto) {
		this.puerto = puerto;
	}


	public void runServer() throws IOException{

		serverComunicacion = new ServerSocket(puerto);

		while(true){

			System.out.println("<----------------Servidor iniciado----------------------------");
			Socket socketComunicacion = null;

			socketComunicacion = serverComunicacion.accept();

			System.out.println("Conexion establecida");

			flujoEntradaComunicacion = new DataInputStream(socketComunicacion.getInputStream());
			flujoSalidaComunicacion = new DataOutputStream(socketComunicacion.getOutputStream());
			flujoSalidaObjeto = new ObjectOutputStream(socketComunicacion.getOutputStream());
		//	flujoEntradaObjeto = new ObjectInputStream(socketComunicacion.getInputStream());
			
			iniciarHiloClienteServidor();
		}

	}

	private void iniciarHiloClienteServidor() {
		HiloClienteServidor hiloClienteServidor = new HiloClienteServidor();
		hiloClienteServidor.inicializar(flujoEntradaComunicacion,flujoSalidaComunicacion,this,flujoSalidaObjeto);
		hiloClienteServidor.start();
	}


}