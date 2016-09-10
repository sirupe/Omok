package frames;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.SizesEnum;

public class LoginFrame extends JFrame {

	private JButton joinButton 		= new JButton(new ImageIcon("Login/signup.png"));
	private JButton loginButton 	= new JButton(new ImageIcon("Login/login.jpg"));
	private JButton searchIdButton  = new JButton(new ImageIcon("Login/forgotID.png"));
	private JButton searchPwButton  = new JButton(new ImageIcon("Login/forgotPW.png"));
	private JButton id			    = new JButton(new ImageIcon("Login/ID.png"));
	private JButton password 		= new JButton(new ImageIcon("Login/PW.png"));

	private Image backGround;
	private TextField idTextField, pwTextField;
	
	public LoginFrame() {
		Font idpwFont 		= new Font("", Font.BOLD, 20);
		Toolkit toolkit 	= Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		
		try{
			this.setContentPane(
					new JLabel(new ImageIcon(ImageIO.read(new File("Login/background.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		};	//배경화면 출력
		
		idTextField = new TextField(10);
		pwTextField = new TextField(10);
		pwTextField.setEchoChar('*');	//입력한 값을 *로 표시
	
		this.setBounds(	//화면출력
				(int)((dimension.getWidth() / 2) - (SizesEnum.SIZE_X.getSizeX() / 2)), 
				(int)((dimension.getHeight() / 2) - (SizesEnum.SIZE_Y.getSizeY()) / 2), 
                SizesEnum.SIZE_X.getSizeX(), 
                SizesEnum.SIZE_Y.getSizeY());
		
		this.add(id);
		this.add(password);
		this.add(loginButton);
		this.add(joinButton);
		this.add(searchIdButton);
		this.add(searchPwButton);
		this.add(idTextField);
		this.add(pwTextField);
		
		id.setBounds(
				(int)(( SizesEnum.SIZE_X.getSizeX() / 2.5) - (SizesEnum.SIZE_ICON_X.getSizeX() / 2)), 
				(int)((SizesEnum.SIZE_Y.getSizeY() / 2.5) - (SizesEnum.SIZE_ICON_Y.getSizeY()) / 2), 
				SizesEnum.SIZE_LABEL_X.getSizeX(), 
				SizesEnum.SIZE_LABEL_Y.getSizeY());
		id.setBorderPainted(false);
		id.setContentAreaFilled(false);
		id.setFocusPainted(false);
		
		password.setBounds(
				(int)(( SizesEnum.SIZE_X.getSizeX() / 2.5) - (SizesEnum.SIZE_ICON_X.getSizeX() / 2)), 
				(int)((SizesEnum.SIZE_Y.getSizeY() / 2.15) - (SizesEnum.SIZE_ICON_Y.getSizeY()) / 2), 
				SizesEnum.SIZE_LABEL_X.getSizeX(), 
				SizesEnum.SIZE_LABEL_Y.getSizeY());
		password.setBorderPainted(false);
		password.setContentAreaFilled(false);
		password.setFocusPainted(false);
		
		loginButton.setBounds(
				(int)(( SizesEnum.SIZE_X.getSizeX() / 2) - (SizesEnum.SIZE_ICON_X.getSizeX() / 2)), 
				(int)((SizesEnum.SIZE_Y.getSizeY() / 1.67) - (SizesEnum.SIZE_ICON_Y.getSizeY()) / 2), 
                SizesEnum.SIZE_ICON_X.getSizeX(), 
                SizesEnum.SIZE_ICON_Y.getSizeY());
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		
		joinButton.setBounds(
				(int)(( SizesEnum.SIZE_X.getSizeX() / 2.5) - (SizesEnum.SIZE_ICON_X.getSizeX() / 2)), 
				(int)((SizesEnum.SIZE_Y.getSizeY() / 1.85) - (SizesEnum.SIZE_ICON_Y.getSizeY()) / 2), 
                SizesEnum.SIZE_ICON_X.getSizeX(), 
                SizesEnum.SIZE_JOIN_ICON_Y.getSizeY());
		joinButton.setBorderPainted(false);
		joinButton.setContentAreaFilled(false);
		joinButton.setFocusPainted(false);
		
		searchIdButton.setBounds(
				(int)(( SizesEnum.SIZE_X.getSizeX() / 2) - (SizesEnum.SIZE_ICON_X.getSizeX() / 2)), 
				(int)((SizesEnum.SIZE_Y.getSizeY() / 1.85) - (SizesEnum.SIZE_ICON_Y.getSizeY()) / 2), 
                SizesEnum.SIZE_ICON_X.getSizeX(), 
                SizesEnum.SIZE_JOIN_ICON_Y.getSizeY());
		searchIdButton.setBorderPainted(false);
		searchIdButton.setContentAreaFilled(false);
		searchIdButton.setFocusPainted(false);
		
		searchPwButton.setBounds(
				(int)(( SizesEnum.SIZE_X.getSizeX() / 1.67) - (SizesEnum.SIZE_ICON_X.getSizeX() / 2)), 
				(int)((SizesEnum.SIZE_Y.getSizeY() / 1.85) - (SizesEnum.SIZE_ICON_Y.getSizeY()) / 2), 
                SizesEnum.SIZE_PW_ICON_X.getSizeX(), 
                SizesEnum.SIZE_JOIN_ICON_Y.getSizeY());
		searchPwButton.setBorderPainted(false);
		searchPwButton.setContentAreaFilled(false);
		searchPwButton.setFocusPainted(false);
		
		idTextField.setBounds(
				(int)(( SizesEnum.SIZE_X.getSizeX() / 2.09) - (SizesEnum.SIZE_ICON_X.getSizeX() / 2)), 
				(int)((SizesEnum.SIZE_Y.getSizeY() / 2.4) - (SizesEnum.SIZE_ICON_Y.getSizeY()) / 2), 
                SizesEnum.SIZE_TEXT_X.getSizeX(), 
                SizesEnum.SIZE_TEXT_Y.getSizeY());
		
	
		
		pwTextField.setBounds(
				(int)(( SizesEnum.SIZE_X.getSizeX() / 2.09) - (SizesEnum.SIZE_ICON_X.getSizeX() / 2)), 
				(int)((SizesEnum.SIZE_Y.getSizeY() / 2.1) - (SizesEnum.SIZE_ICON_Y.getSizeY()) / 2), 
                SizesEnum.SIZE_TEXT_X.getSizeX(), 
                SizesEnum.SIZE_TEXT_Y.getSizeY());
		
		idTextField.setFont(idpwFont);
		pwTextField.setFont(idpwFont);
		
		this.setLayout(null);
		this.setTitle("Login");
		this.setVisible(true);
		this.setResizable(false);
	}
}
