package frames;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.GamePayRoomEnum;
import enums.frames.searchIdEnum;

public class GamePayRoomFrame extends JFrame{
	private Image icon;
	private JLabel basicMoneyJLabel;
	private JLabel itemQuantityLabel;
	private JLabel equals;
	private JLabel totalAmountLabel;
	
	private JButton payButton;
	private JButton cancelButton;
	private BufferedImage backGround;
	
	public GamePayRoomFrame() throws IOException {
		
		
		
		
		//배경화면 정해주기
		backGround = ImageIO.read(new File(""));
		this.backGround.getScaledInstance(
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
		
		
		this.setResizable(false);
		this.setVisible(true);
	}
	public void setImageLine() {
		//상품을 구매하는사람에 따라 아이템이 다르니깐..? 라벨로 처리 했씁니당
		
		
	}
	public void setPayLine() {

	}
	public void setButtonLine() {
		
	}
	
	public static void main(String[] args) {
		try {
			new GamePayRoomFrame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
