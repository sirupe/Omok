package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import enums.ServerIPEnum;

// 클라이언트 실행시 클라이언트 소켓 및 프레임 등등 생성
public class ClientAccept {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	// TODO 여기에 프레임 넣어줘용
	
	public ClientAccept() throws UnknownHostException, IOException {
		System.out.println("ClientAccept 생성자 실행");
		this.clientSocket = new Socket(ServerIPEnum.SERVER_IP.getServerIP(), ServerIPEnum.SERVER_PORT.getServerPort());
		System.out.println("소켓생성");
		this.clientOS = new ObjectOutputStream(this.clientSocket.getOutputStream());
		System.out.println("아웃풋 생성");
		//TODO 여기서 프레임 열기.
		System.out.println(this.clientSocket.getInputStream());
		this.clientIS = new ObjectInputStream(this.clientSocket.getInputStream());
		System.out.println("인풋 생성");
		ClientReciever reciever = new ClientReciever(this);
		reciever.start();
	}

//	public void recieverStart() {
//		new ClientReciever(this).start();
//	}
	
	public ObjectInputStream getClientIS() {
		return clientIS;
	}
	

}
