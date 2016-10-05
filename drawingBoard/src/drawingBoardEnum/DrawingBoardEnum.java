package drawingBoardEnum;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public enum DrawingBoardEnum {
	
	//��ü ��ũ�� ������
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
//	//DRAWINGBOARD��������  x, y ��ġ�� ����, ���� ������
	DRAWINGBOARD_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.55)),
	DRAWINGBOARD_SIZE_HEIGHT((int)(DRAWINGBOARD_SIZE_WIDTH.getSize() * 0.65)),	
	DRAWINGBOARD_SIZE_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (DRAWINGBOARD_SIZE_WIDTH.getSize() / 2 ))),
	DRAWINGBOARD_SIZE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (DRAWINGBOARD_SIZE_HEIGHT.getSize() / 2))),
	
	//DRAWINGBOARD��������  x, y ��ġ�� ����, ���� ������ RECT
	DRAWINGBOARD_FRMAE_RECT(new Rectangle(
			(int)(DRAWINGBOARD_SIZE_POSITION_X.getSize()),
			(int)(DRAWINGBOARD_SIZE_POSITION_Y.getSize()),
			(int)(DRAWINGBOARD_SIZE_WIDTH.getSize()),
			(int)(DRAWINGBOARD_SIZE_HEIGHT.getSize())
	));
	
	//DrawingTypePanel�г��� x, y ��ġ��  ����, ���� ������
//	DRAWINGTYPE_PANEL_RECT(new Rectangle(
//			(int)(DRAWINGBOARD_FRMAE_RECT),
//			(int)(),
//			(int)(),
//			(int)(),
//	));
	
	
	
	
	
	
	
	
	
	private Dimension dimension; //����,���� ���� ������ Ŭ����
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
