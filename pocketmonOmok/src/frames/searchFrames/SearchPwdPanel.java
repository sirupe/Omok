package frames.searchFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import actions.findIDandPW.FindPWAction;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;
import enums.frames.LoginSizesEnum;
import enums.frames.SearchIdEnum;
import enums.frames.SearchPwdEnum;
import frames.LoginPanel;
import utility.RegexCheck;

public class SearchPwdPanel extends JPanel {

	private JLabel searchIdLabel;
	private JLabel searchemailLabel;
	
	private JLabel searchErrorMsgLabel;
	private JLabel searchAnswerMsgLabel;
	private JLabel searchTimeLabel;
	
	private JTextField searchIdTextField;
	private JTextField searchemailTextField;
	private JTextField searchConfirmTextField;
	
	private JButton searchConfirmButton;
	private JButton searchCancelButton;
	//private Image backGround;
	
	private SearchPwdFrame searchPwdFrame;
	private FindPWAction findPwdAction;
	
	
	private String emailAddr;
	
	
	
	public SearchPwdPanel(SearchPwdFrame searchPwdFrame) throws IOException {
		this.searchPwdFrame = searchPwdFrame;
		this.findPwdAction = new FindPWAction(this);
//		this.addWin

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


		//���� �޼��� ���̺�
		String searchErrorMsg = "<html>3���ʰ��� �Ǿ����ϴ�.<br>�ٽ� ������ �޾� �ּ���<br></html>";
		this.searchErrorMsgLabel = new JLabel(searchErrorMsg);
		
		String searchAnswer = "<html>������ �̸��Ϸ� "
				           + "<br>������ȣ�� �߼۵Ǿ����ϴ�.<br></html>";
		this.searchAnswerMsgLabel = new JLabel(searchAnswer);
		
		String time = "3:00";
		this.searchTimeLabel = new JLabel(time);
		this.searchTimeLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		
		//���̺�, �ؽ�Ʈ, ��ư �ҷ�����
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonPosition();
		this.userInfoErrorLabelReset(); 
		this.userNumberMsgReset();
	}
	
	//���̵� �Ǵ� �̸��� ���� �˷��ִ� �ؽ�Ʈ
	public void userInfoError(String searchErrorMsg){
		this.setLayout(null);
		this.searchErrorMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchErrorMsgLabel.setOpaque(false);
		this.add(this.searchErrorMsgLabel);
		
		this.searchErrorMsgLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.searchErrorMsgLabel.setForeground(Color.RED);
		this.searchErrorMsgLabel.setText(searchErrorMsg);
	}
	//�ʱ�ȭ�� �Ǿ ���� �ִ� searchErrorMsg�� �޼����� �ҷ��´�.
	public void userInfoErrorLabelReset() {
		String init = "";
		this.searchErrorMsgLabel.setText(init);
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
		System.out.println("ANJID..");
	}
	
	public void userNumberMsgReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	
	//�̸�, �̸��� , ���� �޼���  ���̺�!!!!!! ��ġ �� ũ��
		public void setLabelPosition() {
			//���̵�� ��ġ�� ũ��
			this.searchIdLabel.setBounds(SearchPwdEnum.SEARCH_ID_LABEL.getRectangle());
			//�̸��� �� ��ġ �� ũ��
			this.searchemailLabel.setBounds(SearchPwdEnum.SEARCH_EMAIL_LABEL.getRectangle());
			//���� �޼��� ��ġ �� ũ��
			this.searchErrorMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
			//
			this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ANSWER_LABEL.getRectangle());
			//3�� Ÿ�� ���� ��ġ �� ũ��
			this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
			this.add(searchIdLabel);
			this.add(searchemailLabel);
			this.add(searchErrorMsgLabel);
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
	    	this.add(searchConfirmTextField);
	    }
	
	    public void setButtonPosition() throws IOException {   	
			//��ư ����
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
	    	this.searchCancelButton.addActionListener(this.findPwdAction); 
	    	}  
	      
		    //��ҹ�ư�� ������ �� ó���ϴ� �޼ҵ�
		    public void doCancelButton() {
		    	this.searchPwdFrame.doCancelButton(); //���⿡�� searchPwdFrame�� �����ؼ� docancel����
		    }
	    
		    //������ư�� �������� ó���ϴ� �޼ҵ�
		    public void getCerfication() {
		    	this.emailAddr = this.getSearchemailTextField().getText();
		    	
		    	String checkMsg = null;
	    	
		    	if(this.getSearchIdTextField().getText().isEmpty() || this.getSearchemailTextField().getText().isEmpty()) {
		    		this.userInfoErrorLabelReset();
		    		this.userInfoError("���̵�� �̸��� �Է����ּ���");
				
		    	} else if (!RegexCheck.emailRegexCheck(this.emailAddr)) {
		    		this.userInfoErrorLabelReset();
		    		checkMsg = "<html>email���Ŀ� ���� �ʽ��ϴ�..<br>�ٽ� �����ּ���<br></html>";
		    		this.userInfoError(checkMsg);	
	    		
		    	} else {
		    		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW_EMAIL);
		    		//�´ٸ� �̸��� ���� �����޶�...?
		    		personalDTO.setUserID(this.getSearchIdTextField().getText());
		    		personalDTO.setUserEmail(this.getSearchemailTextField().getText());
		    		checkMsg = "<html>������ȣ �߼۵Ǿ����ϴ�.<br>������ȣ �����ּ���<br></html>";
		    		this.userNumberMsg(checkMsg);
		    		this.userInfoErrorLabelReset();
				
		    		this.searchPwdFrame.getCerficartion(personalDTO); //action�� ������ ���� �г��� �޾� searchPwdFrame���� ���� �޼����� �����ش�.
		    	} 
		    }
	    
	    public SearchPwdFrame getSearchPwdMain() {
	    	return searchPwdFrame;
	    }
	    public JLabel getSearchIdLabel() {
	    	return searchIdLabel;
	    }
	    public JLabel getSearchemailLabel() {
	    	return searchemailLabel;
	    }
	    public JLabel getSearchErrorMsgLabel() {
	    	return searchErrorMsgLabel;
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
	    public JButton getSearchConfirmButton() {
	    	return searchConfirmButton;
	    }
	    public JButton getSearchCancelButton() {
	    	return searchCancelButton;
	    }

	    
	}

