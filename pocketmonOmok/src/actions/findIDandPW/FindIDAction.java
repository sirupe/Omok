package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import actions.adapters.Adapters;
import enums.frames.SearchIDEnum;
import frames.searchFrames.SearchIdPanel;
import frames.searchFrames.SearchIdResultPanel;
import utility.RegexCheck;

public class FindIDAction  extends Adapters {
	private SearchIdPanel searchIdPanel;
	private boolean isNameCheck = false;
	private boolean isEmailCheck = false;
	
	public FindIDAction(SearchIdPanel searchIdPanel){
		this.searchIdPanel = searchIdPanel;
	}

	//��ҹ�ư, Ȯ�ι�ư
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton)e.getSource()).getName();
		
		if(buttonName.equals(SearchIDEnum.BUTTON_NAME_BACK.getButtonName())) {
			this.searchIdPanel.doCancleButton();
		} else if(buttonName.equals(SearchIDEnum.BUTTON_NAME_CONFIRM.getButtonName())) {
			//�̸�üũ�޼ҵ尡 �������� �����ν����������
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
	public boolean checkName() {
		JTextField getNameTextField = this.searchIdPanel.getNameTextField();
		String name = getNameTextField.getText();
		
		//���� �ý�Ʈ�ʵ� ���ռ� �˻�
		if(name.isEmpty()) {
			this.searchIdPanel.errorMsg("�̸� �Է��ϼ���");
			this.isEmailCheck = false;
			return true;
		}
		
		if(!RegexCheck.nameRegecCheck(name)) {
			this.searchIdPanel.errorMsg("�ѱ۸� �Էµ˴ϴ�.");
			this.isEmailCheck = false;
			return true;
		} 
		
		this.searchIdPanel.errorMsgReset();
		this.isNameCheck = true;
		return false;
	}
	
	//�̸��� ���ռ� �˻�
	public boolean checkEmail() {
		String email = this.searchIdPanel.getEmailTextField().getText();
		//�̸��� �ʵ尡 ��������� �����޽��� ���
		if(email.isEmpty()) {
			this.searchIdPanel.errorMsg("�̸��� �Է��ϼ���");
			this.isEmailCheck = false;
			return true;
		}
		
		if(!RegexCheck.emailRegexCheck(email)) {
			this.searchIdPanel.errorMsg("�̸��� ���� �����Դϴ�");
			this.isEmailCheck = false;
			return true;
		} 
		
		this.searchIdPanel.errorMsgReset();
		this.isEmailCheck = true;
		return false;
	
	}
}
