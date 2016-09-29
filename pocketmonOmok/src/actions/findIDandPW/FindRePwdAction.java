package actions.findIDandPW;

import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import actions.adapters.Adapters;import enums.frames.LoginSizesEnum;
import frames.serchFrames.SearchRePwdPanel;
import utility.RegexCheck;

public class FindRePwdAction extends Adapters {
	private SearchRePwdPanel searchRePwdPanel;
	
	private String pw;
	private String rePwd;
	private String checkMsg;
	
	
	public FindRePwdAction(SearchRePwdPanel searchRePwdPanel) {
		this.searchRePwdPanel = searchRePwdPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
		//��ҹ�ư�� ��������
		if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRMBUTTON.getButtonName())) {
			
			if(this.pw.isEmpty()) {
				this.checkMsg = "��й�ȣ�� �Է����ּ���";
				this.searchRePwdPanel.pwdMsgLabel(checkMsg);
				return;
			}
			//�ι�° ��й�ȣ
			if(this.rePwd.isEmpty()) {
				this.checkMsg = "���Էº�й�ȣ�� �Է����ּ���";
				this.searchRePwdPanel.pwdMsgLabel(checkMsg);
				return;
			}
			//��й�ȣ�ؽ�Ʈ���� ���ռ��� ���� ������
			if(!RegexCheck.passwdRegexCheck(pw)) {
				this.checkMsg = "<html>���ҹ��� 1~6����," 
						+"<br>Ư������ �ϳ� �����Ͻʽÿ�<br></html>";
				this.searchRePwdPanel.pwdMsgLabel(checkMsg);
				return;
			}
			//���й�ȣ�ؽ�Ʈ���� ���ռ��� ���� ������
			if(!RegexCheck.passwdRegexCheck(rePwd)) {
				this.checkMsg = "<html>���ҹ��� 1~6����," 
						+"<br>Ư������ �ϳ� �����Ͻʽÿ�<br></html>";
				this.searchRePwdPanel.pwdMsgLabel(checkMsg);
				return;
			}	
			// ���Է�â�� �Է�â�� ��ġ�ϴµ� ���ռ��� ���� ������
			if(this.pw.equals(this.rePwd) && this.rePwd.equals(this.pw) && 
					!RegexCheck.passwdRegexCheck(pw) && !RegexCheck.passwdRegexCheck(rePwd)) {
					this.checkMsg = "<html>���ҹ��� 1~6����," 
						+"<br>Ư������ �ϳ� �����Ͻʽÿ�<br></html>";
						this.searchRePwdPanel.pwdMsgLabel(checkMsg);
						return;
			} 
			//��й�ȣ�� ���й�ȣ�� ���� ���� ������
			if(!pw.equals(this.searchRePwdPanel.getsearchRePwdText().getText())) {
					this.searchRePwdPanel.pwdMsgLabelReset();
					this.checkMsg = "<html>���й�ȣ�� �����ʽ��ϴ�." 
							+"<br>�ٽü������ּ���<br></html>";
					this.searchRePwdPanel.pwdMsgLabel(checkMsg);	
					return;
			} 
			if(this.pw.equals(this.rePwd)) {	
				System.out.println(this.searchRePwdPanel + " : pannel");
					this.searchRePwdPanel.getSearchPwdFrame().doSearchChangeConfirmPanel();
			} else {
				return;
			}
		}
	}
	
	//�ǽð����� ���� �޼��� ����
	public void keyReleased(KeyEvent e) {
		String inFo = e.getSource().toString();
		//Ű�����ʿ� ������ ���� �޾� ���ڿ��� ��ȯ�Ͽ� inFo�� ����
		
		if(inFo.contains("searchPwdText")) {
				this.pwCheck();
		
		} else if (inFo.contains("searchRePwdText")) {
			this.rePwdCheck();
		}	    
	}
	// ��й�ȣ üũ
	public void pwCheck() {
		this.pw = this.searchRePwdPanel.getSearchPwdText().getText();
		String checkMsg = null;
		
		if(!RegexCheck.passwdRegexCheck(pw)) {
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "<html>���ҹ��� 1~6����," 
					+"<br>Ư������ �ϳ� �����Ͻʽÿ�<br></html>";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		} else {
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "��й�ȣ ����!";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		}
	}
	
	// ���й�ȣ üũ
	public void rePwdCheck() {
		this.rePwd = this.searchRePwdPanel.getsearchRePwdText().getText();
		String checkMsg = null;
		
		if(!RegexCheck.passwdRegexCheck(rePwd)) {
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "<html>���ҹ��� 1~6����," 
					+"<br>Ư������ �ϳ� �����Ͻʽÿ�<br></html>";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		} else if (!rePwd.equals(this.searchRePwdPanel.getSearchPwdText().getText())) {
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "<html>��й�ȣȮ�� ���ּ���!" 
					+"<br>��ġ�����ʽ��ϴ�.<br></html>";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		} else {
			//���� ���Դ� �޼��� ����
			this.searchRePwdPanel.pwdMsgLabelReset();
			checkMsg = "<html>��й�ȣ ��ġ!" 
					+"<br>�α������ּ���~!<br></html>";
			this.searchRePwdPanel.pwdMsgLabel(checkMsg);
		}
	}
}
