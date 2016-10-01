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
			
		//�������Ϻ�����
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.emailCheck();
			
			//�̸����� �ְ� ���Ŀ� ������
			if(this.emailCheck) {
				this.searchPwdPanel.getCerfication();
			}
			
			
		//Ȯ�ι�ư	
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName())) {
			
			this.certification();
			this.emailCheck();
			this.idCheck();
			
			//��δ� Ȯ���� �Ǹ� 
			if(this.idCheck && this.emailCheck && this.confirmCheck) {
				this.searchPwdPanel.getChangePanel();
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
	public void idCheck() { 
		JTextField idTextField = this.searchPwdPanel.getSearchIdTextField(); 
		String id = idTextField.getText();
		
		System.out.println("���̵� �׽�Ʈ : " + id);
		
		if(id.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("���̵��Է����ּ���");
			this.idCheck = false;
			return;
		}
		
		if(!RegexCheck.idRegexCheck(id)) {
			this.searchPwdPanel.userNumberMsg("<html>���ҹ���, Ư�����ڱ���<br>�ٽ�Ȯ���ϼ���<br></html>");
			this.idCheck = false;
			return;
		}
		
		this.searchPwdPanel.userNumberMsgReset();
		this.idCheck = true;	
	}
	
	//�̸��� �˻�
	public void emailCheck() { 
		JTextField emailTextField = this.searchPwdPanel.getSearchemailTextField(); 
		String email = emailTextField.getText();
		
		
		if(email.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("�̸��� �ۼ����ּ���.");
			this.emailCheck = false;
			return;
		}
		
		if(!RegexCheck.emailRegexCheck(email)) {
			this.searchPwdPanel.userNumberMsg("�̸��� ���� üũ���ּ���!");
			this.emailCheck = false;
			return;
		} 
		
		this.searchPwdPanel.userNumberMsgReset();
		this.emailCheck = true;
		
	}
	
	//������ȣ �˻�
	public void certification() { 
		JTextField confirmNumberTextField = this.searchPwdPanel.getSearchConfirmTextField();
		String confirmNumberNumber = confirmNumberTextField.getText();
		
		if(confirmNumberNumber.isEmpty()) {
			this.searchPwdPanel.userNumberMsg("������ȣ ���ּ���");
			this.confirmCheck = false;
			return;
		}
	
		this.searchPwdPanel.userNumberMsgReset();
		this.confirmCheck = true;
	}
}
