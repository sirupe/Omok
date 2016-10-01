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
	
//�α���--------------------------------------------------------------------------
	public void login(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		// Ŭ���̾�Ʈ���Լ� ���� ������ DTO�� ��ȯ
		UserPersonalInfoDTO inputUserPersonalInfo = (UserPersonalInfoDTO) data;
		// DB�� ���̵� �н����带 ���� ��ġ���� ��� DTO�� ����
		UserPersonalInfoDTO resultDTO = this.loginDAO.checkIDMatchesPW(inputUserPersonalInfo);
		
		// ���� Ŭ���̾�Ʈ�� ������ DB�� �ִٸ� 
		if(resultDTO.getServerAction() == ServerActionEnum.LOGIN_SUCCESS) {
			//Ŭ���̾�Ʈ�� ID�� ó�� ���Ե� ���̶��
			if(this.loginUsersMap.get(resultDTO.getUserID()) == null) {
				// ������ ������ ���� ������Ͽ� �߰�
				this.loginUsersMap.put(resultDTO.getUserID(), personalServer);
				// ����ڿ��� ���� ���� ������ ��Ͽ� �߰�
				this.userIDList.add(gamedataDAO.getUserGrade(resultDTO));
			//Ŭ���̾�Ʈ�� ID�� �̹� �����ڸ���Ʈ�� �����Ѵٸ�
			} else {
				resultDTO.setServerAction(ServerActionEnum.LOGIN_FAIL_OVERLAP_ACCEPT);
			}
		}
		personalServer.getServerOutputStream().writeObject(resultDTO);
	}
	
//����--------------------------------------------------------------------------
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
		// �����ڸ���Ʈ �� ���ӹ� ����Ʈ�� ���� �������� ���������� ��� Ŭ���̾�Ʈ�� �߼�
		RoomAndUserListDTO roomAndUserListDTO = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
		roomAndUserListDTO.setServerAction(ServerActionEnum.WAITING_ROOM_ENTER);
		roomAndUserListDTO.setUserList(this.userIDList);
		
		roomAndUserListDTO.setGameRoomList(this.gameRoomList);
		roomAndUserListDTO.setUserGameData(this.gamedataDAO.userGameData(((UserPersonalInfoDTO)listDTO).getUserID()));
		personalServer.getServerOutputStream().writeObject(roomAndUserListDTO);
		
		// ���� �������� �����鿡�� ���ο� �������� ������ ����
		UserGamedataInfoDTO newUserDTO = roomAndUserListDTO.getUserGameData();
		newUserDTO.setPosition(UserPositionEnum.POSITION_WAITING_ROOM);
		newUserDTO.setServerAction(ServerActionEnum.LOGIN_NEW_USER);
		for(String id : this.loginUsersMap.keySet()) {
			if(!id.equals(roomAndUserListDTO.getUserGameData().getUserID())) {
				this.loginUsersMap.get(id).getServerOutputStream().writeObject(newUserDTO);
			}
		}
	}
	
	// �ٸ����� ����Ȯ��
	public void waitingRoomConfirmUSerInfo(AbstractEnumsDTO dto, OmokPersonalServer personalServer) throws IOException {
		UserGamedataInfoDTO gamedata = (UserGamedataInfoDTO)dto;
		UserGamedataInfoDTO userGamedata = this.gamedataDAO.userGameData(gamedata.getUserID());
		userGamedata.setServerAction(ServerActionEnum.OTHER_USER_INFO);
		personalServer.getServerOutputStream().writeObject(userGamedata);
	}
	
	// ������ �޼����� �Է��ߴ�.
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
	
	// ������ �ӼӸ��� ���´�.
	public void waitingRoomSecretMessage(AbstractEnumsDTO dto, OmokPersonalServer personalServer) throws IOException {
		UserMessageVO messageVO = (UserMessageVO)dto;
		
		StringBuffer targetMessage = new StringBuffer();
		targetMessage.append(messageVO.getUserID());
		targetMessage.append(" ���� �ӼӸ� : ");
		targetMessage.append(messageVO.getMessage());
		
		StringBuffer userMessage = new StringBuffer();
		userMessage.append(messageVO.getUserID());
		userMessage.append(" �Կ��� �ӼӸ� : ");
		userMessage.append(messageVO.getMessage());
		
		messageVO.setServerAction(ServerActionEnum.MESSAGE_SEND_SUCCESS);
		
		messageVO.setMessage(targetMessage.toString());
		this.loginUsersMap.get(messageVO.getTargetID()).getServerOutputStream().writeObject(messageVO);
		
		messageVO.setMessage(userMessage.toString());
		this.loginUsersMap.get(messageVO.getUserID()).getServerOutputStream().writeObject(messageVO);
		
	}
	
	// �������Լ� ������ϰڴ� ��� �޼����� ������ ������ ���ӹ渮��Ʈ�� ���� �߰����� �� ����ڿ��� ����� �����ش�.
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
		
		// ���� 20���� ���� �ʴ´ٸ� ���� ����. ���� ������ �������� ���ӹ� ����� �ʿ��� ������ ����.
		// ���� 20���� �Ѵ´ٸ� �游��� ����.
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
		
		// �� ���� ���� �� ������ ��� �������� ������ �� ���� ����.
		gameRoomInfo.setServerAction(ServerActionEnum.GAME_ROOM_ADD);
		for(String id : this.loginUsersMap.keySet()) {
			this.loginUsersMap.get(id).getServerOutputStream().writeObject(gameRoomInfo);
		}
	}
	
	// �� �� ���� 
	public void waitingRoomEnterPossibleGameRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) {
		
		GameRoomInfoVO userChoiceRoom = (GameRoomInfoVO)listDTO;
		// ������ �Խ�Ʈ�� ���� ���� ������Ʈ
		userChoiceRoom.setOwner(userChoiceRoom.getOwner());
		userChoiceRoom.setGuest(userChoiceRoom.getGuest());
		
		// ���ӷ� ���� ������Ʈ
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
	
	// ��, ��й� ����. ���������� �ʰ� ��й�ȣ�� ȸ���Ѵ�.
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
		// �����Ͱ� ����� ���۵ǰ� ���� �ʱ� ������ ��� ���ο� ��ü�� ��� �����Ѵ�.
		
		// ���� ������ ����Ǿ��ִ� ����Ʈ�� roomList�� ����
		
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
			
			// ��� �����ڿ��� ����� �� ���� ����(������ ����)
			RoomAndUserListDTO roomListInfo = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			roomListInfo.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_LIST);
			roomListInfo.setGameRoomList(roomList);
			for(String user : this.loginUsersMap.keySet()) {
				this.loginUsersMap.get(user).getServerOutputStream().writeObject(roomListInfo);
			}
			
			// ���� ���ʿ� �Խ�Ʈ���� ���� ����(������ ���ӷ�)
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
	

//ȸ������--------------------------------------------------------------------------
	public void join(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
		
		UserPersonalInfoDTO resultPersonalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
		switch(data.getUserAction()) {
		// ���̵� �ߺ�üũ�� ���
		case USER_JOIN_ID_OVERLAP_CHECK :
			UserPersonalInfoDTO resultDTO = this.joinDAO.checkOverlapID(personalDTO);
			personalServer.getServerOutputStream().writeObject(resultDTO);
			break;

		// ������ȣ �߼��� ���
		case USER_JOIN_CERTIFICATION_CREATE :
			String confirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
			personalServer.setCertificationNumber(confirmNumber);
			
			resultPersonalDTO.setUserAction(UserActionEnum.USER_JOIN_CERTIFICATION_CREATE);
			//�̸��Ϲ߼�
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

		//ȸ�������� ���
		default :
			// ���̵� �ߺ����� �ʴ´ٸ�
			if(this.joinDAO.checkOverlapID(personalDTO).getUserID() == null) {
				ServerMessageDTO serverMessage = new ServerMessageDTO(UserPositionEnum.POSITION_JOIN);
				serverMessage.setUserAction(UserActionEnum.USER_JOIN_JOINACTION);
				// DB�� ������ ������Ʈ 
				int result = this.joinDAO.creatUserPersonalInfo(personalDTO);
				result += this.joinDAO.createUserGameDataInfo(personalDTO);
				result += this.joinDAO.createUserStoreInfo(personalDTO);
				result += this.joinDAO.createUserSkinInfo(personalDTO);
				// ���������� ������Ʈ �� ���
				if(result == 4) {
					serverMessage.setServerAction(ServerActionEnum.JOIN_SUCCESS);
					// ������Ʈ ������ ���
				} else {
					serverMessage.setServerAction(ServerActionEnum.JOIN_FAIL);
				}
				personalServer.getServerOutputStream().writeObject(serverMessage);
			}
			break;
		}
	}
	
	
	
	public void findID(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		// Ŭ���̾�Ʈ���Լ� ���� ������ DTO�� ��ȯ
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO) data;
		System.out.println("server data - > " + data.toString());
		
		// DB�� ���̵� �н����带 ���� ��ġ���� ��� DTO�� ����
		UserPersonalInfoDTO resultDTO = this.loginDAO.findUserID(personalDTO);
		
		System.out.println("server resultData - >" + resultDTO);
		
		ObjectOutputStream oos = personalServer.getServerOutputStream();
		
		oos.writeObject(resultDTO);
	}
	
	
	public void findPW() {
		System.out.println("��й�ȣã��");
	}
	//
//	public void findEmail(AbstractEnumsDTO data, OmokPersonalServer PersonalServer) throws IOException {
//		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
//		if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
//			UserPersonalInfoDTO resultDTO = (UserPersonalInfoDTO)data;
//			
//			String ConfirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
//			//���� �߼� -- ���� ��ȣ�� resultDTO�� ��� ����� �̸��Ϸ� ������..
//			new SendEmail(ConfirmNumber, resultDTO.getUserEmail());
//			
//			resultDTO.setCertificationNumber(ConfirmNumber);
//			resultDTO.setServerAction(ServerActionEnum.JOIN_CERTIFICATION);
//			PersonalServer.getServerOutputStream().writeObject(resultDTO);
//		}
//	}
	
//���ӹ�----------------------------------------------------------------------------------------------------
	public void gameRoom(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		this.positionCheck = new StonePositionCheck();
		
		switch(index.getUserAction()) {
		// ������ ���ӹ濡�� ä���� ���� ��
		case USER_IN_GAME_ROOM_CHATTING :
			this.inGameRoomChatting(index, personalServer);
			break;
		// ���ӹ� ���� �Խ�Ʈ�� �����ư üũ�� �����ϸ�
		case USER_GUEST_READY_DECHECK : 
			this.guestReadyClick(index, personalServer);
			break;
		// ���ӹ� ���� �Խ�Ʈ�� ���� ��ư�� üũ�ϸ�
		case USER_GUEST_READY_CHECK :
			this.guestReadyClick(index, personalServer);
			break;
		// ���ʰ� ���ӽ�ŸƮ�� �ߴ�!
		case USER_GAME_START :
			this.gameStart(index, personalServer);
			break;
		// ������ ���� ����.
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
		
		// �������� ���� ������ ����
		UserMessageVO pasteMessageVO = new UserMessageVO(UserPositionEnum.POSITION_GAME_ROOM);
		pasteMessageVO.setServerAction(ServerActionEnum.GAME_ROOM_USER_CHATTING);
		pasteMessageVO.setUserID(messageVO.getUserID());
		pasteMessageVO.setTargetID(messageVO.getTargetID());
		pasteMessageVO.setMessage(message.toString());
		
		// ������ �����͸� �� �ȿ� �ִ� ������ ���濡�Ը� ������.
		try {
			personalServer.getServerOutputStream().writeObject(pasteMessageVO);
			this.loginUsersMap.get(pasteMessageVO.getTargetID()).getServerOutputStream().writeObject(pasteMessageVO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// �Խ�Ʈ ������ ���� �������� ���� �������� �Խ�Ʈ�� ���� �����ٴ� ������ �������ش�.
	public void guestReadyClick(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		// ������ ���� �������� üũ�� �����Ѱ��� Ȯ���Ͽ� �������� ������ ������ �ٲ��ش�.
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
	
	// ������ ���۵Ǿ��ٴ� ������ �Խ�Ʈ�� ���ʿ��� �����ش�.
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
		System.out.println("����");
	}
	
	public void modifyMyInfo() {
		System.out.println("����������");
	}
//��������---------------------------------------------------------------------------------------------------
	public void exitProgram(AbstractEnumsDTO index, OmokPersonalServer personalServer) throws IOException {
		System.out.println("���α׷� ����");
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
