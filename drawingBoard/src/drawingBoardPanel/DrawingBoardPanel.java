package drawingBoardPanel;

import java.awt.Color;

import javax.swing.JPanel;

import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardFrame.DrawingBoaradFrame;

//�׸��� �׸��� �г�
public class DrawingBoardPanel extends JPanel{
	
	
	public DrawingBoardPanel(DrawingBoaradFrame drawingBoaradFrame) {
		this.setBounds(DrawingBoardFrameEnum.DRAWINGBOARD_PANEL_RECT.getRectangle());
		this.setVisible(true);
		//TODO �����
		this.setBackground(Color.MAGENTA);
	}

}
