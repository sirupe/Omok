package frames;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import enums.LoginFrameSizesEnum;
// 태성
@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private JButton joinButton;
	private JButton loginButton; 	
	private JButton searchIdButton;  
	private JButton searchPwButton; 
	private JButton idImage;
	private JButton passwordImage;
	private JTextField idField;
	private JPasswordField pwField;
	
	public LoginFrame() throws IOException {
		Font idpwFont 		= new Font("", Font.BOLD, 20);
		Image image = ImageIO.read(new File("Login/background.jpg"));
		Image reimage = image.getScaledInstance(1300, 900, Image.SCALE_SMOOTH);
		this.setContentPane(new JLabel(new ImageIcon(reimage)));	//배경화면 출력
		
		this.joinButton 	= new JButton(new ImageIcon("Login/signup.png"));
		this.loginButton 	= new JButton(new ImageIcon("Login/login.jpg"));
		this.searchIdButton = new JButton(new ImageIcon("Login/forgotID.png"));
		this.searchPwButton = new JButton(new ImageIcon("Login/forgotPW.png"));
		this.idImage 		= new JButton(new ImageIcon("Login/ID.png"));
		this.passwordImage 	= new JButton(new ImageIcon("Login/PW.png"));

		this.idField 		= new JTextField(10);
		this.pwField 		= new JPasswordField(10);
	
		this.setBounds(	//화면출력
				LoginFrameSizesEnum.LOGIN_FRAME_POSITION_X.getSize(),
				LoginFrameSizesEnum.LOGIN_FRMAE_POSITION_Y.getSize(),
                LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(), 
                LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize());
		
		
		this.idImage.setBounds(
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2.5) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2)), 
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 2.5) - (LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()) / 2), 
				LoginFrameSizesEnum.SIZE_LABEL_WIDTH.getSize(), 
				LoginFrameSizesEnum.SIZE_LABEL_HEIGHT.getSize());
		this.idImage.setBorderPainted(false);
		this.idImage.setContentAreaFilled(false);
		this.idImage.setFocusPainted(false);
		
		this.passwordImage.setBounds(
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2.5) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2)), 
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 2.15) - (LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()) / 2), 
				LoginFrameSizesEnum.SIZE_LABEL_WIDTH.getSize(), 
				LoginFrameSizesEnum.SIZE_LABEL_HEIGHT.getSize());
		this.passwordImage.setBorderPainted(false);
		this.passwordImage.setContentAreaFilled(false);
		this.passwordImage.setFocusPainted(false);
		
		this.loginButton.setBounds(
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2)), 
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 1.67) - (LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()) / 2), 
                LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize(), 
                LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize());
		this.loginButton.setBorderPainted(false);
		this.loginButton.setContentAreaFilled(false);
		this.loginButton.setFocusPainted(false);
		this.loginButton.setToolTipText("로그인");
		
		this.joinButton.setBounds(
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2.5) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2)), 
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 1.85) - (LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()) / 2), 
                LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize(), 
                LoginFrameSizesEnum.SIZE_JOIN_ICON_WIDTH.getSize());
		this.joinButton.setBorderPainted(false);
		this.joinButton.setContentAreaFilled(false);
		this.joinButton.setFocusPainted(false);
		this.joinButton.setToolTipText("회원가입");
		
		this.searchIdButton.setBounds(
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2)), 
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 1.85) - (LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()) / 2), 
                LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize(), 
                LoginFrameSizesEnum.SIZE_JOIN_ICON_WIDTH.getSize());
		this.searchIdButton.setBorderPainted(false);
		this.searchIdButton.setContentAreaFilled(false);
		this.searchIdButton.setFocusPainted(false);
		this.searchIdButton.setToolTipText("아이디 찾기");
		
		this.searchPwButton.setBounds(
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 1.67) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2)), 
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 1.85) - (LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()) / 2), 
                LoginFrameSizesEnum.SIZE_PW_ICON_HEIGHT.getSize(), 
                LoginFrameSizesEnum.SIZE_JOIN_ICON_WIDTH.getSize());
		this.searchPwButton.setBorderPainted(false);
		this.searchPwButton.setContentAreaFilled(false);
		this.searchPwButton.setFocusPainted(false);
		this.searchPwButton.setToolTipText("비밀번호 찾기");
		
		this.idField.setBounds(
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2.09) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2)), 
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 2.4) - (LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()) / 2), 
                LoginFrameSizesEnum.SIZE_TEXT_WIDTH.getSize(), 
                LoginFrameSizesEnum.SIZE_TEXT_HEIGHT.getSize());
		this.idField.setFont(idpwFont);
		
		this.pwField.setBounds(
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2.09) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2)), 
				(int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 2.1) - (LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()) / 2), 
                LoginFrameSizesEnum.SIZE_TEXT_WIDTH.getSize(), 
                LoginFrameSizesEnum.SIZE_TEXT_HEIGHT.getSize());
		this.pwField.setFont(idpwFont);
		
		this.add(this.idImage);
		this.add(this.passwordImage);
		this.add(this.loginButton);
		this.add(this.joinButton);
		this.add(this.searchIdButton);
		this.add(this.searchPwButton);
		this.add(this.idField);
		this.add(this.pwField);

		this.setLayout(null);
		this.setTitle("Login");
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		try {
			new LoginFrame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}