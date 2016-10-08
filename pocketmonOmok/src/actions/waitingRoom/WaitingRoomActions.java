package actions.waitingRoom;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import datasDTO.GameRoomInfoVO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserMessageVO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ImageEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import frames.BasicFrame;
import frames.waitingRoom.CreateGameRoomFrame;
import frames.waitingRoom.WaitingRoomPanel;

public class WaitingRoomActions {
	private WaitingRoomPanel waitingRoomPanel;
	private CreateGameRoomFrame createRoom;
	private int openPrivate;
	private int userListInputCheck;
	private int roomListInputCheck;
	private String targetUser;
	private String listSelectUser;
	
	public WaitingRoomActions(WaitingRoomPanel waitingRoomPanel) {
		this.waitingRoomPanel = waitingRoomPanel;
		this.listSelectUser = "";
		this.openPrivate = 2;
	}
	
	// �游��� â ����
	public void createRoomFrameView() {
		try {
			this.createRoom = this.waitingRoomPanel.newCreateGameRoomFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.waitingRoomPanel.getBasicFrame().setVisible(false);
	}
	
	// �游��� â �ݱ�
	public void createRoomFrameExit() {
		this.openPrivate = 0;
		this.waitingRoomPanel.getBasicFrame().setVisible(true);
		this.createRoom.setVisible(false);
		this.createRoom.dispose();
	}
	
	// �游���- ���ο� �� ����
	public void createRoomNewGameRoom() {
		
		GameRoomInfoVO gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		gameRoomInfo.setUserAction(UserActionEnum.USER_CREATE_ROOM);
		gameRoomInfo.setRoomName(this.createRoom.getCreateRoomNameText().getText());
		gameRoomInfo.setOwner(this.waitingRoomPanel.getBasicFrame().getUserID());
		gameRoomInfo.setRoomNumber(this.waitingRoomPanel.getRoomNumber());
		gameRoomInfo.setPersons(1);
		
		if(this.createRoom.getCreateRoomNameText().getText().length() != 0) {
			// ��й��� ���
			if(this.openPrivate == 1) {
				if(this.createRoom.getCreateRoomPwdText().getText().length() == 0) {
					JOptionPane.showMessageDialog(this.createRoom, "��й�ȣ�� �Է����ּ���.", "", JOptionPane.WARNING_MESSAGE);
				} else {
					gameRoomInfo.setEnterImage(ImageEnum.WAITINGROOM_ENTER_PRIVATE.getImageDir());
					gameRoomInfo.setPwd(this.createRoom.getCreateRoomPwdText().getText());			
					this.waitingRoomPanel.sendDTO(gameRoomInfo);
				}
			// �Ϲݹ��ΰ��
			} else {
				gameRoomInfo.setEnterImage(ImageEnum.WAITINGROOM_ENTER_POSSIBLE.getImageDir());
				this.waitingRoomPanel.sendDTO(gameRoomInfo);
			}
		} else {
			JOptionPane.showMessageDialog(this.createRoom, "�� �̸��� �Է����ּ���.", "", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	// �޼������۹�ư�� �ؽ�Ʈ�ʵ��� ���Ϳ� ������ ������ �ϰ� �ϱ� ���� ���� �߰�����.
	public void inputCheckPlus() {
		this.userListInputCheck = 1;
	}

	public void correctMyInfo() {
		BasicFrame basicFrame = this.waitingRoomPanel.getBasicFrame();
		
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_MODIFY_MY_INFO);
		personalDTO.setUserAction(UserActionEnum.USER_MODIFY_GET_MY_INFO);
		personalDTO.setUserID(basicFrame.getUserID());
		basicFrame.sendDTO(personalDTO);

	}
	
	
	
	//TODO
	//�α׾ƿ� ��ư
	public void logout() {
		int result = JOptionPane.showConfirmDialog(this.waitingRoomPanel, "������ �����Ͻðڽ��ϱ�?", "��������", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		BasicFrame basicFrame = this.waitingRoomPanel.getBasicFrame();
		if(result == JOptionPane.YES_OPTION) {
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_EXIT);
			personalDTO.setUserID(basicFrame.getUserID());
			basicFrame.sendDTO(personalDTO);
			basicFrame.setVisible(false);
		}
	}
	
	
	
	// ä�� �޼����� ����
	public void inputChattingMessage() {
		this.userListInputCheck++; 		// ������ ������ �� ����
		if(userListInputCheck == 2) {	// �� ���� ������ ������ ����
			this.userListInputCheck = 0;// ���԰� �ʱ�ȭ
			String userMessage = this.waitingRoomPanel.getChattingInputTextField().getText();
			UserMessageVO userMessageVO = new UserMessageVO(UserPositionEnum.POSITION_WAITING_ROOM);
			userMessageVO.setUserID(this.waitingRoomPanel.getBasicFrame().getUserID());
			userMessageVO.setMessage(userMessage + "\n");
			if(this.waitingRoomPanel.getNoticeTextField().getText().equals("��üä��")) {
				userMessageVO.setUserAction(UserActionEnum.USER_MESSAGE_DEFAULT);
			} else {
				userMessageVO.setUserAction(UserActionEnum.USER_MESSAGE_SECRET);
				userMessageVO.setTargetID(this.targetUser);
			}
			this.waitingRoomPanel.sendDTO(userMessageVO);
			this.waitingRoomPanel.getChattingInputTextField().setText("");
		}
	}
	
	// �游��������ӿ��� ������ ��й� ���ý� ����
	public void createRoomSelectPrivateRoom(int index) {
		this.openPrivate = index;
		if(index == 1) { // ��й��� ���
			this.createRoom.getCreateRoomPwdText().setEditable(true);
		} else { // �������� ���
			this.createRoom.getCreateRoomPwdText().setText("");
			this.createRoom.getCreateRoomPwdText().setEditable(false);
		}
	}
	
	// ��������Ʈ���� �����̸� ���ý�
	public void userListSelectAction(boolean bool) {
		String userID = this.waitingRoomPanel.getBasicFrame().getUserID();
		// ���� ���Ե� ������Ʈ�� �ؽ�Ʈ�ʵ���
		if(bool) {
			this.waitingRoomPanel.getNoticeTextField().setText("��üä��");
		
		// �ƴ϶�� (����Ʈ)
		} else {
			// ���õ� ���� ���̵� 
			String selectValue = this.waitingRoomPanel.getPlayerList().getSelectedValue();
			
			// ���� ���� ����� ������(ó�� Ŭ���� ����� ���̵�) ���ٸ� 
			if(this.listSelectUser.equals(selectValue)) {
				// ������ ������ �ٸ� �������Ը� �ӼӸ� ���� ����. (�ӼӸ�)
				if(!userID.equals(selectValue)) {	
					this.targetUser = selectValue;
					this.waitingRoomPanel.getNoticeTextField().setText(selectValue + " ���� �ӼӸ�");
				}
				
				UserGamedataInfoDTO gameData = new UserGamedataInfoDTO(UserPositionEnum.POSITION_WAITING_ROOM);
				gameData.setUserAction(UserActionEnum.USER_CONFIRM_USERINFO);
				gameData.setUserID(this.listSelectUser);
				try {
					this.waitingRoomPanel.getBasicFrame().getClientOS().writeObject(gameData);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				this.listSelectUser = this.waitingRoomPanel.getPlayerList().getSelectedValue();
			}
		}
	}
	
	// ���ӹ� ���� (���ӹ� ����Ʈ ����Ŭ��)
	public void enterGameRoom() throws IOException {
		this.roomListInputCheck++;
		if(this.roomListInputCheck == 2) {
			this.roomListInputCheck = 0;
			int row = this.waitingRoomPanel.getWaitingRoomTable().getSelectedRow();
			String image = ((ImageIcon)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 0)).getDescription();
			
			// ���� ������ ���
			if(image.equals(ImageEnum.WAITINGROOM_ENTER_POSSIBLE.getImageDir())) {

				GameRoomInfoVO roomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
				roomVO.setEnterImage(image);
				roomVO.setRoomNumber((int)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 1));
				roomVO.setRoomName((String)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 2));
				roomVO.setOwner((String)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 3));
				roomVO.setPersons(1);
				roomVO.setGuest(this.waitingRoomPanel.getBasicFrame().getUserID());
				roomVO.setUserAction(UserActionEnum.USER_ENTER_ROOM);
				this.waitingRoomPanel.sendDTO(roomVO);
			
			// �ش� ���� ��й��� ���
			} else if(image.equals(ImageEnum.WAITINGROOM_ENTER_PRIVATE.getImageDir())) {
				GameRoomInfoVO roomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
				roomVO.setUserAction(UserActionEnum.USER_PRIVATE_ROOM_ENTER);
				roomVO.setOwner((String)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 3));
				this.waitingRoomPanel.sendDTO(roomVO);
			
			// ���� �Ұ����� ���
			} else {
				JOptionPane.showMessageDialog(null, "�ο��� �ʰ��Ǿ����ϴ�.", "����Ұ�", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}


	
}
