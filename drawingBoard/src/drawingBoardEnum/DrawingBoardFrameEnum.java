package drawingBoardEnum;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public enum DrawingBoardFrameEnum {
	
	//전체 스크린 사이즈
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
//	//DRAWINGBOARD프레임의 가로, 세로 , x,y
	DRAWINGBOARD_FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.5)),
	DRAWINGBOARD_FRAME_SIZE_HEIGHT((int)(DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize() * 0.8)),	
	DRAWINGBOARD_FRAME_SIZE_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize() / 2 ))),
	DRAWINGBOARD_FRAME_SIZE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize() / 2))),
	
	//DRAWINGBOARD프레임의  x, y 위치와 가로, 세로 사이즈 RECT
	DRAWINGBOARD_FRMAE_RECT(new Rectangle(
			(int)(DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize()),
			(int)(DRAWINGBOARD_FRAME_SIZE_POSITION_Y.getSize()),
			(int)(DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize()),
			(int)(DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize())
	)),
	
	//DRAWINGPOSITIOPanel패널의 x, y 위치와  가로, 세로 사이즈
	DRAWINGPOSITION_PANEL_RECT(new Rectangle(
			(int)(DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize() * 0.001),
			(int)(DRAWINGBOARD_FRAME_SIZE_POSITION_Y.getSize() * 0.001),
			(int)(DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize()),
			(int)(DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize() * 0.15)
	)),
	
	
	//DrawingTypePanel 패널의 x, y 위치와  가로, 세로 사이즈
	DRAWINGTYPE_PANEL_RECT(new Rectangle(
			(int)(DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize() * 0.001),
			(int)(DRAWINGBOARD_FRAME_SIZE_POSITION_Y.getSize() * 2.827),
			(int)(DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize()),
			(int)(DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize() * 0.15)
	)),
	
	
	//DrawingBoaradFrame 패널의 x, y 위치와  가로, 세로 사이즈
	CANVAS_PANEL_RECT(new Rectangle(
			(int)(DRAWINGBOARD_FRAME_SIZE_POSITION_X.getSize() * 0.001),
			(int)(DRAWINGPOSITION_PANEL_RECT.getRectangle().getHeight()),//타입패널의 세로 길이
			(int)(DRAWINGBOARD_FRAME_SIZE_WIDTH.getSize()),
			(int)(DRAWINGBOARD_FRAME_SIZE_HEIGHT.getSize() * 0.645)
	));
	
	
	
	//color도 해줄거야
	
	
	private Dimension dimension; //가로,세로 값을 가지는 클래스
	private int size;
	private Rectangle rectangle;
	
	private DrawingBoardFrameEnum() {}
	
	//set
	private DrawingBoardFrameEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	private DrawingBoardFrameEnum(int x) {
		this.size = x;
	}
	private DrawingBoardFrameEnum(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	
	//get
	public Dimension getDimension() {
		return dimension;
	}
	
	public int getSize() {
		return size;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}


}
