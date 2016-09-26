package frames.store;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.ChargeEnum;
import enums.frames.CorrectEnum;
import enums.frames.SearchIdEnum;

public class PayConfirmFrame extends JFrame{
	private Image backGround;
	private JLabel success;
	private JButton confirm;
	
	public PayConfirmFrame() throws IOException {
		this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
                Image.SCALE_SMOOTH);

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		this.setBounds(
				ChargeEnum.CHARGE_CONFIRM_FRAME_SIZE_POSITION_X.getSize(),
				ChargeEnum.CHARGE_CONFIRM_FRAME_SIZE_POSITION_Y.getSize(),
				ChargeEnum.CHARGE_CONFIRM_FRAME_SIZE_WIDTH.getSize(),
				ChargeEnum.CHARGE_CONFIRM_FRAME_SIZE_HEIGHT.getSize()
		);
		
		this.success = new JLabel("결제 완료 :)");	
		this.success.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.success.setOpaque(false);
		this.success.setBounds(ChargeEnum.CHARGE_SUCCESS_TEXT_SIZE_RECT.getRect());		
		
		this.confirm = new JButton(){
			@Override
	        protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/confirm.kor.png")), 
						0, 
						0, 
						ChargeEnum.CHARGE_CONFIRM_BUTTON_SIZE_RECT.getRect().width,
						ChargeEnum.CHARGE_CONFIRM_BUTTON_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
		        }      
		    }
		};
		this.confirm.setBackground(Color.black);
		this.confirm.setFocusPainted(false);
		this.confirm.setBorderPainted(false);
		this.confirm.setContentAreaFilled(false);
		this.confirm.setBounds(ChargeEnum.CHARGE_CONFIRM_BUTTON_SIZE_RECT.getRect());

		this.add(success);
		this.add(confirm);
		this.setLayout(null);
		this.setTitle("충전");
		this.setResizable(false);
		this.setVisible(true);
		
	}

	public static void main(String[] args) throws IOException {
		new PayConfirmFrame();
	}

}
