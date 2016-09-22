package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.GameStoreEnum;

public class GameStoreFrame extends JFrame{
	private GameStorePanel gameStorePanel;
	private Image backGround;
	
	public GameStoreFrame() throws IOException {

		this.gameStorePanel = new GameStorePanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
							new File("resources/signUp/backg.png")),
							0,
							0,
							GameStoreEnum.GAME_STORE_PANEL_WIDTH.getSize(),
							GameStoreEnum.GAME_STORE_PANEL_HEIGHT.getSize(),
							this);
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		gameStorePanel.setOpaque(false);

		this.setBounds(
				GameStoreEnum.GAME_STORE_PANEL_POSITION_X.getSize(),
				GameStoreEnum.GAME_STORE_PANEL_POSITION_Y.getSize(),
				GameStoreEnum.GAME_STORE_PANEL_WIDTH.getSize(),
				GameStoreEnum.GAME_STORE_PANEL_HEIGHT.getSize()
				);
		
		
		this.add(gameStorePanel);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args) {	
		try {
			new GameStoreFrame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
