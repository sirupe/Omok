package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ProgressMonitor;

import enums.GameRoomEnum;
import enums.ImageEnum;
import enums.LoginSizesEnum;

public class GameRoomPanel extends JPanel {
	private JPanel gameBoardPanel;	// 오목판
	private JPanel omokStonePanel;	// 돌을 놓을 패널
	private JPanel timeLimitPanel;	// 시간제한 표시
	private JPanel userImagePanel;	// 유저이미지
	private JPanel gameMenuPanel;	// 게임메뉴 및 아이템
	private JPanel chattingPanel;	// 채팅
	
	public GameRoomPanel() {
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
//		this.omokStonePanel.setBackground(Color.blue);
			
	} // 생성자
	
	@SuppressWarnings("serial")
	public void setGameBoard() {
		this.gameBoardPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/gameRoom/gameBoard.png")), 
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
		this.gameBoardPanel.setBackground(Color.red);
		this.gameBoardPanel.setBounds(GameRoomEnum.GAME_BOARD_PANEL_RECT.getRect());
		
		
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
		timeLabel.setFont(new Font("Consolas", Font.BOLD, 35));
		
		new Thread() {
			@Override
			public void run() {
				for(int i = time; i >= 0; i--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					timeBar.setValue(i);
					timeLabel.setText("0:" + String.valueOf(i));
				}
			}
		}.start();
		
		this.timeLimitPanel.add(timeLabel);
		this.timeLimitPanel.add(timeBar);
		this.add(this.timeLimitPanel);
	} // 시간제한 표시 패널
	
	public void setUserImage() {
		this.userImagePanel.setBounds(GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect());
		this.userImagePanel.setLayout(null);
		
		JPanel leftUser = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(
						ImageIO.read(new File(ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir())), 
						0, 
						0, 
						GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
						GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height, 
						this);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		leftUser.setBounds(GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect());
		leftUser.setBorder(BorderFactory.createLineBorder(Color.red, 6));
		
		JPanel rightUser = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(
						ImageIO.read(new File(ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir())), 
						0, 
						0, 
						GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect().width, 
						GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect().height, 
						this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		rightUser.setBorder(BorderFactory.createLineBorder(Color.blue, 6));
		rightUser.setBounds(GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect());
		
		this.userImagePanel.add(leftUser);
		this.userImagePanel.add(rightUser);
		this.add(this.userImagePanel);
		
	} // 유저이미지 패널
	
	public void setInGameMenuButtons() {
		this.gameMenuPanel.setLayout(null);
		this.gameMenuPanel.setBackground(Color.orange);
		this.gameMenuPanel.setBounds(GameRoomEnum.GAME_MENU_PANEL_RECT.getRect());
		Rectangle defaultRect = GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect();
		String[] buttonsName = GameRoomEnum.GAME_BUTTONNAME.getButtonName();
		JButton[] menuButtons = new JButton[buttonsName.length];
		for(int i = 0, size = buttonsName.length; i < size; i++) {
			menuButtons[i] = new JButton();
			if(i < 4) {
				menuButtons[i].setBounds(new Rectangle(
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().x + (GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width * i),
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().y,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height
				));
			} else {
				System.out.println(GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().x * (i + 1));
				menuButtons[i].setBounds(new Rectangle(
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().x + (GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width * (i - 4)),
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height
				));
				
			}
			menuButtons[i].setName(buttonsName[i]);
			this.gameMenuPanel.add(menuButtons[i]);
		}
		
		this.add(this.gameMenuPanel);
	} // 게임메뉴 및  아이템 패널
	
	public void setInGameChattingArea() {
		this.chattingPanel.setBackground(Color.yellow);
		this.chattingPanel.setBounds(GameRoomEnum.GAME_CHATTING_PANEL_RECT.getRect());
		
		this.add(this.chattingPanel);
	} // 채팅 패널

}
