package frames;

import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePayRoomFrame extends JFrame{
	private Image icon;
	private JLabel basicMoneyJLabel;
	private JLabel itemQuantityLabel;
	private JLabel equals;
	private JLabel totalAmountLabel;
	
	private JButton payButton;
	private JButton cancelButton;
	
	public GamePayRoomFrame() {
		
		
		this.setResizable(false);
		this.setVisible(true);
	}
	public void setImageLine() {
		
	}
	public void setPayLine() {

	}
	public void setButtonLine() {
		
	}
	
	public static void main(String[] args) {
		new GamePayRoomFrame();

	}

}
