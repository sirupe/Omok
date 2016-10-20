package omokGame.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import datasDAO.JoinDAO;
import datasDAO.UserGamedataInfoDAO;
import datasDAO.UserPersonalInfoDAO;
import datasDTO.AbstractEnumsDTO;
import datasDTO.GameBoardVO;
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
	private UserPersonalInfoDAO userPersonalDAO;
	private UserGamedataInfoDAO gamedataDAO;
	
	public OmokServer() throws IOException {
		this.serverSocket = new ServerSocket(ServerIPEnum.SERVER_PORT.getServerPort());
		this.joinDAO 	  = new JoinDAO();
		this.userPersonalDAO = new UserPersonalInfoDAO();
		this.gamedataDAO  = new UserGamedataInfoDAO();
		
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
	public synchronized void login(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		// 클라이언트에게서 받은 데이터 DTO로 전환
		UserPersonalInfoDTO inputUserPersonalInfo = (UserPersonalInfoDTO) data;
		// DB에 아이디 패스워드를 보내 일치여부 결과 DTO에 저장
		UserPersonalInfoDTO resultDTO = this.userPersonalDAO.checkIDMatchesPW(inputUserPersonalInfo);
		
		// 만약 클라이언트의 정보가 DB에 있다면 
		if(resultDTO.getServerAction() == ServerActionEnum.LOGIN_SUCCESS) {
			//사용자정보가 탈퇴유저라면
			if(this.userPersonalDAO.loginDropUserCheck(inputUserPersonalInfo) == 0) {
				resultDTO.setServerAction(ServerActionEnum.LOGIN_DROP_USER);
			//클라이언트의 ID가 처음 인입된 것이라면
			} else if(this.loginUsersMap.get(resultDTO.getUserID()) == null) {
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
	public synchronized void waitingRoom(AbstractEnumsDTO dto, OmokPersonalServer personalServer) throws IOException {
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
	
	// 로그인 성공시
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
		userMessage.append(messageVO.getTargetID());
		userMessage.append(" 님에게 귓속말 : ");
		userMessage.append(messageVO.getMessage());
		
		messageVO.setServerAction(ServerActionEnum.MESSAGE_SEND_SUCCESS);
		
		messageVO.setMessage(targetMessage.toString());
		this.loginUsersMap.get(messageVO.getTargetID()).getServerOutputStream().writeObject(messageVO);
		
		messageVO.setMessage(userMessage.toString());
		this.loginUsersMap.get(messageVO.getUserID()).getServerOutputStream().writeObject(messageVO);
		
	}
	
	// 유저에게서 방생성하겠다 라는 메세지를 받으면 서버의 게임방리스트에 방을 추가해준 후 사용자에게 결과를 보내준다.
	public void waitingRoomCreateRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) throws IOException {
		GameRoomInfoVO gameRoomInfo = (GameRoomInfoVO)listDTO;
		GameRoomInfoVO pasteGameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		pasteGameRoomInfo.setGuest(gameRoomInfo.getGuest());
		pasteGameRoomInfo.setEnterImage(gameRoomInfo.getEnterImage());
		pasteGameRoomInfo.setOwner(gameRoomInfo.getOwner());
		pasteGameRoomInfo.setPersons(gameRoomInfo.getPersonNum());
		pasteGameRoomInfo.setPwd(gameRoomInfo.getPwd());
		pasteGameRoomInfo.setRoomName(gameRoomInfo.getRoomName());
		pasteGameRoomInfo.setRoomNumber(gameRoomInfo.getRoomNumber());
		// 방이 20개가 넘지 않는다면 생성 성공. 방을 생성한 유저에게 게임방 입장시 필요한 정보를 전송.
		// 방이 20개가 넘는다면 방만들기 실패.
		if(this.gameRoomList.size() < 20) {
			this.gameRoomList.add(pasteGameRoomInfo);
			UserInGameRoomDTO userInGameRoom = new UserInGameRoomDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			userInGameRoom.setServerAction(ServerActionEnum.GAME_CREATEROOM_SUCCESS);
			userInGameRoom.setGameRoomInfo(pasteGameRoomInfo);
			userInGameRoom.setUserGameData(this.gamedataDAO.userGameData(pasteGameRoomInfo.getOwner()));
			personalServer.getServerOutputStream().writeObject(userInGameRoom);
		} else {
			gameRoomInfo.setServerAction(ServerActionEnum.GAME_CREATEROOM_FAIL);			
			personalServer.getServerOutputStream().writeObject(gameRoomInfo);
		}
		
		// 방 생성 성공 후 접속한 모든 유저에게 생성된 방 정보 전송.
		gameRoomInfo.setServerAction(ServerActionEnum.GAME_ROOM_ADD);
		for(String id : this.loginUsersMap.keySet()) {
			this.loginUsersMap.get(id).getServerOutputStream().writeObject(gameRoomInfo);
		}
	}
	
	// 빈 방 접속 
	public void waitingRoomEnterPossibleGameRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) {
		GameRoomInfoVO userChoiceRoom = (GameRoomInfoVO)listDTO;
		GameRoomInfoVO pasteGameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		pasteGameRoomInfo.setGuest(userChoiceRoom.getGuest());
		pasteGameRoomInfo.setOwner(userChoiceRoom.getOwner());
		pasteGameRoomInfo.setPersons(2);
		pasteGameRoomInfo.setPwd(userChoiceRoom.getPwd());
		pasteGameRoomInfo.setRoomName(userChoiceRoom.getRoomName());
		pasteGameRoomInfo.setRoomNumber(userChoiceRoom.getRoomNumber());
		pasteGameRoomInfo.setEnterImage(ImageEnum.WAITINGROOM_ENTER_IMPOSSIBLE.getImageDir());
		
		// 게임룸 정보 업데이트
		for(int i = 0, size = this.gameRoomList.size(); i < size; i++) {
			if(this.gameRoomList.get(i).getOwner().equals(userChoiceRoom.getOwner())) {
				this.gameRoomList.set(i, pasteGameRoomInfo);				
				break;
			}
		}
		
		ArrayList<GameRoomInfoVO> roomList = new ArrayList<GameRoomInfoVO>();
		
		for(int i = 0, size = this.gameRoomList.size(); i < size; i++)  {
			GameRoomInfoVO roomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
			roomVO.setGuest(this.gameRoomList.get(i).getGuest());
			roomVO.setEnterImage(this.gameRoomList.get(i).getEnterImage());
			roomVO.setOwner(this.gameRoomList.get(i).getOwner());
			roomVO.setPersons(this.gameRoomList.get(i).getPersonNum());
			roomVO.setPwd(this.gameRoomList.get(i).getPwd());
			roomVO.setRoomName(this.gameRoomList.get(i).getRoomName());
			roomVO.setRoomNumber(this.gameRoomList.get(i).getRoomNumber());
			roomList.add(roomVO);
		}

		try {
			int ownerGender = this.userPersonalDAO.getUserGender(pasteGameRoomInfo.getOwner());
			int guestGender = this.userPersonalDAO.getUserGender(pasteGameRoomInfo.getGuest());
			
			// 모든 접속자에게 변경된 방 정보 전송(포지션 대기실)
			RoomAndUserListDTO roomListInfo = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			roomListInfo.setServerAction(ServerActionEnum.GAME_ROOM_LIST_MODIFY);
			roomListInfo.setGameRoomList(roomList);
			for(String user : this.loginUsersMap.keySet()) {
				this.loginUsersMap.get(user).getServerOutputStream().writeObject(roomListInfo);
			}
			
			// 각각 오너와 게스트에게 정보 전송(포지션 게임룸)
			GameRoomInfoVO roomOwnerVO = new GameRoomInfoVO(null);
			roomOwnerVO.setGuest(pasteGameRoomInfo.getGuest());
			roomOwnerVO.setEnterImage(pasteGameRoomInfo.getEnterImage());
			roomOwnerVO.setOwner(pasteGameRoomInfo.getOwner());
			roomOwnerVO.setPersons(2);
			roomOwnerVO.setPwd(pasteGameRoomInfo.getPwd());
			roomOwnerVO.setRoomName(pasteGameRoomInfo.getRoomName());
			roomOwnerVO.setRoomNumber(pasteGameRoomInfo.getRoomNumber());
			UserInGameRoomDTO ownerGameRoomDTO = new UserInGameRoomDTO(UserPositionEnum.POSITION_GAME_ROOM);
			ownerGameRoomDTO.setGameRoomInfo(roomOwnerVO);
			ownerGameRoomDTO.setGuestGender(guestGender);
			ownerGameRoomDTO.setOtherGameData(this.gamedataDAO.userGameData(pasteGameRoomInfo.getGuest()));
			ownerGameRoomDTO.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_OWNER);
			this.loginUsersMap.get(roomOwnerVO.getOwner()).getServerOutputStream().writeObject(ownerGameRoomDTO);
			
			GameRoomInfoVO roomGuestVO = new GameRoomInfoVO(null);
			roomGuestVO.setGuest(pasteGameRoomInfo.getGuest());
			roomGuestVO.setEnterImage(pasteGameRoomInfo.getEnterImage());
			roomGuestVO.setOwner(pasteGameRoomInfo.getOwner());
			roomGuestVO.setPersons(2);
			roomGuestVO.setPwd(pasteGameRoomInfo.getPwd());
			roomGuestVO.setRoomName(pasteGameRoomInfo.getRoomName());
			roomGuestVO.setRoomNumber(pasteGameRoomInfo.getRoomNumber());
			UserInGameRoomDTO userInGameRoomDTO = new UserInGameRoomDTO(UserPositionEnum.POSITION_GAME_ROOM);
			userInGameRoomDTO.setUserGameData(this.gamedataDAO.userGameData(roomGuestVO.getGuest()));
			userInGameRoomDTO.setGameRoomInfo(roomGuestVO);
			userInGameRoomDTO.setOwnerGender(ownerGender);
			userInGameRoomDTO.setGuestGender(guestGender);
			userInGameRoomDTO.setOtherGameData(this.gamedataDAO.userGameData(pasteGameRoomInfo.getOwner()));
			userInGameRoomDTO.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_GUEST);
			this.loginUsersMap.get(userChoiceRoom.getGuest()).getServerOutputStream().writeObject(userInGameRoomDTO);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 빈, 비밀방 접속. 접속하지는 않고 비밀번호만 회신한다.
	public void waitingRoomEnterPrivateGameRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) {
		GameRoomInfoVO roomVO = (GameRoomInfoVO)listDTO;
		String owner = roomVO.getOwner();
		
		GameRoomInfoVO serverRoomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		for(GameRoomInfoVO vo : this.gameRoomList) {
			if(vo.getOwner().equals(owner)) {
				serverRoomVO.setGuest(vo.getGuest());
				serverRoomVO.setEnterImage(vo.getEnterImage());
				serverRoomVO.setOwner(vo.getOwner());
				serverRoomVO.setPersons(vo.getPersonNum());
				serverRoomVO.setPwd(vo.getPwd());
				serverRoomVO.setRoomName(vo.getRoomName());
				serverRoomVO.setRoomNumber(vo.getRoomNumber());
				serverRoomVO.setServerAction(ServerActionEnum.ENTER_PRIVATE_GAME_ROOM);
				break;
			}
		}
		try {
			personalServer.getServerOutputStream().writeObject(serverRoomVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//회원가입--------------------------------------------------------------------------
	public synchronized void join(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
		
		UserPersonalInfoDTO resultPersonalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
		switch(data.getUserAction()) {
		// 아이디 중복체크인 경우
		case USER_JOIN_ID_OVERLAP_CHECK :
			UserPersonalInfoDTO resultDTO = this.joinDAO.checkOverlapID(personalDTO);
			personalServer.getServerOutputStream().writeObject(resultDTO);
			break;

		// 인증번호 발송인 경우
		case USER_JOIN_CERTIFICATION_CREATE :
			String confirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
			personalServer.setCertificationNumber(confirmNumber);
			
			resultPersonalDTO.setUserAction(UserActionEnum.USER_JOIN_CERTIFICATION_CREATE);
			//이메일발송
			new SendEmail(confirmNumber, personalDTO.getUserEmail());
			
			resultPersonalDTO.setServerAction(ServerActionEnum.JOIN_CERTIFICATION);
			personalServer.getServerOutputStream().writeObject(resultPersonalDTO);
			break;
			
		case USER_JOIN_CERTIFICATION_CHECK :
			String inputCertifNum = personalDTO.getCertificationNumber();
			String saveCertifNum  = personalServer.getCertificationNumber();
			resultPersonalDTO.setUserAction(
					inputCertifNum.equals(saveCertifNum) ?
					UserActionEnum.USER_JOIN_CERTIFICATION_SUCCESS : UserActionEnum.USER_JOIN_CERTIFICATION_FAIL);
			personalServer.getServerOutputStream().writeObject(resultPersonalDTO);
			break;

		//회원가입인 경우
		default :
			// 아이디가 중복되지 않는다면
			if(this.joinDAO.checkOverlapID(personalDTO).getUserID() == null) {
				ServerMessageDTO serverMessage = new ServerMessageDTO(UserPositionEnum.POSITION_JOIN);
				serverMessage.setUserAction(UserActionEnum.USER_JOIN_JOINACTION);
				// DB에 데이터 업데이트 
				// 관련 DB테이블은 총 2개이고 정상적으로 생성되면 1을 반환한다.
				// 두 테이블이 정상적으로 생성된 경우 result는 2가 될 것.
				int result = this.joinDAO.creatUserPersonalInfo(personalDTO);
				result += this.joinDAO.createUserGameDataInfo(personalDTO);
				// 성공적으로 업데이트 된 경우
				if(result == 2) {
					serverMessage.setServerAction(ServerActionEnum.JOIN_SUCCESS);
					// 업데이트 실패한 경우
				} else {
					serverMessage.setServerAction(ServerActionEnum.JOIN_FAIL);
				}
				personalServer.getServerOutputStream().writeObject(serverMessage);
			}
			break;
		}
	}
	
	
	
	public synchronized void findID(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		// 클라이언트에게서 받은 데이터 DTO로 전환
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO) data;
		
		// DB에 아이디 패스워드를 보내 일치여부 결과 DTO에 저장
		UserPersonalInfoDTO resultDTO = this.userPersonalDAO.findUserID(personalDTO);
		
		
		ObjectOutputStream oos = personalServer.getServerOutputStream();
		
		oos.writeObject(resultDTO);
	}
	

//패스워드 찾기---------------------------------------------------------------------------------------------------
	public synchronized void findPw(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {	
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO) data; //부모로 가져온걸 형변환
		UserPersonalInfoDTO resultDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		ObjectOutputStream oos;
		
		switch(data.getUserAction()) {
			
		//TODO
		 //아이디 이메일 체크
		case USER_SEARCH_ID_EMAIL_CHECK :
			UserPersonalInfoDTO resultDTOPersonal = this.userPersonalDAO.findUserPW(personalDTO);
			oos = personalServer.getServerOutputStream();
			oos.writeObject(resultDTOPersonal);
			break;
		//인증 번호 발송
		case USER_SEARCH_CERTIFICATION_CHECK :
			UserPersonalInfoDTO resultPersonalDTO = this.userPersonalDAO.findUserPW(personalDTO);
			
			if(resultPersonalDTO.getUserCount() > 0) {
				resultPersonalDTO.setUserAction(UserActionEnum.USER_SEARCH_CERTIFICATION_CHECK);
		
				String confirmNumber = String.valueOf(new Random().nextInt(90000) + 10000);
				personalServer.setCertificationNumber(confirmNumber);
			
				//이메일 발송
				try {
					new SendEmail(confirmNumber, personalDTO.getUserEmail());
					resultPersonalDTO.setEmailSuccess(true);
				} catch (Throwable e) {
					resultPersonalDTO.setEmailSuccess(false);
				}
			} else {
				resultPersonalDTO.setUserAction(UserActionEnum.USER_SEARCHPW_SERCHFAIL);
			}
			oos = personalServer.getServerOutputStream();
			oos.writeObject(resultPersonalDTO);
			break;
			
		//인증번호 비교
		case USER_SEARCH_PASSWORD_CERTIFICATION_NUMBER :
			String RandomNumber = personalServer.getCertificationNumber();
			String clientNumebr = personalDTO.getCertificationNumber();
			
			resultDTO.setUserAction(UserActionEnum.USER_SEARCH_PASSWORD_CERTIFICATION_NUMBER);
	
			if(RandomNumber.equals(clientNumebr)) {
				resultDTO.setCertificationNumber(true);
			} else {
				resultDTO.setCertificationNumber(false);
			}
			oos = personalServer.getServerOutputStream();
			oos.writeObject(resultDTO);
			break;
			
	
		// 변경된 패스워드확인. TODO
		case USER_SEARCH_PASSWD :
			System.out.println(personalDTO.getUserID());
			System.out.println(personalDTO.getUserPasswd());
			ServerMessageDTO serverMessage = new ServerMessageDTO(UserPositionEnum.POSITION_FIND_PW);
			serverMessage.setUserAction(UserActionEnum.USER_SEARCH_PASSWD);
			int result = this.userPersonalDAO.updateUserPasswd(personalDTO);
			
			if(result == 1) {
				serverMessage.setServerAction(ServerActionEnum.SEARCH_PASSWD_SUCCESS);
			} else {
				serverMessage.setServerAction(ServerActionEnum.SEARCH_PASSWD_SUCCESS);
			}	 		
			personalServer.getServerOutputStream().writeObject(serverMessage);
			
		default: //do nothing..
		}
	}


	
//게임방----------------------------------------------------------------------------------------------------
	public synchronized void gameRoom(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		switch(index.getUserAction()) {
		// 유저가 게임방에서 채팅을 보낼 떼
		case USER_IN_GAME_ROOM_CHATTING :
			this.inGameRoomChatting(index, personalServer);
			break;
		// 게임방 내의 게스트가 레디버튼 체크를 해제하면
		case USER_GUEST_READY_DECHECK : 
			this.guestReadyClick(index, personalServer);
			break;
		// 게임방 내의 게스트가 레디 버튼을 체크하면
		case USER_GUEST_READY_CHECK :
			this.guestReadyClick(index, personalServer);
			break;
		// 오너가 게임스타트를 했다!
		case USER_GAME_START :
			this.gameStart(index, personalServer);
			break;
		// 유저가 돌을 놨다.
		case USER_GAME_BOARD_INFO :
			this.stonePositionCheck(index, personalServer);
			break;
		// 유저가 방을 나가려 한다.
		case USER_GAME_ROOM_EXIT :
			this.userExitRoom(index, personalServer);
			break;
		default:
			break;
		}
	}
	
	public void inGameRoomChatting(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		UserMessageVO messageVO = (UserMessageVO)index;
		
		StringBuffer message = new StringBuffer();
		message.append(messageVO.getUserID());
		message.append(" : ");
		message.append(messageVO.getMessage());
		
		// 안정성을 위해 데이터 복사
		UserMessageVO pasteMessageVO = new UserMessageVO(UserPositionEnum.POSITION_GAME_ROOM);
		pasteMessageVO.setServerAction(ServerActionEnum.GAME_ROOM_USER_CHATTING);
		pasteMessageVO.setUserID(messageVO.getUserID());
		pasteMessageVO.setTargetID(messageVO.getTargetID());
		pasteMessageVO.setMessage(message.toString());
		
		// 복사한 데이터를 방 안에 있는 유저와 상대방에게만 전송함.
		try {
			personalServer.getServerOutputStream().writeObject(pasteMessageVO);
			if(pasteMessageVO.getTargetID() != null) {
				this.loginUsersMap.get(pasteMessageVO.getTargetID()).getServerOutputStream().writeObject(pasteMessageVO);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 게스트 유저가 레디를 눌렀으면 오너 유저에게 게스트가 레디를 눌렀다는 정보를 전송해준다.
	public void guestReadyClick(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		// 유저가 레디를 누른건지 체크를 해제한건지 확인하여 서버에서 보내는 정보를 바꿔준다.
		ServerActionEnum serverAction = index.getUserAction() == UserActionEnum.USER_GUEST_READY_CHECK ? 
				ServerActionEnum.GAME_ROOM_GUEST_READY_CHECK : ServerActionEnum.GAME_ROOM_GUEST_READY_DECHECK ;
		
		GameRoomInfoVO inputInfo = (GameRoomInfoVO)index;
			
		GameRoomInfoVO gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomInfo.setServerAction(serverAction);
		gameRoomInfo.setGuest(inputInfo.getGuest());
		gameRoomInfo.setOwner(inputInfo.getOwner());
		
		try {
			this.loginUsersMap.get(gameRoomInfo.getOwner()).getServerOutputStream().writeObject(gameRoomInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 게임이 시작되었다는 정보를 게스트와 오너에게 보내준다.
	public void gameStart(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		GameRoomInfoVO inputInfo = (GameRoomInfoVO)index;
		
		GameRoomInfoVO gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomInfo.setServerAction(ServerActionEnum.GAME_ROOM_GAME_START);
		gameRoomInfo.setOwner(inputInfo.getOwner());
		gameRoomInfo.setGuest(inputInfo.getGuest());
		
		try {
			this.loginUsersMap.get(gameRoomInfo.getGuest()).getServerOutputStream().writeObject(gameRoomInfo);
			personalServer.getServerOutputStream().writeObject(gameRoomInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 유저가 돌을 놓으면 클라이언트 측에서 위치 검사 후 서버에게 결과를 회신한다. 
	public void stonePositionCheck(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		GameBoardVO gameBoard = (GameBoardVO)index;
		int inputBoard[][] = gameBoard.getGameBoard();
		int board[][] = new int[15][];
		
		// 정보의 안정성을 위해 모든 값을 일일히 복사하여 저장시킨다.
		for (int i = 0, iLen = board.length; i < iLen; i++) {
			board[i] = new int[15];
			for (int j = 0, jLen = board[0].length; j < jLen; j++) {
				board[i][j] = inputBoard[i][j];
			}
		}
		
		// 서버에서 돌을 놓았다는 정보를 받아 현재 진행중인 유저와 다음턴 유저를 바꾸어주고
		// 정보를 게임 진행중인 두 유저에게 보낸다.
		String nextTurnUser = gameBoard.getNextTurnUser();
		String nowTurnUser  = gameBoard.getNowTurnUser();
		GameBoardVO sendGameBoardVO = new GameBoardVO(UserPositionEnum.POSITION_GAME_ROOM);
		if(gameBoard.getWinUser() == null) {
			sendGameBoardVO.setServerAction(ServerActionEnum.GAME_ROOM_SEND_BOARD_INFO);
		} else {
			// win 유저정보가 있다면 DAO 에 이긴 유저의 정보를 업데이트 한다.
			sendGameBoardVO.setServerAction(ServerActionEnum.GAME_ROOM_WINNER_INFO);
			sendGameBoardVO.setWinUser(gameBoard.getWinUser());
			sendGameBoardVO.setLoseUser(gameBoard.getLoseUser());
			this.gamedataDAO.winUserGameDataUpdate(sendGameBoardVO.getWinUser());
			this.gamedataDAO.loseUserGameDataUpdate(sendGameBoardVO.getLoseUser());
		}
		// 사용자에게 보낼 정보에 돌 5개를 놓은 유저의 정보가 있다면 승리정보, 아니라면 그냥 맵 업데이트 하는 정보(Enum)를 담는다.
		sendGameBoardVO.setGameBoard(board);
		sendGameBoardVO.setX(gameBoard.getX());
		sendGameBoardVO.setY(gameBoard.getY());
		sendGameBoardVO.setNextTurnUser(nowTurnUser);
		sendGameBoardVO.setNowTurnUser(nextTurnUser);

		try {
			personalServer.getServerOutputStream().writeObject(sendGameBoardVO);
			this.loginUsersMap.get(nextTurnUser).getServerOutputStream().writeObject(sendGameBoardVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 유저가 게임방을 나갔을 때.
	public void userExitRoom(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		GameRoomInfoVO gameRoomInfoVO = (GameRoomInfoVO)index;
		// 유저가 넘겨준 데이터 복사하여 사용
		GameRoomInfoVO pasteRoomInfoVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		pasteRoomInfoVO.setGuest(gameRoomInfoVO.getGuest());
		pasteRoomInfoVO.setOwner(gameRoomInfoVO.getOwner());
		pasteRoomInfoVO.setPersons(gameRoomInfoVO.getPersonNum());
		pasteRoomInfoVO.setPwd(gameRoomInfoVO.getPwd());
		pasteRoomInfoVO.setRoomName(gameRoomInfoVO.getRoomName());
		pasteRoomInfoVO.setRoomNumber(gameRoomInfoVO.getRoomNumber());
		
		try {			
			// 방이 없어진 것이 아니라면 남겨진 유저에게 정보를 전달해주어야 한다.
			if(pasteRoomInfoVO.getPersonNum() > 0) {
				for(int i = 0, len = this.gameRoomList.size(); i < len; i++) {
					if(this.gameRoomList.get(i).getRoomNumber() == gameRoomInfoVO.getRoomNumber()) {
						pasteRoomInfoVO.setEnterImage(pasteRoomInfoVO.getPwd() == null ? 
								ImageEnum.WAITINGROOM_ENTER_POSSIBLE.getImageDir() : ImageEnum.WAITINGROOM_ENTER_PRIVATE.getImageDir());
						this.gameRoomList.set(i, pasteRoomInfoVO);
						pasteRoomInfoVO.setServerAction(ServerActionEnum.GAME_ROOM_EXIT_OTHER_USER);
						this.loginUsersMap.get(pasteRoomInfoVO.getOwner()).getServerOutputStream().writeObject(pasteRoomInfoVO);
						break;
					}
				}

			// 방이 없어진 것이라면 현재 리스트에서 삭제하여야 한다.
			} else {
				// 유저가 모두 나갔다.
				for(int i = 0, len = this.gameRoomList.size(); i < len; i++) {
					if(this.gameRoomList.get(i).getRoomNumber() == gameRoomInfoVO.getRoomNumber()) {
						this.gameRoomList.remove(i);
						break;
					}
				}
			}
		// 방 리스트 정보 갱신 후 모든 유저에게 방리스트 갱신정보를 전달해주어야 한다.
			RoomAndUserListDTO roomList = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			
			ArrayList<GameRoomInfoVO> pasteGameRoomList = new ArrayList<GameRoomInfoVO>();
			for(GameRoomInfoVO roomInfo : this.gameRoomList) {
				GameRoomInfoVO pasteRoomInfo = new GameRoomInfoVO(null);
				pasteRoomInfo.setPosition(roomInfo.getPosition());
				pasteRoomInfo.setServerAction(roomInfo.getServerAction());
				pasteRoomInfo.setUserAction(roomInfo.getUserAction());
				pasteRoomInfo.setGuest(roomInfo.getGuest());
				pasteRoomInfo.setOwner(roomInfo.getOwner());
				pasteRoomInfo.setPersons(roomInfo.getPersonNum());
				pasteRoomInfo.setPwd(roomInfo.getPwd());
				pasteRoomInfo.setRoomName(roomInfo.getRoomName());
				pasteRoomInfo.setRoomNumber(roomInfo.getRoomNumber());
				pasteRoomInfo.setEnterImage(roomInfo.getEnterImage());
				pasteGameRoomList.add(pasteRoomInfo);
			}
			//TODO
			
			roomList.setServerAction(ServerActionEnum.GAME_ROOM_LIST_MODIFY);
			roomList.setGameRoomList(pasteGameRoomList);
			for(String userID : this.loginUsersMap.keySet()) {
				this.loginUsersMap.get(userID).getServerOutputStream().writeObject(roomList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void store() {
//	}
//개인정보 수정------------------------------------------------------------------------------------------------
	public synchronized void modifyMyInfo(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)index;
		UserPersonalInfoDTO resultDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_MODIFY_MY_INFO);
		try {
			switch(personalDTO.getUserAction()) {
			case USER_MODIFY_GET_MY_INFO :
				resultDTO = this.userPersonalDAO.getUserPersonalInfo(personalDTO.getUserID());
				resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_PERSONAL_INFO);
				break;
			
			case USER_MODIFY_UPDATE :
				String dbPasswd = this.userPersonalDAO.checkIDMatchesPW(personalDTO).getUserPasswd();
				
				// DB의 패스워드가 일치할 때
				if(dbPasswd != null) {
					int result = this.userPersonalDAO.updateUserInfo(personalDTO);
					// DB에 정상적으로 업데이트 됨
					if(result == 1) {
						resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_SUCCESS);
					
					// DB에 업데이트 실패
					} else {
						resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_FAIL);
					}
				// DB의 패스워드와 불일치
				} else {
					resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_PASSWD_FAIL);
				}
				break;
				
			case USER_MODIFY_DROP :
				resultDTO = this.userPersonalDAO.checkIDMatchesPW(personalDTO);
				if(resultDTO.getUserID() == null) {
					resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_DROPCHECK_FAIL);
				} else {
					resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_DROPCHECK_SUCCESS);
				}
				break;
			//회원탈퇴
			case USER_DROP_CERTAIN :
				int result = this.userPersonalDAO.userDropOut(personalDTO);
				if(result == 1) {
					resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_DROPOUT_SUCCESS);
				} else {
					resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_DROPOUT_FAIL);
				}
				break;
			default:
				break;
			}
			resultDTO.setPosition(UserPositionEnum.POSITION_MODIFY_MY_INFO);
			personalServer.getServerOutputStream().writeObject(resultDTO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
//게임종료---------------------------------------------------------------------------------------------------
	public synchronized void exitProgram(AbstractEnumsDTO index, OmokPersonalServer personalServer) throws IOException {
		System.out.println("프로그램 종료");
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)index;
		personalServer.getServerOutputStream().writeObject(index);
		
		if(personalDTO.getUserID() != null) {
			for(int i = 0, size = this.userIDList.size(); i < size; i++) {
				if(personalDTO.getUserID().equals(this.userIDList.get(i).getUserID())) {
					this.userIDList.remove(i);
					break;
				}
			}	
		}
		
		
		this.loginUsersMap.remove(((UserPersonalInfoDTO)index).getUserID());
	
		personalServer.getServerOutputStream().close();
		personalServer.getServerInputStream().close();
		personalServer.getPersonalSocket().close();
		
		index.setServerAction(ServerActionEnum.OTHERS_UER_EXIT);
		
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
