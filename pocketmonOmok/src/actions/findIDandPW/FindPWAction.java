package actions.findIDandPW;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import actions.adapters.Adapters;
import enums.frames.LoginSizesEnum;
import frames.searchFrames.SearchPwdPanel;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	private SearchPwdPanel searchPwdFrame;
	

	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		this.searchPwdPanel = searchPwdPanel;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		//취소버튼 눌렀을때
		if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName())) {
			//1. 취소 버튼이 눌렸다.
			//2. searchPwdFrame을 꺼준다.
			//3. 나는 panel밖에 모른다. 그렇기 때문에 panel에 있는 메소드를 실행해 줄 수 밖에 없다.
			//4. panel에서 searchFrame을 숨겨주는 메소드를 만든다.
			//5. frame에서 basic을 숨길 수 있는 호출을 하고 자기를 숨길 수 있는 호출을 한다.
			this.searchPwdPanel.doCancelButton();
			//인증 버튼 눌렀을때
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.searchPwdPanel.getCerfication(); //프레임한테 보내준다 -- > action은 frame을 모르므로 panel가서 불러온다,
		}
	}
	
	public void findPwdAction() {

	
	//메세지 출력
	
	//3분 돌아가는ㄴ중

//		try {
//			this.loginPanel.getBasicFrame().getClientOS().defaultWriteObject();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}
}
