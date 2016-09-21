package server.omokGameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datasDAO.JoinDAO;
import datasDAO.LoginDAO;
import datasDAO.UserGamedataInfoDAO;
import datasDAO.UserStoreInfoDAO;
import datasDAO.UserStoreSkinInfoDAO;
import datasDTO.UserPersonalInfoDTO;
import datasDTO.AbstractEnumsDTO;
import enums.etc.ServerIPEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;

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
	
	//TODO ���⿡ ������ �б���� �߰�
	
	
	public void login(AbstractEnumsDTO data, OmokPersonalServer personalServer) {
		UserPersonalInfoDTO inputUserPersonalInfo = (UserPersonalInfoDTO) data;
		UserPersonalInfoDTO resultDTO = this.loginDAO.checkIDMatchesPW(inputUserPersonalInfo);
		this.sendObject(resultDTO, personalServer);
	}
	
	public void waitingRoom() {
		System.out.println("����");
	}
	
	
	public void join(AbstractEnumsDTO data, OmokPersonalServer personalServer) {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
		if(data.getUserAction() == UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK) {
			UserPersonalInfoDTO resultDTO = this.joinDAO.checkOverlapID(personalDTO);
			this.sendObject(resultDTO, personalServer);
		} else {
			if(this.joinDAO.checkOverlapID(personalDTO).getUserID() == null) {
				
			}
			
			
			int result = this.joinDAO.userJoin(personalDTO);
			if(result == 1) {
				
			} else {
				
			}
		}
	}
	
	public void findID() {
		System.out.println("IDã��");
	}
	
	public void findPW() {
		System.out.println("��й�ȣã��");
	}
	
	public void gameRoom() {
		System.out.println("���ӹ�");
	}
	
	public void store() {
		System.out.println("����");
	}
	
	public void otherUserInfo() {
		System.out.println("�ٸ������������");
	}
	
	public void modifyMyInfo() {
		System.out.println("����������");
	}

	public void exitProgram(AbstractEnumsDTO index, OmokPersonalServer personalServer) throws IOException {
		System.out.println("���α׷� ����");
		personalServer.getServerOutputStream().writeObject(index);
		
		personalServer.getServerOutputStream().close();
		personalServer.getServerInputStream().close();
		personalServer.getPersonalSocket().close();
	}
	
	public void sendObject(AbstractEnumsDTO data, OmokPersonalServer personalServer) {
		try {
			personalServer.getServerOutputStream().writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public Map<String, OmokPersonalServer> getPsersonalServerMap() {
		return psersonalServerMap;
	}
}