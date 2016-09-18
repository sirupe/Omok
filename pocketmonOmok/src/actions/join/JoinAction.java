package actions.join;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JLabel;

import actions.adapters.Adapters;
import datasDTO.UserPersonalInfoDTO;
import enums.ClientJoinSizesEnum;
import enums.UserPositionEnum;
import frames.JoinFrame;
import frames.LoginPanel;
import utility.RegexCheck;

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
	
	// 누를 때마다 갱신되기 때문에 birth~ 들에게 초기값을 지정.
	public JoinAction(LoginPanel loginPanel, JoinFrame joinFrame){
		this.loginPanel = loginPanel;
		this.joinFrame = joinFrame;
		this.birthYear = "2016";
		this.birthMonth = "1";
		this.birthDate = "1";
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		this.loginPanel.getBasicFrame().setVisible(true);
		this.joinFrame.setVisible(false);
		this.joinFrame.dispose();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().toString().contains("idTextField")) {
			this.idSuitabilityCheck();
		} else if(e.getSource().toString().contains("pwdField")) {
			this.pwSuitabilityCheck();
		} else if(e.getSource().toString().contains("rePwdField")) {
			this.rePwSuitabilityCheck();
		} else if(e.getSource().toString().contains("nameTextField")) {
			this.nameSuitabilityCheck();
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("genderManRadio")) {
			this.gender = 1;
		} else if(e.getSource().toString().contains("genderWomanRadio")) {
			this.gender = 2;
		}
	}
	
	public void idSuitabilityCheck() {
		this.id = this.joinFrame.getIdTextField().getText();
		String checkMsg = null;
		Color color = null;
		
		if(id.length() < 3 || id.length() > 15) {
			checkMsg = "joinID길이";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		
		} else if (!RegexCheck.idRegexCheck(id)) {
			checkMsg = "joinID정합성";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		
		} else {
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
			personalDTO.setUserID(this.joinFrame.getIdTextField().getText());
			
			try {
				this.loginPanel.getBasicFrame().getClientOS().writeObject(personalDTO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		this.joinFrame.labelSetting(this.joinFrame.getIdErrorLabel(), color, checkMsg);
	}
	
	public void pwSuitabilityCheck() {
		char[] pwChar = this.joinFrame.getPwdTextField().getPassword();
		this.pw = new String(pwChar, 0, pwChar.length);
		String checkMsg = null;
		Color color 	= null;
		
		if(pw.length() < 6 || pw.length() > 16) {
			checkMsg = "joinPW길이";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		
		} else if(pw.equals(id)) {
			checkMsg = "joinPW아이디";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		
		} else if(!RegexCheck.passwdRegexCheck(pw)) {
			checkMsg = "joinPW정합성";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_ERROR.getColor();
		} else {
			checkMsg = "join성공";
			color 	 = ClientJoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
		}
		this.joinFrame.labelSetting(this.joinFrame.getPwdErrorLabel(), color, checkMsg);
	}
	
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
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == 1) {
			if(e.getSource().toString().contains("yearChoice")) {
				this.birthYear = e.getItem().toString();
			} else if(e.getSource().toString().contains("monthChoice")) {
				this.birthMonth = (String)e.getItem().toString();
			} else if(e.getSource().toString().contains("dateChoice")) {
				this.birthDate = (String)e.getItem().toString();
			}
			this.joinFrame.calSetDate(Integer.parseInt(this.birthYear), Integer.parseInt(this.birthMonth));
		}
	}
}
