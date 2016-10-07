package omokGame.server;

import java.io.IOException;
import java.io.ObjectInputStream;
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
	private UserPersonalInfoDAO userPersonalDAO;
	private UserGamedataInfoDAO gamedataDAO;
	
	public OmokServer() throws IOException {
		this.serverSocket = new ServerSocket(ServerIPEnum.SERVER_PORT.getServerPort());
		this.joinDAO 	  = new JoinDAO();
		this.userPersonalDAO 	  = new UserPersonalInfoDAO();
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
	
//�α���--------------------------------------------------------------------------
	public void login(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		// Ŭ���̾�Ʈ���Լ� ���� ������ DTO�� ��ȯ
		UserPersonalInfoDTO inputUserPersonalInfo = (UserPersonalInfoDTO) data;
		// DB�� ���̵� �н����带 ���� ��ġ���� ��� DTO�� ����
		UserPersonalInfoDTO resultDTO = this.userPersonalDAO.checkIDMatchesPW(inputUserPersonalInfo);
		
		// ���� Ŭ���̾�Ʈ�� ������ DB�� �ִٸ� 
		if(resultDTO.getServerAction() == ServerActionEnum.LOGIN_SUCCESS) {
			//����������� Ż���������
			if(this.userPersonalDAO.loginDropUserCheck(inputUserPersonalInfo) == 0) {
				System.out.println("�׷��ù߿����ӵ��´ٴ°ž�?");
				resultDTO.setServerAction(ServerActionEnum.LOGIN_DROP_USER);
			//Ŭ���̾�Ʈ�� ID�� ó�� ���Ե� ���̶��
			} else if(this.loginUsersMap.get(resultDTO.getUserID()) == null) {
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
		GameRoomInfoVO pasteGameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		pasteGameRoomInfo.setGuest(userChoiceRoom.getGuest());
		pasteGameRoomInfo.setOwner(userChoiceRoom.getOwner());
		pasteGameRoomInfo.setPersons(2);
		pasteGameRoomInfo.setPwd(userChoiceRoom.getPwd());
		pasteGameRoomInfo.setRoomName(userChoiceRoom.getRoomName());
		pasteGameRoomInfo.setRoomNumber(userChoiceRoom.getRoomNumber());
		pasteGameRoomInfo.setEnterImage(ImageEnum.WAITINGROOM_ENTER_IMPOSSIBLE.getImageDir());
		
		// ���ӷ� ���� ������Ʈ
		for(int i = 0, size = this.gameRoomList.size(); i < size; i++) {
			if(this.gameRoomList.get(i).getOwner().equals(userChoiceRoom.getOwner())) {
				this.gameRoomList.set(i, pasteGameRoomInfo);				
				break;
			}
		}
		
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
			int ownerGender = this.userPersonalDAO.getUserGender(pasteGameRoomInfo.getOwner());
			int guestGender = this.userPersonalDAO.getUserGender(pasteGameRoomInfo.getGuest());
			
			// ��� �����ڿ��� ����� �� ���� ����(������ ����)
			RoomAndUserListDTO roomListInfo = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			roomListInfo.setServerAction(ServerActionEnum.GAME_ROOM_LIST_MODIFY);
			roomListInfo.setGameRoomList(roomList);
			for(String user : this.loginUsersMap.keySet()) {
				this.loginUsersMap.get(user).getServerOutputStream().writeObject(roomListInfo);
			}
			
			// ���� ���ʿ� �Խ�Ʈ���� ���� ����(������ ���ӷ�)
			GameRoomInfoVO roomOwnerVO = new GameRoomInfoVO(null);
			roomOwnerVO.setGuest(pasteGameRoomInfo.getGuest());
			roomOwnerVO.setEnterImage(pasteGameRoomInfo.getEnterImage().getDescription());
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
			roomGuestVO.setEnterImage(pasteGameRoomInfo.getEnterImage().getDescription());
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
			//TODO ���⼭ �Խ�Ʈ�� ����.
			this.loginUsersMap.get(userChoiceRoom.getGuest()).getServerOutputStream().writeObject(userInGameRoomDTO);	
		} catch (IOException e) {
			e.printStackTrace();
		}
//		this.sendEnterRoomSuccessInfo(); TODO �ι��� ���� �ִ� �����̹Ƿ� ���������� ������ ������ �����ϵ��� �Ѵ�.
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
	
//	public void sendEnterRoomSuccessInfo() {
//		System.out.println("sendEnterRoomSuccessInfo");
//		// �����Ͱ� ����� ���۵ǰ� ���� �ʱ� ������ ��� ���ο� ��ü�� ��� �����Ѵ�.
//		
//		// ���� ������ ����Ǿ��ִ� ����Ʈ�� roomList�� ����
//		
//		ArrayList<GameRoomInfoVO> roomList = new ArrayList<GameRoomInfoVO>();
//		GameRoomInfoVO roomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
//		for(int i = 0, size = this.gameRoomList.size(); i < size; i++)  {
//			roomVO.setGuest(this.gameRoomList.get(i).getGuest());
//			roomVO.setEnterImage(this.gameRoomList.get(i).getEnterImage().getDescription());
//			roomVO.setOwner(this.gameRoomList.get(i).getOwner());
//			roomVO.setPersons(2);
//			roomVO.setPwd(this.gameRoomList.get(i).getPwd());
//			roomVO.setRoomName(this.gameRoomList.get(i).getRoomName());
//			roomVO.setRoomNumber(this.gameRoomList.get(i).getRoomNumber());
//			roomList.add(roomVO);
//		}
//
//		try {
//			int ownerGender = this.userPersonalDAO.getUserGender(roomVO.getOwner());
//			int guestGender = this.userPersonalDAO.getUserGender(roomVO.getGuest());
//			
//			// ��� �����ڿ��� ����� �� ���� ����(������ ����)
//			RoomAndUserListDTO roomListInfo = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
//			roomListInfo.setServerAction(ServerActionEnum.GAME_ROOM_LIST_MODIFY);
//			roomListInfo.setGameRoomList(roomList);
//			for(String user : this.loginUsersMap.keySet()) {
//				this.loginUsersMap.get(user).getServerOutputStream().writeObject(roomListInfo);
//			}
//			
//			// ���� ���ʿ� �Խ�Ʈ���� ���� ����(������ ���ӷ�)
//			GameRoomInfoVO roomOwnerVO = new GameRoomInfoVO(null);
//			roomOwnerVO.setGuest(roomVO.getGuest());
//			roomOwnerVO.setEnterImage(roomVO.getEnterImage().getDescription());
//			roomOwnerVO.setOwner(roomVO.getOwner());
//			roomOwnerVO.setPersons(2);
//			roomOwnerVO.setPwd(roomVO.getPwd());
//			roomOwnerVO.setRoomName(roomVO.getRoomName());
//			roomOwnerVO.setRoomNumber(roomVO.getRoomNumber());
//			UserInGameRoomDTO ownerGameRoomDTO = new UserInGameRoomDTO(UserPositionEnum.POSITION_GAME_ROOM);
//			ownerGameRoomDTO.setGameRoomInfo(roomOwnerVO);
//			ownerGameRoomDTO.setGuestGender(guestGender);
//			ownerGameRoomDTO.setOtherGameData(this.gamedataDAO.userGameData(roomVO.getGuest()));
//			ownerGameRoomDTO.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_OWNER);
//			this.loginUsersMap.get(roomOwnerVO.getOwner()).getServerOutputStream().writeObject(ownerGameRoomDTO);
//			
//			GameRoomInfoVO roomGuestVO = new GameRoomInfoVO(null);
//			roomGuestVO.setGuest(roomVO.getGuest());
//			roomGuestVO.setEnterImage(roomVO.getEnterImage().getDescription());
//			roomGuestVO.setOwner(roomVO.getOwner());
//			roomGuestVO.setPersons(2);
//			roomGuestVO.setPwd(roomVO.getPwd());
//			roomGuestVO.setRoomName(roomVO.getRoomName());
//			roomGuestVO.setRoomNumber(roomVO.getRoomNumber());
//			UserInGameRoomDTO userInGameRoomDTO = new UserInGameRoomDTO(UserPositionEnum.POSITION_GAME_ROOM);
//			userInGameRoomDTO.setUserGameData(this.gamedataDAO.userGameData(roomGuestVO.getGuest()));
//			userInGameRoomDTO.setGameRoomInfo(roomGuestVO);
//			userInGameRoomDTO.setOwnerGender(ownerGender);
//			userInGameRoomDTO.setOtherGameData(this.gamedataDAO.userGameData(roomVO.getOwner()));
//			userInGameRoomDTO.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_GUEST);
//			System.out.println("1.��VO : " + roomVO.getGuest());
//			System.out.println("2.��Խ�ƮVO : " + roomGuestVO.getGuest());
//			System.out.println("3.��������Ʈ�� : " + this.loginUsersMap.get(roomGuestVO.getGuest()));
//			System.out.println(userInGameRoomDTO == null);
//			this.loginUsersMap.get(roomGuestVO.getGuest()).getServerOutputStream().writeObject(userInGameRoomDTO);	
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	

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
		
		// DB�� ���̵� �н����带 ���� ��ġ���� ��� DTO�� ����
		UserPersonalInfoDTO resultDTO = this.userPersonalDAO.findUserID(personalDTO);
		
		
		ObjectOutputStream oos = personalServer.getServerOutputStream();
		
		oos.writeObject(resultDTO);
	}
	

//�н����� ã��---------------------------------------------------------------------------------------------------
public void findPw(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {	
	 UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO) data; //�θ�� �����°� ����ȯ
	 UserPersonalInfoDTO resultDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
	 ObjectOutputStream oos;
	 
	 switch(data.getUserAction()) {
	 	
	 	//���� ��ȣ �߼�
	 	case USER_SEARCH_CERTIFICATION_CHECK :
		 	
		 	resultDTO.setUserAction(UserActionEnum.USER_SEARCH_CERTIFICATION_CHECK);
	 	
	 		String confirmNumber = String.valueOf(new Random().nextInt(90000) + 10000);
	 		personalServer.setCertificationNumber(confirmNumber);
	 		
	 		resultDTO.setEmailSuccess(true);
	 		
	 		oos = personalServer.getServerOutputStream();
	 		oos.writeObject(resultDTO);
	 		break;
	 		
	 	//������ȣ ��
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
	 		
	 	//���̵� �̸��� üũ
	 	case USER_SEARCH_ID_EMAIL_CHECK :
	 		UserPersonalInfoDTO resultDTOPersonal = this.userPersonalDAO.findUserPW(personalDTO);
	 		oos = personalServer.getServerOutputStream();
	 		oos.writeObject(resultDTOPersonal);
	 		break;

	 	// ����� �н�����Ȯ��.
	 	case USER_SEARCH_PASSWD :

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

	
//���ӹ�----------------------------------------------------------------------------------------------------
	public void gameRoom(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
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
			this.stonePositionCheck(index, personalServer);
			break;
		// ������ ���� ������ �Ѵ�.
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
	
	// ������ ���� ������ Ŭ���̾�Ʈ ������ ��ġ �˻� �� �������� ����� ȸ���Ѵ�. 
	public void stonePositionCheck(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		GameBoardVO gameBoard = (GameBoardVO)index;
		int inputBoard[][] = gameBoard.getGameBoard();
		int board[][] = new int[15][];
		
		// ������ �������� ���� ��� ���� ������ �����Ͽ� �����Ų��.
		for (int i = 0, iLen = board.length; i < iLen; i++) {
			board[i] = new int[15];
			for (int j = 0, jLen = board[0].length; j < jLen; j++) {
				board[i][j] = inputBoard[i][j];
			}
		}
		
		// �������� ���� ���Ҵٴ� ������ �޾� ���� �������� ������ ������ ������ �ٲپ��ְ�
		// ������ ���� �������� �� �������� ������.
		String nextTurnUser = gameBoard.getNextTurnUser();
		String nowTurnUser  = gameBoard.getNowTurnUser();
		GameBoardVO sendGameBoardVO = new GameBoardVO(UserPositionEnum.POSITION_GAME_ROOM);
		if(gameBoard.getWinUser() == null) {
			sendGameBoardVO.setServerAction(ServerActionEnum.GAME_ROOM_SEND_BOARD_INFO);
		} else {
			// win ���������� �ִٸ� DAO �� �̱� ������ ������ ������Ʈ �Ѵ�.
			sendGameBoardVO.setServerAction(ServerActionEnum.GAME_ROOM_WINNER_INFO);
			sendGameBoardVO.setWinUser(gameBoard.getWinUser());
			sendGameBoardVO.setLoseUser(gameBoard.getLoseUser());
			this.gamedataDAO.winUserGameDataUpdate(sendGameBoardVO.getWinUser());
			this.gamedataDAO.loseUserGameDataUpdate(sendGameBoardVO.getLoseUser());
		}
		// ����ڿ��� ���� ������ �� 5���� ���� ������ ������ �ִٸ� �¸�����, �ƴ϶�� �׳� �� ������Ʈ �ϴ� ����(Enum)�� ��´�.
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
	
	// ������ ���ӹ��� ������ ��.
	public void userExitRoom(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
		GameRoomInfoVO gameRoomInfoVO = (GameRoomInfoVO)index;
		// ������ �Ѱ��� ������ �����Ͽ� ���
		GameRoomInfoVO pasteRoomInfoVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		pasteRoomInfoVO.setGuest(gameRoomInfoVO.getGuest());
		pasteRoomInfoVO.setOwner(gameRoomInfoVO.getOwner());
		pasteRoomInfoVO.setPersons(gameRoomInfoVO.getPersonNum());
		pasteRoomInfoVO.setPwd(gameRoomInfoVO.getPwd());
		pasteRoomInfoVO.setRoomName(gameRoomInfoVO.getRoomName());
		pasteRoomInfoVO.setRoomNumber(gameRoomInfoVO.getRoomNumber());
		
		try {			
			// ���� ������ ���� �ƴ϶�� ������ �������� ������ �������־�� �Ѵ�.
			if(pasteRoomInfoVO.getPersonNum() > 0) {
				for(int i = 0, len = this.gameRoomList.size(); i < len; i++) {
					System.out.println(i + "��");
					if(this.gameRoomList.get(i).getRoomNumber() == gameRoomInfoVO.getRoomNumber()) {
						pasteRoomInfoVO.setEnterImage(ImageEnum.WAITINGROOM_ENTER_POSSIBLE.getImageDir());
						this.gameRoomList.set(i, pasteRoomInfoVO);
						pasteRoomInfoVO.setServerAction(ServerActionEnum.GAME_ROOM_EXIT_OTHER_USER);
						this.loginUsersMap.get(pasteRoomInfoVO.getOwner()).getServerOutputStream().writeObject(pasteRoomInfoVO);
						break;
					}
				}

			// ���� ������ ���̶�� ���� ����Ʈ���� �����Ͽ��� �Ѵ�.
			} else {
				// ������ ��� ������.
				for(int i = 0, len = this.gameRoomList.size(); i < len; i++) {
					if(this.gameRoomList.get(i).getRoomNumber() == gameRoomInfoVO.getRoomNumber()) {
						this.gameRoomList.remove(i);
						break;
					}
				}
			}
		// �� ����Ʈ ���� ���� �� ��� �������� �渮��Ʈ ���������� �������־�� �Ѵ�.
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
				pasteRoomInfo.setEnterImage(roomInfo.getEnterImage().getDescription());
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
//�������� ����------------------------------------------------------------------------------------------------
	public void modifyMyInfo(AbstractEnumsDTO index, OmokPersonalServer personalServer) {
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
				
				// DB�� �н����尡 ��ġ�� ��
				if(dbPasswd != null) {
					int result = this.userPersonalDAO.updateUserInfo(personalDTO);
					// DB�� ���������� ������Ʈ ��
					if(result == 1) {
						resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_SUCCESS);
					
					// DB�� ������Ʈ ����
					} else {
						resultDTO.setServerAction(ServerActionEnum.MODIFY_USER_FAIL);
					}
				// DB�� �н������ ����ġ
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
			//ȸ��Ż��
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
