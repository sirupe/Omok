package frames;

import java.awt.Color;
import java.awt.Component;

import java.awt.Font;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import actions.join.JoinAction;
import enums.ClientJoinSizesEnum;
import enums.ClientJoinSizesEnum;
import enums.LoginFrameSizesEnum;
import oracle.sql.CLOB;

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
	private JComboBox<Integer> yearchoice;
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
	private JTextField pwdTextField;
	private JTextField rePwdTextField;
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

	
//생년월일 콤보 박스
	private Calendar rightNow = Calendar.getInstance();
	private int nowyear       = rightNow.get(Calendar.YEAR);
	private int nowmonth 	  = rightNow.get(Calendar.MONTH);
	private int nowdate 	  = rightNow.get(Calendar.DATE);

//배경
	private Image backGround;
	private Object joinButtonimage;
	private Object resetButtonImage;
	
	private int month;


	public JoinFrame() throws IOException {
//모든 레이블 
		this.idLabel = new JLabel("아이디"); 
		this.pwLabel    = new JLabel("비밀번호");
		this.rePwLabel  = new JLabel("비밀번호재입력");
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
				this.pwdTextField   = new JTextField(10);
				this.rePwdTextField = new JTextField(10);
				this.nameTextField  = new JTextField(10);
				this.yearTextField  = new JTextField(4);
				this.monthTextField = new JTextField(2);
				this.dateTextField  = new JTextField(2);

				this.emailIDTextField    = new JTextField(10); 
				this.emailAddrTextField = new JTextField(10);
				this.telMiddleNumTextField      = new JTextField(4);
				this.telBackNumTextField     = new JTextField(4);
				this.emailConfirmTextField  = new JTextField(6);

				
		// 이메일 전화번호 콤보 박스
				this.emailAddrChoice = new JComboBox<>();

				this.emailAddrChoice.addItem("직접입력");
				this.emailAddrChoice.addItem("naver.com");
				this.emailAddrChoice.addItem("hanmail.net");
				this.emailAddrChoice.addItem("nate.com");
				this.emailAddrChoice.addItem("gmail.com");
				 
				
				this.telFrontNumChoice = new JComboBox<>();
				
				this.telFrontNumChoice.addItem("010");
				this.telFrontNumChoice.addItem("011");
				this.telFrontNumChoice.addItem("016");
				this.telFrontNumChoice.addItem("019");
				
				
		// 성별 여자남자 라디오 박스
				this.genderButtonGroup = new ButtonGroup();
				this.genderWomanRadio = new JRadioButton("여자");
				this.genderManRadio   = new JRadioButton("남자");

				ButtonGroup genderGroup = new ButtonGroup(); 
				
				genderGroup.add(genderManRadio);
				genderGroup.add(genderWomanRadio);
				
				this.add(genderManRadio);
				this.add(genderWomanRadio);
				
				
		//회원가입, 취소 버튼	

				
				resetButton = new JButton("취소");
				
				joinButton  = new JButton();
				joinButton.setBorderPainted(false);
				joinButton.setFocusPainted(false);
				joinButton.setContentAreaFilled(false);
				
				joinButton = new JButton("회원가입");
				
				
				confirmButton  = new JButton();	
				confirmButton.setBorderPainted(false);
				confirmButton.setFocusPainted(false);
				confirmButton.setContentAreaFilled(false);
				
				confirmButton = new JButton("인증");
				
				this.add(confirmButton);
				this.add(resetButton);
				this.add(joinButton);
				
				yearchoice  = new JComboBox<>();
				monthChoice = new JComboBox<>();
				dateChoice = new JComboBox<>();
// 에러 레이블
		
		String idErrMsg 	  = "특수문자 입력불가,6~15자 이외 글자수";
		String pwErrMsg 	  = ",6~16글자수";
		String repwErrMsg 	  = "pw불일치시";
		String nameErrMsg 	  = "한글만 가능, 2자 이상";
		String genderErrMsg   = "미선택시 - 필수 입력";
		String emailErrMsg 	  = "인증번호 틀렸을시";
		
		this.idErrorLabel	  = new JLabel(idErrMsg);
		this.idErrorLabel.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.pwdErrorLabel	  = new JLabel(pwErrMsg);
		this.pwdErrorLabel.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.rePwdErrorLabel  = new JLabel(repwErrMsg);
		this.rePwdErrorLabel.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.nameErrorLabel   = new JLabel(nameErrMsg);
		this.nameErrorLabel.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.genderErrorLabel = new JLabel(genderErrMsg);
		this.genderErrorLabel.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.emailErrorLabel  = new JLabel(emailErrMsg);
		this.emailErrorLabel.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
//레이블 폰트
		
		this.idLabel.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		this.pwLabel.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		this.rePwLabel.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		this.nameLabel.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		this.birthLabel.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		this.genderLabel.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		this.emailLabel.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		this.telLabel.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		
//텍스트필드 테두리 없애기
		
		//border=new BevelBorder(BevelBorder.RAISED);//3차원적인 테두리 효과를 위한것이고 양각의 옵션을 준다.
		//  label.setBorder(border);//라벨에 적용시킨다.
		//  add(label);
		
		//idTextField.setBorder(new EmptyBorder(5,5,5,5));
		//setContentPane(idTextField);
		

			
// 모든 텍스트 필드
		this.idTextField	  = new JTextField(10);
		this.pwdTextField	  = new JTextField(10);
		this.rePwdTextField	  = new JTextField(10);
		this.nameTextField	  = new JTextField(10);
		this.yearTextField	  = new JTextField(4);
		this.monthTextField	  = new JTextField(2);
		this.dateTextField	  = new JTextField(2);

		this.emailIDTextField	   = new JTextField(10); 
		this.emailAddrTextField	   = new JTextField(10);
		this.telMiddleNumTextField = new JTextField(4);
		this.telBackNumTextField   = new JTextField(4);
		this.emailConfirmTextField = new JTextField(6);

		
// 이메일 전화번호 콤보 박스
		this.emailAddrChoice = new JComboBox<>();

		this.emailAddrChoice.addItem("직접입력");
		this.emailAddrChoice.addItem("naver.com");
		this.emailAddrChoice.addItem("hanmail.net");
		this.emailAddrChoice.addItem("nate.com");
		this.emailAddrChoice.addItem("gmail.com");
		 
		
		this.telFrontNumChoice = new JComboBox<>();
		
		this.telFrontNumChoice.addItem("010");
		this.telFrontNumChoice.addItem("011");

		this.telFrontNumChoice.addItem("016");
		this.telFrontNumChoice.addItem("019");
		
//텍스트 필드 테두리 없애기		
		rePwdTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		

		nameTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);

		yearTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		
		monthTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		
		dateTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		
		emailIDTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		
		emailAddrTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		
		telMiddleNumTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		
		telBackNumTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		
		emailConfirmTextField.setBorder(ClientJoinSizesEnum.LABEL_DEFAULT.getBorder());
		this.pwdTextField.setOpaque(true);
		

		
	
		


//전체 프레임 크기 출력
		
		//배경이미지 모니터의 해상도에 따라 조절되게 설정
	      backGround = ImageIO.read(new File("resources/signUp/joinn.jpg")).getScaledInstance(
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
	      
	      
		
//레이블 
		this.setLabelPosition();
//덱스트 필드
		this.setTextFieldPosition();
//이메일 생일 전화번호 콤보 박스
		this.setChoicePosition();
//성별 그룹 묶음
		this.setButtonGroup();
		this.setButtonPosItion();
//에러 메세지
		this.setErrorPosition();
		this.calendar();

		
		
		
		this.setLayout(null);
	    this.setTitle("회원가입");
	    this.setVisible(true);
	    this.setResizable(true);
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
		this.pwdTextField.setBounds(
				ClientJoinSizesEnum.JOIN_PWDT_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_PWDT_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.rePwdTextField.setBounds(
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
		
		
		
		this.add(idTextField);
		this.add(pwdTextField); this.add(rePwdTextField);
		this.add(nameTextField); 
		
		this.add(yearTextField); this.add(monthTextField); this.add(dateTextField);
		this.add(emailIDTextField); this.add(emailAddrTextField); this.add(emailConfirmTextField);
		this.add(telMiddleNumTextField); this.add(telBackNumTextField);

	}
	
	public void calendar() {
		int year = 2014;
		int month = 2;

		Calendar cal = Calendar.getInstance();//생성
		
		cal.set(year,month,1);
		cal.add(cal.DATE,-1);
		
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int startDate = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(year);
		System.out.println(month);
		System.out.println(lastDate);
		
 
		for(int i = 1990; i <= year; i++ ) {
			yearchoice.addItem(i);
			//yearchoice.setSelectedIndex(1);
		}
		for(int j = 1; j <= 12; j++) {
			monthChoice.addItem(j);
		}
		for(int k = startDate; k <= lastDate; k++) {
			
			dateChoice.addItem(k);
		}

	}
// 이메일 핸드폰 콤보 박스 출력
	public void setChoicePosition() {
		
		this.emailAddrChoice.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_HEIGHT.getSize()
		);
		//이메일 콤보 배경화면색깔, 글씨색깔, 글씨체
		this.emailAddrChoice.setBackground(Color.white);
		//this.emailAddrChoice.setBorder(new EmptyBorder(0,0,0,0));
		this.emailAddrChoice.setForeground(ClientJoinSizesEnum.CHOICEBACKGROUND.getColor());
		this.emailAddrChoice.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		
		this.telFrontNumChoice.setBounds(
				ClientJoinSizesEnum.JOIN_NUMCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NUMCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.telFrontNumChoice.setBackground(Color.white);
		this.telFrontNumChoice.setBorder(new EmptyBorder(0,0,0,0));
		this.telFrontNumChoice.setForeground(ClientJoinSizesEnum.CHOICEBACKGROUND.getColor());
		this.telFrontNumChoice.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		
		this.yearchoice.setBounds(
				ClientJoinSizesEnum.JOIN_YEARCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_YEARCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.yearchoice.setBackground(Color.white);
		this.yearchoice.setBorder(new EmptyBorder(0,0,0,0));
		this.yearchoice.setForeground(ClientJoinSizesEnum.CHOICEBACKGROUND.getColor());
		this.yearchoice.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		
		this.monthChoice.setBounds(
				ClientJoinSizesEnum.JOIN_MONTHCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_MONTHCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.monthChoice.setBackground(Color.white);
		this.monthChoice.setBorder(new EmptyBorder(0,0,0,0));
		this.monthChoice.setForeground(ClientJoinSizesEnum.CHOICEBACKGROUND.getColor());
		this.monthChoice.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		
		this.dateChoice.setBounds(
				ClientJoinSizesEnum.JOIN_DATECHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_DATECHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.dateChoice.setBackground(Color.white);
		this.dateChoice.setBorder(new EmptyBorder(0,0,0,0));
		this.dateChoice.setForeground(ClientJoinSizesEnum.CHOICEBACKGROUND.getColor());
		this.dateChoice.setFont(ClientJoinSizesEnum.LABELFONT_DEFAULT.getFont());
		

		
		this.add(emailAddrChoice);
		this.add(telFrontNumChoice);
		
		this.add(yearchoice);
		this.add(monthChoice);
		this.add(dateChoice);
		
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
				
		this.add(this.genderManRadio);
		this.add(this.genderWomanRadio);
		
	}
	
	// 이메일인증, 회원가입, 취소 버튼
	public void setButtonPosItion() throws IOException {
		
		//방생성 버튼이미지 짤리는걸 이미지 간격이동으로 해결해줌
	      this.joinButton.setIconTextGap(this.joinButton.getIconTextGap() - 15);
	      this.resetButton.setIconTextGap(this.joinButton.getIconTextGap() - 15);
	      this.confirmButton.setIconTextGap(this.joinButton.getIconTextGap() - 15);
	      
		
		//회원가입 해상도 맞게 그리기
//		this.joinButton      = ImageIO.read(new File("resources/signUp/signup.jpg"));
		
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

		this.add(idErrorLabel);
		this.add(pwdErrorLabel);
		this.add(rePwdErrorLabel);
		this.add(nameErrorLabel);
		this.add(genderErrorLabel);
		this.add(emailErrorLabel);
		
	}

//
//	public static void main(String[] args) throws IOException  {
//		
//		new JoinFrame();
//	}

}
       
