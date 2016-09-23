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
import datasDTO.AbstractEnumsDTO;
import datasDTO.GameRoomInfoVO;
import datasDTO.RoomAndUserListDTO;
import datasDTO.ServerMessageDTO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.ServerIPEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;

public class OmokServer {
	private ServerSocket serverSocket;
	private Socket socket;
	private Map<String, OmokPersonalServer> loginUsersMap;
	private List<GameRoomInfoVO> gameRoomList;
	private List<UserGamedataInfoDTO> userIDList;
	
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
		
		this.loginUsersMap = new HashMap<String, OmokPersonalServer>();
		this.gameRoomList  = new ArrayList<GameRoomInfoVO>();
		this.userIDList    = new ArrayList<UserGamedataInfoDTO>();
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
	public void login(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		// 클라이언트에게서 받은 데이터 DTO로 전환
		UserPersonalInfoDTO inputUserPersonalInfo = (UserPersonalInfoDTO) data;
		// DB에 아이디 패스워드를 보내 일치여부 결과 DTO에 저장
		UserPersonalInfoDTO resultDTO = this.loginDAO.checkIDMatchesPW(inputUserPersonalInfo);
		// 성공여부 클라이언트에게 전송
		personalServer.getServerOutputStream().writeObject(resultDTO);
		
		
		// 만약 클라이언트가 로그인 성공했다면
		if(resultDTO.getServerAction() == ServerActionEnum.LOGIN_SUCCESS) {
			// 서버가 가지고 있을 유저목록에 추가
			this.loginUsersMap.put(resultDTO.getUserID(), personalServer);
			// 사용자에게 보낼 현재 접속자 목록에 추가
			this.userIDList.add(gamedataDAO.getUserGrade(resultDTO));
			
			// 접속자리스트 와 게임방 리스트와 현재 접속자의 게임정보를 담아 클라이언트로 발송
			RoomAndUserListDTO roomAndUserListDTO = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			roomAndUserListDTO.setUserList(this.userIDList);

			roomAndUserListDTO.setGameRoomList(this.gameRoomList);
			roomAndUserListDTO.setUserGameData(this.gamedataDAO.userGameData(resultDTO));
			System.out.println("보내기 전 리스트 크기는 : " + roomAndUserListDTO.getUserList().size());
			
			roomAndUserListDTO.getUserGameData().setServerAction(ServerActionEnum.LOGIN_NEWUSER);
			for(String id : this.loginUsersMap.keySet()) {
				this.loginUsersMap.get(id).getServerOutputStream().writeObject(roomAndUserListDTO.getUserGameData());
			}

				
//			personalServer.getServerOutputStream().writeObject(roomAndUserListDTO);
			
		}
	}
	
	public void waitingRoom() {
		System.out.println("대기실");
	}
	
	
	//회원가입 프레임에서 넘어온 데이터
	public void join(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
		// 아이디 중복체크인 경우
		if(data.getUserAction() == UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK) {
			UserPersonalInfoDTO resultDTO = this.joinDAO.checkOverlapID(personalDTO);
			personalServer.getServerOutputStream().writeObject(resultDTO);
		
		// 회원가입인 경우
		} else {
			// 아이디가 중복되지 않는다면
			System.out.println(this.joinDAO.checkOverlapID(personalDTO).getUserID() == null);
			if(this.joinDAO.checkOverlapID(personalDTO).getUserID() == null) {
				ServerMessageDTO serverMessage = new ServerMessageDTO(UserPositionEnum.POSITION_JOIN);
				serverMessage.setUserAction(UserActionEnum.USER_JOIN_JOINACTION);
				// DB에 데이터 업데이트 
				int result = this.joinDAO.userJoin(personalDTO);
				
				// 성공적으로 업데이트 된 경우
				if(result == 1) {
					serverMessage.setServerAction(ServerActionEnum.JOIN_SUCCESS);

					// 업데이트 실패한 경우
				} else {
					serverMessage.setServerAction(ServerActionEnum.JOIN_FAIL);
				}
				personalServer.getServerOutputStream().writeObject(serverMessage);
			}
		}
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

	public void exitProgram(AbstractEnumsDTO index, OmokPersonalServer personalServer) throws IOException {
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
		return loginUsersMap;
	}
}
