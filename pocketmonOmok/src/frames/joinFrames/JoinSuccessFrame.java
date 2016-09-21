package frames.joinFrames;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.JoinSizesEnum;

public class JoinSuccessFrame extends JFrame {
	private JLabel textLabel;
	private JButton checkButton;
	
	public JoinSuccessFrame() {
		this.textLabel = new JLabel("회원가입이 완료되었습니다.");
		this.checkButton = new JButton("확인");
		this.setComp();
		this.setFrame();
	}
	
	public void setComp() {
//		this.textLabel.setBounds();
	}
	
	public void setFrame() {
		this.setBounds(
				JoinSizesEnum.JOIN_SUCCESS_FRAME_X.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_FRAME_Y.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_FRAME_WIDTH.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_FRAME_HEIGHT.getSize()
		);
		this.getContentPane().setBackground(Color.white);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		new JoinSuccessFrame();
	}
}
