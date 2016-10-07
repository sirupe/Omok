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
	
	private boolean isDrag;	//드래그 상태
	int x1,x2,y1,y2;
	
	public DrawingBoardAction(DrawingPositionPanel drawingPositionPanel) {
		this.drawingPositionPanel = drawingPositionPanel;
		
		this.canvasPanel.addMouseListener(this);  //내가 MouseAdapter를 상속받고 있기 때문에
		
	}
	@Override //버튼 관련 이벤트 발생시 호출 되는 메소드(함수)
	public void actionPerformed(ActionEvent e) {
		this.canvasPanel.addMouseListener(this);
	}
	

	
	@Override //마우스를 눌렀을때
	public void mousePressed(MouseEvent e) {
		this.x1 = e.getX();	//시작 지점x
		this.y1 = e.getY();	//시작 지점y
		this.drawingPositionPanel.getX1Value().setText(e.getX() + "");	//x1TF에 시작값을 저장
		this.drawingPositionPanel.getY1Value().setText(e.getX() + "");	//y1TF에 시작값을 저장
	}
	
	@Override 
	public void mouseDragged(MouseEvent e){
		this.isDrag = true; //드래그중
		this.x2 = e.getX();	//종료 지점x
		this.y2 = e.getY();	//종료 지점y
		this.drawingPositionPanel.getX2Value().setText(e.getX() + "");	//x2TF에 종료값을 저장
		this.drawingPositionPanel.getY2Value().setText(e.getX() + "");	//y2TF에 종료값을 저장
	}
	
	@Override //마우스를 땔때 호출되는 메소드
	public void mouseReleased(MouseEvent e){
		this.isDrag = false; //드래그 땜
	}
	
	
	//그래픽 이벤트가 발생하였을 때 호출하는 메소드 
	//이때 실제 그림을 그리는 것은 paint()메소드의 매개변수를 통해 전달되는
	//Grphics 객체 그래픽 출력을 위한 메소드로 update()와 repaint()가 있다.
	public void paint(Graphics g) {
		g.drawLine(x1,x2,y1,y2);
	}
	
	//그린것을 저장
	public void savaShaper() {
		if(isDrag) { //마우스를 때면 = false일때 데이터 저장
			this.saveData.add(new DrawingBoardDTO(x1,x2,y1,y2));
		}
	}
	
	public void draw(int[] position) {
		this.x1 = position[0];
		
		
	}
	
	//이미지 출력 화면을 갱신하고 싶을 때 사용(주로 화면의 깜빡임을 막기위해 사용)
	public void update() {}
	//강제로 paint()메소드를 한 번 더 호출하고 싶을 때 사용
	public void repaint() {}
	

	
}
