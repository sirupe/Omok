package drawingBoardPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardEnum.DrawingTypePanelEnum;
import drawingBoardFrame.DrawingBoaradFrame;

//선,원,사각형,둥근사각형,펜,지우개,그리기,채우기
public class DrawingTypePanel extends JPanel {
	private JButton line;			 //선
	private JButton circle;			 //원
	private JButton square;			 //사각형
	private JButton round;			 //둥근사각형
	private JButton pen;			 //펜
	private JButton eraser;			 //지우개
	
	private JLabel fill;			 //"채우기"
	private	JRadioButton fillButton; //채우기 버튼
	private JLabel draw;			 //"그리기"
	private	JRadioButton drawButton; //그리기 버튼
	
	
	
	public DrawingTypePanel(DrawingBoaradFrame drawingBoaradFrame) {
		
		this.ButtonsFunction();
		this.fillFunction();
		this.drawFunction();
		
		this.setBounds(DrawingBoardFrameEnum.DRAWINGTYPE_PANEL_RECT.getRectangle());
		this.setVisible(true);
		this.setLayout(null);
		//TODO 지우기
		this.setBackground(Color.BLUE);
		
	}
	
	public void ButtonsFunction(){
		this.line = new JButton("선");
		this.circle = new JButton("원");
		this.square = new JButton("동글");
		this.round = new JButton("둥근사각");
		this.pen = new JButton("펜");
		this.eraser = new JButton("지우개");
		
		this.line.setBounds(DrawingTypePanelEnum.LINE_JBUTTON_RECT.getRectangle());
		this.circle.setBounds(DrawingTypePanelEnum.CIRCLE_JBUTTON_RECT.getRectangle());
		this.square.setBounds(DrawingTypePanelEnum.SQUARE_JBUTTON_RECT.getRectangle());
		this.round.setBounds(DrawingTypePanelEnum.ROUND_JBUTTON_RECT.getRectangle());
		this.pen.setBounds(DrawingTypePanelEnum.PEN_JBUTTON_RECT.getRectangle());
		this.eraser.setBounds(DrawingTypePanelEnum.ERASER_JBUTTON_RECT.getRectangle());
		
		
		
		this.add(line);
		this.add(circle);
		this.add(square);
		this.add(round);
		this.add(pen);
		this.add(eraser);
	}
	
	//채우기 기능
	public void fillFunction() {
		this.fill = new JLabel("채우기");
		this.fillButton = new JRadioButton();
		
	}
	
	//그리기 기능
	public void drawFunction() {
		this.draw = new JLabel("그리기");
		this.drawButton = new JRadioButton();
			
	}

	

}
