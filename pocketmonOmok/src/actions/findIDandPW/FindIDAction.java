package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import actions.adapters.Adapters;
import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.SearchIdEnum;
import frames.BasicFrame;
import frames.searchFrames.SearchIdFrame;
import frames.searchFrames.SearchIdPanel;
import frames.searchFrames.SearchIdResultPanel;
import utility.RegexCheck;

public class FindIdAction  extends Adapters {
	private SearchIdPanel searchIdPanel;
	private SearchIdResultPanel searchIdResultPanel;
	
	private boolean isNameCheck = false;
	private boolean isEmailCheck = false;
	
	public FindIdAction(SearchIdPanel searchIdPanel){
		this.searchIdPanel = searchIdPanel;
	}

	//취소버튼, 확인버튼
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton)e.getSource()).getName();
		this.checkName();
		this.checkEmail();
		
		//   if 취소 버튼 액션      if else 확인버튼	
		if(buttonName.equals(SearchIdEnum.BUTTON_NAME_BACK.getButtonName())) {
			this.searchIdPanel.doCancleButton();
		} else if(buttonName.equals(SearchIdEnum.BUTTON_NAME_CONFIRM.getButtonName())) {
			
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
	public void checkName() {
		String name = this.searchIdPanel.getNameTextField().getText();
		
		//네임 택스트필드 정합성 검사
		if(name.isEmpty()) {
			this.searchIdPanel.errorMsg("이름 입력하세요");
			this.isEmailCheck = false;
			return;
		}
		
		if(!RegexCheck.nameRegecCheck(name)) {
			this.searchIdPanel.errorMsg("한글만 입력됩니다.");
			this.isEmailCheck = false;
			return;
		} 
		
		this.searchIdPanel.errorMsgReset();
		this.isNameCheck = true;
	}
	
	//이메일 정합성 검사
	public void checkEmail() {
		String email = this.searchIdPanel.getEmailTextField().getText();
		//이메일 필드가 비어있을때 에러메시지 출력
		if(email.isEmpty()) {
			this.searchIdPanel.errorMsg("이메일 입력하세요");
			this.isEmailCheck = false;
			return;
		}
		
		if(!RegexCheck.emailRegexCheck(email)) {
			this.searchIdPanel.errorMsg("이메일 형식 오류입니다");
			this.isEmailCheck = false;
			return;
		} 
		
		this.searchIdPanel.errorMsgReset();
		this.isEmailCheck = true;
	
	}
}
