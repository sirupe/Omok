package drawingBoardEnum;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public enum DrawingBoardEnum {
	
	//전체 스크린 사이즈
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
//	//DRAWINGBOARD프레임의  x, y 위치와 가로, 세로 사이즈
	DRAWINGBOARD_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.55)),
	DRAWINGBOARD_SIZE_HEIGHT((int)(DRAWINGBOARD_SIZE_WIDTH.getSize() * 0.65)),	
	DRAWINGBOARD_SIZE_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (DRAWINGBOARD_SIZE_WIDTH.getSize() / 2 ))),
	DRAWINGBOARD_SIZE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (DRAWINGBOARD_SIZE_HEIGHT.getSize() / 2))),
	
	//DRAWINGBOARD프레임의  x, y 위치와 가로, 세로 사이즈 RECT
	DRAWINGBOARD_FRMAE_RECT(new Rectangle(
			(int)(DRAWINGBOARD_SIZE_POSITION_X.getSize()),
			(int)(DRAWINGBOARD_SIZE_POSITION_Y.getSize()),
			(int)(DRAWINGBOARD_SIZE_WIDTH.getSize()),
			(int)(DRAWINGBOARD_SIZE_HEIGHT.getSize())
	));
	
	//DrawingTypePanel패널의 x, y 위치와  가로, 세로 사이즈
//	DRAWINGTYPE_PANEL_RECT(new Rectangle(
//			(int)(DRAWINGBOARD_FRMAE_RECT),
//			(int)(),
//			(int)(),
//			(int)(),
//	));
	
	
	
	
	
	
	
	
	
	private Dimension dimension; //가로,세로 값을 가지는 클래스
	private int size;
	private Rectangle rectangle;
	
	private DrawingBoardEnum() {}
	
	//set
	private DrawingBoardEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	private DrawingBoardEnum(int x) {
		this.size = x;
	}
	private DrawingBoardEnum(Rectangle rectangle) {
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
