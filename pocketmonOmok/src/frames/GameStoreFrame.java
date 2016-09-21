package frames;

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
		
		
//		
//		backGround = ImageIO.read(new File("resources/signUp/backg.png")).getScaledInstance(
//				searchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
//				searchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
//                Image.SCALE_SMOOTH);
//		this.add(new JLabel(new ImageIcon(backGround)));
//		this.setContentPane(new JLabel(new ImageIcon(backGround))); 
		
		this.setBounds(
				GameStoreEnum.GAME_STORE_PANEL_POSITION_X.getSize(),
				GameStoreEnum.GAME_STORE_PANEL_POSITION_Y.getSize(),
				GameStoreEnum.GAME_STORE_PANEL_WIDTH.getSize(),
				GameStoreEnum.GAME_STORE_PANEL_HEIGHT.getSize()
				);
		
		this.gameStorePanel = new GameStorePanel();
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
