package actions.waitingRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import actions.adapters.Adapters;

public class WaitingRoomActionListeners extends Adapters {
	private WaitingRoomActions waitingRoomActions;
	
	public WaitingRoomActionListeners(WaitingRoomActions waitingRoomActions) {
		this.waitingRoomActions = waitingRoomActions;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		this.waitingRoomActions.createRoomFrameExit();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getSource() instanceof JButton ? ((JButton)e.getSource()).getName() : ((JTextField)e.getSource()).getName();
		switch(source) {
		case "createRoomButton" :
			this.waitingRoomActions.createRoomFrameView();
			break;

		case "createRoomConfirmButton" :
			this.waitingRoomActions.createRoomNewGameRoom();
			break;
		
		//sendMessageButton과 chattingInputTextField 가 동일한 동작을 하기 때문에 break를 주지 않는다.
		case "sendMessageButton" :
			this.waitingRoomActions.inputCheckPlus(); // chattingInput에서 inputCheck을 ++ 한 후 2여야지만 실행하기 때문에 기본값을 1로 설정
		
		case "chattingInputTextField" :
			this.waitingRoomActions.inputChattingMessage();
			break;
		
		case "createRoomCancelButton" :
			this.waitingRoomActions.createRoomFrameExit();
			break;
			
		case "modifyInfoButton" :
			break;
		default :
			break;
		}
		
	}
	
	// 방만들기프레임에서 공개방 비밀방 선택시 인입
	@Override
	public void itemStateChanged(ItemEvent e) {
		this.waitingRoomActions.createRoomSelectPrivateRoom(e.getStateChange());
	}
	
	// 방리스트, 유저 접속자 리스트, 노티스 필드(전체채팅) 에서 액션 인입
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JTable) {
			try {
				this.waitingRoomActions.enterGameRoom();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			this.waitingRoomActions.userListSelectAction(e.getSource() instanceof JTextField);
		}
	}
}