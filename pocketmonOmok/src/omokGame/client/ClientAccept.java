package omokGame.client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import datasDTO.AbstractEnumsDTO;
import datasDTO.GameRoomInfoVO;
import datasDTO.RoomAndUserListDTO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserMessageVO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.ServerIPEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import frames.BasicFrame;
import frames.LoginPanel;
import frames.joinFrames.JoinFrame;
import frames.joinFrames.JoinSuccessFrame;
import frames.waitingRoom.WaitingRoomListTable;
import frames.waitingRoom.WaitingRoomPanel;

// Ŭ���̾�Ʈ ����� Ŭ���̾�Ʈ ���� �� ������ ��� ����
public class ClientAccept {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	private BasicFrame basicFrame;
	private String userID;
	
	public ClientAccept() throws UnknownHostException, IOException {
		this.clientSocket = new Socket(ServerIPEnum.SERVER_IP.getServerIP(), ServerIPEnum.SERVER_PORT.getServerPort());
		this.clientOS 	  = new ObjectOutputStream(this.clientSocket.getOutputStream());
		this.clientIS	  = new ObjectInputStream(this.clientSocket.getInputStream());
		this.basicFrame	  = new BasicFrame(this);
		ClientReciever reciever = new ClientReciever(this, this.basicFrame);
		reciever.start();
	}
//�α���---------------------------------------------------------------------------------------------
	public void loginSuccessCheck(AbstractEnumsDTO data, LoginPanel loginPanel) throws IOException {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)data;
		
		// ������ �޼���
		switch(userPersonalDTO.getServerAction()) {
		// - �α��� �� ������ �Է��� �߸����� ��
		case LOGIN_FAIL_INPUT_ERROR :
			loginPanel.loginFailLabelReset();
			loginPanel.loginFail("���̵�, �н����� �����Դϴ�.");
			break;
		// - �α��� �� �̹� ������ ������ ������ �Է����� ��
		case LOGIN_FAIL_OVERLAP_ACCEPT :
			loginPanel.loginFailLabelReset();
			loginPanel.loginFail("�̹� �������� ���̵��Դϴ�.");
			break;
		// �α��ο� �������� ��
		case LOGIN_SUCCESS :
			// ���Ƿ� �̵��ϰڴٴ� ������ ��� ������ ���� (������ �̵����� �ʴ´�.)
			this.userID = userPersonalDTO.getUserID();
			userPersonalDTO.setPosition(UserPositionEnum.POSITION_WAITING_ROOM);
			userPersonalDTO.setUserAction(UserActionEnum.USER_LOGIN_SUCCESS);
			this.basicFrame.getClientOS().writeObject(userPersonalDTO);
			break;
		default :
			break;
		}
	}
	
//ȸ������----------------------------------------------------------------------------------------------------------
	public void joinFrameInputAction(AbstractEnumsDTO data, JoinFrame joinFrame) throws IOException {
		// ���̵� �ߺ�üũ
		if(data.getUserAction() == UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK) {
			UserPersonalInfoDTO userPersonalInfoDTO = (UserPersonalInfoDTO)data;
			String checkMsg = null;
			Color color 	= null;
			
			if(userPersonalInfoDTO.getUserID() == null) {
				checkMsg = "join����";
				color = JoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
			
			} else {
				checkMsg = "joinID�ߺ�";
				color = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
			}
			
			joinFrame.labelSetting(
					joinFrame.getIdErrorLabel(), 
					color, checkMsg);
			
		// ȸ������
		} else if(data.getUserAction() == UserActionEnum.USER_JOIN_JOINACTION) {
			if(data.getServerAction() == ServerActionEnum.JOIN_SUCCESS) {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "ȸ������ �Ϸ�:)");
				joinFrame.setVisible(false);
				joinFrame.dispose();
			
			} else {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "���� �߻� :(");
				joinFrame.setVisible(false);
				joinFrame.dispose();
				
			}
		} else if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
			System.out.println("������ȣ �����.." + ((UserPersonalInfoDTO)data).getCertificationNumber());
			joinFrame.getJoinAction().setCertificationNumber(((UserPersonalInfoDTO)data).getCertificationNumber());
		}
	}
//����------------------------------------------------------------------------------------------
	public void waitingRoomAction(AbstractEnumsDTO data, WaitingRoomPanel waitingRoomPanel) throws IOException {
		switch(data.getServerAction() != null ? data.getServerAction() : ServerActionEnum.NOTHING) {
		// �������� ���� ������ "���ο� ������ �����ߴ�" �� �������
		case LOGIN_NEW_USER :
			UserGamedataInfoDTO newUserData = (UserGamedataInfoDTO)data;
			waitingRoomPanel.userListAddSetting(newUserData);
			waitingRoomPanel.getChattingOutput().append(newUserData.getUserID() + " �Բ��� �����ϼ̽��ϴ�.\n");
			break;
			
		// �������� ���� ������ "���� ����" �̶�� 
		case WAITING_ROOM_ENTER :
			RoomAndUserListDTO waitingRoomInfo = (RoomAndUserListDTO)data;
			
			WaitingRoomListTable roomTable = new WaitingRoomListTable(waitingRoomInfo.getGameRoomList());
			waitingRoomPanel.setUserInfo(waitingRoomInfo.getUserGameData());
			waitingRoomPanel.roomListSetting(roomTable);			
			waitingRoomPanel.userListSetting(waitingRoomInfo.getUserList());
			waitingRoomPanel.getChattingOutput().append(waitingRoomInfo.getUserGameData().getUserID() + " ��, ȯ���մϴ�.\n");
			this.basicFrame.inWaitingRoom();
			break;
		
		// �������� ���� ������ "����� ����" �̶��
		case GAME_CREATEROOM_SUCCESS :
			waitingRoomPanel.getCreateGameRoomFrame().dispose();
			this.basicFrame.inGameRoom();
			this.basicFrame.setVisible(true);
			break;
		//TODO ����� ���� �ؾ� ��.
		// �������� ���� ������ "�� �߰�" ���
		case GAME_ROOM_ADD :
			waitingRoomPanel.addGameRoom((GameRoomInfoVO)data);
			break;
		// �������� ���� ������ �ٸ� ���� ���� ������ TODO
		case OTHER_USER_INFO : 
			waitingRoomPanel.setUserInfo((UserGamedataInfoDTO)data);
			break;
		// �������� �޼����� ���� !!
		case MESSAGE_SEND_SUCCESS :
			waitingRoomPanel.getChattingOutput().append(((UserMessageVO)data).getMessage());
			break;
		default:
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
	
	public ObjectInputStream getClientIS() {
		return clientIS;
	}

	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
	
	public String getUserID() {
		return userID;
	}

}
