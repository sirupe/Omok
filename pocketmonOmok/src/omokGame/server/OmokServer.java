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
import datasDAO.UserStoreInfoDAO;
import datasDAO.UserStoreSkinInfoDAO;
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
	private UserPersonalInfoDAO loginDAO;
	private UserGamedataInfoDAO gamedataDAO;
	private UserStoreInfoDAO storeDAO;
	private UserStoreSkinInfoDAO skinDAO;
	
	private StonePositionCheck positionCheck;
	
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
	
	// 유저에게서 방생성하겠다 라는 메세지를 받으면 서버의 게임방리스트에 방을 추가해준 후 사용자에게 결과를 보내준다.
	public void waitingRoomCreateRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) throws IOException {
		GameRoomInfoVO gameRoomInfo = (GameRoomInfoVO)listDTO;
		GameRoomInfoVO pasteGameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		pasteGameRoomInfo.setGuest(gameRoomInfo.getGuest());
		pasteGameRoomInfo.setEnterImage(gameRoomInfo.getEnterImage().getDescription());
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
			userInGameRoom.setUserItemInfo(this.storeDAO.getUserStoreInfo(pasteGameRoomInfo.getOwner()));
			userInGameRoom.setUserSkinInfo(this.skinDAO.getUserSkinInfo(pasteGameRoomInfo.getOwner()));
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
		// 유저와 게스트의 성별 정보 업데이트
		userChoiceRoom.setOwner(userChoiceRoom.getOwner());
		userChoiceRoom.setGuest(userChoiceRoom.getGuest());
		
		// 게임룸 정보 업데이트
		for(int i = 0, size = this.gameRoomList.size(); i < size; i++) {
			if(this.gameRoomList.get(i).getOwner().equals(userChoiceRoom.getOwner())) {
				this.gameRoomList.get(i).setGuest(userChoiceRoom.getGuest());
				this.gameRoomList.get(i).setPersons(2);
				this.gameRoomList.get(i).setEnterImage(ImageEnum.WAITINGROOM_ENTER_IMPOSSIBLE.getImageDir());
				
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
				serverRoomVO.setEnterImage(vo.getEnterImage().getDescription());
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
	
	public void sendEnterRoomSuccessInfo() {
		// 데이터가 제대로 전송되고 있지 않기 때문에 모두 새로운 객체에 담아 전송한다.
		
		// 현재 서버에 저장되어있는 리스트를 roomList에 복사
		
		ArrayList<GameRoomInfoVO> roomList = new ArrayList<GameRoomInfoVO>();
		GameRoomInfoVO roomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		for(int i = 0, size = this.gameRoomList.size(); i < size; i++)  {
			roomVO.setGuest(this.gameRoomList.get(i).getGuest());
			roomVO.setEnterImage(this.gameRoomList.get(i).getEnterImage().getDescription());
			roomVO.setOwner(this.gameRoomList.get(i).getOwner());
			roomVO.setPersons(2);
			roomVO.setPwd(this.gameRoomList.get(i).getPwd());
			roomVO.setRoomName(this.gameRoomList.get(i).getRoomName());
			roomVO.setRoomNumber(this.gameRoomList.get(i).getRoomNumber());
			roomList.add(roomVO);
		}

		try {
			int ownerGender = this.loginDAO.getUserGender(roomVO.getOwner());
			int guestGender = this.loginDAO.getUserGender(roomVO.getGuest());
			
			// 모든 접속자에게 변경된 방 정보 전송(포지션 대기실)
			RoomAndUserListDTO roomListInfo = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			roomListInfo.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_LIST);
			roomListInfo.setGameRoomList(roomList);
			for(String user : this.loginUsersMap.keySet()) {
				this.loginUsersMap.get(user).getServerOutputStream().writeObject(roomListInfo);
			}
			
			// 각각 오너와 게스트에게 정보 전송(포지션 게임룸)
			GameRoomInfoVO roomOwnerVO = new GameRoomInfoVO(null);
			roomOwnerVO.setGuest(roomVO.getGuest());
			roomOwnerVO.setEnterImage(roomVO.getEnterImage().getDescription());
			roomOwnerVO.setOwner(roomVO.getOwner());
			roomOwnerVO.setPersons(2);
			roomOwnerVO.setPwd(roomVO.getPwd());
			roomOwnerVO.setRoomName(roomVO.getRoomName());
			roomOwnerVO.setRoomNumber(roomVO.getRoomNumber());
			UserInGameRoomDTO ownerGameRoomDTO = new UserInGameRoomDTO(UserPositionEnum.POSITION_GAME_ROOM);
			ownerGameRoomDTO.setGameRoomInfo(roomOwnerVO);
			ownerGameRoomDTO.setGuestGender(guestGender);
			ownerGameRoomDTO.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_OWNER);
			this.loginUsersMap.get(roomOwnerVO.getOwner()).getServerOutputStream().writeObject(ownerGameRoomDTO);
			
			GameRoomInfoVO roomGuestVO = new GameRoomInfoVO(null);
			roomGuestVO.setGuest(roomVO.getGuest());
			roomGuestVO.setEnterImage(roomVO.getEnterImage().getDescription());
			roomGuestVO.setOwner(roomVO.getOwner());
			roomGuestVO.setPersons(2);
			roomGuestVO.setPwd(roomVO.getPwd());
			roomGuestVO.setRoomName(roomVO.getRoomName());
			roomGuestVO.setRoomNumber(roomVO.getRoomNumber());
			UserInGameRoomDTO userInGameRoomDTO = new UserInGameRoomDTO(UserPositionEnum.POSITION_GAME_ROOM);
			System.out.println(roomGuestVO.getGuest());
			userInGameRoomDTO.setUserGameData(this.gamedataDAO.userGameData(roomGuestVO.getGuest()));
			userInGameRoomDTO.setGameRoomInfo(roomGuestVO);
			userInGameRoomDTO.setOwnerGender(ownerGender);
			userInGameRoomDTO.setUserItemInfo(this.storeDAO.getUserStoreInfo(roomGuestVO.getGuest()));
			userInGameRoomDTO.setUserSkinInfo(this.skinDAO.getUserSkinInfo(roomGuestVO.getGuest()));
			userInGameRoomDTO.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_GUEST);
			this.loginUsersMap.get(roomGuestVO.getGuest()).getServerOutputStream().writeObject(userInGameRoomDTO);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

//회원가입--------------------------------------------------------------------------
	public void join(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
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
			break;
		}
	}
	
	
	
	public void findID(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		// 클라이언트에게서 받은 데이터 DTO로 전환
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO) data;
		System.out.println("server data - > " + data.toString());
		
		// DB에 아이디 패스워드를 보내 일치여부 결과 DTO에 저장
		UserPersonalInfoDTO resultDTO = this.loginDAO.findUserID(personalDTO);
		
		System.out.println("server resultData - >" + resultDTO);
		
		ObjectOutputStream oos = personalServer.getServerOutputStream();
		
		oos.writeObject(resultDTO);
	}
	
	
	public void findPW() {
		System.out.println("비밀번호찾기");
	}
	//
//	public void findEmail(AbstractEnumsDTO data, OmokPersonalServer PersonalServer) throws IOException {
//		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
//		if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
//			UserPersonalInfoDTO resultDTO = (UserPersonalInfoDTO)data;
//			
//			String ConfirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
//			//메일 발송 -- 랜덤 번호와 resultDTO에 담긴 사용자 이메일로 보낸다..
//			new SendEmail(ConfirmNumber, resultDTO.getUserEmail());
//			
//			resultDTO.setCertificationNumber(ConfirmNumber);
//			resultDTO.setServerAction(ServerActionEnum.JOIN_CERTIFICATION);
//			PersonalServer.getServerOutputStream().writeObject(resultDTO);
//		}
//	}
	
//게임방----------------------------------------------------------------------------------------------------
	public void gameRoom(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		this.positionCheck = new StonePositionCheck();
		
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
			this.loginUsersMap.get(pasteMessageVO.getTargetID()).getServerOutputStream().writeObject(pasteMessageVO);
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
	
	public void stonePositionCheck(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		GameBoardVO gameBoard = (GameBoardVO)index;
		int inputBoard[][] = gameBoard.getGameBoard();
		int board[][] = new int[15][];
		
		int x = 0;
		int y = 0;
		
		for (int i = 0, iLen = board.length; i < iLen; i++) {
			board[i] = new int[15];
			for (int j = 0, jLen = board[0].length; j < jLen; j++) {
				board[i][j] = inputBoard[i][j];
				if(board[i][j] >= 3) {
					board[i][j] -= 2;
					x = i;
					y = j;
				}
			}
		}
		
		String nextTurnUser = gameBoard.getNextTurnUser();
		GameBoardVO sendGameBoardVO = new GameBoardVO(UserPositionEnum.POSITION_GAME_ROOM);
		sendGameBoardVO.setServerAction(ServerActionEnum.GAME_ROOM_WIN_CHECK);
		sendGameBoardVO.setGameBoard(board);
		sendGameBoardVO.setX(x);
		sendGameBoardVO.setY(y);
		sendGameBoardVO.setNextTurnUser(nextTurnUser);
		sendGameBoardVO.setNowTurnUser(gameBoard.getNowTurnUser());
		sendGameBoardVO.setWinUser(
			this.positionCheck.stoneDiagonalLeftCheck(x, y, board) < 5 ? 
				(this.positionCheck.stoneDiagonalRightCheck(x, y, board) < 5 ? 
					(this.positionCheck.stoneHeightCheck(x, y, board) < 5 ? 
						(this.positionCheck.stoneWidthCheck(x, y, board) < 5 ? null : gameBoard.getNowTurnUser()) 
					: gameBoard.getNowTurnUser()) 
				: gameBoard.getNowTurnUser()) 
			: gameBoard.getNowTurnUser()
		);
		
		try {
			personalServer.getServerOutputStream().writeObject(sendGameBoardVO);
			this.loginUsersMap.get(nextTurnUser).getServerOutputStream().writeObject(sendGameBoardVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
