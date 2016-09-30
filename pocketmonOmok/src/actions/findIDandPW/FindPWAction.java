package actions.findIDandPW;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import actions.adapters.Adapters;
import enums.frames.LoginSizesEnum;
import frames.searchFrames.SearchPwdPanel;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	private SearchPwdPanel searchPwdFrame;
	

	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		this.searchPwdPanel = searchPwdPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		//��ҹ�ư ��������
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
		}
	}
	
	public void findPwdAction() {

	
	//�޼��� ���
	
	//3�� ���ư��¤���

//		try {
//			this.loginPanel.getBasicFrame().getClientOS().defaultWriteObject();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}
}
