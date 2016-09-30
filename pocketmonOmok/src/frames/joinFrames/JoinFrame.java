package frames.joinFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import actions.join.JoinClientAction;
import enums.frames.JoinSizesEnum;
import frames.BasicFrame;
import utility.JTextFieldNumOnly;

// ����
@SuppressWarnings("serial")
public class JoinFrame extends JFrame {
	
	private JLabel idLabel;
	private JLabel idErrorLabel;
	
	private JLabel pwLabel;
	private JLabel rePwLabel;
	private JLabel pwdErrorLabel;
	private JLabel rePwdErrorLabel;
	
	private JLabel nameLabel;
	private JLabel nameErrorLabel;
	
	private JLabel genderLabel;
	
	private JRadioButton genderManRadio;
	private JRadioButton genderWomanRadio;
	private ButtonGroup genderButtonGroup;
	private JLabel genderErrorLabel;

	private JLabel birthLabel;
	private JComboBox<Integer> yearChoice;
	private JComboBox<Integer> monthChoice;
	private JComboBox<Integer> dateChoice;
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dateLabel;
	
	private JLabel emailLabel;
	private JLabel emailTimeLabel;
	private JLabel emailErrorLabel;
	
	private JLabel telLabel;
	private JLabel telHyphen1Label;
	private JLabel telHyphen2Label;
	private JLabel atLabel;
	private JLabel telErrorLabel;
	
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
	
	private JButton joinButton; //ȸ������ ��ư
	private JButton cancelButton; //��ҹ�ư
	private JButton confirmButton; //���� ��ū

//���
	private Image backGround;
	
	private BasicFrame basicFrame;
	private JoinClientAction joinAction;
	private Map<String, String> errMessageMap;

	public JoinFrame(BasicFrame basicFrame) throws IOException {
		this.basicFrame = basicFrame;
		this.joinAction = new JoinClientAction(this.basicFrame, this);
		this.addWindowListener(this.joinAction);

		this.errMessageMap = JoinSizesEnum.JOIN_MESSAGE.getMessageMap();

		//��� ���̺� 
		this.idLabel 	 = new JLabel("���̵�"); 
		this.pwLabel     = new JLabel("��й�ȣ");
		this.rePwLabel   = new JLabel("��й�ȣ���Է�");
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
		this.emailTimeLabel	  = new JLabel("3:00");
		
		// ��� �ؽ�Ʈ �ʵ�
		this.idTextField    = new JTextField(10);

		this.pwdField       = new JPasswordField(10);
		this.rePwdField     = new JPasswordField(10);
		this.nameTextField  = new JTextField(10);

		this.emailIDTextField    	= new JTextField(10); 
		this.emailAddrTextField 	= new JTextField(10);
		this.telMiddleTextField		= new JTextField(new JTextFieldNumOnly(4), "", 0);
		this.telLastNumTextField    = new JTextField(new JTextFieldNumOnly(4), "", 0);
		this.emailConfTextField  = new JTextField(new JTextFieldNumOnly(6), "", 0);

		// ���� ���ڳ��� ���� �ڽ�
		this.genderButtonGroup = new ButtonGroup();
		this.genderWomanRadio  = new JRadioButton("����");
		this.genderManRadio    = new JRadioButton("����");

		this.genderButtonGroup = new ButtonGroup();
		this.genderButtonGroup.add(this.genderManRadio);
		this.genderButtonGroup.add(this.genderWomanRadio);
		
				
		//ȸ������, ��� ��ư	
		this.cancelButton   = new JButton();
		this.joinButton	   = new JButton();
		this.confirmButton = new JButton();	

		this.yearChoice  = new JComboBox<Integer>();
		this.monthChoice = new JComboBox<Integer>();
		this.dateChoice  = new JComboBox<Integer>();

//���̺� ��Ʈ
		Font labelFont = JoinSizesEnum.LABELFONT_DEFAULT.getFont();
		this.idLabel.setFont(labelFont);
		this.pwLabel.setFont(labelFont);
		this.rePwLabel.setFont(labelFont);
		this.nameLabel.setFont(labelFont);
		this.birthLabel.setFont(labelFont);
		this.genderLabel.setFont(labelFont);
		this.emailLabel.setFont(labelFont);
		this.telLabel.setFont(labelFont);
		this.emailTimeLabel.setFont(labelFont);

//�ؽ�Ʈ�ʵ�
		Font textFont = JoinSizesEnum.LABELFONT_DEFAULT.getFont();
		this.idTextField.setFont(textFont);
		this.pwdField.setFont(textFont);
		this.rePwdField.setFont(textFont);
		this.nameTextField.setFont(textFont);

		this.emailIDTextField.setFont(textFont);
		this.emailAddrTextField.setFont(textFont);
		
// �̸��� ��ȭ��ȣ �޺� �ڽ�
		this.emailAddrChoice = new JComboBox<String>();
		this.telFrontNumChoice = new JComboBox<String>();
//��ü ������ ũ�� ���
		
		//����̹��� ������� �ػ󵵿� ���� �����ǰ� ����
	    backGround = ImageIO.read(
	   		  new File("resources/signUp/joinn.jpg")).getScaledInstance(
	                   JoinSizesEnum.JOINFRAME_SIZE_WIDTH.getSize(),
	                   JoinSizesEnum.JOINFRMAE_SIZE_HEIGHT.getSize(),
	                   Image.SCALE_SMOOTH);

	    this.setContentPane(new JLabel(new ImageIcon(backGround))); 
	    
		
	    this.setBounds(
			JoinSizesEnum.JOINFRMAE_POSITION_X.getSize(),
			JoinSizesEnum.JOINFRMAE_POSITION_Y.getSize(),
			JoinSizesEnum.JOINFRAME_SIZE_WIDTH.getSize(),
			JoinSizesEnum.JOINFRMAE_SIZE_HEIGHT.getSize()
		);

		this.setLabelPosition();	//���̺� 
		
		this.setTextFieldPosition();//����Ʈ �ʵ�
	
		this.setChoicePosition();	//�̸��� ���� ��ȭ��ȣ �޺� �ڽ�
		
		this.setButtonGroup();		//���� �׷� ����
		
		this.setButtonPosItion();	
		
		this.setErrorPosition();	//���� �޼���	

		this.calSetDate(2016, 12);			//Ķ����
		for(int i = 2016; i >= 1900; i-- ) {
			yearChoice.addItem(i);
		}
		for(int j = 1; j <= 12; j++) {
			monthChoice.addItem(j);
		}

		this.setLayout(null);
	    this.setTitle("ȸ������");
	    this.setVisible(true);
	    this.setResizable(false);
	    
		this.addKeyAction(this.idTextField, 		"idTextField");
		this.addKeyAction(this.pwdField, 			"pwdField");
		this.addKeyAction(this.rePwdField, 			"rePwdField");
		this.addKeyAction(this.nameTextField, 		"nameTextField");
		this.addKeyAction(this.emailIDTextField, 	"emailIDTextField");
		this.addKeyAction(this.emailAddrTextField, 	"emailAddrTextField");
		this.addKeyAction(this.telMiddleTextField, 	"telMiddleTextField");
		this.addKeyAction(this.telLastNumTextField, "telLastNumTextField");
		this.addKeyAction(this.emailConfTextField,	"emailConfTextField");
		this.addItemAction(this.yearChoice, 		"yearChoice");
		this.addItemAction(this.monthChoice, 		"monthChoice");
		this.addItemAction(this.dateChoice, 		"dateChoice");
		this.addItemAction(this.emailAddrChoice, 	"emailAddrChoice");
		this.addItemAction(this.telFrontNumChoice,  "telFrontNumChoice");
		this.addActionPerform(this.joinButton, 		"joinButton");
		this.addActionPerform(this.confirmButton, 	"confirmButton");
		this.addActionPerform(this.cancelButton, 	"cancelButton");
		this.addItemAction(this.genderManRadio, 	"genderManRadio");
		this.addItemAction(this.genderWomanRadio, 	"genderWomanRadio");
		
//		this.test();
	}

	//��� ���̺� ��ġ -- > �������
	public void setLabelPosition() {
		this.idLabel.setBounds(
				JoinSizesEnum.JOIN_IDLABEL_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_IDLABEL_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.pwLabel.setBounds(
				JoinSizesEnum.JOIN_PWDLABEL_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_PWDLABEL_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.rePwLabel.setBounds(
				JoinSizesEnum.JOIN_REPWDLABEL_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_REPWDLABEL_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.nameLabel.setBounds(
				JoinSizesEnum.JOIN_NAMELABEL_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_NAMELABEL_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
	
		this.genderLabel.setBounds(
				JoinSizesEnum.JOIN_GENDERLABEL_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_GENDERLABEL_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.birthLabel.setBounds(
				JoinSizesEnum.JOIN_BIRTHLABEL_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_BIRTHLABEL_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.yearLabel.setBounds(
				JoinSizesEnum.JOIN_YEARLABEL_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_YEARLABEL_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.monthLabel.setBounds(
				JoinSizesEnum.JOIN_MONTHLABEL_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_MONTHLABEL_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.dateLabel.setBounds(
				JoinSizesEnum.JOIN_DATE_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_DATE_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.emailLabel.setBounds(
				JoinSizesEnum.JOIN_EMAIL_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_EMAIL_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.telLabel.setBounds(
				JoinSizesEnum.JOIN_TEL_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_TEL_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.telHyphen1Label.setBounds(
				JoinSizesEnum.JOIN_HYPHEN1_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_HYPHEN1_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.telHyphen2Label.setBounds(
				JoinSizesEnum.JOIN_HYPHEN2_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_HYPHEN2_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.atLabel.setBounds(
				JoinSizesEnum.JOIN_AT_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_AT_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.emailTimeLabel.setBounds(
				JoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_CONFIRM_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_TEXT_WIDTH.getSize(),
				JoinSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
				
		
		this.add(this.idLabel);
		this.add(this.pwLabel);
		this.add(this.rePwLabel);
		this.add(this.nameLabel);
		this.add(this.genderLabel);
		this.add(this.birthLabel);
		         
		this.add(this.yearLabel);
		this.add(this.monthLabel);
		this.add(this.dateLabel);
	             
		this.add(this.emailLabel);
		this.add(this.telLabel);
		this.add(this.telHyphen1Label);
		this.add(this.telHyphen2Label);
		this.add(this.atLabel);
	}
	
	public void setTextFieldPosition() {
		this.idTextField.setBounds(
				JoinSizesEnum.JOIN_IDT_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_IDT_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.pwdField.setBounds(
				JoinSizesEnum.JOIN_PWDT_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_PWDT_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.rePwdField.setBounds(
				JoinSizesEnum.JOIN_REPWDT_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_REPWDT_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.nameTextField.setBounds(
				JoinSizesEnum.JOIN_NAMET_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_NAMET_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
	
		this.emailIDTextField.setBounds(
				JoinSizesEnum.JOIN_EMAILT_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_EMAILT_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.emailAddrTextField.setBounds(
				JoinSizesEnum.JOIN_EMAILADRT_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_EMAILADRT_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.emailConfTextField.setBounds(
				JoinSizesEnum.JOIN_CONFIRMT_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_CONFIRMT_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.telMiddleTextField.setBounds(
				JoinSizesEnum.JOIN_TELT_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_TELT_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_TEXT_WIDTH.getSize(),
				JoinSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
		
		this.telLastNumTextField.setBounds(
				JoinSizesEnum.JOIN_TELT2_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_TELT2_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_TEXT_WIDTH.getSize(),
				JoinSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
	
		this.add(emailConfTextField);
	}

	public void calSetDate(int selectYear, int selectMonth) {

		Calendar cal = Calendar.getInstance();//����
		
		cal.set(selectYear, selectMonth,1);
		cal.add(Calendar.DATE,-1);
		
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
 
		
		for(int k = 1; k <= lastDate; k++) {
			
			dateChoice.addItem(k);
		}

	}
// �̸��� �ڵ��� �޺� �ڽ� ���
	public void setChoicePosition() {
		String[] emailAddr = JoinSizesEnum.JOIN_EMAIL_ADDRESS.getStrArr();
		for(int i = 0, size = emailAddr.length; i < size; i++) {
			this.emailAddrChoice.addItem(emailAddr[i]);
		}
		
		String[] telFrontNum = JoinSizesEnum.JOIN_TEL_FRONT_NUM.getStrArr();
		for(int i = 0, size = telFrontNum.length; i < size; i++) {
			this.telFrontNumChoice.addItem(telFrontNum[i]);
		}
		
		this.emailAddrChoice.setBounds(
				JoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_JOIN_WIDTH.getSize(),
				JoinSizesEnum.SIZE_JOIN_HEIGHT.getSize()
		);
		this.setComboBoxStr(this.emailAddrChoice);
	
		this.telFrontNumChoice.setBounds(
				JoinSizesEnum.JOIN_NUMCHOICE_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_NUMCHOICE_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.setComboBoxStr(this.telFrontNumChoice);
		
		this.yearChoice.setBounds(
				JoinSizesEnum.JOIN_YEARCHOICE_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_YEARCHOICE_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.setComboBoxInt(this.yearChoice);
		
		this.monthChoice.setBounds(
				JoinSizesEnum.JOIN_MONTHCHOICE_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_MONTHCHOICE_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.setComboBoxInt(this.monthChoice);
		
		this.dateChoice.setBounds(
				JoinSizesEnum.JOIN_DATECHOICE_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_DATECHOICE_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.setComboBoxInt(this.dateChoice);
			
		this.add(emailAddrChoice);
		
	}
	
	public void setComboBoxInt(JComboBox<Integer> comboBox) {
		comboBox.setBackground(JoinSizesEnum.JOIN_EMAIL_COMBOBOX_BACKGROUND.getColor());
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(JoinSizesEnum.CHOICEBACKGROUND.getColor());
		comboBox.setFont(JoinSizesEnum.LABELFONT_DEFAULT.getFont());
	}
	
	public void setComboBoxStr(JComboBox<String> comboBox) {
		comboBox.setBackground(JoinSizesEnum.JOIN_EMAIL_COMBOBOX_BACKGROUND.getColor());
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(JoinSizesEnum.CHOICEBACKGROUND.getColor());
		comboBox.setFont(JoinSizesEnum.LABELFONT_DEFAULT.getFont());
	}
	
	//���� ���� ���� ��
	public void setButtonGroup() {
		this.genderManRadio.setBounds(
				JoinSizesEnum.GENDER_MAN_POSITION_X.getSize(),
				JoinSizesEnum.GENDER_MAN_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
	
		this.genderWomanRadio.setBounds(
				JoinSizesEnum.GENDER_WOMAN_POSITION_X.getSize(),
				JoinSizesEnum.GENDER_WOMAN_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		
	}
	
	// �̸�������, ȸ������, ��� ��ư
	public void setButtonPosItion() throws IOException {	
		//ȸ������ �ػ� �°� �׸���		
		this.joinButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/signUp/signup.jpg")).getScaledInstance(
					JoinSizesEnum.BUTTONIMAGE_WIDTH.getSize(),
					JoinSizesEnum.BUTTONIMAGE_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
	       
	    			
	    // ��� ��ư �ػ� �°� �׸���
		this.cancelButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/signUp/reset.jpg")).getScaledInstance(
					JoinSizesEnum.BUTTONIMAGE_WIDTH.getSize(),
	    		    JoinSizesEnum.BUTTONIMAGE_HEIGHT.getSize(),
	    		    Image.SCALE_AREA_AVERAGING))
		);

	
	    //���� �ػ� �±� �׸���
		this.confirmButton.setIcon(
    		new ImageIcon(ImageIO.read(
    			new File("resources/signUp/confirm.jpg")).getScaledInstance(
					JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
					JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
	    );
	    					
	    
		
		this.cancelButton.setBounds(
				JoinSizesEnum.JOIN_RESET_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_RESET_POSITTION_Y.getSize(),
				JoinSizesEnum.BUTTONIMAGE_WIDTH.getSize(),
				JoinSizesEnum.BUTTONIMAGE_HEIGHT.getSize()
		);
		this.joinButton.setBounds(
				JoinSizesEnum.JOIN_JOIN_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_JOIN_POSITTION_Y.getSize(),
				JoinSizesEnum.BUTTONIMAGE_WIDTH.getSize(),
				JoinSizesEnum.BUTTONIMAGE_HEIGHT.getSize()
		);
		this.confirmButton.setBounds(
				JoinSizesEnum.JOIN_CONFIRM_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_CONFIRM_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				JoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);		
	}
	
	//���� �޼��� ���
	public void setErrorPosition() {
		// ���� ���̺�
		
		Font font = JoinSizesEnum.JOIN_CHECKLABEL_FONT_DEFAULT.getFont();
		this.idErrorLabel	  = new JLabel();	
		this.idErrorLabel.setFont(font);
		this.pwdErrorLabel	  = new JLabel();
		this.pwdErrorLabel.setFont(font);
		this.rePwdErrorLabel  = new JLabel();
		this.rePwdErrorLabel.setFont(font);
		this.nameErrorLabel   = new JLabel();
		this.nameErrorLabel.setFont(font);
		this.genderErrorLabel = new JLabel();
		this.genderErrorLabel.setFont(font);
		this.emailErrorLabel  = new JLabel();		
		this.emailErrorLabel.setFont(font);
		this.telErrorLabel	  = new JLabel();
		this.telErrorLabel.setFont(font);
		this.labelSetting(this.telErrorLabel, JoinSizesEnum.LABELCOLOR_DEFAULT.getColor(), "join����");
		
		
		this.idErrorLabel.setBounds(
				JoinSizesEnum.JOIN_IDERROR_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_IDERROR_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				JoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.pwdErrorLabel.setBounds(
				JoinSizesEnum.JOIN_PWDERROR_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_PWDERROR_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				JoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.rePwdErrorLabel.setBounds(
				JoinSizesEnum.JOIN_REPWDERROR_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_REPWDERROR_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				JoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.nameErrorLabel.setBounds(
				JoinSizesEnum.JOIN_NAMEERROR_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_NAMEERROR_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				JoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.genderErrorLabel.setBounds(
				JoinSizesEnum.JOIN_GENDERERROR_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_GENDERERROR_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				JoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.emailErrorLabel.setBounds(
				JoinSizesEnum.JOIN_EMAILERROR_POSITTION_X.getSize(),
				JoinSizesEnum.JOIN_EMAILERROR_POSITTION_Y.getSize(),
				JoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				JoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.telErrorLabel.setBounds(
				JoinSizesEnum.JOIN_TELERROR_POSITION_X.getSize(),
				JoinSizesEnum.JOIN_TELERROR_POSITION_Y.getSize(),
				JoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				JoinSizesEnum.SIZE_ERROR_HEIGHT.getSize());

		this.add(this.idErrorLabel);
		this.add(this.pwdErrorLabel);
		this.add(this.rePwdErrorLabel);
		this.add(this.nameErrorLabel);
		this.add(this.genderErrorLabel);
		this.add(this.emailErrorLabel);
		this.add(this.telErrorLabel);
		this.add(this.emailTimeLabel);
		this.emailTimeLabel.setVisible(false);
	}
	
	
	public void labelSetting(JLabel label, Color color, String text) {
		label.setForeground(color);
		label.setText(this.errMessageMap.get(text));
	}
	
	public void addKeyAction(JComponent comp, String compName) {
		EmptyBorder border = JoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(compName);
		comp.setBorder(border);
		comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addKeyListener(this.joinAction);
		this.add(comp);
	}
	
	@SuppressWarnings("rawtypes")
	public void addItemAction(JComboBox comp, String compName) {
		EmptyBorder border = JoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(compName);
		comp.setBorder(border);
		comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addItemListener(this.joinAction);
		this.add(comp);
	}
	
	public void addItemAction(JRadioButton comp, String compName) {
		EmptyBorder border = JoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(compName);
		comp.setBorder(border);
		comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addItemListener(this.joinAction);
		this.add(comp);
	}
	
	public void addActionPerform(JButton comp, String compName) {
		comp.setName(compName);
		comp.setBorderPainted(false);
		comp.setFocusPainted(false);
		comp.setContentAreaFilled(false);
		comp.setIconTextGap(comp.getIconTextGap() - 15);
		comp.addActionListener(this.joinAction);
		this.add(comp);
	}
	
	public JTextField getIdTextField() {
		return idTextField;
	}
	
	public JPasswordField getPwdTextField() {
		return pwdField;
	}
	
	public JPasswordField getRePwdField() {
		return rePwdField;
	}
	
	public JTextField getNameTextField() {
		return nameTextField;
	}
	
	public JTextField getEmailIDTextField() {
		return emailIDTextField;
	}
	
	public JTextField getEmailAddrTextField() {
		return emailAddrTextField;
	}
	
	public JTextField getTelMidTextField() {
		return telMiddleTextField;
	}
	
	public JTextField getTelLastNumTextField() {
		return telLastNumTextField;
	}
	
	public JTextField getEmailConfTextField() {
		return emailConfTextField;
	}
	

	
	
	public JLabel getIdErrorLabel() {
		return idErrorLabel;
	}
	
	public JLabel getPwdErrorLabel() {
		return pwdErrorLabel;
	}
	
	public JLabel getRePwdErrorLabel() {
		return rePwdErrorLabel;
	}
	
	public JLabel getNameErrorLabel() {
		return nameErrorLabel;
	}
	
	public JLabel getEmailErrorLabel() {
		return emailErrorLabel;
	}
	
	public JLabel getGenderErrorLabel() {
		return genderErrorLabel;
	}
	
	public JLabel getTelErrorLabel() {
		return telErrorLabel;
	}
	
	public JLabel getEmailTimeLabel() {
		return emailTimeLabel;
	}
	
	
	
	
	
	public JComboBox<Integer> getDateChoice() {
		return dateChoice;
	}
	
	public JButton getConfirmButton() {
		return confirmButton;
	}
	
	public JRadioButton getGenderManRadio() {
		return genderManRadio;
	}
	
	public JRadioButton getGenderWomanRadio() {
		return genderWomanRadio;
	}
	
	public JoinClientAction getJoinAction() {
		return joinAction;
	}
	
	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
}