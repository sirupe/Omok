package omokGame.client;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ClientSender {
	private ObjectOutputStream clientOS;
	
	
	public ClientSender(ClientAccept accept) {
		this.clientOS = accept.getClientOS();
	}


	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
	
	
}
