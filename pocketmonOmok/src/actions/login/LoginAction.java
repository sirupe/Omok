package actions.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import frames.BasicFrame;
import frames.LoginPanel;

public class LoginAction implements ActionListener{
	private LoginPanel loginPanel;
	
	public LoginAction(LoginPanel loginPanel){
		this.loginPanel = loginPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		//getPassword() 를 하면 String을 char[]로 가져오기 때문에 char[]자료형인 passwd 변수에 넣었둔다.
		char[] passwd = this.loginPanel.getPwField().getPassword();

		if(this.loginPanel.getLoginButton() == e.getSource()) {
			
			if(this.loginPanel.getIdField().getText().equals(this.loginPanel.getDataId())
						//passwd변수에 넣어둔 getPassword값을 String형태로 변환하여 비교한다.
						&& new String(passwd, 0, passwd.length).equals(this.loginPanel.getDataPw())){
			
			this.loginPanel.getBasicFrame().inWaitingRoom();
				
			//로그인 성공 뒤에 아이디 텍스트필드 초기화
			this.loginPanel.getIdField().setText("");
			//로그인 성공뒤에 비밀번호 텍스트필드 초기화
			this.loginPanel.getPwField().setText("");       
			
			//this.loginFailText.setText("");
			} if(!(this.loginPanel.getIdField().getText().equals(this.loginPanel.getDataId())) 
						&& this.loginPanel.getPwField().getPassword().equals(this.loginPanel.getDataPw())) {
				this.loginPanel.loginFail();
				
			} else if(!(this.loginPanel.getPwField().getPassword().equals(this.loginPanel.getDataPw())) 
						&& this.loginPanel.getIdField().getText().equals(this.loginPanel.getDataId())) {
				this.loginPanel.loginFail();
				
			} else {
				this.loginPanel.loginFail();
			}
		}
	}
}
