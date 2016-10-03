package actions.modifyMyInfo;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import actions.adapters.Adapters;

public class ModifyMyInfoAction extends Adapters {
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton)e.getSource()).getName();
		
		switch(buttonName) {
		case "cancelButton" :
			break;
		case "modifyButton" :
			break;
		case "dropoutButton" :
			break;
		}
	}
}
