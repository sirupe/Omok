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

import enums.frames.ChargeEnum;
import enums.frames.CorrectEnum;
import enums.frames.SearchIdEnum;

public class CorrectCompleteFrame extends JFrame {
	private Image backGround;
	private JLabel correctComplete;
	private JButton confirmButton;
	
	public CorrectCompleteFrame() throws IOException {
		this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
                Image.SCALE_SMOOTH);

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
			
		
		this.setBounds(CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect());
		this.correctComplete = new JLabel("수정이 완료되었습니다.");
		this.correctComplete.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.correctComplete.setBounds(CorrectEnum.CORRECT_COMPLETE_TEXT_SIZE_RECT.getRect());

		this.confirmButton = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/confirm.kor.png")), 
						0, 
						0, 
						CorrectEnum.CORRECT_COMPLETE_BUTTON_SIZE_RECT.getRect().width,
						CorrectEnum.CORRECT_COMPLETE_BUTTON_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.confirmButton.setFocusPainted(false);
		this.confirmButton.setBorderPainted(false);
		this.confirmButton.setContentAreaFilled(false);
		this.confirmButton.setBounds(CorrectEnum.CORRECT_COMPLETE_BUTTON_SIZE_RECT.getRect());
		
		this.add(correctComplete);
		this.add(confirmButton);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new CorrectCompleteFrame();
	}
}
