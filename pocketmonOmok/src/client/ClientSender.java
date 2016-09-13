package client;

import java.io.ObjectOutputStream;

public class ClientSender {
	private ClientAccept clientAccept;
	private ObjectOutputStream clientOS;
	
	
	public ClientSender(ClientAccept accept) {
		this.clientAccept = accept;
		this.clientOS = accept.getClientOS();
	}


	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
	
	
}
