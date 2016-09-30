package omokGame.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import actions.gameRoom.GameRoomServerAction;
import actions.join.JoinServerAction;
import actions.login.LoginServerAction;
import datasDTO.AbstractEnumsDTO;
import datasDTO.GameRoomInfoVO;
import datasDTO.RoomAndUserListDTO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserInGameRoomDTO;
import datasDTO.UserMessageVO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.ServerIPEnum;
import frames.BasicFrame;
import frames.waitingRoom.WaitingRoomListTable;
import frames.waitingRoom.WaitingRoomPanel;

// Ŭ���̾�Ʈ ����� Ŭ���̾�Ʈ ���� �� ������ ��� ����
public class ClientAccept {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	private BasicFrame basicFrame;
	private LoginServerAction loginRequestAction;
	private JoinServerAction joinRequestAction;
	private GameRoomServerAction gameRoomRequestAction;
//	private String userID;
	
	public ClientAccept() throws UnknownHostException, IOException {
		this.clientSocket = new Socket(ServerIPEnum.SERVER_IP.getServerIP(), ServerIPEnum.SERVER_PORT.getServerPort());
		this.clientOS 	  = new ObjectOutputStream(this.clientSocket.getOutputStream());
		this.clientIS	  = new ObjectInputStream(this.clientSocket.getInputStream());
		this.basicFrame	  = new BasicFrame(this);
		
		this.loginRequestAction		= new LoginServerAction(this.basicFrame.getLoginPanel());
		this.joinRequestAction		= new JoinServerAction(this.basicFrame.getJoinFrame());
		this.gameRoomRequestAction 	= new GameRoomServerAction(this.basicFrame.getGameRoomPanel());
		
		ClientReciever reciever = new ClientReciever(this, this.basicFrame);
		reciever.start();
	}
//�α���---------------------------------------------------------------------------------------------
	public void loginSuccessCheck(AbstractEnumsDTO data) throws IOException {		
		// ������ �޼���
		switch(data.getServerAction()) {
		// - �α��� �� ������ �Է��� �߸����� ��
		case LOGIN_FAIL_INPUT_ERROR :
		// - �α��� �� �̹� ������ ������ ������ �Է����� ��
		case LOGIN_FAIL_OVERLAP_ACCEPT :
			this.loginRequestAction.loginFail(data);
			break;
		// �α��ο� �������� ��
		case LOGIN_SUCCESS :
			// ���Ƿ� �̵��ϰڴٴ� ������ ��� ������ ���� (������ �̵����� �ʴ´�.)
			this.loginRequestAction.loginSuccess(data);
			break;
		default :
			break;
		}
	}
	
//ȸ������----------------------------------------------------------------------------------------------------------
	public void joinFrameInputAction(AbstractEnumsDTO data) throws IOException {
		switch(data.getUserAction()) {
		case USER_JOIN_ID_OVERLAP_CHECK :
			this.joinRequestAction.joinOverlapCheck(data);
			break;
		case USER_JOIN_JOINACTION : 
			this.joinRequestAction.joinTry(data);
			break;
		case USER_JOIN_CERTIFICATION :
			this.joinRequestAction.cercificationNumber(data);
			break;
		default : 
			break;
		}
	}
//����------------------------------------------------------------------------------------------
	public void waitingRoomAction(AbstractEnumsDTO data) throws IOException {
		WaitingRoomPanel panel = this.basicFrame.getWaitingRoomPanel();
		switch(data.getServerAction() != null ? data.getServerAction() : ServerActionEnum.NOTHING) {
		// �������� ���� ������ "���ο� ������ �����ߴ�" �� �������
		case LOGIN_NEW_USER :
			UserGamedataInfoDTO newUserData = (UserGamedataInfoDTO)data;
			this.basicFrame.getWaitingRoomPanel().userListAddSetting(newUserData);
			this.basicFrame.getWaitingRoomPanel().getChattingOutput().append(newUserData.getUserID() + " �Բ��� �����ϼ̽��ϴ�.\n");
			break;
			
		// �������� ���� ������ "���� ����" �̶�� 
		case WAITING_ROOM_ENTER :
			RoomAndUserListDTO waitingRoomInfo = (RoomAndUserListDTO)data;
			
			WaitingRoomListTable roomTable = new WaitingRoomListTable(waitingRoomInfo.getGameRoomList());
			panel.setUserInfo(waitingRoomInfo.getUserGameData());
			panel.roomListSetting(roomTable);			
			panel.userListSetting(waitingRoomInfo.getUserList());
			panel.getChattingOutput().append(waitingRoomInfo.getUserGameData().getUserID() + " ��, ȯ���մϴ�.\n");
			this.basicFrame.showWaitingRoom();
			break;
		
		// �������� ���� ������ "����� ����" �̶��
		case GAME_CREATEROOM_SUCCESS :
			UserInGameRoomDTO inGameUserInfo = (UserInGameRoomDTO)data;
			panel.getCreateGameRoomFrame().setVisible(false);
			panel.getCreateGameRoomFrame().dispose();
			this.basicFrame.showGameRoom(inGameUserInfo);
			this.basicFrame.setVisible(true);
			break;
		//TODO ����� ���� �ؾ� ��.
		// �������� ���� ������ "�� ����Ʈ �߰�" ���
		case GAME_ROOM_ADD :
			panel.addGameRoom((GameRoomInfoVO)data);
			break;
		// �������� ���� ������ �ٸ� ���� ���� ������ 
		case OTHER_USER_INFO : 
			panel.setUserInfo((UserGamedataInfoDTO)data);
			break;
		// �������� �޼����� ���� !!
		case MESSAGE_SEND_SUCCESS :
			panel.getChattingOutput().append(((UserMessageVO)data).getMessage());
			break;
		// �渮��Ʈ ���� ���� (�ٸ� ������ ���ӹ濡 ������) 
		case ENTER_ROOM_SUCCESS_LIST :
			RoomAndUserListDTO waitingRoomModInfo = (RoomAndUserListDTO)data;
			panel.modGameRoom(waitingRoomModInfo);
			break;
		// ����ڰ� ��й� ������ �õ��ϰ� �ִ�.
		case ENTER_PRIVATE_GAME_ROOM :
			GameRoomInfoVO roomVO = (GameRoomInfoVO)data;
			boolean isPasswordFail = true;
			
			while(isPasswordFail) {
				String passwd = JOptionPane.showInputDialog("��й�ȣ�� �Է����ּ���.");
				if(roomVO.getPwd().equals(passwd)) {
					isPasswordFail = false;
					this.basicFrame.showGameRoom((UserInGameRoomDTO)data);
				} else if(passwd == null) {
					isPasswordFail = false;
				} else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ���ϴ�.");
				}
			}
			break;
		default:
			break;
			
		}
	}
	
//���ӷ�------------------------------------------------------------------------------
	public void inGameRoom(AbstractEnumsDTO data) {
		switch(data.getServerAction()) {
		case ENTER_ROOM_SUCCESS_GUEST :
			this.gameRoomRequestAction.guestEnterRoom(data);
			break;
		default :
			break;
		}
	}

	
	
	public void gameExit(AbstractEnumsDTO infoDTO) throws IOException {
		if(infoDTO.getServerAction() == ServerActionEnum.OTHER_USER_EXIT) {
			this.basicFrame.getWaitingRoomPanel().deleteUserSetting((UserPersonalInfoDTO)infoDTO);
		} else {
			this.clientOS.close();
			this.clientIS.close();
			this.clientSocket.close();
			this.basicFrame.dispose();
			System.exit(0);
		}
	}
	
	
	
	public void sendDTO(AbstractEnumsDTO dto) {
		try {
			this.clientOS.writeObject(dto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObjectInputStream getClientIS() {
		return clientIS;
	}

	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
}
