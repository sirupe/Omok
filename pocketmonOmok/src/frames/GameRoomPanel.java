package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import enums.GameRoomEnum;
import enums.LoginSizesEnum;

public class GameRoomPanel extends JPanel {
	private JPanel gameBoardPanel;
	private JPanel omokStonePanel;
	private JPanel timeLimitPanel;
	private JPanel userImagePanel;
	private JPanel gameMenuPanel;
	private JPanel chattingPanel;
	
	
	
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
			
	}
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		this.gameBoardPanel.setBackground(Color.red);
		this.gameBoardPanel.setBounds(GameRoomEnum.GAME_BOARD_PANEL_RECT.getRect());
		
		
		this.add(this.gameBoardPanel);
	}
	
	public void setTimeLimit() {
		this.timeLimitPanel.setBackground(Color.black);
		this.timeLimitPanel.setBounds(GameRoomEnum.GAME_TIMELIMIT_PANEL_RECT.getRect());
		this.add(this.timeLimitPanel);
	}
	
	public void setUserImage() {
		this.userImagePanel.setBackground(Color.green);
		this.userImagePanel.setBounds(GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect());
		
		this.add(this.userImagePanel);
		
	}
	
	public void setInGameMenuButtons() {
		this.gameMenuPanel.setBackground(Color.orange);
		this.gameMenuPanel.setBounds(GameRoomEnum.GAME_MENU_PANEL_RECT.getRect());
		
		this.add(this.gameMenuPanel);
	}
	
	public void setInGameChattingArea() {
		this.chattingPanel.setBackground(Color.yellow);
		this.chattingPanel.setBounds(GameRoomEnum.GAME_CHATTING_PANEL_RECT.getRect());
		
		this.add(this.chattingPanel);
	}
}
