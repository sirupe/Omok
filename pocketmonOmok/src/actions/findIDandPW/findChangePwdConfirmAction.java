package actions.findIDandPW;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import actions.adapters.Adapters;
import enums.frames.LoginPanelEnum;
import frames.searchFrames.SearchChangePanel;

public class findChangePwdConfirmAction extends Adapters{
	private SearchChangePanel searchChangePanel;
	
	
	public findChangePwdConfirmAction(SearchChangePanel searchChangePanel) {
		this.searchChangePanel = searchChangePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = ((JButton) e.getSource()).getName();
		
		if(button.equals(LoginPanelEnum.BUTTON_NAME_CHANGE_CONFIRM.getButtonName())) {
			this.searchChangePanel.goHome();
		}	
	}
}
