package actions.join;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import actions.adapters.Adapters;
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
	public void windowClosed(WindowEvent e) {
		System.out.println("여기 오긴 오니 ??");
		this.loginPanel.getBasicFrame().setVisible(true);
		this.joinFrame.setVisible(false);
		this.joinFrame.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
