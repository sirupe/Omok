package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import enums.ServerIPEnum;

public class ClientAccept {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	
	public ClientAccept() throws UnknownHostException, IOException {
		this.clientSocket = new Socket(ServerIPEnum.SERVER_IP.getServerIP(), ServerIPEnum.SERVER_PORT.getServerPort());
		this.clientIS = new ObjectInputStream(this.clientSocket.getInputStream());
		this.clientOS = new ObjectOutputStream(this.clientSocket.getOutputStream());
		//TODO 여기서 프레임 열기.
		new ClientReciever(this).start();
		

	}

	public ObjectInputStream getClientIS() {
		return clientIS;
	}
	

}
