package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import actions.adapters.Adapters;
import enums.frames.SearchIDEnum;
import frames.searchFrames.SearchIdPanel;
import utility.RegexCheck;

public class FindIDAction  extends Adapters {
	private SearchIdPanel searchIdPanel;
	private boolean isNameCheck = false;
	private boolean isEmailCheck = false;

	public FindIDAction(SearchIdPanel searchIdPanel){
		this.searchIdPanel = searchIdPanel;
	}

	//취소버튼, 확인버튼
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton)e.getSource()).getName();
		
		if(buttonName.equals(SearchIDEnum.BUTTON_NAME_BACK.getButtonName())) {
			this.searchIdPanel.doCancleButton();
		} else if(buttonName.equals(SearchIDEnum.BUTTON_NAME_CONFIRM.getButtonName())) {
			
			//이름체크메소드가 끝났을때 밑으로실행시지말기
			if(this.checkName()) {
				return;
			}
			
			if(this.checkEmail()) {
				return;
			}
			
			if(this.isNameCheck && this.isEmailCheck) {
				this.searchIdPanel.doCheckId();
			}
		}
	}
	
	//실시간 타이핑 에러메시지 송출
	@Override
	public void keyReleased(KeyEvent e) {
		String source = e.getSource().toString();
		if(source.contains("nameTextField")) {
			this.checkName();
		} else if(source.contains("emailTextField")) {
			this.checkEmail();
		}
	}
	
	//이름 정합성 검사
	public boolean checkName() {
		JTextField getNameTextField = this.searchIdPanel.getNameTextField();
		String name = getNameTextField.getText();
		
		//네임 택스트필드 정합성 검사
		if(name.isEmpty()) {
			this.searchIdPanel.errorMsg("이름 입력하세요");
			this.isEmailCheck = false;
			return true;
		}
		
		if(!RegexCheck.nameRegecCheck(name)) {
			this.searchIdPanel.errorMsg("한글만 입력됩니다.");
			this.isEmailCheck = false;
			return true;
		} 
		
		this.searchIdPanel.errorMsgReset();
		this.isNameCheck = true;
		return false;
	}
	
	//이메일 정합성 검사
	public boolean checkEmail() {
		String email = this.searchIdPanel.getEmailTextField().getText();
		
		//이메일 필드가 비어있을때 에러메시지 출력
		if(email.isEmpty()) {
			this.searchIdPanel.errorMsg("이메일 입력하세요");
			this.isEmailCheck = false;
			return true;
		}
		
		if(!RegexCheck.emailRegexCheck(email)) {
			this.searchIdPanel.errorMsg("이메일 형식 오류입니다");
			this.isEmailCheck = false;
			return true;
		} 
		
		this.searchIdPanel.errorMsgReset();
		this.isEmailCheck = true;
		return false;
	
	}
}
