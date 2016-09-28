package frames.joinFrames;

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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.media.sound.MidiOutDeviceProvider;

import enums.frames.JoinSizesEnum;
import enums.frames.ModifyJoinEnum;
import enums.frames.SearchIdEnum;
import utility.JTextFieldNumOnly;

public class ModifyJoinFrame extends JFrame {

	private JLabel userIdLabel;
	
	private JLabel pwdLabel;
	private JLabel rePwdLabel;
	private JLabel PwdLabelError; //패스워드 일치 에러
	
	private JLabel nameLabel;
	
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

	private JComboBox<String> telFrontNumChoice; //전화번호 02,010..
	private JTextField telMiddleTextField;
	private JTextField telLastNumTextField;	
	
	private JButton modifyButton; //회원가입 버튼
	private JButton cancelButton; //취소버튼
	private JButton dropoutButton; //인증 버큰

	
	
	private Image background;

	public ModifyJoinFrame() throws IOException {
		//모든 레이블 
				this.userIdLabel 	 = new JLabel("아이디"); 
				this.pwdLabel         = new JLabel("비밀번호");
				this.rePwdLabel      = new JLabel("비밀번호재입력");
				
				this.PwdLabelError   = new JLabel("ERROR MESSAGE"); //에러 입히기
				this.PwdLabelError.setBackground(Color.pink);
				
				
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
				this.emailTimeLabel	  = new JLabel("3:00");
				
				// 모든 텍스트 필드
				this.idTextField    = new JTextField(10);

				this.pwdField       = new JPasswordField(10);
				this.rePwdField     = new JPasswordField(10);
				this.nameTextField  = new JTextField(10);

				this.emailIDTextField    	= new JTextField(10); 
				this.emailAddrTextField 	= new JTextField(10);
				this.telMiddleTextField		= new JTextField(new JTextFieldNumOnly(4), "", 0);
				this.telLastNumTextField    = new JTextField(new JTextFieldNumOnly(4), "", 0);
				this.emailConfTextField     = new JTextField(new JTextFieldNumOnly(6), "", 0);

				// 성별 여자남자 라디오 박스
				this.genderButtonGroup = new ButtonGroup();
				this.genderWomanRadio  = new JRadioButton("여자");
				this.genderManRadio    = new JRadioButton("남자");
				this.genderWomanRadio.setOpaque(false);
				this.genderManRadio.setOpaque(false);
				

				this.genderButtonGroup = new ButtonGroup();
				this.genderButtonGroup.add(this.genderManRadio);
				this.genderButtonGroup.add(this.genderWomanRadio);
				
				//이메일 전화번호 콤보박스
				this.emailAddrChoice = new JComboBox<String>();
				this.telFrontNumChoice = new JComboBox<String>();
				
				//수정, 취소, 탈퇴버튼
				this.modifyButton   = new JButton();
				this.cancelButton	= new JButton();
				this.dropoutButton  = new JButton();	

				this.yearChoice  = new JComboBox<Integer>();
				this.monthChoice = new JComboBox<Integer>();
				this.dateChoice  = new JComboBox<Integer>();
				
				//레이블 폰트
				Font labelFont = SearchIdEnum.LABELFONT_DEFAULT.getFont();
				this.userIdLabel.setFont(labelFont);
				this.pwdLabel.setFont(labelFont);
				this.rePwdLabel.setFont(labelFont);
				this.nameLabel.setFont(labelFont);
				this.birthLabel.setFont(labelFont);
				this.genderLabel.setFont(labelFont);
				this.emailLabel.setFont(labelFont);
				this.telLabel.setFont(labelFont);
				this.emailTimeLabel.setFont(labelFont);
				
				//텍스트필드
				Font textFont = JoinSizesEnum.LABELFONT_DEFAULT.getFont();
				this.idTextField.setFont(textFont);
				this.pwdField.setFont(textFont);
				this.rePwdField.setFont(textFont);
				this.nameTextField.setFont(textFont);

				this.emailIDTextField.setFont(textFont);
				this.emailAddrTextField.setFont(textFont);
				
		//배경화면
				background = ImageIO.read(
			   		  new File("resources/signUp/joinn.jpg")).getScaledInstance(
		                  JoinSizesEnum.JOINFRAME_SIZE_WIDTH.getSize(),
		                  JoinSizesEnum.JOINFRMAE_SIZE_HEIGHT.getSize(),
		                  Image.SCALE_SMOOTH);
	
				this.setContentPane(new JLabel(new ImageIcon(background))); 
				
				this.setBounds(				
					ModifyJoinEnum.MODIFY_JOINFRMAE_POSITION_X.getSize(),
					ModifyJoinEnum.MODIFY_JOINFRMAE_POSITION_Y.getSize(),
					ModifyJoinEnum.MODIFY_JOINFRAME_SIZE_WIDTH.getSize(),
					ModifyJoinEnum.MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize()		
				);
				
				this.calSetDate(2016, 12);			//캘린더
				for(int i = 2016; i >= 1900; i-- ) {
					yearChoice.addItem(i);
				}
				for(int j = 1; j <= 12; j++) {
					monthChoice.addItem(j);
				}
		  
				  //부를 메소드 추가 
				  this.setLabelPosition();
				  this.setTextFieldPosition();
				  this.setButtonGroup();
				  this.setButtonPosition();
				  this.setChoicePosition();
			  
			 
				  this.setLayout(null);
				  this.setVisible(true);
				  this.setTitle("회원가입수정");
				  this.setResizable(false);
		  
		
	}
	//라벨 위치 -- > 순서대로
	public void setLabelPosition() {
		this.userIdLabel.setBounds(ModifyJoinEnum.MODIFY_ID_LABEL.getRectangle());
		this.pwdLabel.setBounds(ModifyJoinEnum.MODIFY_PWD_LABEL.getRectangle());	
		this.rePwdLabel.setBounds(ModifyJoinEnum.MODIFY_REPWD_LABEL.getRectangle());	
		this.PwdLabelError.setBounds(ModifyJoinEnum.MODIFY_REPWDERROR_LABEL.getRectangle());
		this.PwdLabelError.setForeground(Color.red);
		
		this.nameLabel.setBounds(ModifyJoinEnum.MODIFY_NAME_LABEL.getRectangle());
		this.genderLabel.setBounds(ModifyJoinEnum.MODIFY_GENDER_LABEL.getRectangle());
		this.birthLabel.setBounds(ModifyJoinEnum.MODIFY_BIRTH_LABEL.getRectangle());
		this.yearLabel.setBounds(ModifyJoinEnum.MODIFY_YEAR_LABEL.getRectangle());
		this.monthLabel.setBounds(ModifyJoinEnum.MODIFY_MONTH_LABEL.getRectangle());
		this.dateLabel.setBounds(ModifyJoinEnum.MODIFY_DATE_LABEL.getRectangle());
	
		
		this.emailLabel.setBounds(ModifyJoinEnum.MODIFY_EMAIL_LABEL.getRectangle());
		
		this.telLabel.setBounds(ModifyJoinEnum.MODIFY_TELNAME_LABELMID.getRectangle());
		this.telHyphen1Label.setBounds(ModifyJoinEnum.MODIFY_TELHYPHEN1_LABEL.getRectangle());
		this.telHyphen2Label.setBounds(ModifyJoinEnum.MODIFY_TELHYPHEN2_LABEL.getRectangle());
		this.atLabel.setBounds(ModifyJoinEnum.MODIFY_AT_LABEL.getRectangle());
		
		
		this.add(userIdLabel);
		this.add(pwdLabel);
		this.add(rePwdLabel);
		this.add(PwdLabelError);
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
	
	//텍스트필드
	public void setTextFieldPosition() {
		this.idTextField.setBounds(ModifyJoinEnum.MODIFY_ID_TEXT.getRectangle());
		this.pwdField.setBounds(ModifyJoinEnum.MODIFY_PWD_TEXT.getRectangle());
		this.rePwdField.setBounds(ModifyJoinEnum.MODIFY_REPWD_TEXT.getRectangle());
		this.nameTextField.setBounds(ModifyJoinEnum.MODIFY_NAME_TEXT.getRectangle());
		
		this.telMiddleTextField.setBounds(ModifyJoinEnum.MODIFY_TELMID_TEXT.getRectangle());
		this.telLastNumTextField.setBounds(ModifyJoinEnum.	MODIFY_TELEND_TEXT.getRectangle());
		this.emailIDTextField.setBounds(ModifyJoinEnum.MODIFY_EAMILID_TEXT.getRectangle());
		this.emailAddrTextField.setBounds(ModifyJoinEnum.MODIFY_EAMILADDR_TEXT.getRectangle());
		
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
	}
	public void setButtonGroup() {
		this.genderManRadio.setBounds(ModifyJoinEnum.MODIFY_GENDERMAN_RADIOBUTTON.getRectangle());
		this.genderWomanRadio.setBounds(ModifyJoinEnum.MODIFY_GENDERWOMAN_RADIOBUTTON.getRectangle());
		
		this.add(genderWomanRadio);
		this.add(genderManRadio);
	}
//=========================================================================================================
	
	public void calSetDate(int selectYear, int selectMonth) {

		Calendar cal = Calendar.getInstance();//생성
		cal.set(selectYear, selectMonth,1);
		cal.add(Calendar.DATE,-1);	
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int k = 1; k <= lastDate; k++) {	
			dateChoice.addItem(k);
		}
	}
	public void setChoicePosition() {
		//이메일과 전화번호를 이넘을 받아 배열로 나열하고 for문으로 반복하여 넘겨 받았습니다..?
		String[] emailAddr = ModifyJoinEnum.MODIFY_EMAIL_COMBO_ADDRESS.getStrArr();
		for(int i = 0, size = emailAddr.length; i < size; i++) {
			this.emailAddrChoice.addItem(emailAddr[i]);
		}
		String[] telFrontNum = ModifyJoinEnum.MODIFY_TEL_FRONT_NUM_COMBO.getStrArr();
		for(int i = 0, size = telFrontNum.length; i < size; i++) {
			this.telFrontNumChoice.addItem(telFrontNum[i]);
		}
		
		//이메일주소
		this.emailAddrChoice.setBounds(ModifyJoinEnum.MODIFY_EMAILADDR_COMBO.getRectangle());
		this.setComboBoxStr(this.emailAddrChoice);
		this.add(emailAddrChoice);
		
		//전화번호 앞자리
		this.telFrontNumChoice.setBounds(ModifyJoinEnum.MODIFY_TELFRONTNUM_COMBO.getRectangle());
		this.setComboBoxStr(this.telFrontNumChoice);
		this.add(telFrontNumChoice);

		//년
		this.yearChoice.setBounds(ModifyJoinEnum.MODIFY_YEAR_COMBOBOX.getRectangle());
		this.setComboBoxInt(this.yearChoice);
		this.add(yearChoice);
		//월
		this.monthChoice.setBounds(ModifyJoinEnum.MODIFY_MONTH_COMBOBOX.getRectangle());
		this.setComboBoxInt(this.monthChoice);
		this.add(monthChoice);
		//일
		this.dateChoice.setBounds(ModifyJoinEnum.MODIFY_DATE_COMBOBOX.getRectangle());
		this.setComboBoxInt(this.dateChoice);
		this.add(dateChoice);
	 }
	public void setComboBoxInt(JComboBox<Integer> comboBox) {
		comboBox.setBackground(ModifyJoinEnum.MODIFY_EMAIL_COMBOBOX_BACKGROUND.getColor());
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(ModifyJoinEnum.CHOICEBACKGROUND.getColor());
		comboBox.setFont(ModifyJoinEnum.LABELFONT_DEFAULT.getFont());
	 }
	public void setComboBoxStr(JComboBox<String> comboBox) {
		comboBox.setBackground(ModifyJoinEnum.MODIFY_EMAIL_COMBOBOX_BACKGROUND.getColor());
		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(ModifyJoinEnum.CHOICEBACKGROUND.getColor());
		comboBox.setFont(ModifyJoinEnum.LABELFONT_DEFAULT.getFont());
	}
	//=========================================================================================================
		public void setButtonPosition() throws IOException {
			this.modifyButton.setIconTextGap(this.modifyButton.getIconTextGap() - 15);
			
			// 수정 버튼 해상도 맞게 그리기
			this.modifyButton.setIcon(
				new ImageIcon(ImageIO.read(
					new File("resources/myData/correct.kor.png")).getScaledInstance(
						ModifyJoinEnum.MODIFY_MODIFY_BUTTON.getRectangle().width,
						ModifyJoinEnum.MODIFY_MODIFY_BUTTON.getRectangle().height,
		    		    Image.SCALE_AREA_AVERAGING))
			);
		    //취소 해상도 맞기 그리기
			this.cancelButton.setIcon(
	    		new ImageIcon(ImageIO.read(
	    			new File("resources/myData/reset.Kor.png")).getScaledInstance(
	    				ModifyJoinEnum.MODIFY_CANCEL_BUTTON.getRectangle().width,
	    				ModifyJoinEnum.MODIFY_CANCEL_BUTTON.getRectangle().height,
						Image.SCALE_AREA_AVERAGING))
		    );
			//탈퇴 해상도 맞기 그리기
			this.dropoutButton.setIcon(
	    		new ImageIcon(ImageIO.read(
	    			new File("resources/myData/quit.Kor.png")).getScaledInstance(
	    				ModifyJoinEnum.MODIFY_DROPOUT_BUTTON.getRectangle().width,
	    				ModifyJoinEnum.MODIFY_DROPOUT_BUTTON.getRectangle().height,
						Image.SCALE_AREA_AVERAGING))
		    );
			
			this.modifyButton.setBounds(ModifyJoinEnum.MODIFY_MODIFY_BUTTON.getRectangle());
			this.cancelButton.setBounds(ModifyJoinEnum.MODIFY_CANCEL_BUTTON.getRectangle());
			this.dropoutButton.setBounds(ModifyJoinEnum.MODIFY_DROPOUT_BUTTON.getRectangle());
			this.add(cancelButton);
			this.add(modifyButton);
			this.add(dropoutButton);
		
	}
	public static void main(String[] args) {
		try {
			new ModifyJoinFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
