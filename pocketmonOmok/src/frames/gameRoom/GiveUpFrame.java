package frames.gameRoom;

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
import enums.frames.GameRoomEnum;
import enums.frames.SearchIdEnum;

public class GiveUpFrame extends JFrame{
	private Image backGround;
	private JLabel giveUpText;
	private JButton yes;
	private JButton no;
	
	public  GiveUpFrame() throws IOException {
		this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
                Image.SCALE_SMOOTH);

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		this.setBounds(GameRoomEnum.GIVEUP_FRAME_SIZE_RECT.getRect());
		
		this.giveUpText = new JLabel("기권하시겠습니까?");
		this.giveUpText.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.giveUpText.setBounds(GameRoomEnum.GIVEUP_TEXT_SIZE_RECT.getRect());
		
		this.yes = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/yesno/yes.kor.png")), 
						0, 
						0, 
						GameRoomEnum.GIVEUP_YES_BUTTON_RECT.getRect().width,
						GameRoomEnum.GIVEUP_YES_BUTTON_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.yes.setFocusPainted(false);
		this.yes.setBorderPainted(false);
		this.yes.setContentAreaFilled(false);
		this.yes.setBounds(GameRoomEnum.GIVEUP_YES_BUTTON_RECT.getRect());

		this.no = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/yesno/no.kor.png")), 
						0, 
						0, 
						GameRoomEnum.GIVEUP_NO_BUTTON_RECT.getRect().width,
						GameRoomEnum.GIVEUP_NO_BUTTON_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.no.setFocusPainted(false);
		this.no.setBorderPainted(false);
		this.no.setContentAreaFilled(false);
		this.no.setBounds(GameRoomEnum.GIVEUP_NO_BUTTON_RECT.getRect());
	
		this.add(giveUpText);
		this.add(yes);
		this.add(no);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		
	}

	public static void main(String[] args) throws IOException {
		new GiveUpFrame();
	}
}
