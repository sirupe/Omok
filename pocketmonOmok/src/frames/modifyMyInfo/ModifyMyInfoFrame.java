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

@SuppressWarnings("serial")
public class ModifyMyInfoFrame extends JFrame {

	private JLabel userIdLabel;
	
	private JLabel pwdLabel;
	private JLabel rePwdLabel;
	private JLabel pwdLabelError; //패스워드 일치 에러
	
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
	private JComboBox<String> emailAddrChoice;

	private JComboBox<String> telFrontNumChoice; //전화번호 02,010..
	private JTextField telMiddleTextField;
	private JTextField telLastNumTextField;	
	
	private JButton modifyButton; //회원가입 버튼
	private JButton cancelButton; //취소버튼
	private JButton dropoutButton; //인증 버큰
	
	private Image background;
	private ModifyMyInfoAction modifyAction;
	private BasicFrame basicFrame;
	private CorrectPwdFrame correctPwdFrame;
	public ModifyMyInfoFrame(BasicFrame basicFrame) throws IOException {
		this.modifyAction = new ModifyMyInfoAction(this);
		this.basicFrame = basicFrame;
		//모든 레이블 
		this.userIdLabel 	 = new JLabel("아이디"); 
		this.pwdLabel         = new JLabel("비밀번호");
		this.rePwdLabel      = new JLabel("비밀번호재입력");
		
		this.pwdLabelError   = new JLabel("ERROR MESSAGE"); //에러 입히기
		this.pwdLabelError.setBackground(Color.pink);
		
		
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
		this.emailErrLabel	  = new JLabel("asdflaksdjgal;kdsjlasd");
		
		// 모든 텍스트 필드
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
		
		//텍스트필드
		Font textFont = ModifyEnum.LABELFONT_DEFAULT.getFont();
		this.idTextField.setFont(textFont);
		this.pwdField.setFont(textFont);
		this.rePwdField.setFont(textFont);
		this.nameTextField.setFont(textFont);
		
		this.emailIDTextField.setFont(textFont);
		this.emailAddrTextField.setFont(textFont);
		
		this.emailIDTextField.setEditable(false);
		this.emailAddrTextField.setEditable(false);
		//배경화면
		background = ImageIO.read(
				//배경이미지 join.png로 바꾸세욤
			  new File("resources/signUp/join.png")).getScaledInstance(

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
		this.setTitle("회원정보수정");
		this.setResizable(false);
	}
	
	
	//라벨 위치 -- > 순서대로
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
	
	//텍스트필드
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
		String[] emailAddr = ModifyEnum.MODIFY_EMAIL_COMBO_ADDRESS.getStrArr();
		for(int i = 0, size = emailAddr.length; i < size; i++) {
			this.emailAddrChoice.addItem(emailAddr[i]);
		}
		String[] telFrontNum = ModifyEnum.MODIFY_TEL_FRONT_NUM_COMBO.getStrArr();
		for(int i = 0, size = telFrontNum.length; i < size; i++) {
			this.telFrontNumChoice.addItem(telFrontNum[i]);
		}
		
//		//이메일주소
//		this.emailAddrChoice.setBounds(ModifyEnum.MODIFY_EMAILADDR_COMBO.getRectangle());
//		this.setComboBoxStr(this.emailAddrChoice);
//		this.add(emailAddrChoice);
		
		//전화번호 앞자리
		this.telFrontNumChoice.setBounds(ModifyEnum.MODIFY_TELFRONTNUM_COMBO.getRectangle());
		this.setComboBoxStr(this.telFrontNumChoice);
		this.add(telFrontNumChoice);

		//년
		this.yearChoice.setBounds(ModifyEnum.MODIFY_YEAR_COMBOBOX.getRectangle());
		this.setComboBoxInt(this.yearChoice);
		this.add(yearChoice);
		//월
		this.monthChoice.setBounds(ModifyEnum.MODIFY_MONTH_COMBOBOX.getRectangle());
		this.setComboBoxInt(this.monthChoice);
		this.add(monthChoice);
		//일
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
		
		// 수정 버튼 해상도 맞게 그리기
		this.modifyButton.setIcon(GetResources.getImageIcon("resources/myData/correct.kor.png", 
				ModifyEnum.MODIFY_MODIFY_BUTTON.getRectangle().width,
				ModifyEnum.MODIFY_MODIFY_BUTTON.getRectangle().height));
		
	    //취소 해상도 맞기 그리기
		this.cancelButton.setIcon(GetResources.getImageIcon("resources/myData/reset.Kor.png", 
				ModifyEnum.MODIFY_CANCEL_BUTTON.getRectangle().width,
				ModifyEnum.MODIFY_CANCEL_BUTTON.getRectangle().height));
	    
		//탈퇴 해상도 맞기 그리기
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
	
	// 회원정보 수정 버튼을 누르면 실행되며 클라이언트의 정보를 세팅한다.
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
		
		if(!telFrontNum.equals("선택") || telMidNum.length() != 0 || telLastNum.length() != 0) {
			if(telFrontNum.equals("선택")) {
				errMsg = "전화번호 형식이 맞지 않습니다.";
				errCheck++;
			}
			if(telMidNum.length() != 4) {
				errMsg = "전화번호 형식이 맞지 않습니다.";
				errCheck++;
			}
			if(telLastNum.length() != 4) {
				errMsg = "전화번호 형식이 맞지 않습니다.";
				errCheck++;
			}
		}
		
		if(this.emailIDTextField.getText().length() == 0 || !RegexCheck.emailDomainRegexCheck(this.emailAddrTextField.getText())) {
			this.pwdLabelError.setForeground(ModifyEnum.ERROR_MESSAGE_COLOR.getColor());
			errMsg = "이메일이 정확하지 않습니다.";
			errCheck++;
		}

		if(!pwdStr.equals(rePwdStr)) {
			this.pwdLabelError.setForeground(ModifyEnum.ERROR_MESSAGE_COLOR.getColor());
			errMsg = "두 패스워드를 정확히 입력해주세요.";
			errCheck++;
		}
		
		if(pwdStr.length() == 0 || 
			rePwdStr.length() == 0 || 
			this.nameTextField.getText().length() == 0 || 
			this.emailAddrTextField.getText().length() == 0 || 
			this.emailAddrTextField.getText().length() == 0) {
			errMsg = "모든 정보를 입력해주세요";
			errCheck++;
		}
		
		if(errCheck == 0) {
			
			this.sendServer(telFrontNum.equals("선택") ? null : telFrontNum + "-" + telMidNum + "-" + telLastNum);
		} else {
			JOptionPane.showMessageDialog(this, errMsg);			
		}
	}
	
	// 서버로 고객의 수정데이터를 보낸다.
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
		JOptionPane.showMessageDialog(this, "처리 중 오류가 발생하였습니다. 다시 시도해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
	}
	
	public void passwdFail() {
		JOptionPane.showMessageDialog(this, "패스워드가 일치하지 않습니다.", "패스워드 오류", JOptionPane.ERROR_MESSAGE);
	}
	
	public void updateSuccess() {
		JOptionPane.showMessageDialog(this, "처리가 완료되었습니다.");
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
	
	public static void main(String[] args) throws IOException {
		new ModifyMyInfoFrame(null);
	}
}
