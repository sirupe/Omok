package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import actions.adapters.Adapters;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginSizesEnum;
import frames.serchFrames.SearchPwdPanel;
import utility.RegexCheck;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	private String checkMsg;
	private String emailCheck;
	private String idCheck;
	private String confirmNumberText; // ������ȣ������ ��...
	//private boolean checkNumberText;
	private String checkNumberText; //������ȣ �޾ƿ���
	
	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		this.searchPwdPanel = searchPwdPanel;
		this.checkNumberText = "0";
		
		//this.searchPwdPanel.addKeyAction(this.searchPwdPanel.getSearchConfirmTextField(), "confirmNumberText");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
//��ҹ�ư �������� -- >Ȩ����
		if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName())) {
			//1. ��� ��ư�� ���ȴ�.
			//2. searchPwdFrame�� ���ش�.
			//3. ���� panel�ۿ� �𸥴�. �׷��� ������ panel�� �ִ� �޼ҵ带 ������ �� �� �ۿ� ����.
			//4. panel���� searchFrame�� �����ִ� �޼ҵ带 �����.
			//5. frame���� basic�� ���� �� �ִ� ȣ���� �ϰ� �ڱ⸦ ���� �� �ִ� ȣ���� �Ѵ�.
			this.searchPwdPanel.doCancelButton();
			
			
//���� ��ư ��������
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.searchPwdPanel.getCerfication(); //���������� �����ش� -- > action�� frame�� �𸣹Ƿ� panel���� �ҷ��´�,
		System.out.println();
//Ȯ�� ��ư�� ������ ������ �Ѿ�� �ƴϸ� �ƴ϶�� �����޼��� �����
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName())) {
			//this.searchPwdPanel.getSearchPwdFrame().doCheckButton(); //���й�ȣ �Է��ϴ� �гη� �Ѿ
			this.idCheck = searchPwdPanel.getSearchIdTextField().getText();
			this.emailCheck = searchPwdPanel.getSearchemailTextField().getText();
			this.confirmNumberText = searchPwdPanel.getSearchConfirmTextField().getText(); //������ȣ���°�
			
			if(this.confirmNumberText.isEmpty()) {
				this.searchPwdPanel.userInfoErrorLabelReset();
				this.checkMsg = "������ȣ �Է��ϼ���.";
	    		this.searchPwdPanel.userNumberMsg(checkMsg);
			}
			
			if(this.emailCheck.isEmpty()) {
				this.searchPwdPanel.userInfoErrorLabelReset();
				this.checkMsg = "�̸��� �Է� ��Ź�帳�ϴ�.";
	    		this.searchPwdPanel.userNumberMsg(checkMsg);
			}
			
			if(this.idCheck.isEmpty()) {
				this.searchPwdPanel.userInfoErrorLabelReset();
				this.checkMsg = "���̵� �Է� ��Ź�帳�ϴ�.";
	    		this.searchPwdPanel.userNumberMsg(checkMsg);
			}
			
			//������ȣ�� �´ٸ� -- > ���Է����� ����
			if(!this.confirmNumberText.equals(this.checkNumberText)) {
				this.searchPwdPanel.userInfoErrorLabelReset();
				this.checkMsg = "<html>������ȣ�� Ʋ�Ƚ��ϴ�." 
						+"<br>�ٽ�Ȯ�����ּ���!<br></html>";
	    		this.searchPwdPanel.userNumberMsg(checkMsg);
			}
			
			if(this.confirmNumberText.equals(this.checkNumberText)) {
				this.searchPwdPanel.getSearchConfirmTextField().setEditable(false);
				this.searchPwdPanel.getsearchCheckButton().setEnabled(false);
				this.searchPwdPanel.getSearchPwdFrame().doCheckButton();
				System.out.println("��y");
			}
		}
	}
	
	
	
	public void addKeyAction(JComponent comp, String Name) {
//		EmptyBorder border = JoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(Name);
//		comp.setBorder(border);
		comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addKeyListener(this);
		//this.add(comp);	
	}
	
	
	

}	