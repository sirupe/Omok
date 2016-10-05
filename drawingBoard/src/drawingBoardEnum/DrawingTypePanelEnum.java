package drawingBoardEnum;

import java.awt.Rectangle;

public enum DrawingTypePanelEnum {
	//DRAWINGPOSITION PANEL의 x,y,가로,세로
	DRAWINGPOSITION_PANEL_RECT(new Rectangle(
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_POSITION_Y.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize(),
			DrawingBoardFrameEnum.DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize()
	)),
	
	
	//선 버튼 의 x,y,가로,세로
	LINE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	
	//원 버튼 의 x,y,가로,세로
	CIRCLE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.11),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	//사각형 버튼 의 x,y,가로,세로
	SQUARE_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.21),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	//둥근사각형 버튼 의 x,y,가로,세로
	ROUND_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.31),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	//펜 버튼 의 x,y,가로,세로
	PEN_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.41),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
	)),
	//지우개 버튼 의 x,y,가로,세로
	ERASER_JBUTTON_RECT(new Rectangle(
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.51),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.01),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getWidth() * 0.09),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight() * 0.13)
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
