package drawingBoardFrame;

import javax.swing.JFrame;
import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardPanel.DrawingBoardPanel;
import drawingBoardPanel.DrawingPositionPanel;
import drawingBoardPanel.DrawingTypePanel;

public class DrawingBoaradFrame extends JFrame{
	
	private DrawingBoardPanel drawingBoardPanel;       //�׸���
	private DrawingTypePanel drawingTypePanel;         //��,��,�簢��,�ձٻ簢��,��,���찳,�׸���,ä���
	private DrawingPositionPanel drawingPositionPanel; //x,y,z��ǥ��,����, 
	
	
	public DrawingBoaradFrame() {
		this.setBounds(DrawingBoardFrameEnum.DRAWINGBOARD_FRMAE_RECT.getRectangle());
		this.setTitle("DrawingBoard");
		
		this.drawingBoardPanel = new DrawingBoardPanel(this);
		this.drawingTypePanel = new DrawingTypePanel(this);
		this.drawingPositionPanel = new DrawingPositionPanel(this);
									//DrawingPositionPanel�����ڸżҵ尡
									//this(DrawingBoaradFrame)�� �Ű������� ���� ����
									//drawingPositionPanel �� �ش�
		
		this.add(drawingBoardPanel);
		this.add(drawingTypePanel);
		this.add(drawingPositionPanel);
		this.setLayout(null); 			//������ BorderLayout �� ���� ���� �ֱ� ������ null�� ���ش�.
		this.setVisible(true);
	}
	
	
	//main
	public static void main(String[] args) {
		new DrawingBoaradFrame();
		
	}

}
