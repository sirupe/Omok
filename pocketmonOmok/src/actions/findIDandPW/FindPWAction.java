package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import actions.adapters.Adapters;
import enums.frames.LoginPanelEnum;
import frames.searchFrames.SearchPwdPanel;
import utility.RegexCheck;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	
	private boolean emailCheck;
	private boolean idCheck;
	private boolean confirmCheck;
	
	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		this.searchPwdPanel = searchPwdPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getName();
		
		if(buttonName.equals(LoginPanelEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName())) {
			this.searchPwdPanel.doCancelButton();
			
		//인증버튼이 눌렸을때
		} else if(buttonName.equals(LoginPanelEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.emailCheck();
			
			//이메일이 있고 형식에 맞을때
			if(this.emailCheck) {
				this.searchPwdPanel.getCerfication();
				
			} 

		} else if(buttonName.equals(LoginPanelEnum.BUTTON_NAVE_CONFIRM_NUMBER.getButtonName())) {
			//인증번호 확인 눌렀을때
			this.certification();
	
			if(this.confirmCheck) {
				this.searchPwdPanel.confirmNumberCheck();
			} 
			
		//확인버튼	
		} else if(buttonName.equals(LoginPanelEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName())) {
			//아이디를 체크하고 그 결과 값을 가지고 하단으로 안내려 보낼 수 있다면 
			if(this.idCheck()) {
				return;
			}
			
			if(this.emailCheck()) {
				return;
			}
			
			if(this.certification()) {
				return;
			}
	
			//모두다 확인이 되면 
			if(this.idCheck && this.emailCheck && this.confirmCheck &&
					this.searchPwdPanel.isEmailConfirmLimitTime() 
					&& this.searchPwdPanel.isConfirmNumberSuccess()) {
				//인증이 다됬을 때 넘어감
				this.searchPwdPanel.checkIdEmail();
			}
		}
	}//액션 끝남
	
	@Override
	public void keyReleased(KeyEvent e) {
		JTextField textField = (JTextField) e.getSource();
		String id = textField.getName();
		
		if(id.equals("searchIdTextField")) {
			this.idCheck();
		} else {
			this.emailCheck();
		}
	}
	
	//아이디 검사
	public boolean idCheck() { 
		JTextField idTextField = this.searchPwdPanel.getSearchIdTextField(); 
		String id = idTextField.getText();
		
		if(id.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("아이디입력해주세요");
			this.idCheck = false;
			return true;
		}
		
		if(!RegexCheck.idRegexCheck(id)) {
			this.searchPwdPanel.userNumberMsg("<html>영소문자, 특수문자구분<br>다시확인하세요<br></html>");
			this.idCheck = false;
			return true;
		}
		
		this.searchPwdPanel.userNumberMsgReset();
		this.idCheck = true;	
		return false;
	}
	
	//이메일 검사
	public boolean emailCheck() { 
		JTextField emailTextField = this.searchPwdPanel.getSearchemailTextField(); 
		String email = emailTextField.getText();
		
		if(email.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("이메일 작성해주세요.");
			this.emailCheck = false;
			return true;
		}
		
		if(!RegexCheck.emailRegexCheck(email)) {
			this.searchPwdPanel.userNumberMsg("이메일 형식 체크해주세요!");
			this.emailCheck = false;
			return true;
		} 
		
		this.searchPwdPanel.userNumberMsgReset();
		this.emailCheck = true;
		return false;
	}
	
	//인증번호 검사
	public boolean certification() { 
		JTextField confirmNumberTextField = this.searchPwdPanel.getSearchConfirmTextField();
		String confirmNumber = confirmNumberTextField.getText();
		
		if(confirmNumber.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("인증번호 써주세요");
			this.confirmCheck = false;
			return true;
		} 
		
		this.searchPwdPanel.userNumberMsgReset();
		this.confirmCheck = true;
		this.searchPwdPanel.setEmailConfirmLimitTime(true);
		return false;
	}
}