package frames.gameRoom;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import enums.frames.CorrectEnum;
import enums.frames.GameRoomEnum;
import enums.frames.SearchIDEnum;
import frames.BasicFrame;

public class GameEndDialog extends JDialog{
	private static final long serialVersionUID = -2499879498833430195L;
	private Image backGround;
	private JLabel textLabel;
	private JButton yes;
	
	public  GameEndDialog(BasicFrame basicFrame, String message) {
		super(basicFrame, true);
		try {
			this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
					CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
					CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
			        Image.SCALE_SMOOTH);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		this.setBounds(GameRoomEnum.GIVEUP_FRAME_SIZE_RECT.getRect());
		
		this.textLabel = new JLabel(message);
		this.textLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.textLabel.setBounds(GameRoomEnum.GIVEUP_TEXT_SIZE_RECT.getRect());
		
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
		
		this.yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		
		this.yes.setFocusPainted(false);
		this.yes.setBorderPainted(false);
		this.yes.setContentAreaFilled(false);
		this.yes.setBounds(GameRoomEnum.DIALOG_CHECK_BUTTON_RECT.getRect());

		this.add(textLabel);
		this.add(yes);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		
	}
}
