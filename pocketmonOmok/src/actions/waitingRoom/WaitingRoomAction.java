package actions.waitingRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import actions.adapters.Adapters;
import datasDTO.GameRoomInfoVO;
import datasDTO.UserGamedataInfoDTO;
import enums.etc.ImageEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import frames.BasicFrame;
import frames.waitingRoom.CreateGameRoomFrame;

public class WaitingRoomAction extends Adapters {
	private BasicFrame basicFrame;
	private CreateGameRoomFrame createRoom;
	private int openPrivate;
	private String listSelectUser; // 접속자 리스트에서 유저가 선택한 정보를 확인하기 위해 사용
	
	public WaitingRoomAction(BasicFrame basicFrame) {
		this.basicFrame = basicFrame;
		this.listSelectUser = "";
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		this.basicFrame.setVisible(true);
		this.createRoom.setVisible(false);
		this.createRoom.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getSource().toString();
		// 대기실 방만들기 버튼
		if(source.contains("createRoomButton")) {
			this.createRoomFrameView();
		
		// 방만들기프레임의 확인 버튼
		} else if(source.contains("createRoomConfirmButton")) {
			try {
				this.newCreateRoom();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
	
	// 유저 접속자 리스트에서 액션 인입
	@Override
	public void mouseClicked(MouseEvent e) {
		String selectValue = this.basicFrame.getWaitingRoomPanel().getPlayerList().getSelectedValue();
		if(this.listSelectUser.equals(selectValue)) {
			UserGamedataInfoDTO gameData = new UserGamedataInfoDTO(UserPositionEnum.POSITION_WAITING_ROOM);
			gameData.setUserAction(UserActionEnum.USER_CONFIRM_USERINFO);
			gameData.setUserID(this.listSelectUser);
			try {
				this.basicFrame.getClientOS().writeObject(gameData);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			this.listSelectUser = this.basicFrame.getWaitingRoomPanel().getPlayerList().getSelectedValue();
		}
		
	}
	
	// 방만들기프레임 new
	public void createRoomFrameView() {
		try {
			this.createRoom = this.basicFrame.getWaitingRoomPanel().newCreateGameRoomFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.basicFrame.setVisible(false);
	}
	
	// 방만들기-확인버튼
	public void newCreateRoom() throws IOException {
		// 게임방 정보 세팅
		GameRoomInfoVO gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
		gameRoomInfo.setUserAction(UserActionEnum.USER_CREATE_ROOM);
		gameRoomInfo.setRoomName(this.createRoom.getCreateRoomNameText().getText());
		gameRoomInfo.setOwner(this.basicFrame.getClientAccept().getUserID());
		gameRoomInfo.setRoomNumber(this.basicFrame.getWaitingRoomPanel().getRoomNumber());
		gameRoomInfo.setPersons(1);
		if(this.openPrivate == 1) {
			gameRoomInfo.setImage(ImageEnum.WAITINGROOM_ROOM_ENTERCHECK_IMAGE_MAP.getMap().get("비밀방"));
			gameRoomInfo.setPwd(this.createRoom.getCreateRoomPwdText().getText());			
		} else {
			gameRoomInfo.setImage(ImageEnum.WAITINGROOM_ROOM_ENTERCHECK_IMAGE_MAP.getMap().get("입장가능"));
		}
				
		this.basicFrame.getClientOS().writeObject(gameRoomInfo);
	}	
}