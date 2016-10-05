package drawingBoardFrame;

import javax.swing.JFrame;
import drawingBoardEnum.DrawingBoardEnum;

public class DrawingBoaradFrame extends JFrame{
	
	
	public DrawingBoaradFrame() {
		this.setBounds(DrawingBoardEnum.DRAWINGBOARD_FRMAE_RECT.getRectangle());
		this.setTitle("DrawingBoard");
		this.setLayout(null);
		this.setVisible(true);
	
		
		
	}
	
	public static void main(String[] args) {
		new DrawingBoaradFrame();
	}

}
