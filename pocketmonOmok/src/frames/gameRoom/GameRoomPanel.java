package frames.gameRoom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import datasDTO.UserInGameRoomDTO;
import enums.etc.ImageEnum;
import enums.frames.GameRoomEnum;
import frames.BasicFrame;

@SuppressWarnings("serial")
public class GameRoomPanel extends JPanel {
	private BasicFrame basicFrame;
	
	private JPanel gameBoardPanel;	// 오목판
	private JPanel omokStonePanel;	// 돌을 놓을 패널
	private JPanel timeLimitPanel;	// 시간제한 표시
	private JPanel userImagePanel;	// 유저이미지
	private JPanel gameMenuPanel;	// 게임메뉴 및 아이템
	private JPanel chattingPanel;	// 채팅
	private Thread timeLimitThread;
	
	private JLabel leftUser;
	private JLabel rightUser;
	private JButton[] menuButtons;
	
	public GameRoomPanel(BasicFrame basicFrame) throws IOException {
		this.basicFrame = basicFrame;
		
		this.setLayout(null);
		
		this.omokStonePanel = new JPanel();
		this.timeLimitPanel = new JPanel();
		this.userImagePanel = new JPanel();
		this.gameMenuPanel  = new JPanel();
		this.chattingPanel  = new JPanel();

		this.setGameBoard();
		this.setUserImage();
		this.setInGameMenuButtons();
		this.setInGameChattingArea();
		this.setTimeLimit();
		this.setStonePanel();
			
	} // 생성자
	
	public void setStonePanel() {
		this.omokStonePanel.setLayout(null);
		this.omokStonePanel.setOpaque(false);
		JButton[][] stonesLocation = new JButton[15][];

		for(int i = 0, iSize = stonesLocation.length; i < iSize; i++) {
			stonesLocation[i] = new JButton[15];
			for(int j = 0, jSize = stonesLocation.length; j < jSize; j++) {
				stonesLocation[i][j] = new JButton();
				stonesLocation[i][j].setBounds(new Rectangle(
						j * GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
						i * GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height,
						GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
						GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height
				));
				
				stonesLocation[i][j].setContentAreaFilled(false);
				stonesLocation[i][j].setFocusPainted(false);
				stonesLocation[i][j].setBorderPainted(false);
				this.omokStonePanel.add(stonesLocation[i][j]);
			}
		}
//		try {
//			stonesLocation[7][7].setIcon(
//					new ImageIcon(
//						ImageIO.read(new File(ImageEnum.GAMEROOM_STONE_CHARMANDER.getImageDir())).getScaledInstance(
//							GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
//							GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height, 
//							Image.SCALE_AREA_AVERAGING)
//					));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		this.omokStonePanel.setBounds(GameRoomEnum.GAME_STONEPANEL_RECT.getRect());
	}
	
	
	public void setGameBoard() {
		this.gameBoardPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File(ImageEnum.GAMEROOM_BOARD_IMAGE.getImageDir())), 
						0, 
						0, 
						GameRoomEnum.GAME_BOARD_PANEL_RECT.getRect().width, 
						GameRoomEnum.GAME_BOARD_PANEL_RECT.getRect().height, 
						this
					);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		this.gameBoardPanel.setLayout(null);
		this.gameBoardPanel.setBounds(GameRoomEnum.GAME_BOARD_PANEL_RECT.getRect());
		
		this.gameBoardPanel.add(this.omokStonePanel);
		this.add(this.gameBoardPanel);
	} // 오목판 패널
	
	
	public void setTimeLimit() {
		this.timeLimitPanel.setLayout(null);
		this.timeLimitPanel.setOpaque(false);
		this.timeLimitPanel.setBounds(GameRoomEnum.GAME_TIMELIMIT_PANEL_RECT.getRect());
		
		Integer time = 20;
		JProgressBar timeBar = new JProgressBar(0, 30);
		timeBar.setValue(time);
		timeBar.setBounds(GameRoomEnum.GAME_TIMELIMIT_PROGRESS_RECT.getRect());
		
		
		JLabel timeLabel = new JLabel("0:" + time.toString());
		timeLabel.setBounds(GameRoomEnum.GAME_TIMELIMIT_TIMELABEL_RECT.getRect());
		timeLabel.setForeground(Color.red);
		timeLabel.setFont(GameRoomEnum.GAME_TIMELABEL_FONT.getFont());
		
		this.timeLimitThread = new Thread() {
			@Override
			public void run() {
				for(int i = time; i >= 0; i--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					timeBar.setValue(i);
					timeLabel.setText("0:" + String.valueOf(i));
				}
			}
		};
		
		this.timeLimitPanel.add(timeLabel);
		this.timeLimitPanel.add(timeBar);
		this.add(this.timeLimitPanel);
	} // 시간제한 표시 패널
	
	
	public void setUserImage() throws IOException {
		this.userImagePanel.setBounds(GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect());
		this.userImagePanel.setLayout(null);
			
		this.leftUser = new JLabel();
		this.leftUser.setIcon(new ImageIcon(ImageIO.read(
			new File(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir())).getScaledInstance(
				GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
				GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height, 
				Image.SCALE_AREA_AVERAGING)));
		this.leftUser.setBounds(GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect());
		this.leftUser.setBorder(BorderFactory.createLineBorder(Color.red, 6));
		this.userImagePanel.add(this.leftUser);
		
		this.rightUser = new JLabel();
		this.rightUser.setIcon(new ImageIcon(ImageIO.read(
			new File(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir())).getScaledInstance(
				GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect().width, 
				GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect().height,
				Image.SCALE_AREA_AVERAGING)));		
		this.rightUser.setBounds(GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect());
		this.rightUser.setBorder(BorderFactory.createLineBorder(Color.blue, 6));
		this.userImagePanel.add(this.rightUser);
		
		this.userImagePanel.add(this.leftUser);
		this.userImagePanel.add(this.rightUser);
		this.add(this.userImagePanel);
		
	} // 유저이미지 패널
	
	
	public void setInGameMenuButtons() {
		this.gameMenuPanel.setLayout(null);
		this.gameMenuPanel.setOpaque(false);
		this.gameMenuPanel.setBounds(GameRoomEnum.GAME_MENU_PANEL_RECT.getRect());
		String[] buttonsName = GameRoomEnum.GAME_BUTTONNAME.getButtonName();
		String[] buttonsDir = ImageEnum.GAMEROOM_MENU_IMAGES_OWNER.getImages();
		this.menuButtons = new JButton[buttonsName.length];
		
		for(int i = 0, size = buttonsName.length; i < size; i++) {
			try {
				this.menuButtons[i] = new JButton(
					new ImageIcon(
						ImageIO.read(new File(buttonsDir[i])).getScaledInstance(
							GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
							GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height, 
							Image.SCALE_AREA_AVERAGING)
					)
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(i < 4) {
				this.menuButtons[i].setBounds(new Rectangle(
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().x + (GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width * i),
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().y,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height
				));
			} else {
				this.menuButtons[i].setBounds(new Rectangle(
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().x + (GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width * (i - 4)),
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height
				));
				
			}
			this.menuButtons[i].setName(buttonsName[i]);
			this.menuButtons[i].setBorderPainted(false);
			this.menuButtons[i].setContentAreaFilled(false);
			this.menuButtons[i].setFocusPainted(false);
			this.gameMenuPanel.add(this.menuButtons[i]);
		}
		
		this.add(this.gameMenuPanel);
	} // 게임메뉴 및  아이템 패널
	
	
	public void setInGameChattingArea() {
		this.chattingPanel.setLayout(null);
		this.chattingPanel.setOpaque(false);
		this.chattingPanel.setBounds(GameRoomEnum.GAME_CHATTING_PANEL_RECT.getRect());
		
		
		JTextArea chattingArea = new JTextArea();
		chattingArea.setLineWrap(true);
		chattingArea.setFont(GameRoomEnum.GAME_CHATTING_FONT.getFont());
		chattingArea.setEditable(false);
		
		JTextField chattingField = new JTextField();
		chattingField.setBounds(GameRoomEnum.GAME_CHATTINGFIELD_RECT.getRect());
		chattingField.setFont(GameRoomEnum.GAME_CHATTING_FONT.getFont());

		JScrollPane scroll = new JScrollPane(chattingArea);
		scroll.setBounds(GameRoomEnum.GAME_SCROLL_PANE_RECT.getRect());
		
		this.chattingPanel.add(scroll);
		this.chattingPanel.add(chattingField);
		this.add(this.chattingPanel);
	} // 채팅 패널
	
	//TODO
	public void setEnterUserInfo(UserInGameRoomDTO inGameUserInfo) {
		System.out.println("고객들어왔다고");
		String imageDir = null;
		if(this.basicFrame.getUserID().equals(inGameUserInfo.getGameRoomInfo().getOwner())) {
			imageDir = ImageEnum.GAMEROOM_START_GRAY.getImageDir();
			System.out.println("주인이라고");
			this.leftUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
		} else {
			System.out.println("손님이라고");
			imageDir = ImageEnum.GAMEROOM_READY_GRAY.getImageDir();
			this.rightUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
		}
		
		try {
			this.menuButtons[0].setIcon(new ImageIcon(
				ImageIO.read(new File(imageDir)).getScaledInstance(
					GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
					GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height, 
					Image.SCALE_AREA_AVERAGING)
			));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
}