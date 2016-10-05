package drawingBoardPanel;

import java.awt.Color;

import javax.swing.JPanel;

import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardFrame.DrawingBoaradFrame;

//x,y,zÁÂÇ¥°ª,»ö±ò, 
public class DrawingPositionPanel extends JPanel{
	
	
	public DrawingPositionPanel(DrawingBoaradFrame drawingBoaradFrame) {
		
		this.setBounds(DrawingBoardFrameEnum.DRAWINGPOSITION_PANEL_RECT.getRectangle());
		this.setLayout(null);
		this.setVisible(true);
		//TODO Áö¿ì±â
		this.setBackground(Color.BLACK);
	}
	
}
