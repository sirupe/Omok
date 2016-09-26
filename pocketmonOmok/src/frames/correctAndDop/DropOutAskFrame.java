package frames.correctAndDop;

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

import enums.frames.CorrectEnum;
import enums.frames.SearchIdEnum;

public class DropOutAskFrame extends JFrame{
	private Image backGround;
	private JLabel dropOutLabel;
	private JButton confirm;
	private JButton reset;
	
	public DropOutAskFrame() throws IOException {
		this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
                Image.SCALE_SMOOTH);

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		this.dropOutLabel = new JLabel("Á¤¸» Å»ÅðÇÏ½Ã°Ú½À´Ï±î? :(");
		this.dropOutLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.dropOutLabel.setBounds(CorrectEnum.DROPOUT_TEXT_SIZE_RECT.getRect());
		
		this.confirm = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/confirm.kor.png")), 
						0, 
						0, 
						CorrectEnum.DROPOUT_CONFIRM_BUTTON_RECT.getRect().width,
						CorrectEnum.DROPOUT_CONFIRM_BUTTON_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.confirm.setFocusPainted(false);
		this.confirm.setBorderPainted(false);
		this.confirm.setContentAreaFilled(false);
		this.confirm.setBounds(CorrectEnum.DROPOUT_CONFIRM_BUTTON_RECT.getRect());
		
		this.reset = new  JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/reset.kor.png")), 
						0, 
						0, 
						CorrectEnum.DROPOUT_RESET_BUTTON_RECT.getRect().width,
						CorrectEnum.DROPOUT_RESET_BUTTON_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.reset.setFocusPainted(false);
		this.reset.setBorderPainted(false);
		this.reset.setContentAreaFilled(false);
		this.reset.setBounds(CorrectEnum.DROPOUT_RESET_BUTTON_RECT.getRect());
		
		this.add(dropOutLabel);
		this.add(confirm);
		this.add(reset);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	public static void main(String[] args) throws IOException {
		new DropOutAskFrame();
	}

}
