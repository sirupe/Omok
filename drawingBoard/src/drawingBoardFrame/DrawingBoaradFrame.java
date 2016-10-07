package drawingBoardFrame;

import javax.swing.JFrame;
import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardPanel.CanvasPanel;
import drawingBoardPanel.DrawingPositionPanel;
import drawingBoardPanel.DrawingTypePanel;

@SuppressWarnings("serial")
public class DrawingBoaradFrame extends JFrame{
	
	private CanvasPanel canvasPanel;       //�׸���
	private DrawingTypePanel drawingTypePanel;         //��,��,�簢��,�ձٻ簢��,��,���찳,�׸���,ä���
	private DrawingPositionPanel drawingPositionPanel; //x,y,z��ǥ��,����, 
	
	
	public DrawingBoaradFrame() {
		this.setBounds(DrawingBoardFrameEnum.DRAWINGBOARD_FRMAE_RECT.getRectangle());
		
		this.canvasPanel = new CanvasPanel(this);
		this.drawingTypePanel = new DrawingTypePanel(this);
		this.drawingPositionPanel = new DrawingPositionPanel(this);
									//DrawingPositionPanel�����ڸżҵ尡
									//this(DrawingBoaradFrame)�� �Ű������� ���� ����
									//drawingPositionPanel �� �ش�
		
		this.add(canvasPanel);
		this.add(drawingTypePanel);
		this.add(drawingPositionPanel);
		this.setTitle("DrawingBoard");
		this.setLayout(null); 			//������ BorderLayout �� ���� ���� �ֱ� ������ null�� ���ش�.
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
