package actions.join;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JTextField;

import actions.adapters.Adapters;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import frames.BasicFrame;
import frames.joinFrames.JoinFrame;
import utility.RegexCheck;

public class JoinClientAction extends Adapters {
	private BasicFrame basicFrame;
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
	private String certificationNumber;
	
	private StringBuffer totalEmail;
	private boolean emailConfirmTime;

	private Thread timeThread;
	
	// 누를 때마다 갱신되기 때문에 birth~ 들에게 초기값을 지정.
	public JoinClientAction(BasicFrame basicFrame, JoinFrame joinFrame){
		this.basicFrame = basicFrame;
		this.joinFrame = joinFrame;
		this.birthYear = "2016";
		this.birthMonth = "1";
		this.birthDate = "1";
		this.emailConfirmTime = false;
		this.certificationNumber = "0";
	}
	
	// x버튼을 누르면 joinFrame 창이 꺼진 후 BasicFrame이 보이게 하기 위해 따로 설정.
	@Override
	public void windowClosing(WindowEvent e) {
		this.basicFrame.setVisible(true);
		this.joinFrame.setVisible(false);
		this.joinFrame.dispose();	
	}
	
	// 실시간으로 타이핑 감시하여 에러메세지 송출.
	@Override
	public void keyReleased(KeyEvent e) {
		JTextField textField = (JTextField)e.getSource();
		String source = textField.getName();
		
		if(source.equals("idTextField")) {
			this.idSuitabilityCheck();
		
		} else if(source.equals("pwdField")) {
			this.pwSuitabilityCheck();
		
		} else if(source.equals("rePwdField")) {
			this.rePwSuitabilityCheck();
		
		} else if(source.equals("nameTextField")) {
			this.nameSuitabilityCheck();
		
		} else if(source.equals("emailIDTextField")) {
			this.emailID = this.joinFrame.getEmailIDTextField().getText();			
			this.emailIDSuitabilityCheck();
			
		} else if(source.equals("emailAddrTextField")) {
			this.emailAddrSuitabilityCheck();
			
		} else if(source.equals("telMiddleTextField")) {
			this.telMidTextFieldLengthLimit();
		
		} else if(source.equals("telLastNumTextField")) {
			this.telLastTextFieldLengthLimit();
		
		} else if(source.equals("emailConfTextField")) {
			this.emailConfirm();
		}

	}
	
	// 버튼 클릭 등 마우스이벤트에 사용
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("genderManRadio")) {
			this.gender = 1;
			this.joinFrame.getGenderErrorLabel().setVisible(false);
		
		} else if(e.getSource().toString().contains("genderWomanRadio")) {
			this.gender = 2;
			this.joinFrame.getGenderErrorLabel().setVisible(false);
		}
	}

	
	// 콤보박스의 아이템을 얻어오기 위한 메소드.
	// 한번 클릭하면 두 번 인입되는데, 변경된 값은 getstatchange가 1일 때, 변경 전 값은 2일 때 들어온다.
	// 항상 바뀐 값을 얻어오기 위하여 e.getStateChange() 메소드가 1을 반환할 때에만 작업을 실시한다.
	@Override
	public void itemStateChanged(ItemEvent e) {
		String source = e.getSource().toString();
		
		if(e.getStateChange() == 1) {
			if(source.contains("yearChoice")) {
				this.birthYear = e.getItem().toString();
				this.joinFrame.getDateChoice().removeAllItems();
				this.joinFrame.calSetDate(Integer.parseInt(this.birthYear), Integer.parseInt(this.birthMonth));
			
			} else if(source.contains("monthChoice")) {
				this.birthMonth = (String)e.getItem().toString();
				this.joinFrame.getDateChoice().removeAllItems();
				this.joinFrame.calSetDate(Integer.parseInt(this.birthYear), Integer.parseInt(this.birthMonth));

			} else if(source.contains("dateChoice")) {
				this.birthDate = (String)e.getItem().toString();

			} else if(source.contains("emailAddrChoice")) {
				this.emailAddrChoiceResult(e.getItem());
				
			} else if(source.contains("telFrontNumChoice")) {
				this.telFrontNum = (String)e.getItem().toString();
				this.joinFrame.labelSetting(this.joinFrame.getTelErrorLabel(), JoinSizesEnum.LABELCOLOR_DEFAULT.getColor(), "join선택");
			} else if(source.contains("genderManRadio")) {
				this.gender = 1;
				this.joinFrame.getGenderErrorLabel().setVisible(false);
			
			} else if(source.contains("genderWomanRadio")) {
				this.gender = 2;
				this.joinFrame.getGenderErrorLabel().setVisible(false);
			}
		}
	}
	
	// 회원가입 버튼, 메일 인증 버튼, 취소 버튼 입력받기
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getSource().toString();
	
		if(source.contains("joinButton")) {
			this.joinAction();
		} else if(source.contains("confirmButton")) {
			this.confirmAction();
		} else if(source.contains("cancelButton")) {
			this.basicFrame.setVisible(true);
			this.joinFrame.setVisible(false);
			this.joinFrame.dispose();
		}
 	}
	
	//id 유효성 및 정합성 검사
	public void idSuitabilityCheck() {
		this.id = this.joinFrame.getIdTextField().getText();
		String checkMsg = null;
		Color color = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		
		if(id.length() < 3 || id.length() > 15) {
			checkMsg = "joinID길이";
		
		} else if (!RegexCheck.idRegexCheck(id)) {
			checkMsg = "joinID정합성";
		
		} else {
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
			personalDTO.setUserAction(UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK);
			personalDTO.setUserID(this.joinFrame.getIdTextField().getText());
			
			try {
				this.basicFrame.getClientOS().writeObject(personalDTO);
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
		Color color 	= JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		
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
			color 	 = JoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
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
			color	 = JoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
		} else {
			checkMsg = "joinPW불일치";
			color	 = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
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
			color 	 = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else if(!RegexCheck.nameRegecCheck(this.name)) {
			checkMsg = "joinName정합성";
			color	 = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else {
			checkMsg = "join성공";
			color	 = JoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
		}
		this.joinFrame.labelSetting(this.joinFrame.getNameErrorLabel(), color, checkMsg);
	}
	
	//email 유효성 및 정합성 검사
	public boolean emailAddrSuitabilityCheck() {
		this.emailAddr  = this.joinFrame.getEmailAddrTextField().getText();
		String checkMsg = null;
		Color color 	= null;
		boolean result  = false;
		
		if(!RegexCheck.emailDomainRegexCheck(this.emailAddr)) {
			checkMsg = "joinMail정합성";
			color 	 = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else if(this.joinFrame.getEmailIDTextField().getText().length() == 0) {
			checkMsg = "joinMail아이디미입력";
			color 	 = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else {
			checkMsg = "join성공";
			color 	 = JoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
			this.totalEmail = new StringBuffer();
			this.totalEmail.append(this.emailID);
			this.totalEmail.append("@");
			this.totalEmail.append(this.emailAddr);
			result = true;
			
		}
		this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), color, checkMsg);
		
		return result;
	}
	
	//email id 유효성 및 정합성 검사
	public boolean emailIDSuitabilityCheck() {
		boolean result = true;
		if(this.joinFrame.getEmailAddrTextField().getText().length() == 0) {
			this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), JoinSizesEnum.LABELCOLOR_ERROR.getColor(), "joinMail도메인미입력");
			result = false;
		}
		
		return result;
	}

	//email 선택 콤보박스 액션
	public void emailAddrChoiceResult(Object object) {
		if(object.toString().equals("직접입력")) {
			this.joinFrame.getEmailAddrTextField().setEditable(true);
		} else {
			this.joinFrame.getEmailAddrTextField().setEditable(false);
			this.emailAddr = object.toString();
			this.joinFrame.getEmailAddrTextField().setText(this.emailAddr);
			this.emailAddrSuitabilityCheck();
		}
	}

	//인증 버튼 눌렀을 시 
	public void confirmAction() {
		if(this.emailAddrSuitabilityCheck() && this.emailIDSuitabilityCheck()) {
			StringBuffer email = new StringBuffer();
			email.append(this.joinFrame.getEmailIDTextField().getText());
			email.append("@");
			email.append(this.joinFrame.getEmailAddrTextField().getText());
			
			UserPersonalInfoDTO userPersonalInfoDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
			userPersonalInfoDTO.setUserAction(UserActionEnum.USER_JOIN_CERTIFICATION_CREATE);
			userPersonalInfoDTO.setUserEmail(email.toString());
			
			try {
				this.basicFrame.getClientOS().writeObject(userPersonalInfoDTO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//시간라벨보여주기
			this.joinFrame.getEmailTimeLabel().setVisible(true);
			//메일발송 메세지 보여주기
			this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), Color.blue, "joinMail발송");
			//시간 세어주는 쓰레드 생성
			this.timeThread = new Thread() {
				@Override
				public void run() {
					StringBuffer time = new StringBuffer();
					Color color = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
					joinFrame.getConfirmButton().removeActionListener(joinFrame.getJoinAction());
					
					for(int i = 3; i >= 0; --i) {
						for(int j = (i >= 3) ? 0 : 59; j >= 0; j-- ) {
							time.delete(0, time.length());
							time.append(i);
							time.append(" : ");
							time.append(j < 10 ? "0" + j : j);
							
							joinFrame.getEmailTimeLabel().setText(time.toString());
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								break;
							}
						}
						if(emailConfirmTime) {
							return;
						}
					}
					
					joinFrame.labelSetting(joinFrame.getEmailErrorLabel(), color, "joinMail시간초과");
					joinFrame.getEmailTimeLabel().setVisible(false);
					joinFrame.getConfirmButton().addActionListener(joinFrame.getJoinAction());
					
					if(!certificationNumber.equals(joinFrame.getEmailConfTextField().getText())) {
						certificationNumber = "0";
					};
				}
			};
			
			this.timeThread.start();
		}
	}
	
	//이메일 인증번호 입력 텍스트필드 
	public void emailConfirm() {
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
		personalDTO.setUserAction(UserActionEnum.USER_JOIN_CERTIFICATION_CHECK);
		personalDTO.setCertificationNumber(this.joinFrame.getEmailConfTextField().getText());
		this.basicFrame.sendDTO(personalDTO);
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
	
	//회원가입 버튼 눌렀을 시
	public void joinAction() {
		Color color = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		String msg  = "join필수";
		
		int errCount = 0;
		
		//아이디가 공란일 때
		if(this.id == null) {
			this.joinFrame.labelSetting(this.joinFrame.getIdErrorLabel(), color, msg);
			errCount++;
		}
		
		//패스워드가 공란일 때
		if(this.pw == null) {
			this.joinFrame.labelSetting(this.joinFrame.getPwdErrorLabel(), color, msg);
			errCount++;
		}
		
		//rePw가 공란일 때
		if(this.rePw == null) {
			this.joinFrame.labelSetting(this.joinFrame.getRePwdErrorLabel(), color, msg);
			errCount++;
		}
		
		//이름이 공란일 때
		if(this.name == null) {
			this.joinFrame.labelSetting(this.joinFrame.getNameErrorLabel(), color, msg);
			errCount++;
		}
		
		//성별 입력이 안되었을 때 
		if(this.gender == 0) {
			this.joinFrame.labelSetting(this.joinFrame.getGenderErrorLabel(), color, msg);
			errCount++;
		}
		
		//이메일 아이디나 주소가 입력 안되었을 때
		if(this.emailID == null || this.emailAddr == null) {
			this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), color, msg);
			errCount++;
		}
		this.telFrontNum = (String) this.joinFrame.getTelFrontNumChoice().getSelectedItem();
		this.telMidNum 	 = this.joinFrame.getTelMidTextField().getText();
		this.telLastNum  = this.joinFrame.getTelLastNumTextField().getText();
		
		//전화번호가 앞번호, 뒷번호 중 하나만 입력되었을 때
		if(this.isTelNullCheck()) {
			
			if(this.telFrontNum.equals("선택")) {
				this.joinFrame.labelSetting(this.joinFrame.getTelErrorLabel(), color, "joinTel정합성");
				errCount++;
			}
			if(this.telMidNum.length() != 4) {
				this.joinFrame.labelSetting(this.joinFrame.getTelErrorLabel(), color, "joinTel정합성");
				errCount++;
			}
			if(this.telLastNum.length() != 4) {
				this.joinFrame.labelSetting(this.joinFrame.getTelErrorLabel(), color, "joinTel정합성");
				errCount++;
			}
		}
		
		//인증번호를 받지 않았을 때
		if(this.joinFrame.getEmailConfTextField().isEditable()) {
			this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), color, "joinMail인증필");
			errCount++;
		}
		
		if(errCount == 0) {
			StringBuffer totalBirth = new StringBuffer();
			totalBirth.append(this.birthYear);
			totalBirth.append(".");
			totalBirth.append(this.birthMonth);
			totalBirth.append(".");
			totalBirth.append(this.birthDate);
			totalBirth.append(".");
		
			StringBuffer totalTel = new StringBuffer();
			totalTel.append(this.telFrontNum);
			totalTel.append(this.telFrontNum == null ? null : "-");
			totalTel.append(this.telMidNum);
			totalTel.append(this.telMidNum == null ? null : "-");
			totalTel.append(this.telLastNum);
			
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
			personalDTO.setUserAction(UserActionEnum.USER_JOIN_JOINACTION);
			personalDTO.setUserBirth(totalBirth.toString());
			personalDTO.setUserEmail(totalEmail.toString());
			personalDTO.setUserGender(this.gender);
			personalDTO.setUserID(this.id);
			personalDTO.setUserName(this.name);
			personalDTO.setUserPasswd(this.pw);
			personalDTO.setUserPhoneNumber(totalTel.toString());
			
			try {
				this.basicFrame.getClientOS().writeObject(personalDTO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
	
	public void setEmailConfirmTime(boolean emailConfirmTime) {
		this.emailConfirmTime = emailConfirmTime;
	}
	
	public Thread getTimeThread() {
		return timeThread;
	}
	
	public boolean isTelNullCheck() {
		return !this.telFrontNum.equals("선택") || 
				this.telMidNum.length() != 0 || 
				this.telLastNum.length() != 0;
	}
	
}
