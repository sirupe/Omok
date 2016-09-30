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
		
		//sendMessageButton�� chattingInputTextField �� ������ ������ �ϱ� ������ break�� ���� �ʴ´�.
		case "sendMessageButton" :
			this.waitingRoomActions.inputCheckPlus(); // chattingInput���� inputCheck�� ++ �� �� 2�������� �����ϱ� ������ �⺻���� 1�� ����
		
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
	
	// �游��������ӿ��� ������ ��й� ���ý� ����
	@Override
	public void itemStateChanged(ItemEvent e) {
		this.waitingRoomActions.createRoomSelectPrivateRoom(e.getStateChange());
	}
	
	// �渮��Ʈ, ���� ������ ����Ʈ, ��Ƽ�� �ʵ�(��üä��) ���� �׼� ����
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