package omokGameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import enums.ServerIPEnum;

public class OmokServer {
	private ServerSocket serverSocket;
	private Socket socket;
	//TODO 입장한 클라이언트 저장할 컬렉션 추가 필요
	
	public OmokServer() throws IOException {
		this.serverSocket = new ServerSocket(ServerIPEnum.SERVER_PORT.getServerPort());
	}
	
	public void gameServerOn() throws IOException {
		System.out.println("Server On...");
		
		while(true) {
			System.out.println("Waiting User...");
			this.socket = this.serverSocket.accept();
			System.out.println("User Accept .. " + socket.getLocalAddress());
		}
	}
	
	//TODO 여기에 서버의 분기업무 추가
	
	public void login() {
		
	}
	
	public void waitingRoom() {
		
	}
	
	public void join() {
		
	}
	
	public void findID() {
		
	}
	
	public void findPW() {
		
	}
	
	public void gameRoom() {
		
	}
	
	public void store() {
		
	}
	
	public void otherUserInfo() {
		
	}
	
	public void modifyMyInfo() {
		
	}

	public Socket getSocket() {
		return socket;
	}
	
	
	
}
