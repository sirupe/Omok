package client;

import java.io.ObjectInputStream;
// 인풋스트림을 지속적으로 읽어들인다.
public class ClientReciever extends Thread {
	private ClientAccept clientAccept;
	private ObjectInputStream clientIS;
	
	public ClientReciever(ClientAccept accept) {
		this.clientAccept = accept;
		this.clientIS = this.clientAccept.getClientIS();
	}
	
	//TODO 데이터가 넘나들게 될 것.
	@Override
	public void run() {
		while(true) {
			
		}
	}
	
	
}
