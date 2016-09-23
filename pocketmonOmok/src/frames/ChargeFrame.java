package frames;

import javax.swing.JFrame;

import enums.frames.ChargeEnum;

public class ChargeFrame extends JFrame {
	
	private ChargePanel chargePanel;

	public ChargeFrame() {
		
		this.chargePanel = new ChargePanel();
		
		this.setBounds(
				ChargeEnum.CHARGE_FRAME_SIZE_POSITION_X.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_POSITION_Y.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_WIDTH.getSize(),
				ChargeEnum.CHARGE_FRAME_SIZE_HEIGHT.getSize()
		);
		
		this.add(chargePanel);
		this.setTitle("ÃæÀü");
		this.setResizable(false);
		this.setVisible(true);
		
	}
	

	
	public static void main(String[] args) {
		new ChargeFrame();
	}
}
