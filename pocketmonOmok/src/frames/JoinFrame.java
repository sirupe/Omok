package frames;

import java.awt.Color;
import java.awt.Component;
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

import actions.join.JoinAction;
import enums.ClientJoinSizesEnum;

// 수진
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
	private JLabel emailErrorLabel;
	
	private JLabel telLabel;
	private JLabel telHyphen1Label;
	private JLabel telHyphen2Label;
	private JLabel atLabel;
	
	private JTextField idTextField;
	private JPasswordField pwdField;
	private JPasswordField rePwdField;
	private JTextField nameTextField;
	
	private JTextField yearTextField;
	private JTextField monthTextField;
	private JTextField dateTextField;
	
	private JTextField emailIDTextField;
	private JTextField emailAddrTextField;
	private JTextField emailConfirmTextField;
	private JComboBox<String> emailAddrChoice;

	private JComboBox<String> telFrontNumChoice; //전화번호 02,010..
	private JTextField telMiddleNumTextField;
	private JTextField telBackNumTextField;	
	
	private JButton joinButton; //회원가입 버튼
	private JButton resetButton; //취소버튼
	private JButton confirmButton; //인증 버큰

	
////생년월일 콤보 박스
//	private Calendar rightNow = Calendar.getInstance();
//	private int nowyear       = rightNow.get(Calendar.YEAR);
//	private int nowmonth 	  = rightNow.get(Calendar.MONTH);
//	private int nowdate 	  = rightNow.get(Calendar.DATE);

//배경
	private Image backGround;
<<<<<<< HEAD
//	private Object joinButtonimage;
//	private Object resetButtonImage;
=======
>>>>>>> c8fbb831f30fada30afcd93fc75ca9929e1b0d0b
	
	private LoginPanel loginPanel;
	private JoinAction joinAction;

	public JoinFrame(LoginPanel loginPanel) throws IOException {
		this.loginPanel = loginPanel;
		this.joinAction = new JoinAction(this.loginPanel, this);
		this.addWindowListener(this.joinAction);

		//모든 레이블 
		this.idLabel 	 = new JLabel("아이디"); 
		this.pwLabel     = new JLabel("비밀번호");
		this.rePwLabel   = new JLabel("비밀번호재입력");
		this.nameLabel   = new JLabel("이름");
		this.genderLabel = new JLabel("성별");
		this.birthLabel  = new JLabel("생년월일");
		this.emailLabel  = new JLabel("이메일");
		this.telLabel    = new JLabel("전화번호");
	
		this.telHyphen1Label  = new JLabel("-");
		this.telHyphen2Label  = new JLabel("-");		
		this.atLabel      	  = new JLabel("@");
		
		this.yearLabel    	  = new JLabel("년");
		this.monthLabel   	  = new JLabel("월");
		this.dateLabel    	  = new JLabel("일");
		
		// 모든 텍스트 필드
		this.idTextField    = new JTextField(10);
		this.pwdField   = new JPasswordField(10);
		this.rePwdField = new JPasswordField(10);
		this.nameTextField  = new JTextField(10);
		this.yearTextField  = new JTextField(4);
		this.monthTextField = new JTextField(2);
		this.dateTextField  = new JTextField(2);

		this.emailIDTextField    	= new JTextField(10); 
		this.emailAddrTextField 	= new JTextField(10);
		this.telMiddleNumTextField	= new JTextField(4);
		this.telBackNumTextField    = new JTextField(4);
		this.emailConfirmTextField  = new JTextField(6);
				
		// 이메일 전화번호 콤보 박스
		this.emailAddrChoice   = new JComboBox<>();
		this.telFrontNumChoice = new JComboBox<>();

		// 성별 여자남자 라디오 박스
		this.genderButtonGroup = new ButtonGroup();
		this.genderWomanRadio  = new JRadioButton("여자");
		this.genderManRadio    = new JRadioButton("남자");

		this.genderButtonGroup = new ButtonGroup();
		this.genderButtonGroup.add(this.genderManRadio);
		this.genderButtonGroup.add(this.genderWomanRadio);
		
				
		//회원가입, 취소 버튼	
		this.resetButton  = new JButton();
		this.resetButton.setBorderPainted(false);
		this.resetButton.setFocusPainted(false);
		this.resetButton.setContentAreaFilled(false);
		
		this.joinButton  = new JButton();
		this.joinButton.setBorderPainted(false);
		this.joinButton.setFocusPainted(false);
		this.joinButton.setContentAreaFilled(false);
		
		this.confirmButton  = new JButton();	
		this.confirmButton.setBorderPainted(false);
		this.confirmButton.setFocusPainted(false);
		this.confirmButton.setContentAreaFilled(false);
		
		this.add(confirmButton);
		this.add(resetButton);
		this.add(joinButton);

		this.yearChoice  = new JComboBox<Integer>();
		this.monthChoice = new JComboBox<Integer>();
		this.dateChoice  = new JComboBox<Integer>();

//레이블 폰트
		Font labelFont = ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont();
		this.idLabel.setFont(labelFont);
		this.pwLabel.setFont(labelFont);
		this.rePwLabel.setFont(labelFont);
		this.nameLabel.setFont(labelFont);
		this.birthLabel.setFont(labelFont);
		this.genderLabel.setFont(labelFont);
		this.emailLabel.setFont(labelFont);
		this.telLabel.setFont(labelFont);

//텍스트필드
		Font textFont = ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont();
		this.idTextField.setFont(textFont);
		this.pwdTextField.setFont(textFont);
		this.rePwdTextField.setFont(textFont);
		this.nameTextField.setFont(textFont);
		this.yearTextField.setFont(textFont);
		this.monthTextField.setFont(textFont);
		this.dateTextField.setFont(textFont);

		this.emailIDTextField.setFont(textFont);
		this.emailAddrTextField.setFont(textFont);
		this.telMiddleNumTextField.setFont(textFont);
		this.telBackNumTextField.setFont(textFont);
		this.emailConfirmTextField.setFont(textFont);
		
// 이메일 전화번호 콤보 박스
		this.emailAddrChoice = new JComboBox<String>();
		this.telFrontNumChoice = new JComboBox<String>();
//전체 프레임 크기 출력
		
		//배경이미지 모니터의 해상도에 따라 조절되게 설정
	    backGround = ImageIO.read(
	   		  new File("resources/signUp/joinn.jpg")).getScaledInstance(
	                   ClientJoinSizesEnum.JOINFRAME_SIZE_WIDTH.getSize(),
	                   ClientJoinSizesEnum.JOINFRMAE_SIZE_HEIGHT.getSize(),
	                   Image.SCALE_SMOOTH);

	    this.setContentPane(new JLabel(new ImageIcon(backGround))); 
	    
		
	    this.setBounds(
			ClientJoinSizesEnum.JOINFRMAE_POSITION_X.getSize(),
			ClientJoinSizesEnum.JOINFRMAE_POSITION_Y.getSize(),
			ClientJoinSizesEnum.JOINFRAME_SIZE_WIDTH.getSize(),
			ClientJoinSizesEnum.JOINFRMAE_SIZE_HEIGHT.getSize()
		);

		this.setLabelPosition();	//레이블 
		
		this.setTextFieldPosition();//덱스트 필드
	
		this.setChoicePosition();	//이메일 생일 전화번호 콤보 박스
		
		this.setButtonGroup();		//성별 그룹 묶음
		this.setButtonPosItion();	
		
		this.setErrorPosition();	//에러 메세지	

		this.calSetDate(2016, 12);			//캘린더
		for(int i = 1900; i <= 2016; i++ ) {
			yearChoice.addItem(i);
		}
		for(int j = 1; j <= 12; j++) {
			monthChoice.addItem(j);
		}

		this.setLayout(null);
	    this.setTitle("회원가입");
	    this.setVisible(true);
	    this.setResizable(false);
	    
		this.addKeyAction(this.idTextField, 		"idTextField");
		this.addKeyAction(this.pwdField, 			"pwdField");
		this.addKeyAction(this.rePwdField, 			"rePwdField");
		this.addKeyAction(this.nameTextField, 		"nameTextField");
		this.addMouseAction(this.genderManRadio, 	"genderManRadio");
		this.addMouseAction(this.genderWomanRadio, 	"genderWomanRadio");
		this.addItemAction(this.yearChoice, 		"yearChoice");
		this.addItemAction(this.monthChoice, 		"monthChoice");
		this.addItemAction(this.dateChoice, 		"dateChoice");
	}

	//모든 레이블 위치 -- > 순서대로
	public void setLabelPosition() {
		this.idLabel.setBounds(
				ClientJoinSizesEnum.JOIN_IDLABEL_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_IDLABEL_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.pwLabel.setBounds(
				ClientJoinSizesEnum.JOIN_PWDLABEL_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_PWDLABEL_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.rePwLabel.setBounds(
				ClientJoinSizesEnum.JOIN_REPWDLABEL_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_REPWDLABEL_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.nameLabel.setBounds(
				ClientJoinSizesEnum.JOIN_NAMELABEL_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NAMELABEL_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
	
		this.genderLabel.setBounds(
				ClientJoinSizesEnum.JOIN_GENDERLABEL_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_GENDERLABEL_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.birthLabel.setBounds(
				ClientJoinSizesEnum.JOIN_BIRTHLABEL_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_BIRTHLABEL_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.yearLabel.setBounds(
				ClientJoinSizesEnum.JOIN_YEARLABEL_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_YEARLABEL_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.monthLabel.setBounds(
				ClientJoinSizesEnum.JOIN_MONTHLABEL_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_MONTHLABEL_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.dateLabel.setBounds(
				ClientJoinSizesEnum.JOIN_DATE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_DATE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.emailLabel.setBounds(
				ClientJoinSizesEnum.JOIN_EMAIL_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAIL_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.telLabel.setBounds(
				ClientJoinSizesEnum.JOIN_TEL_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_TEL_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.telHyphen1Label.setBounds(
				ClientJoinSizesEnum.JOIN_HYPHEN1_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_HYPHEN1_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.telHyphen2Label.setBounds(
				ClientJoinSizesEnum.JOIN_HYPHEN2_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_HYPHEN2_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.atLabel.setBounds(
				ClientJoinSizesEnum.JOIN_AT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_AT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
				
		
		this.add(idLabel);
		this.add(pwLabel);
		this.add(rePwLabel);
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
	
	public void setTextFieldPosition() {
		this.idTextField.setBounds(
				ClientJoinSizesEnum.JOIN_IDT_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_IDT_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.pwdField.setBounds(
				ClientJoinSizesEnum.JOIN_PWDT_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_PWDT_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.rePwdField.setBounds(
				ClientJoinSizesEnum.JOIN_REPWDT_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_REPWDT_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.nameTextField.setBounds(
				ClientJoinSizesEnum.JOIN_NAMET_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NAMET_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
	
		this.emailIDTextField.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.emailAddrTextField.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILADRT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILADRT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.emailConfirmTextField.setBounds(
				ClientJoinSizesEnum.JOIN_CONFIRMT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_CONFIRMT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.telMiddleNumTextField.setBounds(
				ClientJoinSizesEnum.JOIN_TELT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_TELT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_TEXT_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
		
		this.telBackNumTextField.setBounds(
				ClientJoinSizesEnum.JOIN_TELT2_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_TELT2_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_TEXT_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
	
		this.add(yearTextField); 
		this.add(monthTextField); 
		this.add(dateTextField);
		this.add(emailIDTextField); 
		this.add(emailAddrTextField); 
		this.add(emailConfirmTextField);
		this.add(telMiddleNumTextField); 
		this.add(telBackNumTextField);
	}
	//TODO
	public void calSetDate(int selectYear, int selectMonth) {

		Calendar cal = Calendar.getInstance();//생성
		
		cal.set(selectYear, selectMonth,1);
		cal.add(Calendar.DATE,-1);
		
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
 
		
		for(int k = 1; k <= lastDate; k++) {
			
			dateChoice.addItem(k);
		}

	}
// 이메일 핸드폰 콤보 박스 출력
	public void setChoicePosition() {
		String[] emailAddr = ClientJoinSizesEnum.JOIN_EMAIL_ADDRESS.getStrArr();
		for(int i = 0, size = emailAddr.length; i < size; i++) {
			this.emailAddrChoice.addItem(emailAddr[i]);
		}
		
		String[] telFrontNum = ClientJoinSizesEnum.JOIN_TEL_FRONT_NUM.getStrArr();
		for(int i = 0, size = telFrontNum.length; i < size; i++) {
			this.telFrontNumChoice.addItem(telFrontNum[i]);
		}
		
		this.emailAddrChoice.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_HEIGHT.getSize()
		);
		this.setComboBoxStr(this.emailAddrChoice);
	
		this.telFrontNumChoice.setBounds(
				ClientJoinSizesEnum.JOIN_NUMCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NUMCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.setComboBoxStr(this.telFrontNumChoice);
		
		this.yearChoice.setBounds(
				ClientJoinSizesEnum.JOIN_YEARCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_YEARCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.setComboBoxInt(this.yearChoice);
		
		this.monthChoice.setBounds(
				ClientJoinSizesEnum.JOIN_MONTHCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_MONTHCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.setComboBoxInt(this.monthChoice);
		
		this.dateChoice.setBounds(
				ClientJoinSizesEnum.JOIN_DATECHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_DATECHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.setComboBoxInt(this.dateChoice);
			
		this.add(emailAddrChoice);
		this.add(telFrontNumChoice);
		
	}
	
	public void setComboBoxInt(JComboBox<Integer> comboBox) {
		comboBox.setBackground(ClientJoinSizesEnum.JOIN_EMAIL_COMBOBOX_BACKGROUND.getColor());
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(ClientJoinSizesEnum.CHOICEBACKGROUND.getColor());
		comboBox.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
	}
	
	public void setComboBoxStr(JComboBox<String> comboBox) {
		comboBox.setBackground(ClientJoinSizesEnum.JOIN_EMAIL_COMBOBOX_BACKGROUND.getColor());
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(ClientJoinSizesEnum.CHOICEBACKGROUND.getColor());
		comboBox.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
	}
	
	//여자 남자 라디오 뱍스
	public void setButtonGroup() {
		this.genderManRadio.setBounds(
				ClientJoinSizesEnum.GENDER_MAN_POSITION_X.getSize(),
				ClientJoinSizesEnum.GENDER_MAN_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
	
		this.genderWomanRadio.setBounds(
				ClientJoinSizesEnum.GENDER_WOMAN_POSITION_X.getSize(),
				ClientJoinSizesEnum.GENDER_WOMAN_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		
	}
	
	// 이메일인증, 회원가입, 취소 버튼
	public void setButtonPosItion() throws IOException {
		
		//방생성 버튼이미지 짤리는걸 이미지 간격이동으로 해결해줌
		this.joinButton.setIconTextGap(this.joinButton.getIconTextGap() - 15);
		this.resetButton.setIconTextGap(this.joinButton.getIconTextGap() - 15);
		this.confirmButton.setIconTextGap(this.joinButton.getIconTextGap() - 15);
	      
		
		//회원가입 해상도 맞게 그리기		
		this.joinButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/signUp/signup.jpg")).getScaledInstance(
					ClientJoinSizesEnum.BUTTONIMAGE_WIDTH.getSize(),
					ClientJoinSizesEnum.BUTTONIMAGE_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
	       
	    			
	    // 취소 버튼 해상도 맞게 그리기
		this.resetButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/signUp/reset.jpg")).getScaledInstance(
					ClientJoinSizesEnum.BUTTONIMAGE_WIDTH.getSize(),
	    		    ClientJoinSizesEnum.BUTTONIMAGE_HEIGHT.getSize(),
	    		    Image.SCALE_AREA_AVERAGING))
		);

	
	    //인증 해상도 맞기 그리기
		this.confirmButton.setIcon(
    		new ImageIcon(ImageIO.read(
    			new File("resources/signUp/confirm.jpg")).getScaledInstance(
					ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
					ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
	    );
	    					
	    
		
		this.resetButton.setBounds(
				ClientJoinSizesEnum.JOIN_RESET_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_RESET_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.BUTTONIMAGE_WIDTH.getSize(),
				ClientJoinSizesEnum.BUTTONIMAGE_HEIGHT.getSize()
		);
		this.joinButton.setBounds(
				ClientJoinSizesEnum.JOIN_JOIN_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_JOIN_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.BUTTONIMAGE_WIDTH.getSize(),
				ClientJoinSizesEnum.BUTTONIMAGE_HEIGHT.getSize()
		);
		this.confirmButton.setBounds(
				ClientJoinSizesEnum.JOIN_CONFIRM_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_CONFIRM_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
	 
		this.add(resetButton);
		this.add(joinButton);
		this.add(confirmButton);
		
	}
	
	//에러 메세지 출력
	public void setErrorPosition() {
		// 에러 레이블
		
		this.idErrorLabel	  = new JLabel();		
		this.pwdErrorLabel	  = new JLabel();		
		this.rePwdErrorLabel  = new JLabel();		
		this.nameErrorLabel   = new JLabel();		
		this.genderErrorLabel = new JLabel();		
		this.emailErrorLabel  = new JLabel();		
		
		
		this.idErrorLabel.setBounds(
				ClientJoinSizesEnum.JOIN_IDERROR_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_IDERROR_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.pwdErrorLabel.setBounds(
				ClientJoinSizesEnum.JOIN_PWDERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_PWDERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.rePwdErrorLabel.setBounds(
				ClientJoinSizesEnum.JOIN_REPWDERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_REPWDERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.nameErrorLabel.setBounds(
				ClientJoinSizesEnum.JOIN_NAMEERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NAMEERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.genderErrorLabel.setBounds(
				ClientJoinSizesEnum.JOIN_GENDERERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_GENDERERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.emailErrorLabel.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);

		this.add(this.idErrorLabel);
		this.add(this.pwdErrorLabel);
		this.add(this.rePwdErrorLabel);
		this.add(this.nameErrorLabel);
		this.add(this.genderErrorLabel);
		this.add(this.emailErrorLabel);
	}
	
	public void labelSetting(JLabel label, Color color, String text) {
		Map<String, String> map = ClientJoinSizesEnum.JOIN_MESSAGE.getMessageMap();
		
		label.setForeground(color);
		label.setText(map.get(text));
	}
	
	public void addKeyAction(JComponent comp, String compName) {
		EmptyBorder border = ClientJoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(compName);
		comp.setBorder(border);
		comp.setFont(ClientJoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addKeyListener(this.joinAction);
		this.add(comp);
	}
	
	public void addMouseAction(JComponent comp, String compName) {
		EmptyBorder border = ClientJoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(compName);
		comp.setBorder(border);
		comp.setFont(ClientJoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.setOpaque(false);
		comp.addKeyListener(this.joinAction);
		this.add(comp);
	}
	
	public void addItemAction(JComboBox comp, String compName) {
		EmptyBorder border = ClientJoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(compName);
		comp.setBorder(border);
		comp.setFont(ClientJoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addItemListener(this.joinAction);
		this.add(comp);
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
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
}