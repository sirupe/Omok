package actions.waitingRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;

import actions.adapters.Adapters;
import datasDTO.GameRoomInfoVO;
import enums.etc.ImageEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import frames.BasicFrame;
import frames.waitingRoom.CreateGameRoomFrame;

public class WaitingRoomAction extends Adapters {
	private BasicFrame basicFrame;
	private CreateGameRoomFrame createRoom;
	private int openPrivate;
	
	public WaitingRoomAction(BasicFrame basicFrame) {
		this.basicFrame = basicFrame;
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
		String roomPwd = null;
		if(this.openPrivate == 1) {
			gameRoomInfo.setImage(ImageEnum.WAITINGROOM_ROOM_ENTERCHECK_IMAGE_MAP.getMap().get("비밀방"));
			gameRoomInfo.setPwd(this.createRoom.getCreateRoomPwdText().getText());			
		} else {
			gameRoomInfo.setImage(ImageEnum.WAITINGROOM_ROOM_ENTERCHECK_IMAGE_MAP.getMap().get("입장가능"));
		}
		
		System.out.println(gameRoomInfo.toString());
		
		this.basicFrame.getClientOS().writeObject(gameRoomInfo);
	}
	
}
