package frames.store;


import java.io.IOException;
import javax.swing.JFrame;

import enums.frames.GameStoreEnum;

@SuppressWarnings("serial")
public class GameStoreFrame extends JFrame  {
	private GameStorePanel gameStorePanel;
	
	public GameStoreFrame() throws IOException {

		this.gameStorePanel = new GameStorePanel();
		
		this.gameStorePanel.setOpaque(false);
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
			e.printStackTrace();
		}
	}
}
