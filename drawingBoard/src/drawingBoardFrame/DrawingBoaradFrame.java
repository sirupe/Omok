package drawingBoardFrame;

import javax.swing.JFrame;
import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardPanel.CanvasPanel;
import drawingBoardPanel.DrawingPositionPanel;
import drawingBoardPanel.DrawingTypePanel;

@SuppressWarnings("serial")
public class DrawingBoaradFrame extends JFrame{
	
	private CanvasPanel canvasPanel;       //그림판
	private DrawingTypePanel drawingTypePanel;         //선,원,사각형,둥근사각형,펜,지우개,그리기,채우기
	private DrawingPositionPanel drawingPositionPanel; //x,y,z좌표값,색깔, 
	
	
	public DrawingBoaradFrame() {
		this.setBounds(DrawingBoardFrameEnum.DRAWINGBOARD_FRMAE_RECT.getRectangle());
		
		this.canvasPanel = new CanvasPanel(this);
		this.drawingTypePanel = new DrawingTypePanel(this);
		this.drawingPositionPanel = new DrawingPositionPanel(this);
									//DrawingPositionPanel생성자매소드가
									//this(DrawingBoaradFrame)을 매개변수로 가진 값을
									//drawingPositionPanel 에 준다
		
		this.add(canvasPanel);
		this.add(drawingTypePanel);
		this.add(drawingPositionPanel);
		this.setTitle("DrawingBoard");
		this.setLayout(null); 			//기존의 BorderLayout 의 값을 갖고 있기 때문에 null을 해준다.
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	public CanvasPanel getCanvasPanel() {
		return canvasPanel;
	}
	public DrawingTypePanel getDrawingTypePanel() {
		return drawingTypePanel;
	}
	public DrawingPositionPanel getDrawingPositionPanel() {
		return drawingPositionPanel;
	}



	//main
	public static void main(String[] args) {
		new DrawingBoaradFrame();
	}

}
