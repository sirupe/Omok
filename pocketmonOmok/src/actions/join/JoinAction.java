package actions.join;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import actions.adapters.Adapters;
import datas.UserPersonalInfoDTO;
import enums.UserPositionEnum;
import frames.JoinFrame;
import frames.LoginPanel;

public class JoinAction extends Adapters {
	private LoginPanel loginPanel;
	private JoinFrame joinFrame;
	
	public JoinAction(LoginPanel loginPanel, JoinFrame joinFrame){
		this.loginPanel = loginPanel;
		this.joinFrame = joinFrame;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		this.loginPanel.getBasicFrame().setVisible(true);
		this.joinFrame.setVisible(false);
		this.joinFrame.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(this.joinFrame.getIdTextField().getText());
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
		personalDTO.setUserID(this.joinFrame.getIdTextField().getText());
		try {
			this.loginPanel.getBasicFrame().getClientOS().writeObject(personalDTO);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
