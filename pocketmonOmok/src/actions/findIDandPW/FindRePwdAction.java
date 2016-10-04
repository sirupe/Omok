package actions.findIDandPW;

import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

import actions.adapters.Adapters;import enums.frames.LoginPanelEnum;
import frames.searchFrames.SearchRePwdPanel;
import utility.RegexCheck;

public class FindRePwdAction extends Adapters {
	private SearchRePwdPanel searchRePwdPanel;
	
	
	private boolean pwdCheck;
	private boolean pwdReCheck;
	
	
	public FindRePwdAction(SearchRePwdPanel searchRePwdPanel) {
		this.searchRePwdPanel = searchRePwdPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getName();
		
		//Ȯ�� ��ư ��������
		if(buttonName.equals(LoginPanelEnum.BUTTON_NAME_SEARCH_CONFIRMBUTTON.getButtonName())) {
			if(this.pwCheck()) {
				return;
			}
			
			if(this.rePwdCheck()) {
				return;
			}
			
			//��� Ȯ���� �Ǹ�
			if(this.pwdCheck && this.pwdReCheck) {
				this.searchRePwdPanel.getSearchPwdText().setEditable(false);
				this.searchRePwdPanel.getsearchRePwdText().setEditable(false);
				this.searchRePwdPanel.pwdChange();
				
			}
		}
	}
	
	//�ǽð����� ���� �޼��� ����
	public void keyReleased(KeyEvent e) {
		JTextField textField = (JTextField) e.getSource();
		String pwdChange = textField.getText();
		
		if(pwdChange.contains("searchPwdText")) {
				this.pwCheck();
		
		} else {
			this.rePwdCheck();
		}
	}
	
	// ��й�ȣ üũ
	public boolean pwCheck() {

		JTextField TextField = this.searchRePwdPanel.getSearchPwdText();
		String pwd = TextField.getText();
		
		
		if(pwd.isEmpty()) {
			this.searchRePwdPanel.pwdMsgLabel("��й�ȣ �Է����ּ���");
			this.pwdCheck = false;
			return true;
		} 
		
		if(!RegexCheck.passwdRegexCheck(pwd)) {
			this.searchRePwdPanel.pwdMsgLabel("<html>���ҹ��� 1~6����," 
					+"<br>Ư������ �ϳ� �����Ͻʽÿ�<br></html>");
			this.pwdCheck = false;
			return true;
		}
		this.searchRePwdPanel.pwdMsgLabelReset();
		this.pwdCheck = true;
		return false;
	}
	
	// ���й�ȣ üũ
	public boolean rePwdCheck() {
		JTextField textField = this.searchRePwdPanel.getsearchRePwdText();
		JTextField TextField = this.searchRePwdPanel.getSearchPwdText();
		String rePwd = textField.getText();
		String pwd = TextField.getText();
		
		
		if(rePwd.isEmpty()) {
			this.searchRePwdPanel.pwdMsgLabel("��й�ȣ �Է����ּ���");
			this.pwdReCheck = false;
			return true;
		} 
		
		if(!RegexCheck.passwdRegexCheck(rePwd)) {
			this.searchRePwdPanel.pwdMsgLabel( "<html>���ҹ��� 1~6����," 
					+"<br>Ư������ �ϳ� �����Ͻʽÿ�<br></html>");
			this.pwdReCheck = false;
			return true;
		}
		
		if (!rePwd.equals(pwd)) {
			this.searchRePwdPanel.pwdMsgLabel("<html>��й�ȣȮ�� ���ּ���!" 
					+"<br>��ġ�����ʽ��ϴ�.<br></html>");
			this.pwdReCheck = false;
			return true;
		} 
			this.searchRePwdPanel.pwdMsgLabel("<html>��й�ȣ ��ġ�մϴ�.!");
			this.pwdReCheck = true;
			return false;
	}
}
