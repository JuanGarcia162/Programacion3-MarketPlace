package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import co.edu.uniquindio.marketplace.controller.ModelFactoryController;
import co.edu.uniquindio.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.persistencia.Persistencia;


public class HiloClienteServidor extends  Thread{

	private DataInputStream flujoEntradaComunicacion;
	private DataOutputStream flujoSalidaComunicacion;
	private ObjectOutputStream flujoSalidaObjeto;
	Servidor servidor;
	private int tipoServicio;


	public void inicializar(DataInputStream flujoEntradaComunicacion, DataOutputStream flujoSalidaComunicacion,
			Servidor servidor, ObjectOutputStream flujoSalidaObjeto) {
		this.flujoEntradaComunicacion = flujoEntradaComunicacion;
		this.flujoSalidaComunicacion = flujoSalidaComunicacion;
		this.flujoSalidaObjeto = flujoSalidaObjeto;
		this.servidor = servidor;
	}

	@Override
	public void run() {

		try {
			tipoServicio = flujoEntradaComunicacion.readInt();
			switch (tipoServicio) {
			case 1:
				String mensajeBienvenida = "Bienvenido a la app";
				enviarMensajeBienvenida(mensajeBienvenida);
				break;
			case 2:
				String mensajeCargarDatos = "Entrar Datos Devolucion";
//				enviarMensajeBienvenida(mensajeCargarDatos);
//				enviarInformacion(5);
				System.out.println("Antes de enviarlo");
				System.out.println(Persistencia.cargarRecursoMarketplaceXML());
				Object example = Persistencia.cargarRecursoMarketplaceXML();
				flujoSalidaObjeto.writeObject(example);
				System.out.println("Se envio correctamente");
				break;
			case 3:
				break;

			default:
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	
	private void enviarMensajeBienvenida(String mensaje) {
		try {
			flujoSalidaComunicacion.writeUTF(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void enviarInformacion(int info) {
		try {
			flujoSalidaComunicacion.writeInt(info);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
