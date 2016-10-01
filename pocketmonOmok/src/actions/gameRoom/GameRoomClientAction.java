package actions.gameRoom;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import actions.adapters.Adapters;
import frames.gameRoom.GameRoomPanel;

public class GameRoomClientAction extends Adapters {
	private GameRoomPanel gameRoomPanel;
	private int readyCheck;
	private boolean isStartCheck;
	
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
		this.gameRoomPanel.changeButtonColorImage(button.getName());
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		JButton button = (JButton)e.getSource();
		this.gameRoomPanel.changeButtonGrayImage(button.getName());
	}
	//TODO
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("��������!!!!");
		JButton button = (JButton)e.getSource();
		// �Խ�Ʈ�� ���� ������ �Խ�Ʈ ���� ��ư�� �̹����� �ٲ��ְ� ����ڰ� ���� �����ٰ� ������ �����Ѵ�.
		if(button.getName().equals("ready")) {
			if(readyCheck > 0) {
				this.readyCheck = 0;
				this.gameRoomPanel.changeGameReadyButton(false);
			} else {
				this.gameRoomPanel.changeGameReadyButton(true);
				this.readyCheck++;				
			}
		
		// ���ʰ� ��ŸƮ�� ������ ��ư�� ���� �� ���� ���·� ����� ������ ������ �����Ѵٴ� �޼����� ������.
		} else {
			this.gameRoomPanel.buttonRemoveAction();
		}

	}
}
