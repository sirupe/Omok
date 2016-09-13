package omokGameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import enums.ServerIPEnum;

public class OmokServer {
	private ServerSocket serverSocket;
	private Socket socket;
	private List<OmokPersonalServer> psersonalServerList = new ArrayList<OmokPersonalServer>();
	//TODO 입장한 클라이언트 저장할 컬렉션 추가 필요
	
	public OmokServer() throws IOException {
		this.serverSocket = new ServerSocket(ServerIPEnum.SERVER_PORT.getServerPort());
	}
	
	public void gameServerOn() throws IOException {
		System.out.println("Server On...");
		
		while(true) {
			System.out.println("Waiting User...");
			this.socket = this.serverSocket.accept();
			OmokPersonalServer persomalServer = new OmokPersonalServer(this);
			this.psersonalServerList.add(persomalServer);
			persomalServer.start();
			System.out.println("now list size is " + this.psersonalServerList.size());
			System.out.println("User Accept .. " + socket.getLocalAddress());
		}
	}
	
	//TODO 여기에 서버의 분기업무 추가
	
	public void login() {
		System.out.println("로그인창");
	}
	
	public void waitingRoom() {
		System.out.println("대기실");
	}
	
	public void join() {
		System.out.println("회원가입");
	}
	
	public void findID() {
		System.out.println("ID찾기");
	}
	
	public void findPW() {
		System.out.println("비밀번호찾기");
	}
	
	public void gameRoom() {
		System.out.println("게임방");
	}
	
	public void store() {
		System.out.println("상점");
	}
	
	public void otherUserInfo() {
		System.out.println("다른사람정보보기");
	}
	
	public void modifyMyInfo() {
		System.out.println("내정보보기");
	}

	
	
	
	public Socket getSocket() {
		return socket;
	}
}
