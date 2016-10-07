package drawingBoardPanel;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import drawingBoardEnum.DrawingBoardFrameEnum;
import drawingBoardEnum.DrawingPositionPanelEnum;
import drawingBoardFrame.DrawingBoaradFrame;

//x,y,z좌표값,색깔, 
@SuppressWarnings("serial")
public class DrawingPositionPanel extends JPanel{
	private CanvasPanel canvasPanel;

	private JLabel x1Label;
	private JLabel x2Label;
	private JLabel y1Label;
	private JLabel y2Label;
	private JLabel z1Label;
	private JLabel z2Label;
	
	private JTextField x1Value; //시작지점 x값
	private JTextField x2Value; //종료지점 x값
	private JTextField y1Value; //시작지점 y값
	private JTextField y2Value; //종료지점 y값
	private JTextField z1Value; //시작지점 z값
	private JTextField z2Value; //종료지점 z값
	
	private JLabel color;				
	private JComboBox<String> colors;
	
	
	public DrawingPositionPanel(DrawingBoaradFrame drawingBoaradFrame) {
		
		this.positionValue();
		this.colors();
		
		
		this.setBounds(DrawingBoardFrameEnum.DRAWINGPOSITION_PANEL_RECT.getRectangle());
		this.setLayout(null);
		this.setVisible(true);
		//TODO 지우기
		this.setBackground(Color.PINK);
	}
	
	public void positionValue() {
		
		//LABEL 
		this.x1Label = new JLabel("X1");
		this.x2Label = new JLabel("X2");
		this.y1Label = new JLabel("Y1");
		this.y2Label = new JLabel("Y2");
		this.z1Label = new JLabel("Z1");
		this.z2Label = new JLabel("Z2");
		
		//LABEL위치
		this.x1Label.setBounds(DrawingPositionPanelEnum.X1_LABEL.getRectangle());
		this.x2Label.setBounds(DrawingPositionPanelEnum.X2_LABEL.getRectangle());
		this.y1Label.setBounds(DrawingPositionPanelEnum.Y1_LABEL.getRectangle());
		this.y2Label.setBounds(DrawingPositionPanelEnum.Y2_LABEL.getRectangle());
		this.z1Label.setBounds(DrawingPositionPanelEnum.Z1_LABEL.getRectangle());
		this.z2Label.setBounds(DrawingPositionPanelEnum.Z2_LABEL.getRectangle());
		
		//폰트
		this.x1Label.setFont(DrawingPositionPanelEnum.JLABEL_FONT_SIZE.getFont());
		this.x2Label.setFont(DrawingPositionPanelEnum.JLABEL_FONT_SIZE.getFont());
		this.y1Label.setFont(DrawingPositionPanelEnum.JLABEL_FONT_SIZE.getFont());
		this.y2Label.setFont(DrawingPositionPanelEnum.JLABEL_FONT_SIZE.getFont());
		this.z1Label.setFont(DrawingPositionPanelEnum.JLABEL_FONT_SIZE.getFont());
		this.z2Label.setFont(DrawingPositionPanelEnum.JLABEL_FONT_SIZE.getFont());
		
		//VALUE
		this.x1Value = new JTextField();
		this.x2Value = new JTextField();
		this.y1Value = new JTextField();
		this.y2Value = new JTextField();
		this.z1Value = new JTextField();
		this.z2Value = new JTextField();
		
		//VALUE위치
		this.x1Value.setBounds(DrawingPositionPanelEnum.X1_VALUE.getRectangle());
		this.x2Value.setBounds(DrawingPositionPanelEnum.X2_VALUE.getRectangle());
		this.y1Value.setBounds(DrawingPositionPanelEnum.Y1_VALUE.getRectangle());
		this.y2Value.setBounds(DrawingPositionPanelEnum.Y2_VALUE.getRectangle());
		this.z1Value.setBounds(DrawingPositionPanelEnum.Z1_VALUE.getRectangle());
		this.z2Value.setBounds(DrawingPositionPanelEnum.Z2_VALUE.getRectangle());
		
		
		this.add(x1Label);
		this.add(x2Label);
		this.add(y1Label);
		this.add(y2Label);
		this.add(z1Label);
		this.add(z2Label);
		this.add(x1Value);
		this.add(x2Value);
		this.add(y1Value);
		this.add(y2Value);
		this.add(z1Value);
		this.add(z2Value);
	}
	
	public void colors(){
		
		String[] rainbow = {
				"빨강", "주황", "노랑", "초록", "파랑", "보라"
		};
		
		this.color = new JLabel("COLOR");
		this.color.setBounds(DrawingPositionPanelEnum.COLOR_LABEL.getRectangle());
		this.color.setFont(DrawingPositionPanelEnum.JLABEL_COLOR_FONT_SIZE.getFont());
		this.colors = new JComboBox<String>(rainbow);
		this.colors.setBounds(DrawingPositionPanelEnum.COLOR_COMBOBOX.getRectangle());
		this.colors.setFont(DrawingPositionPanelEnum.JLABEL_COLOR_FONT_SIZE.getFont());
		
		this.add(color);
		this.add(colors);
	}

	//get, set
	public JTextField getX1Value() {
		return x1Value;
	}

	public JTextField getX2Value() {
		return x2Value;
	}

	public JTextField getY1Value() {
		return y1Value;
	}

	public JTextField getY2Value() {
		return y2Value;
	}

	public JTextField getZ1Value() {
		return z1Value;
	}

	public JTextField getZ2Value() {
		return z2Value;
	}
	
	public CanvasPanel canvasPanel() {
		return canvasPanel;
	}
	
	
}
