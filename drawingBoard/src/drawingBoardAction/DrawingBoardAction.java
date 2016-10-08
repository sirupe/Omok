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
//		this.canvasPanel.getLb().setText("마우스 위치  : " + " X : " + e.getX() + "Y : " + e.getY());
//	}
//	
//	public void mouseReleased(MouseEvent e){
//		this.isDrag = false; //드래그 땜
//	}
//	
//	public void mousePressed(MouseEvent e) {
//		x1 = e.getX();	//시작 지점x
//		y1 = e.getY();	//시작 지점y
//		drawingPositionPanel.getX1Value().setText(e.getX() + "");	//x1TF에 시작값을 저장
//		drawingPositionPanel.getY1Value().setText(e.getX() + "");	//y1TF에 시작값을 저장
//	}
//	
//	public void mouseDragged(MouseEvent e){
//		isDrag = true; //드래그중
//		x2 = e.getX();	//종료 지점x
//		y2 = e.getY();	//종료 지점y
//		drawingPositionPanel.getX2Value().setText(e.getX() + "");	//x2TF에 종료값을 저장
//		drawingPositionPanel.getY2Value().setText(e.getX() + "");	//y2TF에 종료값을 저장
//	}
//	
//	
//	//버튼 매소드
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		
//	}
//	
//	
//	
//}