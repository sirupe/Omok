package omokGame.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import datasDAO.JoinDAO;
import datasDAO.UserPersonalInfoDAO;
import datasDAO.UserGamedataInfoDAO;
import datasDAO.UserStoreInfoDAO;
import datasDAO.UserStoreSkinInfoDAO;
import datasDTO.AbstractEnumsDTO;
import datasDTO.GameRoomInfoVO;
import datasDTO.RoomAndUserListDTO;
import datasDTO.ServerMessageDTO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserInGameRoomDTO;
import datasDTO.UserMessageVO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ImageEnum;
import enums.etc.ServerActionEnum;
import enums.etc.ServerIPEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import utility.SendEmail;

public class OmokServer {
	private ServerSocket serverSocket;
	private Socket socket;
	private HashMap<String, OmokPersonalServer> loginUsersMap;
	private ArrayList<GameRoomInfoVO> gameRoomList;
	private ArrayList<UserGamedataInfoDTO> userIDList;
	
	private JoinDAO joinDAO;
	private UserPersonalInfoDAO loginDAO;
	private UserGamedataInfoDAO gamedataDAO;
	private UserStoreInfoDAO storeDAO;
	private UserStoreSkinInfoDAO skinDAO;
	
	public OmokServer() throws IOException {
		this.serverSocket = new ServerSocket(ServerIPEnum.SERVER_PORT.getServerPort());
		this.joinDAO 	  = new JoinDAO();
		this.loginDAO 	  = new UserPersonalInfoDAO();
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
	
//로그인--------------------------------------------------------------------------
	public void login(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		// 클라이언트에게서 받은 데이터 DTO로 전환
		UserPersonalInfoDTO inputUserPersonalInfo = (UserPersonalInfoDTO) data;
		// DB에 아이디 패스워드를 보내 일치여부 결과 DTO에 저장
		UserPersonalInfoDTO resultDTO = this.loginDAO.checkIDMatchesPW(inputUserPersonalInfo);
		
		// 만약 클라이언트의 정보가 DB에 있다면 
		if(resultDTO.getServerAction() == ServerActionEnum.LOGIN_SUCCESS) {
			//클라이언트의 ID가 처음 인입된 것이라면
			if(this.loginUsersMap.get(resultDTO.getUserID()) == null) {
				// 서버가 가지고 있을 유저목록에 추가
				this.loginUsersMap.put(resultDTO.getUserID(), personalServer);
				// 사용자에게 보낼 현재 접속자 목록에 추가
				this.userIDList.add(gamedataDAO.getUserGrade(resultDTO));
			//클라이언트의 ID가 이미 접속자리스트에 존재한다면
			} else {
				resultDTO.setServerAction(ServerActionEnum.LOGIN_FAIL_OVERLAP_ACCEPT);
			}
		}
		personalServer.getServerOutputStream().writeObject(resultDTO);
	}
	
//대기실--------------------------------------------------------------------------
	public void waitingRoom(AbstractEnumsDTO dto, OmokPersonalServer personalServer) throws IOException {
		switch(dto.getUserAction()) {
		case USER_LOGIN_SUCCESS :
			this.waitingRoomLoginSuccess(dto, personalServer);
			break;
		case USER_CREATE_ROOM :
			this.waitingRoomCreateRoom(dto, personalServer);
			break;
		case USER_CONFIRM_USERINFO :
			this.waitingRoomConfirmUSerInfo(dto, personalServer);
			break;
		case USER_MESSAGE_DEFAULT :
			this.waitingRoomDefaultMessage(dto, personalServer);
			break;
		case USER_MESSAGE_SECRET :
			this.waitingRoomSecretMessage(dto, personalServer);
			break;
		case USER_ENTER_ROOM :
			this.waitingRoomEnterPossibleGameRoom(dto, personalServer);
			break;
		case USER_PRIVATE_ROOM_ENTER :
			this.waitingRoomEnterPrivateGameRoom(dto, personalServer);
			break;
		default :
			break;
		}
	}
	
	public void waitingRoomLoginSuccess(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) throws IOException {
		// 접속자리스트 와 게임방 리스트와 현재 접속자의 게임정보를 담아 클라이언트로 발송
		RoomAndUserListDTO roomAndUserListDTO = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
		roomAndUserListDTO.setServerAction(ServerActionEnum.WAITING_ROOM_ENTER);
		roomAndUserListDTO.setUserList(this.userIDList);
		
		roomAndUserListDTO.setGameRoomList(this.gameRoomList);
		roomAndUserListDTO.setUserGameData(this.gamedataDAO.userGameData(((UserPersonalInfoDTO)listDTO).getUserID()));
		personalServer.getServerOutputStream().writeObject(roomAndUserListDTO);
		
		// 현재 접속중인 유저들에게 새로운 접속자의 정보를 전송
		UserGamedataInfoDTO newUserDTO = roomAndUserListDTO.getUserGameData();
		newUserDTO.setPosition(UserPositionEnum.POSITION_WAITING_ROOM);
		newUserDTO.setServerAction(ServerActionEnum.LOGIN_NEW_USER);
		for(String id : this.loginUsersMap.keySet()) {
			if(!id.equals(roomAndUserListDTO.getUserGameData().getUserID())) {
				this.loginUsersMap.get(id).getServerOutputStream().writeObject(newUserDTO);
			}
		}
	}
	
	// 유저에게서 방생성하겠다 라는 메세지를 받으면 서버의 게임방리스트에 방을 추가해준 후 사용자에게 결과를 보내준다.
	// 방이 20개가 넘는다면 방만들기 실패.
	public void waitingRoomCreateRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) throws IOException {
		GameRoomInfoVO gameRoomInfo = (GameRoomInfoVO)listDTO;
		GameRoomInfoVO pasteGameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		pasteGameRoomInfo.setGuest(gameRoomInfo.getGuest());
		pasteGameRoomInfo.setImage(gameRoomInfo.getImage().getDescription());
		pasteGameRoomInfo.setOwner(gameRoomInfo.getOwner());
		pasteGameRoomInfo.setPersons(gameRoomInfo.getPersonNum());
		pasteGameRoomInfo.setPwd(gameRoomInfo.getPwd());
		pasteGameRoomInfo.setRoomName(gameRoomInfo.getRoomName());
		pasteGameRoomInfo.setRoomNumber(gameRoomInfo.getRoomNumber());
		
		if(this.gameRoomList.size() < 20) {
			this.gameRoomList.add(gameRoomInfo);
			UserInGameRoomDTO userInGameRoom = new UserInGameRoomDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			userInGameRoom.setServerAction(ServerActionEnum.GAME_CREATEROOM_SUCCESS);
			userInGameRoom.setGameRoomInfo(gameRoomInfo);
			userInGameRoom.setUserGameData(this.gamedataDAO.userGameData(gameRoomInfo.getOwner()));
			userInGameRoom.setUserItemInfo(this.storeDAO.getUserStoreInfo(gameRoomInfo.getOwner()));
			userInGameRoom.setUserSkinInfo(this.skinDAO.getUserSkinInfo(gameRoomInfo.getOwner()));
			personalServer.getServerOutputStream().writeObject(userInGameRoom);
		} else {
			gameRoomInfo.setServerAction(ServerActionEnum.GAME_CREATEROOM_FAIL);			
			personalServer.getServerOutputStream().writeObject(gameRoomInfo);
		}
		
		gameRoomInfo.setServerAction(ServerActionEnum.GAME_ROOM_ADD);
		for(String id : this.loginUsersMap.keySet()) {
			this.loginUsersMap.get(id).getServerOutputStream().writeObject(gameRoomInfo);
		}
	}
	
	// 다른유저 정보확인
	public void waitingRoomConfirmUSerInfo(AbstractEnumsDTO dto, OmokPersonalServer personalServer) throws IOException {
		UserGamedataInfoDTO gamedata = (UserGamedataInfoDTO)dto;
		UserGamedataInfoDTO userGamedata = this.gamedataDAO.userGameData(gamedata.getUserID());
		userGamedata.setServerAction(ServerActionEnum.OTHER_USER_INFO);
		personalServer.getServerOutputStream().writeObject(userGamedata);
	}
	
	// 유저가 메세지를 입력했다.
	public void waitingRoomDefaultMessage(AbstractEnumsDTO dto, OmokPersonalServer personalServer) throws IOException {
		UserMessageVO messageVO = (UserMessageVO)dto;
		
		StringBuffer message = new StringBuffer();
		message.append(messageVO.getUserID());
		message.append(" : ");
		message.append(messageVO.getMessage());
		
		messageVO.setServerAction(ServerActionEnum.MESSAGE_SEND_SUCCESS);
		messageVO.setMessage(message.toString());
		for(String id : this.loginUsersMap.keySet()) {
			this.loginUsersMap.get(id).getServerOutputStream().writeObject(messageVO);
		}
		
	}
	
	// 유저가 귓속말을 보냈다.
	public void waitingRoomSecretMessage(AbstractEnumsDTO dto, OmokPersonalServer personalServer) throws IOException {
		UserMessageVO messageVO = (UserMessageVO)dto;
		
		StringBuffer targetMessage = new StringBuffer();
		targetMessage.append(messageVO.getUserID());
		targetMessage.append(" 님의 귓속말 : ");
		targetMessage.append(messageVO.getMessage());
		
		StringBuffer userMessage = new StringBuffer();
		userMessage.append(messageVO.getUserID());
		userMessage.append(" 님에게 귓속말 : ");
		userMessage.append(messageVO.getMessage());
		
		messageVO.setServerAction(ServerActionEnum.MESSAGE_SEND_SUCCESS);
		
		messageVO.setMessage(targetMessage.toString());
		this.loginUsersMap.get(messageVO.getTargetID()).getServerOutputStream().writeObject(messageVO);
		
		messageVO.setMessage(userMessage.toString());
		this.loginUsersMap.get(messageVO.getUserID()).getServerOutputStream().writeObject(messageVO);
		
	}
	
	// 빈 방 접속 (TODO)
	public void waitingRoomEnterPossibleGameRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) {
		GameRoomInfoVO userChoiceRoom = (GameRoomInfoVO)listDTO;
		// 게임룸 정보 업데이트
		for(int i = 0, size = this.gameRoomList.size(); i < size; i++) {
			if(this.gameRoomList.get(i).getOwner().equals(userChoiceRoom.getOwner())) {
				this.gameRoomList.get(i).setGuest(userChoiceRoom.getGuest());
				this.gameRoomList.get(i).setPersons(2);
				this.gameRoomList.get(i).setImage(ImageEnum.WAITINGROOM_ENTER_IMPOSSIBLE.getImageDir());
				
				break;
			}
		}
		
		
		this.sendEnterRoomSuccessInfo();
	}
	
	// 빈, 비밀방 접속. 접속하지는 않고 비밀번호만 회신한다.
	public void waitingRoomEnterPrivateGameRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) {
		GameRoomInfoVO roomVO = (GameRoomInfoVO)listDTO;
		String owner = roomVO.getOwner();
		
		GameRoomInfoVO serverRoomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		for(GameRoomInfoVO vo : this.gameRoomList) {
			if(vo.getOwner().equals(owner)) {
				serverRoomVO.setGuest(vo.getGuest());
				serverRoomVO.setImage(vo.getImage().getDescription());
				serverRoomVO.setOwner(vo.getOwner());
				serverRoomVO.setPersons(vo.getPersonNum());
				serverRoomVO.setPwd(vo.getPwd());
				serverRoomVO.setRoomName(vo.getRoomName());
				serverRoomVO.setRoomNumber(vo.getRoomNumber());
				serverRoomVO.setServerAction(ServerActionEnum.ENTER_PRIVATE_GAME_ROOM);
				break;
			}
		}
		try {//TODO
			personalServer.getServerOutputStream().writeObject(serverRoomVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEnterRoomSuccessInfo() {
		// 데이터가 제대로 전송되고 있지 않기 때문에 모두 새로운 객체에 담아 전송한다.
		
		// 현재 서버에 저장되어있는 리스트를 roomList에 복사
		ArrayList<GameRoomInfoVO> roomList = new ArrayList<GameRoomInfoVO>();
		GameRoomInfoVO roomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		for(int i = 0, size = this.gameRoomList.size(); i < size; i++)  {
			roomVO.setGuest(this.gameRoomList.get(i).getGuest());
			roomVO.setImage(this.gameRoomList.get(i).getImage().getDescription());
			roomVO.setOwner(this.gameRoomList.get(i).getOwner());
			roomVO.setPersons(2);
			roomVO.setPwd(this.gameRoomList.get(i).getPwd());
			roomVO.setRoomName(this.gameRoomList.get(i).getRoomName());
			roomVO.setRoomNumber(this.gameRoomList.get(i).getRoomNumber());
			roomList.add(roomVO);
		}

		try {
			// 모든 접속자에게 변경된 방 정보 전송(포지션 대기실)
			RoomAndUserListDTO roomListInfo = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			roomListInfo.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_LIST);
			roomListInfo.setGameRoomList(roomList);
			for(String user : this.loginUsersMap.keySet()) {
				this.loginUsersMap.get(user).getServerOutputStream().writeObject(roomListInfo);
			}
			
			// 각각 오너와 게스트에게 정보 전송(포지션 게임룸)
			GameRoomInfoVO roomOwnerVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
			roomOwnerVO.setGuest(roomVO.getGuest());
			roomOwnerVO.setImage(roomVO.getImage().getDescription());
			roomOwnerVO.setOwner(roomVO.getOwner());
			roomOwnerVO.setPersons(2);
			roomOwnerVO.setPwd(roomVO.getPwd());
			roomOwnerVO.setRoomName(roomVO.getRoomName());
			roomOwnerVO.setRoomNumber(roomVO.getRoomNumber());
			roomOwnerVO.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_OWNER);
			this.loginUsersMap.get(roomOwnerVO.getOwner()).getServerOutputStream().writeObject(roomOwnerVO);
			
			GameRoomInfoVO roomGuestVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
			roomGuestVO.setGuest(roomVO.getGuest());
			roomGuestVO.setImage(roomVO.getImage().getDescription());
			roomGuestVO.setOwner(roomVO.getOwner());
			roomGuestVO.setPersons(2);
			roomGuestVO.setPwd(roomVO.getPwd());
			roomGuestVO.setRoomName(roomVO.getRoomName());
			roomGuestVO.setRoomNumber(roomVO.getRoomNumber());
			
			UserInGameRoomDTO userInGameRoomDTO = new UserInGameRoomDTO(UserPositionEnum.POSITION_GAME_ROOM);
			userInGameRoomDTO.setUserGameData(this.gamedataDAO.userGameData(roomGuestVO.getGuest()));
			userInGameRoomDTO.setGameRoomInfo(roomGuestVO);
			userInGameRoomDTO.setUserItemInfo(this.storeDAO.getUserStoreInfo(roomGuestVO.getGuest()));
			userInGameRoomDTO.setUserSkinInfo(this.skinDAO.getUserSkinInfo(roomGuestVO.getGuest()));
			userInGameRoomDTO.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_GUEST);
			System.out.println("게스트 유저 : " + roomGuestVO.getGuest());
			this.loginUsersMap.get(roomGuestVO.getGuest()).getServerOutputStream().writeObject(userInGameRoomDTO);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

//회원가입--------------------------------------------------------------------------
	public void join(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
		// 아이디 중복체크인 경우
		if(data.getUserAction() == UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK) {
			UserPersonalInfoDTO resultDTO = this.joinDAO.checkOverlapID(personalDTO);
			personalServer.getServerOutputStream().writeObject(resultDTO);
		
		// 인증번호 발송인 경우
		} else if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
			UserPersonalInfoDTO resultDTO = (UserPersonalInfoDTO)data;
			//인증번호 생성
			String confirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
			//이메일발송
			new SendEmail(confirmNumber, resultDTO.getUserEmail());
			
			resultDTO.setCertificationNumber(confirmNumber);
			resultDTO.setServerAction(ServerActionEnum.JOIN_CERTIFICATION);
			personalServer.getServerOutputStream().writeObject(resultDTO);
		// 회원가입인 경우
		} else {
			// 아이디가 중복되지 않는다면
			if(this.joinDAO.checkOverlapID(personalDTO).getUserID() == null) {
				ServerMessageDTO serverMessage = new ServerMessageDTO(UserPositionEnum.POSITION_JOIN);
				serverMessage.setUserAction(UserActionEnum.USER_JOIN_JOINACTION);
				// DB에 데이터 업데이트 
				int result = this.joinDAO.creatUserPersonalInfo(personalDTO);
				result += this.joinDAO.createUserGameDataInfo(personalDTO);
				result += this.joinDAO.createUserStoreInfo(personalDTO);
				result += this.joinDAO.createUserSkinInfo(personalDTO);
				// 성공적으로 업데이트 된 경우
				if(result == 4) {
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
	//
	public void findEmail(AbstractEnumsDTO data, OmokPersonalServer PersonalServer) throws IOException {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
		if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
			UserPersonalInfoDTO resultDTO = (UserPersonalInfoDTO)data;
			
			String ConfirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
			//메일 발송 -- 랜덤 번호와 resultDTO에 담긴 사용자 이메일로 보낸다..
			new SendEmail(ConfirmNumber, resultDTO.getUserEmail());
			
			resultDTO.setCertificationNumber(ConfirmNumber);
			resultDTO.setServerAction(ServerActionEnum.JOIN_CERTIFICATION);
			PersonalServer.getServerOutputStream().writeObject(resultDTO);
		}
	}
	public void gameRoom() {
		System.out.println("게임방");
	}
	
	public void store() {
		System.out.println("상점");
	}
	
	public void modifyMyInfo() {
		System.out.println("내정보보기");
	}
//게임종료---------------------------------------------------------------------------------------------------
	public void exitProgram(AbstractEnumsDTO index, OmokPersonalServer personalServer) throws IOException {
		System.out.println("프로그램 종료");
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)index;
		personalServer.getServerOutputStream().writeObject(index);
		
		for(int i = 0, size = this.userIDList.size(); i < size; i++) {
			if(personalDTO.getUserID().equals(this.userIDList.get(i).getUserID())) {
				this.userIDList.remove(i);
				break;
			}
		}
		
		
		this.loginUsersMap.remove(((UserPersonalInfoDTO)index).getUserID());
	
		personalServer.getServerOutputStream().close();
		personalServer.getServerInputStream().close();
		personalServer.getPersonalSocket().close();
		
		index.setServerAction(ServerActionEnum.OTHER_USER_EXIT);
		for(String id : this.loginUsersMap.keySet()) {
			this.loginUsersMap.get(id).getServerOutputStream().writeObject(index);
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public Map<String, OmokPersonalServer> getPsersonalServerMap() {
		return loginUsersMap;
	}


}
