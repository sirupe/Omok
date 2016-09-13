package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import enums.ServerIPEnum;
import frames.BasicFrame;

// 클라이언트 실행시 클라이언트 소켓 및 프레임 등등 생성
@SuppressWarnings("serial")
public class ClientAccept implements Serializable {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	// TODO 여기에 프레임 넣어줘용
	
	public ClientAccept() throws UnknownHostException, IOException {
		this.clientSocket = new Socket(ServerIPEnum.SERVER_IP.getServerIP(), ServerIPEnum.SERVER_PORT.getServerPort());
		this.clientOS = new ObjectOutputStream(this.clientSocket.getOutputStream());
		this.clientIS = new ObjectInputStream(this.clientSocket.getInputStream());
		ClientReciever reciever = new ClientReciever(this);
		reciever.start();
		ClientSender sender = new ClientSender(this);
		new BasicFrame(sender);
	}

	public ObjectInputStream getClientIS() {
		return clientIS;
	}

	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
	

}
