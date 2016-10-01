package actions.findIDandPW;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import actions.adapters.Adapters;
import enums.frames.SearchIdEnum;
import frames.searchFrames.SearchIdResultPanel;

public class FindIdResultAction extends Adapters {
	private SearchIdResultPanel searchIdResultPanel;
	
	public FindIdResultAction(SearchIdResultPanel searchIdResultPanel) {
		this.searchIdResultPanel = searchIdResultPanel;
	}
	
	//홈으로
	@Override
	public void actionPerformed(ActionEvent e) {
		//버튼 액션	
		String buttonName = ((JButton)e.getSource()).getName();
		if(buttonName.equals(SearchIdEnum.BUTTON_NAME_GOHOME.getButtonName())) {
			this.searchIdResultPanel.doHomeButton();
		}
	}
	

	
}

