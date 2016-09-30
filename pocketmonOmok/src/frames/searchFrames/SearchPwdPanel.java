package frames.searchFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.findIDandPW.FindPWAction;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginSizesEnum;
import enums.frames.SearchIdEnum;
import enums.frames.SearchPwdEnum;
import utility.RegexCheck;
	
	public class SearchPwdPanel extends JPanel {
	
		private JLabel searchIdLabel;
		private JLabel searchemailLabel;
		
		private JLabel searchAnswerMsgLabel;
		private static JLabel searchTimeLabel;
		
		private JTextField searchIdTextField;
		private JTextField searchemailTextField;
		private JTextField searchConfirmTextField;
		
		private JButton searchConfirmButton; //Ȯ�� ��ư
		private JButton searchCancelButton; //��� ��ư -- > Ȩ���� ���� ��ư
		private JButton searchCheckButton; // ���� ��ư
		private JButton confirmCheckButton; // ���� ��ȣ�� ��ġ�ϴ��� ����Ȯ�� ��ư
	
	//private Image backGround;
	
		private SearchPwdFrame searchPwdFrame;
		private FindPWAction findPwdAction;
		
		
		private String emailAddr; // ��������
		private String idCheck;
		private boolean emailConfirmLimitTime;
		private String checkMsg;
		
	
	public SearchPwdPanel(SearchPwdFrame searchPwdFrame) throws IOException {
		this.searchPwdFrame = searchPwdFrame;
		this.findPwdAction = new FindPWAction(this);
		this.emailConfirmLimitTime = false;
	
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
	Font default_Font  = SearchIdEnum.LABELFONT_DEFAULT.getFont(); //�Ϲ�
	Font error_FONT    = SearchIdEnum.LABELFONT_ERROR.getFont(); //����
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
	
	//���̺�, �ؽ�Ʈ, ��ư �ҷ�����
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonPosition();
		this.userInfoErrorLabelReset(); 
		this.userNumberMsgReset();
		
		
		this.addKeyAction(this.searchIdTextField, "searchIdTextField");
		this.addKeyAction(this.searchemailTextField, "searchemailTextField");
		this.addKeyAction(this.searchConfirmTextField, "confirmNumberText");
		 
	}
	
	//���̵� �Ǵ� �̸��� ���� �˷��ִ� �ؽ�Ʈ
	//�ʱ�ȭ�� �Ǿ ���� �ִ� searchErrorMsg�� �޼����� �ҷ��´�.
	public void userInfoErrorLabelReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------
	//������ȣ�� �߼� �Ǿ��ٸ� ��Ÿ���� �޼���
	public void userNumberMsg(String searchAnswer){
		this.setLayout(null);
		this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchAnswerMsgLabel.setOpaque(false);
		this.add(this.searchAnswerMsgLabel);
		
		this.searchAnswerMsgLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.searchAnswerMsgLabel.setForeground(Color.blue);
		this.searchAnswerMsgLabel.setText(searchAnswer);
	}
	
	public void userNumberMsgReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	//-----------------------------------------------------------------------------
	//3�� ������
	public void limitTimeMsg(String time) {
		this.setLayout(null);
		this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
		this.searchTimeLabel.setOpaque(false);
		this.add(this.searchTimeLabel);
		
		this.searchTimeLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.searchTimeLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		this.searchTimeLabel.setText(time);
	}
	
	public void limitTimeMsg() {
		String init = "";
		this.searchTimeLabel.setText(init);
	
	}
	//�̸�, �̸��� , ���� �޼���  ���̺�!!!!!! ��ġ �� ũ��
	public void setLabelPosition() {
		//���̵�� ��ġ�� ũ��
	this.searchIdLabel.setBounds(SearchPwdEnum.SEARCH_ID_LABEL.getRectangle());
	//�̸��� �� ��ġ �� ũ��
	this.searchemailLabel.setBounds(SearchPwdEnum.SEARCH_EMAIL_LABEL.getRectangle());
	//���� �޼��� ��ġ �� ũ��
	//
	this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ANSWER_LABEL.getRectangle());
	//3�� Ÿ�� ���� ��ġ �� ũ��
		this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
		this.add(searchIdLabel);
		this.add(searchemailLabel);
		this.add(searchAnswerMsgLabel);
		this.add(searchTimeLabel);
		this.searchIdLabel.setOpaque(false);
	}
	
	//	// �̸� �̸���, ���� �ؽ�Ʈ�ʵ�!!!!!!!!!!!!!! ��ġ �� ũ��
	public void setTextFieldPosition() {
		//���̵� �ؽ�Ʈ �ʵ�
	this.searchIdTextField.setBounds(SearchPwdEnum.SEARCH_ID_TEXTFIELD.getRectangle());
	//�̸��� �Է�â 
	this.searchemailTextField.setBounds(SearchPwdEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
	//���� ��ȣ �Է�â
		this.searchConfirmTextField.setBounds(SearchPwdEnum.SEARCH_CONFIRM_TEXTFIELD.getRectangle());
		this.add(searchIdTextField);
		this.add(searchemailTextField);

	}
	
	//��ư ����
	public void setButtonPosition() throws IOException {   	

	this.searchConfirmButton  = new JButton(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName());
	
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
	this.add(searchConfirmButton);
	this.searchConfirmButton.addActionListener(this.findPwdAction);
	
	
	//��ҹ�ư
	this.searchCancelButton  = new JButton(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName());
	
	this.searchCancelButton.setBorderPainted(false);
	this.searchCancelButton.setFocusPainted(false);
	this.searchCancelButton.setContentAreaFilled(false);
	
	this.searchCancelButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);    	
	this.searchCancelButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/login/back.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
	);
	this.searchCancelButton.setBounds(SearchPwdEnum.SEARCH_CANCEL_BUTTON.getRectangle()); 
	this.add(searchCancelButton); 
	this.searchCancelButton.setName(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName());
	this.searchCancelButton.addActionListener(this.findPwdAction); 
	
	
	//-------------------------------------------------------------------------------------------------
	//Ȯ�� ��ư -- > ������ ��к��� ī�巹�̾ƿ����� ����.
	
	
	this.searchCheckButton  = new JButton(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName());
	this.searchCheckButton.setIconTextGap(this.searchCheckButton.getIconTextGap() - 15);    	
	this.searchCheckButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/forgotPW/confirm.kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
	);
	this.searchCheckButton.setBounds(SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle()); 
	this.add(searchCheckButton); 
	//this.searchCheckButton.setName(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName());
	this.searchCheckButton.addActionListener(this.findPwdAction); 
	} 
	
	//====================�׼�ó��========================================================================================================
	//��ҹ�ư�� ������ �� ó���ϴ� �޼ҵ�
	public void doCancelButton() {
		this.searchPwdFrame.doCancelButton(); //���⿡�� searchPwdFrame�� �����ؼ� docancel����
	}
	
	public void getChangePanel() {
		this.searchPwdFrame.doCheckButton();
	}
	
	//������ư�� �������� ó���ϴ� �޼ҵ�
	public void getCerfication() {
		this.emailAddr = this.getSearchemailTextField().getText();
		this.idCheck   = this.getSearchIdTextField().getText();
		this.checkMsg = null;

		if(!RegexCheck.emailRegexCheck(this.emailAddr) &&this.idCheck.isEmpty() && this.emailAddr.isEmpty()) {
		this.userNumberMsgReset();
		checkMsg = "<html>email���Ŀ� ���� �ʽ��ϴ�..<br>�ٽ� �����ּ���<br></html>";
		this.userNumberMsg(checkMsg);
		
		} 
		if(RegexCheck.emailRegexCheck(this.emailAddr)) {
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW_EMAIL);
		//�´ٸ� �̸��� ���� �����޶�...?
			personalDTO.setUserID(this.idCheck);
			personalDTO.setUserEmail(this.emailAddr);
			
			System.out.println("����µ������ȿ���... ���Ͷ� ����");
			this.searchPwdFrame.getCerficartion(personalDTO); //action�� ������ ���� �г��� �޾� searchPwdFrame���� ���� �޼����� �����ش�.
			
			//3�� ������ ..
			this.userNumberMsgReset();
			checkMsg = "<html>������ȣ �߻�.<br>������ȣ �����ּ���<br></html>";
			this.userNumberMsg(checkMsg);
	
	
	new Thread() {
		@Override
		public void run() {
			StringBuffer time = new StringBuffer();
			
		for(int i = 0; i >= 0; --i) {
			for(int j = (i >= 3) ? 0 : 59; j >= 0; j--) {
				time.delete(0, time.length());
				time.append(i);
				time.append(" : ");
		time.append(j < 10 ? "0" +  j : j);
		
		SearchPwdPanel.getSearchTimeLabel().setText(time.toString());
		try {
			Thread.sleep(1000);
			if(emailConfirmLimitTime) {
				this.interrupt();
				//userNumberMsg().setEditable(false);
					}
				} catch (InterruptedException e) {
					break;
				}
			}
			if(emailConfirmLimitTime) {
				return;
			}
		}
	SearchPwdPanel.getSearchTimeLabel().setVisible(false);
	String checkMsg = "<html>������ȣ�� ���� �ʽ��ϴ�..<br>�ٽ� Ȯ�ιٶ��ϴ�.<br></html>";
	SearchPwdPanel.this.userNumberMsg(checkMsg);
	//������ȣ �ʱ�ȭ �Ѵ�.
	SearchPwdPanel.this.searchConfirmButton = null;
		    	}
		   }.start();
		    	}
		    }
	
	
	public void addKeyAction(JComponent comp, String Name) {
//		EmptyBorder border = JoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(Name);
//		comp.setBorder(border);
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
	    public static JLabel getSearchTimeLabel() {
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
	    public JButton getSearchConfirmButton() {
	    	return searchConfirmButton;
	    }
	    public JButton getSearchCancelButton() {
	    	return searchCancelButton;
	    }
	    public JButton getConfirmCheckButton() {
	    	return confirmCheckButton;
	    }
	    public JButton getsearchCheckButton() {
	    	return searchCheckButton;
	    }
	
	
	    
	}

