package actions.findIDandPW;

import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import actions.adapters.Adapters;import enums.frames.LoginSizesEnum;
import frames.serchFrames.SearchRePwdPanel;
import utility.RegexCheck;

public class FindRePwdAction extends Adapters {
	private SearchRePwdPanel searchRePwdPanel;
	
	private String pw;
	private String rePwd;
	private String checkMsg;
	
	
	public FindRePwdAction(SearchRePwdPanel searchRePwdPanel) {
		this.searchRePwdPanel = searchRePwdPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
		//취소버튼을 눌렀을때
		if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRMBUTTON.getButtonName())) {
			
			if(this.pw.isEmpty()) {
				this.checkMsg = "비밀번호를 입력해주세요";
				this.searchRePwdPanel.pwdMsgLabel(checkMsg);
				return;
			}
			//두번째 비밀번호
			if(this.rePwd.isEmpty()) {
				this.checkMsg = "재입력비밀번호를 입력해주세요";
				this.searchRePwdPanel.pwdMsgLabel(checkMsg);
				return;
			}
			//비밀번호텍스트에서 정합성이 맞지 않을때
			if(!RegexCheck.passwdRegexCheck(pw)) {
				this.checkMsg = "<html>영소문자 1~6글자," 
						+"<br>특수문자 하나 포함하십시오<br></html>";
				this.searchRePwdPanel.pwdMsgLabel(checkMsg);
				return;
			}
			//재비밀번호텍스트에서 정합성이 맞지 않을때
			if(!RegexCheck.passwdRegexCheck(rePwd)) {
				this.checkMsg = "<html>영소문자 1~6글자," 
						+"<br>특수문자 하나 포함하십시오<br></html>";
				this.searchRePwdPanel.pwdMsgLabel(checkMsg);
				return;
			}	
			// 재입력창과 입력창이 일치하는데 정합성이 맞지 않을때
			if(this.pw.equals(this.rePwd) && this.rePwd.equals(this.pw) && 
					!RegexCheck.passwdRegexCheck(pw) && !RegexCheck.passwdRegexCheck(rePwd)) {
					this.checkMsg = "<html>영소문자 1~6글자," 
						+"<br>특수문자 하나 포함하십시오<br></html>";
						this.searchRePwdPanel.pwdMsgLabel(checkMsg);
						return;
			} 
			//비밀번호랑 재비밀번호가 서로 맞지 않을떄
			if(!pw.equals(this.searchRePwdPanel.getsearchRePwdText().getText())) {
					this.searchRePwdPanel.pwdMsgLabelReset();
					this.checkMsg = "<html>재비밀번호랑 맞지않습니다." 
							+"<br>다시설정해주세요<br></html>";
					this.searchRePwdPanel.pwdMsgLabel(checkMsg);	
					return;
			} 
			if(this.pw.equals(this.rePwd)) {	
				System.out.println(this.searchRePwdPanel + " : pannel");
					this.searchRePwdPanel.getSearchPwdFrame().doSearchChangeConfirmPanel();
			} else {
				return;
			}
		}
	}
	
	//실시간으로 에러 메세지 송출
	public void keyReleased(KeyEvent e) {
		String inFo = e.getSource().toString();
		//키리스너에 들어오는 것을 받아 문자열로 반환하여 inFo에 저장
		
		if(inFo.contains("searchPwdText")) {
				this.pwCheck();
		
		} else if (inFo.contains("searchRePwdText")) {
			this.rePwdCheck();
		}	    
	}
	// 비밀번호 체크
	public void pwCheck() {
		this.pw = this.searchRePwdPanel.getSearchPwdText().getText();
		String checkMsg = null;
		
		if(!RegexCheck.passwdRegexCheck(pw)) {
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "<html>영소문자 1~6글자," 
					+"<br>특수문자 하나 포함하십시오<br></html>";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		} else {
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "비밀번호 가능!";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		}
	}
	
	// 재비밀번호 체크
	public void rePwdCheck() {
		this.rePwd = this.searchRePwdPanel.getsearchRePwdText().getText();
		String checkMsg = null;
		
		if(!RegexCheck.passwdRegexCheck(rePwd)) {
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "<html>영소문자 1~6글자," 
					+"<br>특수문자 하나 포함하십시오<br></html>";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		} else if (!rePwd.equals(this.searchRePwdPanel.getSearchPwdText().getText())) {
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "<html>비밀번호확인 해주세요!" 
					+"<br>일치하지않습니다.<br></html>";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		} else {
			//전에 나왔던 메세지 리셋
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "<html>비밀번호 일치!" 
					+"<br>로그인해주세요~!<br></html>";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		}
	}
}
