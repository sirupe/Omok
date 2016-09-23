package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

public enum GamePayRoomEnum {
Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	GAME_PAY_ROOM_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	GAME_PAY_ROOM_HEIGHT((int)(GAME_PAY_ROOM_WIDTH.getSize() * 1.2)),	
	GAME_PAY_ROOM_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (GAME_PAY_ROOM_WIDTH.getSize() / 2 ))),
	GAME_PAY_ROOM_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (GAME_PAY_ROOM_HEIGHT.getSize() / 2))),
	//===========================================================================
	
	
	//일반 폰트
	LABELFONT_DEFAULT(new Font("a으라차차",Font.BOLD,17)),
	// 일반색깔
	LABELCOLOR_DEFAULT(Color.gray),
	//텍스트 테두리 없애기
	LABEL_DEFAULT(new EmptyBorder(0,0,0,0));

	private Dimension dimension;
	private int size;
	private Font font;
	private Color color;
	private Rectangle rec;
	private EmptyBorder emptyBorder;
	
	private GamePayRoomEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	private GamePayRoomEnum(int size) {
		this.size = size;
	}
	private GamePayRoomEnum(Font font) {
		this.font = font;
	}
	private GamePayRoomEnum(Color color) {
		this.color = color;
	}
	private GamePayRoomEnum(Rectangle rec) {
		this.rec = rec;
	}
	private GamePayRoomEnum(EmptyBorder emptyBorder) {
		this.emptyBorder = emptyBorder;
	}
	public EmptyBorder getEmptyBorder() {
		return emptyBorder;
	}
	public Rectangle getRectangle() {
		return rec;
	}
	public Color getColor() {
		return color;
	}
	public Font getFont() {
		return font;
	}
	private Dimension getDimension() {
		return dimension;
	}
	public int getSize() {
		return size;
	}
	
}
