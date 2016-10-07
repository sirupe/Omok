package drawingBoardEnum;

import java.awt.Rectangle;

public enum DrawingTypePanelEnum {
	//DRAWINGPOSITION PANEL�� x,y,����,����
	DRAWINGTYPE_PANEL_RECT(new Rectangle(
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_Y.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize()
	)),
	
	
	//�� ��ư �� x,y,����,����
	LINE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	
	//�� ��ư �� x,y,����,����
	CIRCLE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.12),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//�簢�� ��ư �� x,y,����,����
	SQUARE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.23),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//�ձٻ簢�� ��ư �� x,y,����,����
	ROUND_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.34),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//�� ��ư �� x,y,����,����
	PEN_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.45),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//���찳 ��ư �� x,y,����,����
	ERASER_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.56),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	
	//===========================ä���================================
	FILL_JRADIOBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.74),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.055),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.025),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.03)
	)),
	
	FILL_JLABEL_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.78),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	
	//===========================�׸���================================
	DRAW_JRADIOBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.85),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.055),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.025),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.03)
	)),
	
	DRAW_JLABEL_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.89),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	));
	
	//�̹����̳ѵ� ���� �־��ֻ���
	
	private int size;
	private Rectangle rectangle;
	
	private DrawingTypePanelEnum() {}
	
	//set
	private DrawingTypePanelEnum(int x) {
		this.size = x;
	}
	private DrawingTypePanelEnum(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	
	//get
	
	public int getSize() {
		return size;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	

}
