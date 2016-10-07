package drawingBoardEnum;

import java.awt.Font;
import java.awt.Rectangle;


public enum DrawingPositionPanelEnum {
	
	DRAWINGPOSITION_PANEL_RECT(new Rectangle(
			(DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize()),
			(DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_Y.getSize()),
			(DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize()),
			(DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize())
	)),
	
//x,y,z ��=====================================================================
	//x1 �� �� x,y,����,����
	X1_LABEL(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//x2 �� �� x,y,����,����
	X2_LABEL(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.14),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//y1 �� �� x,y,����,����
	Y1_LABEL(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.27),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//y2 �� �� x,y,����,����
	Y2_LABEL(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.4),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//z1 �� �� x,y,����,����
	Z1_LABEL(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.535),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//z2 �� �� x,y,����,����
	Z2_LABEL(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.657),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	
//x,y,z �ؽ�Ʈ �ʵ�=====================================================================
	//x1 �ؽ�Ʈ�ʵ� �� x,y,����,����
	X1_VALUE(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.05),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.08),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05)
	)),
	//x2 �ؽ�Ʈ�ʵ� �� x,y,����,����
	X2_VALUE(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.18),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.08),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05)
	)),
	//y1 �ؽ�Ʈ�ʵ� �� x,y,����,����
	Y1_VALUE(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.31),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.08),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05)
	)),
	//y2 �ؽ�Ʈ�ʵ� �� x,y,����,����
	Y2_VALUE(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.445),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.08),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05)
	)),
	//z1 �ؽ�Ʈ�ʵ� �� x,y,����,����
	Z1_VALUE(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.575),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.08),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05)
	)),
	//z2 �ؽ�Ʈ�ʵ� �� x,y,����,����
	Z2_VALUE(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.7),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.08),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05)
	)),
	
//COLORS �ʵ�=====================================================================	
	COLOR_LABEL(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.84),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.025),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05)
	)),
	COLOR_COMBOBOX(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.82),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.07),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.13),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.05)
	)),
	
	
	JLABEL_FONT_SIZE(new Font("a��������", Font.PLAIN, 22)),
	JLABEL_COLOR_FONT_SIZE(new Font("a��������", Font.PLAIN, 18));
	
	private int size;
	private Rectangle rectangle;
	private Font font;
	
	private DrawingPositionPanelEnum() {}
	
	//set
	
	private DrawingPositionPanelEnum(int x) {
		this.size = x;
	}
	
	private DrawingPositionPanelEnum(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	private DrawingPositionPanelEnum(Font font) {
		this.font = font;
	}
	
	//get
	public int getSize() {
		return size;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	public Font getFont() {
		return font;
	}
	
}
