package drawingBoardPanel;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import drawingBoardDTO.DrawingBoardDTO;
import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardFrame.DrawingBoaradFrame;

//그림을 그리는 패널
@SuppressWarnings("serial")
public class CanvasPanel extends JPanel{
	private DrawingPositionPanel drawingPositionPanel;
	private Label lb;
	
	private boolean isDrag;
	int x1,x2,y1,y2;
	
	
	public CanvasPanel(DrawingBoaradFrame drawingBoaradFrame) {
		
		this.lb = new Label();
		this.lb.setBounds(5, 5, 200, 20);
		
		this.add(lb);
		this.setLayout(null);
		this.setBounds(DrawingBoardFrameEnum.CANVAS_PANEL_RECT.getRectangle());
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				lb.setText("마우스 위치 : " + "X :"+ e.getX() + "  Y : " + e.getY());
			}

			public void mouseReleased(MouseEvent e){
				isDrag = false; //드래그 땜
			}
			
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();	//시작 지점x
				y1 = e.getY();	//시작 지점y
				drawingPositionPanel.getX1Value().setText(e.getX() + "");	//x1TF에 시작값을 저장
				drawingPositionPanel.getY1Value().setText(e.getX() + "");	//y1TF에 시작값을 저장
			}
			
			@Override 
			public void mouseDragged(MouseEvent e){
				isDrag = true; //드래그중
				x2 = e.getX();	//종료 지점x
				y2 = e.getY();	//종료 지점y
				drawingPositionPanel.getX2Value().setText(e.getX() + "");	//x2TF에 종료값을 저장
				drawingPositionPanel.getY2Value().setText(e.getX() + "");	//y2TF에 종료값을 저장
			}
		});
	}

	public DrawingPositionPanel getDrawingPositionPanel() {
		return drawingPositionPanel;
	}

	public Label getLb() {
		return lb;
	}
	
	
}
