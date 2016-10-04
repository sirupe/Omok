package actions.findIDandPW;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import actions.adapters.Adapters;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginPanelEnum;
import utility.RegexCheck;
import enums.frames.LoginPanelEnum;
import frames.searchFrames.SearchPwdPanel;
import sun.util.logging.resources.logging;

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
			
		//������ư�� ��������
		} else if(buttonName.equals(LoginPanelEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.emailCheck();
			
			//�̸����� �ְ� ���Ŀ� ������
			if(this.emailCheck) {
				this.searchPwdPanel.getCerfication();
			} 

		} else if(buttonName.equals(LoginPanelEnum.BUTTON_NAVE_CONFIRM_NUMBER.getButtonName())) {
			//������ȣ Ȯ�� ��������
			this.certification();
	
			//TODO 
			if(this.confirmCheck) {
				this.searchPwdPanel.confirmNumberCheck();
			} 
			
		//Ȯ�ι�ư	

		} else if(buttonName.equals(LoginPanelEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName())) {
			//���̵� üũ�ϰ� �� ��� ���� ������ �ϴ����� �ȳ��� ���� �� �ִٸ� 
			if(this.idCheck()) {
				return;
			}
			
			if(this.emailCheck()) {
				return;
			}
			
			if(this.certification()) {
				return;
			}
	
			//��δ� Ȯ���� �Ǹ� 
			if(this.idCheck && this.emailCheck && this.confirmCheck &&
					this.searchPwdPanel.isEmailConfirmLimitTime() 
					&& this.searchPwdPanel.isConfirmNumberSuccess()) {
				//������ �ى��� �� �Ѿ
				this.searchPwdPanel.checkIdEmail();
			}
		}
	}//�׼� ����
	
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
	
	//���̵� �˻�
	public boolean idCheck() { 
		JTextField idTextField = this.searchPwdPanel.getSearchIdTextField(); 
		String id = idTextField.getText();
		
		if(id.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("���̵��Է����ּ���");
			this.idCheck = false;
			return true;
		}
		
		if(!RegexCheck.idRegexCheck(id)) {
			this.searchPwdPanel.userNumberMsg("<html>���ҹ���, Ư�����ڱ���<br>�ٽ�Ȯ���ϼ���<br></html>");
			this.idCheck = false;
			return true;
		}
		
		this.searchPwdPanel.userNumberMsgReset();
		this.idCheck = true;	
		return false;
	}
	
	//�̸��� �˻�
	public boolean emailCheck() { 
		JTextField emailTextField = this.searchPwdPanel.getSearchemailTextField(); 
		String email = emailTextField.getText();
		
		if(email.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("�̸��� �ۼ����ּ���.");
			this.emailCheck = false;
			return true;
		}
		
		if(!RegexCheck.emailRegexCheck(email)) {
			this.searchPwdPanel.userNumberMsg("�̸��� ���� üũ���ּ���!");
			this.emailCheck = false;
			return true;
		} 
		
		this.searchPwdPanel.userNumberMsgReset();
		this.emailCheck = true;
		return false;
		
	}
	
	//������ȣ �˻�
	public boolean certification() { 
		JTextField confirmNumberTextField = this.searchPwdPanel.getSearchConfirmTextField();
		String confirmNumberNumber = confirmNumberTextField.getText();
		
		if(confirmNumberNumber.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("������ȣ ���ּ���");
			this.confirmCheck = false;
			return true;
		} 
		
		this.searchPwdPanel.userNumberMsgReset();
		this.confirmCheck = true;
		this.searchPwdPanel.setEmailConfirmLimitTime(true);
		return false;
	}

}
