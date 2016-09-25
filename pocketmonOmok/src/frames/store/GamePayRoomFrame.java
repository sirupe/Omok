package frames.store;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.frames.GamePayRoomEnum;
import enums.frames.SearchIdEnum;

public class GamePayRoomFrame extends JFrame {
	private Image icon;
	private JLabel imageIcon;
	private JLabel basicMoneyLabel;
	private JLabel divLabel;
	private JTextField userAmountText;
	private JLabel equalsLabel;
	private JLabel totalAmountLabel;
	
	private JPanel itemquanity;
	
	private JButton payButton;
	private JButton cancelButton;
	private Image backGround;
	
	public GamePayRoomFrame() throws IOException {
		
		this.itemquanity = new JPanel();
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
	
		
		this.payButton = new JButton();
		this.payButton.setBorderPainted(false);
		this.payButton.setFocusPainted(false);
		this.payButton.setContentAreaFilled(false);
		
		this.cancelButton = new JButton();
		this.cancelButton.setBorderPainted(false);
		this.cancelButton.setFocusPainted(false);
		this.cancelButton.setContentAreaFilled(false);
//프레임 화면 크기 위치
		//배경화면	
		
				backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
						GamePayRoomEnum.GAME_PAY_ROOM_WIDTH.getSize(),
						GamePayRoomEnum.GAME_PAY_ROOM_HEIGHT.getSize(),
		                Image.SCALE_SMOOTH);

				this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		
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
		this.add(imageIcon);	
	}
	//결제라인
	public void setPayLine() {
		//아이템 *수량 -- > 패널로 묶기
		this.itemquanity.setBounds(GamePayRoomEnum.GAME_ROOM_AMOUNT_PANEL.getRectangle());
		this.itemquanity.setLayout(null);
		this.itemquanity.setOpaque(false);
//		this.itemquanity.setBackground(Color.blue);
		
		//수량을 넣을 수 있는 텍스트 필드 
		JTextField userAmountText = new JTextField();
		userAmountText.setBounds(GamePayRoomEnum.GAME_ROOM_USER_AMOUNT_PANEL.getRectangle());
		//폰트  searchIdEnum에서 가져옴
		userAmountText.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		userAmountText.setBorder(GamePayRoomEnum.LABEL_LINE.getMatterBorder());
		userAmountText.setOpaque(false);

		//증가하는 버튼
		JButton up = new JButton() {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/up.png")), 
						0, 
						0, 
						GamePayRoomEnum.GAME_ROOM_UP_ADD.getRectangle().width,
						GamePayRoomEnum.GAME_ROOM_UP_ADD.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		// 감소하는 버튼
		JButton down = new JButton() {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/down.png")), 
						0, 
						0, 
						GamePayRoomEnum.GAME_ROOM_DOWN_SUB.getRectangle().width,
						GamePayRoomEnum.GAME_ROOM_DOWN_SUB.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		up.setBounds(GamePayRoomEnum.GAME_ROOM_UP_ADD.getRectangle());
		up.setIconTextGap(up.getIconTextGap() - 15);
		
		down.setBounds(GamePayRoomEnum.GAME_ROOM_DOWN_SUB.getRectangle());
		down.setIconTextGap(down.getIconTextGap() - 15);

		

		this.basicMoneyLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_BASICMONEY_LABEL.getRectangle());
		this.basicMoneyLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.basicMoneyLabel.setOpaque(false);
		
		this.divLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_DIV_LABEL.getRectangle());
		this.divLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.divLabel.setOpaque(false);
		
		this.equalsLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_EQUAL_LABEL.getRectangle());
		this.equalsLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		
		
		this.totalAmountLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_TOTALMONEY_LABEL.getRectangle());
		this.totalAmountLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.totalAmountLabel.setBorder(GamePayRoomEnum.LABEL_LINE.getMatterBorder());
		this.totalAmountLabel.setOpaque(false);

		this.add(totalAmountLabel);
		this.add(equalsLabel);
		this.add(basicMoneyLabel);
		this.add(divLabel);
//		itemquanity.setOpaque(true);
		
		
		up.setFocusPainted(false);
		up.setBorderPainted(false);
		up.setContentAreaFilled(false);
		
		down.setFocusPainted(false);
		down.setBorderPainted(false);
		down.setContentAreaFilled(false);
		
		this.itemquanity.add(up);
		this.itemquanity.add(down);
		this.itemquanity.add(userAmountText);
		this.add(itemquanity);
		
	}
	public void setButtonLine() throws IOException {
		//결제 버튼
		this.payButton.setIconTextGap(this.payButton.getIconTextGap() - 15 );
		this.payButton.setIcon(
				new ImageIcon(ImageIO.read(
						new File("resources/store/pay.png")).getScaledInstance(
								GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle().width,
								GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle().height,
	    						Image.SCALE_AREA_AVERAGING))
	    		);
						
		this.payButton.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle());
		
		//취소 버튼
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
