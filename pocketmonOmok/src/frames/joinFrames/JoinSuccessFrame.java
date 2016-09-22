package frames.joinFrames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.JoinSizesEnum;

//은정/
@SuppressWarnings("serial")
public class JoinSuccessFrame extends JFrame {
	private JLabel textLabel;
	private JButton checkButton;
	private JoinFrame joinFrame;
	
	public JoinSuccessFrame(JoinFrame joinFrame, String message) {
		this.textLabel = new JLabel(message);
		this.checkButton = new JButton("확인");
		this.joinFrame = joinFrame;
		this.setFrame();
		this.setComp();
	}
	
	public void setComp() {
		this.textLabel.setBounds(
				JoinSizesEnum.JOIN_SUCCESS_LABEL_X.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_LABEL_Y.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_LABEL_WIDTH.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_LABEL_HEIGHT.getSize()
		);
		
		this.checkButton.setBounds(
				JoinSizesEnum.JOIN_SUCCESS_BUTTON_X.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_BUTTON_Y.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_BUTTON_WIDTH.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_BUTTON_HEIGHT.getSize()
		);
		this.checkButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					dispose();
					joinFrame.getLoginPanel().getBasicFrame().setVisible(true);
				}
			}
		);
		
		this.add(this.textLabel);
		this.add(this.checkButton);
	}
	
	public void setFrame() {
		this.setLayout(null);
		this.setBounds(
				JoinSizesEnum.JOIN_SUCCESS_FRAME_X.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_FRAME_Y.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_FRAME_WIDTH.getSize(),
				JoinSizesEnum.JOIN_SUCCESS_FRAME_HEIGHT.getSize()
		);
		this.getContentPane().setBackground(Color.white);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
