package frames.store;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.frames.GameStoreEnum;
import enums.frames.WaitingRoomSizesEnum;
import enums.frames.SearchIdEnum;


public class GameStorePanel extends JPanel {
	private JPanel userMoneyPanel;
	private JPanel itemChoicePanel;
	private JPanel outPanel;
	private JPanel linePanel;
	private Image backGround;
	
	private JTextField userMoney;
	private JButton userConfirm;
	private JButton outButton;
	
	public GameStorePanel() throws IOException {
		
		this.setLayout(null);
		
		
		this.backGround = ImageIO.read(new File("resources/signUp/backg.png"));
		this.backGround.getScaledInstance(
			GameStoreEnum.GAME_STORE_PANEL_WIDTH.getSize(),
			GameStoreEnum.GAME_STORE_PANEL_HEIGHT.getSize(),
            Image.SCALE_SMOOTH
        );

		this.add(new JLabel(new ImageIcon(backGround)));
		
		this.userMoneyPanel   = new JPanel();
		this.outPanel         = new JPanel();
		this.linePanel		  = new JPanel();
		
		this.setUserPanel();
		this.setItemPanel();
		this.setOutPanel();
		
		this.setOpaque(false);	
		this.setVisible(true);	
	}	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(
					new File("resources/background/popup.png")),
					0,
					0,
					GameStoreEnum.GAME_STORE_PANEL_WIDTH.getSize(),
					GameStoreEnum.GAME_STORE_PANEL_HEIGHT.getSize(),
					this);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}



	//사용자 보유 금액, 충전하기 
	public void setUserPanel() throws IOException {
		this.userMoneyPanel.setBounds(GameStoreEnum.STORE_USER_MONEY_PANEL_REC.getRectangle());
		this.userMoneyPanel.setLayout(null);
		//this.userMoneyPanel.setBackground(Color.black);
		
		JTextField userMoney = new JTextField("보유한 금액");
		this.userMoney.setBounds(GameStoreEnum.STORE_USER_MONEY_REC.getRectangle());
		this.userMoney.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.userMoney.setBorder(GameStoreEnum.LABEL_LINE.getMatteBorder());
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
		this.userMoneyPanel.setOpaque(false);
		this.add(this.userMoneyPanel);		
	}
//========================================================================================================
	
	//아이템 보유 개수 , 아이템 위치
	public void setItemPanel() {
		this.itemChoicePanel = new JPanel();

		this.itemChoicePanel.setBounds(
			GameStoreEnum.GAME_STORE_PANEL_POSITION_X.getSize(), 
			GameStoreEnum.GAME_STORE_PANEL_POSITION_Y.getSize(), 
			GameStoreEnum.GAME_STORE_PANEL_WIDTH.getSize(), 
			GameStoreEnum.GAME_STORE_PANEL_HEIGHT.getSize()
		);
		this.itemChoicePanel.setOpaque(false);
		this.itemChoicePanel.setLayout(null);
		this.itemChoicePanel.setBounds(GameStoreEnum.STORE_ITEM_CHOICE_PANEL_REC.getRectangle());

		//get Data.
		Map<String, List<Map<String, Object>>> itemsMap = this.getResourceData();
	
		for(String itemsKey : itemsMap.keySet()) {
			
			List<Map<String, Object>> list = itemsMap.get(itemsKey);
			
			for(Map<String, Object> map : list) {
				
				if(itemsKey.equals("buttons")) {
					createButton(map);
				} else {
					createLabel(map);
				}
				
			}
		}
		
		this.add(this.itemChoicePanel);
}
	
	@SuppressWarnings({ "serial", "unchecked" })
	public HashMap<String, List<Map<String, Object>>> getResourceData() {
		return new HashMap<String, List<Map<String, Object>>>() {
			{
				put("buttons", Arrays.asList(new Map [] {
					new HashMap<String, Object>() {
						{
							put("imagePath", "resources/store/re_interrupt.png");
							put("width", GameStoreEnum.STORE_USER_OWN_INTERRUPT_ITEM_BUTTON_REC.getRectangle().width);
							put("height", GameStoreEnum.STORE_USER_OWN_INTERRUPT_ITEM_BUTTON_REC.getRectangle().height);
							put("setBounds", GameStoreEnum.STORE_USER_OWN_INTERRUPT_ITEM_BUTTON_REC.getRectangle());
						}
					},
					
					new HashMap<String, Object>() {
						{
							put("imagePath", "resources/store/return.png");
							put("width", GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle().width);
							put("height", GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle().height);
							put("setBounds", GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle());
						}
					},
					
					new HashMap<String, Object>() {
						{
							put("imagePath", "resources/store/timeExtension.png");
							put("width", GameStoreEnum.STORE_USER_OWN_TIMEEXTION_ITEM_BUtton_REC.getRectangle().width);
							put("height", GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle().height);
							put("setBounds", GameStoreEnum.STORE_USER_OWN_TIMEEXTION_ITEM_BUtton_REC.getRectangle());
						}
					},
					
					new HashMap<String, Object>() {
						{
							put("imagePath", "resources/store/ball2.png");
							put("width", GameStoreEnum.STORE_USER_OWN_TIMEEXTION_ITEM_BUtton_REC.getRectangle().width);
							put("height", GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_BUTTON_REC.getRectangle().height);
							put("setBounds", GameStoreEnum.STORE_USER_SKIN_CATCH_BUTTON_REC.getRectangle());
						}
					}
				}));
				
				put("labels", Arrays.asList(new Map [] {
					new HashMap<String, Object>() {
						{	
							put("title", "1/99");
							put("setBounds", GameStoreEnum.STORE_USER_OWN_INTERRUPT_ITEM_REC.getRectangle());
							put("setFont", SearchIdEnum.LABELFONT_DEFAULT.getFont());
							put("setOpaque", false);
						}
					},
					
					new HashMap<String, Object>() {
						{	
							put("title", "2/99");
							put("setBounds", GameStoreEnum.STORE_USER_OWN_RETURN_ITEM_REC.getRectangle());
							put("setFont", SearchIdEnum.LABELFONT_DEFAULT.getFont());
							put("setOpaque", false);
						}
					},
					
					new HashMap<String, Object>() {
						{	
							put("title", "0/99");
							put("setBounds", GameStoreEnum.STORE_USER_OWN_TIMEEXTION_ITEM_REC.getRectangle());
							put("setFont", SearchIdEnum.LABELFONT_DEFAULT.getFont());
							put("setOpaque", false);
						}
					},
					
					new HashMap<String, Object>() {
						{	
							put("title", "스킨뽑기");
							put("setBounds", GameStoreEnum.STORE_USER_SKIN_CATCH_LABEL_REC.getRectangle());
							put("setFont", SearchIdEnum.LABELFONT_DEFAULT.getFont());
							put("setOpaque", false);
						}
					}
				}));
			}
		};
	}

	//make labels
	public void createLabel(Map<String, Object> map) {
		JLabel label = new JLabel((String) map.get("title"));
		label.setBounds((Rectangle) map.get("setBounds"));
		label.setFont((Font) map.get("setFont"));
		label.setOpaque((Boolean) map.get("setOpaque"));
		
		this.itemChoicePanel.add(label);
	}
	
	//make buttons
	public void createButton(Map<String, Object> map) {
		JButton button = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File((String) map.get("imagePath"))), 
						0, 
						0, 
						(int) map.get("width"),
						(int) map.get("height"),
						this
					);
				}catch (IOException e) {
					e.printStackTrace();
				}		
			}
		};	
		
		button.setBounds((Rectangle) map.get("setBounds"));
		button.setIconTextGap(button.getIconTextGap() - 15);
		
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		
		this.itemChoicePanel.add(button);
	}
	
	// 나가기 버튼
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
		OutButton.setFocusPainted(false);
		OutButton.setBorderPainted(false);
		OutButton.setContentAreaFilled(false);
		
		this.outPanel.add(OutButton);
		this.add(this.outPanel);
		outPanel.setOpaque(false);
	}
	
}
