package frames.searchFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.findIDandPW.FindPWAction;
import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginPanelEnum;
import enums.frames.SearchIDEnum;
import enums.frames.SearchPwdEnum;
import frames.BasicFrame;
import omokGame.client.ClientAccept;
	
@SuppressWarnings("serial")
public class SearchPwdPanel extends JPanel {

	private JLabel searchIdLabel;
	private JLabel searchemailLabel;
	
	private JLabel searchAnswerMsgLabel;
	private JLabel searchTimeLabel;
	
	private JTextField searchIdTextField;
	private JTextField searchemailTextField;
	private JTextField searchConfirmTextField;
	
	private JButton searchConfirmButton; //���� ��ư
	private JButton searchCancelButton; //��� ��ư -- > Ȩ���� ���� ��ư
	private JButton searchCheckButton; // Ȯ�ι�ư
	private JButton checkNumberButton; // ���� ��ȣ�� ��ġ�ϴ��� ����Ȯ�� ��ư
	
	
	private SearchPwdFrame searchPwdFrame;
	private FindPWAction findPwdAction;
	
	private boolean isEmailConfirmLimitTime;
	private boolean isConfirmNumberSuccess;
	
	
	
	public SearchPwdPanel(SearchPwdFrame searchPwdFrame) throws IOException {
		
		this.searchPwdFrame = searchPwdFrame;
		this.findPwdAction = new FindPWAction(this);
		
		
	    this.setLayout(null);
	
		//�� ���� TODO
		this.searchIdLabel    = new JLabel("ID");
		this.searchemailLabel = new JLabel("Email");
		
		//		�ؽ�Ʈ �ʵ����
		this.searchIdTextField      = new JTextField(10);
		this.searchemailTextField   = new JTextField(10);
		this.searchConfirmTextField = new JTextField(10);
		
		
		this.add(searchIdLabel);
		this.add(searchemailLabel);
		
		//this.add(searchConfirmButton);
		
		//���̺� ��Ʈ - searchIdEnum ���� �ҷ��Խ��ϴ�.
		Font default_Font  = SearchIDEnum.LABELFONT_DEFAULT.getFont(); //�Ϲ�
		this.searchIdLabel.setFont(default_Font);
		this.searchemailLabel.setFont(default_Font);
		
		// �ؽ�Ʈ ��Ʈ
		this.searchemailTextField.setFont(default_Font);
		this.searchIdTextField.setFont(default_Font);
		this.searchConfirmTextField.setFont(default_Font);
		
		
		String searchAnswer = "";
		this.searchAnswerMsgLabel = new JLabel(searchAnswer);
		
		this.searchTimeLabel = new JLabel("3:00");
		this.searchTimeLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		this.searchTimeLabel.setVisible(false);
		
		//���̺�, �ؽ�Ʈ, ��ư �ҷ�����
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonPosition();
		this.userInfoErrorLabelReset(); 
		this.userNumberMsgReset();
		
		
		this.addKeyAction(this.searchIdTextField, "searchIdTextField");
		this.addKeyAction(this.searchemailTextField, "searchemailTextField");
		this.addKeyAction(this.searchConfirmTextField, "confirmNumberText");
		this.searchConfirmTextField.setVisible(false);
	}
	
	//�ʱ�ȭ�� �Ǿ ���� �ִ� searchErrorMsg�� �޼����� �ҷ��´�.
	public void userInfoErrorLabelReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	
	
	//������ȣ�� �߼� �Ǿ��ٸ� ��Ÿ���� �޼���
	public void userNumberMsg(String searchAnswer){
		this.setLayout(null);
		this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchAnswerMsgLabel.setOpaque(false);
		this.add(this.searchAnswerMsgLabel);
		
		this.searchAnswerMsgLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.searchAnswerMsgLabel.setForeground(Color.blue);
		this.searchAnswerMsgLabel.setText(searchAnswer);
	}
	
	public void userNumberMsgReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	
	
	//3�� ������
	public void limitTimeMsg(String time) {
		this.setLayout(null);
		this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
		this.searchTimeLabel.setOpaque(false);
		this.add(this.searchTimeLabel);
		
		this.searchTimeLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.searchTimeLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		this.searchTimeLabel.setText(time);
	}
	
	public void limitTimeMsg() {
		String init = "";
		this.searchTimeLabel.setText(init);
	
	}
	
	//�̸�, �̸��� , ���� �޼���  ���̺� ��ġ �� ũ��
	public void setLabelPosition() {
		
	//���̵�� ��ġ�� ũ��
	this.searchIdLabel.setBounds(SearchPwdEnum.SEARCH_ID_LABEL.getRectangle());
	
	//�̸��� �� ��ġ �� ũ��
	this.searchemailLabel.setBounds(SearchPwdEnum.SEARCH_EMAIL_LABEL.getRectangle());
	
	//���� �޼��� ��ġ �� ũ��
	this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ANSWER_LABEL.getRectangle());
	
	//3�� Ÿ�� ���� ��ġ �� ũ��
		this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
		this.add(searchIdLabel);
		this.add(searchemailLabel);
		this.add(searchAnswerMsgLabel);
		this.add(searchTimeLabel);
		this.searchIdLabel.setOpaque(false);
	}
	
	//�̸� �̸���, ���� �ؽ�Ʈ�ʵ� ��ġ �� ũ��
	public void setTextFieldPosition() {
		
	//���̵� �ؽ�Ʈ �ʵ�
	this.searchIdTextField.setBounds(SearchPwdEnum.SEARCH_ID_TEXTFIELD.getRectangle());
	
	//�̸��� �Է�â 
	this.searchemailTextField.setBounds(SearchPwdEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
	
	//���� ��ȣ �Է�â
		this.searchConfirmTextField.setBounds(SearchPwdEnum.SEARCH_CONFIRM_TEXTFIELD.getRectangle());
		this.add(searchIdTextField);
		this.add(searchemailTextField);
		
	} // ������ ��
	
	//��ư ���� -- ������ư
	public void setButtonPosition() throws IOException {   	
	
	this.searchConfirmButton  = new JButton();
	
	this.searchConfirmButton.setBorderPainted(false);
	this.searchConfirmButton.setFocusPainted(false);
	this.searchConfirmButton.setContentAreaFilled(false);
	
	this.searchConfirmButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);    	
	this.searchConfirmButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/yesno/certify.kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
			);
	
	this.searchConfirmButton.setBounds(SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle()); 
	this.searchConfirmButton.setName(LoginPanelEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName());
	
	this.add(searchConfirmButton);
	this.getSearchConfirmButton().setVisible(true);
	
	this.searchConfirmButton.addActionListener(this.findPwdAction);
	
	
	//���� ��ư�� ���� �� �ٲ�� Ȯ�� ��ư
	this.checkNumberButton  = new JButton();
	
	this.checkNumberButton.setBorderPainted(false);
	this.checkNumberButton.setFocusPainted(false);
	this.checkNumberButton.setContentAreaFilled(false);
	
	this.checkNumberButton.setIconTextGap(this.checkNumberButton.getIconTextGap() - 15);    	
	this.checkNumberButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/forgotPW/confirm.kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CONFIRM_CHECK_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CONFIRM_CHECK_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
		);
	
	this.checkNumberButton.setBounds(SearchPwdEnum.SEARCH_CONFIRM_CHECK_BUTTON.getRectangle()); 
	this.checkNumberButton.setName(LoginPanelEnum.BUTTON_NAVE_CONFIRM_NUMBER.getButtonName());
	
	this.add(checkNumberButton);
	this.checkNumberButton.setVisible(false);
	
	this.checkNumberButton.addActionListener(this.findPwdAction);
	
	
	//��ҹ�ư
	this.searchCancelButton  = new JButton();
	
	this.searchCancelButton.setBorderPainted(false);
	this.searchCancelButton.setFocusPainted(false);
	this.searchCancelButton.setContentAreaFilled(false);
	
	this.searchCancelButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);    	
	this.searchCancelButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/myData/reset.Kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CANCEL_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CANCEL_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
	);
	
	this.searchCancelButton.setBounds(SearchPwdEnum.SEARCH_CANCEL_BUTTON.getRectangle()); 
	this.add(searchCancelButton); 
	
	this.searchCancelButton.setName(LoginPanelEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName());
	this.searchCancelButton.addActionListener(this.findPwdAction); 
	
	
	//Ȯ�� ��ư -- > ������ ��к��� ī�巹�̾ƿ����� ����.
	
	this.searchCheckButton  = new JButton();
	this.searchCheckButton.setIconTextGap(this.searchCheckButton.getIconTextGap() - 15);    	
	this.searchCheckButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/forgotPW/confirm.kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
			);
	
	this.searchCheckButton.setBorderPainted(false);
	this.searchCheckButton.setFocusPainted(false);
	this.searchCheckButton.setContentAreaFilled(false);
	
	this.searchCheckButton.setBounds(SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle()); 
	this.add(searchCheckButton); 
	this.searchCheckButton.setName(LoginPanelEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName());
	this.searchCheckButton.addActionListener(this.findPwdAction); 
	} 
	
	//====================�׼�ó��========================================================================================================
	
	//��ҹ�ư�� ������ �� ó���ϴ� �޼ҵ�
	public void doCancelButton() {
		this.searchPwdFrame.doCancelButton(); //���⿡�� searchPwdFrame�� �����ؼ� docancel����
	}
	
	public void getChangePanel(AbstractEnumsDTO userPosition) {
		UserPersonalInfoDTO data = (UserPersonalInfoDTO) userPosition;
		
		if(data.getUserCount() == 0) {
			this.getSearchIdTextField().setEditable(true);
			this.userNumberMsg("<html>��ϵ� ���̵� �����ϴ�.." 
					+"<br>���̵� Ȯ���ϼ���<br></html>");
			
		} else {
			this.searchPwdFrame.doCheckButton();
		}
	}
	
	//�������� ������
	public void getCerfication() {
		this.searchConfirmButton.setEnabled(false);
		BasicFrame basicFrame = this.searchPwdFrame.getBasicFrame();
	
		String email = this.searchemailTextField.getText();
		String id    = this.searchIdTextField.getText();
		
		UserPersonalInfoDTO userPersonalInfoDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		userPersonalInfoDTO.setUserAction(UserActionEnum.USER_SEARCH_CERTIFICATION_CHECK);
		userPersonalInfoDTO.setUserEmail(email);
		userPersonalInfoDTO.setUserID(id);
		
		basicFrame.sendDTO(userPersonalInfoDTO);
	}
	
	//�̸��� ������ȣ �߼� ���� �� �߻��ϴ� �޼ҵ�
	public void emailSuccess(AbstractEnumsDTO userPosition) {
		UserPersonalInfoDTO data = (UserPersonalInfoDTO) userPosition;
		System.out.println(data.isEmailSuccess());
		if(data.isEmailSuccess()) {
			this.getSearchemailTextField().setEditable(false);
			this.userNumberMsg("<html>������ȣ �߼۵Ǿ����ϴ�." 
					+"<br>������ȣ �Է� �ٶ��ϴ�.<br></html>");
			
			JButton btn;
			btn = this.searchConfirmButton;
			btn.setVisible(false);
			
			btn = this.checkNumberButton;
			btn.setVisible(true);
			
			this.searchConfirmTextField.setVisible(true);
			this.timeLimit();
		} else {
			this.userNumberMsg("�̸��� �߼� ����");
			this.searchemailTextField.setText(null);
			this.getSearchemailTextField().setEditable(true);
			this.searchConfirmTextField.setText(null);
		}
	
	}
	
	//3�н����� �޼ҵ�
	public void timeLimit() {
		this.searchTimeLabel.setVisible(true);
	
		new Thread(() -> {
			StringBuffer time = new StringBuffer();
			
			for(int i = 2; i >= 0; --i) {
				
				for(int j = (i >= 3) ? 0 : 59 ; j >= 0; j--) {
					time.delete(0, time.length());
					time.append(i);
					time.append(" : ");
					time.append(j < 10 ? "0" +  j : j);
			
					this.searchTimeLabel.setText(time.toString());
			
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
							
					}
				}
			}
	
			//�ð��� ���� �� ī��Ʈ ����
			this.searchTimeLabel.setText("");
			this.searchTimeLabel.setVisible(false);
			this.searchConfirmButton.setEnabled(true);
			
			//������ȣ �ʱ�ȭ �Ѵ�.
			this.searchConfirmTextField.setText("");
			this.searchConfirmTextField.setVisible(false);
			this.checkNumberButton.setVisible(false);
			this.searchConfirmButton.setVisible(true); 
			
			if(this.isEmailConfirmLimitTime) {
				return;
			}
			
			//�����尡 ������ �������� ���������� �����ϴ°�. 
			this.userNumberMsg("<html>���������� �߼��ϼ���.</html>");
			this.checkNumberButton.setVisible(false);
			this.searchConfirmButton.setVisible(true); 
		}).start();
	}
	
	//Ȯ�� ��ư ������ �߻��ϴ� �޼ҵ� -- > ������ȣ�� �Էµ� �� �߻� �Ǵ� �޼ҵ�
	public void confirmNumberCheck() {
		String certificationNumber = getSearchConfirmTextField().getText();
		
		//TODO
		System.out.println(certificationNumber + " : �̰� ����ڰ� �� ��ȣ");
		
		BasicFrame basicFrame = this.searchPwdFrame.getBasicFrame();
		ClientAccept clientAccpet = basicFrame.getClientAccept();
		ObjectOutputStream oos = clientAccpet.getClientOS(); 
		
		UserPersonalInfoDTO userPersonalInfoDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		userPersonalInfoDTO.setUserAction(UserActionEnum.USER_SEARCH_PASSWORD_CERTIFICATION_NUMBER);
		userPersonalInfoDTO.setCertificationNumber(certificationNumber);
	
		try {
			oos.writeObject(userPersonalInfoDTO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Ȯ���� ������ ���� ��ȣ�� ������ 
	public void confirmNumberSuccess(AbstractEnumsDTO userPosition) {
		UserPersonalInfoDTO data = (UserPersonalInfoDTO) userPosition;
		
		if(data.isCertificationNumber()) {
			this.userNumberMsg("<html>�����Ϸ�!!" 
					+"<br>��й�ȣ�ٽü������ּ���.<br></html>");
			this.checkNumberButton.setVisible(false);
			this.searchConfirmTextField.setEditable(false);
			
			//�ð��� ���� �� ī��Ʈ ����
			this.searchTimeLabel.setText("");
			this.searchTimeLabel.setVisible(false);
			
			//������ȣ �ʱ�ȭ �Ѵ�.
			this.searchConfirmTextField.setVisible(false);
			this.isEmailConfirmLimitTime = true;
			this.isConfirmNumberSuccess = true;
			return;
			
		} else {
			this.getSearchemailTextField().setEditable(false);
			
			JButton btn;
			btn = this.getSearchConfirmButton();
			btn.setVisible(false);
			
			btn = this.getCheckNumberButton();
			btn.setVisible(true);
			this.searchConfirmTextField.setVisible(true);
			this.userNumberMsg("<html>������ȣ��ġ���� �ʽ��ϴ�." 
								+"<br>�ٽü������ּ���.<br></html>");
			this.isConfirmNumberSuccess = false;
			return;
		}
	}
	
	public void userInfoSearchFail() {
		this.userNumberMsg("��ġ�ϴ� ������ �����ϴ�.");
		this.searchConfirmButton.setEnabled(true);
	}
	
	//������ �ٵ� �� ���̵� �̸��� �˻��ϴ� ��
	public void checkIdEmail() {
		BasicFrame basicFrame = this.searchPwdFrame.getBasicFrame();
		ClientAccept clientAccpet = basicFrame.getClientAccept();
		ObjectOutputStream oos = clientAccpet.getClientOS();
		
		String email = this.searchemailTextField.getText();
		String id	 = this.searchIdTextField.getText();
		
	
		this.searchIdTextField.setEditable(false);
		
		UserPersonalInfoDTO userPersonalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
	
		userPersonalDTO.setUserAction(UserActionEnum.USER_SEARCH_ID_EMAIL_CHECK);
		userPersonalDTO.setUserID(id);
		userPersonalDTO.setUserEmail(email);			
		
		try {
			oos.writeObject(userPersonalDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addKeyAction(JComponent comp, String Name) {
		comp.setName(Name);
		comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addKeyListener(this.findPwdAction);
		this.add(comp);	
	}
	
	public SearchPwdFrame getSearchPwdFrame() {
		return searchPwdFrame;
	}
	
	public JLabel getSearchIdLabel() {
		return searchIdLabel;
	}
	
	public JLabel getSearchemailLabel() {
		return searchemailLabel;
	}
	
	public JLabel getSearchAnswerMsg() {
		return searchAnswerMsgLabel;
	}
	
	public JLabel getSearchTimeLabel() {
		return searchTimeLabel;
	}
	
	public JTextField getSearchIdTextField() {
		return searchIdTextField;
	}
	
	public JTextField getSearchemailTextField() {
		return searchemailTextField;
	}
	
	public JTextField getSearchConfirmTextField() {
		return searchConfirmTextField;
	}
	
	public JButton getSearchConfirmButton() { //������ư
		return searchConfirmButton;
	}
	
	public JButton getSearchCancelButton() { //��ҹ�ư
		return searchCancelButton;
	}
	
	public JButton getCheckNumberButton() { //������ȣ�� �´��� ���� Ȯ�ι�ư
		return checkNumberButton;
	}
	
	public JButton getsearchCheckButton() { //������ Ȯ�� ��ư
	    	return searchCheckButton;
	    }
	    
	public void setEmailConfirmLimitTime(boolean isEmailConfirmLimitTime) {
		this.isEmailConfirmLimitTime = isEmailConfirmLimitTime;
	}
	
	public boolean isEmailConfirmLimitTime() {
		return isEmailConfirmLimitTime;
	}

	public boolean isConfirmNumberSuccess() {
		return isConfirmNumberSuccess;
	}

	public void setConfirmNumberSuccess(boolean isConfirmNumberSuccess) {
		this.isConfirmNumberSuccess = isConfirmNumberSuccess;
	}


	
}

