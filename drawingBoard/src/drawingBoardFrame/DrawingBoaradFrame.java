package drawingBoardFrame;

import javax.swing.JFrame;
import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardPanel.DrawingBoardPanel;
import drawingBoardPanel.DrawingPositionPanel;
import drawingBoardPanel.DrawingTypePanel;

public class DrawingBoaradFrame extends JFrame{
	
	private DrawingBoardPanel drawingBoardPanel;       //그림판
	private DrawingTypePanel drawingTypePanel;         //선,원,사각형,둥근사각형,펜,지우개,그리기,채우기
	private DrawingPositionPanel drawingPositionPanel; //x,y,z좌표값,색깔, 
	
	
	public DrawingBoaradFrame() {
		this.setBounds(DrawingBoardFrameEnum.DRAWINGBOARD_FRMAE_RECT.getRectangle());
		this.setTitle("DrawingBoard");
		
		this.drawingBoardPanel = new DrawingBoardPanel(this);
		this.drawingTypePanel = new DrawingTypePanel(this);
		this.drawingPositionPanel = new DrawingPositionPanel(this);
									//DrawingPositionPanel생성자매소드가
									//this(DrawingBoaradFrame)을 매개변수로 가진 값을
									//drawingPositionPanel 에 준다
		
		this.add(drawingBoardPanel);
		this.add(drawingTypePanel);
		this.add(drawingPositionPanel);
		this.setLayout(null); 			//기존의 BorderLayout 의 값을 갖고 있기 때문에 null을 해준다.
		this.setVisible(true);
	}
	
	
	//main
	public static void main(String[] args) {
		new DrawingBoaradFrame();
		
	}

}
