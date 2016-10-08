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

//�׸��� �׸��� �г�
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
				lb.setText("���콺 ��ġ : " + "X :"+ e.getX() + "  Y : " + e.getY());
			}

			public void mouseReleased(MouseEvent e){
				isDrag = false; //�巡�� ��
			}
			
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();	//���� ����x
				y1 = e.getY();	//���� ����y
				drawingPositionPanel.getX1Value().setText(e.getX() + "");	//x1TF�� ���۰��� ����
				drawingPositionPanel.getY1Value().setText(e.getX() + "");	//y1TF�� ���۰��� ����
			}
			
			@Override 
			public void mouseDragged(MouseEvent e){
				isDrag = true; //�巡����
				x2 = e.getX();	//���� ����x
				y2 = e.getY();	//���� ����y
				drawingPositionPanel.getX2Value().setText(e.getX() + "");	//x2TF�� ���ᰪ�� ����
				drawingPositionPanel.getY2Value().setText(e.getX() + "");	//y2TF�� ���ᰪ�� ����
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
