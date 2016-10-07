package drawingBoardAction;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import drawingBoardDTO.DrawingBoardDTO;
import drawingBoardPanel.CanvasPanel;
import drawingBoardPanel.DrawingPositionPanel;
import drawingBoardPanel.DrawingTypePanel;

public abstract class DrawingBoardAction extends MouseAdapter implements  ActionListener {
	private DrawingPositionPanel drawingPositionPanel;
	private DrawingTypePanel drawingTypePanel;
	private CanvasPanel canvasPanel;
	
	private ArrayList<DrawingBoardDTO> saveData = new ArrayList<DrawingBoardDTO>();
	
	private boolean isDrag;	//�巡�� ����
	int x1,x2,y1,y2;
	
	public DrawingBoardAction(DrawingPositionPanel drawingPositionPanel) {
		this.drawingPositionPanel = drawingPositionPanel;
		
		this.canvasPanel.addMouseListener(this);  //���� MouseAdapter�� ��ӹް� �ֱ� ������
		
	}
	@Override //��ư ���� �̺�Ʈ �߻��� ȣ�� �Ǵ� �޼ҵ�(�Լ�)
	public void actionPerformed(ActionEvent e) {
		this.canvasPanel.addMouseListener(this);
	}
	

	
	@Override //���콺�� ��������
	public void mousePressed(MouseEvent e) {
		this.x1 = e.getX();	//���� ����x
		this.y1 = e.getY();	//���� ����y
		this.drawingPositionPanel.getX1Value().setText(e.getX() + "");	//x1TF�� ���۰��� ����
		this.drawingPositionPanel.getY1Value().setText(e.getX() + "");	//y1TF�� ���۰��� ����
	}
	
	@Override 
	public void mouseDragged(MouseEvent e){
		this.isDrag = true; //�巡����
		this.x2 = e.getX();	//���� ����x
		this.y2 = e.getY();	//���� ����y
		this.drawingPositionPanel.getX2Value().setText(e.getX() + "");	//x2TF�� ���ᰪ�� ����
		this.drawingPositionPanel.getY2Value().setText(e.getX() + "");	//y2TF�� ���ᰪ�� ����
	}
	
	@Override //���콺�� ���� ȣ��Ǵ� �޼ҵ�
	public void mouseReleased(MouseEvent e){
		this.isDrag = false; //�巡�� ��
	}
	
	
	//�׷��� �̺�Ʈ�� �߻��Ͽ��� �� ȣ���ϴ� �޼ҵ� 
	//�̶� ���� �׸��� �׸��� ���� paint()�޼ҵ��� �Ű������� ���� ���޵Ǵ�
	//Grphics ��ü �׷��� ����� ���� �޼ҵ�� update()�� repaint()�� �ִ�.
	public void paint(Graphics g) {
		g.drawLine(x1,x2,y1,y2);
	}
	
	//�׸����� ����
	public void savaShaper() {
		if(isDrag) { //���콺�� ���� = false�϶� ������ ����
			this.saveData.add(new DrawingBoardDTO(x1,x2,y1,y2));
		}
	}
	
	public void draw(int[] position) {
		this.x1 = position[0];
		
		
	}
	
	//�̹��� ��� ȭ���� �����ϰ� ���� �� ���(�ַ� ȭ���� �������� �������� ���)
	public void update() {}
	//������ paint()�޼ҵ带 �� �� �� ȣ���ϰ� ���� �� ���
	public void repaint() {}
	

	
}
