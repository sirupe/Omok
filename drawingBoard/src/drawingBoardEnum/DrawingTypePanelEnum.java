package drawingBoardEnum;

import java.awt.Rectangle;

public enum DrawingTypePanelEnum {
	//DRAWINGPOSITION PANEL의 x,y,가로,세로
	DRAWINGTYPE_PANEL_RECT(new Rectangle(
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_Y.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize()
	)),
	
	
	//선 버튼 의 x,y,가로,세로
	LINE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	
	//원 버튼 의 x,y,가로,세로
	CIRCLE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.12),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//사각형 버튼 의 x,y,가로,세로
	SQUARE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.23),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//둥근사각형 버튼 의 x,y,가로,세로
	ROUND_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.34),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//펜 버튼 의 x,y,가로,세로
	PEN_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.45),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	//지우개 버튼 의 x,y,가로,세로
	ERASER_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.56),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getWidth() * 0.1),
			(int)(DRAWINGTYPE_PANEL_RECT.getRectangle().getHeight() * 0.12)
	)),
	
	//===========================채우기================================
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
	
	//===========================그리기================================
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
	
	//이미지이넘도 여기 넣어주생여
	
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
