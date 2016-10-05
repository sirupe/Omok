package drawingBoardPanel;

import java.awt.Color;

import javax.swing.JPanel;

import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardFrame.DrawingBoaradFrame;

//그림을 그리는 패널
public class DrawingBoardPanel extends JPanel{
	
	
	public DrawingBoardPanel(DrawingBoaradFrame drawingBoaradFrame) {
		this.setBounds(DrawingBoardFrameEnum.DRAWINGBOARD_PANEL_RECT.getRectangle());
		this.setVisible(true);
		//TODO 지우기
		this.setBackground(Color.MAGENTA);
	}

}
