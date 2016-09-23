package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.frames.GamePayRoomEnum;
import enums.frames.GameStoreEnum;
import enums.frames.searchIdEnum;

public class GamePayRoomFrame extends JFrame {
	private Image icon;
	private JLabel imageIcon;
	private JLabel basicMoneyLabel;
	private JLabel divLabel;
	private JTextField userAmountText;
	private JLabel equalsLabel;
	private JLabel totalAmountLabel;
	
	private JPanel q;
	
	private JButton payButton;
	private JButton cancelButton;
	private Image backGround;
	
	public GamePayRoomFrame() throws IOException {

		
		this.q = new JPanel();

		this.imageIcon = new JLabel(""); //안에 구입할 아이템이 들어 가야 한다.
		
		this.imageIcon.setOpaque(true);
		this.imageIcon.setBackground(Color.pink);
		

		this.basicMoneyLabel  = new JLabel("1000");
		this.basicMoneyLabel.setOpaque(true);
		//this.basicMoneyLabel.setBackground(Color.pink);
		this.divLabel         = new JLabel("X");
		this.equalsLabel      = new JLabel("=");
		this.totalAmountLabel = new JLabel();
		this.totalAmountLabel.setOpaque(true);
		this.totalAmountLabel.setBackground(Color.BLUE);
	
		
		this.payButton = new JButton();
		this.payButton.setBorderPainted(false);
		this.payButton.setFocusPainted(false);
		this.payButton.setContentAreaFilled(false);
		
		this.cancelButton = new JButton();
		this.cancelButton.setBorderPainted(false);
		this.cancelButton.setFocusPainted(false);
		this.cancelButton.setContentAreaFilled(false);
//프레임 화면 크기 위치
		this.setBounds(
				GamePayRoomEnum.GAME_PAY_ROOM_POSITION_X.getSize(),
				GamePayRoomEnum.GAME_PAY_ROOM_POSITION_Y.getSize(),
				GamePayRoomEnum.GAME_PAY_ROOM_WIDTH.getSize(),
				GamePayRoomEnum.GAME_PAY_ROOM_HEIGHT.getSize()
		);
		
		this.setImageLine();
		this.setPayLine();
		this.setButtonLine();
		
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	public void setImageLine() {
		//상품을 구매하는사람에 따라 아이템이 다르니깐..? 라벨로 처리 했씁니당
		this.imageIcon.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_ITEM_LABEL.getRectangle());
		this.imageIcon.setFont(GamePayRoomEnum.LABELFONT_DEFAULT.getFont());
		this.add(imageIcon);	
	}
	public void setPayLine() {
		this.q.setBounds(GamePayRoomEnum.GAME_ROOM_AMOUNT_PANEL.getRectangle());
		this.q.setLayout(null);
		this.q.setBackground(Color.blue);
		
		JTextField userAmountText = new JTextField();
		userAmountText.setBounds(GamePayRoomEnum.GAME_ROOM_USER_AMOUNT_PANEL.getRectangle());
		userAmountText.setFont(GamePayRoomEnum.LABELFONT_DEFAULT.getFont());
		userAmountText.setBorder(GamePayRoomEnum.LABEL_DEFAULT.getEmptyBorder());
		userAmountText.setBackground(Color.red);
				

		this.basicMoneyLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_BASICMONEY_LABEL.getRectangle());
		this.basicMoneyLabel.setFont(GamePayRoomEnum.LABELFONT_DEFAULT.getFont());
		
		this.divLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_DIV_LABEL.getRectangle());
		this.divLabel.setFont(GamePayRoomEnum.LABELFONT_DEFAULT.getFont());
		
		
		this.equalsLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_EQUAL_LABEL.getRectangle());
		this.equalsLabel.setFont(GamePayRoomEnum.LABELFONT_DEFAULT.getFont());
		
		this.totalAmountLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_TOTALMONEY_LABEL.getRectangle());
		this.totalAmountLabel.setFont(GamePayRoomEnum.LABELFONT_DEFAULT.getFont());
		

		this.add(totalAmountLabel);
		this.add(equalsLabel);
		this.add(basicMoneyLabel);
		this.add(divLabel);
		//q.setOpaque(false);
		
		this.q.add(userAmountText);
		this.add(q);
		
		System.out.println(GamePayRoomEnum.GAME_ROOM_PAY_ITEM_LABEL.getRectangle());

	}
	public void setButtonLine() throws IOException {
		this.payButton.setIconTextGap(this.payButton.getIconTextGap() - 15 );
		this.payButton.setIcon(
				new ImageIcon(ImageIO.read(
						new File("resources/store/pay.png")).getScaledInstance(
								GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle().width,
								GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle().height,
	    						Image.SCALE_AREA_AVERAGING))
	    		);
						
		this.payButton.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle());
		
		
		this.cancelButton.setIconTextGap(this.cancelButton.getIconTextGap() - 15 );
		this.cancelButton.setIcon(
				new ImageIcon(ImageIO.read(
						new File("resources/myData/reset.kor.png")).getScaledInstance(
								GamePayRoomEnum.GAME_ROOM_PAY_CANCEL_BUTTON.getRectangle().width,
								GamePayRoomEnum.GAME_ROOM_PAY_CANCEL_BUTTON.getRectangle().height,
	    						Image.SCALE_AREA_AVERAGING))
	    		);
						
		this.cancelButton.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_CANCEL_BUTTON.getRectangle());
		
	
		this.add(payButton);
		this.add(cancelButton);
	}
	
	public static void main(String[] args) throws IOException {
		new GamePayRoomFrame();

	}

}
