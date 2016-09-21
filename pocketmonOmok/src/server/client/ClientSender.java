package server.client;

import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientSender implements Serializable {
	private ObjectOutputStream clientOS;
	
	
	public ClientSender(ClientAccept accept) {
		this.clientOS = accept.getClientOS();
	}


	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
	
	
}
