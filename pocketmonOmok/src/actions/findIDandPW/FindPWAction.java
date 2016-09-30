package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import actions.adapters.Adapters;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginSizesEnum;
import utility.RegexCheck;
import enums.frames.LoginSizesEnum;
import frames.searchFrames.SearchPwdPanel;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	private String checkMsg;
	
	private String emailTextCheck;
	private String idTextCheck;
	private String confirmNumberCheck;
	
	
	private String idText;
	private String emailText;
	private String confirmNumberText;
	
//	private boolean emailCheck;
//	private boolean idCheck;
//	
//	private boolean confirmNumberCheck;
	
	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		this.searchPwdPanel = searchPwdPanel;
		this.idTextCheck = "sujin";
		this.emailTextCheck = "tnwls1@naver.com";
		this.confirmNumberCheck = "0";
//		this.emailCheck         = false;
//		this.idCheck            = false;
//		this.confirmNumberCheck = false;  
		
		
	}
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
		if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName())) {
			this.searchPwdPanel.doCancelButton();
			System.out.println(this.searchPwdPanel.getSearchPwdFrame());
			System.out.println("���");
		//������ư
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.searchPwdPanel.getCerfication();
			System.out.println("������ư");
		//Ȯ�ι�ư	
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName())) {
			this.idText = searchPwdPanel.getSearchIdTextField().getText();
			this.emailText  = searchPwdPanel.getSearchemailTextField().getText();
			this.confirmNumberText = searchPwdPanel.getSearchConfirmTextField().getText();
			
			if(this.idText.isEmpty()) {
				this.checkMsg = "���̵� �Է� ����.";
				this.searchPwdPanel.userNumberMsg(checkMsg);
				System.out.println("���̵�");
			} else if(this.emailText.isEmpty() && !RegexCheck.emailRegexCheck(emailText)) {
				this.checkMsg = "�̸��� �Է¿���.";
				System.out.println("�̸���");
				this.searchPwdPanel.userNumberMsg(checkMsg);
			} else if(this.confirmNumberText.isEmpty() && !this.confirmNumberCheck.equals(confirmNumberText)) {
				this.checkMsg = "������ȣ �Է¿���.";
				this.searchPwdPanel.userNumberMsg(checkMsg);
				System.out.println("������ȣ");
			} else {
				this.searchPwdPanel.getSearchPwdFrame().doCheckButton();
				
				
				
			}
		}
	}
	//�ǽð����� ���� �޼��� ����
		public void keyReleased(KeyEvent e) {
			String inFo = e.getSource().toString();
			//Ű�����ʿ� ������ ���� �޾� ���ڿ��� ��ȯ�Ͽ� inFo�� ����
			this.idText = searchPwdPanel.getSearchIdTextField().getText();
			this.emailText= searchPwdPanel.getSearchemailTextField().getText();
			
			if(inFo.contains("searchIdTextField")) {
					this.idCheck();
					System.out.println("����Ф� ���̵�");
			
			} else if (inFo.contains("searchemailTextField")) {
				this.emailCheck();
			}    
		}
		
		public void idCheck() {
			if(!this.idTextCheck.equals(idText)) {
				checkMsg = "<html>�Է��������� ��ġ���� �ʽ��ϴ�." 
						+"<br>�ٽ�Ȯ���ϼ���<br></html>";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}if(!RegexCheck.idRegexCheck(idText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "<html>���ҹ���, Ư�����ڱ���" 
						+"<br>�ٽ�Ȯ���ϼ���<br></html>";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			} if (this.idTextCheck.equals(idText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "Ȯ��!";
				this.searchPwdPanel.userNumberMsg(checkMsg);
//				this.searchPwdPanel.userNumberMsgReset();
			}
			
		}
		public void emailCheck() {
			if(this.emailText.isEmpty()) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "�̸��� �ۼ����ּ���.";
				System.out.println("����³�����");
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}if(!RegexCheck.emailRegexCheck(emailText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "�̸��� ���� üũ���ּ���!";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			} if (this.emailTextCheck.equals(emailText) ){
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "Ȯ��!";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}
		}
		
		public void ConfirmCheck() {
			if(this.confirmNumberText.isEmpty()) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "������ȣ ���ּ���";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}if(this.confirmNumberCheck.equals(confirmNumberText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "������ȣ�� ��ġ���� �ʽ��ϴ�.";
				System.out.println("���Ⱑ ��������???����.");
				this.searchPwdPanel.userNumberMsg(checkMsg);
			} if (this.confirmNumberCheck.equals(confirmNumberText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "Ȯ��!";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}
		}
}


