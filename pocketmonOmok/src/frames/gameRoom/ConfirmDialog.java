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

public class ConfirmDialog extends JDialog{
	private static final long serialVersionUID = 4259628714175624194L;
	private Image backGround;
	private JLabel giveUpText;
	private JButton yes;
	private JButton no;
	private boolean isWithdrawCheck;
	
	public  ConfirmDialog(BasicFrame basicFrame, String message) {
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
		
		this.giveUpText = new JLabel(message);
		this.giveUpText.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.giveUpText.setBounds(GameRoomEnum.GIVEUP_TEXT_SIZE_RECT.getRect());
		
		this.yes = new JButton() {
			private static final long serialVersionUID = -3843119526775466924L;

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
			private static final long serialVersionUID = 7309166668119941728L;

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
	
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		
		this.yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isWithdrawCheck = true;
				dispose();
			}
		});
		
		this.no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isWithdrawCheck = false;
				dispose();
			}
		});
		
		this.add(giveUpText);
		this.add(yes);
		this.add(no);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public boolean isYesNoCheck() {
		return isWithdrawCheck;
	}
}

