package frames;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.LoginFrameSizesEnum;

@SuppressWarnings("serial")
public class LoginAccess extends JFrame implements ActionListener{
	private JButton loginOkButton;
	private JLabel loginOkLabel;
	private Font loginOkFont;
	
	public LoginAccess() {
		
		this.setBounds(
				LoginFrameSizesEnum.LOGIN_ACCESS_FRAME_POSITION_X.getSize(), 
				LoginFrameSizesEnum.LOGIN_ACCESS_FRAME_POSITION_Y.getSize(), 
				LoginFrameSizesEnum.LOGIN_ACCESS_SIZE_WIDTH.getSize(), 
				LoginFrameSizesEnum.LOGIN_ACCESS_SIZE_HEIGHT.getSize());		
		
		this.loginOK();
	}
	
	//로그인 성공 창에 설정되는 버튼과 텍스트
	public void loginOK() {
		this.loginOkButton = new JButton(new ImageIcon("resources/login/okbutton2.png"));
		this.loginOkLabel = new JLabel("로그인 되었습니다!");
		this.loginOkFont = new Font("", Font.BOLD, 25);
		
		//확인버튼 설정
		this.loginOkButton.setBounds(
				LoginFrameSizesEnum.LOGIN_ACCESS_OK_BUTTON_POSITION_X.getSize(),
				LoginFrameSizesEnum.LOGIN_ACCESS_OK_BUTTON_POSITION_Y.getSize(),
				LoginFrameSizesEnum.LOGIN_ACCESS_OK_BUTTON_WIDTH.getSize(),
				LoginFrameSizesEnum.LOGIN_ACCESS_OK_BUTTON_HEIGHT.getSize());
		this.loginOkButton.setBorderPainted(false);
		this.loginOkButton.setContentAreaFilled(false);
		this.loginOkButton.setFocusPainted(false);
		this.loginOkButton.addActionListener(this);
		
		this.loginOkLabel.setBounds(
				LoginFrameSizesEnum.LOGIN_ACCESS_LABEL_POSITION_X.getSize(),
				LoginFrameSizesEnum.LOGIN_ACCESS_LABEL_POSITION_Y.getSize(),
				LoginFrameSizesEnum.LOGIN_ACCESS_LABEL_WIDTH.getSize(),
				LoginFrameSizesEnum.LOGIN_ACCESS_LABEL_HEIGTH.getSize());
		this.loginOkLabel.setFont(loginOkFont);
		
		this.add(loginOkButton);
		this.add(loginOkLabel);
		
		this.setLayout(null);
		this.setTitle("Login");
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginOkButton) {
			this.setVisible(false);
		}
	}
	
}
