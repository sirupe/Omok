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
		// �Խ�Ʈ�� ���� ������ �Խ�Ʈ ���� ��ư�� �̹����� �ٲ��ְ� ����ڰ� ���� �����ٰ� ������ �����Ѵ�.
		if(buttonName.equals("ready")) {
			readyCheck++;
			
			if(readyCheck == 1) {
				this.gameRoomPanel.changeGameReadyButton(true);
			} 
			
			if(readyCheck == 2) {
				this.gameRoomPanel.changeGameReadyButton(false);
				this.readyCheck = 0;
			}
		
		// ���ʰ� ��ŸƮ�� ������ ��ư�� ���� �� ���� ���·� ����� ������ ������ �����Ѵٴ� �޼����� ������.
		} else if(buttonName.equals("start")) {
			this.gameRoomPanel.startReadyButtonRemoveAction();
		
		} else if(buttonName.equals("withdraw")) {
			this.gameRoomPanel.clickWithDraw();
		
		} else if(buttonName.equals("exit")) {
			this.gameRoomPanel.exitGame();
		// ���� ��ư�� ���� �� ���� �۾��̶��
		// ��ư�� ��ġ���� ������ �����ϰ�
		// ���� �����ϴ� �޼ҵ带 �����Ѵ�.
		// ���� �� �Ͽ� �ι� ���� ���� �����ϱ� ���� �÷��׸� false �� �ٲپ��ش�.
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