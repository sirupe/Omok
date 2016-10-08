//package drawingBoardAction;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//
//import drawingBoardPanel.CanvasPanel;
//import drawingBoardPanel.DrawingPositionPanel;
//
//public class DrawingBoardAction implements ActionListener  {
//	
//	private DrawingPositionPanel drawingPositionPanel;
//	private CanvasPanel canvasPanel;
//	private boolean isDrag;
//	int x1,x2,y1,y2;
//	
//	public DrawingBoardAction(CanvasPanel canvasPanel) {
//		this.canvasPanel = canvasPanel;
//		
//	}
//	
//	public void mouseMoved(MouseEvent e) {
//		this.canvasPanel.getLb().setText("���콺 ��ġ  : " + " X : " + e.getX() + "Y : " + e.getY());
//	}
//	
//	public void mouseReleased(MouseEvent e){
//		this.isDrag = false; //�巡�� ��
//	}
//	
//	public void mousePressed(MouseEvent e) {
//		x1 = e.getX();	//���� ����x
//		y1 = e.getY();	//���� ����y
//		drawingPositionPanel.getX1Value().setText(e.getX() + "");	//x1TF�� ���۰��� ����
//		drawingPositionPanel.getY1Value().setText(e.getX() + "");	//y1TF�� ���۰��� ����
//	}
//	
//	public void mouseDragged(MouseEvent e){
//		isDrag = true; //�巡����
//		x2 = e.getX();	//���� ����x
//		y2 = e.getY();	//���� ����y
//		drawingPositionPanel.getX2Value().setText(e.getX() + "");	//x2TF�� ���ᰪ�� ����
//		drawingPositionPanel.getY2Value().setText(e.getX() + "");	//y2TF�� ���ᰪ�� ����
//	}
//	
//	
//	//��ư �żҵ�
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		
//	}
//	
//	
//	
//}