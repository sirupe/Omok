package actions.join;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.JLabel;

import actions.adapters.Adapters;
import datasDTO.UserPersonalInfoDTO;
import enums.ClientJoinSizesEnum;
import enums.UserPositionEnum;
import enums.UtilityEnums;
import frames.JoinFrame;
import frames.LoginPanel;
import utility.RegexCheck;
import utility.SendEmail;

public class JoinAction extends Adapters {
	private LoginPanel loginPanel;
	private JoinFrame joinFrame;
	
	private String id;
	private String pw;
	private String rePw;
	private String name;
	private int gender;
	private String birthYear;
	private String birthMonth;
	private String birthDate;
	private String emailID;
	private String emailAddr;
	private String telFrontNum;
	private String telMidNum;
	private String telLastNum;
	
	private StringBuffer totalEmail;
	
	// 누를 때마다 갱신되기 때문에 birth~ 들에게 초기값을 지정.
	public JoinAction(LoginPanel loginPanel, JoinFrame joinFrame){
		this.loginPanel = loginPanel;
		this.joinFrame = joinFrame;
		this.birthYear = "2016";
		this.birthMonth = "1";
		this.birthDate = "1";
	}
	
	// x버튼을 누르면 joinFrame 창이 꺼진 후 BasicFrame이 보이게 하기 위해 따로 설정.
	@Override
	public void windowClosing(WindowEvent e) {
		this.loginPanel.getBasicFrame().setVisible(true);
		this.joinFrame.setVisible(false);
		this.joinFrame.dispose();
	}
	
	// 실시간으로 타이핑 감시하여 에러메세지 송출.
	
	@Override
	public void keyReleased(KeyEvent e) {
		String source = e.getSource().toString();
		if(source.contains("idTextField")) {
			this.idSuitabilityCheck();
		
		} else if(source.contains("pwdField")) {
			this.pwSuitabilityCheck();
		
		} else if(source.contains("rePwdField")) {
			this.rePwSuitabilityCheck();
		
		} else if(source.contains("nameTextField")) {
			this.nameSuitabilityCheck();
		
		} else if(source.contains("emailIDTextField")) {
			this.emailID = this.joinFrame.getEmailIDTextField().getText();			
			this.emailIDSuitabilityCheck();
			
		} else if(source.contains("emailAddrTextField")) {
			this.emailAddrSuitabilityCheck();
			
		} else if(source.contains("telMiddleTextField")) {
			System.out.println("telMidTextField");
			this.telMidTextFieldLengthLimit();
		
		} else if(source.contains("telLastNumTextField")) {
			System.out.println("telLastNumTextField");
			this.telLastTextFieldLengthLimit();
		}

	}
	
	// 버튼 클릭 등 마우스이벤트에 사용
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("genderManRadio")) {
			this.gender = 1;
			this.joinFrame.getGenderErrorLabel().setText("");
		} else if(e.getSource().toString().contains("genderWomanRadio")) {
			this.gender = 2;
			this.joinFrame.getGenderErrorLabel().setText("");
		}
	}
	
	// 콤보박스의 아이템을 얻어오기 위한 메소드.
	// 한번 클릭하면 두 번 인입되는데, 변경된 값은 getstatchange가 1일 때, 변경 전 값은 2일 때 들어온다.
	// 항상 바뀐 값을 얻어오기 위하여 e.getStateChange() 메소드가 1을 반환할 때에만 작업을 실시한다.
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == 1) {
			if(e.getSource().toString().contains("yearChoice")) {
				this.birthYear = e.getItem().toString();
				this.joinFrame.getDateChoice().removeAllItems();
				this.joinFrame.calSetDate(Integer.parseInt(this.birthYear), Integer.parseInt(this.birthMonth));
			
			} else if(e.getSource().toString().contains("monthChoice")) {
				this.birthMonth = (String)e.getItem().toString();
				this.joinFrame.getDateChoice().removeAllItems();
				this.joinFrame.calSetDate(Integer.parseInt(this.birthYear), Integer.parseInt(this.birthMonth));

			} else if(e.getSource().toString().contains("dateChoice")) {
				this.birthDate = (String)e.getItem().toString();

			} else if(e.getSource().toString().contains("emailAddrChoice")) {
				this.emailAddrChiceResult(e.getItem());
				
			} else if(e.getSource().toString().contains("telFrontNumChoice")) {
				this.telFrontNum = (String)e.getItem().toString();

			}
		}
	}
	
	@Override // TODO
	public void actionPerformed(ActionEvent e) {
		String source = e.getSource().toString();
		if(source.contains("joinButton")) {
			this.joinAction();
		} else if(source.contains("confirmButton")) {
			this.confirmAction();
		}
	}
	
	//id 유효성 및 정합성 검사
	public void idSuitabilityCheck() {
		this.id = this.joinFrame.getIdTextField().getText();
		String checkMsg = null;
		Color color = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		
		if(id.length() < 3 || id.length() > 15) {
			checkMsg = "joinID길이";
		
		} else if (!RegexCheck.idRegexCheck(id)) {
			checkMsg = "joinID정합성";
		
		} else {
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
			personalDTO.setUserID(this.joinFrame.getIdTextField().getText());
			checkMsg = "join성공";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
			try {
				this.loginPanel.getBasicFrame().getClientOS().writeObject(personalDTO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		this.joinFrame.labelSetting(this.joinFrame.getIdErrorLabel(), color, checkMsg);
	}
	
	//pw 유효성 및 정합성 검사
	public void pwSuitabilityCheck() {
		char[] pwChar = this.joinFrame.getPwdTextField().getPassword();
		this.pw = new String(pwChar, 0, pwChar.length);
		String checkMsg = null;
		Color color 	= ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		
		if(!this.pw.equals(this.rePw)) {
			this.joinFrame.labelSetting(this.joinFrame.getRePwdErrorLabel(), color, "joinPW불일치");
		}
		
		if(pw.length() < 6 || pw.length() > 16) {
			checkMsg = "joinPW길이";
		
		} else if(pw.equals(id)) {
			checkMsg = "joinPW아이디";
		
		} else if(!RegexCheck.passwdRegexCheck(pw)) {
			checkMsg = "joinPW정합성";
			
		} else {
			checkMsg = "join성공";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
		}
		this.joinFrame.labelSetting(this.joinFrame.getPwdErrorLabel(), color, checkMsg);
		
	}
	
	//pw재입력 부분 유효성 및 정합성 검사
	public void rePwSuitabilityCheck() {
		char[] rePwChar = this.joinFrame.getRePwdField().getPassword();
		this.rePw = new String(rePwChar, 0, rePwChar.length);
		String checkMsg = null;
		Color color 	= null;
		
		if(this.rePw.equals(this.pw)) {
			checkMsg = "join성공";
			color	 = ClientJoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
		} else {
			checkMsg = "joinPW불일치";
			color	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		}
		this.joinFrame.labelSetting(this.joinFrame.getRePwdErrorLabel(), color, checkMsg);
	}
	
	//이름 유효성 및 정합성 검사
	public void nameSuitabilityCheck() {
		this.name = this.joinFrame.getNameTextField().getText();
		String checkMsg = null;
		Color color 	= null;
		
		if(this.name.length() < 2) {
			checkMsg = "joinName길이";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else if(!RegexCheck.nameRegecCheck(this.name)) {
			checkMsg = "joinName정합성";
			color	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else {
			checkMsg = "join성공";
			color	 = ClientJoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
		}
		this.joinFrame.labelSetting(this.joinFrame.getNameErrorLabel(), color, checkMsg);
	}
	
	//email 유효성 및 정합성 검사
	public void emailAddrSuitabilityCheck() {
		this.emailAddr = this.joinFrame.getEmailAddrTextField().getText();
		String checkMsg = null;
		Color color 	= null;
		
		if(!RegexCheck.emailDomainRegexCheck(this.emailAddr)) {
			checkMsg = "joinMail정합성";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else if(this.joinFrame.getEmailIDTextField().getText().length() == 0) {
			checkMsg = "joinMail아이디미입력";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else {
			checkMsg = "join성공";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
			this.totalEmail = new StringBuffer();
			this.totalEmail.append(this.emailID);
			this.totalEmail.append("@");
			this.totalEmail.append(this.emailAddr);
			
		}
		this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), color, checkMsg);
	}
	
	//email id 유효성 및 정합성 검사
	public void emailIDSuitabilityCheck() {
		if(this.joinFrame.getEmailAddrTextField().getText().length() == 0) {
			this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor(), "joinMail도메인미입력");
		}
	}

	//email 선택 콤보박스 액션
	public void emailAddrChiceResult(Object object) {
		if(object.toString().equals("직접입력")) {
			this.joinFrame.getEmailAddrTextField().setEditable(true);
		} else {
			this.joinFrame.getEmailAddrTextField().setEditable(false);
			this.emailAddr = object.toString();
			this.joinFrame.getEmailAddrTextField().setText(this.emailAddr);
			this.emailAddrSuitabilityCheck();
		}
	}
	
	//tel 중간번호 체크
	public void telMidTextFieldLengthLimit() {
		String telMid = this.joinFrame.getTelMidTextField().getText();
		if(telMid.length() == 4) {
			this.telMidNum = telMid;
		} else if(telMid.length() > 4) {
			this.joinFrame.getTelMidTextField().setText(this.telMidNum);
		}
	}
	
	//tel 끝번호 체크
	public void telLastTextFieldLengthLimit() {
		String telLast = this.joinFrame.getTelLastNumTextField().getText();
		if(telLast.length() == 4) {
			this.telLastNum = telLast;
		} else if(telLast.length() > 4) {
			this.joinFrame.getTelLastNumTextField().setText(this.telLastNum);
		}
	}
	
	public void joinAction() {
		System.out.println(this.toString());
		System.out.println(this.id == null);
		Color color = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		String msg = "join필수";
		int errCount = 0;
		if(this.id == null) {
			this.joinFrame.labelSetting(this.joinFrame.getIdErrorLabel(), color, msg);
			errCount++;
		}
		
		if(this.pw == null) {
			this.joinFrame.labelSetting(this.joinFrame.getPwdErrorLabel(), color, msg);
			errCount++;
		}
		
		if(this.rePw == null) {
			this.joinFrame.labelSetting(this.joinFrame.getRePwdErrorLabel(), color, msg);
			errCount++;
		}
		
		if(this.name == null) {
			this.joinFrame.labelSetting(this.joinFrame.getNameErrorLabel(), color, msg);
			errCount++;
		}
		
		if(this.gender == 0) {
			this.joinFrame.labelSetting(this.joinFrame.getGenderErrorLabel(), color, msg);
			errCount++;
		}
		
		if(this.emailID == null || this.emailAddr == null) {
			this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), color, msg);
			errCount++;
		}
		
		if((this.telMidNum != null && this.telLastNum == null) || (this.telMidNum == null && this.telLastNum != null)) {
			this.joinFrame.labelSetting(this.joinFrame.getTelErrorLabel(), color, "joinTel정합성");
		}
		
		
		
		if(errCount == 0) {
			StringBuffer totalBirth = new StringBuffer();
			totalBirth.append(this.birthYear);
			totalBirth.append("년 ");
			totalBirth.append(this.birthMonth);
			totalBirth.append("월 ");
			totalBirth.append(this.birthDate);
			totalBirth.append("일");
		
			StringBuffer totalTel = new StringBuffer();
			totalTel.append(this.telFrontNum);
			totalTel.append(this.telFrontNum == null ? null : "-");
			totalTel.append(this.telMidNum);
			totalTel.append(this.telMidNum == null ? null : "-");
			totalTel.append(this.telLastNum);
			
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
			personalDTO.setUserBirth(totalBirth.toString());
			personalDTO.setUserEmail(totalEmail.toString());
			personalDTO.setUserGender(this.gender);
			personalDTO.setUserID(this.id);
			personalDTO.setUserName(this.name);
			personalDTO.setUserPasswd(this.pw);
			personalDTO.setUserPhoneNumber(this.telLastNum);
		}
	}

	public void confirmAction() {
		String confirmNumber = String.valueOf(new Random().nextInt(900000) + 100000);
		new SendEmail(confirmNumber, this.totalEmail.toString());
		new Thread() {
			@Override
			public void run() {
			}
		}.start();
	}
	
	@Override
	public String toString() {
		return "JoinAction [id=" + id + ", pw=" + pw + ", rePw=" + rePw + ", name=" + name + ", gender=" + gender
				+ ", birthYear=" + birthYear + ", birthMonth=" + birthMonth + ", birthDate=" + birthDate + ", emailID="
				+ emailID + ", emailAddr=" + emailAddr + ", telFrontNum=" + telFrontNum + ", telMidNum=" + telMidNum
				+ ", telLastNum=" + telLastNum + "]";
	}
	
	
}
