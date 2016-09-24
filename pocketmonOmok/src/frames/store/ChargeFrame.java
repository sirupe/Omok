package frames.store;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.ChargeEnum;

public class ChargeFrame extends JFrame {
	
	private ChargePanel chargePanel;
	private Image backGround;
	
	public ChargeFrame() throws IOException {
		
		this.chargePanel = new ChargePanel()  {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/background/popup.png")),
							0,
							0,
							ChargeEnum.CHARGE_FRAME_SIZE_WIDTH.getSize(), 
							ChargeEnum.CHARGE_FRAME_SIZE_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		this.chargePanel.setBounds(
				ChargeEnum.CHARGE_FRAME_SIZE_POSITION_X.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_POSITION_Y.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_WIDTH.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_HEIGHT.getSize()
		);
		this.chargePanel.setOpaque(false);
		
		this.setBounds(
				ChargeEnum.CHARGE_FRAME_SIZE_POSITION_X.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_POSITION_Y.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_WIDTH.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_HEIGHT.getSize()
		);
		

		this.getContentPane().add(chargePanel);
		this.setTitle("ÃæÀü");
		this.setResizable(false);
		this.setVisible(true);
	}
	

	
	public static void main(String[] args) throws IOException {
		new ChargeFrame();
	}
}
