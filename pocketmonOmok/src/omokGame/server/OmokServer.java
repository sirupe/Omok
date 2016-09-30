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
	
	// �������Լ� ������ϰڴ� ��� �޼����� ������ ������ ���ӹ渮��Ʈ�� ���� �߰����� �� ����ڿ��� ����� �����ش�.
	// ���� 20���� �Ѵ´ٸ� �游��� ����.
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
	
	// �� �� ���� (TODO)
	public void waitingRoomEnterPossibleGameRoom(AbstractEnumsDTO listDTO, OmokPersonalServer personalServer) {
		GameRoomInfoVO userChoiceRoom = (GameRoomInfoVO)listDTO;
		// ���ӷ� ���� ������Ʈ
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
	
	// ��, ��й� ����. ���������� �ʰ� ��й�ȣ�� ȸ���Ѵ�.
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
		// �����Ͱ� ����� ���۵ǰ� ���� �ʱ� ������ ��� ���ο� ��ü�� ��� �����Ѵ�.
		
		// ���� ������ ����Ǿ��ִ� ����Ʈ�� roomList�� ����
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
			// ��� �����ڿ��� ����� �� ���� ����(������ ����)
			RoomAndUserListDTO roomListInfo = new RoomAndUserListDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			roomListInfo.setServerAction(ServerActionEnum.ENTER_ROOM_SUCCESS_LIST);
			roomListInfo.setGameRoomList(roomList);
			for(String user : this.loginUsersMap.keySet()) {
				this.loginUsersMap.get(user).getServerOutputStream().writeObject(roomListInfo);
			}
			
			// ���� ���ʿ� �Խ�Ʈ���� ���� ����(������ ���ӷ�)
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
			System.out.println("�Խ�Ʈ ���� : " + roomGuestVO.getGuest());
			this.loginUsersMap.get(roomGuestVO.getGuest()).getServerOutputStream().writeObject(userInGameRoomDTO);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

//ȸ������--------------------------------------------------------------------------
	public void join(AbstractEnumsDTO data, OmokPersonalServer personalServer) throws IOException {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
		// ���̵� �ߺ�üũ�� ���
		if(data.getUserAction() == UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK) {
			UserPersonalInfoDTO resultDTO = this.joinDAO.checkOverlapID(personalDTO);
			personalServer.getServerOutputStream().writeObject(resultDTO);
		
		// ������ȣ �߼��� ���
		} else if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
			UserPersonalInfoDTO resultDTO = (UserPersonalInfoDTO)data;
			//������ȣ ����
			String confirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
			//�̸��Ϲ߼�
			new SendEmail(confirmNumber, resultDTO.getUserEmail());
			
			resultDTO.setCertificationNumber(confirmNumber);
			resultDTO.setServerAction(ServerActionEnum.JOIN_CERTIFICATION);
			personalServer.getServerOutputStream().writeObject(resultDTO);
		// ȸ�������� ���
		} else {
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
		}
	}
	
	public void findID() {
		System.out.println("IDã��");
	}
	
	public void findPW() {
		System.out.println("��й�ȣã��");
	}
	//
	public void findEmail(AbstractEnumsDTO data, OmokPersonalServer PersonalServer) throws IOException {
		UserPersonalInfoDTO personalDTO = (UserPersonalInfoDTO)data;
		if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
			UserPersonalInfoDTO resultDTO = (UserPersonalInfoDTO)data;
			
			String ConfirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
			//���� �߼� -- ���� ��ȣ�� resultDTO�� ��� ����� �̸��Ϸ� ������..
			new SendEmail(ConfirmNumber, resultDTO.getUserEmail());
			
			resultDTO.setCertificationNumber(ConfirmNumber);
			resultDTO.setServerAction(ServerActionEnum.JOIN_CERTIFICATION);
			PersonalServer.getServerOutputStream().writeObject(resultDTO);
		}
	}
	public void gameRoom() {
		System.out.println("���ӹ�");
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
