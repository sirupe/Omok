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
	
	// 방만들기 창 생성
	public void createRoomFrameView() {
		try {
			this.createRoom = this.waitingRoomPanel.newCreateGameRoomFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.waitingRoomPanel.getBasicFrame().setVisible(false);
	}
	
	// 방만들기 창 닫기
	public void createRoomFrameExit() {
		this.openPrivate = 0;
		this.waitingRoomPanel.getBasicFrame().setVisible(true);
		this.createRoom.setVisible(false);
		this.createRoom.dispose();
	}
	
	// 방만들기- 새로운 방 생성
	public void createRoomNewGameRoom() {
		
		GameRoomInfoVO gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		gameRoomInfo.setUserAction(UserActionEnum.USER_CREATE_ROOM);
		gameRoomInfo.setRoomName(this.createRoom.getCreateRoomNameText().getText());
		gameRoomInfo.setOwner(this.waitingRoomPanel.getBasicFrame().getUserID());
		gameRoomInfo.setRoomNumber(this.waitingRoomPanel.getRoomNumber());
		gameRoomInfo.setPersons(1);
		
		if(this.createRoom.getCreateRoomNameText().getText().length() != 0) {
			// 비밀방인 경우
			if(this.openPrivate == 1) {
				if(this.createRoom.getCreateRoomPwdText().getText().length() == 0) {
					JOptionPane.showMessageDialog(this.createRoom, "비밀번호를 입력해주세요.", "", JOptionPane.WARNING_MESSAGE);
				} else {
					this.createRoom.confirmButtonActionRemove();
					gameRoomInfo.setEnterImage(ImageEnum.WAITINGROOM_ENTER_PRIVATE.getImageDir());
					gameRoomInfo.setPwd(this.createRoom.getCreateRoomPwdText().getText());			
					this.waitingRoomPanel.sendDTO(gameRoomInfo);
				}
			// 일반방인경우
			} else {
				this.createRoom.confirmButtonActionRemove();
				gameRoomInfo.setEnterImage(ImageEnum.WAITINGROOM_ENTER_POSSIBLE.getImageDir());
				this.waitingRoomPanel.sendDTO(gameRoomInfo);
			}
		} else {
			JOptionPane.showMessageDialog(this.createRoom, "방 이름을 입력해주세요.", "", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	// 메세지전송버튼이 텍스트필드의 엔터와 동일한 역할을 하게 하기 위해 값을 추가해줌.
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
	
	//로그아웃 버튼
	public void logout() {
		int result = JOptionPane.showConfirmDialog(this.waitingRoomPanel, "게임을 종료하시겠습니까?", "게임종료", 
					 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		BasicFrame basicFrame = this.waitingRoomPanel.getBasicFrame();
		if(result == JOptionPane.YES_OPTION) {
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_EXIT);
			personalDTO.setUserID(basicFrame.getUserID());
			basicFrame.sendDTO(personalDTO);
			basicFrame.setVisible(false);
		}
	}
	
	// 채팅 메세지가 들어옴
	public void inputChattingMessage() {
		this.userListInputCheck++; 		// 들어오면 무조건 값 증가
		if(userListInputCheck == 2) {	// 두 번쨰 인입일 때에만 실행
			this.userListInputCheck = 0;// 인입값 초기화
			String userMessage = this.waitingRoomPanel.getChattingInputTextField().getText();
			if(!userMessage.equals("")) {
				UserMessageVO userMessageVO = new UserMessageVO(UserPositionEnum.POSITION_WAITING_ROOM);
				userMessageVO.setUserID(this.waitingRoomPanel.getBasicFrame().getUserID());
				userMessageVO.setMessage(userMessage + "\n");
				
				if(this.waitingRoomPanel.getNoticeTextField().getText().equals("전체채팅")) {
					userMessageVO.setUserAction(UserActionEnum.USER_MESSAGE_DEFAULT);
				} else {
					userMessageVO.setUserAction(UserActionEnum.USER_MESSAGE_SECRET);
					userMessageVO.setTargetID(this.targetUser);
				}
				this.waitingRoomPanel.sendDTO(userMessageVO);
				this.waitingRoomPanel.getChattingInputTextField().setText("");
			}
		}
	}
	
	// 방만들기프레임에서 공개방 비밀방 선택시 인입
	public void createRoomSelectPrivateRoom(int index) {
		this.openPrivate = index;
		if(index == 1) { // 비밀방인 경우
			this.createRoom.getCreateRoomPwdText().setEditable(true);
		} else { // 공개방인 경우
			this.createRoom.getCreateRoomPwdText().setText("");
			this.createRoom.getCreateRoomPwdText().setEditable(false);
		}
	}
	
	// 유저리스트에서 유저이름 선택시
	public void userListSelectAction(boolean bool) {
		String userID = this.waitingRoomPanel.getBasicFrame().getUserID();
		// 만약 인입된 컴포넌트가 텍스트필드라면
		if(bool) {
			this.waitingRoomPanel.getNoticeTextField().setText("전체채팅");
		
		// 아니라면 (리스트)
		} else {
			// 선택된 유저 아이디 
			String selectValue = this.waitingRoomPanel.getPlayerList().getSelectedValue();
			String resultValue = selectValue.contains("★") ? selectValue.substring(0, selectValue.length() - 1) : selectValue;
			// 만약 현재 저장된 유저와(처음 클릭시 저장된 아이디) 같다면 
			if(this.listSelectUser.equals(resultValue)) {
				// 접속한 유저와 다른 유저에게만 귓속말 전송 가능. (귓속말)
				if(!userID.equals(resultValue)) {	
					this.targetUser = resultValue;
					this.waitingRoomPanel.getNoticeTextField().setText(resultValue + " 에게 귓속말");
				}
				
				UserGamedataInfoDTO gameData = new UserGamedataInfoDTO(UserPositionEnum.POSITION_WAITING_ROOM);
				gameData.setUserAction(UserActionEnum.USER_CONFIRM_USERINFO);
				gameData.setUserID(resultValue);
				try {
					this.waitingRoomPanel.getBasicFrame().getClientOS().writeObject(gameData);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				this.listSelectUser = resultValue;
			}
		}
	}
	
	// 게임방 들어가기 (게임방 리스트 더블클릭)
	public void enterGameRoom() throws IOException {
		this.roomListInputCheck++;
		if(this.roomListInputCheck == 2) {
			this.roomListInputCheck = 0;
			int row = this.waitingRoomPanel.getWaitingRoomTable().getSelectedRow();
			ImageIcon image = ((ImageIcon)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 0));
			// 입장 가능인 경우
			if(image.getDescription().equals(ImageEnum.WAITINGROOM_ENTER_POSSIBLE.getImageDir())) {
				GameRoomInfoVO roomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
				roomVO.setEnterImage(image.getDescription());
				roomVO.setRoomNumber((int)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 1));
				roomVO.setRoomName((String)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 2));
				roomVO.setOwner((String)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 3));
				roomVO.setPersons(1);
				roomVO.setGuest(this.waitingRoomPanel.getBasicFrame().getUserID());
				roomVO.setUserAction(UserActionEnum.USER_ENTER_ROOM);
				this.waitingRoomPanel.sendDTO(roomVO);
			
			// 해당 방이 비밀방인 경우
			} else if(image.getDescription().equals(ImageEnum.WAITINGROOM_ENTER_PRIVATE.getImageDir())) {
				GameRoomInfoVO roomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
				roomVO.setUserAction(UserActionEnum.USER_PRIVATE_ROOM_ENTER);
				roomVO.setOwner((String)this.waitingRoomPanel.getWaitingRoomTable().getValueAt(row, 3));
				this.waitingRoomPanel.sendDTO(roomVO);
			
			// 입장 불가능한 경우
			} else {
				JOptionPane.showMessageDialog(null, "인원이 초과되었습니다.", "입장불가", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}


	
}
