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

	//��ҹ�ư, Ȯ�ι�ư
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton)e.getSource()).getName();
		this.checkName();
		this.checkEmail();
		
		//   if ��� ��ư �׼�      if else Ȯ�ι�ư	
		if(buttonName.equals(SearchIdEnum.BUTTON_NAME_BACK.getButtonName())) {
			this.searchIdPanel.doCancleButton();
		} else if(buttonName.equals(SearchIdEnum.BUTTON_NAME_CONFIRM.getButtonName())) {
			
			if(this.isNameCheck && this.isEmailCheck) {
				this.searchIdPanel.doCheckId();
			}
		}
	}
	
	//�ǽð� Ÿ���� �����޽��� ����
	@Override
	public void keyReleased(KeyEvent e) {
		String source = e.getSource().toString();
		if(source.contains("nameTextField")) {
			this.checkName();
		} else if(source.contains("emailTextField")) {
			this.checkEmail();
		}
	}
	
	//�̸� ���ռ� �˻�
	public void checkName() {
		String name = this.searchIdPanel.getNameTextField().getText();
		
		//���� �ý�Ʈ�ʵ� ���ռ� �˻�
		if(name.isEmpty()) {
			this.searchIdPanel.errorMsg("�̸� �Է��ϼ���");
			this.isEmailCheck = false;
			return;
		}
		
		if(!RegexCheck.nameRegecCheck(name)) {
			this.searchIdPanel.errorMsg("�ѱ۸� �Էµ˴ϴ�.");
			this.isEmailCheck = false;
			return;
		} 
		
		this.searchIdPanel.errorMsgReset();
		this.isNameCheck = true;
	}
	
	//�̸��� ���ռ� �˻�
	public void checkEmail() {
		String email = this.searchIdPanel.getEmailTextField().getText();
		//�̸��� �ʵ尡 ��������� �����޽��� ���
		if(email.isEmpty()) {
			this.searchIdPanel.errorMsg("�̸��� �Է��ϼ���");
			this.isEmailCheck = false;
			return;
		}
		
		if(!RegexCheck.emailRegexCheck(email)) {
			this.searchIdPanel.errorMsg("�̸��� ���� �����Դϴ�");
			this.isEmailCheck = false;
			return;
		} 
		
		this.searchIdPanel.errorMsgReset();
		this.isEmailCheck = true;
	
	}
}
