package actions.waitingRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.addressing.v200408.MemberSubmissionWsaServerTube;

import actions.adapters.Adapters;
import datasDTO.GameRoomInfoVO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserMessageVO;
import enums.etc.ImageEnum;
import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import frames.waitingRoom.CreateGameRoomFrame;
import frames.waitingRoom.WaitingRoomPanel;

public class WaitingRoomAction extends Adapters {
	private WaitingRoomPanel waitingRoomPanel;
	private CreateGameRoomFrame createRoom;
	private int openPrivate;
	private String listSelectUser; // 접속자 리스트에서 유저가 선택한 정보를 확인하기 위해 사용
	private int inputCheck;	// 채팅시 Enter 입력하면 두 번 인입되기 때문에 체크를 위해 사용
	private String targetUser;
	
	public WaitingRoomAction(WaitingRoomPanel waitingRoomPanel) {
		this.waitingRoomPanel = waitingRoomPanel;
		this.listSelectUser = "";
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		this.waitingRoomPanel.getBasicFrame().setVisible(true);
		this.createRoom.setVisible(false);
		this.createRoom.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getSource() instanceof JButton ? ((JButton)e.getSource()).getName() : ((JTextField)e.getSource()).getName();
		System.out.println(((JTextField)e.getSource()).getName());
		
		switch(source) {
		case "createRoomButton" :
			this.createRoomFrameView();
			break;

		case "createRoomConfirmButton" :
			try {
				this.newCreateRoom();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		
		case "chattingInputTextField" :
			try {
				this.chattingInput();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		default :
			break;
		}
		
	}
	
	// 방만들기프레임에서 공개방 비밀방 선택시 인입
	@Override
	public void itemStateChanged(ItemEvent e) {
		this.openPrivate = e.getStateChange();
		if(this.openPrivate == 1) { // 비밀방인 경우
			this.createRoom.getCreateRoomPwdText().setEditable(true);
		} else { // 공개방인 경우
			this.createRoom.getCreateRoomPwdText().setEditable(false);
		}
	}
	
	// 유저 접속자 리스트, 노티스 필드(전체채팅) 에서 액션 인입
	@Override
	public void mouseClicked(MouseEvent e) {
		String userID = this.waitingRoomPanel.getBasicFrame().getClientAccept().getUserID();
		
		// 만약 인입된 컴포넌트가 텍스트필드라면
		if(e.getSource() instanceof JTextField) {
			this.waitingRoomPanel.getNoticeTextField().setText("전체채팅");
		
		// 아니라면 (리스트)
		} else {
			// 선택된 유저 아이디 
			String selectValue = this.waitingRoomPanel.getPlayerList().getSelectedValue();
			
			// 만약 현재 저장된 유저와(처음 클릭시 저장된 아이디) 같다면 
			if(this.listSelectUser.equals(selectValue)) {
				// 접속한 유저와 다른 유저에게만 귓속말 전송 가능. (귓속말)
				if(!userID.equals(selectValue)) {	
					this.targetUser = selectValue;
					this.waitingRoomPanel.getNoticeTextField().setText(selectValue + " 에게 귓속말");
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
	
	// 방만들기프레임 new
	public void createRoomFrameView() {
		try {
			this.createRoom = this.waitingRoomPanel.newCreateGameRoomFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.waitingRoomPanel.getBasicFrame().setVisible(false);
	}
	
	// 방만들기-확인버튼
	public void newCreateRoom() throws IOException {
		// 게임방 정보 세팅
		GameRoomInfoVO gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		gameRoomInfo.setUserAction(UserActionEnum.USER_CREATE_ROOM);
		gameRoomInfo.setRoomName(this.createRoom.getCreateRoomNameText().getText());
		gameRoomInfo.setOwner(this.waitingRoomPanel.getBasicFrame().getClientAccept().getUserID());
		gameRoomInfo.setRoomNumber(this.waitingRoomPanel.getRoomNumber());
		gameRoomInfo.setPersons(1);
		if(this.openPrivate == 1) {
			gameRoomInfo.setImage(ImageEnum.WAITINGROOM_ROOM_ENTERCHECK_IMAGE_MAP.getMap().get("비밀방"));
			gameRoomInfo.setPwd(this.createRoom.getCreateRoomPwdText().getText());			
		} else {
			gameRoomInfo.setImage(ImageEnum.WAITINGROOM_ROOM_ENTERCHECK_IMAGE_MAP.getMap().get("입장가능"));
		}
				
		this.waitingRoomPanel.getBasicFrame().getClientOS().writeObject(gameRoomInfo);
	}
	
	public void chattingInput() throws IOException {
		this.inputCheck++; 		// 들어오면 무조건 값 증가
		if(inputCheck == 2) {	// 두 번쨰 인입일 때에만 실행
			this.inputCheck = 0;// 인입값 초기화
			String userMessage = this.waitingRoomPanel.getChattingInputTextField().getText();
			UserMessageVO userMessageVO = new UserMessageVO(UserPositionEnum.POSITION_WAITING_ROOM);
			userMessageVO.setUserID(this.waitingRoomPanel.getBasicFrame().getClientAccept().getUserID());
			userMessageVO.setMessage(userMessage + "\n");
			if(this.waitingRoomPanel.getNoticeTextField().getText().equals("전체채팅")) {
				userMessageVO.setUserAction(UserActionEnum.USER_MESSAGE_DEFAULT);
			} else {
				userMessageVO.setUserAction(UserActionEnum.USER_MESSAGE_SECRET);
				userMessageVO.setTargetID(this.targetUser);
			}
			this.waitingRoomPanel.getBasicFrame().getClientOS().writeObject(userMessageVO);
			this.waitingRoomPanel.getChattingInputTextField().setText("");
		}
	}
}