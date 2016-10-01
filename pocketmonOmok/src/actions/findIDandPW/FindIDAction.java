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

	//��ҹ�ư, Ȯ�ι�ưb p
	@Override
	public void actionPerformed(ActionEvent e) {
		//��ư �׼�	
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
					System.out.println("Ȯ�ι�ư������ PersonDTO�ΰ����Ѵ�!!!");
				}
			} else if(!this.email.isEmpty()) {
				this.checkEmail();
			} else {
				this.checkEmail();
			}
		}
	}	
	//�ǽð� Ÿ���� �����޽��� ����
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
	
	//�̸� ���ռ� �˻�
	public void chechkName() {
		String str = this.searchIdPanel.getNameTextField().getText();
		this.name = str.isEmpty() ? null : str;
		this.checkMsg = null;
		if(this.name == null) {
			this.checkMsg = "�̸� �Է��ϼ���";
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
			this.checkMsg = "�ѱ۸� �Էµ˴ϴ�.";
			this.searchIdPanel.errorMsg(checkMsg);
		}
	}
	
	//�̸��� ���ռ� �˻�
	public void checkEmail() {
		String str = this.searchIdPanel.getEmailTextField().getText();
		this.email = str.isEmpty() ? null : str;
		this.checkMsg = null;
		if(this.email == null) {
			this.checkMsg = "�̸��� �Է��ϼ���";
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
			this.checkMsg = "�̸��� ���� �����Դϴ�";
			this.searchIdPanel.errorMsg(checkMsg);
		}
	}
	
	//�̸�, �̸��� �°��Է��ϰ� Ȯ�ι�ư ��������
	public void findIdAction() {
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_ID);
		personalDTO.setUserAction(UserActionEnum.USER_FIND_ID);
		personalDTO.setUserEmail(this.email.toString());
		personalDTO.setUserName(this.name);
		System.out.println("�̾�");
		try {
			this.searchIdPanel.getSearchIdFrame().getBasicFrame().getClientOS().writeObject(personalDTO);
			System.out.println("try");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
