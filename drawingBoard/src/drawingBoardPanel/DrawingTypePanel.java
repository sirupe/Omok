package drawingBoardPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardEnum.DrawingTypePanelEnum;
import drawingBoardFrame.DrawingBoaradFrame;

//��,��,�簢��,�ձٻ簢��,��,���찳,�׸���,ä���
public class DrawingTypePanel extends JPanel {
	private JButton line;			 //��
	private JButton circle;			 //��
	private JButton square;			 //�簢��
	private JButton round;			 //�ձٻ簢��
	private JButton pen;			 //��
	private JButton eraser;			 //���찳
	
	private JLabel fill;			 //"ä���"
	private	JRadioButton fillButton; //ä��� ��ư
	private JLabel draw;			 //"�׸���"
	private	JRadioButton drawButton; //�׸��� ��ư
	
	
	
	public DrawingTypePanel(DrawingBoaradFrame drawingBoaradFrame) {
		
		this.ButtonsFunction();
		this.fillFunction();
		this.drawFunction();
		
		this.setBounds(DrawingBoardFrameEnum.DRAWINGTYPE_PANEL_RECT.getRectangle());
		this.setVisible(true);
		this.setLayout(null);
		//TODO �����
		this.setBackground(Color.BLUE);
		
	}
	
	public void ButtonsFunction(){
		this.line = new JButton("��");
		this.circle = new JButton("��");
		this.square = new JButton("����");
		this.round = new JButton("�ձٻ簢");
		this.pen = new JButton("��");
		this.eraser = new JButton("���찳");
		
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
	
	//ä��� ���
	public void fillFunction() {
		this.fill = new JLabel("ä���");
		this.fillButton = new JRadioButton();
		
	}
	
	//�׸��� ���
	public void drawFunction() {
		this.draw = new JLabel("�׸���");
		this.drawButton = new JRadioButton();
			
	}

	

}
