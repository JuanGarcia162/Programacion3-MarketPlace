package co.edu.uniquindio.marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatVendedores implements Serializable{

	private static final long serialVersionUID = 1L;
	private Vendedor vendedor1;
	private Vendedor vendedor2;
	ArrayList<String>  miChat;
	
	public ChatVendedores(Vendedor vendedor1, Vendedor vendedor2, ArrayList<String> miChat) {
		super();
		this.vendedor1 = vendedor1;
		this.vendedor2 = vendedor2;
		this.miChat = miChat;
	}

	public Vendedor getVendedor1() {
		return vendedor1;
	}

	public void setVendedor1(Vendedor vendedor1) {
		this.vendedor1 = vendedor1;
	}

	public Vendedor getVendedor2() {
		return vendedor2;
	}

	public void setVendedor2(Vendedor vendedor2) {
		this.vendedor2 = vendedor2;
	}

	public ArrayList<String> getMiChat() {
		return miChat;
	}

	public void setMiChat(ArrayList<String> miChat) {
		this.miChat = miChat;
	}

}
