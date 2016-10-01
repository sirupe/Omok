package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;

import actions.adapters.Adapters;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.SearchIDEnum;
import frames.searchFrames.SearchIdPanel;
import utility.RegexCheck;

public class FindIDAction  extends Adapters {
	private SearchIdPanel searchIdPanel;
	
	private String checkMsg;
	private String name;
	private String email;
	
	private boolean isNameCheck;
	private boolean isEmailCheck;
	
	public FindIDAction(SearchIdPanel searchIdPanel){
		this.searchIdPanel = searchIdPanel;
		this.isNameCheck = false;
		this.isEmailCheck = false;	
	}

	//취소버튼, 확인버튼b p
	@Override
	public void actionPerformed(ActionEvent e) {
		//버튼 액션	
		String buttonName = ((JButton)e.getSource()).getName();
		if(buttonName.equals(SearchIDEnum.BUTTON_NAME_BACK.getButtonName())) {
			this.searchIdPanel.doCancleButton();
		} else if(buttonName.equals(SearchIDEnum.BUTTON_NAME_CONFIRM.getButtonName())) {
			this.name = this.searchIdPanel.getNameTextField().getText();
			this.email = this.searchIdPanel.getEmailTextField().getText();
			if(!this.name.isEmpty() && !this.email.isEmpty()) {
				if(this.isNameCheck && this.isEmailCheck) {
					this.searchIdPanel.getSearchIdFrame().doConfirmButton();
					this.findIdAction();
					System.out.println("확인버튼눌러서 PersonDTO로가야한다!!!");
				}
			} else if(!this.email.isEmpty()) {
				this.checkEmail();
			} else {
				this.checkEmail();
			}
		}
	}	
	//실시간 타이핑 에러메시지 송출
	@Override
	public void keyReleased(KeyEvent e) {
		String source = e.getSource().toString();
		if(source.contains("nameTextField")) {
			this.chechkName();
		} else if(source.contains("emailTextField")) {
			this.checkEmail();
		} else
			this.chechkName();
	}
	
	//이름 정합성 검사
	public void chechkName() {
		String str = this.searchIdPanel.getNameTextField().getText();
		this.name = str.isEmpty() ? null : str;
		this.checkMsg = null;
		if(this.name == null) {
			this.checkMsg = "이름 입력하세요";
			this.searchIdPanel.errorMsg(checkMsg);
			return;
		}
		
		if(RegexCheck.nameRegecCheck(this.name)) {
			this.isNameCheck = true;
			this.searchIdPanel.errorMsgReset();
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_ID);
			personalDTO.setUserAction(UserActionEnum.USER_FIND_ID);
			personalDTO.setUserName(this.searchIdPanel.getNameTextField().getText());
			try {
				this.searchIdPanel.getSearchIdFrame().getBasicFrame().getClientOS().writeObject(personalDTO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			this.checkMsg = "한글만 입력됩니다.";
			this.searchIdPanel.errorMsg(checkMsg);
		}
	}
	
	//이메일 정합성 검사
	public void checkEmail() {
		String str = this.searchIdPanel.getEmailTextField().getText();
		this.email = str.isEmpty() ? null : str;
		this.checkMsg = null;
		if(this.email == null) {
			this.checkMsg = "이메일 입력하세요";
			this.searchIdPanel.errorMsg(checkMsg);
			return;
		}
		if(RegexCheck.emailRegexCheck(this.email)) {
			this.isEmailCheck = true;
			this.searchIdPanel.errorMsgReset();
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_ID);
			personalDTO.setUserAction(UserActionEnum.USER_FIND_ID);
			personalDTO.setUserEmail(this.searchIdPanel.getEmailTextField().getText());
			try {
				this.searchIdPanel.getSearchIdFrame().getBasicFrame().getClientOS().writeObject(personalDTO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			this.checkMsg = "이메일 형식 오류입니다";
			this.searchIdPanel.errorMsg(checkMsg);
		}
	}
	
	//이름, 이메일 맞게입력하고 확인버튼 눌렀을시
	public void findIdAction() {
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_ID);
		personalDTO.setUserAction(UserActionEnum.USER_FIND_ID);
		personalDTO.setUserEmail(this.email.toString());
		personalDTO.setUserName(this.name);
		System.out.println("이얍");
		try {
			this.searchIdPanel.getSearchIdFrame().getBasicFrame().getClientOS().writeObject(personalDTO);
			System.out.println("try");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
