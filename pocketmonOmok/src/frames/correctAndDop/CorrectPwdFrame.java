package frames.correctAndDop;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import enums.frames.ChargeEnum;
import enums.frames.CorrectEnum;
import enums.frames.SearchIdEnum;
import enums.frames.SearchPwdEnum;

public class CorrectPwdFrame extends JFrame{
	private Image backGround;
	private JLabel pwdLabel;
	private JLabel pwderror;
	private JTextField pwdTextField;
	private JButton confirm;
	private JButton reset;
	
		
	public CorrectPwdFrame() throws IOException {
		this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
                Image.SCALE_SMOOTH);

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		this.pwdLabel	 = new JLabel("PW 입력");
		this.pwderror  	 = new JLabel("비밀번호 오류 입니다.");
		this.pwdTextField = new JTextField();
		Font pwdLabelfont = new Font("a으라차차", Font.BOLD, 18);
		Font pwderrorfont = new Font("a으라차차", Font.BOLD, 15);
		
		this.setBounds(CorrectEnum.PWD_FRAME_SIZE_RECT.getRect());
		
		this.pwdLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.pwdLabel.setBounds(CorrectEnum.PWD_TEXT_LABEL_RECT.getRect());
		
		this.pwdTextField.setBounds(CorrectEnum.PWD_INPUT_RECT.getRect());
			
		this.pwderror.setFont(SearchPwdEnum.LABELFONT_DEFAULT.getFont());
		this.pwderror.setForeground(Color.red);
		this.pwderror.setBounds(CorrectEnum.PWD_ERROR_RECT.getRect());
		
		
		this.confirm = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/confirm.kor.png")), 
						0, 
						0, 
						CorrectEnum.PWD_CONFIRM_RECT.getRect().width,
						CorrectEnum.PWD_CONFIRM_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.confirm.setFocusPainted(false);
		this.confirm.setBorderPainted(false);
		this.confirm.setContentAreaFilled(false);
		this.confirm.setBounds(CorrectEnum.PWD_CONFIRM_RECT.getRect());

		this.reset = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/reset.kor.png")), 
						0, 
						0, 
						CorrectEnum.PWD_RESET_RECT.getRect().width,
						CorrectEnum.PWD_RESET_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.reset.setFocusPainted(false);
		this.reset.setBorderPainted(false);
		this.reset.setContentAreaFilled(false);
		this.reset.setBounds(CorrectEnum.PWD_RESET_RECT.getRect());
		
		this.add(pwdLabel);
		this.add(pwderror);
		this.add(pwdTextField);
		this.add(confirm);
		this.add(reset);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	public static void main(String[] args) throws IOException {
		new CorrectPwdFrame();

	}

}
