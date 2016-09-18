package omokGameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBaseConnection.JoinDAO;
import dataBaseConnection.LoginDAO;
import dataBaseConnection.UserGamedataInfoDAO;
import dataBaseConnection.UserStoreInfoDAO;
import dataBaseConnection.UserStoreSkinInfoDAO;
import datas.UserPersonalInfoDTO;
import datas.UserPositionIndex;
import enums.ServerIPEnum;
import enums.UserPositionEnum;

public class OmokServer {
	private ServerSocket serverSocket;
	private Socket socket;
	private Map<String, OmokPersonalServer> psersonalServerMap = new HashMap<String, OmokPersonalServer>();
	
	private JoinDAO joinDAO;
	private LoginDAO loginDAO;
	private UserGamedataInfoDAO gamedataDAO;
	private UserStoreInfoDAO storeDAO;
	private UserStoreSkinInfoDAO skinDAO;
	
	public OmokServer() throws IOException {
		this.serverSocket = new ServerSocket(ServerIPEnum.SERVER_PORT.getServerPort());
		this.joinDAO 	  = new JoinDAO();
		this.loginDAO 	  = new LoginDAO();
		this.gamedataDAO  = new UserGamedataInfoDAO();
		this.storeDAO	  = new UserStoreInfoDAO();
		this.skinDAO	  = new UserStoreSkinInfoDAO();
	}
	
	public void gameServerOn() throws IOException {
		System.out.println("Server On...");
		
		while(true) {
			System.out.println("Waiting User...");
			this.socket = this.serverSocket.accept();
			OmokPersonalServer personalServer = new OmokPersonalServer(this, this.socket);
			personalServer.start();
			System.out.println("User Accept .. " + socket.getLocalAddress());
		}
	}
	
	//TODO 여기에 서버의 분기업무 추가
	
	
	public void login(UserPositionIndex index, OmokPersonalServer personalServer) {
		UserPersonalInfoDTO inputUserPersonalInfo = (UserPersonalInfoDTO) index;
		UserPersonalInfoDTO outputUserPersonalInfo = this.loginDAO.checkIDMatchesPW(inputUserPersonalInfo);
		outputUserPersonalInfo.setPosition(UserPositionEnum.POSITION_LOGIN);
		System.out.println(outputUserPersonalInfo.getUserID());
		try {
			personalServer.getServerOutputStream().writeObject(outputUserPersonalInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void waitingRoom() {
		System.out.println("대기실");
	}
	
	public void join(UserPositionIndex index, OmokPersonalServer personalServer) {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)index;
//		personalDTO.get
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

	public void exitProgram(UserPositionIndex index, OmokPersonalServer personalServer) throws IOException {
		System.out.println("프로그램 종료");
		personalServer.getServerOutputStream().writeObject(index);
		
		personalServer.getServerOutputStream().close();
		personalServer.getServerInputStream().close();
		personalServer.getPersonalSocket().close();
	}
	
	
	public Socket getSocket() {
		return socket;
	}
	
	public Map<String, OmokPersonalServer> getPsersonalServerMap() {
		return psersonalServerMap;
	}
}
