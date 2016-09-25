package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.io.IOException;

import actions.adapters.Adapters;
import frames.LoginPanel;
import frames.joinFrames.JoinFrame;
import frames.serchFrames.SearchPwdFrame;
import frames.serchFrames.SearchPwdPanel;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	private JoinFrame joinFrame;
	private LoginPanel loginPanel;

	
	public FindPWAction(SearchPwdFrame searchPwdFrame) {
		this.searchPwdPanel = searchPwdPanel;
		
	}
	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		
	}
	
	public void findPwdAction() {
		try {
			this.loginPanel.getBasicFrame().getClientOS().defaultWriteObject();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
