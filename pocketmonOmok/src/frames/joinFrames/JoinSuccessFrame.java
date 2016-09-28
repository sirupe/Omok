package frames.joinFrames;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.CorrectEnum;

public class JoinSuccessFrame extends JFrame {
	private Image backGround;
	private JLabel joinSuccessLabel;
	private JButton confirm;
	private JoinFrame joinFrame;
	
	
	public JoinSuccessFrame(JoinFrame joinFrame, String message) {
		this.joinFrame = joinFrame;
		try {
			this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
					CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
					CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
			        Image.SCALE_SMOOTH);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		Font font = new Font("a으라차차", Font.BOLD, 20);
		this.joinSuccessLabel = new JLabel(message);
		this.joinSuccessLabel.setFont(font);
		this.joinSuccessLabel.setBounds(CorrectEnum.JOIN_SUCCESS_TEXT_SIZE_RECT.getRect());
		
		this.confirm = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/confirm.kor.png")), 
						0, 
						0, 
						CorrectEnum.DROPOUT_COMPLETE_BUTTON_SIZE_RECT.getRect().width,
						CorrectEnum.DROPOUT_COMPLETE_BUTTON_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.confirm.setFocusPainted(false);
		this.confirm.setBorderPainted(false);
		this.confirm.setContentAreaFilled(false);
		this.confirm.setBounds(CorrectEnum.DROPOUT_COMPLETE_BUTTON_SIZE_RECT.getRect());
		
		this.confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				joinFrame.getBasicFrame().setVisible(true);
			}
		});
		
		this.add(joinSuccessLabel);
		this.add(confirm);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		
		
	
	}
}
