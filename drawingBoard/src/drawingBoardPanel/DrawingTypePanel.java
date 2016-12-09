package drawingBoardPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	private JLabel fillLabel;			 //"ä���"
	private	JCheckBox fillButton; //ä��� ��ư
	private JLabel drawLabel;			 //"�׸���"
	private	JCheckBox drawButton; //�׸��� ��ư
	
	
	
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
		this.fillLabel = new JLabel("ä���");
		this.fillButton = new JCheckBox();
		
		this.fillButton.setBounds(DrawingTypePanelEnum.FILL_JRADIOBUTTON_RECT.getRectangle());
		this.fillButton.setOpaque(false);
		this.fillLabel.setBounds(DrawingTypePanelEnum.FILL_JLABEL_RECT.getRectangle());
		
		this.add(fillLabel);
		this.add(fillButton);
	}
	
	//�׸��� ���
	public void drawFunction() {
		this.drawLabel = new JLabel("�׸���");
		this.drawButton = new JCheckBox();
		
		this.drawButton.setBounds(DrawingTypePanelEnum.DRAW_JRADIOBUTTON_RECT.getRectangle());
		this.drawButton.setOpaque(false);
		this.drawLabel.setBounds(DrawingTypePanelEnum.DRAW_JLABEL_RECT.getRectangle());
		
		this.add(drawLabel);
		this.add(drawButton);
	}
}
