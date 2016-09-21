package frames;

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

import enums.GameRoomEnum;
import enums.GameStoreEnum;
import enums.searchPwdEnum;
import enums.searchRePwdEnum;

public class GameStorePanel extends JPanel {
	private JPanel userMoneyPanel;
	private JPanel itemChoicePanel;
	private JPanel outPanel;
	private Image backGround;
	
	public GameStorePanel() throws IOException {
		
		this.setLayout(null);
		
		backGround = ImageIO.read(new File("resources/signUp/backg.png")).getScaledInstance(
				GameStoreEnum.GAME_STORE_PANEL_WIDTH.getSize(),
				GameStoreEnum.GAME_STORE_PANEL_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);

		this.add(new JLabel(new ImageIcon(backGround)));
		
		this.setVisible(true);	
		this.setOpaque(false);
		
		this.userMoneyPanel   = new JPanel();
		this.itemChoicePanel  = new JPanel();
		this.outPanel         = new JPanel();
		
		this.setUserPanel();
		this.setItemPanel();
		this.setOutPanel();
		
			
	}
	
	public void setUserPanel() throws IOException {
		this.userMoneyPanel.setBounds(GameStoreEnum.STORE_USER_MONEY_PANEL_REC.getRectangle());
		this.userMoneyPanel.setLayout(null);
		//this.userMoneyPanel.setBackground(Color.black);
		
		JTextField userMoney = new JTextField("보유한 금액");
		userMoney.setBounds(GameStoreEnum.STORE_USER_MONEY_REC.getRectangle());
		userMoney.setFont(GameStoreEnum.LABELFONT_DEFAULT.getFont());
		userMoney.setBorder(GameStoreEnum.LABEL_DEFAULT.getEmptyBorder());
		//userMoney.setBackground(Color.red);
		
		JButton userConfirm = new JButton() {	
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			try {
				g.drawImage(ImageIO.read(
					new File("resources/store/charge.png")), 
					0, 
					0, 
					GameStoreEnum.STORE_USER_CONFIRM_BUTTON_REC.getRectangle().width,
					GameStoreEnum.STORE_USER_CONFIRM_BUTTON_REC.getRectangle().height,
					this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		};
		userConfirm.setBounds(GameStoreEnum.STORE_USER_CONFIRM_BUTTON_REC.getRectangle());
		userConfirm.setIconTextGap(userConfirm.getIconTextGap() - 15);
		this.userMoneyPanel.add(userConfirm);
		this.userMoneyPanel.add(userMoney);
		this.add(this.userMoneyPanel);		
	}
	
	public void setItemPanel() {
		this.itemChoicePanel.setLayout(null);
		this.itemChoicePanel.setBounds(GameStoreEnum.STORE_ITEM_CHOICE_PANEL_REC.getRectangle());
		//this.itemChoicePanel.setBackground(Color.blue);
		
//		protected void paintComponent(Graphics g) {
//			 super.paintComponent(g);
//			g.setColor( Color.red );
//			g.drawRect(220, 30, 160, 160);// 사각형 그리기
//	 
//		}
		//방해하기 아이템 보유수 라벨 위치 폰트
		JLabel userOwnInterrptItem = new JLabel("1/99");
		userOwnInterrptItem.setBounds(GameStoreEnum.STORE_USER_OWN_INTERRUPT_ITEM_REC.getRectangle());
		userOwnInterrptItem.setFont(GameStoreEnum.LABELFONT_DEFAULT.getFont());
		userOwnInterrptItem.setBackground(Color.red);
		userOwnInterrptItem.setOpaque(true);
		
//---------------------------------------------------------------------------------------------		
		//무르기 아이템 보유수 라벨
		JLabel userOwnReturnItem = new JLabel("2/99");
		userOwnReturnItem.setBounds(GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_REC.getRectangle());
		userOwnReturnItem.setFont(GameStoreEnum.LABELFONT_DEFAULT.getFont());
		userOwnReturnItem.setBackground(Color.red);
		userOwnReturnItem.setOpaque(true);
		//시간 늘리기 아이템 보유수 라벨
		JLabel userOwnTimeExtionItem = new JLabel("0/99");
		userOwnTimeExtionItem.setBounds(GameStoreEnum.STORE_USER_OWN_TIMEEXTION_ITEM_REC.getRectangle());
		userOwnTimeExtionItem.setFont(GameStoreEnum.LABELFONT_DEFAULT.getFont());
		userOwnTimeExtionItem.setBackground(Color.red);
		userOwnTimeExtionItem.setOpaque(true);
		
		//방해하기 아이템 위치
		JButton userOwnInterrptItemButton = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/gameRoom/interrupt.png")), 
						0, 
						0, 
						GameStoreEnum.STORE_USER_OWN_INTERRUPT_ITEM_BUTTON_REC.getRectangle().width,
						GameStoreEnum.STORE_USER_OWN_INTERRUPT_ITEM_BUTTON_REC.getRectangle().height,
						this);
				}catch (IOException e) {
					e.printStackTrace();
				}		
			}
		};	
		userOwnInterrptItemButton.setBounds(GameStoreEnum.STORE_USER_OWN_INTERRUPT_ITEM_BUTTON_REC.getRectangle());
		userOwnInterrptItemButton.setIconTextGap(userOwnInterrptItemButton.getIconTextGap() - 15);

	//무르기 아이템 
		JButton userOwnReturnItemButton = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/gameRoom/gamereturn.png")), 
						0, 
						0, 
						GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle().width,
						GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		};	
		userOwnReturnItemButton.setBounds(GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle());
		userOwnReturnItemButton.setIconTextGap(userOwnReturnItemButton.getIconTextGap() - 15);
		
//시간 증가 아이템 
				JButton userOwnTimeExtionItemButton = new JButton() {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						try {
							g.drawImage(ImageIO.read(
								new File("resources/gameRoom/itemtimeplus.png")), 
								0, 
								0, 
								GameStoreEnum.STORE_USER_OWN_TIMEEXTION_ITEM_BUtton_REC.getRectangle().width,
								GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle().height,
								this);
						} catch (IOException e) {
							e.printStackTrace();
						}		
					}
				};	
				userOwnTimeExtionItemButton.setBounds(GameStoreEnum.STORE_USER_OWN_TIMEEXTION_ITEM_BUtton_REC.getRectangle());
				userOwnTimeExtionItemButton.setIconTextGap(userOwnTimeExtionItemButton.getIconTextGap() - 15);
		
		
		
		this.itemChoicePanel.add(userOwnInterrptItem);
		this.itemChoicePanel.add(userOwnReturnItem);
		this.itemChoicePanel.add(userOwnTimeExtionItem);
		this.itemChoicePanel.add(userOwnInterrptItemButton);
		this.itemChoicePanel.add(userOwnReturnItemButton);
		this.itemChoicePanel.add(userOwnTimeExtionItemButton);
		this.add(this.itemChoicePanel);
}
	
	
	public void setOutPanel() {
		this.outPanel.setLayout(null);
		this.outPanel.setBounds(GameStoreEnum.STORE_OUT_PANEL_REC.getRectangle());
		//this.outPanel.setBackground(Color.PINK);
		
		JButton OutButton = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/backButton.png")), 
						0, 
						0, 
						GameStoreEnum.STORE_OUT_BUTTON_REC.getRectangle().width,
						GameStoreEnum.STORE_OUT_BUTTON_REC.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		};	
		OutButton.setBounds(GameStoreEnum.STORE_OUT_BUTTON_REC.getRectangle());
		OutButton.setIconTextGap(OutButton.getIconTextGap() - 15);
		this.outPanel.add(OutButton);
		this.add(this.outPanel);
	}
}
