package drawingBoardEnum;

import java.awt.Rectangle;

public enum DrawingTypePanelEnum {
	//DRAWINGPOSITION PANEL�� x,y,����,����
	DRAWINGPOSITION_PANEL_RECT(new Rectangle(
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_Y.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize()
	)),
	
	
	//�� ��ư �� x,y,����,����
	LINE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	
	//�� ��ư �� x,y,����,����
	CIRCLE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.11),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	//�簢�� ��ư �� x,y,����,����
	SQUARE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.21),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	//�ձٻ簢�� ��ư �� x,y,����,����
	ROUND_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.31),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	//�� ��ư �� x,y,����,����
	PEN_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.41),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	//���찳 ��ư �� x,y,����,����
	ERASER_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.51),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
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
