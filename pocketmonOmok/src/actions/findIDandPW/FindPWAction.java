package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import actions.adapters.Adapters;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginSizesEnum;
import utility.RegexCheck;
import enums.frames.LoginSizesEnum;
import frames.searchFrames.SearchPwdPanel;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	
	private boolean emailCheck;
	private boolean idCheck;
	private boolean confirmCheck;

	
	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		this.searchPwdPanel = searchPwdPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
		if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName())) {
			this.searchPwdPanel.doCancelButton();
			
		//인증메일보내기
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.emailCheck();
			
			//이메일이 있고 형식에 맞을때
			if(this.emailCheck) {
				this.searchPwdPanel.getCerfication();
			}
			
			
		//확인버튼	
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName())) {
			
			this.certification();
			this.emailCheck();
			this.idCheck();
			
			//모두다 확인이 되면 
			if(this.idCheck && this.emailCheck && this.confirmCheck) {
				this.searchPwdPanel.getChangePanel();
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
	public void idCheck() { 
		JTextField idTextField = this.searchPwdPanel.getSearchIdTextField(); 
		String id = idTextField.getText();
		
		System.out.println("아이디 테스트 : " + id);
		
		if(id.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("아이디입력해주세요");
			this.idCheck = false;
			return;
		}
		
		if(!RegexCheck.idRegexCheck(id)) {
			this.searchPwdPanel.userNumberMsg("<html>영소문자, 특수문자구분<br>다시확인하세여<br></html>");
			this.idCheck = false;
			return;
		}
		
		this.searchPwdPanel.userNumberMsgReset();
		this.idCheck = true;	
	}
	
	//이메일 검사
	public void emailCheck() { 
		JTextField emailTextField = this.searchPwdPanel.getSearchemailTextField(); 
		String email = emailTextField.getText();
		
		
		if(email.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("이메일 작성해주세요.");
			this.emailCheck = false;
			return;
		}
		
		if(!RegexCheck.emailRegexCheck(email)) {
			this.searchPwdPanel.userNumberMsg("이메일 형식 체크해주세요!");
			this.emailCheck = false;
			return;
		} 
		
		this.searchPwdPanel.userNumberMsgReset();
		this.emailCheck = true;
		
	}
	
	//인증번호 검사
	public void certification() { 
		JTextField confirmNumberTextField = this.searchPwdPanel.getSearchConfirmTextField();
		String confirmNumberNumber = confirmNumberTextField.getText();
		
		if(confirmNumberNumber.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("인증번호 써주세요");
			this.confirmCheck = false;
			return;
		}
	
		this.searchPwdPanel.userNumberMsgReset();
		this.confirmCheck = true;
	}
}
