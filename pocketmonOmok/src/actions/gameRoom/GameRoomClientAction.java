package actions.gameRoom;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import actions.adapters.Adapters;
import frames.gameRoom.GameRoomPanel;

public class GameRoomClientAction extends Adapters {
	private GameRoomPanel gameRoomPanel;
	private int readyCheck;
	
	public GameRoomClientAction(GameRoomPanel gameRoomPanel) {
		this.gameRoomPanel = gameRoomPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.gameRoomPanel.chattingInfoSendServer();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton button = (JButton)e.getSource();
		
		this.gameRoomPanel.changeButtonImageMouseIn(button.getName());
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		JButton button = (JButton)e.getSource();
			
		this.gameRoomPanel.changeButtonImageMouseOut(button.getName());
	}
	//TODO
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton button = (JButton)e.getSource();
		String buttonName = button.getName();
		// 게스트가 레디를 누르면 게스트 레디 버튼의 이미지를 바꿔주고 사용자가 레디를 눌렀다고 서버에 전송한다.
		if(buttonName.equals("ready")) {
			readyCheck++;
			
			if(readyCheck == 1) {
				this.gameRoomPanel.changeGameReadyButton(true);
			} 
			
			if(readyCheck == 2) {
				this.gameRoomPanel.changeGameReadyButton(false);
				this.readyCheck = 0;
			}
		
		// 오너가 스타트를 누르면 버튼을 누를 수 없는 상태로 만들고 서버로 게임을 시작한다는 메세지를 보낸다.
		} else if(buttonName.equals("start")) {
			this.gameRoomPanel.startReadyButtonRemoveAction();
		
		} else if(buttonName.equals("withdraw")) {
			this.gameRoomPanel.clickWithDraw();
		
		} else if(buttonName.equals("exit")) {
			this.gameRoomPanel.exitGame();
		// 눌린 버튼이 게임 돌 놓는 작업이라면
		// 버튼의 위치값을 가져와 저장하고
		// 턴을 종료하는 메소드를 실행한다.
		// 돌을 한 턴에 두번 놓는 것을 방지하기 위해 플래그를 false 로 바꾸어준다.
		} else {
			if(this.gameRoomPanel.isStoneClickCheck()) {
				this.gameRoomPanel.setStoneClickCheck(false);
				String[] xy = button.getName().split(",");
				int x = Integer.parseInt(xy[0]);
				int y = Integer.parseInt(xy[1]);
				this.gameRoomPanel.turnEnd(x, y);
			}
		}
	}
	
	public void setReadyCheck(int readyCheck) {
		this.readyCheck = readyCheck;
	}
	
	public int getReadyCheck() {
		return readyCheck;
	}
}