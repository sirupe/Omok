package frames.modifyMyInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import actions.modifyMyInfo.ModifyMyInfoAction;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.ModifyEnum;
import frames.BasicFrame;
import utility.GetResources;
import utility.JTextFieldNumOnly;
import utility.RegexCheck;

public class ModifyMyInfoFrame extends JFrame {

	private JLabel userIdLabel;
	
	private JLabel pwdLabel;
	private JLabel rePwdLabel;
	private JLabel pwdLabelError; //�н����� ��ġ ����
	
	private JLabel nameLabel;
	
	private JLabel genderLabel;
	
	private JRadioButton genderManRadio;
	private JRadioButton genderWomanRadio;
	private ButtonGroup genderButtonGroup;

	private JLabel birthLabel;
	private JComboBox<Integer> yearChoice;
	private JComboBox<Integer> monthChoice;
	private JComboBox<Integer> dateChoice;
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dateLabel;
	
	private JLabel emailLabel;
	private JLabel emailErrLabel;
	
	private JLabel telLabel;
	private JLabel telHyphen1Label;
	private JLabel telHyphen2Label;
	private JLabel atLabel;
	
	private JTextField idTextField;
	private JPasswordField pwdField;
	private JPasswordField rePwdField;
	private JTextField nameTextField;

	private JTextField emailIDTextField;
	private JTextField emailAddrTextField;
	private JTextField emailConfTextField;
	private JComboBox<String> emailAddrChoice;

	private JComboBox<String> telFrontNumChoice; //��ȭ��ȣ 02,010..
	private JTextField telMiddleTextField;
	private JTextField telLastNumTextField;	
	
	private JButton modifyButton; //ȸ������ ��ư
	private JButton cancelButton; //��ҹ�ư
	private JButton dropoutButton; //���� ��ū
	
	private Image background;
	private ModifyMyInfoAction modifyAction;
	private BasicFrame basicFrame;
	private CorrectPwdFrame correctPwdFrame;
	public ModifyMyInfoFrame(BasicFrame basicFrame) throws IOException {
		this.modifyAction = new ModifyMyInfoAction(this);
		this.basicFrame = basicFrame;
		//��� ���̺� 
		this.userIdLabel 	 = new JLabel("���̵�"); 
		this.pwdLabel         = new JLabel("��й�ȣ");
		this.rePwdLabel      = new JLabel("��й�ȣ���Է�");
		
		this.pwdLabelError   = new JLabel("ERROR MESSAGE"); //���� ������
		this.pwdLabelError.setBackground(Color.pink);
		
		
		this.nameLabel   = new JLabel("�̸�");
		this.genderLabel = new JLabel("����");
		this.birthLabel  = new JLabel("�������");
		this.emailLabel  = new JLabel("�̸���");
		this.telLabel    = new JLabel("��ȭ��ȣ");
		
		this.telHyphen1Label  = new JLabel("-");
		this.telHyphen2Label  = new JLabel("-");		
		this.atLabel      	  = new JLabel("@");
		
		this.yearLabel    	  = new JLabel("��");
		this.monthLabel   	  = new JLabel("��");
		this.dateLabel    	  = new JLabel("��");
		this.emailErrLabel	  = new JLabel("asdflaksdjgal;kdsjlasd");
		
		// ��� �ؽ�Ʈ �ʵ�
		this.idTextField    = new JTextField(10);
		
		this.pwdField       = new JPasswordField(10);
		this.rePwdField     = new JPasswordField(10);
		this.nameTextField  = new JTextField(10);
		
		this.emailIDTextField    	= new JTextField(10); 
		this.emailAddrTextField 	= new JTextField(10);
		this.telMiddleTextField		= new JTextField(new JTextFieldNumOnly(4), "", 0);
		this.telLastNumTextField    = new JTextField(new JTextFieldNumOnly(4), "", 0);
		
		this.pwdField.addKeyListener(this.modifyAction);
		this.rePwdField.addKeyListener(this.modifyAction);
		this.nameTextField.addKeyListener(this.modifyAction);
		this.emailIDTextField.addKeyListener(this.modifyAction);
		this.emailAddrTextField.addKeyListener(this.modifyAction);
		
		// ���� ���ڳ��� ���� �ڽ�
		this.genderButtonGroup = new ButtonGroup();
		this.genderWomanRadio  = new JRadioButton("����");
		this.genderManRadio    = new JRadioButton("����");
		this.genderWomanRadio.setOpaque(false);
		this.genderManRadio.setOpaque(false);
		
		
		this.genderButtonGroup = new ButtonGroup();
		this.genderButtonGroup.add(this.genderManRadio);
		this.genderButtonGroup.add(this.genderWomanRadio);
		
		//�̸��� ��ȭ��ȣ �޺��ڽ�
		this.emailAddrChoice = new JComboBox<String>();
		this.telFrontNumChoice = new JComboBox<String>();
		
		//����, ���, Ż���ư
		this.modifyButton   = new JButton();
		this.cancelButton	= new JButton();
		this.dropoutButton  = new JButton();	
		
		this.yearChoice  = new JComboBox<Integer>();
		this.monthChoice = new JComboBox<Integer>();
		this.dateChoice  = new JComboBox<Integer>();
		
		//���̺� ��Ʈ
		Font labelFont = ModifyEnum.LABELFONT_DEFAULT.getFont();
		this.userIdLabel.setFont(labelFont);
		this.pwdLabel.setFont(labelFont);
		this.rePwdLabel.setFont(labelFont);
		this.nameLabel.setFont(labelFont);
		this.birthLabel.setFont(labelFont);
		this.genderLabel.setFont(labelFont);
		this.emailLabel.setFont(labelFont);
		this.telLabel.setFont(labelFont);
		this.emailErrLabel.setFont(labelFont);
		
		//�ؽ�Ʈ�ʵ�
		Font textFont = ModifyEnum.LABELFONT_DEFAULT.getFont();
		this.idTextField.setFont(textFont);
		this.pwdField.setFont(textFont);
		this.rePwdField.setFont(textFont);
		this.nameTextField.setFont(textFont);
		
		this.emailIDTextField.setFont(textFont);
		this.emailAddrTextField.setFont(textFont);
		
		//���ȭ��
		background = ImageIO.read(
			  new File("resources/signUp/joinn.jpg")).getScaledInstance(
		          JoinSizesEnum.JOINFRAME_SIZE_WIDTH.getSize(),
		          JoinSizesEnum.JOINFRMAE_SIZE_HEIGHT.getSize(),
		          Image.SCALE_SMOOTH);
		
		this.setContentPane(new JLabel(new ImageIcon(background))); 
		
		this.setBounds(				
			ModifyEnum.MODIFY_JOINFRMAE_POSITION_X.getSize(),
			ModifyEnum.MODIFY_JOINFRMAE_POSITION_Y.getSize(),
			ModifyEnum.MODIFY_JOINFRAME_SIZE_WIDTH.getSize(),
			ModifyEnum.MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize()		
		);
		
		this.calSetDate(2016, 12);			//Ķ����
		for(int i = 2016; i >= 1900; i-- ) {
			yearChoice.addItem(i);
		}
		
		for(int j = 1; j <= 12; j++) {
			monthChoice.addItem(j);
		}
		  
		//�θ� �޼ҵ� �߰� 
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonGroup();
		this.setButtonPosition();
		this.setChoicePosition();
			
			
		this.setLayout(null);
		this.setVisible(true);
		this.setTitle("ȸ�����Լ���");
		this.setResizable(false);
		  
		
	}
	//�� ��ġ -- > �������
	public void setLabelPosition() {
		this.userIdLabel.setBounds(ModifyEnum.MODIFY_ID_LABEL.getRectangle());
		this.pwdLabel.setBounds(ModifyEnum.MODIFY_PWD_LABEL.getRectangle());	
		this.rePwdLabel.setBounds(ModifyEnum.MODIFY_REPWD_LABEL.getRectangle());	
		this.pwdLabelError.setBounds(ModifyEnum.MODIFY_REPWDERROR_LABEL.getRectangle());
		
		this.nameLabel.setBounds(ModifyEnum.MODIFY_NAME_LABEL.getRectangle());
		this.genderLabel.setBounds(ModifyEnum.MODIFY_GENDER_LABEL.getRectangle());
		this.birthLabel.setBounds(ModifyEnum.MODIFY_BIRTH_LABEL.getRectangle());
		this.yearLabel.setBounds(ModifyEnum.MODIFY_YEAR_LABEL.getRectangle());
		this.monthLabel.setBounds(ModifyEnum.MODIFY_MONTH_LABEL.getRectangle());
		this.dateLabel.setBounds(ModifyEnum.MODIFY_DATE_LABEL.getRectangle());
	
		this.emailLabel.setBounds(ModifyEnum.MODIFY_EMAIL_LABEL.getRectangle());
		this.emailErrLabel.setBounds(ModifyEnum.MODIFY_EMAIL_ERR_LABEL.getRectangle());
		
		this.telLabel.setBounds(ModifyEnum.MODIFY_TELNAME_LABELMID.getRectangle());
		this.telHyphen1Label.setBounds(ModifyEnum.MODIFY_TELHYPHEN1_LABEL.getRectangle());
		this.telHyphen2Label.setBounds(ModifyEnum.MODIFY_TELHYPHEN2_LABEL.getRectangle());
		this.atLabel.setBounds(ModifyEnum.MODIFY_AT_LABEL.getRectangle());
		
		this.add(emailErrLabel);
		this.add(userIdLabel);
		this.add(pwdLabel);
		this.add(rePwdLabel);
		this.add(pwdLabelError);
		this.add(nameLabel);
		this.add(genderLabel);
		this.add(birthLabel);
		this.add(yearLabel);
		this.add(monthLabel);
		this.add(dateLabel);
		
		this.add(emailLabel);
		
		this.add(telLabel);
		this.add(telHyphen1Label);
		this.add(telHyphen2Label);
		this.add(atLabel);
	}
	
	//�ؽ�Ʈ�ʵ�
	public void setTextFieldPosition() {
		this.idTextField.setBounds(ModifyEnum.MODIFY_ID_TEXT.getRectangle());
		this.pwdField.setBounds(ModifyEnum.MODIFY_PWD_TEXT.getRectangle());
		this.rePwdField.setBounds(ModifyEnum.MODIFY_REPWD_TEXT.getRectangle());
		this.nameTextField.setBounds(ModifyEnum.MODIFY_NAME_TEXT.getRectangle());
		
		this.telMiddleTextField.setBounds(ModifyEnum.MODIFY_TELMID_TEXT.getRectangle());
		this.telLastNumTextField.setBounds(ModifyEnum.	MODIFY_TELEND_TEXT.getRectangle());
		this.emailIDTextField.setBounds(ModifyEnum.MODIFY_EAMILID_TEXT.getRectangle());
		this.emailAddrTextField.setBounds(ModifyEnum.MODIFY_EAMILADDR_TEXT.getRectangle());
		
		this.add(idTextField);
		this.add(pwdField);
		this.add(rePwdField);
		this.add(nameTextField);
		this.add(telMiddleTextField);
		this.add(emailIDTextField);
		this.add(emailAddrTextField);
		this.add(telLastNumTextField);
		this.idTextField.setEditable(false);
		this.idTextField.setBackground(Color.pink);
		
		this.nameTextField.setEditable(false);
		this.nameTextField.setBackground(Color.pink);
		this.emailErrLabel.setVisible(false);
		this.pwdLabelError.setVisible(false);
	}
	public void setButtonGroup() {
		this.genderManRadio.setBounds(ModifyEnum.MODIFY_GENDERMAN_RADIOBUTTON.getRectangle());
		this.genderWomanRadio.setBounds(ModifyEnum.MODIFY_GENDERWOMAN_RADIOBUTTON.getRectangle());
		
		this.add(genderWomanRadio);
		this.add(genderManRadio);
	}
//=========================================================================================================
	
	public void calSetDate(int selectYear, int selectMonth) {

		Calendar cal = Calendar.getInstance();//����
		cal.set(selectYear, selectMonth,1);
		cal.add(Calendar.DATE,-1);	
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int k = 1; k <= lastDate; k++) {	
			dateChoice.addItem(k);
		}
	}
	public void setChoicePosition() {
		//�̸��ϰ� ��ȭ��ȣ�� �̳��� �޾� �迭�� �����ϰ� for������ �ݺ��Ͽ� �Ѱ� �޾ҽ��ϴ�..?
		String[] emailAddr = ModifyEnum.MODIFY_EMAIL_COMBO_ADDRESS.getStrArr();
		for(int i = 0, size = emailAddr.length; i < size; i++) {
			this.emailAddrChoice.addItem(emailAddr[i]);
		}
		String[] telFrontNum = ModifyEnum.MODIFY_TEL_FRONT_NUM_COMBO.getStrArr();
		for(int i = 0, size = telFrontNum.length; i < size; i++) {
			this.telFrontNumChoice.addItem(telFrontNum[i]);
		}
		
		//�̸����ּ�
		this.emailAddrChoice.setBounds(ModifyEnum.MODIFY_EMAILADDR_COMBO.getRectangle());
		this.setComboBoxStr(this.emailAddrChoice);
		this.add(emailAddrChoice);
		
		//��ȭ��ȣ ���ڸ�
		this.telFrontNumChoice.setBounds(ModifyEnum.MODIFY_TELFRONTNUM_COMBO.getRectangle());
		this.setComboBoxStr(this.telFrontNumChoice);
		this.add(telFrontNumChoice);

		//��
		this.yearChoice.setBounds(ModifyEnum.MODIFY_YEAR_COMBOBOX.getRectangle());
		this.setComboBoxInt(this.yearChoice);
		this.add(yearChoice);
		//��
		this.monthChoice.setBounds(ModifyEnum.MODIFY_MONTH_COMBOBOX.getRectangle());
		this.setComboBoxInt(this.monthChoice);
		this.add(monthChoice);
		//��
		this.dateChoice.setBounds(ModifyEnum.MODIFY_DATE_COMBOBOX.getRectangle());
		this.setComboBoxInt(this.dateChoice);
		this.add(dateChoice);
		this.addWindowListener(this.modifyAction);
	 }
	public void setComboBoxInt(JComboBox<Integer> comboBox) {
		comboBox.setBackground(ModifyEnum.MODIFY_EMAIL_COMBOBOX_BACKGROUND.getColor());
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(ModifyEnum.CHOICEBACKGROUND.getColor());
		comboBox.setFont(ModifyEnum.LABELFONT_DEFAULT.getFont());
	 }
	public void setComboBoxStr(JComboBox<String> comboBox) {
		comboBox.setBackground(ModifyEnum.MODIFY_EMAIL_COMBOBOX_BACKGROUND.getColor());
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(ModifyEnum.CHOICEBACKGROUND.getColor());
		comboBox.setFont(ModifyEnum.LABELFONT_DEFAULT.getFont());
	}
	//=========================================================================================================
	public void setButtonPosition() throws IOException {
		this.modifyButton.setIconTextGap(this.modifyButton.getIconTextGap() - 15);
		
		// ���� ��ư �ػ� �°� �׸���
		this.modifyButton.setIcon(GetResources.getImageIcon("resources/myData/correct.kor.png", 
				ModifyEnum.MODIFY_MODIFY_BUTTON.getRectangle().width,
				ModifyEnum.MODIFY_MODIFY_BUTTON.getRectangle().height));
		
	    //��� �ػ� �±� �׸���
		this.cancelButton.setIcon(GetResources.getImageIcon("resources/myData/reset.Kor.png", 
    				ModifyEnum.MODIFY_CANCEL_BUTTON.getRectangle().width,
    				ModifyEnum.MODIFY_CANCEL_BUTTON.getRectangle().height));
	    
		//Ż�� �ػ� �±� �׸���
		this.dropoutButton.setIcon(GetResources.getImageIcon("resources/myData/quit.Kor.png", 
    				ModifyEnum.MODIFY_DROPOUT_BUTTON.getRectangle().width, 
    				ModifyEnum.MODIFY_DROPOUT_BUTTON.getRectangle().height));
		
		this.modifyButton.setBounds(ModifyEnum.MODIFY_MODIFY_BUTTON.getRectangle());
		this.cancelButton.setBounds(ModifyEnum.MODIFY_CANCEL_BUTTON.getRectangle());
		this.dropoutButton.setBounds(ModifyEnum.MODIFY_DROPOUT_BUTTON.getRectangle());
		
		this.add(cancelButton);
		this.add(modifyButton);
		this.add(dropoutButton);
		
		this.cancelButton.setName("cancelButton");
		this.modifyButton.setName("modifyButton");
		this.dropoutButton.setName("dropoutButton");
		
		this.cancelButton.addActionListener(this.modifyAction);
		this.modifyButton.addActionListener(this.modifyAction);
		this.dropoutButton.addActionListener(this.modifyAction);
		
	}
	
	// ȸ������ ���� ��ư�� ������ ����Ǹ� Ŭ���̾�Ʈ�� ������ �����Ѵ�.
	public void setUserInfo(UserPersonalInfoDTO userPersonalInfo) {
		this.idTextField.setText(userPersonalInfo.getUserID());
		this.nameTextField.setText(userPersonalInfo.getUserName());
		
		if(userPersonalInfo.getUserGender() == 1) {
			this.genderManRadio.setSelected(true);
		} else {
			this.genderWomanRadio.setSelected(true);
		}
		
		String[] birth = userPersonalInfo.getUserBirth().split("\\.");
		this.yearChoice.setSelectedItem(Integer.parseInt(birth[0]));
		this.monthChoice.setSelectedItem(Integer.parseInt(birth[1]));
		this.dateChoice.setSelectedItem(Integer.parseInt(birth[2]));
		
		String[] email = userPersonalInfo.getUserEmail().split("@");
		
		this.emailIDTextField.setText(email[0]);
		this.emailAddrTextField.setText(email[1]);
		
		System.out.println(userPersonalInfo.getUserPhoneNumber());
		if(userPersonalInfo.getUserPhoneNumber() != null) {
			String[] phoneNum = userPersonalInfo.getUserPhoneNumber().split("-");
			this.telFrontNumChoice.setSelectedItem(phoneNum[0]);
			this.telMiddleTextField.setText(phoneNum[1]);
			this.telLastNumTextField.setText(phoneNum[2]);
		}
	}

	public void confirmButtonCheck() {
		char[] pwd = this.pwdField.getPassword();
		char[] rePwd = this.rePwdField.getPassword();
		String pwdStr = new String(pwd, 0, pwd.length);
		String rePwdStr = new String(rePwd, 0, rePwd.length);
		String telFrontNum = (String)this.telFrontNumChoice.getSelectedItem();
		String telMidNum = this.telMiddleTextField.getText();
		String telLastNum = this.telLastNumTextField.getText();
		
		int errCheck = 0;
		String errMsg = null;
		if(!(telFrontNum.equals("����") && telMidNum.length() == 0 && telLastNum.length() == 0)) {
			System.out.println("�����н���� �Ф�");
			if((telMidNum.length() == 0 && telLastNum.length() == 0) || 
				(telMidNum.length() == 0 && telLastNum.length() == 0) || 
				telFrontNum.equals("����")) {
				errMsg = "��ȭ��ȣ ������ ���� �ʽ��ϴ�.";
				errCheck++;
			}
		} 
		
		if(this.emailIDTextField.getText().length() == 0 || !RegexCheck.emailDomainRegexCheck(this.emailAddrTextField.getText())) {
			this.pwdLabelError.setForeground(ModifyEnum.ERROR_MESSAGE_COLOR.getColor());
			errMsg = "�̸����� ��Ȯ���� �ʽ��ϴ�.";
			errCheck++;
		}

		if(!pwdStr.equals(rePwdStr)) {
			this.pwdLabelError.setForeground(ModifyEnum.ERROR_MESSAGE_COLOR.getColor());
			errMsg = "�� �н����带 ��Ȯ�� �Է����ּ���.";
			errCheck++;
		}

		if(pwdStr.length() == 0 || 
		   rePwdStr.length() == 0 || 
		   this.nameTextField.getText().length() == 0 || 
		   this.emailAddrTextField.getText().length() == 0 || 
		   this.emailAddrTextField.getText().length() == 0) {
			errMsg = "��� ������ �Է����ּ���";
			errCheck++;
		}
		
		if(errCheck == 0) {
			
			this.sendServer(telFrontNum.equals("����") ? null : telFrontNum + "-" + telMidNum + "-" + telLastNum);
		} else {
			JOptionPane.showMessageDialog(this, errMsg);			
		}
	}
	
	// ������ ���� ���������͸� ������.
	public void sendServer(String phoneNum) {
		StringBuffer birthString = new StringBuffer();
		birthString.append(this.yearChoice.getSelectedItem());
		birthString.append(".");
		birthString.append(this.monthChoice.getSelectedItem());
		birthString.append(".");
		birthString.append(this.dateChoice.getSelectedItem());
		
		StringBuffer emailString = new StringBuffer();
		emailString.append(this.emailIDTextField.getText());
		emailString.append("@");
		emailString.append(this.emailAddrTextField.getText());
		
		char[] pwd = this.pwdField.getPassword();
		String pwdStr = new String(pwd, 0, pwd.length);
		
		UserPersonalInfoDTO userPersonalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_MODIFY_MY_INFO);
		userPersonalDTO.setUserAction(UserActionEnum.USER_MODIFY_UPDATE);
		userPersonalDTO.setUserPasswd(pwdStr);
		userPersonalDTO.setUserID(this.idTextField.getText());
		userPersonalDTO.setUserName(this.nameTextField.getText());
		userPersonalDTO.setUserGender(this.genderManRadio.isSelected() ? 1 : 2);
		userPersonalDTO.setUserBirth(birthString.toString());
		userPersonalDTO.setUserEmail(emailString.toString());
		userPersonalDTO.setUserPhoneNumber(phoneNum);
		
		this.basicFrame.sendDTO(userPersonalDTO);
	}
	
	public void clickCancelButton() {
		this.basicFrame.setVisible(true);
		this.dispose();
	}
	
	public void clickDropButton() {
		this.setVisible(false);
		this.correctPwdFrame = new CorrectPwdFrame(this, this.modifyAction);
	}
	
	public void updateFail() {
		JOptionPane.showMessageDialog(this, "ó�� �� ������ �߻��Ͽ����ϴ�. �ٽ� �õ����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
	}
	
	public void passwdFail() {
		JOptionPane.showMessageDialog(this, "�н����尡 ��ġ���� �ʽ��ϴ�.", "�н����� ����", JOptionPane.ERROR_MESSAGE);
	}
	
	public void updateSuccess() {
		JOptionPane.showMessageDialog(this, "ó���� �Ϸ�Ǿ����ϴ�.");
		this.dispose();
		this.basicFrame.setVisible(true);
	}
	
	public void close() {
		this.basicFrame.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}
	
	public CorrectPwdFrame getCorrectPwdFrame() {
		return correctPwdFrame;
	}
	
	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
}
