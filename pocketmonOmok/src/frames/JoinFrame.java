package frames;

import java.awt.Image;
import java.awt.image.BufferedImage;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import enums.ClientJoinSizesEnum;

@SuppressWarnings("serial")
public class JoinFrame extends JFrame {
	
	private JLabel idL;
	private JLabel idError;
	
	private JLabel pwdL;
	private JLabel rePwdL;
	private JLabel pwdError;
	private JLabel rePwdError;
	
	private JLabel nameL;
	private JLabel nameError;
	
	private JLabel genderL;
	
	private JRadioButton genderMan;
	private JRadioButton genderWoman;
	private ButtonGroup genderGroup;
	private JLabel genderError;

	private JLabel birthL;
	private JComboBox<Integer> yearChoice;
	private JComboBox<Integer> monthChoice;
	private JComboBox<Integer> dateChoice;
	private JLabel year;
	private JLabel month;
	private JLabel date;
	
	private JLabel emailL;
	private JLabel emailError;
	
	private JLabel telL;
	private JLabel hyphen1;
	private JLabel hyphen2;
	private JLabel at;
	
	private JTextField idT;
	private JTextField pwdT;
	private JTextField rePwdT;
	private JTextField nameT;
	
	private JTextField yearT;
	private JTextField monthT;
	private JTextField dateT;
	
	private JTextField emailT;
	private JTextField emailAdrT;
	private JComboBox<String> emailChoice;

	
	private JTextField telT;
	private JTextField telT2;
	private JTextField confirmT;
	
	private JComboBox<String> numChoice; //전화번호 02,010..
	
	private JButton join; //회원가입 버튼
	private JButton reset; //취소버튼
	private JButton confirm; //인증 버큰
	
	
//배경	
	private BufferedImage backGround;
	
	
//생년월일 콤보 박스
	Calendar rightNow = Calendar.getInstance();
	int nowyear       = rightNow.get(Calendar.YEAR);
	int nowmonth 	  = rightNow.get(Calendar.MONTH);
	int nowdate 	  = rightNow.get(Calendar.DATE);
	private Image reimage;
	


	public JoinFrame() throws IOException {
//모든 레이블 
		this.idL = new JLabel("아이디"); 
	
		this.pwdL    = new JLabel("비밀번호");
		this.rePwdL  = new JLabel("비밀번호재입력");
		this.nameL   = new JLabel("이름");
		this.genderL = new JLabel("성별");
		this.birthL  = new JLabel("생년월일");
		this.emailL  = new JLabel("이메일");
		this.telL    = new JLabel("전화번호");
	
		this.hyphen1 = new JLabel("-");
		this.hyphen2 = new JLabel("-");		
		this.at      = new JLabel("@");
		
		
		this.year    = new JLabel("년");
		this.month   = new JLabel("월");
		this.date    = new JLabel("일");
		
// 에러 레이블
		
		String idErrMsg = "특수문자 입력불가,6~15자 이외 글자수";
		String pwErrMsg = "pw불일치시,6~16글자수";
		String repwErrMsg = "";
		String nameErrMsg = "한글만 가능, 2자 이상";
		String genderErrMsg = "미선택시 - 필수 입력";
		String emailErrMsg = "인증번호 틀렸을시";
		
		this.idError     = new JLabel(idErrMsg);
		this.idError.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.pwdError    = new JLabel(pwErrMsg);
		this.pwdError.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.rePwdError  = new JLabel(repwErrMsg);
		this.rePwdError.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.nameError   = new JLabel(nameErrMsg);
		this.nameError.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.genderError = new JLabel(genderErrMsg);
		this.genderError.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		
		this.emailError  = new JLabel(emailErrMsg);
		this.emailError.setForeground(ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor());
		

	
		/*
		this.idError.setForeground(Color.red);
		this.pwdError.setForeground(Color.red);
		this.rePwdError.setForeground(Color.red);
		this.nameError.setForeground(Color.red);
		this.genderError.setForeground(Color.red);
		this.emailError.setForeground(Color.red);
		*/
		
			
// 모든 텍스트 필드
		this.idT    = new JTextField(10);
		this.pwdT   = new JTextField(10);
		this.rePwdT = new JTextField(10);
		this.nameT  = new JTextField(10);
		this.yearT  = new JTextField(4);
		this.monthT = new JTextField(2);
		this.dateT  = new JTextField(2);

		this.emailT    = new JTextField(10); 
		this.emailAdrT = new JTextField(10);
		this.telT      = new JTextField(4);
		this.telT2     = new JTextField(4);
		this.confirmT  = new JTextField(6);

		
// 이메일 전화번호 콤보 박스
		this.emailChoice = new JComboBox<>();
		this.confirm     = new JButton();

		this.emailChoice.addItem("직접입력");
		this.emailChoice.addItem("naver.com");
		this.emailChoice.addItem("hanmail.net");
		this.emailChoice.addItem("nate.com");
		this.emailChoice.addItem("gmail.com");
		 
		
		this.numChoice = new JComboBox<>();
		this.numChoice.addItem("02");
		this.numChoice.addItem("010");
		this.numChoice.addItem("011");
		this.numChoice.addItem("09");
	

		//생일콤보 박스
		this.yearChoice = new JComboBox<>();
		
		for(int i = 1900; i <= 2016; i++) {
			yearChoice.addItem(i);
		}
		
		this.monthChoice = new JComboBox<>();
		for(int j = 1; j <= 12; j++) {
			monthChoice.addItem(j);
		}
		
		this.dateChoice = new JComboBox<>();
		for(int k = 1; k <= 31; k++) {
			dateChoice.addItem(k);
		}
		
// 성별 여자남자 라디오 박스
		this.genderGroup = new ButtonGroup();
		this.genderWoman = new JRadioButton("여자");
		this.genderMan   = new JRadioButton("남자");

		ButtonGroup genderGroup = new ButtonGroup(); 
		
		genderGroup.add(genderMan);
		genderGroup.add(genderWoman);
		
		this.add(genderMan);
		this.add(genderWoman);
		
//회원가입, 취소 버튼	
		join  = new JButton("회원가입");
		reset = new JButton("취소");
		
		
		this.add(reset);
		this.add(join);
		
		confirm = new JButton("인증");
		this.add(confirm);
		


//전체 프레임 크기 출력
		
		//배경이미지 모니터의 해상도에 따라 조절되게 설정
	      backGround = ImageIO.read(new File("resources/signUp/back.png"));
	      reimage = backGround.getScaledInstance(
	                     ClientJoinSizesEnum.JOINFRAME_SIZE_WIDTH.getSize(),
	                     ClientJoinSizesEnum.JOINFRMAE_SIZE_HEIGHT.getSize(),
	                     Image.SCALE_SMOOTH);

	      this.setContentPane(new JLabel(new ImageIcon(reimage)));  
		
		
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
		
		
		 
		this.setLayout(null);
	    this.setTitle("회원가입");
	    this.setVisible(true);
	    this.setResizable(true);

	}
	
	//모든 레이블 위치 -- > 순서대로
	public void setLabelPosition() {
		
		
		this.idL.setBounds(
				ClientJoinSizesEnum.JOIN_ID_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_ID_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.pwdL.setBounds(
				ClientJoinSizesEnum.JOIN_PWD_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_PWD_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.rePwdL.setBounds(
				ClientJoinSizesEnum.JOIN_REPWD_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_REPWD_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.nameL.setBounds(
				ClientJoinSizesEnum.JOIN_NAME_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NAME_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
	
		this.genderL.setBounds(
				ClientJoinSizesEnum.JOIN_GENDER_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_GENDER_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.birthL.setBounds(
				ClientJoinSizesEnum.JOIN_BIRTH_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_BIRTH_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.year.setBounds(
				ClientJoinSizesEnum.JOIN_YEAR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_YEAR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.month.setBounds(
				ClientJoinSizesEnum.JOIN_MONTH_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_MONTH_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.date.setBounds(
				ClientJoinSizesEnum.JOIN_DATE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_DATE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.emailL.setBounds(
				ClientJoinSizesEnum.JOIN_EMAIL_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAIL_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.telL.setBounds(
				ClientJoinSizesEnum.JOIN_TEL_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_TEL_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.hyphen1.setBounds(
				ClientJoinSizesEnum.JOIN_HYPHEN1_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_HYPHEN1_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.hyphen2.setBounds(
				ClientJoinSizesEnum.JOIN_HYPHEN2_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_HYPHEN2_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.at.setBounds(
				ClientJoinSizesEnum.JOIN_AT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_AT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
				
		
		this.add(idL);
		this.add(pwdL);
		this.add(rePwdL);
		this.add(nameL);
		this.add(genderL);
		this.add(birthL);
		
		this.add(year);
		this.add(month);
		this.add(date);
	
		this.add(emailL);
		this.add(telL);
		this.add(hyphen1);
		this.add(hyphen2);
		this.add(at);
		
	}
	
	public void setTextFieldPosition() {
		
		this.idT.setBounds(
				ClientJoinSizesEnum.JOIN_IDT_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_IDT_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.pwdT.setBounds(
				ClientJoinSizesEnum.JOIN_PWDT_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_PWDT_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.rePwdT.setBounds(
				ClientJoinSizesEnum.JOIN_REPWDT_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_REPWDT_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		this.nameT.setBounds(
				ClientJoinSizesEnum.JOIN_NAMET_POSITION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NAMET_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
	
		this.emailT.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.emailAdrT.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILADRT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILADRT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.confirmT.setBounds(
				ClientJoinSizesEnum.JOIN_CONFIRMT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_CONFIRMT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.telT.setBounds(
				ClientJoinSizesEnum.JOIN_TELT_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_TELT_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_TEXT_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
		
		this.telT2.setBounds(
				ClientJoinSizesEnum.JOIN_TELT2_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_TELT2_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_TEXT_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
		
		
		
		this.add(idT);
		this.add(pwdT); this.add(rePwdT);
		this.add(nameT); 
		
		this.add(yearT); this.add(monthT); this.add(dateT);
		this.add(emailT); this.add(emailAdrT); this.add(confirmT);
		this.add(telT); this.add(telT2);

	}
	
// 이메일 핸드폰 콤보 박스 출력
	public void setChoicePosition() {
		this.emailChoice.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_HEIGHT.getSize()
		);
		this.numChoice.setBounds(
				ClientJoinSizesEnum.JOIN_NUMCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NUMCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.yearChoice.setBounds(
				ClientJoinSizesEnum.JOIN_YEARCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_YEARCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.monthChoice.setBounds(
				ClientJoinSizesEnum.JOIN_MONTHCHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_MONTHCHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.dateChoice.setBounds(
				ClientJoinSizesEnum.JOIN_DATECHOICE_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_DATECHOICE_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		
		this.add(emailChoice);
		this.add(numChoice);
		
		this.add(yearChoice);
		this.add(monthChoice);
		this.add(dateChoice);
	}
	
	//여자 남자 라디오 뱍스
	public void setButtonGroup() {
		this.genderMan.setBounds(
				ClientJoinSizesEnum.GENDER_MAN_POSITION_X.getSize(),
				ClientJoinSizesEnum.GENDER_MAN_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
		this.genderWoman.setBounds(
				ClientJoinSizesEnum.GENDER_WOMAN_POSITION_X.getSize(),
				ClientJoinSizesEnum.GENDER_WOMAN_POSITION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
				
		this.add(this.genderMan);
		this.add(this.genderWoman);
		
	}
	
	// 이메일인증, 회원가입, 취소 버튼
	public void setButtonPosItion() {
		this.reset.setBounds(
				ClientJoinSizesEnum.JOIN_RESET_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_RESET_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_HEIGHT.getSize()
		);
		this.join.setBounds(
				ClientJoinSizesEnum.JOIN_JOIN_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_JOIN_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_JOIN_HEIGHT.getSize()
		);
		this.confirm.setBounds(
				ClientJoinSizesEnum.JOIN_CONFIRM_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_CONFIRM_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_EMAIL_HEIGHT.getSize()
		);
				
		this.add(reset);
		this.add(join);
		this.add(confirm);
		
	}
	//에러 메세지 출력
	public void setErrorPosition() {
		this.idError.setBounds(
				ClientJoinSizesEnum.JOIN_IDERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_IDERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.pwdError.setBounds(
				ClientJoinSizesEnum.JOIN_PWDERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_PWDERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.rePwdError.setBounds(
				ClientJoinSizesEnum.JOIN_REPWDERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_REPWDERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.nameError.setBounds(
				ClientJoinSizesEnum.JOIN_NAMEERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_NAMEERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.genderError.setBounds(
				ClientJoinSizesEnum.JOIN_GENDERERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_GENDERERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);
		this.emailError.setBounds(
				ClientJoinSizesEnum.JOIN_EMAILERROR_POSITTION_X.getSize(),
				ClientJoinSizesEnum.JOIN_EMAILERROR_POSITTION_Y.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_WIDTH.getSize(),
				ClientJoinSizesEnum.SIZE_ERROR_HEIGHT.getSize()
		);

		this.add(idError);
		this.add(pwdError);
		this.add(rePwdError);
		this.add(nameError);
		this.add(genderError);
		this.add(emailError);
		
	}

}
