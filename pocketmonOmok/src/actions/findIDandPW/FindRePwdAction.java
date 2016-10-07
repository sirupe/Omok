package actions.findIDandPW;

import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

import actions.adapters.Adapters;import enums.frames.LoginPanelEnum;
import frames.searchFrames.SearchRePwdPanel;
import utility.RegexCheck;

public class FindRePwdAction extends Adapters {
	private SearchRePwdPanel searchRePwdPanel;
	
	private boolean pwdCheck;
	private boolean pwdReCheck;
	
	public FindRePwdAction(SearchRePwdPanel searchRePwdPanel) {
		this.searchRePwdPanel = searchRePwdPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getName();
		
		//확인 버튼 눌렀을때
		if(buttonName.equals(LoginPanelEnum.BUTTON_NAME_SEARCH_CONFIRMBUTTON.getButtonName())) {
			if(this.pwCheck()) {
				return;
			}
			
			if(this.rePwdCheck()) {
				return;
			}
			
			//모두 확인이 되면
			if(this.pwdCheck && this.pwdReCheck) {
				this.searchRePwdPanel.getSearchPwdText().setEditable(false);
				this.searchRePwdPanel.getsearchRePwdText().setEditable(false);
				this.searchRePwdPanel.pwdChange();
			}
		}
	}
	
	//실시간으로 에러 메세지 송출
	public void keyReleased(KeyEvent e) {
		JTextField textField = (JTextField) e.getSource();
		String pwdChange = textField.getText();
		
		if(pwdChange.contains("searchPwdText")) {
			this.pwCheck();
		} else {
			this.rePwdCheck();
		}
	}
	
	// 비밀번호 체크
	public boolean pwCheck() {
		JTextField TextField = this.searchRePwdPanel.getSearchPwdText();
		String pwd = TextField.getText();
		
		if(pwd.isEmpty()) {
			this.searchRePwdPanel.pwdMsgLabel("비밀번호 입력해주세요");
			this.pwdCheck = false;
			return true;
		} 
		
		if(!RegexCheck.passwdRegexCheck(pwd)) {
			this.searchRePwdPanel.pwdMsgLabel("<html>영소문자 1~6글자," 
					+"<br>특수문자 하나 포함하십시오<br></html>");
			this.pwdCheck = false;
			return true;
		}
		this.searchRePwdPanel.pwdMsgLabelReset();
		this.pwdCheck = true;
		return false;
	}
	
	// 재비밀번호 체크
	public boolean rePwdCheck() {
		JTextField textField = this.searchRePwdPanel.getsearchRePwdText();
		JTextField TextField = this.searchRePwdPanel.getSearchPwdText();
		String rePwd = textField.getText();
		String pwd = TextField.getText();
		
		if(rePwd.isEmpty()) {
			this.searchRePwdPanel.pwdMsgLabel("비밀번호 입력해주세요");
			this.pwdReCheck = false;
			return true;
		} 
		
		if(!RegexCheck.passwdRegexCheck(rePwd)) {
			this.searchRePwdPanel.pwdMsgLabel( "<html>영소문자 1~6글자," 
					+"<br>특수문자 하나 포함하십시오<br></html>");
			this.pwdReCheck = false;
			return true;
		}
		
		if (!rePwd.equals(pwd)) {
			this.searchRePwdPanel.pwdMsgLabel("<html>비밀번호확인 해주세요!" 
					+"<br>일치하지않습니다.<br></html>");
			this.pwdReCheck = false;
			return true;
		}
		
		this.searchRePwdPanel.pwdMsgLabel("<html>비밀번호 일치합니다.!");
		this.pwdReCheck = true;
		return false;
	}
}
